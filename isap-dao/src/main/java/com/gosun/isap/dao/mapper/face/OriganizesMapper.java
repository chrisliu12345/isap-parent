package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Origanizes;
import com.gosun.isap.dao.po.face.OriganizesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OriganizesMapper {
    int countByExample(OriganizesExample example);

    int deleteByExample(OriganizesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Origanizes record);

    int insertSelective(Origanizes record);

    List<Origanizes> selectByExample(OriganizesExample example);

    Origanizes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Origanizes record, @Param("example") OriganizesExample example);

    int updateByExample(@Param("record") Origanizes record, @Param("example") OriganizesExample example);

    int updateByPrimaryKeySelective(Origanizes record);

    int updateByPrimaryKey(Origanizes record);
}