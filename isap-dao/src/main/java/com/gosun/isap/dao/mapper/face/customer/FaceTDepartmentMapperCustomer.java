package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.TDepartment;

public interface FaceTDepartmentMapperCustomer {
	List<TDepartment> selectByUserID(@Param("devType") Integer devType,@Param("userID") Long userID);

	List<TDepartment> selectByAdministratorID(@Param("devType") Integer devType);
	
	TDepartment selectByKeyToParentInfo(String id);
}