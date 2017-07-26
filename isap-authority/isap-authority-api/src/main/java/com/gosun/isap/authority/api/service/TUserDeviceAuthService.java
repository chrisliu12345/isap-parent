package com.gosun.isap.authority.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TUserDeviceAuth;
public interface TUserDeviceAuthService {
	void save(TUserDeviceAuth tUserDeviceAuth);
	void update(TUserDeviceAuth tUserDeviceAuth);
	void batchDelete(List<Long> ids);
}
