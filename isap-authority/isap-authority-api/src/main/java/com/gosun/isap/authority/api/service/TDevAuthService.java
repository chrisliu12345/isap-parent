package com.gosun.isap.authority.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TDevAuthdef;
public interface TDevAuthService {

	List<TDevAuthdef> findByDeviceType(int deviceType);
	List<TDevAuthdef> findAll();
}
