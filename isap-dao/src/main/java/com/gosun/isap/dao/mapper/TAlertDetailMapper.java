package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TAlertDetail;
import com.gosun.isap.dao.po.TAlertDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAlertDetailMapper {
    int countByExample(TAlertDetailExample example);

    int deleteByExample(TAlertDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAlertDetail record);

    int insertSelective(TAlertDetail record);

    List<TAlertDetail> selectByExample(TAlertDetailExample example);

    TAlertDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAlertDetail record, @Param("example") TAlertDetailExample example);

    int updateByExample(@Param("record") TAlertDetail record, @Param("example") TAlertDetailExample example);

    int updateByPrimaryKeySelective(TAlertDetail record);

    int updateByPrimaryKey(TAlertDetail record);
}