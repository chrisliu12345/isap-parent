package com.gosun.isap.face.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.face.CamerasMapper;
import com.gosun.isap.dao.mapper.face.HostsMapper;
import com.gosun.isap.dao.mapper.face.OriganizesMapper;
import com.gosun.isap.dao.mapper.face.customer.HostsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.OriganizesMapperCustomer;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.face.CameraDeviceBean;
import com.gosun.isap.dao.po.face.Cameras;
import com.gosun.isap.dao.po.face.CamerasExample;
import com.gosun.isap.dao.po.face.FaceServerRelationBean;
import com.gosun.isap.dao.po.face.Hosts;
import com.gosun.isap.dao.po.face.HostsExample;
import com.gosun.isap.dao.po.face.Origanizes;
import com.gosun.isap.dao.po.face.TFaceServerDep;
import com.gosun.isap.face.api.service.FaceServerRelationService;

@Service
public class FaceServerRelationServiceImpl implements FaceServerRelationService {

	@Autowired
	private HostsMapper hostsMapper;
	@Autowired
	private CamerasMapper camerasMapper;
	@Autowired
	private HostsMapperCustomer hostsMapperCustomer;
	@Autowired
	private OriganizesMapper origanizesMapper;
	@Autowired
	private OriganizesMapperCustomer origanizesMapperCustomer;
	/**
	 * 人脸服务器与社区关联
	 */
	@Override
	public int saveFaceServerRelationService(FaceServerRelationBean faceServerRelation,
			List<TFaceServerDep> faceServerDepList, TDepartment department) {

		int re = 0;
		// 判断人脸组织是否存在
		Origanizes origanizes = origanizesMapperCustomer.selectByContactid(1);
		if (origanizes == null) {
			Origanizes origanizesRecord = new Origanizes();
			origanizesRecord.setContactid(1);
			origanizesRecord.setState(1);
			re = origanizesMapper.insertSelective(origanizesRecord);
			if (re == 0) {
				return -1;
			}
			origanizes = origanizesMapperCustomer.selectByContactid(1);
		}
		Hosts hostsRecord = new Hosts();
		// 判断部门与人脸服务器是否关联
		if (faceServerDepList == null || faceServerDepList.isEmpty()) {
			// 设置人脸系统的主机属性
			hostsRecord.setOriganizeid(origanizes.getId());
			hostsRecord.setName(department.getName());
			hostsRecord.setRemark(department.getId());
			hostsRecord.setAddress(department.getName());
			hostsRecord.setUsedflag(1);
			hostsRecord.setState(1);
			re = hostsMapper.insertSelective(hostsRecord);
			if (re == 0) {
				return -1;
			}
		} else {
			HostsExample hostsexample = new HostsExample();
			HostsExample.Criteria criteria = hostsexample.createCriteria();
			criteria.andRemarkEqualTo(department.getId());
			// 更新人脸系统的主机属性
			hostsRecord.setName(department.getName());
			hostsRecord.setAddress(department.getName());
			re = hostsMapper.updateByExampleSelective(hostsRecord, hostsexample);
			if (re == 0) {
				return -1;
			}
		}
		// 从人脸系统中取得刚刚插入的社区id
		Hosts hosts = hostsMapperCustomer.selectbyRemarkToID(department.getId());
		for (int j = 0; j < faceServerRelation.getCameraDeviceBeanList().size(); j++) {

			CameraDeviceBean cameraDeviceBean = faceServerRelation.getCameraDeviceBeanList().get(j);
			Cameras camerasrecord = new Cameras();

			if (cameraDeviceBean.getFaceFalg().equals(0)) {
				// 增加相机信息
				CamerasExample example = new CamerasExample();
				CamerasExample.Criteria criteria = example.createCriteria();
				criteria.andRemarkEqualTo(cameraDeviceBean.getId());
				List<Cameras> camerasList = camerasMapper.selectByExample(example);
				if (camerasList != null && !camerasList.isEmpty()) {
					return 1;
				}
				camerasrecord.setHostid(hosts.getId());
				camerasrecord.setName(cameraDeviceBean.getName());
				camerasrecord.setCode(cameraDeviceBean.getFaceCode());
				camerasrecord.setUsedflag(1);
				camerasrecord.setX(Float.valueOf(cameraDeviceBean.getLocationX()));
				camerasrecord.setY(Float.valueOf(cameraDeviceBean.getLocationY()));
				camerasrecord.setState(1);
				camerasrecord.setPort(33337);
				camerasrecord.setChanel(0);
				camerasrecord.setAddress(cameraDeviceBean.getDescription());
				camerasrecord.setRemark(cameraDeviceBean.getId());
				re = camerasMapper.insertSelective(camerasrecord);
				if (re == 0) {
					return -1;
				}
			} else {
				// 更新相机信息
				CamerasExample camerasExample = new CamerasExample();
				CamerasExample.Criteria camerasCriteria = camerasExample.createCriteria();
				camerasCriteria.andRemarkEqualTo(cameraDeviceBean.getId());

				camerasrecord.setName(cameraDeviceBean.getName());
				camerasrecord.setCode(cameraDeviceBean.getFaceCode());
				camerasrecord.setX(Float.valueOf(cameraDeviceBean.getLocationX()));
				camerasrecord.setY(Float.valueOf(cameraDeviceBean.getLocationY()));
				camerasrecord.setCode(cameraDeviceBean.getFaceCode());
				camerasrecord.setAddress(cameraDeviceBean.getDescription());
				re = camerasMapper.updateByExampleSelective(camerasrecord, camerasExample);
				if (re == 0) {
					return -1;
				}
			}
		}
		return 0;
	}

}
