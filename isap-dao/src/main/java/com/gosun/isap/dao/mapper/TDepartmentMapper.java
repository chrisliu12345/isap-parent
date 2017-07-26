package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDepartmentMapper {
    int countByExample(TDepartmentExample example);

    int deleteByExample(TDepartmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TDepartment record);

    int insertSelective(TDepartment record);

    List<TDepartment> selectByExample(TDepartmentExample example);

    TDepartment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);

    int updateByExample(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);

    int updateByPrimaryKeySelective(TDepartment record);

    int updateByPrimaryKey(TDepartment record);
}