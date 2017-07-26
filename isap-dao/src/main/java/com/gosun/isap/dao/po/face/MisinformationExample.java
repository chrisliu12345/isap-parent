package com.gosun.isap.dao.po.face;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MisinformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public MisinformationExample() {
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

        public Criteria andListtypeIsNull() {
            addCriterion("listType is null");
            return (Criteria) this;
        }

        public Criteria andListtypeIsNotNull() {
            addCriterion("listType is not null");
            return (Criteria) this;
        }

        public Criteria andListtypeEqualTo(Integer value) {
            addCriterion("listType =", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeNotEqualTo(Integer value) {
            addCriterion("listType <>", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeGreaterThan(Integer value) {
            addCriterion("listType >", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("listType >=", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeLessThan(Integer value) {
            addCriterion("listType <", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeLessThanOrEqualTo(Integer value) {
            addCriterion("listType <=", value, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeIn(List<Integer> values) {
            addCriterion("listType in", values, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeNotIn(List<Integer> values) {
            addCriterion("listType not in", values, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeBetween(Integer value1, Integer value2) {
            addCriterion("listType between", value1, value2, "listtype");
            return (Criteria) this;
        }

        public Criteria andListtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("listType not between", value1, value2, "listtype");
            return (Criteria) this;
        }

        public Criteria andListfeatureIsNull() {
            addCriterion("listFeature is null");
            return (Criteria) this;
        }

        public Criteria andListfeatureIsNotNull() {
            addCriterion("listFeature is not null");
            return (Criteria) this;
        }

        public Criteria andListfeatureEqualTo(String value) {
            addCriterion("listFeature =", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureNotEqualTo(String value) {
            addCriterion("listFeature <>", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureGreaterThan(String value) {
            addCriterion("listFeature >", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureGreaterThanOrEqualTo(String value) {
            addCriterion("listFeature >=", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureLessThan(String value) {
            addCriterion("listFeature <", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureLessThanOrEqualTo(String value) {
            addCriterion("listFeature <=", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureLike(String value) {
            addCriterion("listFeature like", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureNotLike(String value) {
            addCriterion("listFeature not like", value, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureIn(List<String> values) {
            addCriterion("listFeature in", values, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureNotIn(List<String> values) {
            addCriterion("listFeature not in", values, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureBetween(String value1, String value2) {
            addCriterion("listFeature between", value1, value2, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfeatureNotBetween(String value1, String value2) {
            addCriterion("listFeature not between", value1, value2, "listfeature");
            return (Criteria) this;
        }

        public Criteria andListfaceurlIsNull() {
            addCriterion("listFaceURL is null");
            return (Criteria) this;
        }

        public Criteria andListfaceurlIsNotNull() {
            addCriterion("listFaceURL is not null");
            return (Criteria) this;
        }

        public Criteria andListfaceurlEqualTo(String value) {
            addCriterion("listFaceURL =", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlNotEqualTo(String value) {
            addCriterion("listFaceURL <>", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlGreaterThan(String value) {
            addCriterion("listFaceURL >", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlGreaterThanOrEqualTo(String value) {
            addCriterion("listFaceURL >=", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlLessThan(String value) {
            addCriterion("listFaceURL <", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlLessThanOrEqualTo(String value) {
            addCriterion("listFaceURL <=", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlLike(String value) {
            addCriterion("listFaceURL like", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlNotLike(String value) {
            addCriterion("listFaceURL not like", value, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlIn(List<String> values) {
            addCriterion("listFaceURL in", values, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlNotIn(List<String> values) {
            addCriterion("listFaceURL not in", values, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlBetween(String value1, String value2) {
            addCriterion("listFaceURL between", value1, value2, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andListfaceurlNotBetween(String value1, String value2) {
            addCriterion("listFaceURL not between", value1, value2, "listfaceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlIsNull() {
            addCriterion("faceURL is null");
            return (Criteria) this;
        }

        public Criteria andFaceurlIsNotNull() {
            addCriterion("faceURL is not null");
            return (Criteria) this;
        }

        public Criteria andFaceurlEqualTo(String value) {
            addCriterion("faceURL =", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotEqualTo(String value) {
            addCriterion("faceURL <>", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlGreaterThan(String value) {
            addCriterion("faceURL >", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlGreaterThanOrEqualTo(String value) {
            addCriterion("faceURL >=", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLessThan(String value) {
            addCriterion("faceURL <", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLessThanOrEqualTo(String value) {
            addCriterion("faceURL <=", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLike(String value) {
            addCriterion("faceURL like", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotLike(String value) {
            addCriterion("faceURL not like", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlIn(List<String> values) {
            addCriterion("faceURL in", values, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotIn(List<String> values) {
            addCriterion("faceURL not in", values, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlBetween(String value1, String value2) {
            addCriterion("faceURL between", value1, value2, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotBetween(String value1, String value2) {
            addCriterion("faceURL not between", value1, value2, "faceurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlIsNull() {
            addCriterion("backGroundURL is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlIsNotNull() {
            addCriterion("backGroundURL is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlEqualTo(String value) {
            addCriterion("backGroundURL =", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotEqualTo(String value) {
            addCriterion("backGroundURL <>", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlGreaterThan(String value) {
            addCriterion("backGroundURL >", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlGreaterThanOrEqualTo(String value) {
            addCriterion("backGroundURL >=", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlLessThan(String value) {
            addCriterion("backGroundURL <", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlLessThanOrEqualTo(String value) {
            addCriterion("backGroundURL <=", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlLike(String value) {
            addCriterion("backGroundURL like", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotLike(String value) {
            addCriterion("backGroundURL not like", value, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlIn(List<String> values) {
            addCriterion("backGroundURL in", values, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotIn(List<String> values) {
            addCriterion("backGroundURL not in", values, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlBetween(String value1, String value2) {
            addCriterion("backGroundURL between", value1, value2, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andBackgroundurlNotBetween(String value1, String value2) {
            addCriterion("backGroundURL not between", value1, value2, "backgroundurl");
            return (Criteria) this;
        }

        public Criteria andSimilarityIsNull() {
            addCriterion("similarity is null");
            return (Criteria) this;
        }

        public Criteria andSimilarityIsNotNull() {
            addCriterion("similarity is not null");
            return (Criteria) this;
        }

        public Criteria andSimilarityEqualTo(Double value) {
            addCriterion("similarity =", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotEqualTo(Double value) {
            addCriterion("similarity <>", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityGreaterThan(Double value) {
            addCriterion("similarity >", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityGreaterThanOrEqualTo(Double value) {
            addCriterion("similarity >=", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityLessThan(Double value) {
            addCriterion("similarity <", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityLessThanOrEqualTo(Double value) {
            addCriterion("similarity <=", value, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityIn(List<Double> values) {
            addCriterion("similarity in", values, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotIn(List<Double> values) {
            addCriterion("similarity not in", values, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityBetween(Double value1, Double value2) {
            addCriterion("similarity between", value1, value2, "similarity");
            return (Criteria) this;
        }

        public Criteria andSimilarityNotBetween(Double value1, Double value2) {
            addCriterion("similarity not between", value1, value2, "similarity");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeIsNull() {
            addCriterion("capFacesTime is null");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeIsNotNull() {
            addCriterion("capFacesTime is not null");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeEqualTo(Date value) {
            addCriterion("capFacesTime =", value, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeNotEqualTo(Date value) {
            addCriterion("capFacesTime <>", value, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeGreaterThan(Date value) {
            addCriterion("capFacesTime >", value, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeGreaterThanOrEqualTo(Date value) {
            addCriterion("capFacesTime >=", value, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeLessThan(Date value) {
            addCriterion("capFacesTime <", value, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeLessThanOrEqualTo(Date value) {
            addCriterion("capFacesTime <=", value, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeIn(List<Date> values) {
            addCriterion("capFacesTime in", values, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeNotIn(List<Date> values) {
            addCriterion("capFacesTime not in", values, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeBetween(Date value1, Date value2) {
            addCriterion("capFacesTime between", value1, value2, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCapfacestimeNotBetween(Date value1, Date value2) {
            addCriterion("capFacesTime not between", value1, value2, "capfacestime");
            return (Criteria) this;
        }

        public Criteria andCameraaddressIsNull() {
            addCriterion("cameraAddress is null");
            return (Criteria) this;
        }

        public Criteria andCameraaddressIsNotNull() {
            addCriterion("cameraAddress is not null");
            return (Criteria) this;
        }

        public Criteria andCameraaddressEqualTo(String value) {
            addCriterion("cameraAddress =", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressNotEqualTo(String value) {
            addCriterion("cameraAddress <>", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressGreaterThan(String value) {
            addCriterion("cameraAddress >", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressGreaterThanOrEqualTo(String value) {
            addCriterion("cameraAddress >=", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressLessThan(String value) {
            addCriterion("cameraAddress <", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressLessThanOrEqualTo(String value) {
            addCriterion("cameraAddress <=", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressLike(String value) {
            addCriterion("cameraAddress like", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressNotLike(String value) {
            addCriterion("cameraAddress not like", value, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressIn(List<String> values) {
            addCriterion("cameraAddress in", values, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressNotIn(List<String> values) {
            addCriterion("cameraAddress not in", values, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressBetween(String value1, String value2) {
            addCriterion("cameraAddress between", value1, value2, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andCameraaddressNotBetween(String value1, String value2) {
            addCriterion("cameraAddress not between", value1, value2, "cameraaddress");
            return (Criteria) this;
        }

        public Criteria andEdittimeIsNull() {
            addCriterion("editTime is null");
            return (Criteria) this;
        }

        public Criteria andEdittimeIsNotNull() {
            addCriterion("editTime is not null");
            return (Criteria) this;
        }

        public Criteria andEdittimeEqualTo(Date value) {
            addCriterion("editTime =", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeNotEqualTo(Date value) {
            addCriterion("editTime <>", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeGreaterThan(Date value) {
            addCriterion("editTime >", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("editTime >=", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeLessThan(Date value) {
            addCriterion("editTime <", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeLessThanOrEqualTo(Date value) {
            addCriterion("editTime <=", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeIn(List<Date> values) {
            addCriterion("editTime in", values, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeNotIn(List<Date> values) {
            addCriterion("editTime not in", values, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeBetween(Date value1, Date value2) {
            addCriterion("editTime between", value1, value2, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeNotBetween(Date value1, Date value2) {
            addCriterion("editTime not between", value1, value2, "edittime");
            return (Criteria) this;
        }

        public Criteria andEditorIsNull() {
            addCriterion("editor is null");
            return (Criteria) this;
        }

        public Criteria andEditorIsNotNull() {
            addCriterion("editor is not null");
            return (Criteria) this;
        }

        public Criteria andEditorEqualTo(String value) {
            addCriterion("editor =", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorNotEqualTo(String value) {
            addCriterion("editor <>", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorGreaterThan(String value) {
            addCriterion("editor >", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorGreaterThanOrEqualTo(String value) {
            addCriterion("editor >=", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorLessThan(String value) {
            addCriterion("editor <", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorLessThanOrEqualTo(String value) {
            addCriterion("editor <=", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorLike(String value) {
            addCriterion("editor like", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorNotLike(String value) {
            addCriterion("editor not like", value, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorIn(List<String> values) {
            addCriterion("editor in", values, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorNotIn(List<String> values) {
            addCriterion("editor not in", values, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorBetween(String value1, String value2) {
            addCriterion("editor between", value1, value2, "editor");
            return (Criteria) this;
        }

        public Criteria andEditorNotBetween(String value1, String value2) {
            addCriterion("editor not between", value1, value2, "editor");
            return (Criteria) this;
        }

        public Criteria andCapfaceidIsNull() {
            addCriterion("capfaceID is null");
            return (Criteria) this;
        }

        public Criteria andCapfaceidIsNotNull() {
            addCriterion("capfaceID is not null");
            return (Criteria) this;
        }

        public Criteria andCapfaceidEqualTo(Integer value) {
            addCriterion("capfaceID =", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidNotEqualTo(Integer value) {
            addCriterion("capfaceID <>", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidGreaterThan(Integer value) {
            addCriterion("capfaceID >", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("capfaceID >=", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidLessThan(Integer value) {
            addCriterion("capfaceID <", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidLessThanOrEqualTo(Integer value) {
            addCriterion("capfaceID <=", value, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidIn(List<Integer> values) {
            addCriterion("capfaceID in", values, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidNotIn(List<Integer> values) {
            addCriterion("capfaceID not in", values, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidBetween(Integer value1, Integer value2) {
            addCriterion("capfaceID between", value1, value2, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andCapfaceidNotBetween(Integer value1, Integer value2) {
            addCriterion("capfaceID not between", value1, value2, "capfaceid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidIsNull() {
            addCriterion("blacklistID is null");
            return (Criteria) this;
        }

        public Criteria andBlacklistidIsNotNull() {
            addCriterion("blacklistID is not null");
            return (Criteria) this;
        }

        public Criteria andBlacklistidEqualTo(Integer value) {
            addCriterion("blacklistID =", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidNotEqualTo(Integer value) {
            addCriterion("blacklistID <>", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidGreaterThan(Integer value) {
            addCriterion("blacklistID >", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidGreaterThanOrEqualTo(Integer value) {
            addCriterion("blacklistID >=", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidLessThan(Integer value) {
            addCriterion("blacklistID <", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidLessThanOrEqualTo(Integer value) {
            addCriterion("blacklistID <=", value, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidIn(List<Integer> values) {
            addCriterion("blacklistID in", values, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidNotIn(List<Integer> values) {
            addCriterion("blacklistID not in", values, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidBetween(Integer value1, Integer value2) {
            addCriterion("blacklistID between", value1, value2, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andBlacklistidNotBetween(Integer value1, Integer value2) {
            addCriterion("blacklistID not between", value1, value2, "blacklistid");
            return (Criteria) this;
        }

        public Criteria andEditoridIsNull() {
            addCriterion("editorID is null");
            return (Criteria) this;
        }

        public Criteria andEditoridIsNotNull() {
            addCriterion("editorID is not null");
            return (Criteria) this;
        }

        public Criteria andEditoridEqualTo(Integer value) {
            addCriterion("editorID =", value, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridNotEqualTo(Integer value) {
            addCriterion("editorID <>", value, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridGreaterThan(Integer value) {
            addCriterion("editorID >", value, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridGreaterThanOrEqualTo(Integer value) {
            addCriterion("editorID >=", value, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridLessThan(Integer value) {
            addCriterion("editorID <", value, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridLessThanOrEqualTo(Integer value) {
            addCriterion("editorID <=", value, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridIn(List<Integer> values) {
            addCriterion("editorID in", values, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridNotIn(List<Integer> values) {
            addCriterion("editorID not in", values, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridBetween(Integer value1, Integer value2) {
            addCriterion("editorID between", value1, value2, "editorid");
            return (Criteria) this;
        }

        public Criteria andEditoridNotBetween(Integer value1, Integer value2) {
            addCriterion("editorID not between", value1, value2, "editorid");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameIsNull() {
            addCriterion("departmentName is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameIsNotNull() {
            addCriterion("departmentName is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameEqualTo(String value) {
            addCriterion("departmentName =", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameNotEqualTo(String value) {
            addCriterion("departmentName <>", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameGreaterThan(String value) {
            addCriterion("departmentName >", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameGreaterThanOrEqualTo(String value) {
            addCriterion("departmentName >=", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameLessThan(String value) {
            addCriterion("departmentName <", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameLessThanOrEqualTo(String value) {
            addCriterion("departmentName <=", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameLike(String value) {
            addCriterion("departmentName like", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameNotLike(String value) {
            addCriterion("departmentName not like", value, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameIn(List<String> values) {
            addCriterion("departmentName in", values, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameNotIn(List<String> values) {
            addCriterion("departmentName not in", values, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameBetween(String value1, String value2) {
            addCriterion("departmentName between", value1, value2, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentnameNotBetween(String value1, String value2) {
            addCriterion("departmentName not between", value1, value2, "departmentname");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNull() {
            addCriterion("departmentID is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNotNull() {
            addCriterion("departmentID is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidEqualTo(String value) {
            addCriterion("departmentID =", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotEqualTo(String value) {
            addCriterion("departmentID <>", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThan(String value) {
            addCriterion("departmentID >", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThanOrEqualTo(String value) {
            addCriterion("departmentID >=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThan(String value) {
            addCriterion("departmentID <", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThanOrEqualTo(String value) {
            addCriterion("departmentID <=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLike(String value) {
            addCriterion("departmentID like", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotLike(String value) {
            addCriterion("departmentID not like", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIn(List<String> values) {
            addCriterion("departmentID in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotIn(List<String> values) {
            addCriterion("departmentID not in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidBetween(String value1, String value2) {
            addCriterion("departmentID between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotBetween(String value1, String value2) {
            addCriterion("departmentID not between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        protected String getStringColumns() {
            return "listFeature,listFaceURL,faceURL,backGroundURL,cameraAddress,editor,departmentName,departmentID,";
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