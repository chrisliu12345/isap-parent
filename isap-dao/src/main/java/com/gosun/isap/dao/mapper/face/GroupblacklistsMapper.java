package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Groupblacklists;
import com.gosun.isap.dao.po.face.GroupblacklistsExample;
import com.gosun.isap.dao.po.face.GroupblacklistsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupblacklistsMapper {
    int countByExample(GroupblacklistsExample example);

    int deleteByExample(GroupblacklistsExample example);

    int deleteByPrimaryKey(GroupblacklistsKey key);

    int insert(Groupblacklists record);

    int insertSelective(Groupblacklists record);

    List<Groupblacklists> selectByExample(GroupblacklistsExample example);

    Groupblacklists selectByPrimaryKey(GroupblacklistsKey key);

    int updateByExampleSelective(@Param("record") Groupblacklists record, @Param("example") GroupblacklistsExample example);

    int updateByExample(@Param("record") Groupblacklists record, @Param("example") GroupblacklistsExample example);

    int updateByPrimaryKeySelective(Groupblacklists record);

    int updateByPrimaryKey(Groupblacklists record);
}