package com.gosun.isap.resource.impl.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.mapper.customer.TDeviceMapperCustomer;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.resource.api.service.TDeviceService;

@Service
public class DeviceServiceImpl implements TDeviceService {

	@Autowired
	private TDeviceMapper deviceMapper;

	@Autowired
	private TDeviceMapperCustomer devicemappercustomer;

	// TDevice selectByName(String name);

	@Override
	public TDevice findDeviceById(String deviceid) {
		// TODO Auto-generated method stub
		return deviceMapper.selectByPrimaryKey(deviceid);
	}

	@Override
	public void updateDeviceName(String id, String name,String description) {
		// TODO Auto-generated method stub
		devicemappercustomer.updateDev(id, name,description);
	}

	@Override
	public List<TDevice> queryNoBranchDevice(TDeviceExample example) {
		return deviceMapper.selectByExample(example);
	}

	@Override
	public void deleteDevice(String id) {
		devicemappercustomer.updateStatus(id);
	}

	@Override
	public List<TDevice> queryBranchDevice(String branchid) {
		// TODO Auto-generated method stub
		return devicemappercustomer.selectBranchdevice(branchid);
	}

	@Override
	public void updateDevice(List<String> devices, String departId) {
		int len = devices.size();
		for (int i = 0; i < len; i++) {
			String id = devices.get(i);
			devicemappercustomer.updateDevdepartent(id, departId);

		}
	}

	@Override
	public List<TDevice> queryDevice(TDeviceExample example) {
		return deviceMapper.selectByExample(example);
	}

	@Override
	public void insertDev(TDevice dev) {
		// TODO Auto-generated method stub
		deviceMapper.insert(dev);
	}

	public List<TDevice> queryPlatdevice(TDeviceExample example) {
		return deviceMapper.selectByExample(example);
	}

	@Override
	public TDevice queryPlatDeviceByCode(Long platId, String code) {
		// TODO Auto-generated method stub
		TDeviceExample example = new TDeviceExample();
		TDeviceExample.Criteria criteria = example.createCriteria();
		criteria.andPlatIdEqualTo(platId);
		criteria.andCodeEqualTo(code);
		List<TDevice> deviceList = deviceMapper.selectByExample(example);
		if (null == deviceList || deviceList.isEmpty()) {
			return null;
		}
		return deviceList.get(0);
	}

	@Override
	public void updateDevStatus(String id, int status) {
		// TODO Auto-generated method stub
		devicemappercustomer.updateDevstatus(id, status);
	}

	@Override
	public int countByExample(TDeviceExample example) {
		return deviceMapper.countByExample(example);
	}

}
