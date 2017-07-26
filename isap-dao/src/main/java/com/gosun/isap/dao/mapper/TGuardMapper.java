package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuard;
import com.gosun.isap.dao.po.TGuardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardMapper {
    int countByExample(TGuardExample example);

    int deleteByExample(TGuardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuard record);

    int insertSelective(TGuard record);

    List<TGuard> selectByExample(TGuardExample example);

    TGuard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuard record, @Param("example") TGuardExample example);

    int updateByExample(@Param("record") TGuard record, @Param("example") TGuardExample example);

    int updateByPrimaryKeySelective(TGuard record);

    int updateByPrimaryKey(TGuard record);
}