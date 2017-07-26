package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Whitelists;
import com.gosun.isap.dao.po.face.WhitelistsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhitelistsMapper {
    int countByExample(WhitelistsExample example);

    int deleteByExample(WhitelistsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Whitelists record);

    int insertSelective(Whitelists record);

    List<Whitelists> selectByExample(WhitelistsExample example);

    Whitelists selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Whitelists record, @Param("example") WhitelistsExample example);

    int updateByExample(@Param("record") Whitelists record, @Param("example") WhitelistsExample example);

    int updateByPrimaryKeySelective(Whitelists record);

    int updateByPrimaryKey(Whitelists record);
}