package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.model.statistics.AverageItem;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsDiagramData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.rest.GuardGroupRestService;
import com.gosun.isap.warn.api.alert.service.AlertListService;
import com.gosun.isap.warn.api.alert.service.AlertsCountService;
import com.gosun.isap.warn.api.alert.service.GuardGroupService;
import com.gosun.isap.warn.api.alert.util.DateUtils;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.util.DateParseUtil;
import com.gosun.isap.warn.impl.alert.util.StatisticsHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;

/**
 * 保安组数据
 * <p>创建时间：2017-6-9 9:11</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckEmptyParams
public class GuardGroupRestServiceImpl implements GuardGroupRestService{
    @Autowired
    private AlertListService listService;

    @Autowired
    private GuardGroupService groupService;

    @Autowired
    private AlertsCountService countService;

    @Override
    @GET
    @Path(GUARDS_GROUPS_ALERTS)
    public ResponseData<List<Alert>> alerts(
            @PathParam(ID) Long groupId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startTime,
            @QueryParam(END_TIME) String endTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startDate = DateParseUtil.getStartTime(startTime,strDate);
        Date endDate = DateParseUtil.getEndTime(endTime,strDate);
        List<String> departments = groupService.listDepartmentIds(groupId);
        if(departments == null){
            return ResponseData.error(AlertError.NO_DATA);
        }
        List<Alert> list = listService.listAlerts(departments,(Byte) null,startDate,endDate,Alert.DEFAULT_ORDER_BY, SqlLimit.init(start,limit));
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = listService.countAlerts(departments,(Byte) null,startDate,endDate);

        return ResponseData.init(list,count);
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_UNFINISHED)
    public ResponseData<List<Map<String,Object>>> unfinished(
            @PathParam(ID) Long groupId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startTime,
            @QueryParam(END_TIME) String endTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startDate = DateParseUtil.getStartTime(startTime,strDate);
        Date endDate = DateParseUtil.getEndTime(endTime,strDate);
        List<Map<String,Object>> list = groupService.unfinishedAlerts(groupId,startDate,endDate,SqlLimit.init(start,limit));
        if(list == null){
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = groupService.countUnfinishedAlerts(groupId,startDate,endDate);
        return ResponseData.init(list,count);
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_QUESTIONED)
    public ResponseData<List<Map<String,Object>>> questionedSuspect(
            @PathParam(ID) Long groupId,
            @QueryParam(DATE) String strDate,
            @QueryParam(START_TIME) String startTime,
            @QueryParam(END_TIME) String endTime,
            @QueryParam(START) Integer start,
            @QueryParam(LIMIT) Integer limit,
            @HeaderParam(USER_ID) Long userId) {
        Date startDate = DateParseUtil.getStartTime(startTime,strDate);
        Date endDate = DateParseUtil.getEndTime(endTime,strDate);
        List<Map<String,Object>> list = groupService.questionedConfirm(groupId,startDate,endDate,SqlLimit.init(start,limit));
        if(list == null){
            return ResponseData.error(AlertError.NO_DATA);
        }
        int count = groupService.countQuestionedConfirm(groupId,startDate,endDate);
        return ResponseData.init(list,count);
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_STATISTICS)
    public ResponseData<List<StatisticsNode>> getStatisticsItem(
            @QueryParam(DATE) String date,
            @QueryParam(START_TIME) String start,
            @QueryParam(END_TIME) String end,
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = DateUtils.parseDate(start);
        Date endTime = DateUtils.parseDate(end);
        List<String> departments = groupService.listDepartmentIds(groupId);
        if(departments == null){
            return ResponseData.error(AlertError.NO_DATA);
        }
        StatisticsHelper helper = StatisticsHelper.getInstance(startTime,endTime,departments);
        return ResponseData.init(helper.allNode());
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_AVERAGE)
    public ResponseData<List<AverageItem>> getAverageItem(
            @QueryParam(DATE) String date,
            @QueryParam(START_TIME) String start,
            @QueryParam(END_TIME) String end,
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = DateUtils.parseDate(start);
        Date endTime = DateUtils.parseDate(end);
        List<String> departments = groupService.listDepartmentIds(groupId);
        if(departments == null){
            return ResponseData.error(AlertError.NO_DATA);
        }
        StatisticsHelper helper = StatisticsHelper.getInstance(startTime,endTime,departments);
        return ResponseData.init(helper.getAverageItems());
    }

    @Override
    @GET
    @Path(GUARDS_GROUPS_DIAGRAM)
    public ResponseData<List<StatisticsDiagramData>> getDiagramData(
            @QueryParam(DATE) String date,
            @QueryParam(START_TIME) String start,
            @QueryParam(END_TIME) String end,
            @PathParam(ID) Long groupId,
            @HeaderParam(USER_ID) Long userId) {
        Date startTime = DateUtils.parseDate(start);
        Date endTime = DateUtils.parseDate(end);
        List<String> departments = groupService.listDepartmentIds(groupId);
        if(departments == null){
            return ResponseData.error(AlertError.NO_DATA);
        }
        StatisticsHelper helper = StatisticsHelper.getInstance(startTime,endTime,departments);
        return ResponseData.init(helper.getDiagramData());
    }
}
