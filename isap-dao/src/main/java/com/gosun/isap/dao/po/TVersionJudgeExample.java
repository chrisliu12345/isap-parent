package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.List;

public class TVersionJudgeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TVersionJudgeExample() {
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

        public Criteria andSrcVersionIsNull() {
            addCriterion("src_version is null");
            return (Criteria) this;
        }

        public Criteria andSrcVersionIsNotNull() {
            addCriterion("src_version is not null");
            return (Criteria) this;
        }

        public Criteria andSrcVersionEqualTo(String value) {
            addCriterion("src_version =", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionNotEqualTo(String value) {
            addCriterion("src_version <>", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionGreaterThan(String value) {
            addCriterion("src_version >", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionGreaterThanOrEqualTo(String value) {
            addCriterion("src_version >=", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionLessThan(String value) {
            addCriterion("src_version <", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionLessThanOrEqualTo(String value) {
            addCriterion("src_version <=", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionLike(String value) {
            addCriterion("src_version like", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionNotLike(String value) {
            addCriterion("src_version not like", value, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionIn(List<String> values) {
            addCriterion("src_version in", values, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionNotIn(List<String> values) {
            addCriterion("src_version not in", values, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionBetween(String value1, String value2) {
            addCriterion("src_version between", value1, value2, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andSrcVersionNotBetween(String value1, String value2) {
            addCriterion("src_version not between", value1, value2, "srcVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionIsNull() {
            addCriterion("curr_version is null");
            return (Criteria) this;
        }

        public Criteria andCurrVersionIsNotNull() {
            addCriterion("curr_version is not null");
            return (Criteria) this;
        }

        public Criteria andCurrVersionEqualTo(String value) {
            addCriterion("curr_version =", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionNotEqualTo(String value) {
            addCriterion("curr_version <>", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionGreaterThan(String value) {
            addCriterion("curr_version >", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionGreaterThanOrEqualTo(String value) {
            addCriterion("curr_version >=", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionLessThan(String value) {
            addCriterion("curr_version <", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionLessThanOrEqualTo(String value) {
            addCriterion("curr_version <=", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionLike(String value) {
            addCriterion("curr_version like", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionNotLike(String value) {
            addCriterion("curr_version not like", value, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionIn(List<String> values) {
            addCriterion("curr_version in", values, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionNotIn(List<String> values) {
            addCriterion("curr_version not in", values, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionBetween(String value1, String value2) {
            addCriterion("curr_version between", value1, value2, "currVersion");
            return (Criteria) this;
        }

        public Criteria andCurrVersionNotBetween(String value1, String value2) {
            addCriterion("curr_version not between", value1, value2, "currVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionIsNull() {
            addCriterion("target_version is null");
            return (Criteria) this;
        }

        public Criteria andTargetVersionIsNotNull() {
            addCriterion("target_version is not null");
            return (Criteria) this;
        }

        public Criteria andTargetVersionEqualTo(String value) {
            addCriterion("target_version =", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotEqualTo(String value) {
            addCriterion("target_version <>", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionGreaterThan(String value) {
            addCriterion("target_version >", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionGreaterThanOrEqualTo(String value) {
            addCriterion("target_version >=", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionLessThan(String value) {
            addCriterion("target_version <", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionLessThanOrEqualTo(String value) {
            addCriterion("target_version <=", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionLike(String value) {
            addCriterion("target_version like", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotLike(String value) {
            addCriterion("target_version not like", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionIn(List<String> values) {
            addCriterion("target_version in", values, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotIn(List<String> values) {
            addCriterion("target_version not in", values, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionBetween(String value1, String value2) {
            addCriterion("target_version between", value1, value2, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotBetween(String value1, String value2) {
            addCriterion("target_version not between", value1, value2, "targetVersion");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "src_version,curr_version,target_version,";
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