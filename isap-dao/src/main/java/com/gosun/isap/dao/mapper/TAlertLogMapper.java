package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAlertLog;
import com.gosun.isap.dao.po.TAlertLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAlertLogMapper {
    int countByExample(TAlertLogExample example);

    int deleteByExample(TAlertLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAlertLog record);

    int insertSelective(TAlertLog record);

    List<TAlertLog> selectByExample(TAlertLogExample example);

    TAlertLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAlertLog record, @Param("example") TAlertLogExample example);

    int updateByExample(@Param("record") TAlertLog record, @Param("example") TAlertLogExample example);

    int updateByPrimaryKeySelective(TAlertLog record);

    int updateByPrimaryKey(TAlertLog record);
}