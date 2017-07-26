package com.gosun.isap.user.service;

import java.util.List;

import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.TUserExample;
import com.gosun.isap.dao.po.customer.TUserCustomer;

public interface TUserService {

	TUser findByLoginName(String loginName);

	List<TUserCustomer> findList(TUserExample example);

	TUserCustomer get(int id);

	void saveOrUpdate(TUser user);

	void batchDelete(List<Long> intIds);

	int countByExample(TUserExample example);

	List<TUser> findList(String content, String orderSqlString, int start, int pageSize);

	List<TUserCustomer> findUserCustomerList(String content, String orderSqlString, int start, int pageSize);

	void saveOrUpdate(TUser user, int roleId);
}
