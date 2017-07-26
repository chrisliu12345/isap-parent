package com.gosun.isap.dao.mapper.face;

import com.gosun.isap.dao.po.face.Alarminfoes;
import com.gosun.isap.dao.po.face.AlarminfoesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlarminfoesMapper {
    int countByExample(AlarminfoesExample example);

    int deleteByExample(AlarminfoesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Alarminfoes record);

    int insertSelective(Alarminfoes record);

    List<Alarminfoes> selectByExample(AlarminfoesExample example);

    Alarminfoes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Alarminfoes record, @Param("example") AlarminfoesExample example);

    int updateByExample(@Param("record") Alarminfoes record, @Param("example") AlarminfoesExample example);

    int updateByPrimaryKeySelective(Alarminfoes record);

    int updateByPrimaryKey(Alarminfoes record);
}