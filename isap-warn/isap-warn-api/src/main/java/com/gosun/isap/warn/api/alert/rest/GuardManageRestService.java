package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.warn.api.alert.model.ResponseData;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * 保安管理接口。
 * <p>创建时间：2017-5-19 11:59</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardManageRestService extends RestConst {
    /**
     * @param groupId      保安组 id
     * @param departmentId 部门 id
     * @param sort         排序
     * @param start        分页
     * @param limit        分页
     * @param userId       用户 id
     * @return 保安列表
     */
    ResponseData<List<Guard>> listGuardsByGroup(Long groupId, String departmentId, String keyword,String sort, Integer start, Integer limit, Long userId);

    /**
     * @param guard  Guard 数据
     * @param userId 用户 id
     * @return 请求结果
     */
    ResponseData addGuard(Guard guard, Long userId);

    /**
     * @param map    保安组 id
     * @param userId 用户 id
     * @return 请求结果
     */
    ResponseData deleteGuard(Map<String, List<Long>> map, Long userId);

    /**
     * @param guard  保安信息
     * @param userId 用户 id
     * @return 请求结果
     */
    ResponseData updateGuard(Guard guard, Long userId);

    /**
     * @param guardId 保安 id
     * @param guard   保安信息
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData updateGuard(Long guardId, Guard guard, Long userId);

    /**
     * @param id 保安 id
     * @return 请求结果
     */
    ResponseData deleteGuard(Long id);

    /**
     * @param content 关键字
     * @param userId  用户 id
     * @param start   分页
     * @param limit   分页
     * @return 保安列表
     */
    ResponseData<List<Guard>> search(String content, Integer start, Integer limit, Long userId);

    /**
     * 改变保安组
     *
     * @param guardId 保安 id
     * @param groupId 保安组 id
     * @param userId  用户 id
     * @return ResponseData
     */
    ResponseData changeGroup(Long guardId, Long groupId, Long userId);

    /**
     * 改变保安组
     *
     * @param guardIds 保安 id
     * @param groupId  保安组 id
     * @param userId   用户 id
     * @return ResponseData
     */
    ResponseData<Integer> changeGroup(List<Long> guardIds, Long groupId, Long userId);

    /**
     * @param groupId      保安组 id
     * @param departmentId 部门 id
     * @param sort         排序
     * @param userId       用户 id
     * @return 保安列表
     */
    Response exportGuards(Long groupId, String departmentId, String sort, Long userId);
}
