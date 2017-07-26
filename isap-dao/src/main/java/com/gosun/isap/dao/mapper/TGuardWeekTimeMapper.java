package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardWeekTime;
import com.gosun.isap.dao.po.TGuardWeekTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardWeekTimeMapper {
    int countByExample(TGuardWeekTimeExample example);

    int deleteByExample(TGuardWeekTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardWeekTime record);

    int insertSelective(TGuardWeekTime record);

    List<TGuardWeekTime> selectByExample(TGuardWeekTimeExample example);

    TGuardWeekTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardWeekTime record, @Param("example") TGuardWeekTimeExample example);

    int updateByExample(@Param("record") TGuardWeekTime record, @Param("example") TGuardWeekTimeExample example);

    int updateByPrimaryKeySelective(TGuardWeekTime record);

    int updateByPrimaryKey(TGuardWeekTime record);
}