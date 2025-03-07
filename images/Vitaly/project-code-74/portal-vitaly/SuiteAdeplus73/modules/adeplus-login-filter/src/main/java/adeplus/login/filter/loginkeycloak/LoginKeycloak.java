package adeplus.login.filter.loginkeycloak;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectProviderRegistry;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectServiceHandler;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@Component(immediate = true, 
property = { 
	"servlet-context-name=", 
	"servlet-filter-name=Filter login keycloak",
	"url-pattern=/c/portal/login" }, 
service = Filter.class)

public class LoginKeycloak implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) {}

	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		try {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;

			Collection<String> openIdConnectProviderNames = _openIdConnectProviderRegistry.getOpenIdConnectProviderNames(_portal.getCompany(request).getCompanyId());
			//Collection<String> openIdConnectProviderNames = _openIdConnectProviderRegistry.getOpenIdConnectProviderNames();
			
	            if (openIdConnectProviderNames == null || openIdConnectProviderNames.size() == 0) {
	                filterChain.doFilter(servletRequest, servletResponse);
	                return;
	            }
	            
	            String openIdConnectProviderName = openIdConnectProviderNames.iterator().next();
	            _openIdConnectServiceHandler.requestAuthentication(openIdConnectProviderName, request, response);
	            
		
		} catch (Exception e) {
			_log.error(e, e);
		} finally {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}
	
	@Reference    
	private OpenIdConnectProviderRegistry _openIdConnectProviderRegistry;
    @Reference    
    private OpenIdConnectServiceHandler _openIdConnectServiceHandler;
    @Reference
    private Portal _portal;

    private static final Log _log = LogFactoryUtil.getLog(LoginKeycloak.class);
	
}
