package com.gosun.isap.authority.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.authority.api.service.TDevAuthService;
import com.gosun.isap.dao.mapper.TDevAuthdefMapper;
import com.gosun.isap.dao.po.TDevAuthdef;
import com.gosun.isap.dao.po.TDevAuthdefExample;

@Service
public class TDevAuthServiceImpl implements TDevAuthService {
	@Autowired
	private TDevAuthdefMapper deviceAuthMapper;

	@Override
	public List<TDevAuthdef> findByDeviceType(int deviceType) {
		TDevAuthdefExample example = new TDevAuthdefExample();
		TDevAuthdefExample.Criteria criteria = example.createCriteria();
		criteria.andDevTypeEqualTo(deviceType);
		return deviceAuthMapper.selectByExample(example);
	}

	public List<TDevAuthdef> findAll() {
		return deviceAuthMapper.selectByExample(null);
	}
}
