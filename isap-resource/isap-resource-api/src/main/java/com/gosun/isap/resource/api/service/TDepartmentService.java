package com.gosun.isap.resource.api.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDepartmentExample;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.dao.po.customer.TUserCustomer;

public interface TDepartmentService {
	//1.添加和更新
	//public void saveOrUpdate(TDepartment department);
	 public void addDepart(TDepartment department);
	 public void updateDepart(TDepartment department);
	
	// 2.根据id删除
	public void batchDelete(List<String> intIds);

	// 3.组织列表（查看）
	public List<TDepartment> findList(TDepartmentExample tDepartmentExample);

	// 4.根据id查询
	public TDepartment findDepartmentInfo(Integer id);
	
	//5.分页
	public List<TDepartment> findList(String content,String orderSqlString, int start, int limit);
	  
	
	//6.就是根据 parent 获取所有 children 
	public List<TDepartment> getChilds(@Param("parent") String parent);
	
	//7.根据Id获取parent_Ids
	TDepartment get(String id);
	//8.根据name查询单个组织
	public List<TDepartmentCustomer> findDepartByName(@Param("name") String name);
	//9.根据community_flag是否为小区的组织
	 public List<TDepartmentCustomer> findDepartByCommunity(@Param("communityflag") Integer community);
	 
	 //分页
    public List<TDepartment> findByFlag(byte content, String orderSqlString, int start, int limit);

}
