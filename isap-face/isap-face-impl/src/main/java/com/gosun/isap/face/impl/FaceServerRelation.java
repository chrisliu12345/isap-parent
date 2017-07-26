package com.gosun.isap.face.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.face.TFaceServerDepMapper;
import com.gosun.isap.dao.mapper.face.TFaceServerMapper;
import com.gosun.isap.dao.mapper.face.customer.FaceTDepartmentMapperCustomer;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.face.FaceServerRelationBean;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.TFaceServerDep;
import com.gosun.isap.dao.po.face.TFaceServerDepExample;
import com.gosun.isap.face.api.IFaceServerRelationApi;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.FaceServerRelationService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 部门与人脸服务器关联<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class FaceServerRelation implements IFaceServerRelationApi {
	private static Logger logger = LoggerFactory.getLogger(FaceServerRelation.class);
	@Autowired
	private TFaceServerDepMapper faceServerDepMapper;
	@Autowired
	private FaceTDepartmentMapperCustomer faceTDepartmentMapperCustomer;
	@Autowired
	private TFaceServerMapper faceServerMapper;
	@Autowired
	private FaceServerRelationService faceServerRelationService;

	/**
	 * 部门与人脸服务器关联
	 * 
	 * @param faceServerRelation
	 *            部门与人脸服务器关联信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType = ServiceType.FACE_SERVER, operateType = OperateType.CONFIG_ADD, description = "人脸服务器与社区关联")
	public ResponseResult faceServerRelation(FaceServerRelationBean faceServerRelation) {
		// TODO Auto-generated method stub
		ResponseResult response = ResponseResult.ok();
		logger.debug("faceServerRelation Start");
		int re = saveFaceServerRelation(faceServerRelation);

		switch (re) {
		case -1:
			// 人脸服务器与社区关联失败
			response.setErrorEx(ConfigConstants.ADD_FACE_SERVER_DEPARTMENT_ERR, null);
			break;
		case 1:
			// 增加数据重复
			response.setErrorEx(ConfigConstants.ADD_DATA_EXISTS, null);
			break;
		default:
			break;
		}
		logger.debug("faceServerRelation End");
		return response;
	}

	private int saveFaceServerRelation(FaceServerRelationBean faceServerRelation) {

		TFaceServer faceServer = faceServerMapper.selectByPrimaryKey(faceServerRelation.getFaceServerID());
		if (faceServer == null) {
			// 数据库没有人脸数据库信息
			return -1;
		}

		// 根据相机得到其父部门
		TDepartment department = faceTDepartmentMapperCustomer
				.selectByKeyToParentInfo(faceServerRelation.getCameraDeviceBeanList().get(0).getId());

		TFaceServerDepExample faceServerDepExample = new TFaceServerDepExample();
		TFaceServerDepExample.Criteria faceServerDepCriteria = faceServerDepExample.createCriteria();
		faceServerDepCriteria.andDepartmentIdEqualTo(department.getId());
		faceServerDepCriteria.andServerIdEqualTo(faceServerRelation.getFaceServerID());
		List<TFaceServerDep> faceServerDepList = faceServerDepMapper.selectByExample(faceServerDepExample);
		TFaceServerDep faceServerDepRecord = new TFaceServerDep();
		// 判断部门与人脸服务器是否关联
		if (faceServerDepList == null || faceServerDepList.isEmpty()) {
			// 人脸与部门关联起来
			faceServerDepRecord.setServerId(faceServerRelation.getFaceServerID());
			faceServerDepRecord.setDepartmentId(department.getId());
			faceServerDepMapper.insertSelective(faceServerDepRecord);
		}
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);

		return faceServerRelationService.saveFaceServerRelationService(faceServerRelation, faceServerDepList, department);

	}
}
