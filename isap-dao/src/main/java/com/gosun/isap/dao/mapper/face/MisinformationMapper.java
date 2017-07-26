package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Misinformation;
import com.gosun.isap.dao.po.face.MisinformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MisinformationMapper {
    int countByExample(MisinformationExample example);

    int deleteByExample(MisinformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Misinformation record);

    int insertSelective(Misinformation record);

    List<Misinformation> selectByExample(MisinformationExample example);

    Misinformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Misinformation record, @Param("example") MisinformationExample example);

    int updateByExample(@Param("record") Misinformation record, @Param("example") MisinformationExample example);

    int updateByPrimaryKeySelective(Misinformation record);

    int updateByPrimaryKey(Misinformation record);
}