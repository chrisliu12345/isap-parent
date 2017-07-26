package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TRoleDevAuth;
import com.gosun.isap.dao.po.TRoleDevAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleDevAuthMapper {
    int countByExample(TRoleDevAuthExample example);

    int deleteByExample(TRoleDevAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TRoleDevAuth record);

    int insertSelective(TRoleDevAuth record);

    List<TRoleDevAuth> selectByExample(TRoleDevAuthExample example);

    TRoleDevAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TRoleDevAuth record, @Param("example") TRoleDevAuthExample example);

    int updateByExample(@Param("record") TRoleDevAuth record, @Param("example") TRoleDevAuthExample example);

    int updateByPrimaryKeySelective(TRoleDevAuth record);

    int updateByPrimaryKey(TRoleDevAuth record);
}