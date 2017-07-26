package com.gosun.isap.dao.mapper;

import com.gosun.isap.dao.po.TVersionJudge;
import com.gosun.isap.dao.po.TVersionJudgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVersionJudgeMapper {
    int countByExample(TVersionJudgeExample example);

    int deleteByExample(TVersionJudgeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TVersionJudge record);

    int insertSelective(TVersionJudge record);

    List<TVersionJudge> selectByExample(TVersionJudgeExample example);

    TVersionJudge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TVersionJudge record, @Param("example") TVersionJudgeExample example);

    int updateByExample(@Param("record") TVersionJudge record, @Param("example") TVersionJudgeExample example);

    int updateByPrimaryKeySelective(TVersionJudge record);

    int updateByPrimaryKey(TVersionJudge record);
}