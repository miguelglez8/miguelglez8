<%@page import="com.plan.igualdad.liferay.portlet.cuestionario.constants.PlanIgualdadCuestionarioPortletKeys"%>
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
	
    String idDebilidad = preferences.getValue(PlanIgualdadCuestionarioPortletKeys.ID_DEBILIDAD, "");
    String idDiagnosticos = preferences.getValue(PlanIgualdadCuestionarioPortletKeys.ID_DIAGNOSTICOS, "");
    String idFortaleza = preferences.getValue(PlanIgualdadCuestionarioPortletKeys.ID_FORTALEZA, "");
    String idMedidas = preferences.getValue(PlanIgualdadCuestionarioPortletKeys.ID_MEDIDAS, "");
    String idParametrizaciones = preferences.getValue(PlanIgualdadCuestionarioPortletKeys.ID_PARAMETRIZACIONES, "");

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
        	<h2><liferay-ui:message key="cuestionario.form.config.title" /></h2>
        </div>
        <div class="row">
            <div class="col-sm-12">
            	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            	<aui:form action="<%= configurationURL.toString() %>" method="post" >
            		<aui:input name="<%=PlanIgualdadCuestionarioPortletKeys.ID_DEBILIDAD %>" type="text" label="cuestionario.form.config.id-debilidad" value="<%=idDebilidad%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadCuestionarioPortletKeys.ID_DIAGNOSTICOS %>" type="text" label="cuestionario.form.config.id-diagnostico" value="<%=idDiagnosticos%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadCuestionarioPortletKeys.ID_FORTALEZA %>" type="text" label="cuestionario.form.config.id-fortaleza" value="<%=idFortaleza%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadCuestionarioPortletKeys.ID_MEDIDAS %>" type="text" label="cuestionario.form.config.id-medida" value="<%=idMedidas%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadCuestionarioPortletKeys.ID_PARAMETRIZACIONES %>" type="text" label="cuestionario.form.config.id-parametrizacion" value="<%=idParametrizaciones%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:button-row>
                        <aui:button type="submit" />
                    </aui:button-row>
            	</aui:form>
            </div>
       	</div>
  	</div>
</div>