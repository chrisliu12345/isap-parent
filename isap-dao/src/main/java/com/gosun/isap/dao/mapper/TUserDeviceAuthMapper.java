package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TUserDeviceAuth;
import com.gosun.isap.dao.po.TUserDeviceAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserDeviceAuthMapper {
    int countByExample(TUserDeviceAuthExample example);

    int deleteByExample(TUserDeviceAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUserDeviceAuth record);

    int insertSelective(TUserDeviceAuth record);

    List<TUserDeviceAuth> selectByExample(TUserDeviceAuthExample example);

    TUserDeviceAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserDeviceAuth record, @Param("example") TUserDeviceAuthExample example);

    int updateByExample(@Param("record") TUserDeviceAuth record, @Param("example") TUserDeviceAuthExample example);

    int updateByPrimaryKeySelective(TUserDeviceAuth record);

    int updateByPrimaryKey(TUserDeviceAuth record);
}