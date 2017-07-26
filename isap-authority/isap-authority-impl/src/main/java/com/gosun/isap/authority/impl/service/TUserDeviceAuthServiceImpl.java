package com.gosun.isap.authority.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.authority.api.service.TUserDeviceAuthService;
import com.gosun.isap.dao.mapper.TUserDeviceAuthMapper;
import com.gosun.isap.dao.mapper.customer.TUserDeviceAuthMapperCustomer;
import com.gosun.isap.dao.po.TUserDeviceAuth;
import com.gosun.isap.dao.po.TUserDeviceAuthExample;
import com.gosun.isap.dao.po.TUserExample;

@Service
public class TUserDeviceAuthServiceImpl implements TUserDeviceAuthService {
	@Autowired
	private TUserDeviceAuthMapper userDeviceAuthMapper;
	@Autowired
	private TUserDeviceAuthMapperCustomer userDeviceAuthMapperCustomer;

	@Override
	public void save(TUserDeviceAuth tUserDeviceAuth) {
		userDeviceAuthMapper.insert(tUserDeviceAuth);
	}

	@Override
	public void update(TUserDeviceAuth tUserDeviceAuth) {
		userDeviceAuthMapper.updateByPrimaryKey(tUserDeviceAuth);
	}

	@Override
	public void batchDelete(List<Long> ids) {
		TUserDeviceAuthExample example = new TUserDeviceAuthExample();
		// 构造查询条件criteria
		TUserDeviceAuthExample
		.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		userDeviceAuthMapper.deleteByExample(example);
	}

}
