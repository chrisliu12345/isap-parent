package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAlarmRecord;
import com.gosun.isap.dao.po.TAlarmRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAlarmRecordMapper {
    int countByExample(TAlarmRecordExample example);

    int deleteByExample(TAlarmRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAlarmRecord record);

    int insertSelective(TAlarmRecord record);

    List<TAlarmRecord> selectByExample(TAlarmRecordExample example);

    TAlarmRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAlarmRecord record, @Param("example") TAlarmRecordExample example);

    int updateByExample(@Param("record") TAlarmRecord record, @Param("example") TAlarmRecordExample example);

    int updateByPrimaryKeySelective(TAlarmRecord record);

    int updateByPrimaryKey(TAlarmRecord record);
}