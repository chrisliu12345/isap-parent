package com.gosun.isap.warn.api.alert.util.helper;

import com.gosun.isap.dao.mapper.alert.base.AlertLogTemplateBaseMapper;
import com.gosun.isap.dao.po.alert.base.BaseAlertLog;
import com.gosun.isap.dao.po.alert.base.BaseAlertLogTemplate;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.dao.mapper.alert.Interval;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertLogType;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 警情日志辅助类<br>
 * <p>用于辅助生成警情日志，日志模板在 property 文件,或者 xml 文件中配置，helper 负责通过模板生成日志信息。</p>
 * 创建时间：2017/4/15 11:56
 * 0:XXX 将警情标记为 可疑警情
 * 1:XXX 联系保安 XXX-17012345678 出警
 * 2:XXX 联系保安 XXX-17012345678 失败，原因: XXX
 * 3:XXX 联系不到保安，警情处理失败
 * 4:XXX 确认保安 XXX-17012345678 到达现场
 * 5:XXX 确认保安 XXX-17012345678 未到达现
 * 6:XXX 收到保安 XXX-17012345678 二次回电，内容：
 * 7:30 分钟内未收到保安回电，警情处理失败
 * 8:30 分钟内未收到保安二次回电，警情处理结束
 *
 * @author 娄存银
 * @version 1.0
 */
@Component
public class AlertLogHelper {
    private static final int PARAMS_COUNT = 4;
    private static final int TIME_INDEX = 0;
    private static final int USER_INDEX = 1;
    private static final int CONTENT_INDEX = 2;
    private static final int REASON_INDEX = 3;

    private static List<BaseAlertLogTemplate> logTemplates;

    private static AlertLogTemplateBaseMapper mapper;

    private static UserService userService;

    private static void initTemplates() {
        if (logTemplates == null) {
            setLogTemplates(mapper.selectAll(null));
        }
    }

    /**
     * 将日志转为 Sting 类型
     *
     * @param logs 日志列表
     * @return String 类型的日志列表
     */
    public static List<String> convert(List<BaseAlertLog> logs) {
        if (logs == null || logs.size() == 0) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (BaseAlertLog log : logs) {
            String strLog = convert(log);
            if (strLog != null) {
                list.add(strLog);
            }
        }
        return list;
    }

    /**
     * 将警情日志按日志模板的格式转换为字符串
     *
     * @param log 日志数据
     * @return 日志信息
     */
    public static String convert(BaseAlertLog log) {
        if (log == null || log.getLogType() == null) {
            return null;
        }

        BaseAlertLogTemplate template = getTemplate(log.getLogType());
        if (template == null) {
            return null;
        }

        String content = template.getContent();
        if (content == null) {
            return null;
        }

        String[] params = new String[PARAMS_COUNT];

        DateFormat format = new SimpleDateFormat(AlertConst.DATE_TIME_PATTERN);
        params[TIME_INDEX] = format.format(log.getAddTime());

        Long userId = log.getUserId();
        if (userId == null || userId == 0) {
            params[USER_INDEX] = "";
        } else {
            params[USER_INDEX] = userService.getUserName(userId);
        }

        params[CONTENT_INDEX] = log.getContent();
        params[REASON_INDEX] = log.getReason();

        return String.format(content, (Object[]) params);
    }

    /**
     * 根据警情日志类型获取警情日志模板
     *
     * @param type 日志类型
     * @return 模板
     */
    public static BaseAlertLogTemplate getTemplate(byte type) {
        if (logTemplates == null) {
            initTemplates();
        }
        if (logTemplates == null) {
            logTemplates = new ArrayList<>();
            return null;
        }

        for (BaseAlertLogTemplate template : logTemplates) {
            Byte templateLogType = template.getLogType();
            if (templateLogType != null && type == templateLogType) {
                return template;
            }
        }

        return null;
    }


    /**
     * 根据日志类型，在日志列表中筛选日志
     *
     * @param logs 日志列表
     * @param type 日志类型
     * @return 相关日志
     */
    public static BaseAlertLog getLogByStatus(List<BaseAlertLog> logs, AlertLogType type) {
        if (type == null) {
            return null;
        }
        for (BaseAlertLog log : logs) {
            if (log != null && log.getLogType() == type.getType()) {
                return log;
            }
        }

        return null;
    }

    /**
     * 根据日志类型，在日志列表中筛选日志
     *
     * @param logs 日志列表
     * @param type 日志类型
     * @return 相关日志
     */
    public static List<BaseAlertLog> getLogsByStatus(List<BaseAlertLog> logs, AlertLogType type) {
        if (type == null) {
            return null;
        }

        List<BaseAlertLog> list = new ArrayList<>();
        for (BaseAlertLog log : logs) {
            if (log != null && log.getLogType() == type.getType()) {
                list.add(log);
            }
        }

        return list.size() == 0 ? null : list;
    }

    /**
     * 从日志列表中获取处理结束日志
     *
     * @param logs 日志列表
     * @return 相关日志
     */
    public static BaseAlertLog getFinishedLog(List<BaseAlertLog> logs) {
        for (BaseAlertLog log : logs) {
            Interval<Byte> limit = AlertLogType.getFinishedInterval();
            if (log != null && log.getLogType() > limit.getLowerLimit()) {
                return log;
            }
        }

        return null;
    }

    /**
     * 初始化模板
     *
     * @param logTemplates 模板数据
     */
    private static synchronized void setLogTemplates(List<BaseAlertLogTemplate> logTemplates) {
        AlertLogHelper.logTemplates = logTemplates;
    }

    /**
     * 将 mapper 注入到静态变量中
     *
     * @param mapper mapper
     */
    @Autowired
    public void setMapper(AlertLogTemplateBaseMapper mapper) {
        if (AlertLogHelper.mapper == null) {
            AlertLogHelper.mapper = mapper;
        }
    }

    /**
     * 将 userService 注入到静态变量中
     *
     * @param userService userService
     */
    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    public void setUserService(UserService userService) {
        if (AlertLogHelper.userService == null) {
            AlertLogHelper.userService = userService;
        }
    }

}
