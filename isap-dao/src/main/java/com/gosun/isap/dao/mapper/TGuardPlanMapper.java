package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardPlanMapper {
    int countByExample(TGuardPlanExample example);

    int deleteByExample(TGuardPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardPlan record);

    int insertSelective(TGuardPlan record);

    List<TGuardPlan> selectByExample(TGuardPlanExample example);

    TGuardPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardPlan record, @Param("example") TGuardPlanExample example);

    int updateByExample(@Param("record") TGuardPlan record, @Param("example") TGuardPlanExample example);

    int updateByPrimaryKeySelective(TGuardPlan record);

    int updateByPrimaryKey(TGuardPlan record);
}