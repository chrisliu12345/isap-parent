package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.face.BlacklistsExample;
import com.gosun.isap.dao.po.face.Lists;

public interface BlacklistsMapperCustomer {

	int countByListGroupID(Integer listGroupID);

	List<Lists> selectByListGroupID(@Param("listGroupID") Integer listGroupID, @Param("start") Integer start,
			@Param("limit") Integer limit);

	int countToGetlist(@Param("name") String name, @Param("idcard") String idcard, @Param("sex") Integer sex,
			@Param("creator") String creator);

	List<Lists> selectToGetlist(@Param("name") String name, @Param("idcard") String idcard, @Param("sex") Integer sex,
			@Param("start") Integer start, @Param("limit") Integer limit, @Param("creator") String creator);

	List<Lists> selectByExample(BlacklistsExample example);

	int countByExample(BlacklistsExample example);
}
