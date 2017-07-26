package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAlarmRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TAlarmRecordExample() {
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

        public Criteria andAlarmTypeIsNull() {
            addCriterion("alarm_type is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIsNotNull() {
            addCriterion("alarm_type is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeEqualTo(Long value) {
            addCriterion("alarm_type =", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotEqualTo(Long value) {
            addCriterion("alarm_type <>", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeGreaterThan(Long value) {
            addCriterion("alarm_type >", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("alarm_type >=", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLessThan(Long value) {
            addCriterion("alarm_type <", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLessThanOrEqualTo(Long value) {
            addCriterion("alarm_type <=", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIn(List<Long> values) {
            addCriterion("alarm_type in", values, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotIn(List<Long> values) {
            addCriterion("alarm_type not in", values, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeBetween(Long value1, Long value2) {
            addCriterion("alarm_type between", value1, value2, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotBetween(Long value1, Long value2) {
            addCriterion("alarm_type not between", value1, value2, "alarmType");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Byte value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Byte value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Byte value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Byte value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Byte value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Byte> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Byte> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Byte value1, Byte value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelIsNull() {
            addCriterion("alarm_level is null");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelIsNotNull() {
            addCriterion("alarm_level is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelEqualTo(Byte value) {
            addCriterion("alarm_level =", value, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelNotEqualTo(Byte value) {
            addCriterion("alarm_level <>", value, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelGreaterThan(Byte value) {
            addCriterion("alarm_level >", value, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("alarm_level >=", value, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelLessThan(Byte value) {
            addCriterion("alarm_level <", value, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelLessThanOrEqualTo(Byte value) {
            addCriterion("alarm_level <=", value, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelIn(List<Byte> values) {
            addCriterion("alarm_level in", values, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelNotIn(List<Byte> values) {
            addCriterion("alarm_level not in", values, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelBetween(Byte value1, Byte value2) {
            addCriterion("alarm_level between", value1, value2, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("alarm_level not between", value1, value2, "alarmLevel");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeIsNull() {
            addCriterion("alarm_time is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeIsNotNull() {
            addCriterion("alarm_time is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeEqualTo(Date value) {
            addCriterion("alarm_time =", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeNotEqualTo(Date value) {
            addCriterion("alarm_time <>", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeGreaterThan(Date value) {
            addCriterion("alarm_time >", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("alarm_time >=", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeLessThan(Date value) {
            addCriterion("alarm_time <", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeLessThanOrEqualTo(Date value) {
            addCriterion("alarm_time <=", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeIn(List<Date> values) {
            addCriterion("alarm_time in", values, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeNotIn(List<Date> values) {
            addCriterion("alarm_time not in", values, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeBetween(Date value1, Date value2) {
            addCriterion("alarm_time between", value1, value2, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeNotBetween(Date value1, Date value2) {
            addCriterion("alarm_time not between", value1, value2, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmDataIsNull() {
            addCriterion("alarm_data is null");
            return (Criteria) this;
        }

        public Criteria andAlarmDataIsNotNull() {
            addCriterion("alarm_data is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmDataEqualTo(String value) {
            addCriterion("alarm_data =", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataNotEqualTo(String value) {
            addCriterion("alarm_data <>", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataGreaterThan(String value) {
            addCriterion("alarm_data >", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataGreaterThanOrEqualTo(String value) {
            addCriterion("alarm_data >=", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataLessThan(String value) {
            addCriterion("alarm_data <", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataLessThanOrEqualTo(String value) {
            addCriterion("alarm_data <=", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataLike(String value) {
            addCriterion("alarm_data like", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataNotLike(String value) {
            addCriterion("alarm_data not like", value, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataIn(List<String> values) {
            addCriterion("alarm_data in", values, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataNotIn(List<String> values) {
            addCriterion("alarm_data not in", values, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataBetween(String value1, String value2) {
            addCriterion("alarm_data between", value1, value2, "alarmData");
            return (Criteria) this;
        }

        public Criteria andAlarmDataNotBetween(String value1, String value2) {
            addCriterion("alarm_data not between", value1, value2, "alarmData");
            return (Criteria) this;
        }

        public Criteria andConfirmIsNull() {
            addCriterion("confirm is null");
            return (Criteria) this;
        }

        public Criteria andConfirmIsNotNull() {
            addCriterion("confirm is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmEqualTo(Byte value) {
            addCriterion("confirm =", value, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmNotEqualTo(Byte value) {
            addCriterion("confirm <>", value, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmGreaterThan(Byte value) {
            addCriterion("confirm >", value, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmGreaterThanOrEqualTo(Byte value) {
            addCriterion("confirm >=", value, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmLessThan(Byte value) {
            addCriterion("confirm <", value, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmLessThanOrEqualTo(Byte value) {
            addCriterion("confirm <=", value, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmIn(List<Byte> values) {
            addCriterion("confirm in", values, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmNotIn(List<Byte> values) {
            addCriterion("confirm not in", values, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmBetween(Byte value1, Byte value2) {
            addCriterion("confirm between", value1, value2, "confirm");
            return (Criteria) this;
        }

        public Criteria andConfirmNotBetween(Byte value1, Byte value2) {
            addCriterion("confirm not between", value1, value2, "confirm");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNull() {
            addCriterion("check_user is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNotNull() {
            addCriterion("check_user is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserEqualTo(String value) {
            addCriterion("check_user =", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotEqualTo(String value) {
            addCriterion("check_user <>", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThan(String value) {
            addCriterion("check_user >", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThanOrEqualTo(String value) {
            addCriterion("check_user >=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThan(String value) {
            addCriterion("check_user <", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThanOrEqualTo(String value) {
            addCriterion("check_user <=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLike(String value) {
            addCriterion("check_user like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotLike(String value) {
            addCriterion("check_user not like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserIn(List<String> values) {
            addCriterion("check_user in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotIn(List<String> values) {
            addCriterion("check_user not in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserBetween(String value1, String value2) {
            addCriterion("check_user between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotBetween(String value1, String value2) {
            addCriterion("check_user not between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionIsNull() {
            addCriterion("suggestion is null");
            return (Criteria) this;
        }

        public Criteria andSuggestionIsNotNull() {
            addCriterion("suggestion is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestionEqualTo(String value) {
            addCriterion("suggestion =", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionNotEqualTo(String value) {
            addCriterion("suggestion <>", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionGreaterThan(String value) {
            addCriterion("suggestion >", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionGreaterThanOrEqualTo(String value) {
            addCriterion("suggestion >=", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionLessThan(String value) {
            addCriterion("suggestion <", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionLessThanOrEqualTo(String value) {
            addCriterion("suggestion <=", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionLike(String value) {
            addCriterion("suggestion like", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionNotLike(String value) {
            addCriterion("suggestion not like", value, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionIn(List<String> values) {
            addCriterion("suggestion in", values, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionNotIn(List<String> values) {
            addCriterion("suggestion not in", values, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionBetween(String value1, String value2) {
            addCriterion("suggestion between", value1, value2, "suggestion");
            return (Criteria) this;
        }

        public Criteria andSuggestionNotBetween(String value1, String value2) {
            addCriterion("suggestion not between", value1, value2, "suggestion");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "dev_id,alarm_data,check_user,suggestion,";
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