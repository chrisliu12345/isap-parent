package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseAlertLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlertLogBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseAlertLog record);

    int insertSelective(BaseAlertLog record);

    BaseAlertLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseAlertLog record);

    int updateByPrimaryKey(BaseAlertLog record);

    List<BaseAlertLog> selectAll(@Param("limit") String limit);

    List<BaseAlertLog> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseAlertLog> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseAlertLog> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertLog selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseAlertLog> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseAlertLog> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertLog selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}