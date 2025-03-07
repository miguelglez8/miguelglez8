<%@ page import="com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys" %>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="cancelURL">
    <portlet:param name="mvcRenderCommandName" value="/"></portlet:param>
</portlet:renderURL>
<liferay-portlet:actionURL name="/consultor/save" var="saveURL" />

<aui:form  name="edit_form" action="<%= saveURL.toString() %>" method="post">

     <div class="content">
            <div class="formulario">

                <div class="title-group mt-3">
                	<a href="<%= cancelURL.toString() %>" class="toBackView">
                		<i class="icon-arrow-left"></i>                	
                		<liferay-ui:message key="user.edit.backTitle"/>
                	</a>
                    <h2><liferay-ui:message key="user.edit.newTitle"/></h2>
                </div>

                <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                    <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

                        <div class="row">
                            <div class="form-group col-md-6 required">
                                <aui:select name="<%=AdminConsultoresPortletKeys.CONSULTOR_ID%>" label="user.edit.title">
                                    <aui:option value=""></aui:option>
                                    <c:forEach items="${userList}" var="curUser">
                                        <aui:option value="${curUser.userId}">${curUser.name} ${curUser.lastName}</aui:option>
                                    </c:forEach>
                                    <aui:validator name="required"/>
                                </aui:select>
                            </div>
                        </div>

                        <div class="row">
        					<div class="col-12">
        						<div class="button-holder d-flex justify-content-end my-4">
                    				<aui:button-row>
                        				<aui:button onClick="<%= cancelURL.toString() %>" value="back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>
                        				<aui:button type="submit" cssClass="btn btn-primary"/>
                    				</aui:button-row>
                				</div>
        					</div>
        				</div>

                    </div>
                </div>

            </div>
        </div>
     
</aui:form>