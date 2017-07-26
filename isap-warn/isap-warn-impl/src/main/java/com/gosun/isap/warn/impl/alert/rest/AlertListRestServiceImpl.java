package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.mapper.alert.Interval;
import com.gosun.isap.dao.mapper.alert.OrderByBuilder;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.rest.AlertListRestService;
import com.gosun.isap.warn.api.alert.service.AlertListService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.aop.CheckListMenuPermission;
import com.gosun.isap.warn.impl.alert.aop.CheckProcessMenuPermission;
import com.gosun.isap.warn.impl.alert.aop.DataExport;
import com.gosun.isap.warn.impl.alert.util.DateParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;

/**
 * <p>创建时间：2017/5/13 15:39</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@DataExport
@CheckEmptyParams
public class AlertListRestServiceImpl implements AlertListRestService {
    @Autowired
    private AlertListService alertListService;
    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    private UserService userService;

    @Override
    @GET
    @Path(ALERTS_UNPROCESSED)
    @CheckProcessMenuPermission
    public ResponseData<List<Alert>> getUnprocessedAlerts(@QueryParam(START) Integer start, @QueryParam(LIMIT) Integer limit, @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        List<Alert> list = alertListService.listUnprocessedAlerts(userId, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }

        int count = alertListService.countUnprocessedAlerts(userId);
        return ResponseData.init(list, count);
    }

    @Override
    @GET
    @Path(ALERTS_WAITING)
    @CheckProcessMenuPermission
    public ResponseData<List<Alert>> getWaitingAlerts(
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        int size = 0;
        List<Alert> waitingCallback = alertListService.listWaitingCallbackAlerts(userId, SqlLimit.init(start, limit));
        int countCallback = alertListService.countWaitingCallbackAlerts(userId);
        int countCallbackAgain = alertListService.countWaitingCallbackAgainAlerts(userId);
        int total = countCallback + countCallbackAgain;
        if (waitingCallback != null) {
            size = waitingCallback.size();
        }
        if (limit != null && size >= limit) {
            return ResponseData.init(waitingCallback,total);
        }

        if(start != null){
            start = start - size;
            if(start < 0){
                start = 0;
            }
        }
        if(limit != null){
            limit = limit -size;
            if(limit < 0){
                limit = null;
            }
        }
        List<Alert> waitingCallbackAgain = alertListService.listWaitingCallbackAgainAlerts(userId, SqlLimit.init(start,limit));

        if (ListUtils.isEmpty(waitingCallback)) {
            return ResponseData.init(waitingCallbackAgain,total);
        } else {
            if (!ListUtils.isEmpty(waitingCallbackAgain)) {
                waitingCallback.addAll(waitingCallbackAgain);
            }
            return ResponseData.init(waitingCallback,total);
        }
    }

    @Override
    @GET
    @Path(ALERTS_WAITING_CALLBACK)
    @CheckProcessMenuPermission
    public ResponseData<List<Alert>> getWaitingCallAlerts(
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        List<Alert> waitingCallback = alertListService.listWaitingCallbackAlerts(userId, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(waitingCallback)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = alertListService.countWaitingCallbackAlerts(userId);
        return ResponseData.init(waitingCallback, count);
    }

    @Override
    @GET
    @Path(ALERTS_WAITING_CALLBACK_AGAIN)
    @CheckProcessMenuPermission
    public ResponseData<List<Alert>> getWaitingSecondCallAlerts(@QueryParam(START) Integer start, @QueryParam(LIMIT) Integer limit, @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        List<Alert> waitingCallbackAgain = alertListService.listWaitingCallbackAgainAlerts(userId, SqlLimit.init(start, limit));
        if (ListUtils.isEmpty(waitingCallbackAgain)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = alertListService.countWaitingCallbackAgainAlerts(userId);
        return ResponseData.init(waitingCallbackAgain, count);
    }

    @Override
    @GET
    @Path(ALERTS_FINISHED)
    @CheckProcessMenuPermission
    public ResponseData<List<Alert>> getProcessedAlerts(@QueryParam(START) Integer offset, @QueryParam(LIMIT) Integer size,
                                                        @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        List<Alert> list = alertListService.listFinishedAlerts(userId, null, null, Alert.FINISHED_ORDER_BY, SqlLimit.init(offset, size));
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        Integer total = alertListService.countFinishedAlerts(userId, null, null);

        return ResponseData.init(list, total);
    }

    @Override
    @GET
    @Path(ALERTS)
    @CheckListMenuPermission
    public ResponseData<List<Alert>> getAllAlerts(@QueryParam(DEPARTMENT_ID) String departmentId,
                                                  @QueryParam(DATE) String dateStr,
                                                  @QueryParam(START_TIME) String startTimeStr,
                                                  @QueryParam(END_TIME) String endTimeStr,
                                                  @QueryParam(STATUS) Integer status,
                                                  @QueryParam(STATUS_START) Integer startStatus,
                                                  @QueryParam(STATUS_END) Integer endStatus,
                                                  @QueryParam(SORT) String sort,
                                                  @QueryParam(START) Integer start,
                                                  @QueryParam(LIMIT) Integer limit,
                                                  @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        ResponseData<List<Alert>> responseData = new ResponseData<>();
        List<String> orderBy = OrderByBuilder.parseSortString(sort, Alert::contains);
        List<Alert> list;
        Date startTime = getStartTime(startTimeStr, dateStr);
        Date endTime = getEndTime(endTimeStr, dateStr);
        int count = 0;
        SqlLimit sqlLimit = SqlLimit.init(start, limit);
        Interval<Byte> statusInterval = null;
        if (startStatus != null || endStatus != null) {
            Byte upper = null;
            Byte lower = null;
            if (startStatus != null) {
                lower = startStatus.byteValue();
            }
            if (endStatus != null) {
                upper = endStatus.byteValue();
            }
            statusInterval = new Interval<>(upper, lower);
        }
        if (status != null) {
            list = alertListService.listAlerts(userId, departmentId, status.byteValue(), startTime, endTime, orderBy, sqlLimit);
            count = alertListService.countAlerts(userId, departmentId, status.byteValue(), startTime, endTime);
        } else {
            list = alertListService.listAlerts(userId, departmentId, statusInterval, startTime, endTime, orderBy, sqlLimit);
            count = alertListService.countAlerts(userId, departmentId, statusInterval, startTime, endTime);
        }

        responseData.setData(list);
        responseData.setTotal(count);
        return responseData;
    }

    private Date getStartTime(String startTime, String strDate) {
        return DateParseUtil.getStartTime(startTime, strDate);
    }

    private Date getEndTime(String endTime, String strDate) {
        return DateParseUtil.getEndTime(endTime, strDate);
    }
}
