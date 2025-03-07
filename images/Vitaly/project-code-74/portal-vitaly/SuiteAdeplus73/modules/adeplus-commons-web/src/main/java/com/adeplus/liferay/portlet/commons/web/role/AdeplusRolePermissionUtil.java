package com.adeplus.liferay.portlet.commons.web.role;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.bean.RolePermission;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.UserRole;
import com.adeplus.liferay.portlet.suite.manager.service.UserRoleLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserRolePK;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

public class AdeplusRolePermissionUtil {

    private static Log _log = LogFactoryUtil.getLog(AdeplusRolePermissionUtil.class);

    public static boolean saveUserRolePermission(User user, long compId, List<RolePermission> listRolePermission){

        boolean changeRoleList = false;
        UserRolePK pk = null;
        UserRole ur = null;

        if(Validator.isNull(user) || Validator.isNull(listRolePermission)){
            _log.info("The user is null o permission list is null. ");
            return changeRoleList;
        }

        for(RolePermission rp:listRolePermission){
            // Get userrolen if exits.
            UserRole userRole = UserRoleLocalServiceUtil.getUserRole(user.getUserId(), compId, rp.getRole().getRoleId());

            if(rp.isHasPermission()){
                if(Validator.isNull(userRole)){
                    pk = new UserRolePK(user.getUserId(), compId, rp.getRole().getRoleId());
                    ur = UserRoleLocalServiceUtil.createUserRole(pk);
                    UserRoleLocalServiceUtil.addUserRole(ur);
                    AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_ADD_ROLE_PERMISSION, "Add role to "+ user.getEmailAddress() +" for " + rp.getRole().getName());
                    changeRoleList = true;
                }
            }else{
                if(Validator.isNotNull(userRole)){
                    UserRoleLocalServiceUtil.deleteUserRole(userRole);
                    AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_DELETE_ROLE_PERMISSION, "Delete role to "+ user.getEmailAddress() +" for " + rp.getRole().getName());
                    changeRoleList = true;
                }
            }
        }
        return changeRoleList;
    }
}
