package com.gosun.isap.dao.po.face;

import java.io.Serializable;
import java.util.Date;

public class Hosts implements Serializable {
    private Integer id;

    private Integer origanizeid;

    private String name;

    private String code;

    private String address;

    private String hostip;

    private String ftpip;

    private Integer ftpport;

    private String ftpusername;

    private String ftppassword;

    private String fileload;

    private Integer filegettype;

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

    public Integer getOriganizeid() {
        return origanizeid;
    }

    public void setOriganizeid(Integer origanizeid) {
        this.origanizeid = origanizeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getHostip() {
        return hostip;
    }

    public void setHostip(String hostip) {
        this.hostip = hostip == null ? null : hostip.trim();
    }

    public String getFtpip() {
        return ftpip;
    }

    public void setFtpip(String ftpip) {
        this.ftpip = ftpip == null ? null : ftpip.trim();
    }

    public Integer getFtpport() {
        return ftpport;
    }

    public void setFtpport(Integer ftpport) {
        this.ftpport = ftpport;
    }

    public String getFtpusername() {
        return ftpusername;
    }

    public void setFtpusername(String ftpusername) {
        this.ftpusername = ftpusername == null ? null : ftpusername.trim();
    }

    public String getFtppassword() {
        return ftppassword;
    }

    public void setFtppassword(String ftppassword) {
        this.ftppassword = ftppassword == null ? null : ftppassword.trim();
    }

    public String getFileload() {
        return fileload;
    }

    public void setFileload(String fileload) {
        this.fileload = fileload == null ? null : fileload.trim();
    }

    public Integer getFilegettype() {
        return filegettype;
    }

    public void setFilegettype(Integer filegettype) {
        this.filegettype = filegettype;
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