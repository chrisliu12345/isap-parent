package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.warn.api.alert.model.ResponseData;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * 表格导出服务。
 * <p>创建时间：2017-5-18 19:09</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertExportRestService extends RestConst {
    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param userId       用户 id
     * @return response
     * @api {get} /alerts/export/log 警情记录表导出
     * @apiVersion 0.0.1
     * @apiDescription 导出警情记录表 word 文档，访问链接直接下载文档
     * @apiGroup alerts/export
     * @apiParam {String} [department_id] 部门 id
     * @apiParam {String} [date=当天日期] 日期
     * @apiParam {String} [start_time] 开始时间
     * @apiParam {String} [end_time] 截止时间
     */
    Response exportAlertsLog(String departmentId, String date, String strStartTime, String strEndTime, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param start        分页选项
     * @param limit        分页选项
     * @param userId       用户 id
     * @return 警情记录表数据
     * @api {get} /alerts/export/log/data 警情记录表数据
     * @apiVersion 0.0.1
     * @apiDescription 获取警情记录表数据
     * @apiGroup alerts/export
     * @apiParam {String} [department_id] 部门 id
     * @apiParam {String} [date=当天日期] 日期
     * @apiParam {String} [start_time] 开始时间
     * @apiParam {String} [end_time] 截止时间
     * @apiParam {number} [start] 从第几条开始
     * @apiParam {number} [limit] 查询条数
     * @apiSuccessExample success response
     * {
     * "extend": {
     * "totalNum": 45,
     * "rowNum": 2
     * },
     * "resthead": {
     * "errorCode": 0,
     * "message": "OK"
     * },
     * "restbody": [
     * {
     * "alertStatus": 21,
     * "departmentName": "绿地玫瑰城",
     * "alertType": 10,
     * "addTime": "2017-05-26 10:44:13",
     * "isFailed": "×",
     * "guardInfo": "张保安(17014235121)",
     * "guardStartingTime": "2017-05-26 10:44:13",
     * "whereAndWhat": "绿地玫瑰城发现可疑人员",
     * "callbackTime": "2017-05-26 10:44:13",
     * "callbackContent": "道路积水无法通过　",
     * "isQuestionSuspect": null,
     * "id": 3,
     * "isGuardOverTime": null,
     * "failedReason": "道路积水无法通过"
     * },
     * {
     * "alertStatus": 10,
     * "departmentName": "绿地玫瑰城",
     * "alertType": 10,
     * "callbackAgainTime": "2017-05-26 10:44:15",
     * "addTime": "2017-05-26 10:44:14",
     * "isCallbackAgain": true,
     * "guardInfo": "张保安(17014235121)",
     * "guardStartingTime": "2017-05-26 10:44:15",
     * "isArrived": "√",
     * "whereAndWhat": "绿地玫瑰城发现可疑人员",
     * "callbackTime": "2017-05-26 10:44:15",
     * "callbackContent": "到达指定地点　未找到可疑人员",
     * "isQuestionSuspect": null,
     * "id": 6,
     * "isCallback": true,
     * "isGuardOverTime": null
     * }
     * ]
     * }
     * @apiSuccess (Success) {time} guardStartingTime 出警时间
     * @apiSuccess (Success) {String} whereAndWhat 出警地区及事件内容
     * @apiSuccess (Success) {String} guardInfo 保安姓名与电话
     * @apiSuccess (Success) {time} callbackTime 首次回电时间
     * @apiSuccess (Success) {time} callbackAgainTime 二次回电时间
     * @apiSuccess (Success) {String} isArrived 到达指定地点，到达的为‘√’
     * @apiSuccess (Success) {String} callbackContent 回电内容
     * @apiSuccess (Success) {String} isQuestionSuspect 已盘问可疑人员，已盘问的为‘√’
     * @apiSuccess (Success) {String} isGuardOverTime 首次回电超时，超时的为‘×’
     * @apiSuccess (Success) {String} failedReason 未完成出警原因
     */
    ResponseData<List<Map<String, Object>>> alertsLog(String departmentId, String date, String strStartTime, String strEndTime, Integer start, Integer limit, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param userId       用户 id
     * @return 警情详情表
     */
    Response exportAlertsDetailLog(String departmentId, String date, String strStartTime, String strEndTime, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param start        分页选项
     * @param limit        分页选项
     * @param userId       用户 id
     * @return 警情详情表数据
     */
    ResponseData<List<Map<String, Object>>> alertsDetailLog(String departmentId, String date, String strStartTime, String strEndTime, Integer start, Integer limit, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param userId       用户 id
     * @return 未完成出警记录
     */
    Response exportUnfinishedAlerts(String departmentId, String date, String strStartTime, String strEndTime, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param start        分页选项
     * @param limit        分页选项
     * @param userId       用户 id
     * @return 未完成出警记录数据
     */
    ResponseData<List<Map<String, Object>>> unfinishedAlerts(String departmentId, String date, String strStartTime, String strEndTime, Integer start, Integer limit, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param userId       用户 id
     * @return 警情汇总表
     */
    Response exportAlertsSummary(String departmentId, String date, String strStartTime, String strEndTime, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param start        分页选项
     * @param limit        分页选项
     * @param userId       用户 id
     * @return 警情汇总数据
     */
    ResponseData<List<Map<String, Object>>> alertsSummary(String departmentId, String date, String strStartTime, String strEndTime, Integer start, Integer limit, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param userId       用户 id
     * @return 可疑人员盘查确认表
     */
    Response exportConfirmQuestionSuspect(String departmentId, String date, String strStartTime, String strEndTime, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param start        分页选项
     * @param limit        分页选项
     * @param userId       用户 id
     * @return 可疑人员盘查确认数据
     */
    ResponseData<List<Map<String, Object>>> confirmQuestionSuspect(String departmentId, String date, String strStartTime, String strEndTime, Integer start, Integer limit, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param userId       用户 id
     * @return 保安出警记录表
     */
    Response exportGuardProcessedAlerts(String departmentId, String date, String strStartTime, String strEndTime, Long userId);

    /**
     * @param departmentId 部门 id
     * @param date         日期
     * @param strStartTime 开始时间
     * @param strEndTime   截止时间
     * @param start        分页选项
     * @param limit        分页选项
     * @param userId       用户 id
     * @return 保安出警记录数据
     */
    ResponseData<List<Map<String, Object>>> guardProcessedAlerts(String departmentId, String date, String strStartTime, String strEndTime, Integer start, Integer limit, Long userId);
}
