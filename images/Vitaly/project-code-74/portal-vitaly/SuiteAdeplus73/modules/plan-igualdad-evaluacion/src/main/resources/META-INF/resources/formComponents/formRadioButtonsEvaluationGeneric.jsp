<%@page import="plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../init.jsp" %>

<%
	String title = ParamUtil.getString(request, PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE);
	String nameInput = ParamUtil.getString(request, PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT);
%>

<div class="row mb-4">
	<div class="col-lg-5 col-md-5 col-12 required">
	   	<legend><liferay-ui:message key="<%=title %>"/></legend>
	</div>
	<div class="col-lg-3 col-md-3 col-12">
		<div class="checksBorder input-group-inline">
			<div class="custom-control custom-radio mr-4">
			  	<aui:input label="planigualdadevaluacion.evaluation.under" class="form-check-input" name="<%=nameInput %>" value="0" type="radio">
			  		<aui:validator name="required" />
			  	</aui:input>
			</div>
			<div class="custom-control custom-radio mr-4">
			 	<aui:input label="planigualdadevaluacion.evaluation.medium" class="form-check-input" name="<%=nameInput %>" value="1" type="radio">
			 		<aui:validator name="required" />
			 	</aui:input>
			</div>
			<div class="custom-control custom-radio mr-4">
				<aui:input label="planigualdadevaluacion.evaluation.tall" class="form-check-input" name="<%=nameInput %>" value="2" type="radio">
					<aui:validator name="required" />
				</aui:input>
			</div>
		</div>
	</div>
</div>