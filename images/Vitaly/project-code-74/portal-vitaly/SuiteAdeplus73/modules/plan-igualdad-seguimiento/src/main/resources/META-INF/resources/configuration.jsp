<%@page import="com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys"%>
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
	
    String folderFiles = preferences.getValue(PlanIgualdadSeguimientoPortletKeys.FOLDER_FILES, "");
    String idMedidas = preferences.getValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, "");
    String idParametrizaciones = preferences.getValue(PlanIgualdadSeguimientoPortletKeys.ID_PARAMETRIZACIONES, "");
    String idFortaleza = preferences.getValue(PlanIgualdadSeguimientoPortletKeys.ID_FORTALEZA, "");
    String idDebilidad = preferences.getValue(PlanIgualdadSeguimientoPortletKeys.ID_DEBILIDAD, "");
    String idDiagnostico = preferences.getValue(PlanIgualdadSeguimientoPortletKeys.ID_DIAGNOSTICOS, "");

%>

<div class="lfr-form-content">
    <div class="sheet sheet-lg">
        <div class="row">
        	<h2><liferay-ui:message key="planigualdadseguimiento.form.config.title" /></h2>
        </div>
        <div class="row">
            <div class="col-sm-12">
            	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
            	<aui:form action="<%= configurationURL.toString() %>" method="post" >
            		<aui:input name="<%=PlanIgualdadSeguimientoPortletKeys.FOLDER_FILES %>" type="text" label="planigualdadseguimiento.form.config.folder" helpMessage="planigualdadseguimiento.form.config.folder.help" value="<%=folderFiles%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS %>" type="text" label="planigualdadseguimiento.form.config.id-medida" value="<%=idMedidas%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadSeguimientoPortletKeys.ID_PARAMETRIZACIONES %>" type="text" label="planigualdadseguimiento.form.config.id-parametrizacion" value="<%=idParametrizaciones%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadSeguimientoPortletKeys.ID_FORTALEZA %>" type="text" label="planigualdadseguimiento.form.config.id-fortaleza" value="<%=idFortaleza%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadSeguimientoPortletKeys.ID_DEBILIDAD %>" type="text" label="planigualdadseguimiento.form.config.id-debilidad" value="<%=idDebilidad%>">
                        <aui:validator name="required" />
                    </aui:input>
                    <aui:input name="<%=PlanIgualdadSeguimientoPortletKeys.ID_DIAGNOSTICOS %>" type="text" label="planigualdadseguimiento.form.config.id-diagnostico" value="<%=idDiagnostico%>">
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