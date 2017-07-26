package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAction;
import com.gosun.isap.dao.po.TActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TActionMapper {
    int countByExample(TActionExample example);

    int deleteByExample(TActionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAction record);

    int insertSelective(TAction record);

    List<TAction> selectByExample(TActionExample example);

    TAction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAction record, @Param("example") TActionExample example);

    int updateByExample(@Param("record") TAction record, @Param("example") TActionExample example);

    int updateByPrimaryKeySelective(TAction record);

    int updateByPrimaryKey(TAction record);
}