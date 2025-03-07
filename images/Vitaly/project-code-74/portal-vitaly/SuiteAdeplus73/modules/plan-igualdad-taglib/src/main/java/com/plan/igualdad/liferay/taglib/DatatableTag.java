package com.plan.igualdad.liferay.taglib;

import com.liferay.taglib.util.IncludeTag;
import com.plan.igualdad.liferay.taglib.ServletContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class DatatableTag extends IncludeTag {

    @Override
    public void setPageContext(PageContext pageContext) {
        super.setPageContext(pageContext);

        setServletContext(ServletContextUtil.getServletContext());
    }

    public void setId(String id) {
        _id = id;
    }

    public void setValue(String value) {_value = value;}

    public void setForm(String form) {_form = form;}

    public void setTitle(String title) {
        _title = title;
    }

    public void setTooltip(String tooltip) {
        _tooltip = tooltip;
    }

    public void setEditTitle(String editTitle) {
        _editTitle = editTitle;
    }

    public void setDeleteTitle(String deleteTitle) {
        _deleteTitle = deleteTitle;
    }

    @Override
    protected void cleanUp() {
        super.cleanUp();

        _id = null;
        _value = null;
        _form = null;
        _title = null;
        _tooltip = null;
        _editTitle = null;
        _deleteTitle = null;
    }

    @Override
    protected String getPage() {
        return _PAGE;
    }

    @Override
    protected void setAttributes(HttpServletRequest request) {
        request.setAttribute("adeplus-datatable:datatable:id", _id);
        request.setAttribute("adeplus-datatable:datatable:value", _value);
        request.setAttribute("adeplus-datatable:datatable:form", _form);
        request.setAttribute("adeplus-datatable:datatable:title", _title);
        request.setAttribute("adeplus-datatable:datatable:tooltip", _tooltip);
        request.setAttribute("adeplus-datatable:datatable:editTitle", _editTitle);
        request.setAttribute("adeplus-datatable:datatable:deleteTitle", _deleteTitle);
    }

    private static final String _PAGE = "/datatable.jsp";

    private String _id;
    private String _value;
    private String _form;
    private String _title;
    private String _tooltip;
    private String _editTitle;
    private String _deleteTitle;

}