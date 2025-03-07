<%@ page import="com.legalplus.liferay.portlet.contact.form.web.bean.Field" %>
<%@ page import="java.util.List" %>

<%@ include file="/init.jsp" %>

<liferay-ui:success key="contacto-save-success" message="contact.form.success" />

<div class="content">
    <div class="formulario">
        <div class="form-content prv-search prv-form mr-sm-0 mr-md-3 mb-md-3 mb-lg-0 ">

            <h2 class="mb-3">Contacto</h2>

            <liferay-portlet:actionURL name="/contacto/save" var="contactoURL" />
            <aui:form  name="contract_form" action="<%=contactoURL%>" method="post">

                <c:forEach items="${fields}" var="field">

                    <%-- SELECT --%>
                    <c:if test="${field.type eq 'select'}">
                        <div class="row align-items-start mb-4">
                            <div class="col-lg-6 col-md-6 col-12">
                                <fieldset class="input-group-inline ${field.required ? 'required' : ''}">
                                    <div class="form-group input-select-wrapper">
                                        <aui:select name="${field.name}" label="${field.label}" required="${field.required ? 'true' : 'false'}">
                                             <aui:option value=""></aui:option>
                                             <c:forEach items="${field.values}" var="value">
                                                <aui:option selected="${value eq consulta}" value="${value}">${value}</aui:option>
                                             </c:forEach>
                                        </aui:select>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </c:if>

                    <%-- TEXT --%>
                    <c:if test="${field.type eq 'text'}">
                        <div class="row align-items-start mb-4">
                            <div class="form-group col-md-6 ${field.required ? 'required' : ''}">
                                <aui:input name="${field.name}" type="text" label="${field.label}" value="">
                                    <c:if test="${field.required}">
                                        <aui:validator name="required" errorMessage="contacto.validator.required" />
                                    </c:if>
                                </aui:input>
                            </div>
                        </div>
                    </c:if>

                    <%-- TEXTAREA --%>
                    <c:if test="${field.type eq 'textarea'}">
                        <div class="row align-items-start mb-4">
                            <div class="form-group col-md-6 col-12 ${field.required ? 'required' : ''}">
                                <div class="form-group input-text-wrapper">
                                    <aui:input name="${field.name}" type="textarea" label="${field.label}" value="">
                                        <c:if test="${field.required}">
                                            <aui:validator name="required" errorMessage="contacto.validator.required" />
                                        </c:if>
                                    </aui:input>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <%-- FILE --%>
                    <c:if test="${field.type eq 'file'}">
                        <div class="row align-items-start mb-4">
                            <div class="form-group col-md-6 col-12 ${field.required ? 'required' : ''}">
                                <div class="prv-file-container">
                                    <aui:input name="${field.name}" label="${field.label}" type="file" >
                                        <c:if test="${field.required}">
                                            <aui:validator name="required" errorMessage="contacto.validator.required" />
                                        </c:if>
                                    </aui:input>

                                    <div class="prv-input-file__span">
                                        <span class="prv-input-file__btn" id="prv-input-file_${field.name}">Examinar...</span>
                                        <span class="prv-input-file__txt" id="prv-input-file_txt_${field.name}"></span>
                                        <span class="btn-ico delete-ico margin-top-delete delete-file" data-file="${field.name}"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                </c:forEach>

                <div class="row">
                    <div class="col-12">
                        <div class="button-holder d-flex justify-content-end my-4">
                            <aui:button-row>
                                <aui:button type="submit" cssClass="btn btn-primary"/>
                            </aui:button-row>
                        </div>
                    </div>
                </div>

            </aui:form>

        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        <c:forEach items="${fields}" var="field">
            <c:if test="${field.type eq 'file'}">

                $("#prv-input-file_${field.name}, #prv-input-file_txt_${field.name}").on("click", function(e){
                    $('#<portlet:namespace/>${field.name}').click();
                });

                $('#<portlet:namespace/>${field.name}').on("change", function(e){
                    var fileName = $(this).val().split('\\').pop();
                    $("#prv-input-file_txt_${field.name}").html("");
                    $("#prv-input-file_txt_${field.name}").html(fileName);
                });
            </c:if>
        </c:forEach>

        $(".delete-file").on("click", function(e) {
            var name = $(this).data("file");
            $("#prv-input-file_txt_" + name).html("");
            $("#<portlet:namespace/>" + name).val("");
        });
    });
</script>