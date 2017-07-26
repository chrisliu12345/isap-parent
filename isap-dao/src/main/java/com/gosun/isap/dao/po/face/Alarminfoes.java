package com.gosun.isap.dao.po.face;

import java.io.Serializable;
import java.util.Date;

public class Alarminfoes implements Serializable {
    private Integer id;

    private Integer contactid;

    private String name;

    private String idcard;

    private Integer capfaceid;

    private Integer blacklistid;

    private String faceimg;

    private String blacklistimg;

    private String backgroundimg;

    private Double simi;

    private String reason;

    private String cameraname;

    private String capaddr;

    private String hostname;

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

    public Integer getContactid() {
        return contactid;
    }

    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getCapfaceid() {
        return capfaceid;
    }

    public void setCapfaceid(Integer capfaceid) {
        this.capfaceid = capfaceid;
    }

    public Integer getBlacklistid() {
        return blacklistid;
    }

    public void setBlacklistid(Integer blacklistid) {
        this.blacklistid = blacklistid;
    }

    public String getFaceimg() {
        return faceimg;
    }

    public void setFaceimg(String faceimg) {
        this.faceimg = faceimg == null ? null : faceimg.trim();
    }

    public String getBlacklistimg() {
        return blacklistimg;
    }

    public void setBlacklistimg(String blacklistimg) {
        this.blacklistimg = blacklistimg == null ? null : blacklistimg.trim();
    }

    public String getBackgroundimg() {
        return backgroundimg;
    }

    public void setBackgroundimg(String backgroundimg) {
        this.backgroundimg = backgroundimg == null ? null : backgroundimg.trim();
    }

    public Double getSimi() {
        return simi;
    }

    public void setSimi(Double simi) {
        this.simi = simi;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname == null ? null : cameraname.trim();
    }

    public String getCapaddr() {
        return capaddr;
    }

    public void setCapaddr(String capaddr) {
        this.capaddr = capaddr == null ? null : capaddr.trim();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
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