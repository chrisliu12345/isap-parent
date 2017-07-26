package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.List;

public class TEnumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TEnumExample() {
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

        public Criteria andEnumKeyIsNull() {
            addCriterion("enum_key is null");
            return (Criteria) this;
        }

        public Criteria andEnumKeyIsNotNull() {
            addCriterion("enum_key is not null");
            return (Criteria) this;
        }

        public Criteria andEnumKeyEqualTo(String value) {
            addCriterion("enum_key =", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotEqualTo(String value) {
            addCriterion("enum_key <>", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyGreaterThan(String value) {
            addCriterion("enum_key >", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyGreaterThanOrEqualTo(String value) {
            addCriterion("enum_key >=", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyLessThan(String value) {
            addCriterion("enum_key <", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyLessThanOrEqualTo(String value) {
            addCriterion("enum_key <=", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyLike(String value) {
            addCriterion("enum_key like", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotLike(String value) {
            addCriterion("enum_key not like", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyIn(List<String> values) {
            addCriterion("enum_key in", values, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotIn(List<String> values) {
            addCriterion("enum_key not in", values, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyBetween(String value1, String value2) {
            addCriterion("enum_key between", value1, value2, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotBetween(String value1, String value2) {
            addCriterion("enum_key not between", value1, value2, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumValueIsNull() {
            addCriterion("enum_value is null");
            return (Criteria) this;
        }

        public Criteria andEnumValueIsNotNull() {
            addCriterion("enum_value is not null");
            return (Criteria) this;
        }

        public Criteria andEnumValueEqualTo(Integer value) {
            addCriterion("enum_value =", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotEqualTo(Integer value) {
            addCriterion("enum_value <>", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueGreaterThan(Integer value) {
            addCriterion("enum_value >", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("enum_value >=", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueLessThan(Integer value) {
            addCriterion("enum_value <", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueLessThanOrEqualTo(Integer value) {
            addCriterion("enum_value <=", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueIn(List<Integer> values) {
            addCriterion("enum_value in", values, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotIn(List<Integer> values) {
            addCriterion("enum_value not in", values, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueBetween(Integer value1, Integer value2) {
            addCriterion("enum_value between", value1, value2, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotBetween(Integer value1, Integer value2) {
            addCriterion("enum_value not between", value1, value2, "enumValue");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
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

        protected String getStringColumns() {
            return "enum_key,label,description,";
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