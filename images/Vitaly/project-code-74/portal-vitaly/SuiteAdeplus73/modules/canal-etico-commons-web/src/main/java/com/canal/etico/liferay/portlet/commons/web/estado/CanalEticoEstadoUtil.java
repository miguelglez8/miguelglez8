package com.canal.etico.liferay.portlet.commons.web.estado;

import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class CanalEticoEstadoUtil {

    private static Log _log = LogFactoryUtil.getLog(CanalEticoEstadoUtil.class);

    public static boolean esCampoVisibleDenuncia(long denunciaId){

        Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

        return esCampoVisibleDenuncia(denuncia.getDenunciaId());
    }

    public static boolean esCampoVisibleDenuncia(Denuncia denuncia){

        int daysAfterFinish = PrefsPropsUtil.getInteger(CanalEticoCommonsPortletKeys.PROPERTY_DAYS_AFTER_FINISH_VIEW, 1);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(new Date());
        calendarEnd.add(Calendar.DAY_OF_MONTH, -daysAfterFinish);


        if(_log.isDebugEnabled() && Validator.isNotNull(denuncia)){
            _log.debug("--------------------------------------");
            _log.debug("Denuncia ID: " + denuncia.getDenunciaId() +", codigo: " + denuncia.getCodigo() + ", estado: " + denuncia.getEstado());
            if(Validator.isNotNull(denuncia.getEndDate())) {
                _log.debug("Denuncia enddate: " + denuncia.getEndDate() + " before " + calendarEnd.getTime() + ": " + denuncia.getEndDate().before(calendarEnd.getTime()));
            }
        }

        if(Validator.isNotNull(denuncia)
                && (denuncia.getEstado() == 2 || denuncia.getEstado() == 3)
                && Validator.isNotNull(denuncia.getEndDate())
                && denuncia.getEndDate().before(calendarEnd.getTime())){

            if(_log.isDebugEnabled()){
                _log.debug("esCampoVisibleDenuncia: " + false);
            }

            return false;

        }
        if(_log.isDebugEnabled()){
            _log.debug("esCampoVisibleDenuncia: " + true);
        }

        return true;
    }

    public static boolean estaActivaDenuncia(long denunciaId){

        Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

        if(Validator.isNotNull(denuncia)){
            return esEstadoActivo(denuncia.getEstado());
        }
        return true;
    }

    public static boolean esEstadoActivo(long estadoId){

        if(estadoId == 2 || estadoId == 3){
            return false;
        }

        return true;
    }

    public static String getNombreEstadoPorId(long estadoId, Locale locale){

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", locale);
        String registrado  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.registrado");
        String enProceso  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.en.proceso");
        String finalizado  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.finalizado");
        String sinFinalizar = LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.sin.finalizar");

        String estadoDenuncia = registrado;
        if(estadoId == CanalEticoCommonsPortletKeys.ESTADO_EN_PROCESO){
            estadoDenuncia = enProceso;
        }else if(estadoId == CanalEticoCommonsPortletKeys.ESTADO_FINALIZADO){
            estadoDenuncia = finalizado;
        }else if(estadoId == CanalEticoCommonsPortletKeys.ESTADO_SIN_FINALIZAR){
            estadoDenuncia = sinFinalizar;
        }
        return estadoDenuncia;
    }
}
