package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseAlertLogTemplate;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlertLogTemplateBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseAlertLogTemplate record);

    int insertSelective(BaseAlertLogTemplate record);

    BaseAlertLogTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseAlertLogTemplate record);

    int updateByPrimaryKey(BaseAlertLogTemplate record);

    List<BaseAlertLogTemplate> selectAll(@Param("limit") String limit);

    List<BaseAlertLogTemplate> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseAlertLogTemplate> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseAlertLogTemplate> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertLogTemplate selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseAlertLogTemplate> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseAlertLogTemplate> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertLogTemplate selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}