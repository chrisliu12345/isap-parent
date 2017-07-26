package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDeviceMapper {
    int countByExample(TDeviceExample example);

    int deleteByExample(TDeviceExample example);

    int deleteByPrimaryKey(String id);

    int insert(TDevice record);

    int insertSelective(TDevice record);

    List<TDevice> selectByExample(TDeviceExample example);

    TDevice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TDevice record, @Param("example") TDeviceExample example);

    int updateByExample(@Param("record") TDevice record, @Param("example") TDeviceExample example);

    int updateByPrimaryKeySelective(TDevice record);

    int updateByPrimaryKey(TDevice record);
}