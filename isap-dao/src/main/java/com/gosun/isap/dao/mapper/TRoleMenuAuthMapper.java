package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TRoleMenuAuth;
import com.gosun.isap.dao.po.TRoleMenuAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleMenuAuthMapper {
    int countByExample(TRoleMenuAuthExample example);

    int deleteByExample(TRoleMenuAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TRoleMenuAuth record);

    int insertSelective(TRoleMenuAuth record);

    List<TRoleMenuAuth> selectByExample(TRoleMenuAuthExample example);

    TRoleMenuAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TRoleMenuAuth record, @Param("example") TRoleMenuAuthExample example);

    int updateByExample(@Param("record") TRoleMenuAuth record, @Param("example") TRoleMenuAuthExample example);

    int updateByPrimaryKeySelective(TRoleMenuAuth record);

    int updateByPrimaryKey(TRoleMenuAuth record);
}