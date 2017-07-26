package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.face.Blacklistgroups;

public interface BlacklistgroupsMapperCustomer {

	List<Blacklistgroups> selectlistGroupBylistID(Integer listID);

	List<Blacklistgroups> selectListGroupByDepartmentID(String remark);

	int countToGetListGroup(@Param("creator") String creator);

	List<Blacklistgroups> selectToGetListGroup(@Param("start") Integer start, @Param("limit") Integer limit,
			@Param("creator") String creator);

}
