package com.gosun.isap.dao.mapper.alert.base;

import com.gosun.isap.dao.po.alert.base.BaseGuard;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface GuardBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseGuard record);

    int insertSelective(BaseGuard record);

    BaseGuard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseGuard record);

    int updateByPrimaryKey(BaseGuard record);

    List<BaseGuard> selectAll(@Param("limit") String limit);

    List<BaseGuard> selectAllAndSort(@Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    List<BaseGuard> selectByColumn(@Param("column") String column, @Param("value") Object value, @Param("limit") String limit);

    List<BaseGuard> selectByColumnAndSort(@Param("column") String column, @Param("value") Object value, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuard selectFirstByColumn(@Param("column") String column, @Param("value") Object value);

    List<BaseGuard> selectByColumns(@Param("map") Map<String, Object> map, @Param("limit") String limit);

    List<BaseGuard> selectByColumnsAndSort(@Param("map") Map<String, Object> map, @Param("orderBy") List<String> orderBy, @Param("limit") String limit);

    BaseGuard selectFirstByColumns(@Param("map") Map<String, Object> map);

    Integer totalCount();

    Integer countByColumn(@Param("column") String column, @Param("value") Object value);

    Integer countByColumns(@Param("map") Map<String, Object> map);

    Integer deleteByColumn(@Param("column") String column, @Param("value") Object value);

    Integer deleteByColumns(@Param("map") Map<String, Object> map);
}