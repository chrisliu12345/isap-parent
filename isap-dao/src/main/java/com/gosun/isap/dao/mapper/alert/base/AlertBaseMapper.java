package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseAlert;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlertBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseAlert record);

    int insertSelective(BaseAlert record);

    BaseAlert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseAlert record);

    int updateByPrimaryKey(BaseAlert record);

    List<BaseAlert> selectAll(@Param("limit") String limit);

    List<BaseAlert> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseAlert> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseAlert> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlert selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseAlert> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseAlert> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseAlert selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}