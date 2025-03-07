package com.plan.igualdad.liferay.taglib;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true)
public class ServletContextUtil {

    public static final ServletContext getServletContext() {
        return _instance._getServletContext();
    }

    @Activate
    protected void activate() {
        _instance = this;
    }

    @Deactivate
    protected void deactivate() {
        _instance = null;
    }

    @Reference(
            target = "(osgi.web.symbolicname=com.plan.igualdad.liferay.taglib)",
            unbind = "-"
    )
    protected void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    private ServletContext _getServletContext() {
        return _servletContext;
    }

    private static ServletContextUtil _instance;

    private ServletContext _servletContext;

}
