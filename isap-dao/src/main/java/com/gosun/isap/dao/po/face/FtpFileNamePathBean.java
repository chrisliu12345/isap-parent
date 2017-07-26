package com.gosun.isap.dao.po.face;

public class FtpFileNamePathBean {
	private TFaceServer faceServer;									//人脸服务器信息
	private String fileNamePath;										//文件路径

	public TFaceServer getFaceServer() {
		return faceServer;
	}
	public void setFaceServer(TFaceServer faceServer) {
		this.faceServer = faceServer;
	}
	public String getFileNamePath() {
		return fileNamePath;
	}
	public void setFileNamePath(String fileNamePath) {
		this.fileNamePath = fileNamePath;
	}
	
	
}
