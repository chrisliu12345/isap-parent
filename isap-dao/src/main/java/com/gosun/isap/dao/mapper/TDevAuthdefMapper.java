package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TDevAuthdef;
import com.gosun.isap.dao.po.TDevAuthdefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDevAuthdefMapper {
    int countByExample(TDevAuthdefExample example);

    int deleteByExample(TDevAuthdefExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDevAuthdef record);

    int insertSelective(TDevAuthdef record);

    List<TDevAuthdef> selectByExample(TDevAuthdefExample example);

    TDevAuthdef selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TDevAuthdef record, @Param("example") TDevAuthdefExample example);

    int updateByExample(@Param("record") TDevAuthdef record, @Param("example") TDevAuthdefExample example);

    int updateByPrimaryKeySelective(TDevAuthdef record);

    int updateByPrimaryKey(TDevAuthdef record);
}