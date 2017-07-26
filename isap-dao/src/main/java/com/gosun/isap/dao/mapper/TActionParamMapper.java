package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TActionParam;
import com.gosun.isap.dao.po.TActionParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TActionParamMapper {
    int countByExample(TActionParamExample example);

    int deleteByExample(TActionParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TActionParam record);

    int insertSelective(TActionParam record);

    List<TActionParam> selectByExample(TActionParamExample example);

    TActionParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TActionParam record, @Param("example") TActionParamExample example);

    int updateByExample(@Param("record") TActionParam record, @Param("example") TActionParamExample example);

    int updateByPrimaryKeySelective(TActionParam record);

    int updateByPrimaryKey(TActionParam record);
}