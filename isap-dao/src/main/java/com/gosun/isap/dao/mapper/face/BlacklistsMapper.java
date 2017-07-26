package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Blacklists;
import com.gosun.isap.dao.po.face.BlacklistsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlacklistsMapper {
    int countByExample(BlacklistsExample example);

    int deleteByExample(BlacklistsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blacklists record);

    int insertSelective(Blacklists record);

    List<Blacklists> selectByExample(BlacklistsExample example);

    Blacklists selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blacklists record, @Param("example") BlacklistsExample example);

    int updateByExample(@Param("record") Blacklists record, @Param("example") BlacklistsExample example);

    int updateByPrimaryKeySelective(Blacklists record);

    int updateByPrimaryKey(Blacklists record);
}