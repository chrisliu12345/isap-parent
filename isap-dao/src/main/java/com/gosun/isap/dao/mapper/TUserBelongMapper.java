package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TUserBelong;
import com.gosun.isap.dao.po.TUserBelongExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserBelongMapper {
    int countByExample(TUserBelongExample example);

    int deleteByExample(TUserBelongExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUserBelong record);

    int insertSelective(TUserBelong record);

    List<TUserBelong> selectByExample(TUserBelongExample example);

    TUserBelong selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserBelong record, @Param("example") TUserBelongExample example);

    int updateByExample(@Param("record") TUserBelong record, @Param("example") TUserBelongExample example);

    int updateByPrimaryKeySelective(TUserBelong record);

    int updateByPrimaryKey(TUserBelong record);
}