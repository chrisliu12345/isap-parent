package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.mapper.alert.OrderByBuilder;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.DetailGuardGroup;
import com.gosun.isap.dao.po.alert.GuardGroup;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.rest.GuardGroupManageRestService;
import com.gosun.isap.warn.api.alert.service.GuardGroupService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.aop.CheckGuardMenuPermission;
import com.gosun.isap.warn.impl.alert.aop.DataExport;
import com.gosun.isap.warn.impl.alert.aop.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;
import static com.gosun.isap.warn.impl.alert.aop.OperationLog.*;

/**
 * <p>创建时间：2017-5-19 16:48</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckGuardMenuPermission
@CheckEmptyParams
public class GuardGroupManageRestServiceImpl implements GuardGroupManageRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuardGroupManageRestServiceImpl.class);
    @Autowired
    private GuardGroupService groupService;

    @Override
    @GET
    @Path(GUARDS_GROUPS)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = RETRIEVE, description = "查询保安组列表")
    @DataExport
    public ResponseData<List<GuardGroup>> listGuardGroup(@QueryParam(DEPARTMENT_ID) String departmentId,
                                                         @QueryParam(SORT) String sort,
                                                         @QueryParam(START) Integer start,
                                                         @QueryParam(LIMIT) Integer limit,
                                                         @HeaderParam(USER_ID) Long userId) {
        List<GuardGroup> groups;
        List<String> orderBy = OrderByBuilder.parseSortString(sort, GuardGroup::contains);
        int count = 0;
        if (departmentId != null) {
            groups = groupService.listGuardGroupByDepartmentId(departmentId);
        } else {
            groups = groupService.listGuardGroup(orderBy, SqlLimit.init(start, limit));
            count = groupService.countGuardGroup();
        }
        if (ListUtils.isEmpty(groups)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(groups, count);
    }

    @Override
    @POST
    @Transactional
    @Path(GUARDS_GROUPS_ADD)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = ADD, description = "添加保安组")
    public ResponseData addGuardGroup(GuardGroup group, @HeaderParam(USER_ID) Long userId) {
        if (group == null) {
            return ResponseData.error(AlertError.INVALID_PARAMS, "保安组信息");
        }
        group.setUserId(init(userId));
        boolean addGroup = groupService.addGuardGroup(group);
        if (!addGroup) {
            return ResponseData.error(AlertError.INSERT_FAILED);
        }
        List<String> departmentIds = group.getDepartmentIds();
        int row = groupService.bindDepartment(group.getId(), departmentIds, init(userId));
        return ResponseData.ok();
    }

    @Override
    @PUT
    @Transactional
    @Path(GUARDS_GROUPS_ID)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = UPDATE, description = "修改保安组")
    public ResponseData updateGuardGroup(@PathParam(ID) Long groupId, GuardGroup group, @HeaderParam(USER_ID) Long userId) {
        group.setId(groupId);
        group.setUserId(null);
        boolean result = groupService.updateGuardGroup(group);
        if (!result) {
            return ResponseData.error(AlertError.UPDATE_FAILED);
        }

        List<String> departmentIds = group.getDepartmentIds();
        if (departmentIds != null) {
            int row = groupService.unbindDepartment(group.getId());
            row = groupService.bindDepartment(group.getId(), departmentIds, init(userId));
        }

        return ResponseData.ok();
    }

    @Override
    @DELETE
    @Transactional
    @Path(GUARDS_GROUPS_ID)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = DELETE, description = "删除保安组")
    public ResponseData deleteGuardGroup(@PathParam(ID) Long groupId, @HeaderParam(USER_ID) Long userId) {
        if (groupId == null) {
            return ResponseData.error(AlertError.INVALID_PARAMS, "groupId");
        }

        boolean result = groupService.deleteGuardGroup(groupId);

        if (result) {
            return ResponseData.ok();
        }
        return ResponseData.error(AlertError.DELETE_FAILED);
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_ID)
    public ResponseData<DetailGuardGroup> getGuardGroup(@PathParam(ID) Long id, @HeaderParam(USER_ID) Long userId) {
        DetailGuardGroup guardGroup = groupService.getById(id);
        return ResponseData.init(guardGroup);
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_GROUP)
    public ResponseData<GuardGroup> getGuardGroup(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @HeaderParam(USER_ID) Long userId) {
        GuardGroup group = groupService.getBindGuardGroup(departmentId);
        if (group == null) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(group);
    }

    @Override
    @DELETE
    @Transactional
    @Path(GUARDS_GROUP_UNBIND)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = DELETE, description = "解除保安组与小区的绑定")
    public ResponseData unbindDepartment(
            @PathParam(DEPARTMENT_ID) String departmentId,
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        if (groupService.unbindDepartment(groupId, departmentId)) {
            return ResponseData.ok();
        }
        return ResponseData.error(AlertError.UNBIND_DEPARTMENT_FAILED);
    }

    @Override
    @DELETE
    @Transactional
    @Path(GROUP_UNBIND)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = DELETE, description = "解除保安组与多个小区的绑定")
    public ResponseData unbindDepartment(
            List<String> departmentIds,
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        int row = groupService.unbindDepartment(groupId, departmentIds);
        return ResponseData.init(row);
    }

    @Override
    @POST
    @Transactional
    @Path(GUARDS_GROUP_BIND)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = ADD, description = "绑定保安组与小区")
    public ResponseData bindDepartment(
            @PathParam(DEPARTMENT_ID) String departmentId,
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        if (groupService.bindDepartment(groupId, departmentId, userId)) {
            return ResponseData.ok();
        }
        return ResponseData.error(AlertError.BIND_DEPARTMENT_FAILED);
    }

    @Override
    @GET
    @Path(GROUPS_ID_DEPARTMENTS)
    public ResponseData<List<StringIdNameBean>> groupDepartments(
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        List<StringIdNameBean> list = groupService.getDepartmentByGroupId(groupId);
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(list);
    }

    @Override
    @DELETE
    @Transactional
    @Path(GUARDS_GROUPS_DELETE)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = OperationLog.DELETE, description = "批量删除保安组")
    public ResponseData<Integer> deleteGroups(Map<String, List<Long>> map, Long userId) {
        ResponseData<Integer> responseData = new ResponseData<>();
        if (map == null || map.isEmpty()) {
            return responseData;
        }
        List<Long> ids = map.get(ID);
        if (ListUtils.isEmpty(ids)) {
            return responseData;
        }
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (Long id : ids) {
            try {
                if (groupService.deleteGuardGroup(id)) {
                    count++;
                }
            } catch (DataIntegrityViolationException e) {
                LOGGER.error("deleteGroups : ID 为：{}的保安组下存在保安，不能删除", id);
                builder.append(id)
                        .append(",");
            }
        }
        String string = builder.toString();
        String template = "ID 为 %s 的保安组删除失败，请先删除保安组下的保安";
        if (!string.isEmpty()) {
            string = string.substring(0, string.length() - 1);
            responseData.setError(AlertError.DELETE_FAILED);
            responseData.getHead().setMessage(String.format(template, string));
        }
        responseData.setData(count);

        return responseData;
    }
}
