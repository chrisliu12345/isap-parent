package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseAlertDetail;
import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.rest.AlertRestService;
import com.gosun.isap.warn.api.alert.service.AlertService;
import com.gosun.isap.warn.api.alert.service.AlertsCountService;
import com.gosun.isap.warn.api.alert.util.FileUtils;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.aop.CheckAlertPermission;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.AlertConst.*;
import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;

/**
 * <p>创建时间：2017/5/16 19:10</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckAlertPermission
@CheckEmptyParams
public class AlertRestServiceImpl implements AlertRestService {

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertsCountService countService;

    @Override
    @GET
    @Path(ALERTS_LOG)
    public ResponseData<List<String>> getAlertLogs(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        List<String> logs = alertService.getAlertLog(alertId);
        if(ListUtils.isEmpty(logs)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(logs);
    }

    @Override
    @GET
    @Path(ALERTS_BASE)
    public ResponseData<Alert> getBaseAlert(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        Alert alert = alertService.getBaseAlert(alertId);
        return ResponseData.init(alert);
    }

    @Override
    @GET
    @Path(ALERTS_ID_STATISTICS)

    public ResponseData<BaseAlertDetail> getAlertDetail(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        BaseAlertDetail detail = alertService.getAlertDetail(alertId);
        return ResponseData.init(detail);
    }

    @Override
    @GET
    @Path(ALERTS_ID)
    public ResponseData<Map<String, Object>> getAlert(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        Alert alert = alertService.getBaseAlert(alertId);
        BaseAlertDetail detail = alertService.getAlertDetail(alertId);
        List<String> logs = alertService.getAlertLog(alertId);
        List<BaseAlertResource> resources = alertService.getResources(alertId);
        Guard guard = alertService.getAlertGuard(alertId);

        Map<String, Object> map = null;
        if (alert != null) {
            map = new HashMap<>();
            map.put(BASE, alert);
            if (detail != null) {
                map.put(STATISTICS, detail);
            }
            if (logs != null) {
                map.put(LOGS, logs);
            }
            if (!ListUtils.isEmpty(resources)) {
                map.put(RESOURCES, resources);
            }
            if (guard != null) {
                map.put(GUARD, guard);
            }
        }

        return ResponseData.init(map);
    }

    @Override
    @GET
    @Path(ALERTS_EXTEND)
    public ResponseData<Map<String, Object>> getExtend(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        String departmentId = alertService.getDepartmentId(alertId);
        Map<String, Object> map = null;
        if (departmentId != null) {
            map = new HashMap<>();
            Date date = alertService.latestProcessedTime(departmentId);
            int count = countService.currentCountOfProcessed(departmentId);

            if (date != null) {
                map.put(LATEST_ALERT_PROCESS_TIME, date);
            }
            map.put(COUNT_OF_PROCESSED_ALERTS, count);
        }
        return ResponseData.init(map);
    }

    @Override
    @GET
    @Path(ALERTS_RESOURCES)
    public ResponseData<List<BaseAlertResource>> getResources(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        List<BaseAlertResource> list = alertService.getResources(alertId);
        if(ListUtils.isEmpty(list)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(list);
    }

    @Override
    @POST
    @Path(ALERTS_UPLOAD)
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public ResponseData upload(@PathParam(ID) Long alertId, @MultipartForm MultipartFormDataInput input) {
        try {
            alertService.saveUploadResource(alertId, input);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseData.error(AlertError.SAVE_FILE_ERROR);
        }
        return ResponseData.ok();
    }

    @Override
    @GET
    @Path(ALERTS_RESOURCE)
    public Response getResource(@PathParam(ID) Long alertId, @QueryParam(TYPE) Byte type, @QueryParam(INDEX) Byte index, @HeaderParam(USER_ID) Long userId) {
        if (type == null) {
            type = 0;
        }
        if (index == null) {
            index = 0;
        }

        BaseAlertResource resource = alertService.getResource(alertId, type, index);
        if (resource == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        File file = new File(resource.getPath());
        Response.ResponseBuilder builder = Response.ok(file, resource.getContentType());
        String suffix = FileUtils.getFileNameSuffix(resource.getPath());
        if (suffix != null) {
            builder.header(CONTENT_DISPOSITION, String.format("filename=\"id%d-type%d-index%d%s\"", alertId, type, index, suffix));
        }
        return builder.build();
    }

    @Override
    @GET
    @Path(ALERTS_NEARBY_GUARDS)
    public ResponseData<List<Guard>> listNearbyGuards(@PathParam(ID) Long alertId, @HeaderParam(USER_ID) Long userId) {
        List<Guard> guards = alertService.getNearbyGuards(alertId);
        if(ListUtils.isEmpty(guards)){
            return ResponseData.error(AlertError.NO_DATA);
        }
        return ResponseData.init(guards);
    }

}
