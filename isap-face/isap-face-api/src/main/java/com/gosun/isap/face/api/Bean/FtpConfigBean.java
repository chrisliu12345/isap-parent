package com.gosun.isap.face.api.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * FTP服务器信息<br>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class FtpConfigBean {
		@SerializedName("FtpIP")
		private String ftpIP;														//ftpID
		@SerializedName("FtpPort")
		private String ftpPort;													//ftp端口
		@SerializedName("FtpUserName")
		private String ftpUserName;											//ftp用户名
		@SerializedName("FtpPassword")
		private String ftpPassword;											//ftp密码
		
		public String getFtpIP() {
			return ftpIP;
		}
		public void setFtpIP(String ftpIP) {
			this.ftpIP = ftpIP;
		}
		public String getFtpPort() {
			return ftpPort;
		}
		public void setFtpPort(String ftpPort) {
			this.ftpPort = ftpPort;
		}
		public String getFtpUserName() {
			return ftpUserName;
		}
		public void setFtpUserName(String ftpUserName) {
			this.ftpUserName = ftpUserName;
		}
		public String getFtpPassword() {
			return ftpPassword;
		}
		public void setFtpPassword(String ftpPassword) {
			this.ftpPassword = ftpPassword;
		}
}
