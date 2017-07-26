package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TVersionHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TVersionHistoryExample() {
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

        public Criteria andInstallFlagIsNull() {
            addCriterion("install_flag is null");
            return (Criteria) this;
        }

        public Criteria andInstallFlagIsNotNull() {
            addCriterion("install_flag is not null");
            return (Criteria) this;
        }

        public Criteria andInstallFlagEqualTo(Byte value) {
            addCriterion("install_flag =", value, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagNotEqualTo(Byte value) {
            addCriterion("install_flag <>", value, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagGreaterThan(Byte value) {
            addCriterion("install_flag >", value, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("install_flag >=", value, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagLessThan(Byte value) {
            addCriterion("install_flag <", value, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagLessThanOrEqualTo(Byte value) {
            addCriterion("install_flag <=", value, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagIn(List<Byte> values) {
            addCriterion("install_flag in", values, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagNotIn(List<Byte> values) {
            addCriterion("install_flag not in", values, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagBetween(Byte value1, Byte value2) {
            addCriterion("install_flag between", value1, value2, "installFlag");
            return (Criteria) this;
        }

        public Criteria andInstallFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("install_flag not between", value1, value2, "installFlag");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionIsNull() {
            addCriterion("from_inner_version is null");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionIsNotNull() {
            addCriterion("from_inner_version is not null");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionEqualTo(String value) {
            addCriterion("from_inner_version =", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionNotEqualTo(String value) {
            addCriterion("from_inner_version <>", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionGreaterThan(String value) {
            addCriterion("from_inner_version >", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionGreaterThanOrEqualTo(String value) {
            addCriterion("from_inner_version >=", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionLessThan(String value) {
            addCriterion("from_inner_version <", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionLessThanOrEqualTo(String value) {
            addCriterion("from_inner_version <=", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionLike(String value) {
            addCriterion("from_inner_version like", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionNotLike(String value) {
            addCriterion("from_inner_version not like", value, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionIn(List<String> values) {
            addCriterion("from_inner_version in", values, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionNotIn(List<String> values) {
            addCriterion("from_inner_version not in", values, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionBetween(String value1, String value2) {
            addCriterion("from_inner_version between", value1, value2, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andFromInnerVersionNotBetween(String value1, String value2) {
            addCriterion("from_inner_version not between", value1, value2, "fromInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionIsNull() {
            addCriterion("to_inner_version is null");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionIsNotNull() {
            addCriterion("to_inner_version is not null");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionEqualTo(String value) {
            addCriterion("to_inner_version =", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionNotEqualTo(String value) {
            addCriterion("to_inner_version <>", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionGreaterThan(String value) {
            addCriterion("to_inner_version >", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionGreaterThanOrEqualTo(String value) {
            addCriterion("to_inner_version >=", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionLessThan(String value) {
            addCriterion("to_inner_version <", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionLessThanOrEqualTo(String value) {
            addCriterion("to_inner_version <=", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionLike(String value) {
            addCriterion("to_inner_version like", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionNotLike(String value) {
            addCriterion("to_inner_version not like", value, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionIn(List<String> values) {
            addCriterion("to_inner_version in", values, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionNotIn(List<String> values) {
            addCriterion("to_inner_version not in", values, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionBetween(String value1, String value2) {
            addCriterion("to_inner_version between", value1, value2, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andToInnerVersionNotBetween(String value1, String value2) {
            addCriterion("to_inner_version not between", value1, value2, "toInnerVersion");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNull() {
            addCriterion("upate_time is null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNotNull() {
            addCriterion("upate_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeEqualTo(Date value) {
            addCriterion("upate_time =", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotEqualTo(Date value) {
            addCriterion("upate_time <>", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThan(Date value) {
            addCriterion("upate_time >", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upate_time >=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThan(Date value) {
            addCriterion("upate_time <", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThanOrEqualTo(Date value) {
            addCriterion("upate_time <=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIn(List<Date> values) {
            addCriterion("upate_time in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotIn(List<Date> values) {
            addCriterion("upate_time not in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeBetween(Date value1, Date value2) {
            addCriterion("upate_time between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotBetween(Date value1, Date value2) {
            addCriterion("upate_time not between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "from_inner_version,to_inner_version,";
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