package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TPreset;
import com.gosun.isap.dao.po.TPresetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPresetMapper {
    int countByExample(TPresetExample example);

    int deleteByExample(TPresetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TPreset record);

    int insertSelective(TPreset record);

    List<TPreset> selectByExample(TPresetExample example);

    TPreset selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TPreset record, @Param("example") TPresetExample example);

    int updateByExample(@Param("record") TPreset record, @Param("example") TPresetExample example);

    int updateByPrimaryKeySelective(TPreset record);

    int updateByPrimaryKey(TPreset record);
}