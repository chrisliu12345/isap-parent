package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardPlanRes;
import com.gosun.isap.dao.po.TGuardPlanResExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardPlanResMapper {
    int countByExample(TGuardPlanResExample example);

    int deleteByExample(TGuardPlanResExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardPlanRes record);

    int insertSelective(TGuardPlanRes record);

    List<TGuardPlanRes> selectByExample(TGuardPlanResExample example);

    TGuardPlanRes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardPlanRes record, @Param("example") TGuardPlanResExample example);

    int updateByExample(@Param("record") TGuardPlanRes record, @Param("example") TGuardPlanResExample example);

    int updateByPrimaryKeySelective(TGuardPlanRes record);

    int updateByPrimaryKey(TGuardPlanRes record);
}