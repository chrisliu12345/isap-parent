package com.gosun.isap.warn.api.alert;

/**
 * 警情模块通用常量
 * <p>创建时间：2017/5/9 11:54</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertConst {

    /**
     * DATE_TIME_PATTERN
     */
    String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * TIME_PATTERN
     */
    String TIME_PATTERN = "HH:mm:ss";
    /**
     * DATE_PATTERN
     */
    String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * SECOND_TO_MILLISECOND
     */
    int SECOND_TO_MILLISECOND = 1000;
    /**
     * PROCESSED_IN_TIME_MINUTE
     */
    int PROCESSED_IN_TIME_MINUTE = 30;
    /**
     * PROCESSED_IN_TIME_MILLISECOND
     */
    int PROCESSED_IN_TIME_MILLISECOND = PROCESSED_IN_TIME_MINUTE * 60 * 1000;

    /**
     * ADMIN_ID
     */
    long ADMIN_ID = 1L;
    /**
     * LATEST_ALERT_PROCESS_TIME
     */
    String LATEST_ALERT_PROCESS_TIME = "latestAlertProcessTime";
    /**
     * COUNT_OF_PROCESSED_ALERTS
     */
    String COUNT_OF_PROCESSED_ALERTS = "countOfProcessedAlerts";

    /**
     * ALERT_ERROR_PROPERTY
     */
    String ALERT_ERROR_PROPERTY = "alert.alert_error";
    /**
     * FORM_RESOURCE_FILES
     */
    String FORM_RESOURCE_FILES = "files";
    /**
     * FORM_RESOURCE_IMAGE
     */
    String FORM_RESOURCE_IMAGE = "image";
    /**
     * FORM_RESOURCE_ALERT_ID
     */
    String FORM_RESOURCE_ALERT_ID = "alertId";
    /**
     * FORM_RESOURCE_TYPE
     */
    String FORM_RESOURCE_TYPE = "type";
    /**
     * CONTENT_DISPOSITION
     */
    String CONTENT_DISPOSITION = "Content-Disposition";

    /*******************************************************************************************************************
     * 警情菜单常量
     ******************************************************************************************************************/
    /**
     * MENU_ALERT_LIST
     */
    String MENU_ALERT_LIST = "警情列表";
    /**
     * MENU_ALERT_PROCESS
     */
    String MENU_ALERT_PROCESS = "警情处理";
    /**
     * MENU_GUARD_ADMIN
     */
    String MENU_GUARD_ADMIN = "保安管理";
}
