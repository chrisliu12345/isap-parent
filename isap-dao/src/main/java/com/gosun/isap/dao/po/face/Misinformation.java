package com.gosun.isap.dao.po.face;

import java.io.Serializable;
import java.util.Date;

public class Misinformation implements Serializable {
    private Integer id;

    private Integer listtype;

    private String listfeature;

    private String listfaceurl;

    private String faceurl;

    private String backgroundurl;

    private Double similarity;

    private Date capfacestime;

    private String cameraaddress;

    private Date edittime;

    private String editor;

    private Integer capfaceid;

    private Integer blacklistid;

    private Integer editorid;

    private String departmentname;

    private String departmentid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListtype() {
        return listtype;
    }

    public void setListtype(Integer listtype) {
        this.listtype = listtype;
    }

    public String getListfeature() {
        return listfeature;
    }

    public void setListfeature(String listfeature) {
        this.listfeature = listfeature == null ? null : listfeature.trim();
    }

    public String getListfaceurl() {
        return listfaceurl;
    }

    public void setListfaceurl(String listfaceurl) {
        this.listfaceurl = listfaceurl == null ? null : listfaceurl.trim();
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

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public Date getCapfacestime() {
        return capfacestime;
    }

    public void setCapfacestime(Date capfacestime) {
        this.capfacestime = capfacestime;
    }

    public String getCameraaddress() {
        return cameraaddress;
    }

    public void setCameraaddress(String cameraaddress) {
        this.cameraaddress = cameraaddress == null ? null : cameraaddress.trim();
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
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

    public Integer getEditorid() {
        return editorid;
    }

    public void setEditorid(Integer editorid) {
        this.editorid = editorid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }
}