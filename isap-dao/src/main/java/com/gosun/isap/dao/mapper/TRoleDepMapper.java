package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TRoleDep;
import com.gosun.isap.dao.po.TRoleDepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleDepMapper {
    int countByExample(TRoleDepExample example);

    int deleteByExample(TRoleDepExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TRoleDep record);

    int insertSelective(TRoleDep record);

    List<TRoleDep> selectByExample(TRoleDepExample example);

    TRoleDep selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TRoleDep record, @Param("example") TRoleDepExample example);

    int updateByExample(@Param("record") TRoleDep record, @Param("example") TRoleDepExample example);

    int updateByPrimaryKeySelective(TRoleDep record);

    int updateByPrimaryKey(TRoleDep record);
}