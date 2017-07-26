package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseAlertTimeLimit;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlertTimeLimitBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseAlertTimeLimit record);

    int insertSelective(BaseAlertTimeLimit record);

    BaseAlertTimeLimit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseAlertTimeLimit record);

    int updateByPrimaryKey(BaseAlertTimeLimit record);

    List<BaseAlertTimeLimit> selectAll(@Param("limit") String limit);

    List<BaseAlertTimeLimit> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseAlertTimeLimit> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseAlertTimeLimit> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertTimeLimit selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseAlertTimeLimit> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseAlertTimeLimit> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertTimeLimit selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}