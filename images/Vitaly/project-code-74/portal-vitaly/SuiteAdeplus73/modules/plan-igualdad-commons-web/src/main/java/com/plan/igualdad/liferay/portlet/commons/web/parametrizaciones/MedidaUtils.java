package com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.dto.MedidaDTO;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.model.Respuesta;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.RespuestaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;

import java.util.Date;
import java.util.List;

public class MedidaUtils {

    private static Log _log = LogFactoryUtil.getLog(MedidaUtils.class);

    /**
     * 
     * @param medidasRecordList
     * @param preguntas 
     * @param medidaPreguntas 
     * @param themeDisplay
     * @param medidaParam
     * @param compId8
     * @param versionId
     * @param jsonMedida
     * @param addMedida
     * @param seccionId 
     * @param respuestaIdPregunta 
     * @param respuestaIdPregunta 
     * @throws PortalException
     */
    public static void addOrDeleteMedida(List<DDLRecord> medidasRecordList, JSONArray medidaPreguntas, JSONArray preguntas, ThemeDisplay themeDisplay, String medidaParam, Long compId, Long versionId, Boolean addMedida, int contMatrix, Long seccionId, String respuestaParam, String respuestaIdPregunta) throws PortalException {
        if(Validator.isNotNull(medidasRecordList)&& !medidaParam.isEmpty() && medidasRecordList.size()>0) {
            for(DDLRecord medidaRecord : medidasRecordList) {
                DDMFormValues medidaValuesRecord = medidaRecord.getDDMFormValues();         
                List<DDMFormFieldValue> medidaValuesList = medidaValuesRecord.getDDMFormFieldValues();
                if(Validator.isNotNull(medidaValuesList)) {
                    MedidaDTO medidaDTO = new MedidaDTO();
                    for(DDMFormFieldValue formFieldMedida : medidaValuesList) {
                        String valueInputMedida = formFieldMedida.getValue().getString(themeDisplay.getLocale());
                        String nameInputMedida = formFieldMedida.getName();
                        
                        if(PlanIgualdadCommonsPortletKeys.ID.equals(nameInputMedida)) {
                            medidaDTO.setId(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.ID_ESTADOS_APLICA.equals(nameInputMedida)) {
                            medidaDTO.setIdEstadosAplica(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.ID_ESTADOS_APLICA_SIEMPRE.equals(nameInputMedida)) {
                            medidaDTO.setIdEstadosAplicaSiempre(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.ID_ESTADOS_APLICA_NUNCA.equals(nameInputMedida)) {
                            medidaDTO.setIdEstadosAplicaNunca(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.MATERIA.equals(nameInputMedida)) {
                            medidaDTO.setMateria(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.MEDIDA_PARAM.equals(nameInputMedida)) {
                            medidaDTO.setMedida(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.OBJETIVOS.equals(nameInputMedida)) {
                            medidaDTO.setObjetivos(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.DESCRIPCION.equals(nameInputMedida)) {
                            medidaDTO.setDescripcion(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.PRIORIDAD.equals(nameInputMedida)) {
                            medidaDTO.setPrioridad(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.RECURRENTE.equals(nameInputMedida)) {
                            medidaDTO.setRecurrente(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.RECURSOSASOCIADOS.equals(nameInputMedida)) {
                            medidaDTO.setRecursosAsociados(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.INDICADORES.equals(nameInputMedida)) {
                            medidaDTO.setIndicadores(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.PERIODICIDAD.equals(nameInputMedida)) {
                            medidaDTO.setPeriocidad(valueInputMedida);
                        }
                    }

                    if(("select_0".equals(respuestaParam) || "select_1".equals(respuestaParam) )&& medidaParam.equals(medidaDTO.getId())) {
                        Boolean addSelect = GeneralUtils.isCumpleSelect(respuestaIdPregunta, medidaParam, compId, versionId, seccionId);                                                
                        if(addSelect) {
                            addMedida(compId, versionId, themeDisplay, medidaDTO);
                        }else {
                            deleteMedida(compId, medidaDTO);
                        }
                    }else {
                        if(medidaParam.contains(",")) {
                            String[] partsMedida = medidaParam.split(",");
                            for(String partMedida : partsMedida) {
                                String partWhiteSpace = partMedida.replaceAll("\\s","");
                                if(partWhiteSpace.equals(medidaDTO.getId())) {
                                    if(addMedida) {
                                        addMedida(compId, versionId, themeDisplay, medidaDTO);
                                    }else {
                                        deleteMedida(compId, medidaDTO);
                                    }
                                }else if (partWhiteSpace.contains("NAN")) {
                                    if(Validator.isNotNull(medidaPreguntas)) {
                                        for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
                                            JSONObject object = medidaPreguntas.getJSONObject(i);
                                            if(object.getString(PlanIgualdadCommonsPortletKeys.MEDIDA).equals(medidaDTO.getId())) {
                                                Boolean addFortaleza_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);                                               
                                                if(addFortaleza_) {
                                                    addMedida(compId, versionId, themeDisplay, medidaDTO);
                                                }else {
                                                    deleteMedida(compId, medidaDTO);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else if(!medidaParam.contains(",") && medidaParam.contains("NAN")) {
                            if(Validator.isNotNull(medidaPreguntas)) {
                                for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
                                    JSONObject object = medidaPreguntas.getJSONObject(i);
                                    if(object.getString(PlanIgualdadCommonsPortletKeys.MEDIDA).equals(medidaDTO.getId())) {
                                        Boolean addFortaleza_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);
                                        if(addFortaleza_) {
                                            addMedida(compId, versionId, themeDisplay, medidaDTO);
                                        }else {
                                            deleteMedida(compId, medidaDTO);
                                        }
                                    }
                                }
                            }
                        }else if (medidaParam.equals(medidaDTO.getId())){
                            if(addMedida) {
                                addMedida(compId, versionId, themeDisplay, medidaDTO);
                            }else {
                                deleteMedida(compId, medidaDTO);
                            }
                        }
                    }
                    
                    String currentState = EstadoUtils.getIdEstado(compId);
                    
                    if(Validator.isNotNull(medidaDTO.getIdEstadosAplicaSiempre()) && !medidaDTO.getIdEstadosAplicaSiempre().isEmpty()) {
                        GeneralUtils.gestionEstadosAplica(medidaDTO, currentState, compId, versionId, themeDisplay);
                    }
                    
                    if(Validator.isNotNull(medidaDTO.getIdEstadosAplicaNunca()) && !medidaDTO.getIdEstadosAplicaNunca().isEmpty()) {
                        GeneralUtils.gestionEstadosAplicaNunca(medidaDTO, currentState, compId);
                    }
                }
            }
        }
    }
    
    private static void addOrDeleteMedidaEst(List<DDLRecord> medidasRecordList, ThemeDisplay themeDisplay, String medidaParam, Long compId, Long versionId, JSONObject jsonMedida, Boolean addMedida) throws PortalException {
        if(Validator.isNotNull(medidasRecordList) && medidasRecordList.size()>0) {
            for(DDLRecord medidaRecord : medidasRecordList) {
                DDMFormValues medidaValuesRecord = medidaRecord.getDDMFormValues();
                List<DDMFormFieldValue> medidaValuesList = medidaValuesRecord.getDDMFormFieldValues();
                if(Validator.isNotNull(medidaValuesList)) {
                    Boolean haveMedida = Boolean.FALSE;
                    MedidaDTO medidaDTO = new MedidaDTO();
                    
                    boolean manageMeasure = Boolean.TRUE;
                    
                    for(DDMFormFieldValue formFieldMedida : medidaValuesList) {
                        
                        String valueInputMedida = formFieldMedida.getValue().getString(themeDisplay.getLocale());
                        String nameInputMedida = formFieldMedida.getName();
                        
                        if(PlanIgualdadCommonsPortletKeys.ID.equals(nameInputMedida)) {
                            medidaDTO.setId(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.ID_ESTADOS_APLICA.equals(nameInputMedida)) {
                            medidaDTO.setIdEstadosAplica(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.MATERIA.equals(nameInputMedida)) {
                            medidaDTO.setMateria(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.MEDIDA_PARAM.equals(nameInputMedida)) {
                            medidaDTO.setMedida(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.OBJETIVOS.equals(nameInputMedida)) {
                            medidaDTO.setObjetivos(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.DESCRIPCION.equals(nameInputMedida)) {
                            medidaDTO.setDescripcion(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.PRIORIDAD.equals(nameInputMedida)) {
                            medidaDTO.setPrioridad(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.RECURRENTE.equals(nameInputMedida)) {
                            medidaDTO.setRecurrente(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.RECURSOSASOCIADOS.equals(nameInputMedida)) {
                            medidaDTO.setRecursosAsociados(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.INDICADORES.equals(nameInputMedida)) {
                            medidaDTO.setIndicadores(valueInputMedida);
                        }else if(PlanIgualdadCommonsPortletKeys.PERIODICIDAD.equals(nameInputMedida)) {
                            medidaDTO.setPeriocidad(valueInputMedida);
                        }
                        
                        if(Validator.isNotNull(medidaDTO.getIdEstadosAplica()) && !medidaDTO.getIdEstadosAplica().isEmpty()) {
                            String currentState = EstadoUtils.getIdEstado(compId);
                            if(medidaDTO.getIdEstadosAplica().contains(",")) {
                                String[] partsIdEstadosAplica = medidaDTO.getIdEstadosAplica().split(",");
                                for(String partIdEstado : partsIdEstadosAplica) {
                                    String partWhiteSpace = partIdEstado.replaceAll("\\s","");
                                    if(partWhiteSpace.equals(medidaDTO.getId()) && !currentState.equals(medidaDTO.getIdEstadosAplica())) {
                                        manageMeasure = Boolean.FALSE;
                                    }
                                }
                            }else if(!currentState.equals(medidaDTO.getIdEstadosAplica())){
                                manageMeasure = Boolean.FALSE;
                            }
                        }
                        
                        if(!medidaParam.isEmpty() && medidaParam.contains(",")) {
                            String[] partsMedida = medidaParam.split(",");
                            
                            for(String partMedida : partsMedida) {
                                String partWhiteSpace = partMedida.replaceAll("\\s","");
                                if(partWhiteSpace.equals(valueInputMedida)) {
                                    haveMedida= Boolean.TRUE ;
                                }
                            }
                        }else if(!medidaParam.isEmpty() && medidaParam.equals(valueInputMedida)){
                            haveMedida= Boolean.TRUE ;
                        }
                    }
                    
                    if(haveMedida && manageMeasure) {
                        if(addMedida) {
                            addMedidaEst(jsonMedida, compId, versionId, themeDisplay, medidaDTO);
                        }else {
                            deleteMedidaEst(jsonMedida, compId, medidaDTO);
                        }
                    }
                }
            }
        }
    }
    
    private static void addMedidaEst(JSONObject jsonMedida, Long compId, Long versionId, ThemeDisplay themeDisplay, MedidaDTO medidaDTO) {
        _log.debug("init addMedida(JSONObject jsonMedida, Long compId, Long versionId, ThemeDisplay themeDisplay, MedidaDTO medidaDTO)");

        Medida existMedida = MedidaLocalServiceUtil.findMedidaPredefinida(compId, medidaDTO.getId());

        if(Validator.isNull(existMedida)) {
            Medida medida = MedidaLocalServiceUtil.createMedida(CounterLocalServiceUtil.increment(Medida.class.getName()));
            Version version = VersionLocalServiceUtil.findVersionActiva(compId);
            
            medida.setVersionId(version.getVersionId());
            medida.setCompId(compId);
            medida.setIsMedidaPredefinida(Boolean.FALSE);
            medida.setUserId(themeDisplay.getUserId());
            medida.setCreateDate(new Date());
            medida.setDatosGenerales(StringUtil.replace(getJSONDatosGenerales(medidaDTO), StringPool.BACK_SLASH, StringPool.DOUBLE_BACK_SLASH));
            medida.setMedidaPredefinidaId(medidaDTO.getId());
            medida.setIsMedidaPredefinida(Boolean.TRUE);
            medida.setVersionMedida(1);
            medida.setVersionOriginalMedidaId(medida.getMedidaId());
            
            MedidaLocalServiceUtil.updateMedida(medida);
        }else {
            _log.debug("Ya existe la medida, con el id: " + existMedida.getMedidaId());
        }
        
        _log.debug("end addMedida(JSONObject jsonMedida, Long compId, Long versionId, ThemeDisplay themeDisplay, MedidaDTO medidaDTO)");
    }
    
    /**
     * DELETE MEDIDA
     * @param jsonMedida
     * @param compId
     * @throws PortalException
     */
    private static void deleteMedidaEst(JSONObject jsonMedida, Long compId, MedidaDTO medidaDTO) throws PortalException {
        Medida medida = MedidaLocalServiceUtil.findMedidaPredefinida(compId, medidaDTO.getId());
        
        if(Validator.isNotNull(medida)) {
            _log.debug("Se esta eliminando la medida: " + medida.getMedidaId());
            MedidaLocalServiceUtil.deleteMedida(medida.getMedidaId());
        }
        
    }
    
    /**
     * 
     * @param medidaDTO
     * @return
     */
    public static String getJSONDatosGenerales(MedidaDTO medidaDTO) {
        JSONObject jsonDatosGenerales = JSONFactoryUtil.createJSONObject();
        
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MATTER, Integer.parseInt(medidaDTO.getMateria()));
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MEASURE_NAME,  medidaDTO.getMedida());
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MEASURE_DESCRIPTION,  medidaDTO.getDescripcion());
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.OBJECTIVES_PURSUED,  medidaDTO.getObjetivos());
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.PRIORITY,  Integer.parseInt(medidaDTO.getPrioridad()));
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.APPLY,  "Si");
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.ASSOCIATED_RESOURCES,  medidaDTO.getRecursosAsociados());
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MONITORING_INDICATORS,  medidaDTO.getIndicadores());
        jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.PERIODICITY,  medidaDTO.getPeriocidad());

        return jsonDatosGenerales.toString();
    }
    
    /**
     * ADD MEDIDA
     * @param jsonMedida
     * @param compId
     * @param themeDisplay
     */
    public static void addMedida(Long compId, Long versionId, ThemeDisplay themeDisplay, MedidaDTO medidaDTO) {
        _log.debug("init addMedida(Long compId, Long versionId, ThemeDisplay themeDisplay, MedidaDTO medidaDTO)");

        Medida existMedida = MedidaLocalServiceUtil.findMedidaPredefinida(compId, medidaDTO.getId());

        if(Validator.isNull(existMedida)) {
            Medida medida = MedidaLocalServiceUtil.createMedida(CounterLocalServiceUtil.increment(Medida.class.getName()));
            Version version = VersionLocalServiceUtil.findVersionActiva(compId);
            
            medida.setVersionId(version.getVersionId());
            medida.setCompId(compId);
            medida.setIsMedidaPredefinida(Boolean.FALSE);
            medida.setUserId(themeDisplay.getUserId());
            medida.setCreateDate(new Date());
            medida.setDatosGenerales(GeneralUtils.getJSONDatosGenerales(medidaDTO));
            medida.setMedidaPredefinidaId(medidaDTO.getId());
            medida.setIsMedidaPredefinida(Boolean.TRUE);
            medida.setVersionMedida(1);
            medida.setVersionOriginalMedidaId(medida.getMedidaId());
            
            MedidaLocalServiceUtil.updateMedida(medida);
        }else {
            _log.debug("Ya existe la medida, con el id: " + existMedida.getMedidaId());
        }
        
        _log.debug("end addMedida(JSONObject jsonMedida, Long compId, Long versionId, ThemeDisplay themeDisplay, MedidaDTO medidaDTO)");
    }
    
    /**
     * DELETE MEDIDA
     * @param compId
     * @throws PortalException
     */
    public static void deleteMedida(Long compId, MedidaDTO medidaDTO) throws PortalException {
        Medida medida = MedidaLocalServiceUtil.findMedidaPredefinida(compId, medidaDTO.getId());
        
        if(Validator.isNotNull(medida)) {
            _log.debug("Se esta eliminando la medida: " + medida.getMedidaId());
            MedidaLocalServiceUtil.deleteMedida(medida.getMedidaId());
        }
        
    }
    
    public static void gestionMedidaEst(JSONObject jsonMedida, Boolean addMedida, List<DDLRecord> medidasRecordList, ThemeDisplay themeDisplay, Long compId, Long versionId, JSONArray preguntas, JSONArray medidaPreguntas) throws PortalException {

        String medidaParam = jsonMedida.getString(PlanIgualdadCommonsPortletKeys.MEDIDA);
        
        if(medidaParam.contains("NAN")) {
            Boolean cumpleRespuestas = Boolean.TRUE;
            if(Validator.isNotNull(preguntas)) {
                for(int i = 0 ; i< preguntas.length() ; i++) {
                    JSONObject object = preguntas.getJSONObject(i);
                    
                    if(medidaParam.contains(object.getString(PlanIgualdadCommonsPortletKeys.ID_NAN))) {
                        List<Respuesta> respuestasList = RespuestaLocalServiceUtil.fetchRespuestas(compId, versionId);
                
                        if(Validator.isNotNull(respuestasList)) {
                            for(Respuesta respuesta : respuestasList) {
                                if(respuesta.getSeccionId()!=0L && !respuesta.getRespuestas().equals("[]")) {
                                    JSONArray jsonArrayRespuesta = JSONFactoryUtil.createJSONArray(respuesta.getRespuestas());
                                    for(int x = 0 ; x< jsonArrayRespuesta.length() ; x++) {
                                        JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
                                        
                                        if(objectRespuesta.getString("name").equals(object.getString(PlanIgualdadCommonsPortletKeys.PREGUNTA))) {
                                            if(!objectRespuesta.getString("value").equals(object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA))) {
                                                cumpleRespuestas = Boolean.FALSE;

                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            if(Validator.isNotNull(medidaPreguntas)) {
                for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
                    JSONObject object = medidaPreguntas.getJSONObject(i);

                    addOrDeleteMedidaEst(medidasRecordList, themeDisplay, object.getString(PlanIgualdadCommonsPortletKeys.MEDIDA),compId, versionId, jsonMedida, (medidaParam.equals(object.getString(PlanIgualdadCommonsPortletKeys.ID_NAN)) &&
                            object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == cumpleRespuestas) ? Boolean.TRUE : Boolean.FALSE);
                }
                
                if(medidaParam.contains(",")) {
                    addOrDeleteMedidaEst(medidasRecordList, themeDisplay, medidaParam,compId, versionId, jsonMedida, addMedida);
                }
            }
        
        }else {
            addOrDeleteMedidaEst(medidasRecordList, themeDisplay, medidaParam,compId, versionId, jsonMedida, addMedida);
        }
    }
}
