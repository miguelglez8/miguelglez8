package com.adeplus.liferay.portlet.keycloak.web.filter;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectProviderRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;

@Component(
        immediate = true,
        property = "key=logout.events.post",
        service = LifecycleAction.class
)
public class KeycloakLogoutPostAction implements LifecycleAction {
    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
  /*
         try {
          System.out.println("KeycloakLogoutPostAction");

            HttpServletRequest request = lifecycleEvent.getRequest();
            HttpServletResponse response = lifecycleEvent.getResponse();
            Collection<String> openIdConnectProviderNames =
                    openIdConnectProviderRegistry.getOpenIdConnectProviderNames(PortalUtil.getDefaultCompanyId());
            if (openIdConnectProviderNames == null || openIdConnectProviderNames.isEmpty()) {
                _log.warn("No OpenID Connect Providers found.");
                return;
            }

            String openIdConnectProviderName = openIdConnectProviderNames.iterator().next();
            OpenIdConnectProvider openIdConnectProvider =
                    openIdConnectProviderRegistry.getOpenIdConnectProvider(PortalUtil.getDefaultCompanyId(), openIdConnectProviderName);

            Object oidcProviderMetadata = openIdConnectProvider.getOIDCProviderMetadata();
            String oidcJson = oidcProviderMetadata.toString();
            JSONObject oidcJsonObject = JSONFactoryUtil.createJSONObject(oidcJson);
            Object authEndpoint = oidcJsonObject.get("authorization_endpoint");
            String authEndpointUrl = authEndpoint.toString();
            String logoutEndpoint = StringUtil.replaceLast(authEndpointUrl, "/auth", "/logout");
            String redirectUri = getRedirectUrl(request);
            String logoutUrl = logoutEndpoint + "?redirect_uri=" + redirectUri;
            response.sendRedirect(logoutUrl);

            System.out.println("logoutUrl " + logoutUrl);

        } catch (Exception e) {
            _log.error("Error in KeycloakLogoutPostAction: " + e.getMessage(), e);
        }*/
    }

    private String getRedirectUrl(HttpServletRequest request) {
/*        String portalURL = portal.getPortalURL(request);
        long companyId = portal.getCompanyId(request);
        PortletPreferences preferences = prefsProps.getPreferences(companyId);
        String logoutPath = prefsProps.getString(preferences, PropsKeys.DEFAULT_LOGOUT_PAGE_PATH);
        return portalURL + logoutPath;*/
        return "";
    }

    @Reference
    private Portal portal;
    @Reference
    private PrefsProps prefsProps;
    @Reference
    private OpenIdConnectProviderRegistry openIdConnectProviderRegistry;
    private static final Log _log = LogFactoryUtil.getLog(KeycloakLogoutPostAction.class);
}
