package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.TFaceServerDep;
import com.gosun.isap.dao.po.face.TFaceServerDepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFaceServerDepMapper {
    int countByExample(TFaceServerDepExample example);

    int deleteByExample(TFaceServerDepExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TFaceServerDep record);

    int insertSelective(TFaceServerDep record);

    List<TFaceServerDep> selectByExample(TFaceServerDepExample example);

    TFaceServerDep selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TFaceServerDep record, @Param("example") TFaceServerDepExample example);

    int updateByExample(@Param("record") TFaceServerDep record, @Param("example") TFaceServerDepExample example);

    int updateByPrimaryKeySelective(TFaceServerDep record);

    int updateByPrimaryKey(TFaceServerDep record);
}