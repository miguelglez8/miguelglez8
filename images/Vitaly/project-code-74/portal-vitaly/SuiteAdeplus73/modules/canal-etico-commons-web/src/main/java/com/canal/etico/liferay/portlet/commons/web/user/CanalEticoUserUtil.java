package com.canal.etico.liferay.portlet.commons.web.user;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.model.Role;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.*;

import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

public class CanalEticoUserUtil {


    public static UserCompany getUserDefaultCompany(long userId){

        UserCompany userCompany = null;
        long idEmpresa = 0; long idComp = 0;
        if(!CanalEticoCommonsPortletKeys.ITS_NEW_VERSION){
            userCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(userId);

        }else{

            userCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(userId);
            if(Validator.isNotNull(userCompany)){
                idEmpresa=userCompany.getDefaultEmpresaId();
                idComp=userCompany.getCompId();
                userCompany.setCompId(idEmpresa);
                userCompany.setDefaultEmpresaId(idComp);
            }
        }
        return userCompany;
    }

    public static long getUsersCountFromCompany(long compId){
        long total=0;
        if(!CanalEticoCommonsPortletKeys.ITS_NEW_VERSION){
            total = com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil.getUsersCountFromCompany(compId);

        }else{
            CompClientApplication compApp = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(compId);
            long clientId = compApp.getClientId();
            long companyId = compApp.getCompId();
            long contractId = compApp.getContractId();
            total= UserApplicationClientLocalServiceUtil.findByCompClientContractId(companyId,clientId,contractId).size();

        }
        return total;
    }

    public static Comp getCompanyByEmpresaId(long empresaId){
        Comp company=null;
        if(!CanalEticoCommonsPortletKeys.ITS_NEW_VERSION){
            company = CompLocalServiceUtil.fetchComp(empresaId);

        }else{
            CompClientApplication compApp = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(empresaId);
            long companyId = compApp.getCompId();
            try {
                company= CompLocalServiceUtil.getComp(companyId);
            } catch (PortalException e) {
                //La empresa no existe
            }

        }
        return company;
    }

    public static boolean getIsAdmin(boolean admin,long idCompany,long idUser){
        boolean isAdmin=admin;

        if(CanalEticoCommonsPortletKeys.ITS_NEW_VERSION){
            List<Role> allRoles = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
            Role role=null;
            for(Role rol : allRoles){
                if(rol.getAlias().equals(CanalEticoCommonsPortletKeys.NOMBRE_ROLE)){
                    role=rol;
                    break;
                }
            }
            isAdmin=UserApplicationClientLocalServiceUtil.existUserByRole(idUser,role.getRoleId() ,idCompany);
        }

        return isAdmin;
    }

    public static UserCompany getUserCompany(long idGestor,long idCompany){
        UserCompany usuario=null;
        if (!CanalEticoCommonsPortletKeys.ITS_NEW_VERSION){
            usuario=UserCompanyLocalServiceUtil.getUserCompany(idGestor,idCompany);
        }else{
            CompClientApplication compClientApplication=CompClientApplicationLocalServiceUtil.fetchCompClientApplication(idCompany);
            if(Validator.isNotNull(compClientApplication)){
                usuario=UserCompanyLocalServiceUtil.getUserCompany(idGestor,compClientApplication.getCompId());
            }
        }
        return usuario;
    }


}
