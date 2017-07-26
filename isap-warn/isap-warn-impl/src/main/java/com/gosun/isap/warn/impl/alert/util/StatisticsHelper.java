package com.gosun.isap.warn.impl.alert.util;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.gosun.isap.warn.api.alert.model.statistics.AverageItem;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsDiagramData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsParentNode;
import com.gosun.isap.warn.api.alert.service.AlertStatisticsService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.model.statistics.StatisticsLeafNode;
import com.gosun.isap.warn.impl.alert.model.statistics.StatisticsParentNodeImpl;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>创建时间：2017-5-20 17:26</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class StatisticsHelper {
    private static final String TOTAL = "total";
    private static final String TOTAL_DESCRIPTION = "总数";

    private static final String UNPROCESSED = "unprocessed";
    private static final String UNPROCESSED_DESCRIPTION = "未处理警情数";
    private static final String FINISHED = "finished";
    private static final String FINISHED_DESCRIPTION = "处理完毕";
    private static final String PROCESSING = "processing";
    private static final String PROCESSING_DESCRIPTION = "处理中";
    private static final String WAITING_PROCESS = "waitingProcess";
    private static final String WAITING_PROCESS_DESCRIPTION = "等待处理";

    private static final String FALSE_ALERTS = "falseAlerts";
    private static final String FALSE_ALERTS_DESCRIPTION = "误报";

    private static final String NEED_GUARD_ALERTS = "needGuardProcess";
    private static final String NEED_GUARD_ALERTS_DESCRIPTION = "需要出警";

    private static final String GUARD_PROCESSED = "guardProcessed";
    private static final String GUARD_PROCESSED_DESCRIPTION = "实际出警次数";
    private static final String HAS_QUESTION_SUSPECT = "questionedSuspect";
    private static final String HAS_QUESTIONED_DESCRIPTION = "已询问可疑人员";

    private static final String SUSPECT_ALERTS = "suspectAlerts";
    private static final String SUSPECT_ALERTS_DESCRIPTION = "可疑出警";
    private static final String ABNORMAL_ALERTS = "abnormalAlerts";
    private static final String ABNORMAL_ALERTS_DESCRIPTION = "异常出警";

    private static final String SUCCESSFUL = "successful";
    private static final String SUCCESSFUL_DESCRIPTION = "出警成功";
    private static final String FAILED_NEED_GUARD = "failed";
    private static final String FAILED_NEED_GUARD_DESCRIPTION = "出警失败";


    private static final String NO_GUARDS = "noGuards";
    private static final String NO_GUARDS_DESCRIPTION = "没有保安处理";
    private static final String NOT_ARRIVE = "guardNotArrive";
    private static final String NOT_ARRIVE_DESCRIPTION = "保安未到达";
    private static final String GUARD_OVER_TIME = "guardOverTime";
    private static final String GUARD_OVER_TIME_DESCRIPTION = "保安处理超时";
    private static final String GUARD_NO_CALLBACK = "guardNoCallback";
    private static final String GUARD_NO_CALLBACK_DESCRIPTION = "保安 30 分钟内未回电";

    private AlertStatisticsService service;

    private Integer total;
    private Integer waitingProcess;
    private Integer processing;
    private Integer finished;
    private Integer unprocessed;

    private Integer successful;
    private Integer failed;

    private Integer falseAlerts;
    private Integer needGuard;

    private Integer guardProcessed;
    private Integer questionedSuspect;

    private Integer abnormal;
    private Integer suspectAlerts;

    private Integer noGuards;
    private Integer notArrive;
    private Integer guardOverTime;
    private Integer noCallback;

    public StatisticsHelper() {

    }

    private StatisticsHelper(Date start, Date end, String parentId, Long userId) {
        service = getStatisticsService(start, end, parentId, userId);
    }

    private int getTotal() {
        if (total == null) {
            total = service.totalCount();
        }
        return total;
    }

    private int getWaitingProcess() {
        if (waitingProcess == null) {
            waitingProcess = service.waitingProcess();
        }
        return waitingProcess;
    }

    private int getProcessing() {
        if (processing == null) {
            processing = service.processing();
        }
        return processing;
    }

    private int getFinished() {
        if (finished == null) {
            finished = service.finished();
        }
        return finished;
    }

    private int getUnprocessed() {
        if (unprocessed == null) {
            unprocessed = service.unprocessed();
        }
        return unprocessed;
    }

    private int getSuccessful() {
        if (successful == null) {
            successful = service.successful();
        }
        return successful;
    }

    private int getFailed() {
        if (failed == null) {
            failed = service.failed();
        }
        return failed;
    }

    private int getFalseAlerts() {
        if (falseAlerts == null) {
            falseAlerts = service.falseAlerts();
        }
        return falseAlerts;
    }

    private int getNeedGuard() {
        if (needGuard == null) {
            needGuard = service.needGuard();
        }
        return needGuard;
    }

    private int getAbnormal() {
        if (abnormal == null) {
            abnormal = service.abnormal();
        }
        return abnormal;
    }

    private int getSuspectAlerts() {
        if (suspectAlerts == null) {
            suspectAlerts = service.suspect();
        }
        return suspectAlerts;
    }

    private int getNoGuards() {
        if (noGuards == null) {
            noGuards = service.noGuards();
        }
        return noGuards;
    }

    private int getNotArrive() {
        if (notArrive == null) {
            notArrive = service.notArrive();
        }
        return notArrive;
    }

    private int getGuardOverTime() {
        if (guardOverTime == null) {
            guardOverTime = service.guardOverTime();
        }
        return guardOverTime;
    }

    private int getNoCallback() {
        if (noCallback == null) {
            noCallback = service.noCallback();
        }
        return noCallback;
    }

    private int getGuardProcessed() {
        if (guardProcessed == null) {
            guardProcessed = service.guardProcessed();
        }
        return guardProcessed;
    }

    private int getQuestionedSuspect() {
        if (questionedSuspect == null) {
            questionedSuspect = service.hasQuestionSuspect();
        }
        return questionedSuspect;
    }

    private StatisticsParentNode parentNode(int number, String name, String description) {
        StatisticsParentNode parentNode = new StatisticsParentNodeImpl();
        parentNode.setName(name);
        parentNode.setDescription(description);
        parentNode.setNumber(number);
        return parentNode;
    }

    private StatisticsNode childNode(int number, String name, String description, StatisticsParentNode parent) {
        StatisticsNode node = new StatisticsLeafNode();
        node.setDescription(description);
        node.setNumber(number);
        node.setParent(parent);
        node.setName(name);
        return node;
    }

    /**
     * 为 helper 设置 AlertStatisticsService
     *
     * @param service AlertStatisticsService
     */
    public void setService(AlertStatisticsService service) {
        if (service != null) {
            this.service = service;
        }
    }

    /**
     * @return 总数统计（等待处理，处理中，处理完毕，未处理）
     */
    public StatisticsParentNode totalStatistics() {
        StatisticsParentNode total = parentNode(getTotal(), TOTAL, TOTAL_DESCRIPTION);
        childNode(getWaitingProcess(), WAITING_PROCESS, WAITING_PROCESS_DESCRIPTION, total);
        childNode(getProcessing(), PROCESSING, PROCESSING_DESCRIPTION, total);
        childNode(getFinished(), FINISHED, FINISHED_DESCRIPTION, total);
        childNode(getUnprocessed(), UNPROCESSED, UNPROCESSED_DESCRIPTION, total);
        return total;
    }

    /**
     * @return 处理完成的警情统计（误报、可疑出警、异常出警）
     */
    public StatisticsParentNode finishedStatistics() {
        StatisticsParentNode finished = parentNode(getFinished(), FINISHED, FINISHED_DESCRIPTION);
        childNode(getFalseAlerts(), FALSE_ALERTS, FALSE_ALERTS_DESCRIPTION, finished);
        childNode(getSuspectAlerts(), SUSPECT_ALERTS, SUSPECT_ALERTS_DESCRIPTION, finished);
        childNode(getAbnormal(), ABNORMAL_ALERTS, ABNORMAL_ALERTS_DESCRIPTION, finished);

        return finished;
    }

    /**
     * @return 需要出警的警情统计（出警成功，出警失败）
     */
    public StatisticsParentNode needGuardStatusStatistics() {
        StatisticsParentNode needGuard = parentNode(getNeedGuard(), NEED_GUARD_ALERTS, NEED_GUARD_ALERTS_DESCRIPTION);
        childNode(getSuccessful(), SUCCESSFUL, SUCCESSFUL_DESCRIPTION, needGuard);
        childNode(getFailed(), FAILED_NEED_GUARD, FAILED_NEED_GUARD_DESCRIPTION, needGuard);
        return needGuard;
    }

    /**
     * @return 询问可疑人员警情统计
     */
    public StatisticsParentNode questionSuspectStatistics() {
        StatisticsParentNode guardProcessed = parentNode(getGuardProcessed(), GUARD_PROCESSED, GUARD_PROCESSED_DESCRIPTION);
        childNode(getQuestionedSuspect(), HAS_QUESTION_SUSPECT, HAS_QUESTIONED_DESCRIPTION, guardProcessed);
        return guardProcessed;
    }

    /**
     * @return 出警失败统计（没联系到保安，未到达，超时，未回电）
     */
    public StatisticsParentNode failedStatistics() {
        StatisticsParentNode failed = parentNode(getFailed(), FAILED_NEED_GUARD, FAILED_NEED_GUARD_DESCRIPTION);
        childNode(getNoGuards(), NO_GUARDS, NO_GUARDS_DESCRIPTION, failed);
        childNode(getNotArrive(), NOT_ARRIVE, NOT_ARRIVE_DESCRIPTION, failed);
        childNode(getGuardOverTime(), GUARD_OVER_TIME, GUARD_OVER_TIME_DESCRIPTION, failed);
        childNode(getNoCallback(), GUARD_NO_CALLBACK, GUARD_NO_CALLBACK_DESCRIPTION, failed);
        return failed;
    }

    /**
     * @return 所有统计项，全为叶子节点
     */
    public List<StatisticsNode> allNode() {
        List<StatisticsNode> list = new ArrayList<>();
        StatisticsParentNode total = totalStatistics();
        list.add(total.asLeafNode());
        list.addAll(total.getChildren());

        StatisticsParentNode finished = finishedStatistics();
        list.addAll(finished.getChildren());

        StatisticsParentNode needGuard = needGuardStatusStatistics();
        list.add(needGuard.asLeafNode());
        list.addAll(needGuard.getChildren());

        StatisticsParentNode questionSuspect = questionSuspectStatistics();
        list.add(questionSuspect.asLeafNode());
        list.addAll(questionSuspect.getChildren());

        StatisticsParentNode failed = failedStatistics();
        list.addAll(failed.getChildren());

        return list;
    }

    /**
     * @return 平均数据统计
     */
    public List<AverageItem> getAverageItems() {
        Double confirmTime = service.getAverageConfirmTime();
        Double userResponseTime = service.getAverageUserResponseTime();
        Double guardResponseTime = service.getAverageGuardResponseTime();
        Double unprocessed = service.getAverageUnprocessedAlerts();
        Double confirmed = service.getAverageConfirmedAlerts();

        List<AverageItem> list = new ArrayList<>();
        list.add(AverageItem.getInstance("avgConfirmTime", "警情平均确认用时", confirmTime));
        list.add(AverageItem.getInstance("avgUserResponseTime", "警情发生到用户确认平均用时", userResponseTime));
        list.add(AverageItem.getInstance("avgGuardResponseTime", "保安出警平均用时", guardResponseTime));
        list.add(AverageItem.getInstance("avgUnprocessedAlerts", "警情发生时平均未处理条数", unprocessed));
        list.add(AverageItem.getInstance("avgConfirmedAlerts", "警情发生到用户确认期间平均确认的警情数", confirmed));
        return list;
    }

    /**
     * @return 图表数据
     */
    public List<StatisticsDiagramData> getDiagramData() {
        List<StatisticsDiagramData> list = new ArrayList<>();
        StatisticsDiagramData data = StatisticsDiagramData.getInstance("警情状态分布", totalStatistics().getStatisticsData());
        if (data != null) {
            list.add(data);
        }
        data = StatisticsDiagramData.getInstance("警情类型分布", finishedStatistics().getStatisticsData());
        if (data != null) {
            list.add(data);
        }
        data = StatisticsDiagramData.getInstance("出警结果统计", needGuardStatusStatistics().getStatisticsData());
        if (data != null) {
            list.add(data);
        }
        data = StatisticsDiagramData.getInstance("询问可疑人员比例", questionSuspectStatistics().getStatisticsData());
        if (data != null) {
            list.add(data);
        }
        data = StatisticsDiagramData.getInstance("出警失败类型统计", failedStatistics().getStatisticsData());
        if (data != null) {
            list.add(data);
        }
        if (ListUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    /**
     * 获取一个 StatisticsHelper 实例
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param parentId 父部门 id
     * @param userId   用户 id
     * @return StatisticsHelper
     */
    public static StatisticsHelper getInstance(Date start, Date end, String parentId, Long userId) {
        return new StatisticsHelper(start, end, parentId, userId);
    }

    /**
     * 获取一个 StatisticsHelper 实例
     *
     * @param start       开始时间
     * @param end         结束时间
     * @param departments 部门 id 列表
     * @return StatisticsHelper
     */
    public static StatisticsHelper getInstance(Date start, Date end, List<String> departments) {
        StatisticsHelper helper = new StatisticsHelper();
        helper.service = getStatisticsService(start, end, departments);
        return helper;
    }

    /**
     * 获取一个 AlertStatisticsService 实例
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param parentId 父部门 id
     * @param userId   用户 id
     * @return AlertStatisticsService
     */
    public static AlertStatisticsService getStatisticsService(Date start, Date end, String parentId, Long userId) {
        ApplicationContext context = ServiceBean.getSpringContext();
        AlertStatisticsService service = context.getBean(AlertStatisticsService.class);
        service.init(start, end, parentId, userId);
        return service;
    }

    /**
     * 获取一个 AlertStatisticsService 实例
     *
     * @param start         开始时间
     * @param end           截止时间
     * @param departmentIds 部门 id
     * @return AlertStatisticsService
     */
    public static AlertStatisticsService getStatisticsService(Date start, Date end, List<String> departmentIds) {
        ApplicationContext context = ServiceBean.getSpringContext();
        AlertStatisticsService service = context.getBean(AlertStatisticsService.class);
        service.init(start, end, departmentIds);
        return service;
    }
}
