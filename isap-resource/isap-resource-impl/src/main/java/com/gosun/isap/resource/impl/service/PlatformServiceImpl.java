package com.gosun.isap.resource.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.TPlatMapper;
import com.gosun.isap.dao.mapper.customer.TPlatformMapperCustomer;
import com.gosun.isap.dao.mapper.customer.TUserMapperCustomer;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.dao.po.TPlatExample;
import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TUserExample;
import com.gosun.isap.resource.api.service.TPlatformService;

@Service
public class PlatformServiceImpl implements TPlatformService {

	@Autowired
	private TPlatMapper platMapper;
	@Autowired
	private TPlatformMapperCustomer platformMapperCustomer;

	@Override
	public List<TPlat> findList(TPlatExample example) {
		return platMapper.selectByExample(example);
	}

	@Override
	public int addPlat(TPlat plat) {
		// TODO Auto-generated method stub
		 platMapper.insert(plat);
		return 0;
	}

	

	@Override
	public int updatePlat(TPlat plat) {
		// TODO Auto-generated method stub
		platMapper.updateByPrimaryKey(plat);
		return 0;
	}

	@Override
	public List<TPlat> queryPlat(TPlatExample example) {
		// TODO Auto-generated method stub
	//	platMapper.
	//	return 0;
		return platMapper.selectByExample(example);
	}



	@Override
	public void insertBatchDevice(List<TDevice> dev) {
		// TODO Auto-generated method stub
		
		platformMapperCustomer.insertBatchDevice(dev);
		
	}

	@Override
	public int netIn(List<String> id) {
		// TODO Auto-generated method stub
		platformMapperCustomer.devNetIn(id);
		return 0;
	}

	@Override
	public int netOut(List<String> id) {
		// TODO Auto-generated method stub
	
		platformMapperCustomer.devNetOut(id);
		//TDeviceExample
	//	TDeviceExample example = new TDeviceExample();
	//	TDeviceExample.Criteria criteria = example.createCriteria(); 
	//	criteria.
		return 0;
	}

	@Override
	public int deletePlat(long platid) {
		// TODO Auto-generated method stub
		platMapper.deleteByPrimaryKey(platid);	
		return 0;
	}
	
	//deldev
	
	@Override
	public int updateDevice(String id) {
		// TODO Auto-generated method stub	
		platformMapperCustomer.updateDev(id);
		return 0;
	}
//deldevice
	
	@Override
	public int delDevice(String id) {
		// TODO Auto-generated method stub
		platformMapperCustomer.delDev(id);
		return 0;
	}
	

}
