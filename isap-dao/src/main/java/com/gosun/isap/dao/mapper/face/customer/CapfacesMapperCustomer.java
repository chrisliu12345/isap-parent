package com.gosun.isap.dao.mapper.face.customer;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.face.CapFacesInfoBean;
import com.gosun.isap.dao.po.face.Capfaces;

public interface CapfacesMapperCustomer {

	List<Capfaces> selectByCameraID(@Param("deviceID") String deviceID, @Param("lastime") Date lastime,
			@Param("nowtime") Date nowtime);

	List<CapFacesInfoBean> selectByPara(@Param("departmentID") String departmentID, @Param("cameraID") String cameraID,
			@Param("lastime") Date lastime, @Param("nowtime") Date nowtime, @Param("maxReturn") int maxReturn);
	
	List<Capfaces> selectByCameraIDTo10(@Param("deviceID") String deviceID);

}