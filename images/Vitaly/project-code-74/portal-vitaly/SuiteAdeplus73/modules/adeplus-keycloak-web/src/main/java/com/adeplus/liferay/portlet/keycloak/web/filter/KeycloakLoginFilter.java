package com.adeplus.liferay.portlet.keycloak.web.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectProviderRegistry;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectServiceException;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectServiceHandler;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectSession;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component(
        immediate = true,
        property = {
                "servlet-context-name=",
                "servlet-filter-name=Keycloak Login Filter",
                "url-pattern=/c/portal/login"},
        service = Filter.class)

public class KeycloakLoginFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, javax.servlet.FilterChain chain) throws IOException, ServletException {

        OpenIdConnectSession c = null;

        /*
        HttpSession session = PortalUtil.getHttpServletRequest((PortletRequest) request).getSession();
        try {
            if(openIdConnectServiceHandler.hasValidOpenIdConnectSession(session)){
                //correcto
            }
            openIdConnectServiceHandler.processAuthenticationResponse();

        } catch (OpenIdConnectServiceException.NoOpenIdConnectSessionException e) {
            e.printStackTrace();
        }
        */
        /*try {

            //Get OpenId Providers
            Collection openIdConnectProviderNames = openIdConnectProviderRegistry.getOpenIdConnectProviderNames(PortalUtil.getDefaultCompanyId());

            if (openIdConnectProviderNames == null || openIdConnectProviderNames.isEmpty()) {
                chain.doFilter(request, response);
                return;
            }
            // Get first OpenID Provider
            String openIdConnectProviderName = openIdConnectProviderNames.iterator().next().toString();

            // Request Provider's authentication
            openIdConnectServiceHandler.requestAuthentication(openIdConnectProviderName, (HttpServletRequest)request, (HttpServletResponse)response);

        } catch (Exception e) {
            _log.error(e, e);
        } finally {
            chain.doFilter(request, response);
        }*/

    }

    @Override
    public void destroy() {
    }

    @Reference
    private OpenIdConnectProviderRegistry openIdConnectProviderRegistry;

    @Reference
    private OpenIdConnectServiceHandler openIdConnectServiceHandler;

    private static final Log _log = LogFactoryUtil.getLog(KeycloakLoginFilter.class);

}
