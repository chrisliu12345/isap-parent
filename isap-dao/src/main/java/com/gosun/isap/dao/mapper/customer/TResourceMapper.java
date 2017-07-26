package com.gosun.isap.dao.mapper.customer;

import java.util.List;
import com.gosun.isap.dao.po.customer.TResource;
import com.gosun.isap.dao.po.customer.TResourceExample;

public interface TResourceMapper {
	int countByExample(TResourceExample example);

	List<TResource> selectByExample(TResourceExample example);

	TResource selectByPrimaryKey(String id);
}