package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TGuardWeekTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TGuardWeekTimeExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andWeekdaysIsNull() {
            addCriterion("weekdays is null");
            return (Criteria) this;
        }

        public Criteria andWeekdaysIsNotNull() {
            addCriterion("weekdays is not null");
            return (Criteria) this;
        }

        public Criteria andWeekdaysEqualTo(String value) {
            addCriterion("weekdays =", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotEqualTo(String value) {
            addCriterion("weekdays <>", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysGreaterThan(String value) {
            addCriterion("weekdays >", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysGreaterThanOrEqualTo(String value) {
            addCriterion("weekdays >=", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysLessThan(String value) {
            addCriterion("weekdays <", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysLessThanOrEqualTo(String value) {
            addCriterion("weekdays <=", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysLike(String value) {
            addCriterion("weekdays like", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotLike(String value) {
            addCriterion("weekdays not like", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysIn(List<String> values) {
            addCriterion("weekdays in", values, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotIn(List<String> values) {
            addCriterion("weekdays not in", values, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysBetween(String value1, String value2) {
            addCriterion("weekdays between", value1, value2, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotBetween(String value1, String value2) {
            addCriterion("weekdays not between", value1, value2, "weekdays");
            return (Criteria) this;
        }

        public Criteria andBeginTime1IsNull() {
            addCriterion("begin_time1 is null");
            return (Criteria) this;
        }

        public Criteria andBeginTime1IsNotNull() {
            addCriterion("begin_time1 is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTime1EqualTo(Date value) {
            addCriterionForJDBCTime("begin_time1 =", value, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1NotEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time1 <>", value, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1GreaterThan(Date value) {
            addCriterionForJDBCTime("begin_time1 >", value, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time1 >=", value, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1LessThan(Date value) {
            addCriterionForJDBCTime("begin_time1 <", value, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time1 <=", value, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1In(List<Date> values) {
            addCriterionForJDBCTime("begin_time1 in", values, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1NotIn(List<Date> values) {
            addCriterionForJDBCTime("begin_time1 not in", values, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1Between(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time1 between", value1, value2, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime1NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time1 not between", value1, value2, "beginTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1IsNull() {
            addCriterion("end_time1 is null");
            return (Criteria) this;
        }

        public Criteria andEndTime1IsNotNull() {
            addCriterion("end_time1 is not null");
            return (Criteria) this;
        }

        public Criteria andEndTime1EqualTo(Date value) {
            addCriterionForJDBCTime("end_time1 =", value, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1NotEqualTo(Date value) {
            addCriterionForJDBCTime("end_time1 <>", value, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1GreaterThan(Date value) {
            addCriterionForJDBCTime("end_time1 >", value, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time1 >=", value, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1LessThan(Date value) {
            addCriterionForJDBCTime("end_time1 <", value, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time1 <=", value, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1In(List<Date> values) {
            addCriterionForJDBCTime("end_time1 in", values, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1NotIn(List<Date> values) {
            addCriterionForJDBCTime("end_time1 not in", values, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1Between(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time1 between", value1, value2, "endTime1");
            return (Criteria) this;
        }

        public Criteria andEndTime1NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time1 not between", value1, value2, "endTime1");
            return (Criteria) this;
        }

        public Criteria andBeginTime2IsNull() {
            addCriterion("begin_time2 is null");
            return (Criteria) this;
        }

        public Criteria andBeginTime2IsNotNull() {
            addCriterion("begin_time2 is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTime2EqualTo(Date value) {
            addCriterionForJDBCTime("begin_time2 =", value, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2NotEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time2 <>", value, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2GreaterThan(Date value) {
            addCriterionForJDBCTime("begin_time2 >", value, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time2 >=", value, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2LessThan(Date value) {
            addCriterionForJDBCTime("begin_time2 <", value, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time2 <=", value, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2In(List<Date> values) {
            addCriterionForJDBCTime("begin_time2 in", values, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2NotIn(List<Date> values) {
            addCriterionForJDBCTime("begin_time2 not in", values, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2Between(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time2 between", value1, value2, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime2NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time2 not between", value1, value2, "beginTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2IsNull() {
            addCriterion("end_time2 is null");
            return (Criteria) this;
        }

        public Criteria andEndTime2IsNotNull() {
            addCriterion("end_time2 is not null");
            return (Criteria) this;
        }

        public Criteria andEndTime2EqualTo(Date value) {
            addCriterionForJDBCTime("end_time2 =", value, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2NotEqualTo(Date value) {
            addCriterionForJDBCTime("end_time2 <>", value, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2GreaterThan(Date value) {
            addCriterionForJDBCTime("end_time2 >", value, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time2 >=", value, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2LessThan(Date value) {
            addCriterionForJDBCTime("end_time2 <", value, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time2 <=", value, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2In(List<Date> values) {
            addCriterionForJDBCTime("end_time2 in", values, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2NotIn(List<Date> values) {
            addCriterionForJDBCTime("end_time2 not in", values, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2Between(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time2 between", value1, value2, "endTime2");
            return (Criteria) this;
        }

        public Criteria andEndTime2NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time2 not between", value1, value2, "endTime2");
            return (Criteria) this;
        }

        public Criteria andBeginTime3IsNull() {
            addCriterion("begin_time3 is null");
            return (Criteria) this;
        }

        public Criteria andBeginTime3IsNotNull() {
            addCriterion("begin_time3 is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTime3EqualTo(Date value) {
            addCriterionForJDBCTime("begin_time3 =", value, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3NotEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time3 <>", value, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3GreaterThan(Date value) {
            addCriterionForJDBCTime("begin_time3 >", value, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time3 >=", value, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3LessThan(Date value) {
            addCriterionForJDBCTime("begin_time3 <", value, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time3 <=", value, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3In(List<Date> values) {
            addCriterionForJDBCTime("begin_time3 in", values, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3NotIn(List<Date> values) {
            addCriterionForJDBCTime("begin_time3 not in", values, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3Between(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time3 between", value1, value2, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime3NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time3 not between", value1, value2, "beginTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3IsNull() {
            addCriterion("end_time3 is null");
            return (Criteria) this;
        }

        public Criteria andEndTime3IsNotNull() {
            addCriterion("end_time3 is not null");
            return (Criteria) this;
        }

        public Criteria andEndTime3EqualTo(Date value) {
            addCriterionForJDBCTime("end_time3 =", value, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3NotEqualTo(Date value) {
            addCriterionForJDBCTime("end_time3 <>", value, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3GreaterThan(Date value) {
            addCriterionForJDBCTime("end_time3 >", value, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time3 >=", value, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3LessThan(Date value) {
            addCriterionForJDBCTime("end_time3 <", value, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time3 <=", value, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3In(List<Date> values) {
            addCriterionForJDBCTime("end_time3 in", values, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3NotIn(List<Date> values) {
            addCriterionForJDBCTime("end_time3 not in", values, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3Between(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time3 between", value1, value2, "endTime3");
            return (Criteria) this;
        }

        public Criteria andEndTime3NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time3 not between", value1, value2, "endTime3");
            return (Criteria) this;
        }

        public Criteria andBeginTime4IsNull() {
            addCriterion("begin_time4 is null");
            return (Criteria) this;
        }

        public Criteria andBeginTime4IsNotNull() {
            addCriterion("begin_time4 is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTime4EqualTo(Date value) {
            addCriterionForJDBCTime("begin_time4 =", value, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4NotEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time4 <>", value, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4GreaterThan(Date value) {
            addCriterionForJDBCTime("begin_time4 >", value, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time4 >=", value, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4LessThan(Date value) {
            addCriterionForJDBCTime("begin_time4 <", value, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begin_time4 <=", value, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4In(List<Date> values) {
            addCriterionForJDBCTime("begin_time4 in", values, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4NotIn(List<Date> values) {
            addCriterionForJDBCTime("begin_time4 not in", values, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4Between(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time4 between", value1, value2, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andBeginTime4NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("begin_time4 not between", value1, value2, "beginTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4IsNull() {
            addCriterion("end_time4 is null");
            return (Criteria) this;
        }

        public Criteria andEndTime4IsNotNull() {
            addCriterion("end_time4 is not null");
            return (Criteria) this;
        }

        public Criteria andEndTime4EqualTo(Date value) {
            addCriterionForJDBCTime("end_time4 =", value, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4NotEqualTo(Date value) {
            addCriterionForJDBCTime("end_time4 <>", value, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4GreaterThan(Date value) {
            addCriterionForJDBCTime("end_time4 >", value, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time4 >=", value, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4LessThan(Date value) {
            addCriterionForJDBCTime("end_time4 <", value, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4LessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("end_time4 <=", value, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4In(List<Date> values) {
            addCriterionForJDBCTime("end_time4 in", values, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4NotIn(List<Date> values) {
            addCriterionForJDBCTime("end_time4 not in", values, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4Between(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time4 between", value1, value2, "endTime4");
            return (Criteria) this;
        }

        public Criteria andEndTime4NotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("end_time4 not between", value1, value2, "endTime4");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "weekdays,";
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