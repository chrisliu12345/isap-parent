package com.gosun.isap.warn.api.alert.model;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;

import java.util.HashMap;

/**
 * 响应数据<br>
 * <p>用于封装 RESTful 接口请求的返回数据。</p>
 * <p>code 为 0 ，代表请求成功，其他值代表请求失败；message 为失败信息；data 为相关数据</p>
 * <p>创建时间：2017/4/17 17:13</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ResponseData<T> extends ResponseResult<T>{
    /**
     * 请求成功的状态码
     */
    public static final int SUCCESS = 0;
    private static final String TOTAL = "totalNum";
    private static final String SIZE = "rowNum";

    public ResponseData() {
        super();
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(T data) {
        setBody(data);
    }

    /**
     * 设置错误信息
     *
     * @param error 错误信息
     */
    public void setError(AlertError error) {
        if(error != AlertError.NO_DATA){
            setError(error.getCode(), error.getMessage());
        }
    }

    /**
     * @param total 列表数据 total
     */
    @Override
    public void setTotal(int total) {
        Integer saved = null;
        if (getExtend() == null) {
            setExtend(new HashMap<>());
        } else {
            saved = (Integer) getExtend().get(TOTAL);
        }
        if (saved == null || saved < total) {
            addExtend(TOTAL, total);
        }
    }

    /**
     * @param error 错误信息
     * @param <T>   数据类型
     * @return error responseData
     */
    public static <T> ResponseData<T> error(AlertError error) {
        ResponseData<T> data = new ResponseData<>();
        data.setError(error);
        return data;
    }

    /**
     * @param error   错误信息
     * @param message 错误信息的附加信息
     * @param <T>     数据类型
     * @return error responseData
     */
    public static <T> ResponseData<T> error(AlertError error, String... message) {
        ResponseData<T> data = new ResponseData<>();
        data.setError(error.getCode(), String.format(error.getMessage(), (Object[]) message));
        return data;
    }

    /**
     * 初始化一个 ResponseData
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return ResponseData
     */
    public static <T> ResponseData<T> init(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setData(data);
        return responseData;
    }

    /**
     * 初始化一个 ResponseData
     *
     * @param data  数据
     * @param total 数据数量
     * @param <T>   数据类型
     * @return ResponseData
     */
    public static <T> ResponseData<T> init(T data, Integer total) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setData(data);
        responseData.setTotal(total);

        return responseData;
    }

    /**
     * @return 处理成功的 ResponseData
     */
    public static ResponseData ok() {
        return new ResponseData();
    }
}