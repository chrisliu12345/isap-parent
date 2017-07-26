package com.gosun.isap.face.api.Bean;

import java.util.List;

/**
 * 人脸服务器与部门关联<br>
 * <p>创建时间：2017/05/16</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class FaceServerRelationBean {
	
	private Integer faceServerID;			//人脸服务器ID
	private List<String> departmentIDList;			//部门ID

	public Integer getFaceServerID() {
		return faceServerID;
	}
	public void setFaceServerID(Integer faceServerID) {
		this.faceServerID = faceServerID;
	}
	public List<String> getDepartmentIDList() {
		return departmentIDList;
	}
	public void setDepartmentIDList(List<String> departmentIDList) {
		this.departmentIDList = departmentIDList;
	}
	
}
