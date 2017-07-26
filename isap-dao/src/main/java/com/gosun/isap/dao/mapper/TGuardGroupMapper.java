package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardGroup;
import com.gosun.isap.dao.po.TGuardGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardGroupMapper {
    int countByExample(TGuardGroupExample example);

    int deleteByExample(TGuardGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardGroup record);

    int insertSelective(TGuardGroup record);

    List<TGuardGroup> selectByExample(TGuardGroupExample example);

    TGuardGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardGroup record, @Param("example") TGuardGroupExample example);

    int updateByExample(@Param("record") TGuardGroup record, @Param("example") TGuardGroupExample example);

    int updateByPrimaryKeySelective(TGuardGroup record);

    int updateByPrimaryKey(TGuardGroup record);
}