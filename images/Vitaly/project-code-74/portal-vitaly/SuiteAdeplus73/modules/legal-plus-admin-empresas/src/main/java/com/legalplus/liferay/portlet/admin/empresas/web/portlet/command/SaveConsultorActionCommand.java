package com.legalplus.liferay.portlet.admin.empresas.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.ConsultorCompanyPK;
import com.legalplus.liferay.portlet.mailing.web.mail.ConsultorMailing;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Arrays;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminEmpresasPortletKeys.ADMINEMPRESAS,
                "mvc.command.name=/consultant/save"
        },
        service = MVCActionCommand.class
)
public class SaveConsultorActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveConsultorActionCommand.class);

    private static final String APP_LEGAL = "LEGAL_PLUS";

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        Long compId = ParamUtil.getLong(actionRequest, AdminEmpresasPortletKeys.COMPANY_ID, 0);
        Application application = ApplicationLocalServiceUtil.getApplicationByAlias(APP_LEGAL);
        List<String> userIds = Arrays.asList(ParamUtil.getParameterValues(actionRequest, AdminEmpresasPortletKeys.USER_ID));
        List<ConsultorCompany> consultores = ConsultorCompanyLocalServiceUtil.fetchByCompIdAndAppId(compId, application.getApplicationId());

        //Borrar consultores
        for (ConsultorCompany consultor : consultores) {
            ConsultorCompanyLocalServiceUtil.deleteConsultorCompany(consultor);
        }

        //Crear consultores
        for (String userId : userIds) {
            Long id = Long.valueOf(userId);
            User consultor = UserLocalServiceUtil.fetchUser(id);

            ConsultorCompanyPK consultorCompanyPK = new ConsultorCompanyPK();
            consultorCompanyPK.setAppId(application.getApplicationId());
            consultorCompanyPK.setUserId(id);
            consultorCompanyPK.setCompId(compId);

            ConsultorCompany consultorCompany =  ConsultorCompanyLocalServiceUtil.createConsultorCompany(consultorCompanyPK);
            consultorCompany.setName(consultor.getFirstName());
            consultorCompany.setLastName(consultor.getLastName());

            ConsultorCompanyLocalServiceUtil.updateConsultorCompany(consultorCompany);

            if (!consultores.stream().anyMatch(con -> con.getUserId() == id)) {
                Comp company = CompLocalServiceUtil.fetchComp(consultorCompany.getCompId());
                List<UserCompany> companyUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(company.getCompId());

                ConsultorMailing.asignarConsultor(
                        consultor.getEmailAddress(),
                        consultor,
                        company.getName(),
                        consultor.getFullName(),
                        consultor.getEmailAddress());

                for (UserCompany companyUser : companyUsers) {
                    User cliente = UserLocalServiceUtil.fetchUser(companyUser.getUserId());
                    ConsultorMailing.asignarConsultor(
                            cliente.getEmailAddress(),
                            cliente,
                            company.getName(),
                            consultor.getFullName(),
                            consultor.getEmailAddress());
                }
            }

        }

        SessionMessages.add(actionRequest, "consultant-save-success");

        return false;
    }

}
