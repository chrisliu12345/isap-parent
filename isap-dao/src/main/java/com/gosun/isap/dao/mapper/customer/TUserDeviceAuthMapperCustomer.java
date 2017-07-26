package com.gosun.isap.dao.mapper.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.customer.DeviceDevAuthInfo;

public interface TUserDeviceAuthMapperCustomer {
	List<DeviceDevAuthInfo> findUserTempDevAuth(@Param("userId") long userId);
}