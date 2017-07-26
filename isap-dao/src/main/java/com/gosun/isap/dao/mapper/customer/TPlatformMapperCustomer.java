package com.gosun.isap.dao.mapper.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.TDevice;

public interface TPlatformMapperCustomer {
	
	void insertBatchDevice(List<TDevice> dev) ;
	void devNetIn(List<String> id);
	void devNetOut(List<String> id);
	void updateDev(String id);
	void delDev(@Param("id") String id);
}
