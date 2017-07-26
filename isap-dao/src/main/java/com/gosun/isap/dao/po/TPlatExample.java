package com.gosun.isap.dao.po;

import java.util.ArrayList;
import java.util.List;

public class TPlatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TPlatExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andVendorTypeIsNull() {
            addCriterion("vendor_type is null");
            return (Criteria) this;
        }

        public Criteria andVendorTypeIsNotNull() {
            addCriterion("vendor_type is not null");
            return (Criteria) this;
        }

        public Criteria andVendorTypeEqualTo(Byte value) {
            addCriterion("vendor_type =", value, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeNotEqualTo(Byte value) {
            addCriterion("vendor_type <>", value, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeGreaterThan(Byte value) {
            addCriterion("vendor_type >", value, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("vendor_type >=", value, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeLessThan(Byte value) {
            addCriterion("vendor_type <", value, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeLessThanOrEqualTo(Byte value) {
            addCriterion("vendor_type <=", value, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeIn(List<Byte> values) {
            addCriterion("vendor_type in", values, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeNotIn(List<Byte> values) {
            addCriterion("vendor_type not in", values, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeBetween(Byte value1, Byte value2) {
            addCriterion("vendor_type between", value1, value2, "vendorType");
            return (Criteria) this;
        }

        public Criteria andVendorTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("vendor_type not between", value1, value2, "vendorType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeIsNull() {
            addCriterion("access_type is null");
            return (Criteria) this;
        }

        public Criteria andAccessTypeIsNotNull() {
            addCriterion("access_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTypeEqualTo(Byte value) {
            addCriterion("access_type =", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotEqualTo(Byte value) {
            addCriterion("access_type <>", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeGreaterThan(Byte value) {
            addCriterion("access_type >", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("access_type >=", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeLessThan(Byte value) {
            addCriterion("access_type <", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeLessThanOrEqualTo(Byte value) {
            addCriterion("access_type <=", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeIn(List<Byte> values) {
            addCriterion("access_type in", values, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotIn(List<Byte> values) {
            addCriterion("access_type not in", values, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeBetween(Byte value1, Byte value2) {
            addCriterion("access_type between", value1, value2, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("access_type not between", value1, value2, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressIsNull() {
            addCriterion("access_ip_address is null");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressIsNotNull() {
            addCriterion("access_ip_address is not null");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressEqualTo(String value) {
            addCriterion("access_ip_address =", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressNotEqualTo(String value) {
            addCriterion("access_ip_address <>", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressGreaterThan(String value) {
            addCriterion("access_ip_address >", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("access_ip_address >=", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressLessThan(String value) {
            addCriterion("access_ip_address <", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressLessThanOrEqualTo(String value) {
            addCriterion("access_ip_address <=", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressLike(String value) {
            addCriterion("access_ip_address like", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressNotLike(String value) {
            addCriterion("access_ip_address not like", value, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressIn(List<String> values) {
            addCriterion("access_ip_address in", values, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressNotIn(List<String> values) {
            addCriterion("access_ip_address not in", values, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressBetween(String value1, String value2) {
            addCriterion("access_ip_address between", value1, value2, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessIpAddressNotBetween(String value1, String value2) {
            addCriterion("access_ip_address not between", value1, value2, "accessIpAddress");
            return (Criteria) this;
        }

        public Criteria andAccessPortIsNull() {
            addCriterion("access_port is null");
            return (Criteria) this;
        }

        public Criteria andAccessPortIsNotNull() {
            addCriterion("access_port is not null");
            return (Criteria) this;
        }

        public Criteria andAccessPortEqualTo(Integer value) {
            addCriterion("access_port =", value, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortNotEqualTo(Integer value) {
            addCriterion("access_port <>", value, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortGreaterThan(Integer value) {
            addCriterion("access_port >", value, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("access_port >=", value, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortLessThan(Integer value) {
            addCriterion("access_port <", value, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortLessThanOrEqualTo(Integer value) {
            addCriterion("access_port <=", value, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortIn(List<Integer> values) {
            addCriterion("access_port in", values, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortNotIn(List<Integer> values) {
            addCriterion("access_port not in", values, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortBetween(Integer value1, Integer value2) {
            addCriterion("access_port between", value1, value2, "accessPort");
            return (Criteria) this;
        }

        public Criteria andAccessPortNotBetween(Integer value1, Integer value2) {
            addCriterion("access_port not between", value1, value2, "accessPort");
            return (Criteria) this;
        }

        public Criteria andLoginUserIsNull() {
            addCriterion("login_user is null");
            return (Criteria) this;
        }

        public Criteria andLoginUserIsNotNull() {
            addCriterion("login_user is not null");
            return (Criteria) this;
        }

        public Criteria andLoginUserEqualTo(String value) {
            addCriterion("login_user =", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotEqualTo(String value) {
            addCriterion("login_user <>", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserGreaterThan(String value) {
            addCriterion("login_user >", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserGreaterThanOrEqualTo(String value) {
            addCriterion("login_user >=", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserLessThan(String value) {
            addCriterion("login_user <", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserLessThanOrEqualTo(String value) {
            addCriterion("login_user <=", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserLike(String value) {
            addCriterion("login_user like", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotLike(String value) {
            addCriterion("login_user not like", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserIn(List<String> values) {
            addCriterion("login_user in", values, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotIn(List<String> values) {
            addCriterion("login_user not in", values, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserBetween(String value1, String value2) {
            addCriterion("login_user between", value1, value2, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotBetween(String value1, String value2) {
            addCriterion("login_user not between", value1, value2, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdIsNull() {
            addCriterion("login_passwd is null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdIsNotNull() {
            addCriterion("login_passwd is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdEqualTo(String value) {
            addCriterion("login_passwd =", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdNotEqualTo(String value) {
            addCriterion("login_passwd <>", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdGreaterThan(String value) {
            addCriterion("login_passwd >", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("login_passwd >=", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdLessThan(String value) {
            addCriterion("login_passwd <", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdLessThanOrEqualTo(String value) {
            addCriterion("login_passwd <=", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdLike(String value) {
            addCriterion("login_passwd like", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdNotLike(String value) {
            addCriterion("login_passwd not like", value, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdIn(List<String> values) {
            addCriterion("login_passwd in", values, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdNotIn(List<String> values) {
            addCriterion("login_passwd not in", values, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdBetween(String value1, String value2) {
            addCriterion("login_passwd between", value1, value2, "loginPasswd");
            return (Criteria) this;
        }

        public Criteria andLoginPasswdNotBetween(String value1, String value2) {
            addCriterion("login_passwd not between", value1, value2, "loginPasswd");
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
            return "name,access_ip_address,login_user,login_passwd,description,";
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