package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlertResourceBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseAlertResource record);

    int insertSelective(BaseAlertResource record);

    BaseAlertResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseAlertResource record);

    int updateByPrimaryKey(BaseAlertResource record);

    List<BaseAlertResource> selectAll(@Param("limit") String limit);

    List<BaseAlertResource> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseAlertResource> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseAlertResource> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertResource selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseAlertResource> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseAlertResource> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertResource selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}