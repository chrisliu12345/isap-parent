package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.GuardGroupMapper;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.mapper.alert.base.GuardBaseMapper;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户服务，调用用户服务之前需要先鉴权，用户服务不负责鉴权
 * <p>
 * <p>创建时间：2017/5/3 14:04</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service(AlertServiceId.USER_SERVICE)
public class UserServiceImpl implements UserService {

    @Autowired
    private AlertCommonService commonService;

    @Autowired
    private GuardGroupMapper groupMapper;

    @Autowired
    private GuardBaseMapper guardBaseMapper;

    @Override
    public Long getUserId(String token) {
        return commonService.getUserId(token);
    }

    @Override
    public List<Long> getRoles(Long userId) {
        return commonService.getRoles(userId);
    }

    @Override
    public String getUserName(Long id) {
        return commonService.getUserName(id);
    }

    @Override
    public String getUserInfo(Long userId) {
        return getUserName(userId);
    }

    @Override
    public List<String> getUserDepartment(Long userId) {
        if (userId == null) {
            return null;
        }
        if (isSupperAdmin(userId)) {
            return Arrays.asList(commonService.rootDepartment());
        }
        return commonService.getUserDepartments(userId);
    }

    @Override
    public List<String> getCommunities(Long userId) {
        return getCommunities(userId, null);
    }

    @Override
    public List<String> getCommunities(Long userId, SqlLimit limit) {
        // 超级管理员账号
        if (isSupperAdmin(userId)) {
            return commonService.getAllCommunities(limit);
        }

        return commonService.getCommunitiesByUser(userId, limit);
    }

    @Override
    public List<String> getDevices(Long userId) {
        return getDevices(userId, null);
    }

    @Override
    public List<String> getDevices(Long userId, SqlLimit limit) {
        // 超级管理员账号
        if (isSupperAdmin(userId)) {
            return commonService.getAllDevice(limit);
        }

        List<String> departments = commonService.getCommunitiesByUser(userId, limit);
        if (ListUtils.isEmpty(departments)) {
            return null;
        }

        return commonService.getDevicesByDepartments(departments, limit);
    }

    @Override
    public List<String> getChildCommunity(Long userId, String departmentId) {
        return getChildCommunity(userId, departmentId, null);
    }

    @Override
    public List<String> getChildCommunity(Long userId, String departmentId, SqlLimit limit) {
        if (userId == null || StringUtils.isEmpty(departmentId)) {
            return null;
        }
        List<String> communities;
        boolean isCommunity = commonService.isCommunity(departmentId);
        boolean isStartLimit = limit == null || limit.getOffset() == null || limit.getOffset() == 0;
        if (limit != null && isStartLimit) {
            limit.setLength(limit.getLength() - 1);
        }
        // 超级管理员账号
        if (isSupperAdmin(userId)) {
            communities = commonService.getChildCommunities(departmentId, limit);
        } else {
            communities = commonService.getChildDepartmentsByUserId(departmentId, userId, limit);
        }

        if (isCommunity && isStartLimit) {
            List<String> list = new ArrayList<>();
            list.add(departmentId);
            list.addAll(communities);
            communities = list;
        }
        return communities;
    }

    @Override
    public int countChildCommunity(Long userId, String departmentId) {
        if (userId == null || StringUtils.isEmpty(departmentId)) {
            return 0;
        }
        // 超级管理员账号
        Integer count;
        if (isSupperAdmin(userId)) {
            count = commonService.countChildCommunities(departmentId);
        } else {
            count = commonService.countChildDepartmentsByUserId(departmentId, userId);
        }
        if (commonService.isCommunity(departmentId)) {
            if (count == null) {
                count = 0;
            }
            count += 1;
        }
        return count;
    }


    @Override
    public boolean isSupperAdmin(Long userId) {
        return userId != null && userId == AlertConst.ADMIN_ID;
    }

    @Override
    public List<Long> getGuardGroups(long userId) {
        if (isSupperAdmin(userId)) {
            return groupMapper.listAllIds();
        }

        List<String> departments = getCommunities(userId);

        return groupMapper.listUserCreated(userId, departments);
    }

    @Override
    public List<Long> getGuardGroups(long userId, String departmentId) {
        if (isSupperAdmin(userId)) {
            return groupMapper.listAllIds();
        }

        List<String> departments = getChildCommunity(userId, departmentId);

        return groupMapper.listUserCreated(userId, departments);
    }

    @Override
    public boolean hasGroupPermission(Long userId, Long groupId) {
        if (userId == null || groupId == null) {
            return false;
        }
        if (isSupperAdmin(userId)) {
            return true;
        }
        List<String> departments = groupMapper.listDepartmentIds(groupId);
        if (ListUtils.isEmpty(departments)) {
            return false;
        }
        for (String department : departments) {
            if (hasDepartmentPermission(userId, department)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasDepartmentPermission(Long userId, String departmentId) {
        if (userId == null || departmentId == null) {
            return false;
        }
        if (isSupperAdmin(userId)) {
            return true;
        }
        List<String> departments = getUserDepartment(userId);
        if (ListUtils.isEmpty(departments)) {
            return false;
        }

        return commonService.hasDepartmentPermission(userId, departmentId);
    }

    @Override
    public boolean hasGuardPermission(Long userId, Long guardId) {
        if (userId == null || guardId == null) {
            return false;
        }
        if (isSupperAdmin(userId)) {
            return true;
        }
        BaseGuard guard = guardBaseMapper.selectByPrimaryKey(guardId);
        if (guard == null) {
            return false;
        }
        if (guard.getUserId().equals(userId)) {
            return true;
        }
        return hasGroupPermission(userId, guard.getGroupId());
    }

    @Override
    public boolean hasAlertPermission(Long userId, Long alertId) {
        if (userId == null || alertId == null) {
            return false;
        }
        if (isSupperAdmin(userId)) {
            return true;
        }
        String departmentId = commonService.getAlertDepartmentId(alertId);
        if (departmentId == null) {
            return false;
        }
        return hasDepartmentPermission(userId, departmentId);
    }

    @Override
    public boolean hasMenuPermission(Long userId, String menuName) {
        if (userId == null || StringUtils.isEmpty(menuName)) {
            return false;
        }
        if (isSupperAdmin(userId)) {
            return true;
        }
        return commonService.checkMenuPermission(userId, menuName) != null;
    }

    @Override
    public boolean hasMenuPermission(Long userId, Long menuId) {
        if (userId == null || menuId == null) {
            return false;
        }
        if (isSupperAdmin(userId)) {
            return true;
        }
        return commonService.checkMenuPermission(userId, menuId) != null;
    }

}
