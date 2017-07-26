package com.gosun.isap.warn.api.alert.model;

import com.gosun.isap.dao.po.alert.base.BaseAlert;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * <p>创建时间：2017/4/25 10:15</p>
 *
 * @author 娄存银
 * @version 1.0
 */

public class ResponseDataTest {
    @Test
    public void toJson() throws IOException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        com.gosun.isap.warn.api.alert.model.ResponseData<List<Integer>> responseData = new com.gosun.isap.warn.api.alert.model.ResponseData<>();
        responseData.setBody(list);
        responseData.setError(0,"ok");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        System.out.println(json);
    }

    @Test
    public void alertToJson() throws IOException {

        BaseAlert alert = new BaseAlert();
        alert.setId(10L);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(alert);
        System.out.println(json);
    }

    @Test
    public void testEqualOk(){
        assertEquals(ResponseData.ok(),ResponseData.ok());
    }
}
