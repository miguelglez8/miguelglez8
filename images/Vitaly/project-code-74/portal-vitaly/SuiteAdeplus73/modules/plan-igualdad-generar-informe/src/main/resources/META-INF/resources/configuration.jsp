<%@page import="com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ include file="init.jsp" %>

<%
	PortletPreferences preferences = renderRequest.getPreferences();
	String portletResource = ParamUtil.getString(request, "portletResource");
	if (Validator.isNotNull(portletResource)) {
        preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
    }
	
    String planIgualdad = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD, "");
    String diagnostico = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD, "");
    String auditoria = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD, "");
    String seguimiento = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD, "");
    String evaluacion = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD, "");
    String cuestionario = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION, "");
	
    String idDebilidad = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.ID_DEBILIDAD, "");
    String idFortaleza = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.ID_FORTALEZA, "");
    String idDiagnosticos = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.ID_DIAGNOSTICOS, "");
    String idMedidas = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.ID_MEDIDAS, "");
    String idParametrizaciones = preferences.getValue(PlanIgualdadGenerarInformePortletKeys.ID_PARAMETRIZACIONES, "");

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
            <div class="col-sm-12">
            	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            	<aui:form action="<%= configurationURL.toString() %>" method="post" >
            		<aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.ID_DEBILIDAD %>" type="text" label="generar.form.config.id-debilidad" value="<%=idDebilidad%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.ID_FORTALEZA %>" type="text" label="generar.form.config.id-fortaleza" value="<%=idFortaleza%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.ID_DIAGNOSTICOS %>" type="text" label="generar.form.config.id-diagnostico" value="<%=idDiagnosticos%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.ID_MEDIDAS %>" type="text" label="generar.form.config.id-medida" value="<%=idMedidas%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.ID_PARAMETRIZACIONES %>" type="text" label="generar.form.config.id-parametrizacion" value="<%=idParametrizaciones%>">
                        <aui:validator name="required" />
                    </aui:input>
            		<aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD %>" type="text" label="informe.plan-igualdad" value="<%=planIgualdad%>">
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD %>" type="text" label="informe.informe-diagnostico" value="<%=diagnostico%>">
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD %>" type="text" label="informe.auditoria-retributiva" value="<%=auditoria%>">
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD %>" type="text" label="informe.informe-seguimiento" value="<%=seguimiento%>">
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD %>" type="text" label="informe.informe-evaluacion" value="<%=evaluacion%>">
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION %>" type="text" label="informe.cuestionario-empresa" value="<%=cuestionario%>">
                    </aui:input>
                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>
            	</aui:form>
            </div>
       	</div>
  	</div>
</div>