package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TActionLinkage;
import com.gosun.isap.dao.po.TActionLinkageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TActionLinkageMapper {
    int countByExample(TActionLinkageExample example);

    int deleteByExample(TActionLinkageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TActionLinkage record);

    int insertSelective(TActionLinkage record);

    List<TActionLinkage> selectByExample(TActionLinkageExample example);

    TActionLinkage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TActionLinkage record, @Param("example") TActionLinkageExample example);

    int updateByExample(@Param("record") TActionLinkage record, @Param("example") TActionLinkageExample example);

    int updateByPrimaryKeySelective(TActionLinkage record);

    int updateByPrimaryKey(TActionLinkage record);
}