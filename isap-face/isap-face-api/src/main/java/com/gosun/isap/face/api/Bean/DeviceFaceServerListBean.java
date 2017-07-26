package com.gosun.isap.face.api.Bean;

import java.util.List;

import com.gosun.isap.dao.po.face.TFaceServer;

public class DeviceFaceServerListBean {

	List<TFaceServer> faceServerList;
	
	List<CameraDeviceBean> deviceList;
	
	Long bindingID;

	public List<TFaceServer> getFaceServerList() {
		return faceServerList;
	}

	public void setFaceServerList(List<TFaceServer> faceServerList) {
		this.faceServerList = faceServerList;
	}

	public List<CameraDeviceBean> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<CameraDeviceBean> deviceList) {
		this.deviceList = deviceList;
	}
	
	public Long getBindingID() {
		return bindingID;
	}

	public void setBindingID(Long bindingID) {
		this.bindingID = bindingID;
	}
	
}

