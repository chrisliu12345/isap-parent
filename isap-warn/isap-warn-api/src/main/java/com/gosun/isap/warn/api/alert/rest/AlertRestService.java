package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseAlertDetail;
import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * 警情信息服务。
 * <p>创建时间：2017/5/16 19:08</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertRestService extends RestConst {
    /**
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 警情处理日志
     */
    ResponseData<List<String>> getAlertLogs(Long alertId, Long userId);

    /**
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 警情的基本信息
     */
    ResponseData<Alert> getBaseAlert(Long alertId, Long userId);

    /**
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 警情的统计信息
     */
    ResponseData<BaseAlertDetail> getAlertDetail(Long alertId, Long userId);

    /**
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 警情信息
     */
    ResponseData<Map<String, Object>> getAlert(Long alertId, Long userId);

    /**
     * 获取警情的附加信息，包括小区的警情数量，上次出警时间等
     *
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 警情的附加信息
     */
    ResponseData<Map<String, Object>> getExtend(Long alertId, Long userId);

    /**
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 警情的资源信息
     */
    ResponseData<List<BaseAlertResource>> getResources(Long alertId, Long userId);

    /**
     * 警情资源上传
     *
     * @param alertId 警情 id
     * @param input   form 表单数据
     * @return 请求结果
     */
    ResponseData upload(Long alertId, MultipartFormDataInput input);

    /**
     * 获取警情资源
     *
     * @param alertId 警情 id
     * @param type    类型
     * @param index   索引
     * @param userId  用户 id
     * @return 资源
     */
    Response getResource(Long alertId, Byte type, Byte index, Long userId);

    /**
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 附近小区的保安
     */
    ResponseData<List<Guard>> listNearbyGuards(Long alertId, Long userId);
}
