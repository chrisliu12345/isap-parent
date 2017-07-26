package com.gosun.isap.dao.po.face;

import java.util.ArrayList;
import java.util.List;

public class GroupblacklistsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GroupblacklistsExample() {
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

        public Criteria andBlacklistgroupidIsNull() {
            addCriterion("BlacklistGroupID is null");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidIsNotNull() {
            addCriterion("BlacklistGroupID is not null");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidEqualTo(Integer value) {
            addCriterion("BlacklistGroupID =", value, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidNotEqualTo(Integer value) {
            addCriterion("BlacklistGroupID <>", value, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidGreaterThan(Integer value) {
            addCriterion("BlacklistGroupID >", value, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidGreaterThanOrEqualTo(Integer value) {
            addCriterion("BlacklistGroupID >=", value, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidLessThan(Integer value) {
            addCriterion("BlacklistGroupID <", value, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidLessThanOrEqualTo(Integer value) {
            addCriterion("BlacklistGroupID <=", value, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidIn(List<Integer> values) {
            addCriterion("BlacklistGroupID in", values, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidNotIn(List<Integer> values) {
            addCriterion("BlacklistGroupID not in", values, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidBetween(Integer value1, Integer value2) {
            addCriterion("BlacklistGroupID between", value1, value2, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistgroupidNotBetween(Integer value1, Integer value2) {
            addCriterion("BlacklistGroupID not between", value1, value2, "blacklistgroupid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidIsNull() {
            addCriterion("BlacklistID is null");
            return (Criteria) this;
        }

        public Criteria andBlacklistidIsNotNull() {
            addCriterion("BlacklistID is not null");
            return (Criteria) this;
        }

        public Criteria andBlacklistidEqualTo(Integer value) {
            addCriterion("BlacklistID =", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidNotEqualTo(Integer value) {
            addCriterion("BlacklistID <>", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidGreaterThan(Integer value) {
            addCriterion("BlacklistID >", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidGreaterThanOrEqualTo(Integer value) {
            addCriterion("BlacklistID >=", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidLessThan(Integer value) {
            addCriterion("BlacklistID <", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidLessThanOrEqualTo(Integer value) {
            addCriterion("BlacklistID <=", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidIn(List<Integer> values) {
            addCriterion("BlacklistID in", values, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidNotIn(List<Integer> values) {
            addCriterion("BlacklistID not in", values, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidBetween(Integer value1, Integer value2) {
            addCriterion("BlacklistID between", value1, value2, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidNotBetween(Integer value1, Integer value2) {
            addCriterion("BlacklistID not between", value1, value2, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("State is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("State is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("State =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("State <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("State >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("State >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("State <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("State <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("State in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("State not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("State between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("State not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUsedflagIsNull() {
            addCriterion("UsedFlag is null");
            return (Criteria) this;
        }

        public Criteria andUsedflagIsNotNull() {
            addCriterion("UsedFlag is not null");
            return (Criteria) this;
        }

        public Criteria andUsedflagEqualTo(Integer value) {
            addCriterion("UsedFlag =", value, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagNotEqualTo(Integer value) {
            addCriterion("UsedFlag <>", value, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagGreaterThan(Integer value) {
            addCriterion("UsedFlag >", value, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("UsedFlag >=", value, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagLessThan(Integer value) {
            addCriterion("UsedFlag <", value, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagLessThanOrEqualTo(Integer value) {
            addCriterion("UsedFlag <=", value, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagIn(List<Integer> values) {
            addCriterion("UsedFlag in", values, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagNotIn(List<Integer> values) {
            addCriterion("UsedFlag not in", values, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagBetween(Integer value1, Integer value2) {
            addCriterion("UsedFlag between", value1, value2, "usedflag");
            return (Criteria) this;
        }

        public Criteria andUsedflagNotBetween(Integer value1, Integer value2) {
            addCriterion("UsedFlag not between", value1, value2, "usedflag");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "";
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