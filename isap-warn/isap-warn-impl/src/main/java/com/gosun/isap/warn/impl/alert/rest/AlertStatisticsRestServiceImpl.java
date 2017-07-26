package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.model.statistics.AverageItem;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsDiagramData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.rest.AlertStatisticsRestService;
import com.gosun.isap.warn.api.alert.service.AlertStatisticsService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.aop.OperationLog;
import com.gosun.isap.warn.impl.alert.util.DateParseUtil;
import com.gosun.isap.warn.impl.alert.util.StatisticsHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;
import static com.gosun.isap.warn.impl.alert.aop.OperationLog.RETRIEVE;

/**
 * <p>创建时间：2017-5-22 15:26</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckEmptyParams
public class AlertStatisticsRestServiceImpl implements AlertStatisticsRestService {

    @Override
    @GET
    @Path(ALERTS_STATISTICS)
    public ResponseData<List<StatisticsNode>> getStatisticsItem(
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startStr,
            @QueryParam(END_TIME) String endStr,
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @HeaderParam(USER_ID) Long userId) {
        Date start = getStartTime(startStr, strDate);
        Date end = getEndTime(endStr, strDate);
        userId = init(userId);
        StatisticsHelper helper = StatisticsHelper.getInstance(start, end, departmentId, userId);
        return ResponseData.init(helper.allNode());
    }

    @Override
    @GET
    @Path(ALERTS_AVERAGE)
    public ResponseData<List<AverageItem>> getAverageItem(
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startStr,
            @QueryParam(END_TIME) String endStr,
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @HeaderParam(USER_ID) Long userId) {
        Date start = getStartTime(startStr, strDate);
        Date end = getEndTime(endStr, strDate);
        userId = init(userId);
        StatisticsHelper helper = StatisticsHelper.getInstance(start, end, departmentId, userId);
        List<AverageItem> list = helper.getAverageItems();
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(list);
    }

    @Override
    @GET
    @Path(ALERTS_DIAGRAM)
    public ResponseData<List<StatisticsDiagramData>> getDiagramData(
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startStr,
            @QueryParam(END_TIME) String endStr,
            @QueryParam(DEPARTMENT_ID) String departmentId,
            @HeaderParam(USER_ID) Long userId) {
        Date start = getStartTime(startStr, strDate);
        Date end = getEndTime(endStr, strDate);
        userId = init(userId);
        StatisticsHelper helper = StatisticsHelper.getInstance(start, end, departmentId, userId);
        List<StatisticsDiagramData> list = helper.getDiagramData();
        if (ListUtils.isEmpty(list)) {
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(list);
    }

    private Date getStartTime(String startTime, String strDate) {
        return DateParseUtil.getStartTime(startTime, strDate);
    }

    private Date getEndTime(String endTime, String strDate) {
        return DateParseUtil.getEndTime(endTime, strDate);
    }
}
