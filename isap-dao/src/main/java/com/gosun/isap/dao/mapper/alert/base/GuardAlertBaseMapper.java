package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseGuardAlert;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface GuardAlertBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseGuardAlert record);

    int insertSelective(BaseGuardAlert record);

    BaseGuardAlert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseGuardAlert record);

    int updateByPrimaryKey(BaseGuardAlert record);

    List<BaseGuardAlert> selectAll(@Param("limit") String limit);

    List<BaseGuardAlert> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseGuardAlert> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseGuardAlert> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuardAlert selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseGuardAlert> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseGuardAlert> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuardAlert selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}