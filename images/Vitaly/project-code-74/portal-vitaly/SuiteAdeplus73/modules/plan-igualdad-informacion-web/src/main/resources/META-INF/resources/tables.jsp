<%@ taglib prefix="plan" uri="https://adeplusconsultores.com/tld/adeplus" %>

<fieldset class="border p-2 mb-4">
    <legend  class="w-auto"><liferay-ui:message key="organizacion.view.comision" /></legend>
    <div class="mb-4">
        <plan:datatable
                id="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_COMISION %>"
                form="organizacion_form"
                value="<%= Validator.isNotNull(organizacion) ? organizacion.getReprComisionOrganizacion() : "{}" %>"
                title="organizacion.comision.organizacion"
                tooltip="organizacion.comision.organizacion.negociadora.info"
                editTitle="organizacion.comision.organizacion"
                deleteTitle="organizacion.comision.organizacion"
        />
    </div>
    <div class="mb-4">
        <plan:datatable
                id="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_SOCIAL %>"
                form="organizacion_form"
                value="<%= Validator.isNotNull(organizacion) ? organizacion.getReprComisionSocial() : "{}" %>"
                title="organizacion.comision.social"
                tooltip="organizacion.comision.social.negociadora.info"
                editTitle="organizacion.comision.social"
                deleteTitle="organizacion.comision.social"
        />
    </div>
</fieldset>
<fieldset class="border p-2 mb-4">
    <legend  class="w-auto"><liferay-ui:message key="organizacion.view.seguimiento" /></legend>
    <div class="mb-4">
        <plan:datatable
                id="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_PLAN %>"
                form="organizacion_form"
                value="<%= Validator.isNotNull(organizacion) ? organizacion.getReprPlanOrganizacion() : "{}" %>"
                title="organizacion.comision.organizacion"
                tooltip="organizacion.comision.organizacion.seguimiento.info"
                editTitle="organizacion.comision.organizacion"
                deleteTitle="organizacion.comision.organizacion"
        />
    </div>
    <div class="mb-4">
        <plan:datatable
                id="<%= PlanIgualdadInformacionWebPortletKeys.ORGANIZACION_REPRESENTACION_SOCIALES %>"
                form="organizacion_form"
                value="<%= Validator.isNotNull(organizacion) ? organizacion.getReprPlanSociales() : "{}" %>"
                title="organizacion.comision.social"
                tooltip="organizacion.comision.social.seguimiento.info"
                editTitle="organizacion.comision.social"
                deleteTitle="organizacion.comision.social"
        />
    </div>
</fieldset>