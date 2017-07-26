package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseAlertDetail;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlertDetailBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseAlertDetail record);

    int insertSelective(BaseAlertDetail record);

    BaseAlertDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseAlertDetail record);

    int updateByPrimaryKey(BaseAlertDetail record);

    List<BaseAlertDetail> selectAll(@Param("limit") String limit);

    List<BaseAlertDetail> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseAlertDetail> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseAlertDetail> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertDetail selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseAlertDetail> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseAlertDetail> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlertDetail selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}