package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.dao.po.TUser;

/**
 * 存放 REST 用到的常量
 * <p>创建时间：2017/5/15 11:58</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface RestConst {
    /**
     * url param : user_id
     */
    String USER_ID = "user_id";
    /**
     * url param : token
     */
    String TOKEN = "token";
    /**
     * url param : start
     */
    String START = "start";
    /**
     * url param : limit
     */
    String LIMIT = "limit";
    /**
     * url param : sort
     */
    String SORT = "sort";
    /**
     * url param : status
     */
    String STATUS = "status";
    String STATUS_START = "status_start";
    String STATUS_END = "status_end";
    /**
     * url param : start_time
     */
    String START_TIME = "start_time";
    /**
     * url param : end_time
     */
    String END_TIME = "end_time";
    /**
     * url param : department_id
     */
    String DEPARTMENT_ID = "department_id";
    /**
     * url param : id
     */
    String ID = "id";
    /**
     * url param : type
     */
    String TYPE = "type";
    /**
     * url param : index
     */
    String INDEX = "index";
    /**
     * url param : base
     */
    String BASE = "base";
    /**
     * url param : statistics
     */
    String STATISTICS = "statistics";
    /**
     * url param : resource
     */
    String RESOURCES = "resource";
    /**
     * url param : guard
     */
    String GUARD = "guard";
    /**
     * url param : logs
     */
    String LOGS = "logs";
    /**
     * url param : date
     */
    String DATE = "date";
    /**
     * url param : keyword
     */
    String KEYWORD = "keyword";
    /**
     * url param : group_id
     */
    String GROUP_ID = "group_id";


    /**
     * url 路径 根路径
     */
    String ROOT_PATH = "/";
    /**
     * url 路径 警情列表
     */
    String ALERTS = "alerts";
    /**
     * url 路径 未处理警情
     */
    String ALERTS_UNPROCESSED = ALERTS + "/unprocessed";
    /**
     * url 路径 等待中的警情
     */
    String ALERTS_WAITING = ALERTS + "/waiting";
    /**
     * url 路径 等待回电的警情
     */
    String ALERTS_WAITING_CALLBACK = ALERTS + "/waiting_callback";
    /**
     * url 路径 等待二次回电的警情
     */
    String ALERTS_WAITING_CALLBACK_AGAIN = ALERTS + "/waiting_callback_again";
    /**
     * url 路径 处理完毕的警情
     */
    String ALERTS_FINISHED = ALERTS + "/finished";

    /**
     * url 路径 警情导出
     */
    String ALERTS_EXPORT = ALERTS + "/export";
    /**
     * url 路径 导出出警记录表
     */
    String ALERTS_EXPORT_LOG = ALERTS_EXPORT + "/log";
    /**
     * url 路径 出警记录表数据
     */
    String ALERTS_EXPORT_LOG_DATA = ALERTS_EXPORT + "/log/data";
    /**
     * url 路径 导出出警详情表
     */
    String ALERTS_EXPORT_DETAIL = ALERTS_EXPORT + "/detail";
    /**
     * url 路径 出警详情表数据
     */
    String ALERTS_EXPORT_DETAIL_DATA = ALERTS_EXPORT + "/detail/data";
    /**
     * url 路径 未完成出警表
     */
    String ALERTS_EXPORT_UNFINISHED = ALERTS_EXPORT + "/unfinished_alerts";
    /**
     * url 路径 未完成出警数据
     */
    String ALERTS_EXPORT_UNFINISHED_DATA = ALERTS_EXPORT + "/unfinished_alerts/data";
    /**
     * url 路径 导出出警汇总表
     */
    String ALERTS_EXPORT_SUMMARY = ALERTS_EXPORT + "/summary";
    /**
     * url 路径 出警汇总表数据
     */
    String ALERTS_EXPORT_SUMMARY_DATA = ALERTS_EXPORT + "/summary/data";
    /**
     * url 路径 询问可疑人员确认表
     */
    String ALERTS_EXPORT_QUESTIONED = ALERTS_EXPORT + "/question_suspect";
    /**
     * url 路径 询问可疑人员确认表数据
     */
    String ALERTS_EXPORT_QUESTIONED_DATA = ALERTS_EXPORT + "/question_suspect/data";
    /**
     * url 路径 保安出警记录表
     */
    String ALERTS_EXPORT_GUARD_PROCESSED = ALERTS_EXPORT + "/guard_processed";
    /**
     * url 路径 保安出警记录表数据
     */
    String ALERTS_EXPORT_PROCESSED_DATA = ALERTS_EXPORT + "/guard_processed/data";

    /**
     * url 路径 警情统计
     */
    String ALERTS_STATISTICS = ALERTS + "/statistics";
    /**
     * url 路径 平均数据
     */
    String ALERTS_AVERAGE = ALERTS_STATISTICS + "/average";
    /**
     * url 路径 图表数据
     */
    String ALERTS_DIAGRAM = ALERTS_STATISTICS + "/diagram";

    /**
     * url 路径 警情确认
     */
    String ALERTS_CONFIRM = ALERTS + "/confirm";
    /**
     * url 路径 保安回电
     */
    String ALERTS_CALLBACK = ALERTS + "/callback";
    /**
     * url 路径 保安二次回电
     */
    String ALERTS_SECOND_CALLBACK = ALERTS + "/second_callback";

    /**
     * url 路径 警情日志
     */
    String ALERTS_LOG = ALERTS + "/{id}/logs";
    /**
     * url 路径 警情基础信息
     */
    String ALERTS_BASE = ALERTS + "/{id}/base";
    /**
     * url 路径 单条警情的统计信息
     */
    String ALERTS_ID_STATISTICS = ALERTS + "/{id}/statistics";
    /**
     * url 路径 警情 ID
     */
    String ALERTS_ID = ALERTS + "/{id}";
    /**
     * url 路径 department search
     */
    String ALERTS_DEPARTMENT_SUGGEST = ALERTS + "/department/suggest";
    /**
     * url 路径 警情附加信息
     */
    String ALERTS_EXTEND = ALERTS + "/{id}/extend";
    /**
     * url 路径 获取警情资源列表
     */
    String ALERTS_RESOURCES = ALERTS + "/{id}/resources";
    /**
     * url 路径 访问单个资源
     */
    String ALERTS_RESOURCE = ALERTS + "/{id}/resource";
    /**
     * url 路径
     */
    String ALERTS_RESOURCE_ID = ALERTS + "/{id}/resource/{resourceId}";
    /**
     * url 路径 警情资源上传
     */
    String ALERTS_UPLOAD = ALERTS + "/{id}/upload";
    /**
     * url 路径 附近的保安
     */
    String ALERTS_NEARBY_GUARDS = ALERTS + "/{id}/nearby_guards";

    /**
     * url 路径 保安
     */
    String GUARDS = "guards";
    /**
     * url 路径 保安导出
     */
    String GUARDS_EXPORT = GUARDS + "/export";
    /**
     * url 路径 添加保安
     */
    String GUARDS_ADD = GUARDS + "/add";
    /**
     * 搜索保安
     */
    String GUARDS_SEARCH = GUARDS + "/search";
    /**
     * url 路径 删除保安
     */
    String GUARDS_DELETE = GUARDS + "/delete";
    /**
     * url 路径 更新保安
     */
    String GUARDS_UPDATE = GUARDS + "/update";
    /**
     * url 路径 保安 id
     */
    String GUARDS_ID = GUARDS + "/{id}";
    /**
     * url 路径 修改保安分组
     */
    String GUARDS_ID_CHANGE_GROUP = GUARDS + "/{id}/change_group/{group_id}";

    /**
     * url 路径 保安组
     */
    String GUARDS_GROUPS = GUARDS + "/groups";
    /**
     * url 路径 保安组
     */
    String GUARDS_GROUPS_GROUP = GUARDS_GROUPS + "/group";
    String GUARDS_GROUPS_DELETE = GUARDS_GROUPS + "/delete";
    /**
     * url 路径 搜索保安组
     */
    String GUARDS_GROUPS_SUGGEST = GUARDS_GROUPS + "/suggest";
    /**
     * url 路径 保安组 id
     */
    String GUARDS_GROUPS_ID = GUARDS_GROUPS + "/{id}";
    /**
     * url 路径 获取绑定保安组的部门
     */
    String GROUPS_ID_DEPARTMENTS = GUARDS_GROUPS_ID + "/departments";

    /**
     * url 路径
     */
    String GROUPS_ADD_GUARD = GUARDS_GROUPS_ID + "/add_guard";
    /**
     * url 路径 保安组添加
     */
    String GUARDS_GROUPS_ADD = GUARDS_GROUPS + "/add";
    /**
     * url 路径 绑定多个社区
     */
    String GUARDS_GROUPS_BIND = GUARDS_GROUPS + "/{id}/bind";
    /**
     * url 路径 保安组警情
     */
    String GUARDS_GROUPS_ALERTS= GUARDS_GROUPS + "/{id}/alerts";
    /**
     * url 路径 保安组警情统计
     */
    String GUARDS_GROUPS_STATISTICS= GUARDS_GROUPS_ALERTS + "/statistics";

    /**
     * url 路径 保安组警情统计平均数据
     */
    String GUARDS_GROUPS_AVERAGE= GUARDS_GROUPS_STATISTICS + "/average";
    /**
     * url 路径 保安组警情统计图表数据
     */
    String GUARDS_GROUPS_DIAGRAM= GUARDS_GROUPS_STATISTICS + "/diagram";
    /**
     * url 路径 未完成警情
     */
    String GUARDS_GROUPS_UNFINISHED= GUARDS_GROUPS_ALERTS + "/unfinished";

    /**
     * url 路径 询问可疑人员
     */
    String GUARDS_GROUPS_QUESTIONED= GUARDS_GROUPS_ALERTS + "/questioned";
    /**
     * url 路径 解绑社区
     */
    String GUARDS_GROUP_UNBIND = GUARDS_GROUPS + "/{id}/unbind/{department_id}";
    /**
     * url 路径 解绑社区
     */
    String GROUP_UNBIND = GUARDS_GROUPS + "/{id}/unbind";
    /**
     * url 路径 绑定单个社区
     */
    String GUARDS_GROUP_BIND = GUARDS_GROUPS + "/{id}/bind/{department_id}";

    String GUARD_ALERTS = GUARDS_ID + "/alerts";
    String GUARD_UNFINISHED = GUARD_ALERTS + "/unfinished";
    String GUARD_QUESTIONED = GUARD_ALERTS + "/questioned";
    String GUARD_STATISTICS = GUARDS_ID + "/statistics";
    String GUARD_CONFIRM_QUESTIONED = GUARDS_ID + "/confirm";


    /**
     * 初始化 userId
     *
     * @param userId 通过 request 获取到的 userId
     * @return userId
     */
    default Long init(Long userId) {
        TUser user = UserUtil.getCurrentUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }
}
