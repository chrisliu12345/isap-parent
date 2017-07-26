package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.dao.po.TGuardTimeTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuardTimeTemplateMapper {
    int countByExample(TGuardTimeTemplateExample example);

    int deleteByExample(TGuardTimeTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGuardTimeTemplate record);

    int insertSelective(TGuardTimeTemplate record);

    List<TGuardTimeTemplate> selectByExample(TGuardTimeTemplateExample example);

    TGuardTimeTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TGuardTimeTemplate record, @Param("example") TGuardTimeTemplateExample example);

    int updateByExample(@Param("record") TGuardTimeTemplate record, @Param("example") TGuardTimeTemplateExample example);

    int updateByPrimaryKeySelective(TGuardTimeTemplate record);

    int updateByPrimaryKey(TGuardTimeTemplate record);
}