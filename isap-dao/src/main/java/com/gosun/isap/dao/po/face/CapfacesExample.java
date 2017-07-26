package com.gosun.isap.dao.po.face;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CapfacesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CapfacesExample() {
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

        public Criteria andCameraidIsNull() {
            addCriterion("CameraID is null");
            return (Criteria) this;
        }

        public Criteria andCameraidIsNotNull() {
            addCriterion("CameraID is not null");
            return (Criteria) this;
        }

        public Criteria andCameraidEqualTo(Integer value) {
            addCriterion("CameraID =", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotEqualTo(Integer value) {
            addCriterion("CameraID <>", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidGreaterThan(Integer value) {
            addCriterion("CameraID >", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CameraID >=", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLessThan(Integer value) {
            addCriterion("CameraID <", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLessThanOrEqualTo(Integer value) {
            addCriterion("CameraID <=", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidIn(List<Integer> values) {
            addCriterion("CameraID in", values, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotIn(List<Integer> values) {
            addCriterion("CameraID not in", values, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidBetween(Integer value1, Integer value2) {
            addCriterion("CameraID between", value1, value2, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotBetween(Integer value1, Integer value2) {
            addCriterion("CameraID not between", value1, value2, "cameraid");
            return (Criteria) this;
        }

        public Criteria andFaceurlIsNull() {
            addCriterion("FaceURL is null");
            return (Criteria) this;
        }

        public Criteria andFaceurlIsNotNull() {
            addCriterion("FaceURL is not null");
            return (Criteria) this;
        }

        public Criteria andFaceurlEqualTo(String value) {
            addCriterion("FaceURL =", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotEqualTo(String value) {
            addCriterion("FaceURL <>", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlGreaterThan(String value) {
            addCriterion("FaceURL >", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlGreaterThanOrEqualTo(String value) {
            addCriterion("FaceURL >=", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLessThan(String value) {
            addCriterion("FaceURL <", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLessThanOrEqualTo(String value) {
            addCriterion("FaceURL <=", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLike(String value) {
            addCriterion("FaceURL like", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotLike(String value) {
            addCriterion("FaceURL not like", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlIn(List<String> values) {
            addCriterion("FaceURL in", values, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotIn(List<String> values) {
            addCriterion("FaceURL not in", values, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlBetween(String value1, String value2) {
            addCriterion("FaceURL between", value1, value2, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotBetween(String value1, String value2) {
            addCriterion("FaceURL not between", value1, value2, "faceurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlIsNull() {
            addCriterion("BackgroundURL is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlIsNotNull() {
            addCriterion("BackgroundURL is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlEqualTo(String value) {
            addCriterion("BackgroundURL =", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotEqualTo(String value) {
            addCriterion("BackgroundURL <>", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlGreaterThan(String value) {
            addCriterion("BackgroundURL >", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlGreaterThanOrEqualTo(String value) {
            addCriterion("BackgroundURL >=", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlLessThan(String value) {
            addCriterion("BackgroundURL <", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlLessThanOrEqualTo(String value) {
            addCriterion("BackgroundURL <=", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlLike(String value) {
            addCriterion("BackgroundURL like", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotLike(String value) {
            addCriterion("BackgroundURL not like", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlIn(List<String> values) {
            addCriterion("BackgroundURL in", values, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotIn(List<String> values) {
            addCriterion("BackgroundURL not in", values, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlBetween(String value1, String value2) {
            addCriterion("BackgroundURL between", value1, value2, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotBetween(String value1, String value2) {
            addCriterion("BackgroundURL not between", value1, value2, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andFeatureIsNull() {
            addCriterion("Feature is null");
            return (Criteria) this;
        }

        public Criteria andFeatureIsNotNull() {
            addCriterion("Feature is not null");
            return (Criteria) this;
        }

        public Criteria andFeatureEqualTo(String value) {
            addCriterion("Feature =", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotEqualTo(String value) {
            addCriterion("Feature <>", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureGreaterThan(String value) {
            addCriterion("Feature >", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureGreaterThanOrEqualTo(String value) {
            addCriterion("Feature >=", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLessThan(String value) {
            addCriterion("Feature <", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLessThanOrEqualTo(String value) {
            addCriterion("Feature <=", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLike(String value) {
            addCriterion("Feature like", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotLike(String value) {
            addCriterion("Feature not like", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureIn(List<String> values) {
            addCriterion("Feature in", values, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotIn(List<String> values) {
            addCriterion("Feature not in", values, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureBetween(String value1, String value2) {
            addCriterion("Feature between", value1, value2, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotBetween(String value1, String value2) {
            addCriterion("Feature not between", value1, value2, "feature");
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
            return "FaceURL,BackgroundURL,Feature,CameraName,Address,Creator,LastEditor,Remark,";
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