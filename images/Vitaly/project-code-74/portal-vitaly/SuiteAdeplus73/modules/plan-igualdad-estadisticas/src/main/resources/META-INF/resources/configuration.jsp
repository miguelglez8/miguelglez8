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
	
    String idMedidas = preferences.getValue(PlanIgualdadEstadisticasPortletKeys.ID_MEDIDAS, "");
    String idParametrizaciones = preferences.getValue(PlanIgualdadEstadisticasPortletKeys.ID_PARAMETRIZACIONES, "");
    String idFortaleza = preferences.getValue(PlanIgualdadEstadisticasPortletKeys.ID_FORTALEZA, "");
    String idDebilidad = preferences.getValue(PlanIgualdadEstadisticasPortletKeys.ID_DEBILIDAD, "");
    String idDiagnostico = preferences.getValue(PlanIgualdadEstadisticasPortletKeys.ID_DIAGNOSTICOS, "");

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
        	<h2><liferay-ui:message key="estadisticas.form.config.title" /></h2>
        </div>
        <div class="row">
            <div class="col-sm-12">
            	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            	<aui:form action="<%= configurationURL.toString() %>" method="post" >
					<aui:input name="<%=PlanIgualdadEstadisticasPortletKeys.ID_MEDIDAS %>" type="text" label="estadisticas.form.config.id-medida" value="<%=idMedidas%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadEstadisticasPortletKeys.ID_PARAMETRIZACIONES %>" type="text" label="estadisticas.form.config.id-parametrizacion" value="<%=idParametrizaciones%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadEstadisticasPortletKeys.ID_FORTALEZA %>" type="text" label="estadisticas.form.config.id-fortaleza" value="<%=idFortaleza%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadEstadisticasPortletKeys.ID_DEBILIDAD %>" type="text" label="estadisticas.form.config.id-debilidad" value="<%=idDebilidad%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadEstadisticasPortletKeys.ID_DIAGNOSTICOS %>" type="text" label="estadisticas.form.config.id-diagnostico" value="<%=idDiagnostico%>">
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