package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface GuardGroupBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseGuardGroup record);

    int insertSelective(BaseGuardGroup record);

    BaseGuardGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseGuardGroup record);

    int updateByPrimaryKey(BaseGuardGroup record);

    List<BaseGuardGroup> selectAll(@Param("limit") String limit);

    List<BaseGuardGroup> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseGuardGroup> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseGuardGroup> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuardGroup selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseGuardGroup> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseGuardGroup> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuardGroup selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}