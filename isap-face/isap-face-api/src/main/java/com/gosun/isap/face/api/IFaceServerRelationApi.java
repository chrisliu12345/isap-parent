package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.face.FaceServerRelationBean;
/**
 * 部门与人脸服务器关联<br>
 * <p>创建时间：2017/05/04</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
@Path("blackwhitelist")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface IFaceServerRelationApi {
	/**
	 * <p>
	 * 人脸服务器与部门关联
	 * </p>
	 * 
	 * @param faceServerRelation 部门与人脸服务器关联信息
	 * @return 响应数据
	 */
	@POST
	@Path("faceserverrelation")
	ResponseResult faceServerRelation(FaceServerRelationBean faceServerRelation);
}
