package com.gosun.isap.dao.mapper.customer;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.dao.po.customer.TResource;
import com.gosun.isap.dao.po.customer.TResourceExample;

public interface TDeviceMapperCustomer {
	
	TDevice selectByName( @Param("name") String name);
	List<TDevice> selectnoBranchdevice();
	List<TDevice> selectBranchdevice(@Param("branchid") String branchid);
	void updateStatus(@Param("id") String id);
	void updateDev(@Param("id")  String id,@Param("name") String name,@Param("description") String description);
	void updateDevdepartent(@Param("id")  String id,@Param("departId") String departId);
	void updateDevstatus(@Param("id")  String id,@Param("status") int status);
}
