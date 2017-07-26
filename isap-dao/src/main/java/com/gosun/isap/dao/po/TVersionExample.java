package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.List;

public class TVersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TVersionExample() {
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

        public Criteria andVersionTypeIsNull() {
            addCriterion("version_type is null");
            return (Criteria) this;
        }

        public Criteria andVersionTypeIsNotNull() {
            addCriterion("version_type is not null");
            return (Criteria) this;
        }

        public Criteria andVersionTypeEqualTo(String value) {
            addCriterion("version_type =", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeNotEqualTo(String value) {
            addCriterion("version_type <>", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeGreaterThan(String value) {
            addCriterion("version_type >", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("version_type >=", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeLessThan(String value) {
            addCriterion("version_type <", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeLessThanOrEqualTo(String value) {
            addCriterion("version_type <=", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeLike(String value) {
            addCriterion("version_type like", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeNotLike(String value) {
            addCriterion("version_type not like", value, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeIn(List<String> values) {
            addCriterion("version_type in", values, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeNotIn(List<String> values) {
            addCriterion("version_type not in", values, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeBetween(String value1, String value2) {
            addCriterion("version_type between", value1, value2, "versionType");
            return (Criteria) this;
        }

        public Criteria andVersionTypeNotBetween(String value1, String value2) {
            addCriterion("version_type not between", value1, value2, "versionType");
            return (Criteria) this;
        }

        public Criteria andInnerVersionIsNull() {
            addCriterion("inner_version is null");
            return (Criteria) this;
        }

        public Criteria andInnerVersionIsNotNull() {
            addCriterion("inner_version is not null");
            return (Criteria) this;
        }

        public Criteria andInnerVersionEqualTo(String value) {
            addCriterion("inner_version =", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionNotEqualTo(String value) {
            addCriterion("inner_version <>", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionGreaterThan(String value) {
            addCriterion("inner_version >", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionGreaterThanOrEqualTo(String value) {
            addCriterion("inner_version >=", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionLessThan(String value) {
            addCriterion("inner_version <", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionLessThanOrEqualTo(String value) {
            addCriterion("inner_version <=", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionLike(String value) {
            addCriterion("inner_version like", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionNotLike(String value) {
            addCriterion("inner_version not like", value, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionIn(List<String> values) {
            addCriterion("inner_version in", values, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionNotIn(List<String> values) {
            addCriterion("inner_version not in", values, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionBetween(String value1, String value2) {
            addCriterion("inner_version between", value1, value2, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andInnerVersionNotBetween(String value1, String value2) {
            addCriterion("inner_version not between", value1, value2, "innerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionIsNull() {
            addCriterion("outer_version is null");
            return (Criteria) this;
        }

        public Criteria andOuterVersionIsNotNull() {
            addCriterion("outer_version is not null");
            return (Criteria) this;
        }

        public Criteria andOuterVersionEqualTo(String value) {
            addCriterion("outer_version =", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionNotEqualTo(String value) {
            addCriterion("outer_version <>", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionGreaterThan(String value) {
            addCriterion("outer_version >", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionGreaterThanOrEqualTo(String value) {
            addCriterion("outer_version >=", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionLessThan(String value) {
            addCriterion("outer_version <", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionLessThanOrEqualTo(String value) {
            addCriterion("outer_version <=", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionLike(String value) {
            addCriterion("outer_version like", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionNotLike(String value) {
            addCriterion("outer_version not like", value, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionIn(List<String> values) {
            addCriterion("outer_version in", values, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionNotIn(List<String> values) {
            addCriterion("outer_version not in", values, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionBetween(String value1, String value2) {
            addCriterion("outer_version between", value1, value2, "outerVersion");
            return (Criteria) this;
        }

        public Criteria andOuterVersionNotBetween(String value1, String value2) {
            addCriterion("outer_version not between", value1, value2, "outerVersion");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "version_type,inner_version,outer_version,";
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