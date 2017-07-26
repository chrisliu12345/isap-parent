package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.GuardGroupMapper;
import com.gosun.isap.dao.mapper.alert.GuardMapper;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.mapper.alert.base.GuardGroupBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardGroupBelongBaseMapper;
import com.gosun.isap.dao.po.alert.DetailGuardGroup;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import com.gosun.isap.dao.po.alert.GuardGroup;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroupBelong;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.GuardGroupService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017/5/10 9:42</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class GuardGroupServiceImpl implements GuardGroupService {
    @Autowired
    private GuardGroupBaseMapper groupBaseMapper;

    @Autowired
    private GuardGroupBelongBaseMapper belongBaseMapper;

    @Autowired
    private GuardGroupMapper groupMapper;

    @Autowired
    private GuardMapper guardMapper;

    @Autowired
    private AlertCommonService commonService;

    @Override
    public boolean addGuardGroup(BaseGuardGroup guardGroup) {
        return groupBaseMapper.insertSelective(guardGroup) > 0;
    }

    @Override
    public boolean updateGuardGroup(BaseGuardGroup guardGroup) {
        return groupBaseMapper.updateByPrimaryKeySelective(guardGroup) > 0;
    }

    @Override
    public boolean deleteGuardGroup(long id) {
        return groupMapper.deleteGuardGroup(id) > 0;
    }

    @Override
    public int deleteGuardGroup(List<Long> ids) {
        return groupMapper.deleteGuardGroups(ids);
    }

    @Override
    public boolean bindDepartment(long groupId, String departmentId,Long userId) {
        Map<String,Object> map = new HashMap<>();
        map.put(BaseGuardGroupBelong.GROUP_ID,groupId);
        map.put(BaseGuardGroupBelong.DEPARTMENT_ID,departmentId);
        BaseGuardGroupBelong belong = belongBaseMapper.selectFirstByColumns(map);
        if(belong != null){
            return true;
        }

        belong = new BaseGuardGroupBelong();
        belong.setGroupId(groupId);
        belong.setDepartmentId(departmentId);
        belong.setUserId(userId);
        return belongBaseMapper.insertSelective(belong) > 0;
    }

    @Override
    public int bindDepartment(long groupId, List<String> departmentIds,Long userId) {
        if(ListUtils.isEmpty(departmentIds)){
            return 0;
        }
        int count = 0;
        for (String departmentId:departmentIds) {
            if(bindDepartment(groupId,departmentId,userId)){
                count ++;
            }
        }
        return count;
    }

    @Override
    public boolean unbindDepartment(long groupId, String departmentId) {
        return groupMapper.unbindDepartment(groupId,departmentId) > 0;
    }

    @Override
    public int unbindDepartment(long groupId, List<String> departmentIds) {
        if(ListUtils.isEmpty(departmentIds)){
            return 0;
        }
        return groupMapper.unbindDepartments(groupId,departmentIds);
    }

    @Override
    public int unbindDepartment(long groupId) {
        return groupMapper.clearDepartments(groupId);
    }

    @Override
    public DetailGuardGroup getById(long id) {
        GuardGroup guardGroup = groupMapper.selectById(id);
        if(guardGroup == null){
            return null;
        }
        DetailGuardGroup detail = new DetailGuardGroup();
        detail.init(guardGroup);
        List<Guard> guards = guardMapper.listGuardsByGroup(id,null,null,null);
        detail.setGuards(guards);
        List<StringIdNameBean> departments = groupMapper.listDepartmentByGroupId(id);
        detail.setDepartments(departments);

        return detail;
    }

    @Override
    public List<StringIdNameBean> getDepartmentByGroupId(long groupId) {
        return groupMapper.listDepartmentByGroupId(groupId);
    }

    @Override
    public List<BaseGuard> getGuardByGroupId(long groupId) {
        return null;
    }

    @Override
    public List<Long> listGroupId(String departmentId) {
        List<String> departments = getChildDepartment(departmentId);
        return groupMapper.listGroupIdByDepartmentIds(departments);
    }

    @Override
    public List<Long> listChildGroupId(String departmentId) {
        return groupMapper.listGroups(departmentId);
    }

    @Override
    public List<GuardGroup> listGuardGroupById(List<Long> groupIds) {
        return groupMapper.listByIds(groupIds);
    }

    @Override
    public List<GuardGroup> listGuardGroupByDepartmentId(String departmentId) {
        if(StringUtils.isEmpty(departmentId)){
            return null;
        }
        List<Long> groupIds = listChildGroupId(departmentId);
        return listGuardGroupById(groupIds);
    }

    @Override
    public List<GuardGroup> listGuardGroupByDepartmentId(List<String> departmentIds) {
        if(ListUtils.isEmpty(departmentIds)){
            return null;
        }
        List<Long> groupIds = groupMapper.listGroupIdByDepartmentIds(departmentIds);
        return listGuardGroupById(groupIds);
    }

    @Override
    public List<GuardGroup> listGuardGroup(List<String> orderBy, SqlLimit limit) {
        return groupMapper.listGuardGroup(orderBy,limit);
    }

    @Override
    public int countGuardGroup() {
        return groupMapper.countGuardGroup();
    }

    @Override
    public GuardGroup getBindGuardGroup(String departmentId) {
        return groupMapper.getGroupByDepartment(departmentId);
    }

    @Override
    public List<String> listDepartmentIds(Long groupId) {
        if(groupId == null){
            return null;
        }

        return groupMapper.listDepartmentIds(groupId);
    }

    @Override
    public List<Map<String, Object>> questionedConfirm(long groupId, Date start, Date end, SqlLimit limit) {
        List<Long> guards = groupMapper.listGuardIds(groupId);
        if (ListUtils.isEmpty(guards)){
            return null;
        }
        List<Long> alerts = guardMapper.listGuardsQuestionedAlertIds(guards,start,end,limit);
        if(ListUtils.isEmpty(alerts)){
            return null;
        }
        return guardMapper.listQuestionedAlerts(alerts);
    }

    @Override
    public int countQuestionedConfirm(long groupId, Date start, Date end) {
        List<Long> guards = groupMapper.listGuardIds(groupId);
        if (ListUtils.isEmpty(guards)){
            return 0;
        }
        return guardMapper.countGuardsQuestionedAlerts(guards,start,end);
    }

    @Override
    public List<Map<String, Object>> unfinishedAlerts(long groupId, Date start, Date end, SqlLimit limit) {
        List<Long> guards = groupMapper.listGuardIds(groupId);
        if (ListUtils.isEmpty(guards)){
            return null;
        }
        List<Long> alerts = guardMapper.listGuardsUnfinishedAlertsId(guards,start,end,limit);
        if(ListUtils.isEmpty(alerts)){
            return null;
        }
        return guardMapper.listUnfinishedAlerts(alerts);
    }

    @Override
    public int countUnfinishedAlerts(long groupId, Date start, Date end) {
        List<Long> guards = groupMapper.listGuardIds(groupId);
        if (ListUtils.isEmpty(guards)){
            return 0;
        }
        return guardMapper.countGuardsUnfinishedAlerts(guards,start,end);
    }

    private List<String> getChildDepartment(String departmentId){
        List<String> departments = commonService.getChildCommunities(departmentId,null);
        if(departments == null){
            departments = new ArrayList<>();
        }
        departments.add(departmentId);
        return departments;
    }
}
