package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.GuardMapper;
import com.gosun.isap.dao.mapper.alert.OrderByBuilder;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.mapper.alert.base.AlertLogBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertResourceBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardAlertBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardBaseMapper;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseAlertLog;
import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.dao.po.alert.base.BaseGuardAlert;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.model.ResourceWriter;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertLogType;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertResourceType;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.GuardService;
import com.gosun.isap.warn.api.alert.util.FileUtils;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.api.alert.util.ResourcePathUtils;
import com.gosun.isap.warn.impl.alert.model.statistics.StatisticsLeafNode;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>创建时间：2017/5/8 19:50</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service(AlertServiceId.GUARD_SERVICE)
public class GuardServiceImpl implements GuardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuardServiceImpl.class);
    private static final String GUARD_INFO = "%s(%s)";
    @Autowired
    private GuardBaseMapper guardBaseMapper;

    @Autowired
    private GuardMapper guardMapper;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertResourceBaseMapper resourceBaseMapper;

    @Autowired
    private GuardAlertBaseMapper guardAlertBaseMapper;

    @Autowired
    private AlertLogBaseMapper logBaseMapper;

    @Override
    public String getGuardInfo(long id) {
        BaseGuard guard = getGuard(id);
        if (guard == null) {
            return null;
        }
        return String.format(GUARD_INFO, guard.getName(), guard.getPhone());
    }

    @Override
    public Guard getGuard(long id) {
        return guardMapper.selectById(id);
    }

    @Override
    public String getGuardName(long id) {
        BaseGuard guard = getGuard(id);
        if (guard == null) {
            return null;
        }
        return guard.getName();
    }

    @Override
    public String getGuardPhone(long id) {
        BaseGuard guard = getGuard(id);
        if (guard == null) {
            return null;
        }
        return guard.getPhone();
    }

    @Override
    public boolean addGuard(BaseGuard guard) {
        BaseGuard guard1 = guardBaseMapper.selectFirstByColumn(BaseGuard.PHONE, guard.getPhone());
        if (guard1 != null) {
            LOGGER.error("addGuard : add guard failed {} is exist", guard.getPhone());
            return false;
        }
        int row = guardBaseMapper.insertSelective(guard);
        return row > 0;
    }

    @Override
    public int addGuards(List<BaseGuard> guards) {
        int count = 0;
        for (BaseGuard guard : guards) {
            if (addGuard(guard)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean updateGuard(BaseGuard guard) {
        if (guard == null || guard.getId() == null) {
            return false;
        }
        int row = guardBaseMapper.updateByPrimaryKeySelective(guard);
        return row > 0;
    }

    @Override
    public boolean deleteGuard(long guardId) {
        return guardMapper.deleteById(guardId) > 0;
    }

    @Override
    public int deleteGuard(List<Long> guardIds) {
        if (ListUtils.isEmpty(guardIds)) {
            return 0;
        }
        return guardMapper.deleteByIds(guardIds);
    }

    @Override
    public List<Guard> listGuards(String keyword,List<String> orderBy, SqlLimit limit) {
        return guardMapper.listGuardsByGroups(null,keyword, orderBy, limit);
    }

    @Override
    public int countGuards(String keyword) {
        return guardMapper.countGuardsByGroups(keyword,null);
    }

    @Override
    public List<Guard> listGuardsByGroup(long id,String keyword, List<String> orderBy, SqlLimit limit) {
        return guardMapper.listGuardsByGroup(id,keyword, orderBy, limit);
    }

    @Override
    public int countGuardsByGroup(long id,String keyword) {
        return guardMapper.countGuardsByGroup(keyword,id);
    }

    @Override
    public List<Guard> listGuardsByGroup(List<Long> groupIds,String keyword, List<String> orderBy, SqlLimit limit) {
        if (ListUtils.isEmpty(groupIds)) {
            return null;
        }
        return guardMapper.listGuardsByGroups(groupIds,keyword, orderBy, limit);
    }

    @Override
    public int countGuardsByGroup(List<Long> ids,String keyword) {
        if (ListUtils.isEmpty(ids)) {
            return 0;
        }
        Integer count = guardMapper.countGuardsByGroups(keyword,ids);
        return count == null ? 0 : count;
    }

    @Override
    public List<Guard> listGuardsByDepartmentId(String id,String keyword, List<String> orderBy, SqlLimit limit) {
        List<Long> groupIds = guardMapper.getGroupIdByDepartment(id);
        if (ListUtils.isEmpty(groupIds)) {
            return null;
        }
        return listGuardsByGroup(groupIds,keyword, orderBy, limit);
    }

    @Override
    public int countGuardsByDepartmentId(String id,String keyword) {
        List<Long> groupIds = guardMapper.getGroupIdByDepartment(id);
        if (ListUtils.isEmpty(groupIds)) {
            return 0;
        }
       return countGuardsByGroup(groupIds,keyword);
    }

    @Override
    public List<Guard> listGuardsByDepartmentId(List<String> ids,String keyword, List<String> orderBy, SqlLimit limit) {
        List<Long> groupIds = guardMapper.getGroupIdByDepartments(ids);
        if (ListUtils.isEmpty(groupIds)) {
            return null;
        }
        return listGuardsByGroup(groupIds,keyword, orderBy, limit);
    }

    @Override
    public int countGuardsByDepartmentId(List<String> ids,String keyword) {
        List<Long> groupIds = guardMapper.getGroupIdByDepartments(ids);
        if (ListUtils.isEmpty(groupIds)) {
            return 0;
        }
        return countGuardsByGroup(groupIds,keyword);
    }

    @Override
    public List<Alert> listGuardAlerts(long guardId, Date start, Date end, List<String> orderBy, SqlLimit limit) {
        List<Long> ids = listGuardAlertsId(guardId, start, end, orderBy, limit);
        if (ListUtils.isEmpty(ids)) {
            return null;
        }
        return alertMapper.listAlerts(ids);
    }

    @Override
    public int countGuardAlerts(long guardId, Date start, Date end) {
        return guardMapper.countAlerts(guardId, start, end);
    }

    @Override
    public List<Long> listGuardAlertsId(long guardId, Date start, Date end, List<String> orderBy, SqlLimit limit) {
        if (orderBy == null) {
            orderBy = new OrderByBuilder()
                    .add(BaseGuardAlert.ADD_TIME, true)
                    .build();
        }
        return guardMapper.listAlertIds(guardId, start, end, orderBy, limit);
    }

    @Override
    public boolean isPhoneExist(String phone) {
        BaseGuard guard = guardBaseMapper.selectFirstByColumn(BaseGuard.PHONE, phone);
        return guard != null;
    }

    @Override
    public boolean changeGroup(Long guardId, Long groupId) {
        if (guardId == null || groupId == null) {
            return false;
        }
        return guardMapper.changeGroup(guardId, groupId) > 0;
    }

    @Override
    public int changeGroup(List<Long> ids, Long groupId) {
        if (ListUtils.isEmpty(ids) || groupId == null) {
            return 0;
        }
        return guardMapper.changeGuardsGroup(ids, groupId);
    }

    @Override
    public List<Guard> search(String key, SqlLimit limit) {
        if (key == null) {
            return null;
        }
        return guardMapper.search(key, limit);
    }

    @Override
    public List<Map<String, Object>> questionedConfirm(long guardId, Date start, Date end, SqlLimit limit) {
        List<Long> ids = guardMapper.listQuestionedAlertIds(guardId, start, end, limit);
        if (ListUtils.isEmpty(ids)) {
            return null;
        }
        return guardMapper.listQuestionedAlerts(ids);
    }

    @Override
    public int countQuestionedConfirm(long guardId, Date start, Date end) {
        return guardMapper.countQuestioned(guardId,start,end);
    }

    @Override
    public List<Map<String, Object>> unfinishedAlerts(long guardId, Date start, Date end, SqlLimit limit) {
        List<Long> ids = guardMapper.listUnfinishedAlertsId(guardId, start, end, limit);
        if (ListUtils.isEmpty(ids)) {
            return null;
        }
        return guardMapper.listUnfinishedAlerts(ids);
    }

    @Override
    public int countUnfinishedAlerts(long guardId, Date start, Date end) {
        return guardMapper.countUnfinishedAlerts(guardId,start,end);
    }

    @Override
    public List<StatisticsNode> statistics(long guardId, Date start, Date end) {
        int total = guardMapper.countAlerts(guardId, start, end);
        int failed = guardMapper.countFailed(guardId, start, end);
        int questioned = guardMapper.countQuestioned(guardId, start, end);
        int questionedConfirmed = guardMapper.countQuestionedConfirmed(guardId, start, end);
        if (total == 0) {
            return null;
        }
        List<StatisticsNode> list = new ArrayList<>();

        list.add(new StatisticsLeafNode(total, "出警次数", "total"));
        list.add(new StatisticsLeafNode(failed, "出警失败次数", "failed"));
        list.add(new StatisticsLeafNode(questioned, "询问可疑人员次数", "questioned"));
        list.add(new StatisticsLeafNode(questionedConfirmed, "询问可疑人员已确认次数", "questionedConfirmed"));
        return list;
    }

    @Override
    public boolean saveUploadResource(Long guardId, MultipartFormDataInput formData) throws IOException {
        Map<String, List<InputPart>> map = formData.getFormDataMap();
        List<InputPart> parts = map.get(AlertConst.FORM_RESOURCE_IMAGE);
        List<InputPart> alertPart = map.get(AlertConst.FORM_RESOURCE_ALERT_ID);
        Long alertId = null;
        if (alertPart != null) {
            InputPart part = alertPart.get(0);
            try {
                alertId = Long.parseLong(part.getBodyAsString());
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
        if (alertId == null) {
            LOGGER.error("saveUploadResource : {}", "form 表单解析不到 alertId");
            return false;
        }

        // guardId 是否一致
        BaseGuardAlert guardAlert = guardAlertBaseMapper.selectFirstByColumn(BaseGuardAlert.ALERT_ID, alertId);
        if (guardAlert == null || !guardAlert.getGuardId().equals(guardId)) {
            LOGGER.error("saveUploadResource : {}", "保安与警情不对应");
            return false;
        }

        // 是否存在询问可疑人员记录
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put(BaseAlertLog.ALERT_ID,alertId);
        selectMap.put(BaseAlertLog.REFERENCE_ID,guardId);
        selectMap.put(BaseAlertLog.LOG_TYPE, AlertLogType.GUARDS_QUESTIONED_SUSPECT.getType());
        BaseAlertLog log = logBaseMapper.selectFirstByColumns(selectMap);
        if(log == null){
            LOGGER.error("saveUploadResource : {}", "没有保安询问可疑人员的记录");
            return false;
        }
        // 解析 picture
        Integer index = alertMapper.getResourceIndex(alertId, AlertResourceType.SUSPECT_PICTURE.getType());
        if (index == null) {
            index = 0;
        }
        index += 1;
        InputPart imagePart = parts.get(0);
        if (imagePart == null) {
            LOGGER.error("saveUploadResource : {}", "form 表单解析不到 image");
            return false;
        }

        // 保存到文件
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

        // 保存 resource 记录
        BaseAlertResource resource = new BaseAlertResource();
        resource.setPath(file.getAbsolutePath());
        resource.setResourceType(AlertResourceType.SUSPECT_PICTURE.getType());
        resource.setAlertId(alertId);
        resource.setContentType(imagePart.getMediaType().toString());
        resource.setResourceIndex(index.byteValue());
        try {
            resourceBaseMapper.insertSelective(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
