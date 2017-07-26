package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Blacklistgroups;
import com.gosun.isap.dao.po.face.BlacklistgroupsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlacklistgroupsMapper {
    int countByExample(BlacklistgroupsExample example);

    int deleteByExample(BlacklistgroupsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blacklistgroups record);

    int insertSelective(Blacklistgroups record);

    List<Blacklistgroups> selectByExample(BlacklistgroupsExample example);

    Blacklistgroups selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blacklistgroups record, @Param("example") BlacklistgroupsExample example);

    int updateByExample(@Param("record") Blacklistgroups record, @Param("example") BlacklistgroupsExample example);

    int updateByPrimaryKeySelective(Blacklistgroups record);

    int updateByPrimaryKey(Blacklistgroups record);
}