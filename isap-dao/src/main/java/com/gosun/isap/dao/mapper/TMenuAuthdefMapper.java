package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TMenuAuthdefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMenuAuthdefMapper {
    int countByExample(TMenuAuthdefExample example);

    int deleteByExample(TMenuAuthdefExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TMenuAuthdef record);

    int insertSelective(TMenuAuthdef record);

    List<TMenuAuthdef> selectByExample(TMenuAuthdefExample example);

    TMenuAuthdef selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TMenuAuthdef record, @Param("example") TMenuAuthdefExample example);

    int updateByExample(@Param("record") TMenuAuthdef record, @Param("example") TMenuAuthdefExample example);

    int updateByPrimaryKeySelective(TMenuAuthdef record);

    int updateByPrimaryKey(TMenuAuthdef record);
}