package com.gosun.isap.resource.impl.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.dao.mapper.TRoleMapper;
import com.gosun.isap.dao.mapper.customer.TDepartmentMapperCustomer;
import com.gosun.isap.dao.mapper.customer.TRoleMapperCustomer;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDepartmentExample;
import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TRoleExample;
import com.gosun.isap.dao.po.customer.RoleDevAuthCustomer;
import com.gosun.isap.dao.po.customer.RoleMenuAuthCustomer;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.dao.po.customer.TRoleCustomer;
import com.gosun.isap.resource.api.service.TDepartmentService;
import com.gosun.isap.resource.api.service.TRoleService;

@Service
public class TRoleServiceImpl implements TRoleService {

	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TRoleMapperCustomer roleMapperCustom;
	
	@Autowired
	private TDepartmentMapperCustomer departmentMapperCustomer;
	
	@Autowired
	private TDepartmentService departmentService;
	

	@Override
	public int addRole(TRole role) {
		// TODO Auto-generated method stub
		return roleMapperCustom.addRole(role);
	}

	@Override
	public void updateRole(TRole role) {

		roleMapper.updateByPrimaryKeySelective(role);
		
	}
	
	@Override
	public List<TRole> queryRole(String orderSqlString, int start, int limit) {
		// TODO Auto-generated method stub
		//queryRole(TRoleExample example,int pageSize,int start)
		TRoleExample example=new TRoleExample();
		example.setLimit(limit);
		example.setOffset(start);
		TRoleExample.Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(orderSqlString)){
			example.setOrderByClause(orderSqlString);
		}
		//criteria.andNameLike(content);
		return roleMapper.selectByExample(example);
	}

	@Override
	public void deleteRole(List<Long> inIds) {
		// TODO Auto-generated method stub

		TRoleExample roleExample = new TRoleExample();
		TRoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andIdIn(inIds);
		roleMapper.deleteByExample(roleExample);

	}

	@Override
	public List<TRole> getRolePage(String fuzzyField,String fuzzyValue, String orderSqlString, int start, int limit) {
		// TODO Auto-generated method stub
		TRoleExample roleExample = new TRoleExample();
		roleExample.setOffset(start);
		roleExample.setLimit(limit);
		TRoleExample.Criteria criteria = roleExample.createCriteria();
		if (StringUtils.isNotEmpty(orderSqlString)) {
			roleExample.setOrderByClause(orderSqlString);
		}
		roleExample.setLimit(limit);
		roleExample.setOffset(start);
		if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
			criteria.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}
		
		return roleMapper.selectByExample(roleExample);
		
	}



	@Override
	public List<TRoleCustomer> findAllRole(long roleId) {
		// TODO Auto-generated method stub

		//return roleMapperCustom.getRoleCustomer(roleId);
		return null;
	}

	@Override
	public Long roleCount(TRoleExample example) {
		// TODO Auto-generated method stub
		return roleMapperCustom.roleCount(example);
	}

	@Override
	public List<RoleDevAuthCustomer> findRoleDevAuth(Long role_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleMenuAuthCustomer> findRoleMenuAuth(Long role_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(String id) {
		List<TDepartment> departmentCustomers=departmentMapperCustomer.findChildsByParent(id);
		if (departmentCustomers != null && departmentCustomers.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<String> fetchAllByparent(List<String> depId , String parent) {
		
		//depId.add(parent);
		TDepartmentExample departmentExample=new TDepartmentExample();
		TDepartmentExample.Criteria criteria1=departmentExample.createCriteria();
		criteria1.andParentEqualTo(parent);
		List<TDepartment> depList=departmentService.findList(departmentExample);
		
		if(depList.size()>0){
			for(TDepartment department:depList){
				depId.add(department.getId());
				if(!isLeaf(department.getId())){
					fetchAllByparent(depId,department.getId());
				}
			}
			
		}else {
			
			return depId;
		}
		
		return depId;
	}

	
	
	
}
