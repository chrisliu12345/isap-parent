package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.mapper.alert.OrderByBuilder;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.rest.GuardManageRestService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.GuardService;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.aop.CheckGuardMenuPermission;
import com.gosun.isap.warn.impl.alert.aop.DataExport;
import com.gosun.isap.warn.impl.alert.aop.OperationLog;
import com.gosun.isap.warn.impl.alert.export.base.ExportStreamingOutput;
import com.gosun.isap.warn.impl.alert.export.excel.ExcelStreamingOutput;
import com.gosun.isap.warn.impl.alert.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;
import static com.gosun.isap.warn.impl.alert.aop.OperationLog.*;

/**
 * <p>创建时间：2017-5-19 13:52</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckEmptyParams
@CheckGuardMenuPermission
public class GuardManageRestServiceImpl implements GuardManageRestService {
    private static final String GUARD_EXPORT_SETTING = "alert/setting/guard_export_setting.xml";
    private static final String FILE_NAME = "保安信息表";

    @Autowired
    @Qualifier(AlertServiceId.GUARD_SERVICE)
    private GuardService guardService;
    @Autowired
    private UserService userService;

    @Override
    @GET
    @Path(GUARDS)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = RETRIEVE, description = "查询保安列表")
    @DataExport
    public ResponseData<List<Guard>> listGuardsByGroup(@QueryParam(GROUP_ID) Long groupId,
                                                       @QueryParam(DEPARTMENT_ID) String departmentId,
                                                       @QueryParam(KEYWORD) String keyword,
                                                       @QueryParam(SORT) String sort,
                                                       @QueryParam(START) Integer start,
                                                       @QueryParam(LIMIT) Integer limit,
                                                       @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);

        List<String> orderBy = OrderByBuilder.parseSortString(sort, Guard::contains);
        SqlLimit sqlLimit = SqlLimit.init(start, limit);

        List<Guard> list;
        int count;
        boolean superAdmin = userService.isSupperAdmin(userId);

        if (groupId == null && departmentId == null) {
            if(superAdmin){
                list = guardService.listGuards(keyword,orderBy,sqlLimit);
                count = guardService.countGuards(keyword);
            }else {
                List<Long> groups = userService.getGuardGroups(userId);
                if(ListUtils.isEmpty(groups)){
                    return ResponseData.error(AlertError.NO_DATA);
                }
                list = guardService.listGuardsByGroup(groups, keyword, orderBy, sqlLimit);
                count = guardService.countGuardsByGroup(groups, keyword);
            }
        } else if (groupId != null) {
            if(superAdmin || userService.hasGroupPermission(userId,groupId))  {
                list = guardService.listGuardsByGroup(groupId, keyword, orderBy, sqlLimit);
                count = guardService.countGuardsByGroup(groupId, keyword);
            }else {
                return ResponseData.error(AlertError.NO_PERMISSION);
            }
        } else {
            if(superAdmin) {
                list = guardService.listGuardsByDepartmentId(departmentId, keyword, orderBy, sqlLimit);
                count = guardService.countGuardsByDepartmentId(departmentId, keyword);
            }else {
                List<Long> groups = userService.getGuardGroups(userId);
                if(groups == null){
                    return ResponseData.error(AlertError.NO_DATA);
                }
                list = guardService.listGuardsByGroup(groups, keyword, orderBy, sqlLimit);
                count = guardService.countGuardsByGroup(groups, keyword);
            }
        }
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }

        return ResponseData.init(list, count);
    }

    @Override
    @POST
    @Transactional
    @Path(GUARDS_ADD)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = ADD, description = "添加保安")
    public ResponseData addGuard(Guard guard, @HeaderParam(USER_ID) Long userId) {
        // 解析不到 guard
        if (guard == null) {
            return ResponseData.error(AlertError.GUARD_NULL);
        }
        guard.setUserId(init(userId));
        // 手机号是否有效
        String phone = guard.getPhone();
        if (!CommonUtil.isValidPhoneNumber(phone)) {
            return ResponseData.error(AlertError.INVALID_PHONE);
        }
        // 手机号是否存在
        if (guardService.isPhoneExist(guard.getPhone())) {
            return ResponseData.error(AlertError.PHONE_EXIST);
        }
        guard.setUserId(init(userId));
        boolean result = guardService.addGuard(guard);
        return result ? ResponseData.ok() : ResponseData.error(AlertError.INSERT_FAILED);
    }

    @Override
    @DELETE
    @Transactional
    @Path(GUARDS_DELETE)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = DELETE, description = "删除保安")
    public ResponseData deleteGuard(Map<String, List<Long>> map, @HeaderParam(USER_ID) Long userId) {
        List<Long> guardIds = map.get(ID);
        if (ListUtils.isEmpty(guardIds)) {
            return ResponseData.init(0);
        }
        int row = guardService.deleteGuard(guardIds);
        return ResponseData.init(row);
    }

    @Override
    @PUT
    @Transactional
    @Path(GUARDS_UPDATE)
    @SysOperateLog(serviceType = OperationLog.GUARD_MANAGE, operateType = UPDATE, description = "修改保安信息")
    public ResponseData updateGuard(Guard guard, @HeaderParam(USER_ID) Long userId) {
        if (guard == null) {
            return ResponseData.error(AlertError.GUARD_NULL);
        }
        return updateGuard(guard);
    }

    @Override
    @PUT
    @Transactional
    @Path(GUARDS_ID)
    public ResponseData updateGuard(@PathParam(ID) Long guardId, Guard guard, @HeaderParam(USER_ID) Long userId) {
        if (guard == null) {
            return ResponseData.error(AlertError.INVALID_PARAMS, "guard");
        }
        guard.setId(guardId);
        return updateGuard(guard);
    }

    private ResponseData updateGuard(Guard guard) {
        // id 是否存在
        Long id = guard.getId();
        if (id == null) {
            return ResponseData.error(AlertError.NO_ID);
        }
        // 手机号是否有效
        String phone = guard.getPhone();
        if (phone != null) {
            if (!CommonUtil.isValidPhoneNumber(phone)) {
                return ResponseData.error(AlertError.INVALID_PHONE);
            }
            Guard guard1 = guardService.getGuard(id);
            if (!phone.equals(guard1.getPhone())) {
                // 手机号是否存在
                if (guardService.isPhoneExist(guard.getPhone())) {
                    return ResponseData.error(AlertError.PHONE_EXIST);
                }
            }
        }
        boolean result = guardService.updateGuard(guard);
        return result ? ResponseData.ok() : ResponseData.error(AlertError.INSERT_FAILED);
    }

    @Override
    @DELETE
    @Transactional
    @Path(GUARDS_ID)
    public ResponseData deleteGuard(@PathParam(ID) Long id) {
        if (id == null) {
            return ResponseData.error(AlertError.NO_ID);
        }
        boolean result = guardService.deleteGuard(id);

        if (result) {
            return ResponseData.ok();
        }
        return ResponseData.error(AlertError.DELETE_FAILED);
    }

    @Override
    @GET
    @Transactional
    @Path(GUARDS_SEARCH)
    public ResponseData<List<Guard>> search(
            @QueryParam(KEYWORD) String content,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        List<Guard> guards = guardService.search(content, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(guards)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(guards);
    }

    @Override
    @PUT
    @Transactional
    @Path(GUARDS_ID_CHANGE_GROUP)
    public ResponseData changeGroup(
            @PathParam(ID) Long guardId,
            @PathParam(GROUP_ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        if (guardService.changeGroup(guardId, groupId)) {
            return ResponseData.ok();
        } else {
            return ResponseData.error(AlertError.UPDATE_FAILED);
        }
    }

    @Override
    @PUT
    @Transactional
    @Path(GROUPS_ADD_GUARD)
    public ResponseData<Integer> changeGroup(List<Long> guardIds, @PathParam(ID) Long groupId, @HeaderParam(USER_ID) Long userId) {
        int row = guardService.changeGroup(guardIds, groupId);
        return ResponseData.init(row);
    }

    @Override
    @GET
    @Path(GUARDS_EXPORT)
    public Response exportGuards(
            @QueryParam(GROUP_ID) Long groupId,
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(SORT) String sort,
            @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        List<String> orderBy = OrderByBuilder.parseSortString(sort, Guard::contains);

        // 查询保安组
        List<Guard> list;
        if (groupId == null && departmentId == null) {
            List<Long> groups = userService.getGuardGroups(userId);
            list = guardService.listGuardsByGroup(groups,null, orderBy, null);
        } else if (groupId != null) {
            list = guardService.listGuardsByGroup(groupId,null, orderBy, null);
        } else {
            list = guardService.listGuardsByDepartmentId(departmentId,null, orderBy, null);
        }

        // guard 转 map
        List<Map<String, Object>> exportList = new ArrayList<>();
        if (list != null) {
            int index = 0;
            for (Guard guard : list) {
                index++;
                Map<String, Object> map = new HashMap<>();
                map.put("index", index);
                map.put("name", guard.getName());
                map.put("phone", guard.getPhone());
                map.put("groupName", guard.getGroupName());
                exportList.add(map);
            }
        }

        // 返回文件
        ExportStreamingOutput output = new ExcelStreamingOutput(GUARD_EXPORT_SETTING, null,
                exportList, FILE_NAME);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }


}
