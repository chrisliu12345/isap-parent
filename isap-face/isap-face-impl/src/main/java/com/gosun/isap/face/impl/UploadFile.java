package com.gosun.isap.face.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Blacklists;
import com.gosun.isap.dao.po.face.FtpFileNamePathBean;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.Whitelists;
import com.gosun.isap.face.api.IListApi;
import com.gosun.isap.face.api.IUploadFileApi;
import com.gosun.isap.face.api.Bean.FtpConfigBean;
import com.gosun.isap.face.api.Bean.ManInfoBean;
import com.gosun.isap.face.api.Bean.iGFSBean.ImgBean;
import com.gosun.isap.face.api.Bean.iGFSBean.ImgBeanCafe;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.BlackWhiteListService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 与FTP服务器相关的操作<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class UploadFile implements IUploadFileApi {
	private static Logger logger = LoggerFactory.getLogger(UploadFile.class);
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;
	@Autowired
	private BlackWhiteListService blackWhiteListService;
	@Autowired
	private IListApi listApi;

	/**
	 * <p>
	 * 根据照片信息得到特征值
	 * </p>
	 * 
	 * @param imgBean
	 *            照片信息
	 * @return 响应数据
	 */
	public ResponseResult getFeature(ImgBean imgBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		String feature = new String(); // webapi返回信息
		logger.debug("getFeature start");
		// url的设置
		ImgBeanCafe imgBeanCafe = new ImgBeanCafe();
		String imgdataString = UploadFile.byteToString(imgBean.getImgdata());

		imgBeanCafe.setHeight(imgBean.getHeight());
		imgBeanCafe.setWidth(imgBean.getWidth());
		imgBeanCafe.setImgdata(imgdataString);

		TUser user = UserUtil.getCurrentUser();
		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}

		if (faceServerList == null || faceServerList.isEmpty()) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		String url = ConfigConstants.HTTP + faceServerList.get(0).getIp() + ConfigConstants.COLON
				+ faceServerList.get(0).getPort().toString() + ConfigConstants.SLASH + ConfigConstants.GETFEATURE;

		// json传参
		String jsonStr = JsonHelper.getInstance().createJsonString(imgBeanCafe);

		// 连接URL
		logger.debug("getFeature webapi start");
		try {
			feature = IGFSCommunication.post(url, jsonStr);
		} catch (org.apache.http.ParseException | IOException e) {
			// 特征值取得发生异常
			logger.error(e.getMessage());
			response.setErrorEx(ConfigConstants.GET_FEATURE_ERR, null);
			return response;
		}
		logger.debug("getFeature webapi end");

		// TODO 反馈的判断
		if (feature.isEmpty() || feature.length() != ConfigConstants.FEATUREBASE64LENGTH) {
			// 特征值取得失败
			logger.debug(feature);
			logger.debug(String.valueOf(feature.length()));
			response.setErrorEx(ConfigConstants.GET_FEATURE_ERR, null);
			return response;
		} else {
			// 特征值取得成功

			List<FtpFileNamePathBean> ftpFileNamePathBeanList = uploadFile(faceServerList, imgdataString,
					ConfigConstants.IMPORT);

			if (ftpFileNamePathBeanList == null || ftpFileNamePathBeanList.isEmpty()) {
				// 图片上传失败
				response.setErrorEx(ConfigConstants.PICTURE_UPLOAD_ERR, null);
				return response;
			}
			ManInfoBean manInfoBean = new ManInfoBean();
			manInfoBean.setFaceURL(ftpFileNamePathBeanList.get(0).getFileNamePath());

			manInfoBean.setFeature(feature);
			response.setBody(manInfoBean);
		}
		logger.debug("getFeature end");
		return response;
	}

	/**
	 * 增加人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType = ServiceType.LIST_ADMINISTRATION, operateType = OperateType.CONFIG_ADD, description = "名单导入")
	public ResponseResult addList(ManInfoBean manInfoBean) {
		ResponseResult response = ResponseResult.ok();
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		Blacklists blacklists = new Blacklists(); // 插入数据
		Whitelists whitelists = new Whitelists(); // 插入数据

		TUser user = UserUtil.getCurrentUser();
		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}
		if (faceServerList == null || faceServerList.isEmpty()) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		// 数据库数据同步
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		}
		// 身份证认证
		boolean bre = listApi.idCodeAuthentication(manInfoBean.getIDCard());
		if (bre == false) {
			// 身份证认证出错
			response.setErrorEx(ConfigConstants.IDCODE_ERR, null);
			return response;
		}
		// 数据认证
		int re = listApi.stringCodeAuthentication(manInfoBean.getName(), manInfoBean.getReason());
		if (re != 0) {
			// 名单名字与备注不能为空
			response.setErrorEx(ConfigConstants.DATA_ERR, null);
			return response;
		}
		// 图片上传
		List<FtpFileNamePathBean> ftpFileNamePathBeanList = renameFile(faceServerList, manInfoBean.getFaceURL(),
				manInfoBean.getListType());
		if (ftpFileNamePathBeanList == null || ftpFileNamePathBeanList.isEmpty()) {
			// 图片上传失败
			response.setErrorEx(ConfigConstants.PICTURE_UPLOAD_ERR, null);
			return response;
		}

		re = 0;
		if (ConfigConstants.BLACKLIST.equals(manInfoBean.getListType())) {
			// 数据设置
			blacklists.setContactid(ConfigConstants.ADMINISTRATORSID.intValue());
			blacklists.setCreatetime(new Date());
			// TODO 创建者
			blacklists.setCreator(user.getId().toString());
			blacklists.setFeature(manInfoBean.getFeature());
			blacklists.setIdcard(manInfoBean.getIDCard());
			blacklists.setName(manInfoBean.getName());
			blacklists.setReason(manInfoBean.getReason());
			// 备注
			// blacklists.setRemark();
			blacklists.setSex(manInfoBean.getSex());
			blacklists.setState(ConfigConstants.STATE_1);
			blacklists.setUsedflag(ConfigConstants.USEDFLAG_1);
			for (int i = 0; i < ftpFileNamePathBeanList.size(); i++) {
				// 切换数据库
				DataSourceContextHolder.setDataSourceType(ftpFileNamePathBeanList.get(i).getFaceServer());
				String fileNamePath = ftpFileNamePathBeanList.get(i).getFileNamePath();
				re = blackWhiteListService.saveBlackList(fileNamePath, blacklists, user);
				if (re != 0) {
					break;
				}
			}
		} else {
			// 数据设置
			whitelists.setContactid(ConfigConstants.ADMINISTRATORSID.intValue());
			whitelists.setCreatetime(new Date());
			// TODO 创建者
			whitelists.setCreator(user.getId().toString());
			whitelists.setFeature(manInfoBean.getFeature());
			whitelists.setIdcard(manInfoBean.getIDCard());
			whitelists.setName(manInfoBean.getName());
			whitelists.setReason(manInfoBean.getReason());
			// 备注
			// whitelists.setRemark();
			whitelists.setSex(manInfoBean.getSex());
			whitelists.setState(ConfigConstants.STATE_1);
			whitelists.setUsedflag(ConfigConstants.USEDFLAG_1);
			// 将人员信息增加到数据库中
			for (int i = 0; i < ftpFileNamePathBeanList.size(); i++) {
				// 切换数据库
				DataSourceContextHolder.setDataSourceType(ftpFileNamePathBeanList.get(i).getFaceServer());
				String fileNamePath = ftpFileNamePathBeanList.get(i).getFileNamePath();
				re = blackWhiteListService.saveWhiteList(fileNamePath, whitelists, user);
				if (re != 0) {
					break;
				}
			}
		}
		if (re == 1) {
			// 插入数据重复
			response.setErrorEx(ConfigConstants.ADD_DATA_EXISTS, null);
		}
		return response;
	}

	/**
	 * <p>
	 * 得到FTP服务器信息
	 * </p>
	 * 
	 * @param faceServerList
	 *            人脸服务器信息
	 * @return 响应数据
	 */
	public static java.util.List<FtpConfigBean> getFtpConfig(java.util.List<TFaceServer> faceServerList) {
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		java.util.List<FtpConfigBean> ftpConfigBeanList = new ArrayList<FtpConfigBean>();
		String xmlget = new String();

		for (int i = 0; i < faceServerList.size(); i++) {

			FtpConfigBean ftpConfigBean = new FtpConfigBean();
			// url的设置
			String url = ConfigConstants.HTTP + faceServerList.get(i).getIp() + ConfigConstants.COLON
					+ faceServerList.get(i).getPort().toString() + ConfigConstants.SLASH + ConfigConstants.GETFTPCONFIG;
			// 响应数据的取得
			try {
				xmlget = IGFSCommunication.get(url);
				// 判断xmlget是否为空
				if (xmlget == null) {
					return null;
				} else {
					// json数据的反序列化
					ftpConfigBean = JsonHelper.getInstance().getObject(xmlget, FtpConfigBean.class);
					ftpConfigBeanList.add(ftpConfigBean);
				}
			} catch (ParseException | IOException e) {
				// 与webapi连接发生异常
				logger.error(e.getMessage());
				return null;
			}
		}
		return ftpConfigBeanList;
	}

	/**
	 * 将图片上传到ftp服务器上
	 * 
	 * @param faceServerList
	 *            人脸服务器信息
	 * @param imgdata
	 *            图标信息
	 * @param fileTpye
	 *            图片类型
	 * @return 响应数据
	 */
	public static java.util.List<FtpFileNamePathBean> uploadFile(List<TFaceServer> faceServerList, String imgdata,
			int fileTpye) {

		String filePath = new String();
		Integer size = new Integer(0);
		FTPClient ftpClient = new FTPClient();
		List<FtpConfigBean> ftpConfigBeanList = getFtpConfig(faceServerList);
		java.util.List<FtpFileNamePathBean> ftpFileNamePathBeanList = new ArrayList<FtpFileNamePathBean>();

		if (ftpConfigBeanList == null || ftpConfigBeanList.isEmpty()) {
			return null;
		}
		if (ConfigConstants.BLACKLIST.equals(fileTpye)) {
			filePath = ConfigConstants.BLACKLISTPATH;
			size = ftpConfigBeanList.size();
		} else if (ConfigConstants.WHITELIST.equals(fileTpye)) {
			filePath = ConfigConstants.WHITELISPATH;
			size = ftpConfigBeanList.size();
		} else {
			filePath = ConfigConstants.PATH;
			size = 1;
		}
		for (int i = 0; i < size; i++) {
			try {
				// FTP服务器ip和端口
				String ftpIP = ftpConfigBeanList.get(i).getFtpIP();
				String ftpPort = ftpConfigBeanList.get(i).getFtpPort();
				String ftpUserName = ftpConfigBeanList.get(i).getFtpUserName();
				String ftpPassword = ftpConfigBeanList.get(i).getFtpPassword();

				ftpClient.connect(ftpIP, Integer.valueOf(ftpPort));
				// 登录
				boolean loginStatus = ftpClient.login(ftpUserName, ftpPassword);
				if (loginStatus) {
					// 开启端口
					ftpClient.enterLocalPassiveMode();
					FtpFileNamePathBean ftpFileNamePathBean = new FtpFileNamePathBean();
					ftpClient.changeWorkingDirectory(filePath);

					SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
					String time = f.format(new Date());
					String fileName = time + ".jpg";
					// 设置文件
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					OutputStream out = ftpClient.appendFileStream(fileName);

					out.write(generateImage(imgdata));
					out.flush();
					out.close();
					String fileNamePath = "ftp://" + ftpUserName + ":" + ftpPassword + "@" + ftpIP + ":" + ftpPort + "/"
							+ filePath + "/" + fileName;
					ftpFileNamePathBean.setFileNamePath(fileNamePath);
					ftpFileNamePathBean.setFaceServer(faceServerList.get(i));
					ftpFileNamePathBeanList.add(ftpFileNamePathBean);
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				return null;
			}
		}
		return ftpFileNamePathBeanList;
	}

	/**
	 * 重命名ftp照片
	 * 
	 * @param faceServerList
	 *            人脸服务器信息
	 * @param faceURL
	 *            图片路径
	 * @param fileTpye
	 *            图片类型
	 * @return 响应数据
	 */
	public java.util.List<FtpFileNamePathBean> renameFile(List<TFaceServer> faceServerList, String faceURL,
			int fileTpye) {
		// 数据初始化
		Integer size = new Integer(0);
		java.util.List<FtpFileNamePathBean> ftpFileNamePathBeanList = new ArrayList<FtpFileNamePathBean>();
		FTPClient ftpClient = new FTPClient();
		List<FtpConfigBean> ftpConfigBeanList = getFtpConfig(faceServerList);
		String sFileTpye = new String();

		// 设置图片路径
		if (ConfigConstants.BLACKLIST.equals(fileTpye)) {
			sFileTpye = ConfigConstants.BLACKLISTPATH;
		} else if (ConfigConstants.WHITELIST.equals(fileTpye)) {
			sFileTpye = ConfigConstants.WHITELISPATH;
		} else if (ConfigConstants.FILTERLIST.equals(fileTpye)) {
			sFileTpye = ConfigConstants.FILTERPATH;
		}
		size = 1;

		String ftpIP = faceURL.substring(faceURL.indexOf("@") + 1, faceURL.indexOf(":", faceURL.indexOf("@")));
		String ftpPort = faceURL.substring(faceURL.indexOf(":", faceURL.indexOf("@")) + 1,
				faceURL.indexOf("/", faceURL.indexOf(":", faceURL.indexOf("@"))));
		String ftpUserName = faceURL.substring(6, faceURL.indexOf(":", 6));
		String ftpPassword = faceURL.substring(faceURL.indexOf(":", 6) + 1, faceURL.indexOf("@"));

		String newFileNamePath = new String();
		try {
			// 设置ftp服务器地址
			ftpClient.connect(ftpIP, Integer.valueOf(ftpPort));
			// 登录
			boolean loginStatus = ftpClient.login(ftpUserName, ftpPassword);
			// 是否登录成功
			if (loginStatus) {
				// 开启端口
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				// 取得照片路径
				String filePath = faceURL.substring(0,
						faceURL.indexOf(ConfigConstants.PATH) + ConfigConstants.PATH.length() + 1);
				// 取得照片名
				String fileName = faceURL
						.substring(faceURL.indexOf(ConfigConstants.PATH) + ConfigConstants.PATH.length() + 1);
				ftpClient.changeWorkingDirectory(ConfigConstants.PATH);
				FTPFile[] files = ftpClient.listFiles(fileName);
				if (files.length == 1) {
					boolean status = ftpClient.rename(fileName, sFileTpye + fileName);
					if (!status) {
						return null;
					}
				} else {
					return null;
				}
				newFileNamePath = filePath + sFileTpye + fileName;
			}
		} catch (IOException e) {
			// 操作ftp文件异常
			logger.error(e.getMessage());
			return null;
		}

		// 设置将人类服务器IP与文件名
		for (int i = 0; i < size; i++) {
			FtpFileNamePathBean ftpFileNamePathBean = new FtpFileNamePathBean();
			ftpFileNamePathBean.setFileNamePath(newFileNamePath);
			ftpFileNamePathBean.setFaceServer(faceServerList.get(i));
			ftpFileNamePathBeanList.add(ftpFileNamePathBean);
		}
		return ftpFileNamePathBeanList;
	}

	/**
	 * 将String类型转换成byte[]类型(对字节数组字符串进行Base64解码并生成图片)
	 * 
	 * @param imgStr
	 *            照片信息
	 * @return byte[]类型
	 * @throws IOException
	 */
	public static byte[] generateImage(String imgStr) throws IOException {

		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += ConfigConstants.BASE64;
			}
		}
		return b;
	}

	/**
	 * 将byte[]类型转换成String类型
	 * 
	 * @param imgdata
	 *            照片信息
	 * @return String类型
	 */
	public static String byteToString(byte[] imgdata) {

		BASE64Encoder encoder = new BASE64Encoder();
		String string = encoder.encodeBuffer(imgdata);
		string.trim();
		string = string.replace("\r\n", "").replace("\n", "");
		for (int i = 0; i < string.length(); i++) {
			String s = string.substring(string.length() - (1 + i), string.length() - i);
			if (s.equals("=")) {
				string = string.substring(0, string.length() - (1 + i));
				break;
			}
			System.out.println(s);
		}
		return string;
	}
}
