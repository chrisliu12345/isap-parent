package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAlertExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TAlertExample() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(Byte value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(Byte value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(Byte value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(Byte value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(Byte value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(Byte value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<Byte> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<Byte> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(Byte value1, Byte value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(Byte value1, Byte value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andDevIdIsNull() {
            addCriterion("dev_id is null");
            return (Criteria) this;
        }

        public Criteria andDevIdIsNotNull() {
            addCriterion("dev_id is not null");
            return (Criteria) this;
        }

        public Criteria andDevIdEqualTo(String value) {
            addCriterion("dev_id =", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotEqualTo(String value) {
            addCriterion("dev_id <>", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdGreaterThan(String value) {
            addCriterion("dev_id >", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdGreaterThanOrEqualTo(String value) {
            addCriterion("dev_id >=", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdLessThan(String value) {
            addCriterion("dev_id <", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdLessThanOrEqualTo(String value) {
            addCriterion("dev_id <=", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdLike(String value) {
            addCriterion("dev_id like", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotLike(String value) {
            addCriterion("dev_id not like", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdIn(List<String> values) {
            addCriterion("dev_id in", values, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotIn(List<String> values) {
            addCriterion("dev_id not in", values, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdBetween(String value1, String value2) {
            addCriterion("dev_id between", value1, value2, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotBetween(String value1, String value2) {
            addCriterion("dev_id not between", value1, value2, "devId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(String value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(String value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(String value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(String value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(String value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLike(String value) {
            addCriterion("department_id like", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotLike(String value) {
            addCriterion("department_id not like", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<String> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<String> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(String value1, String value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(String value1, String value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeIsNull() {
            addCriterion("is_over_time is null");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeIsNotNull() {
            addCriterion("is_over_time is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeEqualTo(Boolean value) {
            addCriterion("is_over_time =", value, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeNotEqualTo(Boolean value) {
            addCriterion("is_over_time <>", value, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeGreaterThan(Boolean value) {
            addCriterion("is_over_time >", value, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_over_time >=", value, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeLessThan(Boolean value) {
            addCriterion("is_over_time <", value, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_over_time <=", value, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeIn(List<Boolean> values) {
            addCriterion("is_over_time in", values, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeNotIn(List<Boolean> values) {
            addCriterion("is_over_time not in", values, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_over_time between", value1, value2, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andIsOverTimeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_over_time not between", value1, value2, "isOverTime");
            return (Criteria) this;
        }

        public Criteria andAlertTypeIsNull() {
            addCriterion("alert_type is null");
            return (Criteria) this;
        }

        public Criteria andAlertTypeIsNotNull() {
            addCriterion("alert_type is not null");
            return (Criteria) this;
        }

        public Criteria andAlertTypeEqualTo(Byte value) {
            addCriterion("alert_type =", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotEqualTo(Byte value) {
            addCriterion("alert_type <>", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeGreaterThan(Byte value) {
            addCriterion("alert_type >", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("alert_type >=", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeLessThan(Byte value) {
            addCriterion("alert_type <", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeLessThanOrEqualTo(Byte value) {
            addCriterion("alert_type <=", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeIn(List<Byte> values) {
            addCriterion("alert_type in", values, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotIn(List<Byte> values) {
            addCriterion("alert_type not in", values, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeBetween(Byte value1, Byte value2) {
            addCriterion("alert_type between", value1, value2, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("alert_type not between", value1, value2, "alertType");
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

        public Criteria andConfirmStartTimeIsNull() {
            addCriterion("confirm_start_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeIsNotNull() {
            addCriterion("confirm_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeEqualTo(Date value) {
            addCriterion("confirm_start_time =", value, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeNotEqualTo(Date value) {
            addCriterion("confirm_start_time <>", value, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeGreaterThan(Date value) {
            addCriterion("confirm_start_time >", value, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_start_time >=", value, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeLessThan(Date value) {
            addCriterion("confirm_start_time <", value, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("confirm_start_time <=", value, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeIn(List<Date> values) {
            addCriterion("confirm_start_time in", values, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeNotIn(List<Date> values) {
            addCriterion("confirm_start_time not in", values, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeBetween(Date value1, Date value2) {
            addCriterion("confirm_start_time between", value1, value2, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("confirm_start_time not between", value1, value2, "confirmStartTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeIsNull() {
            addCriterion("confirm_end_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeIsNotNull() {
            addCriterion("confirm_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeEqualTo(Date value) {
            addCriterion("confirm_end_time =", value, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeNotEqualTo(Date value) {
            addCriterion("confirm_end_time <>", value, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeGreaterThan(Date value) {
            addCriterion("confirm_end_time >", value, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_end_time >=", value, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeLessThan(Date value) {
            addCriterion("confirm_end_time <", value, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("confirm_end_time <=", value, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeIn(List<Date> values) {
            addCriterion("confirm_end_time in", values, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeNotIn(List<Date> values) {
            addCriterion("confirm_end_time not in", values, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeBetween(Date value1, Date value2) {
            addCriterion("confirm_end_time between", value1, value2, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("confirm_end_time not between", value1, value2, "confirmEndTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNull() {
            addCriterion("confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNotNull() {
            addCriterion("confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeEqualTo(Integer value) {
            addCriterion("confirm_time =", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotEqualTo(Integer value) {
            addCriterion("confirm_time <>", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThan(Integer value) {
            addCriterion("confirm_time >", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_time >=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThan(Integer value) {
            addCriterion("confirm_time <", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_time <=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIn(List<Integer> values) {
            addCriterion("confirm_time in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotIn(List<Integer> values) {
            addCriterion("confirm_time not in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeBetween(Integer value1, Integer value2) {
            addCriterion("confirm_time between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_time not between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsIsNull() {
            addCriterion("confirmed_alerts is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsIsNotNull() {
            addCriterion("confirmed_alerts is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsEqualTo(Integer value) {
            addCriterion("confirmed_alerts =", value, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsNotEqualTo(Integer value) {
            addCriterion("confirmed_alerts <>", value, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsGreaterThan(Integer value) {
            addCriterion("confirmed_alerts >", value, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirmed_alerts >=", value, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsLessThan(Integer value) {
            addCriterion("confirmed_alerts <", value, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsLessThanOrEqualTo(Integer value) {
            addCriterion("confirmed_alerts <=", value, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsIn(List<Integer> values) {
            addCriterion("confirmed_alerts in", values, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsNotIn(List<Integer> values) {
            addCriterion("confirmed_alerts not in", values, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsBetween(Integer value1, Integer value2) {
            addCriterion("confirmed_alerts between", value1, value2, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andConfirmedAlertsNotBetween(Integer value1, Integer value2) {
            addCriterion("confirmed_alerts not between", value1, value2, "confirmedAlerts");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andConfirmDescIsNull() {
            addCriterion("confirm_desc is null");
            return (Criteria) this;
        }

        public Criteria andConfirmDescIsNotNull() {
            addCriterion("confirm_desc is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmDescEqualTo(String value) {
            addCriterion("confirm_desc =", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescNotEqualTo(String value) {
            addCriterion("confirm_desc <>", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescGreaterThan(String value) {
            addCriterion("confirm_desc >", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescGreaterThanOrEqualTo(String value) {
            addCriterion("confirm_desc >=", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescLessThan(String value) {
            addCriterion("confirm_desc <", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescLessThanOrEqualTo(String value) {
            addCriterion("confirm_desc <=", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescLike(String value) {
            addCriterion("confirm_desc like", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescNotLike(String value) {
            addCriterion("confirm_desc not like", value, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescIn(List<String> values) {
            addCriterion("confirm_desc in", values, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescNotIn(List<String> values) {
            addCriterion("confirm_desc not in", values, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescBetween(String value1, String value2) {
            addCriterion("confirm_desc between", value1, value2, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andConfirmDescNotBetween(String value1, String value2) {
            addCriterion("confirm_desc not between", value1, value2, "confirmDesc");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "description,dev_id,department_id,confirm_desc,";
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