<%@ page import="com.legalplus.liferay.portlet.admin.categorias.web.constants.AdminCategoriasPortletKeys" %>
<%@ page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ include file="/init.jsp" %>

<%
    long categoryEditId = ParamUtil.getLong(request, AdminCategoriasPortletKeys.CATEGORY_ID, 0);

    AssetCategory categoryEdit = null;
    if(categoryEditId > 0){
        categoryEdit = AssetCategoryLocalServiceUtil.getCategory(categoryEditId);
    }

%>

<portlet:renderURL var="cancelURL">
    <portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>
<liferay-portlet:actionURL name="/category/save" var="saveURL" />

<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=AdminCategoriasPortletKeys.CATEGORY_ID%>" value="<%=categoryEditId%>" type="hidden" />

     <div class="content">
            <div class="formulario">
                
                <div class="title-group mt-3">
                	<a href="<%= cancelURL.toString() %>" class="toBackView">
                		<i class="icon-arrow-left"></i><liferay-ui:message key="categoria.edit.backTitle"/>
                	</a>
                    <h2> 
                    <c:if test="<%=Validator.isNotNull(categoryEdit)%>">
    					<liferay-ui:message key="categoria.edit.editTitle"/>
    				</c:if>
    				<c:if test="<%=Validator.isNull(categoryEdit)%>">
    					<liferay-ui:message key="categoria.edit.newTitle"/>
    				</c:if>
                    </h2>
                </div>

                <div class="d-flex flex-nowrap flex-sm-column flex-lg-row">
                    <div class="form-content form-datos mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

                        <div class="row">
                            <div class="col-md-12 prv-lang-btn">
                                <label class="control-label disabled" for="<%=AdminCategoriasPortletKeys.CATEGORY_TITLE%>">
                                    <liferay-ui:message key="categories.view.name" />
                                </label>
                                <liferay-ui:input-localized
                                    id="<%=AdminCategoriasPortletKeys.CATEGORY_TITLE%>"
                                    name="<%=AdminCategoriasPortletKeys.CATEGORY_TITLE%>"
                                    xml="<%=Validator.isNotNull(categoryEdit) ? categoryEdit.getTitle() : ""%>"
                                    maxLength="500">
                                </liferay-ui:input-localized>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 prv-lang-btn">
                                <label class="control-label disabled" for="<%=AdminCategoriasPortletKeys.CATEGORY_DESCRIPTION%>">
                                    <liferay-ui:message key="categories.view.description" />
                                </label>
                                <liferay-ui:input-localized
                                    id="<%=AdminCategoriasPortletKeys.CATEGORY_DESCRIPTION%>"
                                    name="<%=AdminCategoriasPortletKeys.CATEGORY_DESCRIPTION%>"
                                    xml="<%=Validator.isNotNull(categoryEdit) ? categoryEdit.getDescription() : ""%>"
                                    type="textarea" maxLength="500">
                                </liferay-ui:input-localized>
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
     </div>
</aui:form>