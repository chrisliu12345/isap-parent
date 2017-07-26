package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TEnum;
import com.gosun.isap.dao.po.TEnumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEnumMapper {
    int countByExample(TEnumExample example);

    int deleteByExample(TEnumExample example);

    int insert(TEnum record);

    int insertSelective(TEnum record);

    List<TEnum> selectByExample(TEnumExample example);

    int updateByExampleSelective(@Param("record") TEnum record, @Param("example") TEnumExample example);

    int updateByExample(@Param("record") TEnum record, @Param("example") TEnumExample example);
}