package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseGuardGroupBelong;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface GuardGroupBelongBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseGuardGroupBelong record);

    int insertSelective(BaseGuardGroupBelong record);

    BaseGuardGroupBelong selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseGuardGroupBelong record);

    int updateByPrimaryKey(BaseGuardGroupBelong record);

    List<BaseGuardGroupBelong> selectAll(@Param("limit") String limit);

    List<BaseGuardGroupBelong> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseGuardGroupBelong> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseGuardGroupBelong> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuardGroupBelong selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseGuardGroupBelong> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseGuardGroupBelong> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuardGroupBelong selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}