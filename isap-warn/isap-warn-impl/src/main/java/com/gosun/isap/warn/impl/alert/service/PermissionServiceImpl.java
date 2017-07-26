package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.dao.mapper.alert.GuardGroupMapper;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.PermissionService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>创建时间：2017/5/12 16:39</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service(AlertServiceId.PERMISSION_SERVICE)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private AlertCommonService commonService;

    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    private UserService userService;

    @Autowired
    private GuardGroupMapper groupMapper;

    @Override
    public boolean isLoggedSupperAdmin(Long userId, String token) {
        return userId != null && isSupperAdmin(userId) && isUserLoggedIn(userId);
    }

    @Override
    public boolean isUserLoggedIn(Long userId, String token) {
        TUser user = UserUtil.getCurrentUser();
        if (user == null || user.getId() == null) {
            return false;
        }
        return user.getId().equals(userId);
    }

    @Override
    public boolean checkAlertPermission(Long userId, Long alertId) {
        if (userId == null || alertId == null) {
            return false;
        }
        String departmentId = commonService.getAlertDepartmentId(alertId);
        return checkDepartmentPermission(userId, departmentId);
    }

    @Override
    public boolean checkDepartmentPermission(Long userId, String departmentId) {
        UserStatus status = userStatus(userId);
        switch (status) {
            case NO_LOGGED:
                return false;
            case LOGGED_ADMIN:
                return true;
            default:
                break;
        }

        List<Long> roles = commonService.getRoles(userId);
        if (ListUtils.isEmpty(roles) || departmentId == null) {
            return false;
        }

        Integer roleId = commonService.checkDepartmentRole(departmentId, roles);
        return roleId != null;
    }

    @Override
    public boolean checkAlertListPermission(Long userId) {
        UserStatus status = userStatus(userId);
        switch (status) {
            case NO_LOGGED:
                return false;
            case LOGGED_ADMIN:
                return true;
            default:
                return checkMenuPermission(userId, AlertConst.MENU_ALERT_LIST);
        }

    }

    @Override
    public boolean checkAlertProcessPermission(Long userId) {
        UserStatus status = userStatus(userId);
        switch (status) {
            case NO_LOGGED:
                return false;
            case LOGGED_ADMIN:
                return true;
            default:
                return checkMenuPermission(userId, AlertConst.MENU_ALERT_PROCESS);
        }
    }

    @Override
    public boolean checkGuardManagementPermission(Long userId) {
        UserStatus status = userStatus(userId);
        switch (status) {
            case NO_LOGGED:
                return false;
            case LOGGED_ADMIN:
                return true;
            default:
                return checkMenuPermission(userId, AlertConst.MENU_GUARD_ADMIN);
        }
    }

    @Override
    public boolean checkGuardPermission(Long userId, Long guardId) {
        return checkGuardManagementPermission(userId);
    }

    @Override
    public boolean checkGroupPermission(Long userId, Long groupId) {
        return false;
    }


    private boolean isSupperAdmin(Long userId){
        return userService.isSupperAdmin(userId);
    }

    private UserStatus userStatus(Long userId) {
        if (userId == null) {
            return UserStatus.NO_LOGGED;
        }

        if (isLoggedSupperAdmin(userId)) {
            return UserStatus.LOGGED_ADMIN;
        } else if (isUserLoggedIn(userId)) {
            return UserStatus.LOGGED_USER;
        } else {
            return UserStatus.NO_LOGGED;
        }
    }

    private boolean checkMenuPermission(long userId, String menuName) {
        return commonService.checkMenuPermission(userId, menuName) != null;
    }

    private boolean isLoggedSupperAdmin(Long userId) {
        return isLoggedSupperAdmin(userId, null);
    }

    private boolean isUserLoggedIn(Long userId) {
        return isUserLoggedIn(userId, null);
    }

    private enum UserStatus {
        LOGGED_ADMIN,
        LOGGED_USER,
        NO_LOGGED
    }
}
