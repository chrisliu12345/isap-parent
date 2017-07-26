package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAlertTimeLimit;
import com.gosun.isap.dao.po.TAlertTimeLimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAlertTimeLimitMapper {
    int countByExample(TAlertTimeLimitExample example);

    int deleteByExample(TAlertTimeLimitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAlertTimeLimit record);

    int insertSelective(TAlertTimeLimit record);

    List<TAlertTimeLimit> selectByExample(TAlertTimeLimitExample example);

    TAlertTimeLimit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAlertTimeLimit record, @Param("example") TAlertTimeLimitExample example);

    int updateByExample(@Param("record") TAlertTimeLimit record, @Param("example") TAlertTimeLimitExample example);

    int updateByPrimaryKeySelective(TAlertTimeLimit record);

    int updateByPrimaryKey(TAlertTimeLimit record);
}