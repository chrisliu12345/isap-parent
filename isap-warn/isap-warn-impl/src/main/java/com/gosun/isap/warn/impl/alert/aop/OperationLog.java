package com.gosun.isap.warn.impl.alert.aop;

import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;

/**
 * 生成操作日志的注解。
 * <p>创建时间：2017-6-1 18:48</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface OperationLog {

    /**
     * 模块-警情处理
     */
    int ALERT_PROCESS = ServiceType.ALERT_PROCESS;
    /**
     * 模块-保安管理
     */
    int GUARD_MANAGE = ServiceType.GUARD_MANAGE;
    /**
     * 模块-警情信息统计
     */
    int ALERTS_STATISTICS = ServiceType.ALERTS_STATISTICS;
    /**
     * 模块-表格导出
     */
    int TABLE_EXPORT = ServiceType.ALERTS_TABLE_EXPORT;

    /**
     * 操作类型-增加
     */
    int ADD = OperateType.CONFIG_ADD;
    /**
     * 操作类型-删除
     */
    int DELETE = OperateType.CONFIG_DEL;
    /**
     * 操作类型-更新
     */
    int UPDATE = OperateType.CONFIG_MOD;
    /**
     * 操作类型-查询
     */
    int RETRIEVE = OperateType.CONFIG_QUERY;
}
