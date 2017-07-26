package com.gosun.isap.face.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.TDepartmentMapper;
import com.gosun.isap.dao.mapper.face.AlarminfoesMapper;
import com.gosun.isap.dao.mapper.face.MisinformationMapper;
import com.gosun.isap.dao.mapper.face.customer.CamerasMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.CapfacesMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.HostsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.MisinformationMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Alarminfoes;
import com.gosun.isap.dao.po.face.AlarminfoesExample;
import com.gosun.isap.dao.po.face.CapFacesInfoBean;
import com.gosun.isap.dao.po.face.Hosts;
import com.gosun.isap.dao.po.face.ListFilterParaBean;
import com.gosun.isap.dao.po.face.Misinformation;
import com.gosun.isap.dao.po.face.MisinformationExample;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.ICapFaceApi;
import com.gosun.isap.face.api.Bean.CapFaceParaBean;
import com.gosun.isap.face.api.Bean.ListFilterInfoBean;
import com.gosun.isap.face.api.Bean.iGFSBean.CapFaceCompareSearchParaBean;
import com.gosun.isap.face.api.Bean.iGFSBean.CapFaceCompareSearchResultBean;
import com.gosun.isap.face.api.Bean.iGFSBean.CapFaceResultBean;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 抓拍人脸相关的操作<br>
 * <p>
 * 名单对比的误报
 * </p>
 * <p>
 * 根据检索条件查询抓拍人脸
 * </p>
 * <p>
 * 根据照片信息得到特征值
 * </p>
 * <p>
 * 创建时间：2017/05/03
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class CapFace implements ICapFaceApi {
	private static Logger logger = LoggerFactory.getLogger(CapFace.class);
	private static String staticFeature = new String();

	@Autowired
	private MisinformationMapper misinformationMapper;
	@Autowired
	private AlarminfoesMapper alarminfoesMapper;
	@Autowired
	private MisinformationMapperCustomer misinformationMapperCustomer;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;
	@Autowired
	private HostsMapperCustomer hostsMapperCustomer;
	@Autowired
	private CamerasMapperCustomer camerasMapperCustomer;
	@Autowired
	private CapfacesMapperCustomer capfacesMapperCustomer;
	@Autowired
	private TDepartmentMapper departmentMapper;

	/**
	 * <p>
	 * 名单对比的误报增加
	 * </p>
	 * 
	 * @param listFilterInfoBean
	 *            误报信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_FILTER,operateType=OperateType.CONFIG_ADD,description="误报增加")
	public ResponseResult listFilter(ListFilterInfoBean listFilterInfoBean) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("listFilter Start");
		Misinformation misinformation = new Misinformation(); // 插入数据
		// 取得人脸服务器IP
		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(listFilterInfoBean.getDepartmentID());
		if (faceServer == null) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		// 用户取得
		TUser user = UserUtil.getCurrentUser();
		// 取得部门信息
		TDepartment department = departmentMapper.selectByPrimaryKey(listFilterInfoBean.getDepartmentID());
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);

		String sFeature = new String();
		if (ConfigConstants.BLACKLIST.equals(listFilterInfoBean.getListType())) {
			sFeature = null;
			Alarminfoes record = new Alarminfoes();
			AlarminfoesExample example = new AlarminfoesExample();
			AlarminfoesExample.Criteria criteria = example.createCriteria();

			criteria.andBlacklistidEqualTo(listFilterInfoBean.getBlacklistID());
			criteria.andCapfaceidEqualTo(listFilterInfoBean.getCapfaceID());
			criteria.andUsedflagEqualTo(ConfigConstants.USEDFLAG_1);

			record.setUsedflag(ConfigConstants.USEDFLAG_0);
			// 更新告警信息
			int re = alarminfoesMapper.updateByExampleSelective(record, example);
			if (re == 0) {
				// 误报增加失败,告警信息不存在!
				response.setErrorEx(ConfigConstants.ADD_MISINFORMATION_ERR_ALARM, null);
				return response;
			}
		} else {
			// TODO 人员筛选
			sFeature = staticFeature;
			MisinformationExample misinformationExample = new MisinformationExample();
			MisinformationExample.Criteria misinformationCriteria = misinformationExample.createCriteria();
			misinformationCriteria.andListfeatureEqualTo(sFeature);
			misinformationCriteria.andCapfaceidEqualTo(listFilterInfoBean.getCapfaceID());

			// 误报取得
			List<Misinformation> misinformationList = new ArrayList<Misinformation>();
			misinformationList = misinformationMapper.selectByExample(misinformationExample);
			if (misinformationList != null && !misinformationList.isEmpty()) {
				// 增加数据重复!
				response.setErrorEx(ConfigConstants.ADD_DATA_EXISTS, null);
				return response;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConstants.TIME_FORMAT.trim());

		//如果实现文件重命名，不重新导入照片，第二次误报会找不到原照片
/*		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		faceServerList.add(faceServer);
		java.util.List<FtpFileNamePathBean> FtpFileNamePathBean = uploadFileApi.renameFile(faceServerList,
				listFilterInfoBean.getListFaceURL(), ConfigConstants.FILTERLIST);*/

		// 插入数据库的信息设置
		try {
			misinformation.setListtype(listFilterInfoBean.getListType());
			misinformation.setListfeature(sFeature);
			misinformation.setListfaceurl(listFilterInfoBean.getListFaceURL());
			misinformation.setFaceurl(listFilterInfoBean.getFaceURL());
			misinformation.setBackgroundurl(listFilterInfoBean.getBackGroundURL());
			misinformation.setSimilarity(listFilterInfoBean.getSimilarity());
			misinformation.setCapfacestime(sdf.parse(listFilterInfoBean.getCapFacesTime()));
			misinformation.setCameraaddress(listFilterInfoBean.getCameraAddress());
			misinformation.setEdittime(new Date());
			misinformation.setEditor(user.getName());
			misinformation.setEditorid(user.getId().intValue());
			misinformation.setCapfaceid(listFilterInfoBean.getCapfaceID());
			misinformation.setBlacklistid(listFilterInfoBean.getBlacklistID());
			misinformation.setDepartmentid(department.getId());
			misinformation.setDepartmentname(department.getName());
		} catch (java.text.ParseException e) {
			logger.error(e.getMessage());
			response.setErrorEx(ConfigConstants.TIME_TYPE_CONVERSION_ERR, null);
			return response;
		}
		// 将误报信息增加到数据库中
		misinformationMapper.insertSelective(misinformation);
		logger.debug("listFilter End");
		return response;
	}

	/**
	 * <p>
	 * 误报恢复
	 * </p>
	 * 
	 * @param listFilterInfoBean
	 *            误报信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_FILTER,operateType=OperateType.CONFIG_DEL,description="误报恢复")
	public ResponseResult listFilterRecovery(ListFilterInfoBean listFilterInfoBean) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("listFilterRecovery Start");
		// 取得人脸服务器IP
		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(listFilterInfoBean.getDepartmentID());
		if (faceServer == null) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);
		Misinformation misinformation = new Misinformation();
		misinformation = misinformationMapper.selectByPrimaryKey(listFilterInfoBean.getID());

		if (misinformation.getListtype().equals(ConfigConstants.BLACKLIST)) {
			Alarminfoes record = new Alarminfoes();
			AlarminfoesExample example = new AlarminfoesExample();
			AlarminfoesExample.Criteria criteria = example.createCriteria();

			criteria.andBlacklistidEqualTo(misinformation.getBlacklistid());
			criteria.andCapfaceidEqualTo(misinformation.getCapfaceid());
			criteria.andUsedflagEqualTo(ConfigConstants.USEDFLAG_0);
			record.setUsedflag(ConfigConstants.USEDFLAG_1);

			int re = alarminfoesMapper.updateByExampleSelective(record, example);
			if (re == 0) {
				// 告警信息不存在
				response.setErrorEx(ConfigConstants.MISINFORMATION_RECOVER_ERR_ALARM, null);
				return response;
			}
		}
		int re = misinformationMapper.deleteByPrimaryKey(listFilterInfoBean.getID());
		if (re == 0) {
			response.setErrorEx(ConfigConstants.MISINFORMATION_RECOVER_ERR, null);
		}
		logger.debug("listFilterRecovery End");
		return response;
	}

	/**
	 * <p>
	 * 误报查询
	 * </p>
	 * 
	 * @param listFilterParaBean
	 *            误报信息
	 * @return 响应数据
	 */
	public ResponseResult getListFilter(ListFilterParaBean listFilterParaBean) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("getListFilter Start");
		int totalRow = 0;
		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(listFilterParaBean.getDepartmentID());
		if (faceServer == null) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);
		List<Misinformation> misinformationList = new ArrayList<Misinformation>();
		if (listFilterParaBean.getEndTime() == null || listFilterParaBean.getEndTime().isEmpty()) {
			listFilterParaBean.setEndTime(null);
		}
		if (listFilterParaBean.getStartTime() == null || listFilterParaBean.getStartTime().isEmpty()) {
			listFilterParaBean.setStartTime(null);
		}
		if (listFilterParaBean.getCameraID() == null || listFilterParaBean.getCameraID().isEmpty()) {
			listFilterParaBean.setCameraID(null);
		}
		// 用户取得
		TUser user = UserUtil.getCurrentUser();
		String userID = null;
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			userID = user.getId().toString();
		}
		totalRow = misinformationMapperCustomer.countByListFilterParaBean(listFilterParaBean, userID);
		misinformationList = misinformationMapperCustomer.selectByListFilterParaBean(listFilterParaBean, userID);

		response.setTotal(totalRow);
		response.setBody(misinformationList);
		logger.debug("getListFilter End");
		return response;
	}

	/**
	 * <p>
	 * 根据检索条件查询抓拍人脸
	 * </p>
	 * 
	 * @param capFaceParaBean
	 *            抓拍人脸的检索条件信息
	 * @return 响应数据
	 */
	public ResponseResult getCapFace(CapFaceParaBean capFaceParaBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("getCapFace Start");
		CapFaceCompareSearchResultBean capFaceCompareSearchResultBean = new CapFaceCompareSearchResultBean();

		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(capFaceParaBean.getDepartmentID());
		if (faceServer == null) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		try {
			if (capFaceParaBean.getFeature() != null && !capFaceParaBean.getFeature().isEmpty()) {
				// 有图片导入
				try {
					// 相似度与最大返回值认证
					int re = dataAuthentication(capFaceParaBean.getMinSimilarity(), capFaceParaBean.getMaxReturn());
					if (re != 0) {
						// 相似度与最大返回值认证失败
						response.setErrorEx(re, null);
						return response;
					}
					capFaceCompareSearchResultBean = getCapFaceByFeature(faceServer, capFaceParaBean);
					// 特征值保存
					staticFeature = capFaceParaBean.getFeature();
					response.addExtend("departmentID", capFaceParaBean.getDepartmentID());
					response.addExtend("listFaceURL", capFaceParaBean.getFaceURL());
				} catch (ParseException | IOException e) {
					// 发生异常
					logger.error(e.getMessage());
					response.setErrorEx(ConfigConstants.COMPARE_CAPFACE_ERR, null);
					return response;
				}
			} else {
				// 无图片导入
				capFaceCompareSearchResultBean = getCapFaceByPara(faceServer, capFaceParaBean);
			}
		} catch (java.text.ParseException e) {
			logger.error(e.getMessage());
			response.setErrorEx(ConfigConstants.TIME_TYPE_CONVERSION_ERR, null);
			return response;
		}

		// 响应数据的设置
		response.setBody(capFaceCompareSearchResultBean.getCapFaceResult());
		response.setTotal(capFaceCompareSearchResultBean.getTotalCount());

		logger.debug("getCapFace End");
		return response;
	}

	/**
	 * 以图搜图
	 * 
	 * @param faceServer
	 *            人脸服务器
	 * @param capFaceParaBean
	 *            搜索条件
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	private CapFaceCompareSearchResultBean getCapFaceByFeature(TFaceServer faceServer, CapFaceParaBean capFaceParaBean)
			throws ParseException, IOException, java.text.ParseException {
		logger.debug("getCapFaceByFeature Start");
		String xmlget = new String(); // webapi返回信息
		// json传参
		CapFaceCompareSearchParaBean capFaceCompareSearchParaBean = new CapFaceCompareSearchParaBean();
		CapFaceCompareSearchResultBean capFaceCompareSearchResultBean = new CapFaceCompareSearchResultBean();
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConstants.TIME_FORMAT.trim());
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);
		// TODO 人员筛选
		MisinformationExample misinformationExample = new MisinformationExample();
		MisinformationExample.Criteria misinformationCriteria = misinformationExample.createCriteria();
		misinformationCriteria.andListfeatureEqualTo(capFaceParaBean.getFeature());
		// 误报查询时间设置
		String startTime = capFaceParaBean.getStartTime();
		String endTime = capFaceParaBean.getEndTime();
		if ((startTime != null && !startTime.isEmpty()) && (endTime != null && !endTime.isEmpty())) {
			misinformationCriteria.andCapfacestimeBetween(sdf.parse(startTime), sdf.parse(endTime));
		} else if ((startTime == null || startTime.isEmpty()) && (endTime != null && !endTime.isEmpty())) {
			misinformationCriteria.andCapfacestimeLessThanOrEqualTo(sdf.parse(endTime));
		} else if ((startTime != null && !startTime.isEmpty()) && (endTime == null || endTime.isEmpty())) {
			misinformationCriteria.andCapfacestimeGreaterThanOrEqualTo(sdf.parse(startTime));
		}
		// 组织ID设置
		misinformationCriteria.andDepartmentidEqualTo(capFaceParaBean.getDepartmentID());

		// 误报取得
		List<Misinformation> misinformationList = new ArrayList<Misinformation>();
		misinformationList = misinformationMapper.selectByExample(misinformationExample);

		// url的设置
		String url = ConfigConstants.HTTP + faceServer.getIp() + ConfigConstants.COLON + faceServer.getPort().toString()
				+ ConfigConstants.SLASH + ConfigConstants.GETCAPFACE;
		// 人脸查询时间设置
		if (capFaceParaBean.getStartTime() != null && !capFaceParaBean.getStartTime().isEmpty()) {
			capFaceCompareSearchParaBean.setStartTime(stringToJsonDatetime(capFaceParaBean.getStartTime()));
		}
		if (capFaceParaBean.getEndTime() != null && !capFaceParaBean.getEndTime().isEmpty()) {
			capFaceCompareSearchParaBean.setEndTime(stringToJsonDatetime(capFaceParaBean.getEndTime()));
		}
		// 人脸主机ID取得
		Hosts hosts = hostsMapperCustomer.selectbyRemarkToID(capFaceParaBean.getDepartmentID());

		// 相机ID判断
		if (capFaceParaBean.getCameraID() != null && !capFaceParaBean.getCameraID().isEmpty()) {

			Integer cameraID = camerasMapperCustomer.selectbyRemarkToID(capFaceParaBean.getCameraID());
			// 查询条件设置
			capFaceCompareSearchParaBean.setCameraID(cameraID);
		} else {
			capFaceCompareSearchParaBean.setCameraID(0);
		}
		// capFaceCompareSearchParaBean.setContactID(contactsList.get(0).getId());
		capFaceCompareSearchParaBean.setContactID(ConfigConstants.ADMINISTRATORSID.intValue());
		capFaceCompareSearchParaBean.setFeature(capFaceParaBean.getFeature());
		capFaceCompareSearchParaBean.setHostID(hosts.getId());
		capFaceCompareSearchParaBean.setMaxReturn(capFaceParaBean.getMaxReturn() + misinformationList.size());
		capFaceCompareSearchParaBean.setMinSimi(Float.valueOf(capFaceParaBean.getMinSimilarity()));
		capFaceCompareSearchParaBean.setOriganizeID(hosts.getOriganizeid());

		// json序列化
		String jsonStr = JsonHelper.getInstance().createJsonString(capFaceCompareSearchParaBean);
		// 反斜杠去重
		String newJsonStr = jsonStr.replaceAll("\\\\\\\\", "\\\\");

		logger.debug("getCapFace webapi start");
		// 连接URL
		xmlget = IGFSCommunication.post(url, newJsonStr);
		logger.debug("getCapFace webapi end");

		// json反序列化
		capFaceCompareSearchResultBean = JsonHelper.getInstance().getObject(xmlget,
				CapFaceCompareSearchResultBean.class);
		// 将误报信息剔除掉
		for (int i = 0; i < misinformationList.size(); i++) {
			for (int j = 0; j < capFaceCompareSearchResultBean.getTotalCount(); j++) {
				if (misinformationList.get(i).getFaceurl()
						.equals(capFaceCompareSearchResultBean.getCapFaceResult().get(j).getKey().getFaceURL())) {
					capFaceCompareSearchResultBean.getCapFaceResult().remove(j);
					capFaceCompareSearchResultBean.setTotalCount(capFaceCompareSearchResultBean.getTotalCount() - 1);
					break;
				}
			}
		}
		// 修正返回数量
		if (capFaceParaBean.getMaxReturn() < capFaceCompareSearchResultBean.getTotalCount()) {
			int reviseSize = capFaceCompareSearchResultBean.getTotalCount() - capFaceParaBean.getMaxReturn();
			for (int index = 0; index < reviseSize; index++) {
				capFaceCompareSearchResultBean.getCapFaceResult()
						.remove(capFaceCompareSearchResultBean.getCapFaceResult().size() - 1);
			}
			capFaceCompareSearchResultBean.setTotalCount(capFaceCompareSearchResultBean.getTotalCount() - reviseSize);
		}
		// 时间转化
		capFaceCompareSearchResultBean = jsonDatetimeToString(capFaceCompareSearchResultBean);
		logger.debug("getCapFaceByFeature End");
		return capFaceCompareSearchResultBean;
	}

	/**
	 * 得到抓拍信息
	 * 
	 * @param faceServer
	 *            人脸服务器
	 * @param capFaceParaBean
	 *            搜索条件
	 * @return
	 * @throws java.text.ParseException
	 */
	private CapFaceCompareSearchResultBean getCapFaceByPara(TFaceServer faceServer, CapFaceParaBean capFaceParaBean)
			throws java.text.ParseException {
		logger.debug("getCapFaceByPara Start");
		CapFaceCompareSearchResultBean capFaceCompareSearchResultBean = new CapFaceCompareSearchResultBean();
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConstants.TIME_FORMAT.trim());

		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);

		if (capFaceParaBean.getCameraID() == null || capFaceParaBean.getCameraID().isEmpty()) {
			capFaceParaBean.setCameraID(null);
		}
		// 取得抓拍信息
		List<CapFacesInfoBean> capFacesInfoBeanList = new ArrayList<CapFacesInfoBean>();
		capFacesInfoBeanList = capfacesMapperCustomer.selectByPara(capFaceParaBean.getDepartmentID(),
				capFaceParaBean.getCameraID(), sdf.parse(capFaceParaBean.getStartTime()),
				sdf.parse(capFaceParaBean.getEndTime()), capFaceParaBean.getMaxReturn());
		// 将消息整合到消息体中
		List<CapFaceResultBean> capFaceResultList = new ArrayList<CapFaceResultBean>();
		for (int index = 0; index < capFacesInfoBeanList.size(); index++) {
			CapFaceResultBean capFaceResultBean = new CapFaceResultBean();
			capFaceResultBean.setKey(capFacesInfoBeanList.get(index));
			capFaceResultList.add(capFaceResultBean);
		}
		capFaceCompareSearchResultBean.setCapFaceResult(capFaceResultList);
		capFaceCompareSearchResultBean.setTotalCount(capFacesInfoBeanList.size());
		logger.debug("getCapFaceByPara End");
		return capFaceCompareSearchResultBean;
	}

	/**
	 * 数字认证
	 * 
	 * @param minSimilarity
	 *            相似度
	 * @param maxReturn
	 *            最大返回值
	 * @return
	 */
	private Integer dataAuthentication(String minSimilarity, int maxReturn) {
		Pattern patternSIMILARITY = Pattern.compile(ConfigConstants.REGEX_SIMILARITY);
		Matcher matcherSIMILARITY = patternSIMILARITY.matcher(minSimilarity);
		if (!matcherSIMILARITY.matches()) {
			// 相似度不正确
			return ConfigConstants.SIMILARITY_ERR;
		}
		Pattern patternMaxReturn = Pattern.compile(ConfigConstants.REGEX_MAXRETURN);
		Matcher matcherMaxReturn = patternMaxReturn.matcher(String.valueOf(maxReturn));
		if (!matcherMaxReturn.matches()) {
			// 相似度不正确
			return ConfigConstants.MAXRETURN_ERR;
		}
		return 0;
	}

	/**
	 * 将时间转化成C#json序列化格式
	 * 
	 * @param time
	 * @return
	 */
	private String stringToJsonDatetime(String time) {

		logger.debug("stringToJsonDatetime start");
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConstants.TIME_FORMAT.trim());

		String jsonDatetime = new String();
		try {
			jsonDatetime = ConfigConstants.JSONTIMEPREFIX + String.valueOf(sdf.parse(time).getTime())
					+ ConfigConstants.JSONTIMESUFFIX;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("stringToJsonDatetime end");
		return jsonDatetime;
	}

	/**
	 * 将C#json序列化格式转化成时间
	 */
	private CapFaceCompareSearchResultBean jsonDatetimeToString(
			CapFaceCompareSearchResultBean capFaceCompareSearchResultBean) {

		logger.debug("jsonDatetimeToString start");
		int size = capFaceCompareSearchResultBean.getTotalCount();
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConstants.TIME_FORMAT.trim());

		for (int i = 0; i < size; i++) {
			String datetime = capFaceCompareSearchResultBean.getCapFaceResult().get(i).getKey().getCapFacesTime();

			datetime = datetime.substring(ConfigConstants.JSONTIME_START, ConfigConstants.JSONTIME_END);

			datetime = sdf.format(Long.parseLong(datetime));
			capFaceCompareSearchResultBean.getCapFaceResult().get(i).getKey().setCapFacesTime(datetime);
		}
		logger.debug("jsonDatetimeToString start");
		return capFaceCompareSearchResultBean;
	}
}
