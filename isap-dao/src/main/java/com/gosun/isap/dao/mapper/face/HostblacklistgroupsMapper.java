package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Hostblacklistgroups;
import com.gosun.isap.dao.po.face.HostblacklistgroupsExample;
import com.gosun.isap.dao.po.face.HostblacklistgroupsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HostblacklistgroupsMapper {
    int countByExample(HostblacklistgroupsExample example);

    int deleteByExample(HostblacklistgroupsExample example);

    int deleteByPrimaryKey(HostblacklistgroupsKey key);

    int insert(Hostblacklistgroups record);

    int insertSelective(Hostblacklistgroups record);

    List<Hostblacklistgroups> selectByExample(HostblacklistgroupsExample example);

    Hostblacklistgroups selectByPrimaryKey(HostblacklistgroupsKey key);

    int updateByExampleSelective(@Param("record") Hostblacklistgroups record, @Param("example") HostblacklistgroupsExample example);

    int updateByExample(@Param("record") Hostblacklistgroups record, @Param("example") HostblacklistgroupsExample example);

    int updateByPrimaryKeySelective(Hostblacklistgroups record);

    int updateByPrimaryKey(Hostblacklistgroups record);
}