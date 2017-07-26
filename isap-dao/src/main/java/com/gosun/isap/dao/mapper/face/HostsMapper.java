package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Hosts;
import com.gosun.isap.dao.po.face.HostsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HostsMapper {
    int countByExample(HostsExample example);

    int deleteByExample(HostsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hosts record);

    int insertSelective(Hosts record);

    List<Hosts> selectByExample(HostsExample example);

    Hosts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hosts record, @Param("example") HostsExample example);

    int updateByExample(@Param("record") Hosts record, @Param("example") HostsExample example);

    int updateByPrimaryKeySelective(Hosts record);

    int updateByPrimaryKey(Hosts record);
}