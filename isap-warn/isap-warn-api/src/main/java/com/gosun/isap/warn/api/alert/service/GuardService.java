package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsItem;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import org.apache.ibatis.annotations.Param;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;
import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.LIMIT;

/**
 * <p>创建时间：2017/5/6 15:16</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardService {
    /**
     * 根据 id 获取保安的信息
     *
     * @param id 保安 id
     * @return 保安信息
     */
    String getGuardInfo(long id);

    /**
     * 根据 id 获取保安信息
     *
     * @param id 保安 id
     * @return 保安信息
     */
    Guard getGuard(long id);

    /**
     * @param id 保安 id
     * @return 保安姓名
     */
    String getGuardName(long id);

    /**
     * @param id 保安 id
     * @return 保安电话
     */
    String getGuardPhone(long id);

    /**
     * @param guard Guard
     * @return 添加成功
     */
    boolean addGuard(BaseGuard guard);

    /**
     * @param guards guards
     * @return 添加成功条数
     */
    int addGuards(List<BaseGuard> guards);

    /**
     * @param guard Guard
     * @return 更新成功
     */
    boolean updateGuard(BaseGuard guard);

    /**
     * @param guardId 保安 id
     * @return 删除成功
     */
    boolean deleteGuard(long guardId);

    /**
     * @param guardIds 保安 id 列表
     * @return 删除条数
     */
    int deleteGuard(List<Long> guardIds);

    /**
     * @param keyword 关键字
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安列表
     */
    List<Guard> listGuards(String keyword, List<String> orderBy, SqlLimit limit);

    /**
     * @param keyword 关键字
     * @return 保安数量
     */
    int countGuards(String keyword);

    /**
     * @param id      保安组 id
     * @param keyword 关键字
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安列表
     */
    List<Guard> listGuardsByGroup(long id, String keyword, List<String> orderBy, SqlLimit limit);

    /**
     * @param id      保安组 id
     * @param keyword 关键字
     * @return 保安数量
     */
    int countGuardsByGroup(long id, String keyword);

    /**
     * @param ids     保安组 id 列表
     * @param keyword 关键字
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安列表
     */
    List<Guard> listGuardsByGroup(List<Long> ids, String keyword, List<String> orderBy, SqlLimit limit);

    /**
     * @param ids     保安组 id 列表
     * @param keyword 关键字
     * @return 保安数量
     */
    int countGuardsByGroup(List<Long> ids, String keyword);

    /**
     * @param id      部门 id
     * @param keyword 关键字
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安列表
     */
    List<Guard> listGuardsByDepartmentId(String id, String keyword, List<String> orderBy, SqlLimit limit);

    /**
     * @param id      部门 id
     * @param keyword 关键字
     * @return 保安数量
     */
    int countGuardsByDepartmentId(String id, String keyword);

    /**
     * @param ids     部门 id 列表
     * @param keyword 关键字
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安列表
     */
    List<Guard> listGuardsByDepartmentId(List<String> ids, String keyword, List<String> orderBy, SqlLimit limit);

    /**
     * @param ids     部门 id 列表
     * @param keyword 关键字
     * @return 保安数
     */
    int countGuardsByDepartmentId(List<String> ids, String keyword);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     截止时间
     * @param orderBy 排序
     * @param limit   分页
     * @return 警情列表
     */
    List<Alert> listGuardAlerts(long guardId, Date start, Date end, List<String> orderBy, SqlLimit limit);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     截止时间
     * @return 保安出警次数
     */
    int countGuardAlerts(long guardId, Date start, Date end);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     截止时间
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安出警 id 列表
     */
    List<Long> listGuardAlertsId(long guardId, Date start, Date end, List<String> orderBy, SqlLimit limit);

    /**
     * 手机号是否存在
     *
     * @param phone 手机号
     * @return 是否存在
     */
    boolean isPhoneExist(String phone);

    /**
     * 改变保安分组
     *
     * @param guardId 保安 id
     * @param groupId 小组 id
     * @return 行数
     */
    boolean changeGroup(Long guardId, Long groupId);

    /**
     * @param ids     保安 id 列表
     * @param groupId 保安组 id
     * @return 行数
     */
    int changeGroup(List<Long> ids, Long groupId);

    /**
     * @param key   关键字
     * @param limit 分页
     * @return 相关保安
     */
    List<Guard> search(String key, SqlLimit limit);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @param limit   分页
     * @return 可疑人员询问确认数据
     */
    List<Map<String, Object>> questionedConfirm(long guardId, Date start, Date end, SqlLimit limit);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 可疑人员询问确认数据条数
     */
    int countQuestionedConfirm(long guardId, Date start, Date end);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @param limit   分页
     * @return 未完成出警
     */
    List<Map<String, Object>> unfinishedAlerts(long guardId, Date start, Date end, SqlLimit limit);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 未完成出警数
     */
    int countUnfinishedAlerts(long guardId, Date start, Date end);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 统计数据
     */
    List<StatisticsNode> statistics(long guardId, Date start, Date end);

    /**
     * @param guardId  保安 id
     * @param formData 表单数据
     * @return 操作成功
     * @throws IOException 文件保存异常
     */
    boolean saveUploadResource(Long guardId, MultipartFormDataInput formData) throws IOException;
}
