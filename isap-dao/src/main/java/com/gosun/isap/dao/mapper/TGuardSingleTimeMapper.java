package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardSingleTime;
import com.gosun.isap.dao.po.TGuardSingleTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardSingleTimeMapper {
    int countByExample(TGuardSingleTimeExample example);

    int deleteByExample(TGuardSingleTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardSingleTime record);

    int insertSelective(TGuardSingleTime record);

    List<TGuardSingleTime> selectByExample(TGuardSingleTimeExample example);

    TGuardSingleTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardSingleTime record, @Param("example") TGuardSingleTimeExample example);

    int updateByExample(@Param("record") TGuardSingleTime record, @Param("example") TGuardSingleTimeExample example);

    int updateByPrimaryKeySelective(TGuardSingleTime record);

    int updateByPrimaryKey(TGuardSingleTime record);
}