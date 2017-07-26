package com.gosun.isap.dao.mapper.customer;

import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.TUserExample;
import com.gosun.isap.dao.po.customer.TUserCustomer;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TUserMapperCustomer {
	List<TUserCustomer> selectByExample(TUserExample example);

	TUserCustomer get(@Param("id") long id);

	void batchDelete(List<Integer> intIds);
}