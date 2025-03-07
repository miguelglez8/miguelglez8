<%@ include file="/init.jsp" %>

<%

Long compId = (Long) request.getAttribute(PlanIgualdadAreaWebPortletKeys.COMPID_PARAM);
Long versionId = (Long) request.getAttribute(PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM);
Area area = (Area) request.getAttribute(PlanIgualdadAreaWebPortletKeys.AREA_PARAM);

boolean emptyFechaBaja = true;
Calendar calendarFechaBaja = Calendar.getInstance();

if (Validator.isNotNull(area)) {

    if (Validator.isNotNull(area.getBaja())) {
        calendarFechaBaja.setTime(area.getBaja());
        emptyFechaBaja = false;
    }

}

%>

<portlet:renderURL var="cancelURL">
    <portlet:param name="compId" value="${compId}"></portlet:param>
    <portlet:param name="mvcRenderCommandName" value="/"></portlet:param>
</portlet:renderURL>

<div class="content">
    <div class="formulario">

        <div class="title-group mt-3">
            <a href="<%= cancelURL %>" class="toBackView">
                <i class="icon-arrow-left"></i>
                <liferay-ui:message key="area.view.back"/>
            </a>
        </div>

        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

            <liferay-portlet:actionURL name="/area/save" var="areaURL" />
            <aui:form  name="area_form" action="<%=areaURL%>" method="post">

                <aui:input type="hidden" name="<%= PlanIgualdadAreaWebPortletKeys.COMPID_PARAM %>" value="<%= compId %>"></aui:input>
                <aui:input type="hidden" name="<%= PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM %>" value="<%= versionId %>"></aui:input>
                <aui:input type="hidden" name="<%= PlanIgualdadAreaWebPortletKeys.AREAID_PARAM %>"
                    value="<%= Validator.isNotNull(area) ? area.getAreaId() : StringPool.BLANK %>"></aui:input>

                <div class="row align-items-start mb-4">

                    <%-- AREA --%>
                    <div class="form-group col-md-6 col-6 required">
                        <div class="form-group input-text-wrapper">
                            <aui:input type="text" label="area.view.area"
                                    name="<%= PlanIgualdadAreaWebPortletKeys.AREA_NOMBRE %>"
                                    value="<%= Validator.isNotNull(area) ? area.getNombre() : StringPool.BLANK %>" >
                                <aui:validator name="required" errorMessage="area.view.validator.required" />
                            </aui:input>
                        </div>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FECHA BAJA --%>
                    <div class="form-group col-md-3 col-12">
                        <div class="form-group input-text-wrapper">
                            <label class="control-label" for="fechaBaja">
                                <liferay-ui:message key="area.view.baja"/>
                            </label>
                            <c:if test="<%= emptyFechaBaja %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadAreaWebPortletKeys.AREA_BAJA %>" nullable="true" showDisableCheckbox="false"/>
                            </c:if>
                            <c:if test="<%= !emptyFechaBaja %>">
                                <liferay-ui:input-date name="<%= PlanIgualdadAreaWebPortletKeys.AREA_BAJA %>" nullable="true" showDisableCheckbox="false"
                                        yearValue="<%= calendarFechaBaja.get(Calendar.YEAR) %>"
                                        monthValue="<%= calendarFechaBaja.get(Calendar.MONTH) %>"
                                        dayValue="<%= calendarFechaBaja.get(Calendar.DAY_OF_MONTH) %>" />
                            </c:if>
                        </div>
                    </div>

                </div>

                <div class="button-holder d-flex justify-content-end my-4">
                    <aui:button-row>
                        <aui:button onClick="<%= cancelURL %>" value="back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
                        <aui:button data-btn="submit" value="area.view.save" cssClass="btn btn-primary" />
                    </aui:button-row>
                </div>

            </aui:form>

        </div>

    </div>
</div>

<div class="modal hide" id="modalArea">
    <div class="modal-backdrop show"></div>
    <div class="modal-dialog center">
        <div class="modal-content">
	        <div class="modal-header">
	            <h5 class="modal-title"><liferay-ui:message key="area.modal.title" /></h5>
	            <button type="button" class="close hide-modal">
	              <span aria-hidden="true">&times;</span>
	            </button>
	        </div>
	        <div class="modal-body">
	        	<p><liferay-ui:message key="area.modal.text" /></p>
	        </div>
	        <div class="modal-footer">
	        	<aui:button value="area.modal.back" cssClass="btn btn-outline-primary mr-4 hide-modal" primary="true"></aui:button>
	            <aui:button data-btn="acceptModal" value="area.modal.accept" cssClass="btn btn-primary"/>	        
	        </div>
        </div>
    </div>
</div>

<script>
	$(document).ready( function () {
		
		$('[data-btn="submit"]').on('click', function() {
			if($('#<portlet:namespace/><%=PlanIgualdadAreaWebPortletKeys.AREA_BAJA%>').val() != "" ){
		   		$('#modalArea').removeClass('hide');
			    $('#modalArea').addClass('show');
		   	}else{
		   		$('#<portlet:namespace/>area_form').submit();
		   	}
		});
		
		$('[data-btn="acceptModal"]').on('click', function() {
			$('#<portlet:namespace/>area_form').submit();
		});
		
		$(document).on('click', '.hide-modal', function(e) {
		    e.preventDefault();
		    $('#modalArea').addClass('hide');
		});
	});
</script>