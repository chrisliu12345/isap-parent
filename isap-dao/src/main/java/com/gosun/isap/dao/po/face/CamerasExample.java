package com.gosun.isap.dao.po.face;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CamerasExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CamerasExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHostidIsNull() {
            addCriterion("HostID is null");
            return (Criteria) this;
        }

        public Criteria andHostidIsNotNull() {
            addCriterion("HostID is not null");
            return (Criteria) this;
        }

        public Criteria andHostidEqualTo(Integer value) {
            addCriterion("HostID =", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotEqualTo(Integer value) {
            addCriterion("HostID <>", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidGreaterThan(Integer value) {
            addCriterion("HostID >", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidGreaterThanOrEqualTo(Integer value) {
            addCriterion("HostID >=", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidLessThan(Integer value) {
            addCriterion("HostID <", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidLessThanOrEqualTo(Integer value) {
            addCriterion("HostID <=", value, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidIn(List<Integer> values) {
            addCriterion("HostID in", values, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotIn(List<Integer> values) {
            addCriterion("HostID not in", values, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidBetween(Integer value1, Integer value2) {
            addCriterion("HostID between", value1, value2, "hostid");
            return (Criteria) this;
        }

        public Criteria andHostidNotBetween(Integer value1, Integer value2) {
            addCriterion("HostID not between", value1, value2, "hostid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("Port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("Port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("Port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("Port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("Port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("Port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("Port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("Port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("Port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("Port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("Port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("Port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andChanelIsNull() {
            addCriterion("Chanel is null");
            return (Criteria) this;
        }

        public Criteria andChanelIsNotNull() {
            addCriterion("Chanel is not null");
            return (Criteria) this;
        }

        public Criteria andChanelEqualTo(Integer value) {
            addCriterion("Chanel =", value, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelNotEqualTo(Integer value) {
            addCriterion("Chanel <>", value, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelGreaterThan(Integer value) {
            addCriterion("Chanel >", value, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelGreaterThanOrEqualTo(Integer value) {
            addCriterion("Chanel >=", value, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelLessThan(Integer value) {
            addCriterion("Chanel <", value, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelLessThanOrEqualTo(Integer value) {
            addCriterion("Chanel <=", value, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelIn(List<Integer> values) {
            addCriterion("Chanel in", values, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelNotIn(List<Integer> values) {
            addCriterion("Chanel not in", values, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelBetween(Integer value1, Integer value2) {
            addCriterion("Chanel between", value1, value2, "chanel");
            return (Criteria) this;
        }

        public Criteria andChanelNotBetween(Integer value1, Integer value2) {
            addCriterion("Chanel not between", value1, value2, "chanel");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("UserName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("UserName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("UserName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("UserName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("UserName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("UserName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("UserName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("UserName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("UserName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("UserName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("UserName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("UserName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("UserName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("UserName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("Password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("Password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("Password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("Password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("Password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("Password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("Password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("Password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("Password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("Password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("Password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("Password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("Password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("Password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("Address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("Address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("Address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("Address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("Address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("Address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("Address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("Address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("Address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("Address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("Address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("Address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("Address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("Address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andXIsNull() {
            addCriterion("X is null");
            return (Criteria) this;
        }

        public Criteria andXIsNotNull() {
            addCriterion("X is not null");
            return (Criteria) this;
        }

        public Criteria andXEqualTo(Float value) {
            addCriterion("X =", value, "x");
            return (Criteria) this;
        }

        public Criteria andXNotEqualTo(Float value) {
            addCriterion("X <>", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThan(Float value) {
            addCriterion("X >", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThanOrEqualTo(Float value) {
            addCriterion("X >=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThan(Float value) {
            addCriterion("X <", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThanOrEqualTo(Float value) {
            addCriterion("X <=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXIn(List<Float> values) {
            addCriterion("X in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXNotIn(List<Float> values) {
            addCriterion("X not in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXBetween(Float value1, Float value2) {
            addCriterion("X between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andXNotBetween(Float value1, Float value2) {
            addCriterion("X not between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andYIsNull() {
            addCriterion("Y is null");
            return (Criteria) this;
        }

        public Criteria andYIsNotNull() {
            addCriterion("Y is not null");
            return (Criteria) this;
        }

        public Criteria andYEqualTo(Float value) {
            addCriterion("Y =", value, "y");
            return (Criteria) this;
        }

        public Criteria andYNotEqualTo(Float value) {
            addCriterion("Y <>", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThan(Float value) {
            addCriterion("Y >", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThanOrEqualTo(Float value) {
            addCriterion("Y >=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThan(Float value) {
            addCriterion("Y <", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThanOrEqualTo(Float value) {
            addCriterion("Y <=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYIn(List<Float> values) {
            addCriterion("Y in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYNotIn(List<Float> values) {
            addCriterion("Y not in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYBetween(Float value1, Float value2) {
            addCriterion("Y between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andYNotBetween(Float value1, Float value2) {
            addCriterion("Y not between", value1, value2, "y");
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

        public Criteria andCreatorIsNull() {
            addCriterion("Creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("Creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("Creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("Creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("Creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("Creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("Creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("Creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("Creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("Creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("Creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("Creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("Creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("Creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andLasteditorIsNull() {
            addCriterion("LastEditor is null");
            return (Criteria) this;
        }

        public Criteria andLasteditorIsNotNull() {
            addCriterion("LastEditor is not null");
            return (Criteria) this;
        }

        public Criteria andLasteditorEqualTo(String value) {
            addCriterion("LastEditor =", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorNotEqualTo(String value) {
            addCriterion("LastEditor <>", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorGreaterThan(String value) {
            addCriterion("LastEditor >", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorGreaterThanOrEqualTo(String value) {
            addCriterion("LastEditor >=", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorLessThan(String value) {
            addCriterion("LastEditor <", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorLessThanOrEqualTo(String value) {
            addCriterion("LastEditor <=", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorLike(String value) {
            addCriterion("LastEditor like", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorNotLike(String value) {
            addCriterion("LastEditor not like", value, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorIn(List<String> values) {
            addCriterion("LastEditor in", values, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorNotIn(List<String> values) {
            addCriterion("LastEditor not in", values, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorBetween(String value1, String value2) {
            addCriterion("LastEditor between", value1, value2, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLasteditorNotBetween(String value1, String value2) {
            addCriterion("LastEditor not between", value1, value2, "lasteditor");
            return (Criteria) this;
        }

        public Criteria andLastedittimeIsNull() {
            addCriterion("LastEditTime is null");
            return (Criteria) this;
        }

        public Criteria andLastedittimeIsNotNull() {
            addCriterion("LastEditTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastedittimeEqualTo(Date value) {
            addCriterion("LastEditTime =", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeNotEqualTo(Date value) {
            addCriterion("LastEditTime <>", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeGreaterThan(Date value) {
            addCriterion("LastEditTime >", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LastEditTime >=", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeLessThan(Date value) {
            addCriterion("LastEditTime <", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeLessThanOrEqualTo(Date value) {
            addCriterion("LastEditTime <=", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeIn(List<Date> values) {
            addCriterion("LastEditTime in", values, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeNotIn(List<Date> values) {
            addCriterion("LastEditTime not in", values, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeBetween(Date value1, Date value2) {
            addCriterion("LastEditTime between", value1, value2, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeNotBetween(Date value1, Date value2) {
            addCriterion("LastEditTime not between", value1, value2, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "Name,Code,IP,UserName,Password,Address,Creator,LastEditor,Remark,";
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