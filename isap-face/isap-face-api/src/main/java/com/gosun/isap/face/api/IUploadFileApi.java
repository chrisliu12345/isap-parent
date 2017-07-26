package com.gosun.isap.face.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.face.FtpFileNamePathBean;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.Bean.ManInfoBean;
import com.gosun.isap.face.api.Bean.iGFSBean.ImgBean;

/**
 * 与FTP服务器相关的操作<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
@Path("blackwhitelist")
public interface IUploadFileApi {

	/**
	 * <p>
	 * 根据照片信息得到特征值
	 * </p>
	 * 
	 * @param imgBean
	 *            照片信息
	 * @return 响应数据
	 */
	@POST
	@Path("getfeature")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_JSON })
	ResponseResult getFeature(@MultipartForm ImgBean imgBean);

	/**
	 * 增加人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	ResponseResult addList(ManInfoBean manInfoBean);

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
	java.util.List<FtpFileNamePathBean> renameFile(List<TFaceServer> faceServerList, String faceURL, int fileTpye);
}
