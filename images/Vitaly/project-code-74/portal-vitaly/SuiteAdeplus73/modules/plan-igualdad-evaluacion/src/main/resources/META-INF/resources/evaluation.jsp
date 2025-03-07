<%@page import="java.util.Date"%>
<%@page import="plan.igualdad.evaluacion.dto.PlanIgualdadEvaluacionDTO"%>
<%@page import="plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys"%>
<%@ include file="init.jsp" %>

<%

boolean hiddenSave = (Boolean) request.getAttribute(PlanIgualdadEvaluacionPortletKeys.EVALUATION_HIDDEN_SAVE);

PlanIgualdadEvaluacionDTO evaluacion = null;

if(hiddenSave){
	evaluacion = (PlanIgualdadEvaluacionDTO) request.getAttribute(PlanIgualdadEvaluacionPortletKeys.EVALUATION_OBJECT);
}

String compId = (String) request.getParameter(PlanIgualdadEvaluacionPortletKeys.COMPIDEVALUATION_PARAM);
%>
<liferay-portlet:actionURL name="/planigualdad/saveEvaluation" var="saveEvaluationURL"  />

<liferay-ui:success key="evaluation-view-success" message="planigualdadevaluacion.message.view-evaluation-success" />

<aui:form action="<%= saveEvaluationURL.toString() %>" method="post">
	<aui:input name="<%= PlanIgualdadEvaluacionPortletKeys.COMPID_PARAM %>" value="<%= compId %>" type="hidden" />
	
	<div class="content">
		<div class="formulario">
			<div class="form-content mb-4">
				<div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
					<div class="input-group mb-3">
						<%--DATOS GENERALES --%>
						<legend class="fieldset-title-section"><liferay-ui:message key="planigualdadevaluacion.general-data"/></legend>
	            		<fieldset class="mt-3 mb-2 ml-5">
	                       	<div class="row mb-4">
	                       		<div class="col-lg-3 col-md-3 col-12 required">
	                       			<legend><liferay-ui:message key="planigualdadevaluacion.general-data-type"/></legend>
	                       		</div>
	                       		<div class="col-lg-3 col-md-3 col-12">
	                       			<div class="checksBorder input-group-inline">
			                        	<div class="custom-control custom-radio mr-4">
			                              	<aui:input cssClass="form-check-input" label="planigualdadevaluacion.evaluation.intermediate" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE %>" value="0" type="radio">
			                              		<aui:validator name="required" />
			                              	</aui:input>
			                          	</div>
			                      		<div class="custom-control custom-radio mr-4">
			                               	<aui:input cssClass="form-check-input" label="planigualdadevaluacion.evaluation.final" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE %>" value="1" type="radio">
			                               		<aui:validator name="required" />
			                               	</aui:input>
			                       		</div>
			                     	</div>
	                       		</div>
	                    	</div>
	                    	<div class="row mb-4">
	                       		<div class="col-lg-6 col-md-6 col-12 required">
	                       			<legend><liferay-ui:message key="planigualdadevaluacion.general-data-date-from"/></legend>
	                       			<div class="col-md-6 col-12 pl-0">
									 	<liferay-ui:input-date  cssClass="filter-item datepicker" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM %>" required="true" nullable="true" showDisableCheckbox="false"/>					 	
									</div>
	                       		</div>
	                       		<div class="col-lg-6 col-md-6 col-12 required">
	                       			<legend><liferay-ui:message key="planigualdadevaluacion.general-data-date-until"/></legend>
	                       			<div class="col-md-6 col-12 pl-0">
									 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL %>" required="true" nullable="true" showDisableCheckbox="false" />
									</div>
	                       		</div>
	                    	</div>
	                    	<div class="row mb-4">
			                    <div class="form-group col-md-12 col-12 required">
			                    	<aui:input type="textarea" label="planigualdadevaluacion.general-data-textarea" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TEXTAREA %>">
			                    		<aui:validator name="required" />
			                    		<aui:validator name="maxLength">3000</aui:validator>
			                    	</aui:input>
			                    </div>
	                    	</div>
	                    	<div class="row mb-4">
	                    		<div class="col-lg-4 col-md-4 col-12 required">
	                       			<legend><liferay-ui:message key="planigualdadevaluacion.general-data-radio"/></legend>
	                       		</div>
	                       		<div class="col-lg-3 col-md-3 col-12">
	                       			<div class="checksBorder input-group-inline">
			                        	<div class="custom-control custom-radio mr-4">
			                              	<aui:input label="planigualdadevaluacion.evaluation.yes" cssClass="form-check-input" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO %>" value="0" type="radio">
			                              		<aui:validator name="required" />
			                              	</aui:input>
			                          	</div>
			                      		<div class="custom-control custom-radio mr-4">
			                               	<aui:input label="planigualdadevaluacion.evaluation.no" cssClass="form-check-input" name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO %>" value="1" type="radio">
			                               		<aui:validator name="required" />
			                               	</aui:input>
			                       		</div>
			                     	</div>
	                       		</div>
	                    	</div>
						</fieldset>
						
						<%--EVALUACION --%>
						<legend class="fieldset-title-section"><liferay-ui:message key="planigualdadevaluacion.evaluation.title"/></legend>
	            		<%-- INFORMACION DE RESULTADOS --%>
	            		<legend class="fieldset-subtitle-section ml-2"><liferay-ui:message key="planigualdadevaluacion.evaluation.result.title"/></legend>
	            		
	            		<fieldset class="mt-3 mb-2 ml-5">
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-1" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_1 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-2" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_2 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-3" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_3 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-4" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_4 %>" />
	            			</liferay-util:include>

	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-5" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_5 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-6" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_6 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.result.radio-7" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_7 %>" />
	            			</liferay-util:include>
	            			
	            			<div class="row mb-4">
			                    <div class="form-group col-md-12 col-12 required">
			                    	<aui:input type="textarea" label="planigualdadevaluacion.evaluation.result.textarea" name="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_TEXTAREA %>">
			                    		<aui:validator name="required" />
			                    		<aui:validator name="maxLength">3000</aui:validator>
			                    	</aui:input>
			                    </div>
	                    	</div>
	            		</fieldset>
	            		
	            		<%-- INFORMACION DE LA IMPLANTACION --%>
	            		<legend class="fieldset-subtitle-section ml-2"><liferay-ui:message key="planigualdadevaluacion.evaluation.implantation.title"/></legend>
	            		
	            		<fieldset class="mt-3 mb-2 ml-5">
	            		
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.implantation.radio-1" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_1 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.implantation.radio-2" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_2 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.implantation.radio-3" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_3 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.implantation.radio-4" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_4 %>" />
	            			</liferay-util:include>
	            			
	            			<div class="row mb-4">
			                    <div class="form-group col-md-12 col-12 required">
			                    	<aui:input type="textarea" label="planigualdadevaluacion.evaluation.implantation.textarea" name="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_TEXTAREA %>" >
			                    		<aui:validator name="required" />
			                    		<aui:validator name="maxLength">3000</aui:validator>
			                    	</aui:input>
			                    </div>
	                    	</div>
	                    	
	            		</fieldset>
	            		
	            		<%-- INFORMACION DEL IMPACTO --%>
	            		<legend class="fieldset-subtitle-section ml-2"><liferay-ui:message key="planigualdadevaluacion.evaluation.impact.title"/></legend>
	            		
	            		<fieldset class="mt-3 mb-2 ml-5">
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.impact.radio-1" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_1 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.impact.radio-2" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_2 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.impact.radio-3" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_3 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.impact.radio-4" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_4 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.impact.radio-5" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_5 %>" />
	            			</liferay-util:include>
	            			
	            			<liferay-util:include page="/formComponents/formRadioButtonsEvaluationGeneric.jsp" servletContext="<%=application %>">
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_TEXT_TITLE %>" value="planigualdadevaluacion.evaluation.impact.radio-6" />
	            				<liferay-util:param name="<%=PlanIgualdadEvaluacionPortletKeys.GENERAL_NAME_INPUT %>" value="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_6 %>" />
	            			</liferay-util:include>
	            			
	            			<div class="row mb-4">
			                    <div class="form-group col-md-12 col-12 required">
			                    	<aui:input label="planigualdadevaluacion.evaluation.impact.textarea" name="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_TEXTAREA %>" type="textarea">
			                    		<aui:validator name="required" />
			                    		<aui:validator name="maxLength">3000</aui:validator>
			                    	</aui:input>
			                    </div>
	                    	</div>
	            		</fieldset>
	            		
	            		<%-- CONCLUSIONES Y PROPUESTAS --%>
	            		<legend class="fieldset-subtitle-section ml-2"><liferay-ui:message key="planigualdadevaluacion.evaluation.conclusions-and-proposals"/></legend>
	            		<fieldset class="mt-3 mb-2 ml-5">
		            		<div class="form-group required">
		                    	<aui:input type="textarea" label=" " value="<%=evaluacion!=null ? evaluacion.getConclusions() : ' '%>" name="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_CONCLUSIONS %>" >
		                    		<aui:validator name="required" />
		                    		<aui:validator name="maxLength">3000</aui:validator>
		                    	</aui:input>
		                    </div>
	                    </fieldset>
	                    
	                    <%-- OBSERVACIONES --%>
	            		<legend class="fieldset-subtitle-section ml-2"><liferay-ui:message key="planigualdadevaluacion.evaluation.observations"/></legend>
	            		<fieldset class="mt-3 mb-2 ml-5">
		            		<div class="form-group">
		                    	<aui:input type="textarea" label=" " value="<%=evaluacion!=null ? evaluacion.getObservations() : ' '%>"  name="<%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_OBSERVATIONS %>">
		                    		<aui:validator name="maxLength">3000</aui:validator>
		                    	</aui:input>
		                    </div>
		              	</fieldset>
					</div>
				</div>
			</div>       
		</div>
	</div>
	<div class="button-holder d-flex justify-content-center my-4">
		<aui:button-row>
			<portlet:renderURL var="cancelURL">
                <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
                <portlet:param name="<%=PlanIgualdadEvaluacionPortletKeys.QUERY_PARAM %>" value="<%=compId %>" />
            </portlet:renderURL>
            <aui:button onClick="<%= cancelURL.toString() %>" value="planigualdadevaluacion.evaluation.back" cssClass="btn btn-outline-primary btn-sm mr-4" primary="true"></aui:button>
			<%if(!hiddenSave){ %>
				<aui:button type="submit" cssClass="btn btn-primary btn-sm" value="planigualdadevaluacion.evaluation.save" />
			<%} %>
		</aui:button-row>
	</div>
</aui:form>

<script>

$(document).ready( function () {
	$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM%>').val('');
	$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL%>').val('');
	YUI().use('aui-datepicker', function(Y) {
	    new Y.DatePicker({
	        trigger: '.datepicker',
	        mask: '%d/%m/%Y',
	        popover : {
	            zIndex : 1
	        }
	    });
	});
	
	<%if(evaluacion != null && hiddenSave){%>
		$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM%>').val('<%=evaluacion.getDatosGenerales().getGeneralDataDateFrom()%>');
		$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL%>').val('<%=evaluacion.getDatosGenerales().getGeneralDataDateUntil()%>');
		$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TEXTAREA%>').val('<%=evaluacion.getDatosGenerales().getGeneralDataTextarea()%>');

		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE%>][value='<%=evaluacion.getDatosGenerales().getGeneralDataType()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO%>][value='<%=evaluacion.getDatosGenerales().getGeneralDataRadio()%>']").prop("checked",true);

		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_1%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio1()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_2%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio2()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_3%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio3()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_4%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio4()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_5%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio5()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_6%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio6()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_7%>][value='<%=evaluacion.getInformacionResultados().getEvaluationResultRadio7()%>']").prop("checked",true);
		$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_TEXTAREA%>').val('<%=evaluacion.getInformacionResultados().getEvaluationResultTextarea()%>');

		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_1%>][value='<%=evaluacion.getInformacionImplantacion().getEvaluationImplantationRadio1()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_2%>][value='<%=evaluacion.getInformacionImplantacion().getEvaluationImplantationRadio2()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_3%>][value='<%=evaluacion.getInformacionImplantacion().getEvaluationImplantationRadio3()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_4%>][value='<%=evaluacion.getInformacionImplantacion().getEvaluationImplantationRadio4()%>']").prop("checked",true);
		$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_TEXTAREA%>').val('<%=evaluacion.getInformacionImplantacion().getEvaluationImplantationTextarea()%>');
		
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_1%>][value='<%=evaluacion.getInformacionImpacto().getEvaluationImpactRadio1()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_2%>][value='<%=evaluacion.getInformacionImpacto().getEvaluationImpactRadio2()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_3%>][value='<%=evaluacion.getInformacionImpacto().getEvaluationImpactRadio3()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_4%>][value='<%=evaluacion.getInformacionImpacto().getEvaluationImpactRadio4()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_5%>][value='<%=evaluacion.getInformacionImpacto().getEvaluationImpactRadio5()%>']").prop("checked",true);
		$("input[name=<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_6%>][value='<%=evaluacion.getInformacionImpacto().getEvaluationImpactRadio6()%>']").prop("checked",true);
		$('#<portlet:namespace/><%=PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_TEXTAREA%>').val('<%=evaluacion.getInformacionImpacto().getEvaluationImpactTextarea()%>');
		
		$(".formulario textarea, .formulario input").each(function(){
			$(this).attr('disabled', true);
		});
	<%
	}
	%>
});
</script>