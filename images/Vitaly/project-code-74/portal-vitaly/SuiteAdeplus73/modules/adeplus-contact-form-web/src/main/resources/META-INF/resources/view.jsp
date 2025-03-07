<%@ include file="init.jsp" %>
<%@ include file="js/main-js.jsp" %>
<liferay-portlet:actionURL name="send" var="sendURL"  />
<aui:form action="<%= sendURL.toString() %>" method="post">
	<div class="content">
		<div class="titulo-seccion">
			<h2><liferay-ui:message key="contact.form.view.title"/></h2>
		</div>

		<c:if test="${not empty userAdminList}">
			<div>
				<p><liferay-ui:message key="contact.form.view.message.user"/></p>
				<ul class="m-4">
					<c:forEach var="uc" items="${userAdminList}" >
						<li>${uc.name} ${uc.lastName} - <a href="mailto:${uc.email}">${uc.email}</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<c:if test="${empty userAdminList}">
			<div class="formulario">
				<div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
					<div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">
						<p><liferay-ui:message key="contact.form.view.message"/></p>
						<div class="row">
							<div class="form-group col-md-12 required">
								<aui:select name="category" label="contact.form.view.categories">
									<c:forEach var="category" items="${categories}" varStatus="categoriesLoop">
										<aui:option value="${categoriesLoop.index}">
											${category}
										</aui:option>
									</c:forEach>
								</aui:select>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12 required">
								<aui:select name="application" label="contact.form.view.application">
									<c:forEach var="application" items="${applications}" >
										<aui:option value="${application.applicationId}">
											${application.name}
										</aui:option>
									</c:forEach>
								</aui:select>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12 required">
								<aui:input name="subject" type="text" label="contact.form.view.subject" >
									<aui:validator name="required" />
								</aui:input>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12 required">
								<aui:input name="description" type="textarea" label="contact.form.view.description" >
									<aui:validator name="required" />
								</aui:input>
							</div>
						</div>

						<div class="row">
							<div class="col-md-11 pr-0" onclick="addFileSelect()">
								<fieldset class="input-group">
									<div class="form-group input-text-wrapper">
										<aui:input name="file" label="contact.form.view.file" type="file">
											<aui:validator name="acceptFiles">'jpg,png,pdf,txt,xlsx,docx'</aui:validator>
										</aui:input>
										<aui:input name="selectedFile" id="selectedFile" type="hidden" value="false">
										</aui:input>
									</div>
								</fieldset>
							</div>
							<div id="deleteAddFile" class="form-group p-0 d-flex align-items-start">
								<span class="btn-ico delete-ico margin-top-delete" onclick="deleteFileSelect()"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>
	<c:if test="${empty userAdminList}">
		<div class="button-holder d-flex justify-content-center my-4">
			<aui:button-row>
				<aui:button type="submit" value="contact.form.view.send" />
			</aui:button-row>
		</div>
	</c:if>
</aui:form>
