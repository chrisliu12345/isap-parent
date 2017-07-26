package com.gosun.isap.resource.api.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TRoleExample;

import com.gosun.isap.dao.po.customer.RoleDevAuthCustomer;
import com.gosun.isap.dao.po.customer.RoleMenuAuthCustomer;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.dao.po.customer.TRoleCustomer;


public interface TRoleService {

    public  Long roleCount(TRoleExample example);
	
	public List<TRole> queryRole(String orderSqlString, int start, int limit);
	
	public void deleteRole(List<Long> inIds);
	
	public List<TRole> getRolePage(String fuzzyField,String fuzzyValue,String orderSqlString, int start, int limit);
	
	public void updateRole(TRole role);
	
	public int addRole(TRole role);
	
	
	
	public List<TRoleCustomer> findAllRole(long roleId);
	
    public List<RoleDevAuthCustomer> findRoleDevAuth(Long role_id);
    
    public List<RoleMenuAuthCustomer> findRoleMenuAuth(Long role_id);
    
    boolean isLeaf(String id);
    
    public List<String> fetchAllByparent(List<String> depId,String parent);
}
