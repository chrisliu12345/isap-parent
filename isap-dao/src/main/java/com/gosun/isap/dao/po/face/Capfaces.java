package com.gosun.isap.dao.po.face;

import java.io.Serializable;
import java.util.Date;

public class Capfaces implements Serializable {
    private Integer id;

    private Integer cameraid;

    private String faceurl;

    private String backgroundurl;

    private String feature;

    private String cameraname;

    private String address;

    private Integer usedflag;

    private Integer state;

    private String creator;

    private Date createtime;

    private String lasteditor;

    private Date lastedittime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCameraid() {
        return cameraid;
    }

    public void setCameraid(Integer cameraid) {
        this.cameraid = cameraid;
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl == null ? null : faceurl.trim();
    }

    public String getBackgroundurl() {
        return backgroundurl;
    }

    public void setBackgroundurl(String backgroundurl) {
        this.backgroundurl = backgroundurl == null ? null : backgroundurl.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname == null ? null : cameraname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getUsedflag() {
        return usedflag;
    }

    public void setUsedflag(Integer usedflag) {
        this.usedflag = usedflag;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLasteditor() {
        return lasteditor;
    }

    public void setLasteditor(String lasteditor) {
        this.lasteditor = lasteditor == null ? null : lasteditor.trim();
    }

    public Date getLastedittime() {
        return lastedittime;
    }

    public void setLastedittime(Date lastedittime) {
        this.lastedittime = lastedittime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}