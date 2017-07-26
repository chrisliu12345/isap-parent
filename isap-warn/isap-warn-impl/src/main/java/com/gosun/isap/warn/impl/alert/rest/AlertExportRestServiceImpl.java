package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.rest.AlertExportRestService;
import com.gosun.isap.warn.api.alert.service.AlertExportService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.aop.CheckListMenuPermission;
import com.gosun.isap.warn.impl.alert.aop.OperationLog;
import com.gosun.isap.warn.impl.alert.export.converter.AlertDetailLogConverter;
import com.gosun.isap.warn.impl.alert.export.converter.AlertLogConverter;
import com.gosun.isap.warn.impl.alert.export.converter.GuardProcessedConverter;
import com.gosun.isap.warn.impl.alert.export.converter.UnfinishedConverter;
import com.gosun.isap.warn.impl.alert.export.excel.ExcelStreamingOutput;
import com.gosun.isap.warn.impl.alert.export.freemarker.FreemarkerStreamingOutput;
import com.gosun.isap.warn.impl.alert.export.base.ExportStreamingOutput;
import com.gosun.isap.warn.impl.alert.export.word.WordStreamingOutput;
import com.gosun.isap.warn.impl.alert.util.DateParseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;
import static com.gosun.isap.warn.impl.alert.aop.OperationLog.RETRIEVE;
import static com.gosun.isap.warn.impl.alert.aop.OperationLog.TABLE_EXPORT;
/**
 * <p>创建时间：2017-5-18 19:11</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckEmptyParams
@CheckListMenuPermission
public class AlertExportRestServiceImpl implements AlertExportRestService {
    private static final String ALERT_LOG_SETTING = "alert/setting/alert_log_setting.xml";
    private static final String ALERT_DETAIL_LOG_SETTING = "alert/setting/alert_detail_log_setting.xml";
    private static final String UNFINISHED_ALERTS_SETTING = "alert/setting/unfinished_alerts_setting.xml";
    private static final String ALERTS_SUMMARY_SETTING = "alert/setting/alerts_summary_setting.xml";
    private static final String ALERTS_QUESTIONED_SETTING = "alert/setting/confirm_question_suspect_setting.xml";

    private static final String ALERT_LOG_NAME = "出警记录表";
    private static final String ALERT_DETAIL_LOG_NAME = "出警明细表";
    private static final String UNFINISHED_ALERTS_NAME = "未完成出警统计表";
    private static final String ALERTS_QUESTION_SUSPECT = "可疑人员盘查确认表";
    private static final String ALERTS_SUMMARY_NAME = "警情汇总表";
    private static final String GUARD_PROCESSED_NAME = "警情处置表";

    private static final String GUARD_STATING_TIME = "guardStartingTime";

    @Autowired
    private AlertExportService exportService;

    @Override
    @GET
    @Path(ALERTS_EXPORT_LOG)
    @SysOperateLog(description = ALERT_LOG_NAME,serviceType = TABLE_EXPORT,operateType = RETRIEVE)
    public Response exportAlertsLog(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.alertLogBody(departmentId, userId, startTime, endTime, null);
        AlertLogConverter convert = new AlertLogConverter();
        convert.convert(list);
        Map<String, Object> header = exportService.alertLogHeader(departmentId, userId, startTime, endTime);
        if (header != null && !ListUtils.isEmpty(list)) {
            Map<String, Object> map = list.get(0);
            header.put(AlertExportService.DATE, map.get(GUARD_STATING_TIME));
        }

        ExportStreamingOutput output = new WordStreamingOutput(ALERT_LOG_SETTING, header, list, ALERT_LOG_NAME);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_LOG_DATA)
    public ResponseData<List<Map<String, Object>>> alertsLog(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.alertLogBody(departmentId, userId, startTime, endTime, SqlLimit.init(start, limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        AlertLogConverter convert = new AlertLogConverter();
        convert.convert(list);
        int total = exportService.countAlertLog(departmentId, userId, startTime, endTime);
        return ResponseData.init(list, total);
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_DETAIL)
    @SysOperateLog(description = ALERT_DETAIL_LOG_NAME, serviceType =TABLE_EXPORT,operateType = RETRIEVE)
    public Response exportAlertsDetailLog(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.alertDetailLogBody(departmentId, userId, startTime, endTime, null);
        AlertDetailLogConverter convert = new AlertDetailLogConverter();
        convert.convert(list);
        ExportStreamingOutput output = new ExcelStreamingOutput(ALERT_DETAIL_LOG_SETTING, null,
                list, ALERT_DETAIL_LOG_NAME);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_DETAIL_DATA)
    public ResponseData<List<Map<String, Object>>> alertsDetailLog(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.alertDetailLogBody(departmentId, userId, startTime, endTime, SqlLimit.init(start, limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        AlertDetailLogConverter convert = new AlertDetailLogConverter();
        convert.convert(list);
        int total = exportService.countAlertDetailLog(departmentId, userId, startTime, endTime);
        return ResponseData.init(list, total);
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_UNFINISHED)
    @SysOperateLog(description = UNFINISHED_ALERTS_NAME,serviceType =TABLE_EXPORT,operateType = RETRIEVE)
    public Response exportUnfinishedAlerts(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.getUnfinishedAlertsBody(departmentId, userId, startTime, endTime, null);
        UnfinishedConverter convert = new UnfinishedConverter();
        convert.convert(list);
        Map<String, Object> header = exportService.getUnfinishedAlertsHeader(departmentId, userId, startTime, endTime);
        if (header != null) {
            header.put(DATE, startTime);
        }

        ExportStreamingOutput output = new ExcelStreamingOutput(UNFINISHED_ALERTS_SETTING, header, list,
                UNFINISHED_ALERTS_NAME);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_UNFINISHED_DATA)
    public ResponseData<List<Map<String, Object>>> unfinishedAlerts(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.getUnfinishedAlertsBody(departmentId, userId, startTime, endTime, SqlLimit.init(start, limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        UnfinishedConverter convert = new UnfinishedConverter();
        convert.convert(list);
        int total = exportService.countUnfinishedAlerts(departmentId, userId, startTime, endTime);
        return ResponseData.init(list, total);
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_SUMMARY)
    @SysOperateLog(description =ALERTS_SUMMARY_NAME ,serviceType =TABLE_EXPORT,operateType = RETRIEVE)
    public Response exportAlertsSummary(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.getAlertsSummary(departmentId, userId, startTime, endTime, null);
        ExportStreamingOutput output = new ExcelStreamingOutput(ALERTS_SUMMARY_SETTING, null, list,
                ALERTS_SUMMARY_NAME);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_SUMMARY_DATA)
    public ResponseData<List<Map<String, Object>>> alertsSummary(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.getAlertsSummary(departmentId, userId, startTime, endTime, SqlLimit.init(start, limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        int total = exportService.countSummary(departmentId, userId, startTime, endTime);
        return ResponseData.init(list, total);
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_QUESTIONED)
    @SysOperateLog(description = ALERTS_QUESTION_SUSPECT,serviceType =TABLE_EXPORT,operateType = RETRIEVE)
    public Response exportConfirmQuestionSuspect(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.confirmQuestionSuspect(departmentId, userId, startTime, endTime, null);
        ExportStreamingOutput output = new ExcelStreamingOutput(ALERTS_QUESTIONED_SETTING, null,
                list, ALERTS_QUESTION_SUSPECT);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_QUESTIONED_DATA)
    public ResponseData<List<Map<String, Object>>> confirmQuestionSuspect(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.confirmQuestionSuspect(departmentId, userId, startTime, endTime, SqlLimit.init(start, limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        int total = exportService.countConfirmQuestionSuspect(departmentId, userId, startTime, endTime);
        return ResponseData.init(list, total);
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_GUARD_PROCESSED)
    @SysOperateLog(description = GUARD_PROCESSED_NAME,serviceType =TABLE_EXPORT,operateType = RETRIEVE)
    public Response exportGuardProcessedAlerts(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.guardProcessedAlerts(departmentId, userId, startTime, endTime, null);
        Map<String, Object> header = exportService.guardProcessedAlertsHeader(departmentId, userId, startTime, endTime);
        GuardProcessedConverter converter = new GuardProcessedConverter();
        converter.setDocument();
        converter.convert(list);
        ExportStreamingOutput output = new FreemarkerStreamingOutput(list,header, GUARD_PROCESSED_NAME);

        return Response.ok(output, output.getMediaType())
                .header(ExportStreamingOutput.CONTENT_DISPOSITION, output.getContentDisposition())
                .build();
    }

    @Override
    @GET
    @Path(ALERTS_EXPORT_PROCESSED_DATA)
    public ResponseData<List<Map<String, Object>>> guardProcessedAlerts(
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String strStartTime,
            @QueryParam(END_TIME) String strEndTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = getStartTime(strStartTime, strDate);
        Date endTime = getEndTime(strEndTime, strDate);
        userId = init(userId);
        List<Map<String, Object>> list = exportService.guardProcessedAlerts(departmentId, userId, startTime, endTime, SqlLimit.init(start, limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        GuardProcessedConverter converter = new GuardProcessedConverter();
        converter.convert(list);
        int total = exportService.countProcessedAlerts(departmentId, userId, startTime, endTime);
        return ResponseData.init(list, total);
    }

    private Date getStartTime(String startTime, String strDate) {
        return DateParseUtil.getStartTime(startTime,strDate);
    }

    private Date getEndTime(String endTime, String strDate) {
        return DateParseUtil.getEndTime(endTime,strDate);
    }
    
}
