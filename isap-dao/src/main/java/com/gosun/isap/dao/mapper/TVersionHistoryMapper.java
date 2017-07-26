package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TVersionHistory;
import com.gosun.isap.dao.po.TVersionHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVersionHistoryMapper {
    int countByExample(TVersionHistoryExample example);

    int deleteByExample(TVersionHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TVersionHistory record);

    int insertSelective(TVersionHistory record);

    List<TVersionHistory> selectByExample(TVersionHistoryExample example);

    TVersionHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TVersionHistory record, @Param("example") TVersionHistoryExample example);

    int updateByExample(@Param("record") TVersionHistory record, @Param("example") TVersionHistoryExample example);

    int updateByPrimaryKeySelective(TVersionHistory record);

    int updateByPrimaryKey(TVersionHistory record);
}