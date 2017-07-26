package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.face.ListFilterParaBean;
import com.gosun.isap.dao.po.face.Misinformation;

public interface MisinformationMapperCustomer {
	List<Misinformation> selectByListFilterParaBean(@Param("listFilterParaBean") ListFilterParaBean listFilterParaBean,
			@Param("userID") String userID);

	int countByListFilterParaBean(@Param("listFilterParaBean") ListFilterParaBean listFilterParaBean,
			@Param("userID") String userID);
}
