package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TOperlog;
import com.gosun.isap.dao.po.TOperlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOperlogMapper {
    int countByExample(TOperlogExample example);

    int deleteByExample(TOperlogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TOperlog record);

    int insertSelective(TOperlog record);

    List<TOperlog> selectByExample(TOperlogExample example);

    TOperlog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TOperlog record, @Param("example") TOperlogExample example);

    int updateByExample(@Param("record") TOperlog record, @Param("example") TOperlogExample example);

    int updateByPrimaryKeySelective(TOperlog record);

    int updateByPrimaryKey(TOperlog record);
}