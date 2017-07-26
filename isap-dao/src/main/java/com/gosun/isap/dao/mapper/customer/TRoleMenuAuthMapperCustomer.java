package com.gosun.isap.dao.mapper.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.customer.TRoleMenuAuthCustomer;

public interface TRoleMenuAuthMapperCustomer {
	List<TRoleMenuAuthCustomer> getRoleMenuAuthList(@Param("roleId") long roleId);
}
