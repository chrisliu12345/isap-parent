package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.List;

public class TAlertDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TAlertDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAlertIdIsNull() {
            addCriterion("alert_id is null");
            return (Criteria) this;
        }

        public Criteria andAlertIdIsNotNull() {
            addCriterion("alert_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlertIdEqualTo(Long value) {
            addCriterion("alert_id =", value, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdNotEqualTo(Long value) {
            addCriterion("alert_id <>", value, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdGreaterThan(Long value) {
            addCriterion("alert_id >", value, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdGreaterThanOrEqualTo(Long value) {
            addCriterion("alert_id >=", value, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdLessThan(Long value) {
            addCriterion("alert_id <", value, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdLessThanOrEqualTo(Long value) {
            addCriterion("alert_id <=", value, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdIn(List<Long> values) {
            addCriterion("alert_id in", values, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdNotIn(List<Long> values) {
            addCriterion("alert_id not in", values, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdBetween(Long value1, Long value2) {
            addCriterion("alert_id between", value1, value2, "alertId");
            return (Criteria) this;
        }

        public Criteria andAlertIdNotBetween(Long value1, Long value2) {
            addCriterion("alert_id not between", value1, value2, "alertId");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeIsNull() {
            addCriterion("user_response_time is null");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeIsNotNull() {
            addCriterion("user_response_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeEqualTo(Integer value) {
            addCriterion("user_response_time =", value, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeNotEqualTo(Integer value) {
            addCriterion("user_response_time <>", value, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeGreaterThan(Integer value) {
            addCriterion("user_response_time >", value, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_response_time >=", value, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeLessThan(Integer value) {
            addCriterion("user_response_time <", value, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeLessThanOrEqualTo(Integer value) {
            addCriterion("user_response_time <=", value, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeIn(List<Integer> values) {
            addCriterion("user_response_time in", values, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeNotIn(List<Integer> values) {
            addCriterion("user_response_time not in", values, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeBetween(Integer value1, Integer value2) {
            addCriterion("user_response_time between", value1, value2, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserResponseTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_response_time not between", value1, value2, "userResponseTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeIsNull() {
            addCriterion("user_process_time is null");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeIsNotNull() {
            addCriterion("user_process_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeEqualTo(Integer value) {
            addCriterion("user_process_time =", value, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeNotEqualTo(Integer value) {
            addCriterion("user_process_time <>", value, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeGreaterThan(Integer value) {
            addCriterion("user_process_time >", value, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_process_time >=", value, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeLessThan(Integer value) {
            addCriterion("user_process_time <", value, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeLessThanOrEqualTo(Integer value) {
            addCriterion("user_process_time <=", value, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeIn(List<Integer> values) {
            addCriterion("user_process_time in", values, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeNotIn(List<Integer> values) {
            addCriterion("user_process_time not in", values, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeBetween(Integer value1, Integer value2) {
            addCriterion("user_process_time between", value1, value2, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andUserProcessTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_process_time not between", value1, value2, "userProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeIsNull() {
            addCriterion("guard_response_time is null");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeIsNotNull() {
            addCriterion("guard_response_time is not null");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeEqualTo(Integer value) {
            addCriterion("guard_response_time =", value, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeNotEqualTo(Integer value) {
            addCriterion("guard_response_time <>", value, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeGreaterThan(Integer value) {
            addCriterion("guard_response_time >", value, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("guard_response_time >=", value, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeLessThan(Integer value) {
            addCriterion("guard_response_time <", value, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeLessThanOrEqualTo(Integer value) {
            addCriterion("guard_response_time <=", value, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeIn(List<Integer> values) {
            addCriterion("guard_response_time in", values, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeNotIn(List<Integer> values) {
            addCriterion("guard_response_time not in", values, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeBetween(Integer value1, Integer value2) {
            addCriterion("guard_response_time between", value1, value2, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardResponseTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("guard_response_time not between", value1, value2, "guardResponseTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeIsNull() {
            addCriterion("guard_process_time is null");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeIsNotNull() {
            addCriterion("guard_process_time is not null");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeEqualTo(Integer value) {
            addCriterion("guard_process_time =", value, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeNotEqualTo(Integer value) {
            addCriterion("guard_process_time <>", value, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeGreaterThan(Integer value) {
            addCriterion("guard_process_time >", value, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("guard_process_time >=", value, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeLessThan(Integer value) {
            addCriterion("guard_process_time <", value, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeLessThanOrEqualTo(Integer value) {
            addCriterion("guard_process_time <=", value, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeIn(List<Integer> values) {
            addCriterion("guard_process_time in", values, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeNotIn(List<Integer> values) {
            addCriterion("guard_process_time not in", values, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeBetween(Integer value1, Integer value2) {
            addCriterion("guard_process_time between", value1, value2, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andGuardProcessTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("guard_process_time not between", value1, value2, "guardProcessTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeIsNull() {
            addCriterion("total_time is null");
            return (Criteria) this;
        }

        public Criteria andTotalTimeIsNotNull() {
            addCriterion("total_time is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTimeEqualTo(Integer value) {
            addCriterion("total_time =", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeNotEqualTo(Integer value) {
            addCriterion("total_time <>", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeGreaterThan(Integer value) {
            addCriterion("total_time >", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_time >=", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeLessThan(Integer value) {
            addCriterion("total_time <", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeLessThanOrEqualTo(Integer value) {
            addCriterion("total_time <=", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeIn(List<Integer> values) {
            addCriterion("total_time in", values, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeNotIn(List<Integer> values) {
            addCriterion("total_time not in", values, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeBetween(Integer value1, Integer value2) {
            addCriterion("total_time between", value1, value2, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("total_time not between", value1, value2, "totalTime");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsIsNull() {
            addCriterion("false_alerts is null");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsIsNotNull() {
            addCriterion("false_alerts is not null");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsEqualTo(Integer value) {
            addCriterion("false_alerts =", value, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsNotEqualTo(Integer value) {
            addCriterion("false_alerts <>", value, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsGreaterThan(Integer value) {
            addCriterion("false_alerts >", value, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsGreaterThanOrEqualTo(Integer value) {
            addCriterion("false_alerts >=", value, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsLessThan(Integer value) {
            addCriterion("false_alerts <", value, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsLessThanOrEqualTo(Integer value) {
            addCriterion("false_alerts <=", value, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsIn(List<Integer> values) {
            addCriterion("false_alerts in", values, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsNotIn(List<Integer> values) {
            addCriterion("false_alerts not in", values, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsBetween(Integer value1, Integer value2) {
            addCriterion("false_alerts between", value1, value2, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andFalseAlertsNotBetween(Integer value1, Integer value2) {
            addCriterion("false_alerts not between", value1, value2, "falseAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsIsNull() {
            addCriterion("confirm_alerts is null");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsIsNotNull() {
            addCriterion("confirm_alerts is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsEqualTo(Integer value) {
            addCriterion("confirm_alerts =", value, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsNotEqualTo(Integer value) {
            addCriterion("confirm_alerts <>", value, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsGreaterThan(Integer value) {
            addCriterion("confirm_alerts >", value, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_alerts >=", value, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsLessThan(Integer value) {
            addCriterion("confirm_alerts <", value, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_alerts <=", value, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsIn(List<Integer> values) {
            addCriterion("confirm_alerts in", values, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsNotIn(List<Integer> values) {
            addCriterion("confirm_alerts not in", values, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsBetween(Integer value1, Integer value2) {
            addCriterion("confirm_alerts between", value1, value2, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmAlertsNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_alerts not between", value1, value2, "confirmAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsIsNull() {
            addCriterion("unprocessed_alerts is null");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsIsNotNull() {
            addCriterion("unprocessed_alerts is not null");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsEqualTo(Integer value) {
            addCriterion("unprocessed_alerts =", value, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsNotEqualTo(Integer value) {
            addCriterion("unprocessed_alerts <>", value, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsGreaterThan(Integer value) {
            addCriterion("unprocessed_alerts >", value, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsGreaterThanOrEqualTo(Integer value) {
            addCriterion("unprocessed_alerts >=", value, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsLessThan(Integer value) {
            addCriterion("unprocessed_alerts <", value, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsLessThanOrEqualTo(Integer value) {
            addCriterion("unprocessed_alerts <=", value, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsIn(List<Integer> values) {
            addCriterion("unprocessed_alerts in", values, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsNotIn(List<Integer> values) {
            addCriterion("unprocessed_alerts not in", values, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsBetween(Integer value1, Integer value2) {
            addCriterion("unprocessed_alerts between", value1, value2, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andUnprocessedAlertsNotBetween(Integer value1, Integer value2) {
            addCriterion("unprocessed_alerts not between", value1, value2, "unprocessedAlerts");
            return (Criteria) this;
        }

        public Criteria andIsCallBackIsNull() {
            addCriterion("is_call_back is null");
            return (Criteria) this;
        }

        public Criteria andIsCallBackIsNotNull() {
            addCriterion("is_call_back is not null");
            return (Criteria) this;
        }

        public Criteria andIsCallBackEqualTo(Boolean value) {
            addCriterion("is_call_back =", value, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackNotEqualTo(Boolean value) {
            addCriterion("is_call_back <>", value, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackGreaterThan(Boolean value) {
            addCriterion("is_call_back >", value, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_call_back >=", value, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackLessThan(Boolean value) {
            addCriterion("is_call_back <", value, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackLessThanOrEqualTo(Boolean value) {
            addCriterion("is_call_back <=", value, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackIn(List<Boolean> values) {
            addCriterion("is_call_back in", values, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackNotIn(List<Boolean> values) {
            addCriterion("is_call_back not in", values, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackBetween(Boolean value1, Boolean value2) {
            addCriterion("is_call_back between", value1, value2, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsCallBackNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_call_back not between", value1, value2, "isCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackIsNull() {
            addCriterion("is_second_call_back is null");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackIsNotNull() {
            addCriterion("is_second_call_back is not null");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackEqualTo(Boolean value) {
            addCriterion("is_second_call_back =", value, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackNotEqualTo(Boolean value) {
            addCriterion("is_second_call_back <>", value, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackGreaterThan(Boolean value) {
            addCriterion("is_second_call_back >", value, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_second_call_back >=", value, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackLessThan(Boolean value) {
            addCriterion("is_second_call_back <", value, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackLessThanOrEqualTo(Boolean value) {
            addCriterion("is_second_call_back <=", value, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackIn(List<Boolean> values) {
            addCriterion("is_second_call_back in", values, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackNotIn(List<Boolean> values) {
            addCriterion("is_second_call_back not in", values, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackBetween(Boolean value1, Boolean value2) {
            addCriterion("is_second_call_back between", value1, value2, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsSecondCallBackNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_second_call_back not between", value1, value2, "isSecondCallBack");
            return (Criteria) this;
        }

        public Criteria andIsArrivedIsNull() {
            addCriterion("is_arrived is null");
            return (Criteria) this;
        }

        public Criteria andIsArrivedIsNotNull() {
            addCriterion("is_arrived is not null");
            return (Criteria) this;
        }

        public Criteria andIsArrivedEqualTo(Boolean value) {
            addCriterion("is_arrived =", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedNotEqualTo(Boolean value) {
            addCriterion("is_arrived <>", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedGreaterThan(Boolean value) {
            addCriterion("is_arrived >", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_arrived >=", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedLessThan(Boolean value) {
            addCriterion("is_arrived <", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_arrived <=", value, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedIn(List<Boolean> values) {
            addCriterion("is_arrived in", values, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedNotIn(List<Boolean> values) {
            addCriterion("is_arrived not in", values, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_arrived between", value1, value2, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsArrivedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_arrived not between", value1, value2, "isArrived");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectIsNull() {
            addCriterion("is_question_suspect is null");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectIsNotNull() {
            addCriterion("is_question_suspect is not null");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectEqualTo(Boolean value) {
            addCriterion("is_question_suspect =", value, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectNotEqualTo(Boolean value) {
            addCriterion("is_question_suspect <>", value, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectGreaterThan(Boolean value) {
            addCriterion("is_question_suspect >", value, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_question_suspect >=", value, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectLessThan(Boolean value) {
            addCriterion("is_question_suspect <", value, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectLessThanOrEqualTo(Boolean value) {
            addCriterion("is_question_suspect <=", value, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectIn(List<Boolean> values) {
            addCriterion("is_question_suspect in", values, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectNotIn(List<Boolean> values) {
            addCriterion("is_question_suspect not in", values, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectBetween(Boolean value1, Boolean value2) {
            addCriterion("is_question_suspect between", value1, value2, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_question_suspect not between", value1, value2, "isQuestionSuspect");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedIsNull() {
            addCriterion("is_question_suspect_confirmed is null");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedIsNotNull() {
            addCriterion("is_question_suspect_confirmed is not null");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedEqualTo(Boolean value) {
            addCriterion("is_question_suspect_confirmed =", value, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedNotEqualTo(Boolean value) {
            addCriterion("is_question_suspect_confirmed <>", value, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedGreaterThan(Boolean value) {
            addCriterion("is_question_suspect_confirmed >", value, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_question_suspect_confirmed >=", value, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedLessThan(Boolean value) {
            addCriterion("is_question_suspect_confirmed <", value, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_question_suspect_confirmed <=", value, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedIn(List<Boolean> values) {
            addCriterion("is_question_suspect_confirmed in", values, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedNotIn(List<Boolean> values) {
            addCriterion("is_question_suspect_confirmed not in", values, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_question_suspect_confirmed between", value1, value2, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsQuestionSuspectConfirmedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_question_suspect_confirmed not between", value1, value2, "isQuestionSuspectConfirmed");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeIsNull() {
            addCriterion("is_user_over_time is null");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeIsNotNull() {
            addCriterion("is_user_over_time is not null");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeEqualTo(Boolean value) {
            addCriterion("is_user_over_time =", value, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeNotEqualTo(Boolean value) {
            addCriterion("is_user_over_time <>", value, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeGreaterThan(Boolean value) {
            addCriterion("is_user_over_time >", value, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_user_over_time >=", value, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeLessThan(Boolean value) {
            addCriterion("is_user_over_time <", value, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_user_over_time <=", value, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeIn(List<Boolean> values) {
            addCriterion("is_user_over_time in", values, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeNotIn(List<Boolean> values) {
            addCriterion("is_user_over_time not in", values, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_user_over_time between", value1, value2, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsUserOverTimeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_user_over_time not between", value1, value2, "isUserOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeIsNull() {
            addCriterion("is_guard_over_time is null");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeIsNotNull() {
            addCriterion("is_guard_over_time is not null");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeEqualTo(Boolean value) {
            addCriterion("is_guard_over_time =", value, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeNotEqualTo(Boolean value) {
            addCriterion("is_guard_over_time <>", value, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeGreaterThan(Boolean value) {
            addCriterion("is_guard_over_time >", value, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_guard_over_time >=", value, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeLessThan(Boolean value) {
            addCriterion("is_guard_over_time <", value, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_guard_over_time <=", value, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeIn(List<Boolean> values) {
            addCriterion("is_guard_over_time in", values, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeNotIn(List<Boolean> values) {
            addCriterion("is_guard_over_time not in", values, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_guard_over_time between", value1, value2, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andIsGuardOverTimeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_guard_over_time not between", value1, value2, "isGuardOverTime");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeIsNull() {
            addCriterion("failed_reason_type is null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeIsNotNull() {
            addCriterion("failed_reason_type is not null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeEqualTo(Byte value) {
            addCriterion("failed_reason_type =", value, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeNotEqualTo(Byte value) {
            addCriterion("failed_reason_type <>", value, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeGreaterThan(Byte value) {
            addCriterion("failed_reason_type >", value, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("failed_reason_type >=", value, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeLessThan(Byte value) {
            addCriterion("failed_reason_type <", value, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeLessThanOrEqualTo(Byte value) {
            addCriterion("failed_reason_type <=", value, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeIn(List<Byte> values) {
            addCriterion("failed_reason_type in", values, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeNotIn(List<Byte> values) {
            addCriterion("failed_reason_type not in", values, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeBetween(Byte value1, Byte value2) {
            addCriterion("failed_reason_type between", value1, value2, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("failed_reason_type not between", value1, value2, "failedReasonType");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIsNull() {
            addCriterion("failed_reason is null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIsNotNull() {
            addCriterion("failed_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonEqualTo(String value) {
            addCriterion("failed_reason =", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotEqualTo(String value) {
            addCriterion("failed_reason <>", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonGreaterThan(String value) {
            addCriterion("failed_reason >", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("failed_reason >=", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLessThan(String value) {
            addCriterion("failed_reason <", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLessThanOrEqualTo(String value) {
            addCriterion("failed_reason <=", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLike(String value) {
            addCriterion("failed_reason like", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotLike(String value) {
            addCriterion("failed_reason not like", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIn(List<String> values) {
            addCriterion("failed_reason in", values, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotIn(List<String> values) {
            addCriterion("failed_reason not in", values, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonBetween(String value1, String value2) {
            addCriterion("failed_reason between", value1, value2, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotBetween(String value1, String value2) {
            addCriterion("failed_reason not between", value1, value2, "failedReason");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "failed_reason,";
        }

        protected boolean isStringColumnExist(String value) {
            String [] columns = getStringColumns().split(",");
            for (String s : columns) {
                if (s.equals(value)) {
                    return true;
                }
            }
            return false;
        }

        public Criteria andGeneralLike(String field, String value) {
            if (isStringColumnExist(field)) {
                addCriterion(field + " like ", value, field);
            }
            return (Criteria) this;
        }

        public Criteria andGeneralNotLike(String field, String value) {
            if (isStringColumnExist(field)) {
                addCriterion(field + " not like ", value, field);
            }
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}