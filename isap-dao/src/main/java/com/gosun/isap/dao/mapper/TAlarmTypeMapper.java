package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAlarmType;
import com.gosun.isap.dao.po.TAlarmTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAlarmTypeMapper {
    int countByExample(TAlarmTypeExample example);

    int deleteByExample(TAlarmTypeExample example);

    int deleteByPrimaryKey(Long alarmType);

    int insert(TAlarmType record);

    int insertSelective(TAlarmType record);

    List<TAlarmType> selectByExample(TAlarmTypeExample example);

    TAlarmType selectByPrimaryKey(Long alarmType);

    int updateByExampleSelective(@Param("record") TAlarmType record, @Param("example") TAlarmTypeExample example);

    int updateByExample(@Param("record") TAlarmType record, @Param("example") TAlarmTypeExample example);

    int updateByPrimaryKeySelective(TAlarmType record);

    int updateByPrimaryKey(TAlarmType record);
}