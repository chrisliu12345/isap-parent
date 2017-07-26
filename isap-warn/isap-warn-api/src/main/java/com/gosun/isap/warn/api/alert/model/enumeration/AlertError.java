package com.gosun.isap.warn.api.alert.model.enumeration;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 警情处理模块错误枚举.
 * <p>创建时间：2017/5/6 15:33</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public enum AlertError {
    /**
     * 没权限
     */
    NO_PERMISSION(9800),
    /**
     * 参数无效
     */
    INVALID_PARAMS(9801, "参数: %s 不能为空"),
    /**
     * 保存文件错误
     */
    SAVE_FILE_ERROR(9802),
    /**
     * 插入数据失败
     */
    INSERT_FAILED(9803),
    /**
     * 删除失败
     */
    DELETE_FAILED(9804),
    /**
     * 更新数据失败
     */
    UPDATE_FAILED(9805),
    /**
     * 没有数据
     */
    NO_DATA(9806),
    /**
     * 警情不存在
     */
    ALERT_NOT_EXIST(9900),
    /**
     * 警情状态未知
     */
    UNKNOWN_STATUS(9901),

    /**
     * 插入日志失败
     */
    INSERT_LOG_FAILED(9903),
    /**
     * 更新警情失败
     */
    UPDATE_ALERT_FAILED(9904),
    /**
     * 生成警情失败
     */
    GENERATE_ALERT_DETAIL_FAILED(9905),
    /**
     * 错误的警情状态
     */
    ERROR_STATUS(9906),
    /**
     * 绑定警情保安失败
     */
    INSERT_GUARD_ALERT_FAILED(9907),
    /**
     * 没有保安信息
     */
    NO_GUARDS_INFO(9908),
    /**
     * 没有保安联系成功，警情应该标记为处理失败
     */
    NO_GUARDS_CALL_SUCCESS(9910),
    /**
     * 无效的保安联系数据
     */
    INVALID_GUARDS_CONTACT(9911),
    /**
     * 没有图表数据
     */
    NO_DIAGRAM_DATA(9912),
    /**
     * 警情类型不存在
     */
    ALERT_TYPE_NOT_EXIST(9913),
    /**
     * 保安信息为空
     */
    GUARD_NULL(9914),
    /**
     * 手机号已存在
     */
    PHONE_EXIST(9915),
    /**
     * 无效的手机号
     */
    INVALID_PHONE(9916),
    /**
     * id 不存在
     */
    NO_ID(9917),
    /**
     * 解绑 department 失败
     */
    UNBIND_DEPARTMENT_FAILED(9918),
    /**
     * 绑定 department 失败
     */
    BIND_DEPARTMENT_FAILED(9919),
    /**
     * 确认保安询问可疑人员失败，请检查参数是否正确，是否存在保安询问可疑人员的记录
     */
    CONFIRM_QUESTIONED_FAILED(9920),
    /**
     * 超出同时处理的警情条数限制，最多为 9 条
     */
    OVER_MAX_PROGRESSING_COUNT(9921);

    private static Properties properties;
    private static final String ERROR_PROPERTIES = "META-INF/message/Message_zh.properties";

    private int code;
    private String message;

    AlertError(int code) {
        this.code = code;
        String message = getMessage(code);
        if (message == null || message.isEmpty()) {
            this.message = name();
        } else {
            this.message = message;
        }
    }

    AlertError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据错误码获取错误信息
     *
     * @param code 错误码
     * @return 错误信息
     */
    public static String getMessage(int code) {
        if (properties == null) {
            try {
                properties = new Properties();
                InputStream inputStream = AlertError.class.getClassLoader().getResourceAsStream(ERROR_PROPERTIES);
                properties.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (properties == null) {
            return null;
        }
        String key = String.valueOf(code);
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            return null;
        }
    }

}
