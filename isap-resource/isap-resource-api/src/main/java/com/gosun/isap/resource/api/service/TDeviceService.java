package com.gosun.isap.resource.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;

public interface TDeviceService {

	TDevice findDeviceById(String id);

	void updateDeviceName(String id, String name,String description);

	void deleteDevice(String id);

	void updateDevStatus(String id, int status);

	List<TDevice> queryNoBranchDevice(TDeviceExample example);

	List<TDevice> queryBranchDevice(String branchid);

	void updateDevice(List<String> devices, String departId);

	List<TDevice> queryDevice(TDeviceExample example);

	void insertDev(TDevice dev);

	List<TDevice> queryPlatdevice(TDeviceExample example);

	TDevice queryPlatDeviceByCode(Long platId, String code);

	int countByExample(TDeviceExample example);
}
