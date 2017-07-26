package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.AlertsCountMapper;
import com.gosun.isap.dao.mapper.alert.GuardMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertDetailBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertLogBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertResourceBaseMapper;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.dao.po.alert.base.BaseAlertDetail;
import com.gosun.isap.dao.po.alert.base.BaseAlertLog;
import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.model.ResourceWriter;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertLogType;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertResourceType;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.AlertService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.FileUtils;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.api.alert.util.ResourcePathUtils;
import com.gosun.isap.warn.api.alert.util.helper.AlertLogHelper;
import com.gosun.isap.warn.impl.alert.util.PictureCaptureUtil;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>创建时间：2017/5/3 11:19</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private AlertBaseMapper alertBaseMapper;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertDetailBaseMapper alertDetailBaseMapper;

    @Autowired
    private AlertLogBaseMapper alertLogBaseMapper;

    @Autowired
    private AlertsCountMapper countMapper;

    @Autowired
    private AlertCommonService commonService;

    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    private UserService userService;

    @Autowired
    private AlertResourceBaseMapper resourceBaseMapper;

    @Autowired
    private GuardMapper guardMapper;

    @Autowired
    private PictureCaptureUtil pictureCaptureUtil;

    @Override
    public long createAlert(String deviceId, String description) {
        return createAlert(deviceId, description, (byte) 0);
    }

    @Override
    public long createAlert(String deviceId, String description, byte reason) {
        BaseAlert alert = new BaseAlert();

        // 设置基础信息
        alert.setDevId(deviceId);
        alert.setDepartmentId(commonService.getDeviceDepartment(deviceId));
        alert.setDescription(description);


        // 保存
        int row = alertBaseMapper.insertSelective(alert);
        if (row < 0) {
            return 0L;
        }

        // 截图保存
        pictureCaptureUtil.capturePicture(alert.getId(), deviceId, AlertResourceType.GENERATE_PICTURE.getType());

        return alert.getId();
    }

    @Override
    public int getCountOfUnprocessedAlerts(long userId, BaseAlert alert) {
        if (alert == null) {
            return 0;
        }

        List<String> departments = userService.getCommunities(userId);
        if (ListUtils.isEmpty(departments)) {
            return 0;
        }

        Date addTime = alert.getAddTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addTime);
        calendar.add(Calendar.MILLISECOND, -AlertConst.PROCESSED_IN_TIME_MILLISECOND);
        Date start = calendar.getTime();
        return countMapper.countUnprocessedAlerts(start, addTime, departments);
    }

    @Override
    public Alert getBaseAlert(long id) {
        return alertMapper.selectAlertById(id);
    }

    @Override
    public BaseAlertDetail getAlertDetail(long id) {
        return alertDetailBaseMapper.selectFirstByColumn(BaseAlertDetail.ALERT_ID, id);
    }

    @Override
    public List<String> getAlertLog(long id) {
        List<BaseAlertLog> list = alertLogBaseMapper.selectByColumn(BaseAlertLog.ALERT_ID, id, null);
        return AlertLogHelper.convert(list);
    }

    @Override
    public Map<String, Object> getAlert(long id) {
        return null;
    }

    @Override
    public boolean generateAlertDetail(long alertId) {
        BaseAlert alert = alertBaseMapper.selectByPrimaryKey(alertId);
        if (alert == null) {
            return false;
        }

        // 初始化日志
        List<BaseAlertLog> logs = alertLogBaseMapper.selectByColumn(BaseAlertLog.ALERT_ID, alert.getId(), null);
        BaseAlertLog notifyGuard = AlertLogHelper.getLogByStatus(logs, AlertLogType.CALL_GUARD_SUCCESS);
        BaseAlertLog guardArrive = AlertLogHelper.getLogByStatus(logs, AlertLogType.GUARDS_ARRIVED);
        BaseAlertLog finished = AlertLogHelper.getFinishedLog(logs);
        BaseAlertLog noCallback = AlertLogHelper.getLogByStatus(logs, AlertLogType.NOT_CALLBACK);
        BaseAlertLog callbackAgain = AlertLogHelper.getLogByStatus(logs, AlertLogType.GUARDS_CALLBACK_AGAIN);
        BaseAlertLog questionSuspect = AlertLogHelper.getLogByStatus(logs, AlertLogType.GUARDS_QUESTIONED_SUSPECT);

        // 统计时间
        int userResponseTime = getSecond(alert.getAddTime(), alert.getConfirmStartTime());
        int userProcessTime = getSecond(alert.getAddTime(), alert.getConfirmEndTime());
        int guardResponseTime = 0;
        if (notifyGuard != null && guardArrive != null) {
            guardResponseTime = getSecond(notifyGuard.getAddTime(), guardArrive.getAddTime());
        }
        int guardProcessTime = guardResponseTime;
        if (notifyGuard != null && callbackAgain != null) {
            guardProcessTime = getSecond(notifyGuard.getAddTime(), callbackAgain.getAddTime());
        }
        int totalTime = 0;
        if (finished != null) {
            totalTime = getSecond(alert.getAddTime(), finished.getAddTime());
        }

        // 保安处理超时判断
        Integer limit = commonService.getLimitTime(alert.getDevId());
        if (limit != null && guardResponseTime > limit) {
            int row = alertMapper.updateStatus(alertId, AlertStatus.FAILED_GUARD_OVER_TIME.getStatus());
            if (row < 1) {
                return false;
            }
        }

        // 统计数量
        int falseAlerts = countMapper.countFalseInResponseTime(alert.getAddTime(), alert.getConfirmEndTime(), alert.getUserId());
        int confirmAlerts = alert.getConfirmedAlerts();
        int unprocessed = alert.getUnprocessedAlerts();

        // 初始化数据
        BaseAlertDetail detail = alertDetailBaseMapper.selectFirstByColumn(BaseAlertDetail.ALERT_ID, alertId);
        boolean detailExist = true;
        if (detail == null) {
            detail = new BaseAlertDetail();
            detailExist = false;
        }

        detail.setAlertId(alertId);
        detail.setUserProcessTime(userProcessTime);
        detail.setUserResponseTime(userResponseTime);
        detail.setGuardProcessTime(guardProcessTime);
        detail.setGuardResponseTime(guardResponseTime);
        detail.setTotalTime(totalTime);
        detail.setIsCallBack(noCallback == null);
        detail.setIsArrived(true);
        detail.setIsSecondCallBack(callbackAgain != null);
        detail.setIsQuestionSuspect(questionSuspect != null);
        detail.setFailedReasonType(alert.getStatus());
        AlertStatus status = AlertStatus.get(alert.getStatus());
        if (status != null) {
            detail.setFailedReason(status.getDescription());
        }

        // 更新或插入
        int row = 0;
        if (detailExist) {
            row = alertDetailBaseMapper.updateByPrimaryKeySelective(detail);
        } else {
            row = alertDetailBaseMapper.insertSelective(detail);
        }
        return row > 0;
    }

    @Override
    public Date latestProcessedTime(String departmentId) {
        if (departmentId == null) {
            return null;
        }
        return alertMapper.latestProcessedTime(departmentId);
    }

    @Override
    public List<BaseAlertResource> getResources(long alertId) {
        return alertMapper.getResources(alertId);
    }

    @Override
    public String getDeviceId(long alertId) {
        return commonService.getAlertDeviceId(alertId);
    }

    @Override
    public String getDepartmentId(long alertId) {
        return commonService.getAlertDepartmentId(alertId);
    }

    @Override
    public boolean saveUploadResource(long alertId, MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> map = input.getFormDataMap();
        List<InputPart> parts = map.get(AlertConst.FORM_RESOURCE_FILES);
        List<InputPart> types = map.get(AlertConst.FORM_RESOURCE_TYPE);
        byte type = 0;
        if (types != null) {
            InputPart part = types.get(0);
            try {
                type = Byte.parseByte(part.getBodyAsString());
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
        Integer index = alertMapper.getResourceIndex(alertId, type);
        int count = 0;
        if (index != null) {
            count = index + 1;
        }
        for (InputPart imagePart : parts) {
            InputStream inputStream = imagePart.getBody(InputStream.class, null);
            String contentDisposition = imagePart.getHeaders().getFirst(AlertConst.CONTENT_DISPOSITION);
            String suffix = FileUtils.getSuffixFromDisposition(contentDisposition);
            if (suffix == null) {
                suffix = "." + imagePart.getMediaType().getSubtype();
            }
            String name = UUID.randomUUID().toString() + suffix;
            File file = ResourcePathUtils.createNewFile(name);
            ResourceWriter writer = new ResourceWriter(inputStream, file);
            writer.write();
            BaseAlertResource resource = new BaseAlertResource();
            resource.setPath(file.getAbsolutePath());
            resource.setResourceType(type);
            resource.setAlertId(alertId);
            resource.setContentType(imagePart.getMediaType().toString());
            resource.setResourceIndex((byte) count);
            try {
                resourceBaseMapper.insertSelective(resource);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }
        return true;
    }

    @Override
    public boolean saveResource(long alertId, int type, String path, String url) {
        Integer index = alertMapper.getResourceIndex(alertId, (byte) type);
        if (index != null) {
            index = index + 1;
        } else {
            index = 0;
        }
        BaseAlertResource resource = new BaseAlertResource();
        resource.setPath(path);
        resource.setResourceType((byte) type);
        resource.setAlertId(alertId);
        resource.setContentType("image/jpg");
        resource.setResourceIndex(index.byteValue());
        try {
            int row = resourceBaseMapper.insertSelective(resource);
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BaseAlertResource getResource(long alertId, byte type, byte index) {
        Map<String, Object> map = new HashMap<>();
        map.put(BaseAlertResource.ALERT_ID, alertId);
        map.put(BaseAlertResource.RESOURCE_TYPE, type);
        map.put(BaseAlertResource.RESOURCE_INDEX, index);

        return resourceBaseMapper.selectFirstByColumns(map);
    }

    @Override
    public BaseAlertResource getResourceById(long id) {
        return resourceBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Guard> getNearbyGuards(Long alertId) {
        String department = commonService.getAlertDepartmentId(alertId);
        List<Long> groups = guardMapper.getGroupIdByDepartment(department);
        return guardMapper.listGuardsByGroups(groups,null, null, null);
    }

    @Override
    public Guard getAlertGuard(Long alertId) {
        if (alertId == null) {
            return null;
        }
        return guardMapper.getGuardByAlertId(alertId);
    }


    /**
     * 将时间段转换为秒数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 秒数
     */
    private int getSecond(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / AlertConst.SECOND_TO_MILLISECOND);
    }
}
