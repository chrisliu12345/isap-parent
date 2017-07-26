package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.TFaceServerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFaceServerMapper {
    int countByExample(TFaceServerExample example);

    int deleteByExample(TFaceServerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TFaceServer record);

    int insertSelective(TFaceServer record);

    List<TFaceServer> selectByExample(TFaceServerExample example);

    TFaceServer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TFaceServer record, @Param("example") TFaceServerExample example);

    int updateByExample(@Param("record") TFaceServer record, @Param("example") TFaceServerExample example);

    int updateByPrimaryKeySelective(TFaceServer record);

    int updateByPrimaryKey(TFaceServer record);
}