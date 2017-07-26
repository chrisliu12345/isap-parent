package com.gosun.isap.dao.po.face;

import java.io.Serializable;
import java.util.List;

/**
 * 人脸服务器与部门关联<br>
 * <p>创建时间：2017/05/16</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class FaceServerRelationBean implements Serializable{
	
	private Long faceServerID;												//人脸服务器ID
	private List<CameraDeviceBean> cameraDeviceBeanList;			//相机信息

	public Long getFaceServerID() {
		return faceServerID;
	}
	public void setFaceServerID(Long faceServerID) {
		this.faceServerID = faceServerID;
	}
	public List<CameraDeviceBean> getCameraDeviceBeanList() {
		return cameraDeviceBeanList;
	}
	public void setCameraDeviceBeanList(List<CameraDeviceBean> cameraDeviceBeanList) {
		this.cameraDeviceBeanList = cameraDeviceBeanList;
	}

}
