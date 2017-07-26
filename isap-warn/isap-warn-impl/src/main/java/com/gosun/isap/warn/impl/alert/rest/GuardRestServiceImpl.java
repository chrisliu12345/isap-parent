package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.rest.GuardRestService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.GuardService;
import com.gosun.isap.warn.api.alert.util.DateUtils;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.util.DateParseUtil;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;

/**
 * <p>创建时间：2017-6-8 17:07</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckEmptyParams
public class GuardRestServiceImpl implements GuardRestService {
    @Autowired
    @Qualifier(AlertServiceId.GUARD_SERVICE)
    private GuardService guardService;

    @Override
    @GET
    @Path(GUARD_ALERTS)
    public ResponseData<List<Alert>> alerts(
            @PathParam(ID) Long guardId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startTime,
            @QueryParam(END_TIME) String endTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startDate = getStartTime(startTime, strDate);
        Date endDate = getEndTime(endTime, strDate);
        List<Alert> list = guardService.listGuardAlerts(guardId, startDate, endDate, null, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = guardService.countGuardAlerts(guardId, startDate, endDate);
        return ResponseData.init(list, count);
    }

    @Override
    @GET
    @Path(GUARD_UNFINISHED)
    public ResponseData<List<Map<String, Object>>> unfinished(
            @PathParam(ID) Long guardId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startTime,
            @QueryParam(END_TIME) String endTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startDate = getStartTime(startTime, strDate);
        Date endDate = getEndTime(endTime, strDate);
        List<Map<String, Object>> list = guardService.unfinishedAlerts(guardId, startDate, endDate, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }

        for (Map<String, Object> map : list) {
            Integer status = null;
            try {
                status = (Integer) map.get("status");
            } catch (Exception ignored) {
            }
            if(status != null && status.byteValue() == AlertStatus.FAILED_GUARD_OVER_TIME.getStatus()){
                map.put("failedReason","保安处理超时");
            }
        }

        int count = guardService.countUnfinishedAlerts(guardId, startDate, endDate);
        return ResponseData.init(list, count);
    }

    @Override
    @GET
    @Path(GUARD_QUESTIONED)
    public ResponseData<List<Map<String, Object>>> questionedSuspect(
            @PathParam(ID) Long guardId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startTime,
            @QueryParam(END_TIME) String endTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startDate = getStartTime(startTime, strDate);
        Date endDate = getEndTime(endTime, strDate);
        List<Map<String, Object>> list = guardService.questionedConfirm(guardId, startDate, endDate, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = guardService.countQuestionedConfirm(guardId, startDate, endDate);
        return ResponseData.init(list, count);
    }


    @Override
    @GET
    @Path(GUARD_STATISTICS)
    public ResponseData<List<StatisticsNode>> statistic(
            @PathParam(ID) Long guardId,
            @QueryParam(START_TIME) String startDate,
            @QueryParam(END_TIME) String endDate,
            @HeaderParam(USER_ID) Long userId) {
        Date start = DateUtils.parseDate(startDate);
        Date end = DateUtils.parseDate(endDate);
        List<StatisticsNode> list = guardService.statistics(guardId, start, end);
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(list);
    }

    @Override
    @POST
    @Path(GUARD_CONFIRM_QUESTIONED)
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public ResponseData confirmQuestionSuspect(@PathParam(ID) Long guardId, MultipartFormDataInput input, @HeaderParam(USER_ID) Long userId) {
        ResponseData responseData = new ResponseData();
        try {
            if (guardService.saveUploadResource(guardId, input)) {
                return responseData;
            }
            responseData.setError(AlertError.CONFIRM_QUESTIONED_FAILED);
        } catch (IOException e) {
            e.printStackTrace();
            responseData.setError(AlertError.SAVE_FILE_ERROR);
        }
        return responseData;
    }

    private Date getStartTime(String startTime, String strDate) {
        return DateParseUtil.getStartTime(startTime, strDate);
    }

    private Date getEndTime(String endTime, String strDate) {
        return DateParseUtil.getEndTime(endTime, strDate);
    }
}
