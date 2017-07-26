package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.dao.po.TPlatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPlatMapper {
    int countByExample(TPlatExample example);

    int deleteByExample(TPlatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TPlat record);

    int insertSelective(TPlat record);

    List<TPlat> selectByExample(TPlatExample example);

    TPlat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TPlat record, @Param("example") TPlatExample example);

    int updateByExample(@Param("record") TPlat record, @Param("example") TPlatExample example);

    int updateByPrimaryKeySelective(TPlat record);

    int updateByPrimaryKey(TPlat record);
}