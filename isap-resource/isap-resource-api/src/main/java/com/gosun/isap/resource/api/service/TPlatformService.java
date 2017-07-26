package com.gosun.isap.resource.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.dao.po.TPlatExample;
public interface TPlatformService {
	List<TPlat> findList(TPlatExample example);
	 int addPlat(TPlat plat);
	 int deletePlat(long platid);
	 int updatePlat(TPlat plat);
	 List<TPlat> queryPlat(TPlatExample example);
	void insertBatchDevice(List<TDevice> dev);
	 int netIn(List<String> id);
	 int netOut(List<String> id);
	 int updateDevice(String id);
	 int delDevice(String id);
	
}
