package com.plan.igualdad.liferay.portlet.informacion.web.portlet.command;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.informacion.web.constants.PlanIgualdadInformacionWebPortletKeys;
import com.plan.igualdad.liferay.portlet.informacion.web.validators.OrganizacionValidator;
import com.plan.igualdad.liferay.portlet.manager.model.Organizacion;
import com.plan.igualdad.liferay.portlet.manager.service.OrganizacionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.OrganizacionPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadInformacionWebPortletKeys.PLANIGUALDADINFORMACIONWEB,
                "mvc.command.name=/organizacion/save"
        },
        service = MVCActionCommand.class
)
public class SaveInformacionPreliminarActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveInformacionPreliminarActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            Long organizacionId = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_ID);
            Long versionId = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_VERSIONID);
            Long compId = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_COMPDID);

            String annoConstitucion = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_ANNO_CONSTITUCION, StringPool.BLANK);
            String paginaWeb = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_PAGINA_WEB, StringPool.BLANK);
            String domicilioSocial = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DOMICILIO, StringPool.BLANK);
            String facturacion = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_FACTURACION, StringPool.BLANK);
            String formaJuridica = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_FORMA_JURIDICA, StringPool.BLANK);
            String nombreResponsableEntidad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NOMBRE_RESPONSABLE_ENTIDAD, StringPool.BLANK);
            String telefonoResponsableEntidad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_TELEFONO_RESPONSABLE_ENTIDAD, StringPool.BLANK);
            String cargoResponsableEntidad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CARGO_RESPONSABLE_ENTIDAD, StringPool.BLANK);
            String emailResponsableEntidad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_EMAIL_RESPONSABLE_ENTIDAD, StringPool.BLANK);
            String nombreResponsableIgualdad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NOMBRE_RESPONSABLE_IGUALDAD, StringPool.BLANK);
            String telefonoResponsableIgualdad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_TELEFONO_RESPONSABLE_IGUALDAD, StringPool.BLANK);
            String cargoResponsableIgualdad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CARGO_RESPONSABLE_IGUALDAD, StringPool.BLANK);
            String emailResponsableIgualdad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_EMAIL_RESPONSABLE_IGUALDAD, StringPool.BLANK);
            String[] cnaes = ParamUtil.getStringValues(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CANES);
            Long nCentros = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NCENTROS);
            String descripcionActividad = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DESCRIPCION_ACTIVIDAD, StringPool.BLANK);
            String convenio = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_CONVENIOS, StringPool.BLANK);
            String ambito = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_AMBITO, StringPool.BLANK);
            String comentarioAmbito = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_COMENTARIO_AMBITO, StringPool.BLANK);
            Long nHombrePlantilla = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NHOMBRES_PLANTILLA);
            Long nMujeresPlantilla = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NMUJERES_PLANTILLA);
            String representacionLegal = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_LEGAL);
            Long nRepresentacionLegalHombres = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_NHOMBRE);
            Long nRepresentacionLegalMujeres = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_NMUJERES);
            String representacionTotal = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_RESPRESENTACION_TOTAL, StringPool.BLANK);
            Long nNoRepresentados = ParamUtil.getLong(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_NNOREPRESENTADOS);
            String comentarioRepresentacion = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_COMENTARIO_REPRESENTADOS, StringPool.BLANK);
            String departamentoPersonas = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_DEPARTAMENTO_PERSONAS, StringPool.BLANK);
            String sindicatos = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_SINDICATOS, StringPool.BLANK);
            String reprComisionOrganizacion = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_COMISION, StringPool.BLANK);
            String reprComisionSocial = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_SOCIAL, StringPool.BLANK);
            String reprPlanOrganizacion = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_PLAN, StringPool.BLANK);
            String reprPlanSociales = ParamUtil.getString(actionRequest, PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_SOCIALES, StringPool.BLANK);

            if (!OrganizacionValidator.validOrganizacion(compId, versionId, cnaes, nCentros, convenio, ambito,
                    representacionLegal, departamentoPersonas, sindicatos)) {
                SessionErrors.add(actionRequest, "organizacion-save-error");

            } else {
                OrganizacionPK organizacionPK = new OrganizacionPK();
                organizacionPK.setCompId(compId);
                organizacionPK.setVersionId(versionId);
                organizacionPK.setOrganizacionId(organizacionId);

                Organizacion organizacion = OrganizacionLocalServiceUtil.fetchOrganizacion(organizacionPK);
                if (Validator.isNull(organizacion)) {
                    organizacionPK.setOrganizacionId(CounterLocalServiceUtil.increment(Organizacion.class.getName()));
                    organizacion = OrganizacionLocalServiceUtil.createOrganizacion(organizacionPK);
                }

                organizacion.setAnnoConstitucion(annoConstitucion);
                organizacion.setPaginaWeb(paginaWeb);
                organizacion.setDomicilioSocial(domicilioSocial);
                organizacion.setFacturacionAnual(facturacion);
                organizacion.setFormaJuridica(formaJuridica);
                organizacion.setNombreResponsableEntidad(nombreResponsableEntidad);
                organizacion.setTelefonoResponsableEntidad(telefonoResponsableEntidad);
                organizacion.setCargoResponsableEntidad(cargoResponsableEntidad);
                organizacion.setEmailResponsableEntidad(emailResponsableEntidad);
                organizacion.setNombreResponsableIgualdad(nombreResponsableIgualdad);
                organizacion.setTelefonoResponsableIgualdad(telefonoResponsableIgualdad);
                organizacion.setCargoResponsableIgualdad(cargoResponsableIgualdad);
                organizacion.setEmailResponsableIgualdad(emailResponsableIgualdad);
                organizacion.setCnaes(String.join(StringPool.SEMICOLON, cnaes));
                organizacion.setNCentros(nCentros);
                organizacion.setDescripcionActividad(descripcionActividad);
                organizacion.setConvenio(convenio);
                organizacion.setAmbito(ambito);
                organizacion.setComentarioAmbito(comentarioAmbito);
                organizacion.setNHombresPlantilla(nHombrePlantilla);
                organizacion.setNMujeresPlantilla(nMujeresPlantilla);
                organizacion.setRepresentacionLegal(representacionLegal);
                organizacion.setNRepresentacionLegalHombres(nRepresentacionLegalHombres);
                organizacion.setNRepresentacionLegalMujeres(nRepresentacionLegalMujeres);
                organizacion.setRepresentaTotalidad(representacionTotal);
                organizacion.setNNoRepresentados(nNoRepresentados);
                organizacion.setComentarioRepresentacion(comentarioRepresentacion);
                organizacion.setDepartamentoPersonal(departamentoPersonas);
                organizacion.setSindicatos(sindicatos);
                organizacion.setReprComisionOrganizacion(reprComisionOrganizacion);
                organizacion.setReprComisionSocial(reprComisionSocial);
                organizacion.setReprPlanOrganizacion(reprPlanOrganizacion);
                organizacion.setReprPlanSociales(reprPlanSociales);

                OrganizacionLocalServiceUtil.updateOrganizacion(organizacion);

                SessionMessages.add(actionRequest, "organizacion-save-success");

            }
            actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId));

        } catch (IOException e) {
            _log.error(e, e);
        }

        return false;
    }

    private String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, Long compId) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", "/");
        redirectURL.setParameter(PlanIgualdadInformacionWebPortletKeys.COMPID_PARAM, String.valueOf(compId));
        return  redirectURL.toString();
    }

}
