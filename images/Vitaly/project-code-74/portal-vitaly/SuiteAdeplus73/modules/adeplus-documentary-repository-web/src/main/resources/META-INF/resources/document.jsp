<%@page import="com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys"%>
<%@ include file="init.jsp" %>

<liferay-portlet:actionURL name="/documentaryRepository/uploadDocument" var="uploadDocumentURL"  />

<%
String compId = (String) request.getParameter(AdeplusDocumentaryRepositoryPortletKeys.COMPIDDOCUMENTARY_PARAM);

%>
<aui:form action="<%= uploadDocumentURL.toString() %>" method="post" enctype="multipart/form-data">
	<aui:input name="<%= AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM %>" value="<%= compId %>" type="hidden" />
	
	<div class="content">
		<div class="titulo-seccion text-center">
			<h2><liferay-ui:message key="document.view.document.new-document"/></h2>
		</div>
		<div class="formulario">
			<div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
				<div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
					<div class="row">
						<div class="form-group col-md-12 required">
							<aui:input name="<%=AdeplusDocumentaryRepositoryPortletKeys.NAME %>" type="text" label="document.view.document.name" >
								<aui:validator name="required" />
							</aui:input>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<aui:input name="<%=AdeplusDocumentaryRepositoryPortletKeys.OBSERVATIONS %>" type="textarea" label="document.view.document.observations" >
							</aui:input>
						</div>
					</div>
					<div class="row">
						<div class="col-md-11 pr-0" onclick="addFileSelect()">
							<fieldset class="input-group">
								<div class="form-group input-text-wrapper required">
									<aui:input name="<%=AdeplusDocumentaryRepositoryPortletKeys.UPLOAD_FILE %>" label="document.view.document.select-file" type="file">
										<aui:validator name="acceptFiles">'jpg,png,pdf,txt,xlsx,docx,xlsm'</aui:validator>
										<aui:validator name="required" />
									</aui:input>
									<aui:input name="selectedFile" id="selectedFile" type="hidden" value="false">
									</aui:input>
								</div>
							</fieldset>
						</div>
						<div id="deleteAddFile" class="form-group p-0 d-flex align-items-start">
							<span class="btn-ico delete-ico margin-top-delete" style="margin-top:25px;" onclick="deleteFileSelect()"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="button-holder d-flex justify-content-center my-4">
		<aui:button-row>
			<portlet:actionURL name="cancel" var="cancelURL">
                <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
            </portlet:actionURL>
            <aui:button onClick="<%= cancelURL.toString() %>" value="document.view.document.cancel" cssClass="btn btn-outline-primary btn-sm mr-4" primary="true"></aui:button>
			<aui:button type="submit" cssClass="btn btn-primary btn-sm" value="document.view.document.save" />
		</aui:button-row>
	</div>
</aui:form>

<script>
	function addFileSelect(){
	    var result = new Array();
	    var inputs = document.getElementsByTagName("input");
	    for(var i=0; i < inputs.length; i++) {
	        if(inputs[i].id.endsWith('selectedFile'))
	        	document.getElementById(inputs[i].id).value = "true";        
	    }
	}
	
	function deleteFileSelect(){
		 var result = new Array();
		 var inputs = document.getElementsByTagName("input");
		 for(var i=0; i < inputs.length; i++) {
		     if(inputs[i].id.endsWith('selectedFile'))
		        document.getElementById(inputs[i].id).value = "false";
		     if(inputs[i].id.endsWith('<%=AdeplusDocumentaryRepositoryPortletKeys.UPLOAD_FILE%>'))
		        	document.getElementById(inputs[i].id).value = "";
		 }
	}
</script>