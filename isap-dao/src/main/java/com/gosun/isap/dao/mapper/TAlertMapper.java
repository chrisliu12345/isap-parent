package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAlert;
import com.gosun.isap.dao.po.TAlertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAlertMapper {
    int countByExample(TAlertExample example);

    int deleteByExample(TAlertExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAlert record);

    int insertSelective(TAlert record);

    List<TAlert> selectByExample(TAlertExample example);

    TAlert selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAlert record, @Param("example") TAlertExample example);

    int updateByExample(@Param("record") TAlert record, @Param("example") TAlertExample example);

    int updateByPrimaryKeySelective(TAlert record);

    int updateByPrimaryKey(TAlert record);
}