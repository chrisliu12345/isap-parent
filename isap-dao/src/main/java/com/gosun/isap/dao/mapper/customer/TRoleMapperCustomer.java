package com.gosun.isap.dao.mapper.customer;

import java.util.List;

import javax.ws.rs.Path;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TRoleExample;
import com.gosun.isap.dao.po.customer.RoleDevAuthCustomer;
import com.gosun.isap.dao.po.customer.RoleMenuAuthCustomer;
import com.gosun.isap.dao.po.customer.TRoleCustomer;
import com.gosun.isap.dao.po.customer.TRoleDepDevCustomer;

public interface TRoleMapperCustomer {

	public int addRole(TRole role);

	public List<TRoleCustomer> getRoleCustomer(@Param("roleId") Long role_id);

	public Long roleCount(TRoleExample example);
	
	public List<TRoleDepDevCustomer> getRoleDepDevCustomer(@Param("roleId") Long role_id);

}
