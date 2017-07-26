package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.dao.mapper.alert.AlertCommonMapper;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * common service 不提供超级用户判断，涉及到超级用户的调用 user service 方法。
 * <p>创建时间：2017-6-3 9:06</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertCommonServiceImpl implements AlertCommonService {
    private static final int START = 0;
    private static final int SUGGEST_LIMIT = 10;
    private static final Map<String, Long> MENU_MAPPER = new HashMap<>();

    @Autowired
    private AlertCommonMapper mapper;

    @Override
    public String getUserName(Long userId) {
        return mapper.getUserName(userId);
    }

    @Override
    public Long getUserId(String token) {
        TUser user = null;
        try {
            user = UserUtil.getCurrentUser();
        } catch (NullPointerException ignored) {
        }
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    @Override
    public String getDeviceDepartment(String deviceId) {
        return mapper.getDeviceDepartment(deviceId);
    }

    @Override
    public String getAlertDeviceId(Long alertId) {
        return mapper.getAlertDeviceId(alertId);
    }

    @Override
    public String getAlertDepartmentId(Long alertId) {
        return mapper.getAlertDepartmentId(alertId);
    }

    @Override
    public Integer getLimitTime(String deviceId) {
        return mapper.getLimitTime(deviceId);
    }

    @Override
    public List<Long> getRoles(Long userId) {
        return mapper.getRoles(userId);
    }

    @Override
    public List<String> getCommunitiesByUser(Long userId, SqlLimit limit) {
        return mapper.getCommunitiesByUser(userId, limit);
    }

    @Override
    public List<String> getDevicesByDepartments(List<String> departmentIds, SqlLimit limit) {
        return mapper.getDevicesByDepartments(departmentIds, limit);
    }

    @Override
    public Integer checkDepartmentRole(String departmentId, List<Long> roleIds) {
        return mapper.checkDepartmentRole(departmentId, roleIds);
    }

    @Override
    public List<String> getAllCommunities(SqlLimit limit) {
        return mapper.getAllCommunities(limit);
    }

    @Override
    public List<String> getAllDevice(SqlLimit limit) {
        return mapper.getAllDevice(limit);
    }

    @Override
    public Long checkMenuPermission(Long userId, String menuName) {
        List<Long> roles = getRoles(userId);
        Long menuId = getMenuId(menuName);
        return mapper.checkMenuPermission(roles, menuId);
    }

    @Override
    public Long checkMenuPermission(Long userId, Long menuId) {
        List<Long> roles = getRoles(userId);
        return mapper.checkMenuPermission(roles, menuId);
    }

    @Override
    public List<String> getChildCommunities(String parentId, SqlLimit limit) {
        return mapper.getChildCommunities(parentId, limit);
    }

    @Override
    public Integer countChildCommunities(String departmentId) {
        return mapper.countChildCommunities(departmentId);
    }

    @Override
    public List<String> getChildDepartmentsByUserId(String parentId, Long userId, SqlLimit limit) {
        return mapper.getChildDepartmentsByUserId(parentId, userId, limit);
    }

    @Override
    public Integer countChildDepartmentsByUserId(String parentId, Long userId) {
        return mapper.countChildDepartmentsByUserId(parentId, userId);
    }

    @Override
    public String getDepartmentName(String id) {
        return mapper.getDepartmentName(id);
    }

    @Override
    public String getParentDepartmentName(String id) {
        return mapper.getParentDepartmentName(id);
    }

    @Override
    public Integer getRelationDepartmentCount(String id) {
        return mapper.getRelationDepartmentCount(id);
    }

    @Override
    public Integer countDepartmentGuards(String id) {
        return mapper.countDepartmentGuards(id);
    }

    @Override
    public List<Map<String, Object>> suggestGuardGroup(String key) {
        SqlLimit limit = SqlLimit.init(START, SUGGEST_LIMIT);
        List<Map<String, Object>> list = mapper.suggestGuardGroup(key, limit);
        if (list != null) {
            if (list.size() == SUGGEST_LIMIT) {
                return list;
            }
            limit = SqlLimit.init(START, SUGGEST_LIMIT - list.size());
        }
        List<Map<String, Object>> childList = mapper.suggestGuardGroupByDepartment(key, limit);
        if (list == null) {
            list = childList;
        } else if (childList != null) {
            list.addAll(childList);
        }
        if (list != null) {
            if (list.size() == SUGGEST_LIMIT) {
                return list;
            }
            limit = SqlLimit.init(START, SUGGEST_LIMIT - list.size());
        }
        List<Map<String, Object>> grandList = mapper.suggestGuardGroupByChildDep(key, limit);
        if (list == null) {
            list = grandList;
        } else if (grandList != null) {
            list.addAll(grandList);
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> suggestDepartment(String key) {
        SqlLimit limit = SqlLimit.init(START, SUGGEST_LIMIT);
        List<Map<String, Object>> list = mapper.suggestDepartment(key, limit);
        if (list != null) {
            if (list.size() >= SUGGEST_LIMIT) {
                return list;
            }
            limit = SqlLimit.init(START, SUGGEST_LIMIT - list.size());
        }
        List<Map<String, Object>> childList = mapper.suggestChildDepartment(key, limit);
        if (list == null) {
            list = childList;
        } else if (childList != null) {
            list.addAll(childList);
        }
        return list;
    }

    @Override
    public Long getMenuId(String menuName) {
        return MENU_MAPPER.computeIfAbsent(menuName, n -> mapper.getMenuId(n));
    }

    @Override
    public boolean isCommunity(String departmentId) {
        if (StringUtils.isEmpty(departmentId)) {
            return false;
        }
        // 有设备的组织可以认为是小区
        String id = mapper.getFirstDeviceId(departmentId);
        return id != null;
    }

    @Override
    public String rootDepartment() {
        return "0";
    }

    @Override
    public List<String> getUserDepartments(Long userId) {
        return mapper.getUserDepartments(userId);
    }

    @Override
    public boolean hasDepartmentPermission(Long userId, String departmentId) {
        if(userId == null || departmentId == null){
            return false;
        }
        String id = mapper.hasChildDepartment(userId,departmentId);
        return id != null;
    }

}
