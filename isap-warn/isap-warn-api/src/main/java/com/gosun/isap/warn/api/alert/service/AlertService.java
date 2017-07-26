package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.dao.po.alert.base.BaseAlertDetail;
import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 警情信息服务<br>
 * 根据 id 获取警情信息
 * <p>
 * 创建时间：2017/4/14 15:38
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertService {
    /**
     * 插入一条警情
     *
     * @param deviceId    设备 id
     * @param description 描述
     * @return 是否插入成功
     */
    long createAlert(String deviceId, String description);

    /**
     * 插入一条警情
     *
     * @param deviceId    设备 id
     * @param description 描述
     * @param reason 原因
     * @return 是否插入成功
     */
    long createAlert(String deviceId, String description,byte reason);

    /**
     * 警情发生时，根据 deviceId 获取未处理警情数
     *
     * @param userId 用户 id
     * @param alert  警情信息用于获取 deviceId
     * @return 未处理的警情数
     */
    int getCountOfUnprocessedAlerts(long userId, BaseAlert alert);

    /**
     * 获取警情的基本信息
     *
     * @param id 警情 id
     * @return 警情基本信息
     */
    Alert getBaseAlert(long id);

    /**
     * 获取警情统计信息
     *
     * @param id 警情 id
     * @return 警情统计信息
     */
    BaseAlertDetail getAlertDetail(long id);

    /**
     * 获取警情日志
     *
     * @param id 警情 id
     * @return 日志列表
     */
    List<String> getAlertLog(long id);

    /**
     * 获取警情信息
     *
     * @param id 警情 id
     * @return 警情信息包括：基本信息、日志以及统计信息
     */
    Map<String, Object> getAlert(long id);

    /**
     * 生成警情的统计数据，需要更新警情的状态
     *
     * @param alertId 警情 id
     * @return 是否成功
     */
    boolean generateAlertDetail(long alertId);

    /**
     * 最后一次出警时间
     *
     * @param departmentId 社区 id
     * @return 最后一次出警时间
     */
    Date latestProcessedTime(String departmentId);

    /**
     * @param alertId 警情 id
     * @return 警情资源
     */
    List<BaseAlertResource> getResources(long alertId);

    /**
     * @param alertId 警情 id
     * @return deviceId
     */
    String getDeviceId(long alertId);

    /**
     * @param alertId 警情 id
     * @return 所在部门 id
     */
    String getDepartmentId(long alertId);

    /**
     * @param alertId  警情 id
     * @param formData 表单数据
     * @return 操作成功
     * @throws IOException 文件保存异常
     */
    boolean saveUploadResource(long alertId, MultipartFormDataInput formData) throws IOException;

    /**
     * @param alertId 警情 id
     * @param type    类型
     * @param path    路径
     * @param url     url
     * @return 保存成功
     */
    boolean saveResource(long alertId, int type, String path, String url);

    /**
     * @param alertId 警情 id
     * @param type    类型
     * @param index   index
     * @return 资源信息
     */
    BaseAlertResource getResource(long alertId, byte type, byte index);

    /**
     * @param id 资源 id
     * @return 资源信息
     */
    BaseAlertResource getResourceById(long id);

    /**
     * @param alertId 警情 id
     * @return 附近的保安信息
     */
    List<Guard> getNearbyGuards(Long alertId);

    /**
     * @param alertId 警情 id
     * @return 处理警情的保安信息
     */
    Guard getAlertGuard(Long alertId);
}
