package com.plan.igualdad.liferay.generar.informe.utils;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.dto.MedidaDTO;
import com.plan.igualdad.liferay.generar.informe.dto.RepresentanteDTO;
import com.plan.igualdad.liferay.generar.informe.dto.SituacionIgualdadDTO;
import com.plan.igualdad.liferay.generar.informe.enums.MateriaEnum;
import com.plan.igualdad.liferay.generar.informe.enums.PrioridadEnum;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.Informes;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.model.Organizacion;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.InformesLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.OrganizacionLocalServiceUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

public class GenerarInformeUtils {

	private static Log _log = LogFactoryUtil.getLog(GenerarInformeUtils.class);

	/**
	 * getParameters Versions
	 * @param compId
	 * @param version
	 * @return
	 */
	public static Map<String, Object> getParametersVersion (Long compId, Version version){
		Map<String, Object> parameters = new HashMap<>();
		
		DateFormat dateFormat = new SimpleDateFormat(PlanIgualdadGenerarInformePortletKeys.FORMAT_DATE);  
		
		String inicioPeriodoEstudio = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String finPeriodoEstudio = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String inicioPeriodoPlan = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String finPeriodoPlan = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		
		if(Validator.isNotNull(version.getInicioPeriodoDatos())) {
			inicioPeriodoEstudio = dateFormat.format(version.getInicioPeriodoDatos());
		}
			
		if(Validator.isNotNull(version.getFinPeriodoDatos())) {
			finPeriodoEstudio = dateFormat.format(version.getFinPeriodoDatos());
		}
		
		if(Validator.isNotNull(version.getInicioPeriodoPlan())) {
			inicioPeriodoPlan = dateFormat.format(version.getInicioPeriodoPlan());
		}
		
		if(Validator.isNotNull(version.getFinPeriodoPlan())) {
			finPeriodoPlan = dateFormat.format(version.getFinPeriodoPlan());
		}
		
		int inicioPeriodoInt = 0;
		int finPeriodoAnyo = 0;
		int mesPeriodo = 0;
		String nAnyos = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String nAnyosVigencia = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		if(Validator.isNotNull(version.getInicioPeriodoPlan()) && Validator.isNotNull(version.getFinPeriodoPlan())) {
			Calendar a = GeneralUtils.getCalendar(version.getInicioPeriodoPlan());
	        Calendar b = GeneralUtils.getCalendar(version.getFinPeriodoPlan());
	        
	        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
	        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
	            (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) &&   
	            a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
	            diff--;
	        }
	        
	        if(diff<4) {
	        	nAnyos = Integer.toString(diff) + " a&#241;os. Una vez finalizada su vigencia este Plan se mantendr&#237; en vigor hasta la aprobaci&#243;n del siguiente, sin que en su caso, su duraci&#243;n m&#225;xima exceda del per&#237;odo de 4 a&#241;os previsto en el art&#237;culo 9.1 del Real Decreto 901/2020).";
	        }else {
	        	nAnyos = Integer.toString(diff) + " a&#241;s. ";
	        }
	        nAnyosVigencia = Integer.toString(diff) ;
	        
	        inicioPeriodoInt = a.get(Calendar.YEAR);
	        finPeriodoAnyo = b.get(Calendar.YEAR);
	        mesPeriodo = a.get(Calendar.MONTH);
		}
        parameters.put(PlanIgualdadGenerarInformePortletKeys.N_ANYOS_PERIODO_PLAN, nAnyos);
        parameters.put(PlanIgualdadGenerarInformePortletKeys.N_ANYOS_VIGENCIA, nAnyosVigencia);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.INICIO_PERIODO_PLAN_INTEGER, inicioPeriodoInt);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FIN_PERIODO_PLAN_ANYO_INTEGER, finPeriodoAnyo);
		
		if(!nAnyosVigencia.equals(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO)) {
			for(int i= 0; i<Integer.parseInt(nAnyosVigencia); i++) {
				parameters.put("cro".concat(Integer.toString(i+1)), Integer.toString(inicioPeriodoInt));
				
				if(i==0) {
					String menor6Meses = "-1";
					if(mesPeriodo>5) {
						menor6Meses="-2";
					}
					parameters.put("cro".concat(Integer.toString(i+1)).concat(menor6Meses), "X");

				}
				inicioPeriodoInt = inicioPeriodoInt+1;
			}
		}
		
		parameters.put(PlanIgualdadGenerarInformePortletKeys.INICIO_PERIODO_ESTUDIO, inicioPeriodoEstudio);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FIN_PERIODO_ESTUDIO, finPeriodoEstudio);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.INICIO_PERIODO_PLAN, inicioPeriodoPlan);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FIN_PERIODO_PLAN, finPeriodoPlan);

		return parameters;
	}
	
	/**
	 * 
	 * @param parameters
	 * @param themeDisplay
	 * @param resourceRequest 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @throws PortalException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void exporterDocument (Map<String, Object> parameters, ThemeDisplay themeDisplay,  ResourceRequest resourceRequest, ResourceResponse resourceResponse, String nameDocument) throws PortalException, JRException, IOException {
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        HttpSession pSession = httpReq.getSession();
        String extension = (String)pSession.getAttribute("extensionDownload");
        JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
		jasperReportsContext.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
		jasperReportsContext.setProperty("net.sf.jasperreports.default.font.name", "Corbel");
		
		if(Validator.isNotNull(extension) && extension.equals("2")){
			parameters.put("isODT", Boolean.TRUE);
		}else {
			parameters.put("isODT", Boolean.FALSE);
		}
		
		Folder folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Jasper Reports");
		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(themeDisplay.getScopeGroupId(), folder.getFolderId(), nameDocument +".jasper");
		JasperPrint print = JasperFillManager.fillReport(fileEntry.getContentStream(), parameters, new JREmptyDataSource());
		OutputStream os = resourceResponse.getPortletOutputStream();

        if(Validator.isNotNull(extension) && extension.equals("0")) {
        	resourceResponse.setContentType("application/pdf");
         	JRPdfExporter exporter = new JRPdfExporter();
    		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
    		resourceResponse.setProperty(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename="+nameDocument.concat(".pdf").replaceAll(" ", ""));
    		exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
    		exporter.exportReport();
    		
    		
    		
        }else if(Validator.isNotNull(extension) && extension.equals("1")){   	
        	resourceResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        	JRDocxExporter exporter = new JRDocxExporter();
    		exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, print);
    		resourceResponse.setProperty(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename="+nameDocument.concat(".docx").replaceAll(" ", ""));
    		exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, os);
    		exporter.exportReport();
        }else if(Validator.isNotNull(extension) && extension.equals("2")){
        	resourceResponse.setContentType("application/vnd.oasis.opendocument.text");
        	JROdtExporter exporter = new JROdtExporter();
        	exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, print);
    		resourceResponse.setProperty(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename="+nameDocument.concat(".odt").replaceAll(" ", ""));
    		exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, os);
    		exporter.exportReport();
        }
		os.flush();
	}
	
	/**
	 * 
	 * @param parameters
	 * @param compId
	 * @param versionId
	 * @param themeDisplay
	 * @param nameDocument
	 * @throws PortalException 
	 */
	public static void saveInforme(Map<String, Object> parameters, Long compId, Long versionId, ThemeDisplay themeDisplay, String nameDocument, ResourceRequest resourceRequest) throws PortalException {
		int versionInforme = InformesLocalServiceUtil.countVersionsInforme(compId, versionId, nameDocument);
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

		Informes informe = InformesLocalServiceUtil.createInformes(CounterLocalServiceUtil.increment(Informes.class.getName()));
		
		informe.setCompId(compId);
		informe.setTipoInforme(nameDocument);
		informe.setVersionId(versionId);
		informe.setVersionInforme(versionInforme +1);
		informe.setCreateDate(new Date());
		informe.setUserId(themeDisplay.getUserId());
		
		JSONObject jsonParameters = JSONFactoryUtil.createJSONObject();
		JSONObject jsonBeans = JSONFactoryUtil.createJSONObject();
		if(Validator.isNotNull(parameters)) {
			boolean haveMedidaList = Boolean.TRUE;
			for(Map.Entry<String, Object> entry: parameters.entrySet()) {

				if(!"REPORT_DATA_SOURCE".equals(entry.getKey()) &&!"JASPER_REPORTS_CONTEXT".equals(entry.getKey()) 
						&&!"REPORT_FORMAT_FACTORY".equals(entry.getKey()) && !"REPORT_LOCALE".equals(entry.getKey())
						&&!"REPORT_TIME_ZONE".equals(entry.getKey()) && !"JASPER_REPORT".equals(entry.getKey()) 
						&& !"REPORT_PARAMETERS_MAP".equals(entry.getKey()) && !"IS_IGNORE_PAGINATION".equals(entry.getKey())
						&& !"IS_IGNORE_PAGINATION".equals(entry.getKey()) && !entry.getKey().contains("graphic")  
						&& !"logoAdeplus".equals(entry.getKey())  && !entry.getKey().contains("Bean") 
						&& !entry.getKey().contains("Table") && !entry.getKey().equals("medianaEQ") 
						&& !entry.getKey().equals("conceptos") && !entry.getKey().equals("promedioGP")
						&& !entry.getKey().equals("medianaGP") && !entry.getKey().equals("promedioEQ")
						&& !entry.getKey().equals("promedioPT") && !entry.getKey().equals("medianaPT")
						&& !entry.getKey().equals("promedioVT") && !entry.getKey().equals("medianaVT") 
						&& !entry.getKey().contains("medidaList") && !entry.getKey().contains("imgAsignacionPesos")) {
					
					if(entry.getKey().contains("comentario") && entry.getKey().contains("imgTag")) {
						jsonParameters.put(entry.getKey().substring(0, entry.getKey().length() - 3), entry.getValue());
					}else if (!entry.getKey().contains("imgTag") && !entry.getKey().contains("img")){
						jsonParameters.put(entry.getKey(), entry.getValue());
					}
				}
				
				if(entry.getKey().contains("Bean") || entry.getKey().contains("Table")) {
					if(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_ORG_BEAN.equals(entry.getKey())) {
						Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_ORG_BEAN, InformacionPreliminarUtil.getJSONArrayRepresentantes(organizacion.getReprComisionOrganizacion()));
					}else if(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_SOC_BEAN.equals(entry.getKey())) {
						Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_SOC_BEAN, InformacionPreliminarUtil.getJSONArrayRepresentantes(organizacion.getReprComisionSocial()));
					}else if(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_SOC_BEAN.concat("Copy").equals(entry.getKey())) {
						Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_SOC_BEAN.concat("Copy"), InformacionPreliminarUtil.getJSONArrayRepresentantes(organizacion.getReprComisionSocial()));
					}else if(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_ORG_BEAN.concat("Copy").equals(entry.getKey())) {
						Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, versionId);
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.REP_COM_NEG_REPRE_ORG_BEAN.concat("Copy"), InformacionPreliminarUtil.getJSONArrayRepresentantes(organizacion.getReprComisionOrganizacion()));
					}else if(PlanIgualdadGenerarInformePortletKeys.SELECCION_CONTRATACION_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 1, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.CLASIFICACION_PROFESIONAL_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 10, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.FORMACION_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 3, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.PROMOSION_PROFESIONAL_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 4, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.CONDICIONES_TRABAJO_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 5, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.EJERCICIO_CORRESPONSABLE_DERECHOS_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 6, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.PREVENCION_ACOSO_SEXUAL_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 7, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.DERECHOS_LABORALES_VICTIMAS_VG_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 8, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.LENGUAJE_COMUNICACION_NO_SEXISTA_BEAN.equals(entry.getKey())){
						jsonBeans.put(entry.getKey(), ParametrizacionUtils.getArraySituacionesIgualdad(compId, versionId, 9, entry.getKey()));
					}else if(PlanIgualdadGenerarInformePortletKeys.ACTIVIDAD_TABLE.equals(entry.getKey())) {
						jsonBeans.put(entry.getKey(), InformacionPreliminarUtil.getActividadTable(themeDisplay, compId, versionId));
					}else if(PlanIgualdadGenerarInformePortletKeys.RESPONSABLE_ENTIDAD_TABLE.equals(entry.getKey())) {
						jsonBeans.put(entry.getKey(), InformacionPreliminarUtil.getRepresentantes(themeDisplay, compId, versionId, Boolean.TRUE));
					}else if(PlanIgualdadGenerarInformePortletKeys.RESPONSABLE_IGUALDAD_TABLE.equals(entry.getKey())) {
						jsonBeans.put(entry.getKey(), InformacionPreliminarUtil.getRepresentantes(themeDisplay, compId, versionId, Boolean.FALSE));
					}else if(PlanIgualdadGenerarInformePortletKeys.PARTICIPANTES_CUESTIONARIO_BEAN.equals(entry.getKey())) {
						jsonBeans.put(entry.getKey(), CuestionarioEmpresaUtil.getParticipantesCuestionario(themeDisplay, compId, versionId));
					}
				}
				
				if(entry.getKey().contains("medidaList")) {
					if(haveMedidaList) {
						List<Medida> listMedidas = MedidaLocalServiceUtil.findByCompVersion(compId, versionId);
						
						List<MedidaDTO> medidasSeleccionContratacion = new ArrayList<>();
						List<MedidaDTO> medidasClasificacionProfesional = new ArrayList<>();
						List<MedidaDTO> medidasFormacion = new ArrayList<>();
						List<MedidaDTO> medidasPromocionProfesional = new ArrayList<>();
						List<MedidaDTO> medidasAuditoria = new ArrayList<>();
						List<MedidaDTO> medidasCondicionesTrabajo = new ArrayList<>();
						List<MedidaDTO> medidasEjercicio = new ArrayList<>();
						List<MedidaDTO> medidasInfraestructuraFemenina = new ArrayList<>();
						List<MedidaDTO> medidasPrevencionAcoso = new ArrayList<>();
						List<MedidaDTO> medidasDerechosLaborales = new ArrayList<>();
						List<MedidaDTO> medidasLenguajeComunicacion = new ArrayList<>();
						List<MedidaDTO> medidasAltaPrioridad = new ArrayList<>();
						List<MedidaDTO> medidasConDocumento = new ArrayList<>();
						List<MedidaDTO> medidasListTodas = new ArrayList<>();
						
						if(Validator.isNotNull(listMedidas)) {
							for(Medida medida : listMedidas) {
								if(Validator.isNotNull(medida.getDatosGenerales()) && !medida.getDatosGenerales().isEmpty()) {
									MedidaDTO medidaDTO = new MedidaDTO();
									JSONObject datosGenerales = JSONFactoryUtil.createJSONObject(medida.getDatosGenerales());
									int matter = Integer.parseInt(datosGenerales.getString("matter"));
									int priority = Integer.parseInt(datosGenerales.getString("priority"));
									
									medidaDTO.setDescripcion(datosGenerales.getString("measureDescription").replaceAll("\\\\n", "<br>"));
									medidaDTO.setIndicadores(datosGenerales.getString("monitoringIndicators").replaceAll("\\\\n", "<br>"));
									medidaDTO.setMateria(LanguageUtil.get(bundle,MateriaEnum.getDescripcionByCodigo(matter)));
									medidaDTO.setNombreMedida(datosGenerales.getString("measureName"));
									medidaDTO.setObjetivos(datosGenerales.getString("objectivesPursued").replaceAll("\\\\n", "<br>"));
									medidaDTO.setPrioridad(LanguageUtil.get(bundle,PrioridadEnum.getDescripcionByCodigo(priority)));
									medidaDTO.setRecursos(datosGenerales.getString("associatedResources").replaceAll("\\\\n", "<br>"));
									medidaDTO.setNoMedida(Boolean.FALSE);
									medidaDTO.setRecurrente(datosGenerales.getString("apply"));
									
									if(Validator.isNotNull(medida.getCumplimentacion()) && !medida.getCumplimentacion().isEmpty()) {
										JSONObject cumplimentacion = JSONFactoryUtil.createJSONObject(medida.getCumplimentacion());
										medidaDTO.setResponsable(cumplimentacion.getString("responsible"));
										medidaDTO.setFechaImplantacion(cumplimentacion.getString("implantationDate"));
										medidaDTO.setFechaSeguimiento(cumplimentacion.getString("tracingDate"));
										medidaDTO.setFechaPrevista(cumplimentacion.getString("plannedDate"));
										medidaDTO.setNivelEjecucion(Integer.toString(cumplimentacion.getInt("execution")));
										medidaDTO.setMotivo(Integer.toString(cumplimentacion.getInt("reason")));
										medidaDTO.setOtrosMotivos(cumplimentacion.getString("reasonOther"));
										medidaDTO.setRecursosAsociados(cumplimentacion.getString("resourcesAssigns").replaceAll("\\\\n", "<br>"));
										medidaDTO.setDificultadesEncontradas(cumplimentacion.getString("difficultiesImplantation").replaceAll("\\\\n", "<br>"));
										medidaDTO.setSolucionesAdoptadas(cumplimentacion.getString("solutionsAdopted").replaceAll("\\\\n", "<br>"));
										medidaDTO.setReduccionDesigualdades(cumplimentacion.getString("reductionInequalities").replaceAll("\\\\n", "<br>"));
										medidaDTO.setMejorasProducidas(cumplimentacion.getString("producedImprovements").replaceAll("\\\\n", "<br>"));
										medidaDTO.setPropuestasFuturo(cumplimentacion.getString("proposalFuture").replaceAll("\\\\n", "<br>"));
										medidaDTO.setDocumentacionAcreditativa(cumplimentacion.getString("documentationText").replaceAll("\\\\n", "<br>"));

										if(Validator.isNotNull(cumplimentacion.getLong("uploadFile")) && cumplimentacion.getLong("uploadFile")!=0L) {
											FileEntry entry_ = DLAppServiceUtil.getFileEntry(cumplimentacion.getLong("uploadFile"));
											if(Validator.isNotNull(entry_)) {
												String dateSubida = new SimpleDateFormat("dd/MM/yyyy").format(entry_.getCreateDate());

												medidaDTO.setFechaSubidaDocumento(dateSubida);
												medidasConDocumento.add(medidaDTO);
											}
										}
									}else {
										medidaDTO.setResponsable(StringPool.BLANK);
										medidaDTO.setFechaImplantacion(StringPool.BLANK);
										medidaDTO.setFechaSeguimiento(StringPool.BLANK);
										medidaDTO.setNivelEjecucion("0");
										medidaDTO.setMotivo("1000");
										medidaDTO.setRecursosAsociados(StringPool.BLANK);
										medidaDTO.setDificultadesEncontradas(StringPool.BLANK);
										medidaDTO.setSolucionesAdoptadas(StringPool.BLANK);
										medidaDTO.setReduccionDesigualdades(StringPool.BLANK);
										medidaDTO.setMejorasProducidas(StringPool.BLANK);
										medidaDTO.setPropuestasFuturo(StringPool.BLANK);
										medidaDTO.setDocumentacionAcreditativa(StringPool.BLANK);
									}
									
									if(matter ==1) medidasSeleccionContratacion.add(medidaDTO);
									if(matter ==2) medidasAuditoria.add(medidaDTO);
									if(matter ==3) medidasFormacion.add(medidaDTO);
									if(matter ==4) medidasPromocionProfesional.add(medidaDTO);
									if(matter ==5) medidasCondicionesTrabajo.add(medidaDTO);
									if(matter ==6) medidasEjercicio.add(medidaDTO);
									if(matter ==7) medidasPrevencionAcoso.add(medidaDTO);
									if(matter ==8) medidasDerechosLaborales.add(medidaDTO);
									if(matter ==9) medidasLenguajeComunicacion.add(medidaDTO);
									if(matter ==10) medidasClasificacionProfesional.add(medidaDTO);
									if(matter ==11) medidasInfraestructuraFemenina.add(medidaDTO);
									
									if(priority==2) medidasAltaPrioridad.add(medidaDTO);
									
									medidasListTodas.add(medidaDTO);
								}
							}
						}
						
						if(medidasSeleccionContratacion.isEmpty()) medidasSeleccionContratacion.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasClasificacionProfesional.isEmpty())medidasClasificacionProfesional.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasFormacion.isEmpty())medidasFormacion.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasPromocionProfesional.isEmpty())medidasPromocionProfesional.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasAuditoria.isEmpty()) medidasAuditoria.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasCondicionesTrabajo.isEmpty()) medidasCondicionesTrabajo.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasEjercicio.isEmpty())medidasEjercicio.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasInfraestructuraFemenina.isEmpty())medidasInfraestructuraFemenina.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasPrevencionAcoso.isEmpty())medidasPrevencionAcoso.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasDerechosLaborales.isEmpty()) medidasDerechosLaborales.add(SeguimientoMedidasUtils.getNoMedida());
						if(medidasLenguajeComunicacion.isEmpty()) medidasLenguajeComunicacion.add(SeguimientoMedidasUtils.getNoMedida());
						
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_SELECCION_CONTRATACION, JSONArrayInformeUtils.getMedidasList(medidasSeleccionContratacion));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_AUDITORIA_RETRIBUTIVA, JSONArrayInformeUtils.getMedidasList(medidasAuditoria));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_AUDITORIA_RETRIBUTIVA_COPY, JSONArrayInformeUtils.getMedidasList(medidasAuditoria));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_FORMACION, JSONArrayInformeUtils.getMedidasList(medidasFormacion));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_PROMOCION_PROFESIONAL, JSONArrayInformeUtils.getMedidasList(medidasPromocionProfesional));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_CONDICIONES_TRABAJO, JSONArrayInformeUtils.getMedidasList(medidasCondicionesTrabajo));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_EJERCICIO, JSONArrayInformeUtils.getMedidasList(medidasEjercicio));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_PREVENCION, JSONArrayInformeUtils.getMedidasList(medidasPrevencionAcoso));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_DERECHOS, JSONArrayInformeUtils.getMedidasList(medidasDerechosLaborales));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_LENGUAJE_COMUNICACION, JSONArrayInformeUtils.getMedidasList(medidasLenguajeComunicacion));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_CLASIFICACION_PROFESIONAL, JSONArrayInformeUtils.getMedidasList(medidasClasificacionProfesional));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_INFRARREPRE_FEMENINA, JSONArrayInformeUtils.getMedidasList(medidasInfraestructuraFemenina));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_ALTA_PRIORIDAD, JSONArrayInformeUtils.getMedidasList(medidasAltaPrioridad));
						jsonBeans.put(PlanIgualdadGenerarInformePortletKeys.MEDIDAS_CON_DOCUMENTO, JSONArrayInformeUtils.getMedidasList(medidasConDocumento));
						jsonBeans.put("medidaListTotal", JSONArrayInformeUtils.getMedidasList(medidasListTodas));

						haveMedidaList = Boolean.FALSE;
					}
				}
				
				if(entry.getKey().contains("graphic")) {
					if("graphic2".equals(entry.getKey())) {
						jsonBeans.put("graphic2",JSONArrayInformeUtils.getAreasPuestosEstadisticas(compId, versionId));
					}else if("graphic5".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 5L);
						jsonBeans.put("graphic5",JSONArrayInformeUtils.getPuestosAntiguedadEstadisticas(compId, versionId, bundle, estadistica));
					}else if("graphic6".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 6L);
						jsonBeans.put("graphic6",JSONArrayInformeUtils.getPuestosNivelEstudiosEstadisticas(compId, versionId, bundle, estadistica));
					}else if("graphic7".equals(entry.getKey())) {
						jsonBeans.put("graphic7",JSONArrayInformeUtils.getPuestosFamiliaresEstadisticas(compId, versionId, bundle));
					}else if("graphic10".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 10L);
						jsonBeans.put("graphic10",JSONArrayInformeUtils.getHorarioTurnosEstadisticas(compId, versionId, bundle, estadistica));
					}else if("graphic12".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 12L);
						jsonBeans.put("graphic12",JSONArrayInformeUtils.getCesesEstadisticas(compId, versionId, bundle, estadistica));
					}else if("graphic13".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 13L);
						jsonBeans.put("graphic13",JSONArrayInformeUtils.getIncapacidadesEstadisticas(compId, versionId, bundle, estadistica));
					}else if("graphic18".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 18L);
						jsonBeans.put("graphic18",JSONArrayInformeUtils.getAuditoriasEstadisticas(compId, versionId, bundle, estadistica));
					}else if("graphic16".equals(entry.getKey())) {
						Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 16L);
						jsonBeans.put("graphic16",JSONArrayInformeUtils.getDistribuciones(compId, versionId, estadistica));
						jsonBeans.put("graphic16-1",JSONArrayInformeUtils.getDistribuciones(compId, versionId, estadistica));
						jsonBeans.put("graphic16-2",JSONArrayInformeUtils.getValoraciones(compId, versionId, estadistica));
					}
				}
				
			}
		}
		informe.setParametrosInforme(jsonParameters !=null ?jsonParameters.toString():null);
		informe.setBeansInforme(jsonBeans != null ? jsonBeans.toString():null);
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        HttpSession pSession = httpReq.getSession();
        String extension = (String)pSession.getAttribute("extensionDownload");
        
        if(Validator.isNotNull(extension) && extension.equals("0")) {
        	informe.setFormato("PDF");
        }else if(Validator.isNotNull(extension) && extension.equals("1")){
        	informe.setFormato("DOCX");
        }else {
        	informe.setFormato("ODT");
        }
		
		InformesLocalServiceUtil.updateInformes(informe);
	}
	
	/**
	 * 
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @param resourceResponse
	 * @return
	 * @throws PortalException
	 * @throws IOException
	 */
	public static Map<String, Object> generarInformeAuditoria(Long compId, ThemeDisplay themeDisplay, Version version, ResourceResponse resourceResponse) throws PortalException, IOException {
		Map<String, Object> parameters = new HashMap<>();
		ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
		Folder folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "img");
		if(Validator.isNotNull(folder)) {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(themeDisplay.getScopeGroupId(), folder.getFolderId(), "img.PNG");
			if(Validator.isNotNull(fileEntry)) {
				parameters.put("imgAsignacionPesos", ImageIO.read(fileEntry.getContentStream()));
			}
		}
		parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 16L));

		parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 17L));
		parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 18L));
		parameters.putAll(CuestionarioEmpresaUtil.getParameterAll(themeDisplay, compId, version.getVersionId()));
		
		parameters.putAll(SeguimientoMedidasUtils.getParameters(compId, version.getVersionId(), bundle, Boolean.FALSE));

		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @param resourceResponse
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> generarInformeSeguimiento(Long compId,
			ThemeDisplay themeDisplay, Version version, ResourceResponse resourceResponse) throws IOException {
		Map<String, Object> parameters = new HashMap<>();
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

		try {
			parameters.putAll(SeguimientoMedidasUtils.getParameters(compId, version.getVersionId(), bundle, Boolean.FALSE));
			parameters.putAll(ValoracionConsultorUtils.getParameters(themeDisplay, compId));
		}catch (PortalException e) {
			_log.error("ERROR: ", e);
		} 
		
		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @param resourceResponse
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> generarCuestionarioEmpresa(Long compId,
			ThemeDisplay themeDisplay, Version version, ResourceResponse resourceResponse) throws IOException {
		Map<String, Object> parameters = new HashMap<>();
        
        try {
			List<ConsultorCompany> consultores = ConsultorCompanyLocalServiceUtil.fetchByCompId(compId);
			String consultor = StringPool.BLANK;
			String emailConsultor = StringPool.BLANK;
			
			if(Validator.isNotNull(consultores) && !consultores.isEmpty()) {
				int cont = 0;
				for(ConsultorCompany consultorCompanuy : consultores) {
					consultor = (cont!=0 ? consultor + ", " : "") + consultorCompanuy.getName() + (consultorCompanuy.getLastName()!=null && !consultorCompanuy.getLastName().isEmpty() ? " " + consultorCompanuy.getLastName() : "");
					
					User user = UserLocalServiceUtil.fetchUser(consultorCompanuy.getUserId());
					if(Validator.isNotNull(user)) {
						emailConsultor = (cont!=0 ? emailConsultor + ", " : "") + user.getEmailAddress() ;
					}
					cont++;
				}
			}else {
				consultor = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
				emailConsultor = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
			}
			
			parameters.put("consultor", consultor);
			parameters.put("emailConsultor", emailConsultor);
			parameters.putAll(CuestionarioEmpresaUtil.getParameterAll(themeDisplay, compId, version.getVersionId()));
			parameters.putAll(CuestionarioEmpresaUtil.getParameterParticipantes(themeDisplay, compId, version.getVersionId()));

		}catch (PortalException e) {
			_log.error("ERROR: ", e);
		} 
		
		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @param resourceResponse
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> generarInformeEvaluacion(Long compId,
			ThemeDisplay themeDisplay, Version version, ResourceResponse resourceResponse) throws IOException {
		Map<String, Object> parameters = new HashMap<>();
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
        
		try {
			parameters.putAll(EvaluacionUtils.getParameters(compId, version.getVersionId()));
			parameters.putAll(SeguimientoMedidasUtils.getParameters(compId, version.getVersionId(), bundle, Boolean.TRUE));
		}catch (PortalException e) {
			_log.error("ERROR: ", e);
		} 
		
		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @param resourceResponse
	 * @param resourceRequest 
	 * @return
	 */
	public static Map<String, Object> generarInformeDiagnostico(Long compId, ThemeDisplay themeDisplay, Version version, ResourceResponse resourceResponse, ResourceRequest resourceRequest) {
		Map<String, Object> parameters = new HashMap<>();

		try {
			/*CUESTIONARIO*/
			_log.info("entro en cuestionario");
			parameters.putAll(CuestionarioEmpresaUtil.getParameterAll(themeDisplay, compId, version.getVersionId()));

			/*ESTADISTICA*/
			_log.info("entro en estadisticas");
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 0L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 1L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 2L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 15L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 3L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 4L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 5L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 6L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 14L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 7L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 8L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 9L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 10L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 11L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 12L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 13L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 18L));
			parameters.putAll(ParametrizacionUtils.getParametersParametrizacion(compId, version.getVersionId(),resourceRequest));

			parameters.putAll(ParametrizacionUtils.getParametersDiagnosticos(themeDisplay, compId, version.getVersionId()));
			_log.info("inserto todas las estadisticas");
			if(ParametrizacionUtils.getSituacionIgualdadDTO(compId, version.getVersionId(), 18L)!=null) {
				List<SituacionIgualdadDTO> listAuditoriaRetributiva = new ArrayList<>();
				listAuditoriaRetributiva.add(ParametrizacionUtils.getSituacionIgualdadDTO(compId, version.getVersionId(), 18L));
				JRBeanCollectionDataSource beanSituacionIgualdad = new JRBeanCollectionDataSource(listAuditoriaRetributiva);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.AUDITORIA_RETRIBUTIVA_BEAN, beanSituacionIgualdad);
			}

			if(ParametrizacionUtils.getSituacionIgualdadDTO(compId, version.getVersionId(), 3L)!=null) {
				List<SituacionIgualdadDTO> listAuditoriaRetributiva = new ArrayList<>();
				listAuditoriaRetributiva.add(ParametrizacionUtils.getSituacionIgualdadDTO(compId, version.getVersionId(), 3L));
				JRBeanCollectionDataSource beanSituacionIgualdad = new JRBeanCollectionDataSource(listAuditoriaRetributiva);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.INFRARREPRESENTACION_FEMENINA_BEAN, beanSituacionIgualdad);
			}

		} catch (PortalException | IOException e) {
			_log.error("ERROR: ", e);
		} 
		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @param resourceResponse
	 * @param resourceRequest 
	 * @return
	 */
	public static Map<String, Object> generarInformePlanIgualdad(Long compId, ThemeDisplay themeDisplay, Version version, ResourceResponse resourceResponse, ResourceRequest resourceRequest) {
		Map<String, Object> parameters = new HashMap<>();
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

		try {
			/*CUESTIONARIO*/
			parameters.putAll(CuestionarioEmpresaUtil.getParameterAll(themeDisplay, compId, version.getVersionId()));
			/*ESTADISTICA*/
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 16L));
			parameters.putAll(EstadisticaPlantillaUtil.getParameter(themeDisplay, compId, version.getVersionId(), 18L));

			parameters.putAll(ParametrizacionUtils.getParametersParametrizacion(compId, version.getVersionId(), resourceRequest));

			parameters.putAll(SeguimientoMedidasUtils.getParameters(compId, version.getVersionId(), bundle, Boolean.TRUE));

			Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(compId, version.getVersionId());

			JSONArray arrComisionOrg =  JSONFactoryUtil.createJSONArray();;
			JSONArray arrComisionSocial= JSONFactoryUtil.createJSONArray();
			if(Validator.isNotNull(organizacion)) {
				JSONObject reprComisionOrg = JSONFactoryUtil.createJSONObject(organizacion.getReprComisionOrganizacion());
				arrComisionOrg = reprComisionOrg.getJSONArray("data");
				JSONObject reprComisionSocial = JSONFactoryUtil.createJSONObject(organizacion.getReprComisionSocial());
				arrComisionSocial = reprComisionSocial.getJSONArray("data");
			}
			List<RepresentanteDTO> listRepresentantesOrg = InformacionPreliminarUtil.getListRepresentantes(arrComisionOrg);
			JRBeanCollectionDataSource beanReprOrg = new JRBeanCollectionDataSource(listRepresentantesOrg);
			parameters.put("repComNegRepreOrgBeanCopy", beanReprOrg);	

			List<RepresentanteDTO> listRepresentantesSocial = InformacionPreliminarUtil.getListRepresentantes(arrComisionSocial);
			JRBeanCollectionDataSource beanReprSocial = new JRBeanCollectionDataSource(listRepresentantesSocial);
			parameters.put("repComNegRepreSocBeanCopy", beanReprSocial);
		} catch (PortalException | IOException e) {
			_log.error("ERROR: ", e);
		}
		return parameters;
	}
	
	/**
	 * 
	 * @param resourceRequest
	 * @param compId
	 * @param themeDisplay
	 * @param version
	 * @return
	 */
	public static Map<String, Object> getParametersGlobal(ResourceRequest resourceRequest, Long compId, ThemeDisplay themeDisplay, Version version){
		Map<String, Object> parameters = new HashMap<>();
		try {
			String idTextarea = ParamUtil.getString(resourceRequest, "problemasDetectadosId");

	        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
	        HttpSession pSession = httpReq.getSession();
	       
	        String problemasDetectados = (String)pSession.getAttribute(idTextarea);
			parameters.put(PlanIgualdadGenerarInformePortletKeys.PROBLEMAS_DETECTADOS, problemasDetectados);
			Comp company = CompLocalServiceUtil.fetchComp(compId);
			parameters.put(PlanIgualdadGenerarInformePortletKeys.RAZON_SOCIAL, company.getName());
			parameters.put(PlanIgualdadGenerarInformePortletKeys.CIF, company.getCif());
			parameters.put(PlanIgualdadGenerarInformePortletKeys.NO_INFORMADO, PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
			parameters.put(PlanIgualdadGenerarInformePortletKeys.LOGO_ADEPLUS, GeneralUtils.getLogo(compId, themeDisplay));
			//FECHAS RELEVANTES
			parameters.putAll(FechasRevelantesUtil.getParameters(compId, version.getVersionId()));
			//VERSION
			parameters.putAll(GenerarInformeUtils.getParametersVersion(compId, version));
			parameters.putAll(InformacionPreliminarUtil.getParameters(compId, version.getVersionId(), themeDisplay));
			parameters.put(PlanIgualdadGenerarInformePortletKeys.CONSULTOR , GeneralUtils.getConsultor(compId));
			DateFormat dateFormat = new SimpleDateFormat(PlanIgualdadGenerarInformePortletKeys.FORMAT_DATE);
			parameters.put("fechaGeneracionInforme", dateFormat.format(new Date()));	
		} catch (PortalException | IOException e) {
			_log.error("ERROR: ", e);
		}

        return parameters;
	}
}
