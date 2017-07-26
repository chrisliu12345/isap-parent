package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Capfaces;
import com.gosun.isap.dao.po.face.CapfacesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CapfacesMapper {
    int countByExample(CapfacesExample example);

    int deleteByExample(CapfacesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Capfaces record);

    int insertSelective(Capfaces record);

    List<Capfaces> selectByExample(CapfacesExample example);

    Capfaces selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Capfaces record, @Param("example") CapfacesExample example);

    int updateByExample(@Param("record") Capfaces record, @Param("example") CapfacesExample example);

    int updateByPrimaryKeySelective(Capfaces record);

    int updateByPrimaryKey(Capfaces record);
}