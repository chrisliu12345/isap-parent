package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardGroupBelong;
import com.gosun.isap.dao.po.TGuardGroupBelongExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardGroupBelongMapper {
    int countByExample(TGuardGroupBelongExample example);

    int deleteByExample(TGuardGroupBelongExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardGroupBelong record);

    int insertSelective(TGuardGroupBelong record);

    List<TGuardGroupBelong> selectByExample(TGuardGroupBelongExample example);

    TGuardGroupBelong selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardGroupBelong record, @Param("example") TGuardGroupBelongExample example);

    int updateByExample(@Param("record") TGuardGroupBelong record, @Param("example") TGuardGroupBelongExample example);

    int updateByPrimaryKeySelective(TGuardGroupBelong record);

    int updateByPrimaryKey(TGuardGroupBelong record);
}