package com.gosun.isap.dao.po.face;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarminfoesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AlarminfoesExample() {
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

        public Criteria andContactidIsNull() {
            addCriterion("ContactID is null");
            return (Criteria) this;
        }

        public Criteria andContactidIsNotNull() {
            addCriterion("ContactID is not null");
            return (Criteria) this;
        }

        public Criteria andContactidEqualTo(Integer value) {
            addCriterion("ContactID =", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotEqualTo(Integer value) {
            addCriterion("ContactID <>", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidGreaterThan(Integer value) {
            addCriterion("ContactID >", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ContactID >=", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLessThan(Integer value) {
            addCriterion("ContactID <", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLessThanOrEqualTo(Integer value) {
            addCriterion("ContactID <=", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidIn(List<Integer> values) {
            addCriterion("ContactID in", values, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotIn(List<Integer> values) {
            addCriterion("ContactID not in", values, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidBetween(Integer value1, Integer value2) {
            addCriterion("ContactID between", value1, value2, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotBetween(Integer value1, Integer value2) {
            addCriterion("ContactID not between", value1, value2, "contactid");
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

        public Criteria andIdcardIsNull() {
            addCriterion("IDCard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("IDCard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("IDCard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("IDCard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("IDCard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("IDCard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("IDCard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("IDCard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("IDCard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("IDCard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("IDCard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("IDCard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("IDCard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("IDCard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andCapfaceidIsNull() {
            addCriterion("CapFaceID is null");
            return (Criteria) this;
        }

        public Criteria andCapfaceidIsNotNull() {
            addCriterion("CapFaceID is not null");
            return (Criteria) this;
        }

        public Criteria andCapfaceidEqualTo(Integer value) {
            addCriterion("CapFaceID =", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidNotEqualTo(Integer value) {
            addCriterion("CapFaceID <>", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidGreaterThan(Integer value) {
            addCriterion("CapFaceID >", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CapFaceID >=", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidLessThan(Integer value) {
            addCriterion("CapFaceID <", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidLessThanOrEqualTo(Integer value) {
            addCriterion("CapFaceID <=", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidIn(List<Integer> values) {
            addCriterion("CapFaceID in", values, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidNotIn(List<Integer> values) {
            addCriterion("CapFaceID not in", values, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidBetween(Integer value1, Integer value2) {
            addCriterion("CapFaceID between", value1, value2, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidNotBetween(Integer value1, Integer value2) {
            addCriterion("CapFaceID not between", value1, value2, "capfaceid");
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

        public Criteria andFaceimgIsNull() {
            addCriterion("FaceImg is null");
            return (Criteria) this;
        }

        public Criteria andFaceimgIsNotNull() {
            addCriterion("FaceImg is not null");
            return (Criteria) this;
        }

        public Criteria andFaceimgEqualTo(String value) {
            addCriterion("FaceImg =", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgNotEqualTo(String value) {
            addCriterion("FaceImg <>", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgGreaterThan(String value) {
            addCriterion("FaceImg >", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgGreaterThanOrEqualTo(String value) {
            addCriterion("FaceImg >=", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgLessThan(String value) {
            addCriterion("FaceImg <", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgLessThanOrEqualTo(String value) {
            addCriterion("FaceImg <=", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgLike(String value) {
            addCriterion("FaceImg like", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgNotLike(String value) {
            addCriterion("FaceImg not like", value, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgIn(List<String> values) {
            addCriterion("FaceImg in", values, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgNotIn(List<String> values) {
            addCriterion("FaceImg not in", values, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgBetween(String value1, String value2) {
            addCriterion("FaceImg between", value1, value2, "faceimg");
            return (Criteria) this;
        }

        public Criteria andFaceimgNotBetween(String value1, String value2) {
            addCriterion("FaceImg not between", value1, value2, "faceimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgIsNull() {
            addCriterion("BlacklistImg is null");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgIsNotNull() {
            addCriterion("BlacklistImg is not null");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgEqualTo(String value) {
            addCriterion("BlacklistImg =", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgNotEqualTo(String value) {
            addCriterion("BlacklistImg <>", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgGreaterThan(String value) {
            addCriterion("BlacklistImg >", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgGreaterThanOrEqualTo(String value) {
            addCriterion("BlacklistImg >=", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgLessThan(String value) {
            addCriterion("BlacklistImg <", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgLessThanOrEqualTo(String value) {
            addCriterion("BlacklistImg <=", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgLike(String value) {
            addCriterion("BlacklistImg like", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgNotLike(String value) {
            addCriterion("BlacklistImg not like", value, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgIn(List<String> values) {
            addCriterion("BlacklistImg in", values, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgNotIn(List<String> values) {
            addCriterion("BlacklistImg not in", values, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgBetween(String value1, String value2) {
            addCriterion("BlacklistImg between", value1, value2, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBlacklistimgNotBetween(String value1, String value2) {
            addCriterion("BlacklistImg not between", value1, value2, "blacklistimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgIsNull() {
            addCriterion("BackgroundImg is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgIsNotNull() {
            addCriterion("BackgroundImg is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgEqualTo(String value) {
            addCriterion("BackgroundImg =", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgNotEqualTo(String value) {
            addCriterion("BackgroundImg <>", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgGreaterThan(String value) {
            addCriterion("BackgroundImg >", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgGreaterThanOrEqualTo(String value) {
            addCriterion("BackgroundImg >=", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgLessThan(String value) {
            addCriterion("BackgroundImg <", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgLessThanOrEqualTo(String value) {
            addCriterion("BackgroundImg <=", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgLike(String value) {
            addCriterion("BackgroundImg like", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgNotLike(String value) {
            addCriterion("BackgroundImg not like", value, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgIn(List<String> values) {
            addCriterion("BackgroundImg in", values, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgNotIn(List<String> values) {
            addCriterion("BackgroundImg not in", values, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgBetween(String value1, String value2) {
            addCriterion("BackgroundImg between", value1, value2, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andBackgroundimgNotBetween(String value1, String value2) {
            addCriterion("BackgroundImg not between", value1, value2, "backgroundimg");
            return (Criteria) this;
        }

        public Criteria andSimiIsNull() {
            addCriterion("Simi is null");
            return (Criteria) this;
        }

        public Criteria andSimiIsNotNull() {
            addCriterion("Simi is not null");
            return (Criteria) this;
        }

        public Criteria andSimiEqualTo(Double value) {
            addCriterion("Simi =", value, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiNotEqualTo(Double value) {
            addCriterion("Simi <>", value, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiGreaterThan(Double value) {
            addCriterion("Simi >", value, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiGreaterThanOrEqualTo(Double value) {
            addCriterion("Simi >=", value, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiLessThan(Double value) {
            addCriterion("Simi <", value, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiLessThanOrEqualTo(Double value) {
            addCriterion("Simi <=", value, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiIn(List<Double> values) {
            addCriterion("Simi in", values, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiNotIn(List<Double> values) {
            addCriterion("Simi not in", values, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiBetween(Double value1, Double value2) {
            addCriterion("Simi between", value1, value2, "simi");
            return (Criteria) this;
        }

        public Criteria andSimiNotBetween(Double value1, Double value2) {
            addCriterion("Simi not between", value1, value2, "simi");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("Reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("Reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("Reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("Reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("Reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("Reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("Reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("Reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("Reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("Reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("Reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("Reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("Reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("Reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andCameranameIsNull() {
            addCriterion("CameraName is null");
            return (Criteria) this;
        }

        public Criteria andCameranameIsNotNull() {
            addCriterion("CameraName is not null");
            return (Criteria) this;
        }

        public Criteria andCameranameEqualTo(String value) {
            addCriterion("CameraName =", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameNotEqualTo(String value) {
            addCriterion("CameraName <>", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameGreaterThan(String value) {
            addCriterion("CameraName >", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameGreaterThanOrEqualTo(String value) {
            addCriterion("CameraName >=", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameLessThan(String value) {
            addCriterion("CameraName <", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameLessThanOrEqualTo(String value) {
            addCriterion("CameraName <=", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameLike(String value) {
            addCriterion("CameraName like", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameNotLike(String value) {
            addCriterion("CameraName not like", value, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameIn(List<String> values) {
            addCriterion("CameraName in", values, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameNotIn(List<String> values) {
            addCriterion("CameraName not in", values, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameBetween(String value1, String value2) {
            addCriterion("CameraName between", value1, value2, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCameranameNotBetween(String value1, String value2) {
            addCriterion("CameraName not between", value1, value2, "cameraname");
            return (Criteria) this;
        }

        public Criteria andCapaddrIsNull() {
            addCriterion("CapAddr is null");
            return (Criteria) this;
        }

        public Criteria andCapaddrIsNotNull() {
            addCriterion("CapAddr is not null");
            return (Criteria) this;
        }

        public Criteria andCapaddrEqualTo(String value) {
            addCriterion("CapAddr =", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrNotEqualTo(String value) {
            addCriterion("CapAddr <>", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrGreaterThan(String value) {
            addCriterion("CapAddr >", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrGreaterThanOrEqualTo(String value) {
            addCriterion("CapAddr >=", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrLessThan(String value) {
            addCriterion("CapAddr <", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrLessThanOrEqualTo(String value) {
            addCriterion("CapAddr <=", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrLike(String value) {
            addCriterion("CapAddr like", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrNotLike(String value) {
            addCriterion("CapAddr not like", value, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrIn(List<String> values) {
            addCriterion("CapAddr in", values, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrNotIn(List<String> values) {
            addCriterion("CapAddr not in", values, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrBetween(String value1, String value2) {
            addCriterion("CapAddr between", value1, value2, "capaddr");
            return (Criteria) this;
        }

        public Criteria andCapaddrNotBetween(String value1, String value2) {
            addCriterion("CapAddr not between", value1, value2, "capaddr");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNull() {
            addCriterion("HostName is null");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNotNull() {
            addCriterion("HostName is not null");
            return (Criteria) this;
        }

        public Criteria andHostnameEqualTo(String value) {
            addCriterion("HostName =", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotEqualTo(String value) {
            addCriterion("HostName <>", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThan(String value) {
            addCriterion("HostName >", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThanOrEqualTo(String value) {
            addCriterion("HostName >=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThan(String value) {
            addCriterion("HostName <", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThanOrEqualTo(String value) {
            addCriterion("HostName <=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLike(String value) {
            addCriterion("HostName like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotLike(String value) {
            addCriterion("HostName not like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameIn(List<String> values) {
            addCriterion("HostName in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotIn(List<String> values) {
            addCriterion("HostName not in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameBetween(String value1, String value2) {
            addCriterion("HostName between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotBetween(String value1, String value2) {
            addCriterion("HostName not between", value1, value2, "hostname");
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
            return "Name,IDCard,FaceImg,BlacklistImg,BackgroundImg,Reason,CameraName,CapAddr,HostName,Creator,LastEditor,Remark,";
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