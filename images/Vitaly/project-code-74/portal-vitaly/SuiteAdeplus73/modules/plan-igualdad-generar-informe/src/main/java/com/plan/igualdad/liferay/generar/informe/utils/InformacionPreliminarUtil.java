package com.plan.igualdad.liferay.generar.informe.utils;

import com.legalplus.liferay.portlet.legalplus.manager.model.CNAES;
import com.legalplus.liferay.portlet.legalplus.manager.service.CNAESLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.dto.RepresentanteDTO;
import com.plan.igualdad.liferay.generar.informe.dto.TableDTO;
import com.plan.igualdad.liferay.generar.informe.enums.Ambito;
import com.plan.igualdad.liferay.portlet.manager.model.Organizacion;
import com.plan.igualdad.liferay.portlet.manager.service.OrganizacionLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class InformacionPreliminarUtil {

	/**
	 * getParameters Informacion Preliminar
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws JSONException 
	 */
	public static Map<String, Object> getParameters (Long compId, Long versionId, ThemeDisplay themeDisplay) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
		Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
        
		parameters.putAll(getParametersDatosOrganizacion(organizacion, bundle));	
		parameters.putAll(getParametersResponsable(organizacion, bundle));
		parameters.putAll(getParametersOrgGestionPersonas(organizacion, bundle));
		parameters.putAll(getParametersComposicionComision(organizacion));
		return parameters;
	}
	
	/**
	 * getParameters Datos Organizacion
	 * @param organizacion
	 * @return
	 */
	private static Map<String, Object> getParametersDatosOrganizacion(Organizacion organizacion, ResourceBundle bundle){
		Map<String, Object> parameters = new HashMap<>();
			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.DOMICILIO_SOCIAL , GeneralUtils.checkParameter(organizacion!=null? organizacion.getDomicilioSocial(): null));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.ANYO_CONSTITUCION , organizacion!=null?  organizacion.getAnnoConstitucion() : StringPool.BLANK);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FACTURACION_ANUAL , GeneralUtils.checkParameter(organizacion!=null?  organizacion.getFacturacionAnual():null));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.PAGINA_WEB , GeneralUtils.checkParameter(organizacion!=null? organizacion.getPaginaWeb():null));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FORMA_JURIDICA, organizacion!=null? organizacion.getFormaJuridica():StringPool.BLANK);
		
		return parameters;
	}
	
	/**
	 * getParameters Responsable
	 * @param organizacion
	 * @param entidad
	 * @return
	 */
	private static Map<String, Object> getParametersResponsable(Organizacion organizacion, ResourceBundle bundle){

		Map<String, Object> parameters = new HashMap<>();
		
		List<TableDTO> listResponsableEntidad = new ArrayList<TableDTO>();
		listResponsableEntidad.add(new TableDTO(LanguageUtil.get(bundle, "informe.nombre"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getNombreResponsableEntidad():null)));
		listResponsableEntidad.add(new TableDTO(LanguageUtil.get(bundle, "informe.cargo"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getCargoResponsableEntidad():null)));
		listResponsableEntidad.add(new TableDTO(LanguageUtil.get(bundle, "informe.telefono"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getTelefonoResponsableEntidad():null)));
		listResponsableEntidad.add(new TableDTO(LanguageUtil.get(bundle, "informe.email"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getEmailResponsableEntidad():null)));
        JRBeanCollectionDataSource responsableEntidad = new JRBeanCollectionDataSource(listResponsableEntidad);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPONSABLE_ENTIDAD_TABLE, responsableEntidad);
		
		List<TableDTO> listResponsableIgualdad = new ArrayList<TableDTO>();
        listResponsableIgualdad.add(new TableDTO(LanguageUtil.get(bundle, "informe.nombre"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getNombreResponsableIgualdad():null)));
        listResponsableIgualdad.add(new TableDTO(LanguageUtil.get(bundle, "informe.cargo"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getCargoResponsableIgualdad():null)));
        listResponsableIgualdad.add(new TableDTO(LanguageUtil.get(bundle, "informe.telefono"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getTelefonoResponsableIgualdad():null)));
        listResponsableIgualdad.add(new TableDTO(LanguageUtil.get(bundle, "informe.email"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getEmailResponsableIgualdad():null)));
        JRBeanCollectionDataSource responsableIgualdad = new JRBeanCollectionDataSource(listResponsableIgualdad);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPONSABLE_IGUALDAD_TABLE, responsableIgualdad);
		return parameters;
	}
	
	/**
	 * getParameters Organizacion Gestion Personas
	 * @param organizacion
	 * @return
	 */
	private static Map<String, Object> getParametersOrgGestionPersonas(Organizacion organizacion, ResourceBundle bundle){
		Map<String, Object> parameters = new HashMap<>();
		
		List<CNAES> cnaesList = CNAESLocalServiceUtil.getCNAESs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        String sectorActividad = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
        String cnae_code = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;

        if(Validator.isNotNull(organizacion) &&  Validator.isNotNull(cnaesList)) {
        	String[] cnaes = null;

        	if(!organizacion.getCnaes().isEmpty() && organizacion.getCnaes().contains(";")){
        		cnaes = organizacion.getCnaes().split(";");
        	}else {
        		cnaes = new String[] {organizacion.getCnaes()} ;
        	}
        	
        	int cont = 0;
        	for(CNAES cnae : cnaesList) {
        		for(String cnaeArray : cnaes) {
        			if(cnae.getCnae().equals(cnaeArray)) {
        				if(cont==0) {
        					sectorActividad = cnae.getNombre();
        					cnae_code = cnae.getCnae();
        				}else {
        					sectorActividad = sectorActividad + ", " + cnae.getNombre();
        					cnae_code = cnae_code + ", " + cnae.getCnae();
        				}
        				cont++;
        			}
        		}
        	}
        }

		String nHombres = "0";
		String nMujeres = "0";
		
		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getNHombresPlantilla())) {
			nHombres = Long.toString(organizacion.getNHombresPlantilla());
		}
        
		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getNHombresPlantilla())) {
			nMujeres = Long.toString(organizacion.getNMujeresPlantilla());
		}
        parameters.put(PlanIgualdadGenerarInformePortletKeys.N_HOMBRES , nHombres);			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MUJERES ,nMujeres);			
		
		Long nTotal = 0L;
		if(Validator.isNotNull(organizacion) &&Validator.isNotNull(organizacion.getNHombresPlantilla()) && Validator.isNotNull(organizacion.getNMujeresPlantilla())) {
			nTotal = organizacion.getNHombresPlantilla() + organizacion.getNMujeresPlantilla();		
		}
		
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_TOTAL , Long.toString(nTotal));		
	
		String nRepreHombres = "0";
		String nRepreMujeres = "0";		
		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getNRepresentacionLegalHombres())) {
			nRepreHombres = Long.toString(organizacion.getNRepresentacionLegalHombres());
		}
        
		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getNRepresentacionLegalMujeres())) {
			nRepreMujeres = Long.toString(organizacion.getNRepresentacionLegalMujeres());
		}
		
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_REPRESENTACION_LEGAL_HOMBRES , nRepreHombres);			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_REPRESENTACION_LEGAL_MUJERES ,nRepreMujeres);			
		
		Long nTotalRepre = 0L;
		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getNRepresentacionLegalHombres()) && Validator.isNotNull(organizacion.getNRepresentacionLegalMujeres())) {
			nTotalRepre = organizacion.getNRepresentacionLegalHombres() + organizacion.getNRepresentacionLegalMujeres();
		}
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_REPRESENTACION_LEGAL_TOTAL , Long.toString(nTotalRepre));		

		
		String orgRLTSi = StringPool.BLANK;
        String orgRLTNo = StringPool.BLANK;
        String orgRLTNoTiene = StringPool.BLANK;
        if(Validator.isNotNull(organizacion)) {
        	if("0".equals(organizacion.getRepresentacionLegal())) {
    			if("0".equals(organizacion.getRepresentaTotalidad())) {
    				orgRLTSi = "X";
    			}else{
    				orgRLTNo = "X";
    			}
    		}else {
    			orgRLTNoTiene = "X";
    		}	
        }

        parameters.put(PlanIgualdadGenerarInformePortletKeys.ORG_RLT_SI, orgRLTSi);
        parameters.put(PlanIgualdadGenerarInformePortletKeys.ORG_RLT_NO, orgRLTNo);
        parameters.put(PlanIgualdadGenerarInformePortletKeys.ORG_RLT_NO_RLT, orgRLTNoTiene);
        
        parameters.put(PlanIgualdadGenerarInformePortletKeys.TIENE_REPRESENTACION_LEGAL_RLT , GeneralUtils.checkParameter(organizacion!=null?organizacion.getRepresentacionLegal():null));			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIOS_REPRESENTACION , GeneralUtils.checkParameter(organizacion!=null?organizacion.getTelefonoResponsableIgualdad(): null));			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.EXISTE_DEPARTAMENTO_PERSONAL , GeneralUtils.checkParameter(organizacion!=null?organizacion.getTelefonoResponsableIgualdad():null));			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.SINDICADOS_REPRESENTATIVOS_SECTOR , GeneralUtils.checkParameter(organizacion!=null?organizacion.getTelefonoResponsableIgualdad():null));			

		String nCentrosDimension = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String orgDepartamentoPersonal = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		
		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getNCentros())) {
			nCentrosDimension = Long.toString(organizacion.getNCentros());
		}

		if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getDepartamentoPersonal())) {
			if("0".equals(organizacion.getDepartamentoPersonal())) {
				orgDepartamentoPersonal= "Si";
			}else {
				orgDepartamentoPersonal = "No";
			}
		}
		
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_CENTROS_TRABAJO_DIMENSION,nCentrosDimension);

        parameters.put(PlanIgualdadGenerarInformePortletKeys.ORG_DEPARTAMENTO_PERSONAL, orgDepartamentoPersonal);
  
        List<TableDTO> listActividad = new ArrayList<TableDTO>();
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.sector-actividad"), sectorActividad));
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.cnae"), cnae_code));
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.descripcion-actividad"), GeneralUtils.checkParameter(organizacion!=null?organizacion.getDescripcionActividad(): null)));
        
        String ambito = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
        if(Validator.isNotNull(organizacion) && Validator.isNotNull(organizacion.getAmbito())) {        	 
        	ambito = Ambito.getAmbito(organizacion.getAmbito(), bundle) + (!organizacion.getComentarioAmbito().isEmpty() ? " " + organizacion.getComentarioAmbito() : StringPool.BLANK);
        }
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.dispersion-geografica"), GeneralUtils.checkParameter(ambito)));
        JRBeanCollectionDataSource beanActividad = new JRBeanCollectionDataSource(listActividad);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.ACTIVIDAD_TABLE, beanActividad);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.AMBITO_TERRITORIAL , GeneralUtils.checkParameter(ambito));			
		parameters.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_AMBITO_TERRITORIAL , organizacion!=null && organizacion.getComentarioAmbito()!=null ?GeneralUtils.checkParameter(organizacion.getComentarioAmbito()): null);	

		return parameters;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @param compId
	 * @param versionId
	 * @param entidad
	 * @return
	 */
	public static JSONArray getRepresentantes(ThemeDisplay themeDisplay, Long compId, Long versionId, boolean entidad) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
		Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);

		List<TableDTO> listRepresentantes = new ArrayList<TableDTO>();
		if(entidad) {
			listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.nombre"), GeneralUtils.checkParameter(organizacion!=null ? organizacion.getNombreResponsableEntidad(): null)));
			listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.cargo"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getCargoResponsableEntidad():null)));
			listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.telefono"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getTelefonoResponsableEntidad():null)));
			listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.email"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getEmailResponsableEntidad():null)));
		}else {
			listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.nombre"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getNombreResponsableIgualdad():null)));
			listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.cargo"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getCargoResponsableIgualdad():null)));
	        listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.telefono"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getTelefonoResponsableIgualdad():null)));
	        listRepresentantes.add(new TableDTO(LanguageUtil.get(bundle, "informe.email"), GeneralUtils.checkParameter(organizacion!=null ?organizacion.getEmailResponsableIgualdad():null)));
		}
		
		if(Validator.isNotNull(listRepresentantes)) {
			for(TableDTO representante : listRepresentantes) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        		jsonObject.put("label", representante.getLabel());
        		jsonObject.put("valor", representante.getValor());
        		
        		jsonArray.put(jsonObject);
			}
		}
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @param compId
	 * @param versionId
	 * @return
	 */
	public static JSONArray getActividadTable(ThemeDisplay themeDisplay, Long compId, Long versionId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

        List<CNAES> cnaesList = CNAESLocalServiceUtil.getCNAESs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        String sectorActividad = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
        String cnae_code = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
        if(Validator.isNotNull(cnaesList)) {
        	String[] cnaes = null;

        	if(!organizacion.getCnaes().isEmpty() && organizacion.getCnaes().contains(";")){
        		cnaes = organizacion.getCnaes().split(";");
        	}else {
        		cnaes = new String[] {organizacion.getCnaes()} ;
        	}
        	
        	int cont = 0;
        	for(CNAES cnae : cnaesList) {
        		for(String cnaeArray : cnaes) {
        			if(cnae.getCnae().equals(cnaeArray)) {
        				if(cont==0) {
        					sectorActividad = cnae.getNombre();
        					cnae_code = cnae.getCnae();
        				}else {
        					sectorActividad = sectorActividad + ", " + cnae.getNombre();
        					cnae_code = cnae_code + ", " + cnae.getCnae();
        				}
        				cont++;
        			}
        		}
        	}
        }
		
		List<TableDTO> listActividad = new ArrayList<TableDTO>();
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.sector-actividad"), sectorActividad));
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.cnae"), cnae_code));
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.descripcion-actividad"), GeneralUtils.checkParameter(organizacion.getDescripcionActividad())));
        
        String ambito = StringPool.BLANK;
        if(Validator.isNotNull(organizacion.getAmbito())) {        	 
        	ambito = Ambito.getAmbito(organizacion.getAmbito(), bundle) + (!organizacion.getComentarioAmbito().isEmpty() ? " " + organizacion.getComentarioAmbito() : StringPool.BLANK);
        }
        listActividad.add(new TableDTO(LanguageUtil.get(bundle, "informe.dispersion-geografica"), GeneralUtils.checkParameter(ambito)));
		
        if(Validator.isNotNull(listActividad)) {
        	for(TableDTO actividad : listActividad) {
        		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        		jsonObject.put("label", actividad.getLabel());
        		jsonObject.put("valor", actividad.getValor());
        		
        		jsonArray.put(jsonObject);
        	}
        }
        
		return jsonArray;
	}
	
	/**
	 * get Parameters Composicion Comision
	 * @param organizacion
	 * @return
	 * @throws JSONException
	 */
	private static Map<String, Object> getParametersComposicionComision(Organizacion organizacion) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
		
		JSONArray arrComisionOrg =  JSONFactoryUtil.createJSONArray();;
		JSONArray arrComisionSocial= JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(organizacion)) {
			JSONObject reprComisionOrg = JSONFactoryUtil.createJSONObject(organizacion.getReprComisionOrganizacion());
			arrComisionOrg = reprComisionOrg.getJSONArray("data");
			JSONObject reprComisionSocial = JSONFactoryUtil.createJSONObject(organizacion.getReprComisionSocial());
			arrComisionSocial = reprComisionSocial.getJSONArray("data");
		}
		List<RepresentanteDTO> listRepresentantesOrg = getListRepresentantes(arrComisionOrg);
		JRBeanCollectionDataSource beanReprOrg = new JRBeanCollectionDataSource(listRepresentantesOrg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_ORG_BEAN, beanReprOrg);	

		List<RepresentanteDTO> listRepresentantesSocial = getListRepresentantes(arrComisionSocial);
		JRBeanCollectionDataSource beanReprSocial = new JRBeanCollectionDataSource(listRepresentantesSocial);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_SOC_BEAN, beanReprSocial);

		return parameters;
	}
	
	/**
	 * get List Representantes
	 * @param arrArrayRepresentantes
	 * @return
	 */
	public static List<RepresentanteDTO> getListRepresentantes(JSONArray arrArrayRepresentantes){
		List<RepresentanteDTO> listRepresentantes =  new ArrayList<>();
		
		if(Validator.isNotNull(arrArrayRepresentantes) && arrArrayRepresentantes.length()>0) {
			for (int i = 0; i < arrArrayRepresentantes.length(); i++) {
				JSONObject objectRespuesta = arrArrayRepresentantes.getJSONObject(i);
				
				if(!objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.REPRE_NOMBRE).isEmpty()) {
					RepresentanteDTO representante = new RepresentanteDTO();
					representante.setNombreCompleto(objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.REPRE_NOMBRE) + " " + objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.REPRE_APELLIDOS));
					representante.setEmail(objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.REPRE_EMAIL));
					representante.setNif(objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.REPRE_NIF));
					representante.setPuestoTrabajo(objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.REPRE_PUESTO));
					listRepresentantes.add(representante);
				}
			}
		}else {
			RepresentanteDTO representante = new RepresentanteDTO();
			representante.setPuestoTrabajo(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
			representante.setNombreCompleto(StringPool.BLANK);
			representante.setEmail(StringPool.BLANK);
			representante.setNif(StringPool.BLANK);
			listRepresentantes.add(representante);
		}
		
		return listRepresentantes;
	}
	
	/**
	 * 
	 * @param organizacion
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray getJSONArrayRepresentantes(String object) throws JSONException {
		JSONArray jsonArrayRepresentantes = JSONFactoryUtil.createJSONArray();
		JSONObject reprComisionOrg = JSONFactoryUtil.createJSONObject(object);
		JSONArray arrComisionOrg = reprComisionOrg.getJSONArray("data");
		List<RepresentanteDTO> listRepresentantesOrg = InformacionPreliminarUtil.getListRepresentantes(arrComisionOrg);

		if(Validator.isNotNull(listRepresentantesOrg) && !listRepresentantesOrg.isEmpty()) {
			for(RepresentanteDTO representante : listRepresentantesOrg) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put(PlanIgualdadGenerarInformePortletKeys.REPRE_EMAIL, representante.getEmail());
				jsonObject.put(PlanIgualdadGenerarInformePortletKeys.REPRE_NIF, representante.getNif());
				jsonObject.put(PlanIgualdadGenerarInformePortletKeys.REPRE_NOMBRE_COMPLETO, representante.getNombreCompleto());
				jsonObject.put(PlanIgualdadGenerarInformePortletKeys.REPRE_PUESTO_TRABAJO, representante.getPuestoTrabajo());
				jsonArrayRepresentantes.put(jsonObject);
			}
		}
		return jsonArrayRepresentantes;
	}
}
