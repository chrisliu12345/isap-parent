package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.face.Lists;
import com.gosun.isap.dao.po.face.WhitelistsExample;

public interface WhitelistsMapperCustomer {
	int countToGetlist(@Param("name") String name, @Param("idcard") String idcard, @Param("sex") Integer sex,
			@Param("creator") String creator);

	List<Lists> selectToGetlist(@Param("name") String name, @Param("idcard") String idcard, @Param("sex") Integer sex,
			@Param("start") Integer start, @Param("limit") Integer limit, @Param("creator") String creator);

	List<Lists> selectByExample(WhitelistsExample example);

	int countByExample(WhitelistsExample example);
}