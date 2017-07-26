package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.warn.api.alert.model.ResponseData;

import java.util.List;
import java.util.Map;

/**
 * 提供工具类功能。
 * <p>创建时间：2017-6-2 8:53</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface UtilsRestService extends RestConst {
    /**
     * 根据关键字查询保安组
     *
     * @param key 关键字
     * @return 保安组列表
     */
    ResponseData<List<Map<String, Object>>> suggestGuardGroup(String key);

    /**
     * 根据关键字查询部门列表
     *
     * @param key 关键字
     * @return 部门列表
     */
    ResponseData<List<Map<String, Object>>> suggestDepartment(String key);
}
