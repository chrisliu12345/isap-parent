package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import com.gosun.isap.dao.po.face.Cameras;

public interface CamerasMapperCustomer {
	List<Cameras> selectbyDepartmentID(String departmentID);
	
	int selectbyRemarkToID(String remark);
}