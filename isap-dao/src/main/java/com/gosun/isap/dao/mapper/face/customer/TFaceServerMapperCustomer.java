package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import com.gosun.isap.dao.po.face.TFaceServer;

public interface TFaceServerMapperCustomer {
    TFaceServer selectByDeviceID(String deviceID);
    
    TFaceServer selectByDepartmentID(String departmentID);

    List<TFaceServer> selectByUserID(Long userID);
    
    List<TFaceServer> selectByAdministratorID();
}