package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Cameras;
import com.gosun.isap.dao.po.face.CamerasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CamerasMapper {
    int countByExample(CamerasExample example);

    int deleteByExample(CamerasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cameras record);

    int insertSelective(Cameras record);

    List<Cameras> selectByExample(CamerasExample example);

    Cameras selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cameras record, @Param("example") CamerasExample example);

    int updateByExample(@Param("record") Cameras record, @Param("example") CamerasExample example);

    int updateByPrimaryKeySelective(Cameras record);

    int updateByPrimaryKey(Cameras record);
}