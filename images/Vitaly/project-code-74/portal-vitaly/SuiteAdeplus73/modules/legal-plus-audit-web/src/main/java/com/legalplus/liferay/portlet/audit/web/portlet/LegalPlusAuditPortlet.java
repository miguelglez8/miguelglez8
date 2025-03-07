package com.legalplus.liferay.portlet.audit.web.portlet;

import com.adeplus.liferay.portlet.suite.manager.model.Audit;
import com.adeplus.liferay.portlet.suite.manager.service.AuditLocalServiceUtil;
import com.legalplus.liferay.portlet.audit.web.constants.LegalPlusAuditPortletKeys;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author abarrero
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.legalplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Auditoría Legal+",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LegalPlusAuditPortletKeys.LEGALPLUSAUDIT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class LegalPlusAuditPortlet extends MVCPortlet {

}