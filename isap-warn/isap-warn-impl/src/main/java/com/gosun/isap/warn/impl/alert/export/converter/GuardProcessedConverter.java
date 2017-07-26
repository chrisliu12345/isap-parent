package com.gosun.isap.warn.impl.alert.export.converter;

import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.util.DateUtils;
import com.gosun.isap.warn.impl.alert.export.base.MapConverter;
import com.gosun.isap.warn.impl.alert.export.freemarker.FreemarkerStreamingOutput;

import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * 保安出警记录表数据转换。
 * <p>创建时间：2017-6-1 10:53</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class GuardProcessedConverter implements MapConverter {
    private static final String ALERT_IMAGE_URL= "alertImageUrl";
    private static final String ARRIVE_IMAGE_URL = "arriveImageUrl";
    private static final String ALERT_IMAGE_URL_TEMPLATE="/%d/resource?index=0&type=0";
    private static final String ARRIVE_IMAGE_URL_TEMPLATE="/%d/resource?index=0&type=1";
    private static final String ALERT_ID="alertId";

    private static final String ALERT_TYPE = "alertType";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String STARTING_TIME = "startingTime";
    private static final String REASON = "reason";
    private static final String DATE = "date";
    private static final String ARRIVE_TIME = "arriveTime";
    private static final String RESULT = "result";
    private static final String ALERT_IMAGE_PATH = "alertImagePath";
    private static final String ALERT_IMAGE= "alertImage";
    private static final String ARRIVE_IMAGE_PATH = "arriveImagePath";
    private static final String ARRIVE_IMAGE= "arriveImage";
    private static final String ALERT_IMAGE_TITLE = "alertImageTitle";
    private static final String ARRIVE_IMAGE_TITLE = "arriveImageTitle";
    private static final String INDEX = "index";

    private static final String FIND_SUSPECT= "%s 发现可疑人员";
    private static final String ABNORMAL_ALERT = "%s 发现异常情况";
    private static final String RESULT_TEMPLATE = "保安到达指定地点";

    private static final String IMAGE_HOLDER = "alert/template/image_holder.jpg";

    private int index ;

    private boolean document;

    @Override
    public void convert(Map<String, Object> map) {
        index ++;

        Integer type = (Integer) map.get(ALERT_TYPE);
        String departmentName = (String) map.get(DEPARTMENT_NAME);
        Date startingDate = (Date) map.get(STARTING_TIME);
        Date arriveDate= (Date) map.get(ARRIVE_TIME);
        String startingTime = DateUtils.formatTime(startingDate);
        String arriveTime = DateUtils.formatTime(arriveDate);
        String alertImage= (String) map.get(ALERT_IMAGE_PATH);
        String arriveImage = (String) map.get(ARRIVE_IMAGE_PATH);

        String template = FIND_SUSPECT;
        if(type != null) {
            AlertType alertType = AlertType.get(type.byteValue());
            if (alertType == AlertType.NORMAL_ALERT){
                template = FIND_SUSPECT;
            }else {
                template = ABNORMAL_ALERT;
            }
        }
        String reason = String.format(template,departmentName);
        map.put(DATE,DateUtils.formatDate(startingDate));
        map.put(STARTING_TIME,startingTime);
        map.put(ARRIVE_TIME,arriveTime);
        map.put(REASON,reason);
        map.put(RESULT,RESULT_TEMPLATE);
        map.put(ALERT_IMAGE_TITLE,startingTime+reason);
        map.put(ARRIVE_IMAGE_TITLE,arriveTime + RESULT_TEMPLATE);

        if (document){
            map.put(ALERT_IMAGE,getImage(alertImage));
            map.put(ARRIVE_IMAGE,getImage(arriveImage));
            map.put(INDEX,index);
        }else {
            map.remove(ARRIVE_IMAGE_PATH);
            map.remove(ALERT_IMAGE_PATH);
        }

        try {
            Long alertId = (Long) map.get(ALERT_ID);
            if(alertId != null) {
                map.putIfAbsent(ARRIVE_IMAGE_URL, String.format(ARRIVE_IMAGE_URL_TEMPLATE, alertId));
                map.putIfAbsent(ALERT_IMAGE_URL, String.format(ALERT_IMAGE_URL_TEMPLATE, alertId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getImage(String path){
        if(path == null || path.isEmpty()){
            return getHolderImage();
        }
        File file = new File(path);
        if(!file.exists()){
            return getHolderImage();
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(inputStream == null){
            return getHolderImage();
        }

        return base64Encoding(inputStream);
    }

    private String getHolderImage(){
        InputStream stream = FreemarkerStreamingOutput.class.getClassLoader().getResourceAsStream(IMAGE_HOLDER);
        return base64Encoding(stream);
    }

    private String base64Encoding(InputStream inputStream)  {
        if(inputStream == null){
            return null;
        }

        byte[] bytes = null;
        try {
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(bytes == null || bytes.length == 0){
            return null;
        }

        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 设置导出的数据类型为 document
     */
    public void setDocument() {
        this.document = true;
    }
}
