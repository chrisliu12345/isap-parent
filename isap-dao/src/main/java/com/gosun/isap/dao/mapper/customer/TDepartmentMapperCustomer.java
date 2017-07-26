package com.gosun.isap.dao.mapper.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.dao.po.customer.TUserCustomer;

public interface TDepartmentMapperCustomer {

	// List<TDepartmentCustomer> findChildByParentIds(String id);
	public List<TDepartment> findChildsByParent(@Param("parent") String parent);

	TDepartment get(@Param("id") String id);

	List<TDepartmentCustomer> selectByName(@Param("name") String name);

	List<TDepartmentCustomer> selectByCommunityFlag(@Param("communityflag") Integer communityflag);
}
