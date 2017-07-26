package com.gosun.isap.dao.po.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TUserDeviceAuthMapperCustomer {
	List<DeviceDevAuthInfo> findUserTempAuth(@Param("userId") int userId);
}
