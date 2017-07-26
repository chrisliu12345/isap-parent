package com.gosun.isap.warn.impl.linkage;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.warn.api.linkage.AlarmLinkageApi;
import com.gosun.isap.warn.api.linkage.LinkageErrorCode;
import com.gosun.isap.warn.api.linkage.LinkageType;
import com.gosun.isap.warn.api.linkage.Sequence;
import com.gosun.isap.warn.api.linkage.livelinkage.LiveLinkageActionItem;
import com.gosun.isap.warn.api.linkage.livelinkage.LiveLinkageInfo;
import com.gosun.isap.warn.api.linkage.livelinkage.PaneAndUser;
import com.gosun.isap.warn.api.linkage.livelinkage.TriggeredLiveLinkageItem;
import com.gosun.isap.dao.po.TActionLinkageExample;
import com.gosun.isap.dao.po.TActionLinkage;
import com.gosun.isap.dao.po.TActionExample;
import com.gosun.isap.dao.po.TAction;
import com.gosun.isap.dao.po.TActionParamExample;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.TActionParam;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.warn.api.report.AlarmInnerServiceApi;
import com.gosun.isap.warn.api.service.alarm.TAlarmLinkageService;

/**
 * 告警联动api实现
 * 
 * @author lucf
 * 
 */
@Path("alarm/linkage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class AlarmLinkageApiImpl implements AlarmLinkageApi {

	private static Logger logger = LoggerFactory.getLogger(AlarmLinkageApiImpl.class);

	@Autowired
	private TAlarmLinkageService alarmLinkageService;

	@Autowired
	private AlarmInnerServiceApi alarmInnerService;

	@Override
	@POST
	@Path("liveLinkages")
	@SysOperateLog(serviceType = ServiceType.CONFIG_LINKAGE, operateType = OperateType.CONFIG_ADD, description = "新增实况联动配置")
	public ResponseResult addLiveLinkage(LiveLinkageInfo info) {

		ResponseResult response = ResponseResult.ok();

		TActionLinkageExample example = new TActionLinkageExample();
		TActionLinkageExample.Criteria criteria = example.createCriteria();
		criteria.andDevIdEqualTo(info.getAlarmDeviceID());
		criteria.andAlarmTypeEqualTo(new Long(info.getAlarmType()));
		criteria.andActionTypeEqualTo(new Byte(LinkageType.LiveLinkage.getValue()));

		List<TActionLinkage> linkageItem = alarmLinkageService.getAlarmLinkage(example);

		TActionExample exampleAction = new TActionExample();
		TActionExample.Criteria criteriaAction = exampleAction.createCriteria();

		List<TAction> actionItems = null;
		if (0 != linkageItem.size()) {
			criteriaAction.andLinkageIdEqualTo(linkageItem.get(0).getId());
			actionItems = alarmLinkageService.getAction(exampleAction);
		}

		/**
		 * step1:下发配置去重复，若下发的配置中配置了相同的联动窗格编号和用户标号，则直接返回错误
		 */
		List<PaneAndUser> paneAndUser = new ArrayList<>();

		// 获取数据库中当前的实况联动配置，若不为空
		if (actionItems != null) {
			for (TAction e : actionItems) {
				TActionParamExample exampleParam = new TActionParamExample();
				TActionParamExample.Criteria criteriaParam = exampleParam.createCriteria();
				criteriaParam.andActionIdEqualTo(e.getId());

				List<TActionParam> actionParamItems = alarmLinkageService.getActionParam(exampleParam);

				String userCode = null;
				Integer paneIndex = null;

				for (TActionParam ep : actionParamItems) {
					if ((Sequence.SECOND == ep.getParamSequence().intValue()) && (ep.getName().equals("userCode"))) {
						userCode = ep.getParamValue();
					} else if ((Sequence.THIRD == ep.getParamSequence().intValue())
							&& (ep.getName().equals("paneIndex"))) {
						paneIndex = Integer.valueOf(ep.getParamValue());
					}
				}

				PaneAndUser obj = new PaneAndUser(paneIndex, userCode);
				if (!paneAndUser.contains(obj)) {
					paneAndUser.add(obj);
				}
			}
		}

		// 去重处理
		for (LiveLinkageInfo.ActionObj e : info.getLinkageObj()) {
			PaneAndUser objTest = new PaneAndUser(e.getPaneIndex(), e.getUserCode());

			if (paneAndUser.contains(objTest)) {
				String message = "Config exist same paneIndex:" + e.getPaneIndex() + " and userCode:" + e.getUserCode();
				logger.error(message);
				response.setError(LinkageErrorCode.LIVELINKAGE_SAME_PANEINDEX_AND_USERCODE_EXIST, message);
				return response;
			} else {
				paneAndUser.add(objTest);
			}
		}

		/*
		 * step2:根据告警设备ID、告警类型和联动类型查询联动业务表t_action_linkage中是否已有记录，如果没记录需要新建一条记录
		 */
		boolean isNewItemCreated = false;

		// 如果联动业务记录为空，需要新建一条记录
		if (0 == linkageItem.size()) {
			TActionLinkage item = new TActionLinkage();
			item.setDevId(info.getAlarmDeviceID());
			item.setAlarmType(new Long(info.getAlarmType()));
			item.setActionType(new Byte(LinkageType.LiveLinkage.getValue()));
			item.setIsEnable(true);

			try {
				if (0 == alarmLinkageService.insertLinkageItem(item)) {
					linkageItem = alarmLinkageService.getAlarmLinkage(example);
					if (1 != linkageItem.size()) {
						logger.error("fail to select a linkage item from table.");
						response.setError(ErrorCode.ERR_DB_OPERATE_FAIL, "fail to select a linkage item from table.");
						return response;
					}
					isNewItemCreated = true;
				} else {
					logger.error("fail to insert a linkage item to table.");
					response.setError(ErrorCode.ERR_DB_OPERATE_FAIL, "fail to insert a linkage item to table.");
					return response;
				}
			} catch (RuntimeException ex) {
				logger.error(ex.getCause().getMessage());
				response.setErrorEx(ErrorCode.ERR_DB_OPERATE_FAIL, new String[] { ex.getCause().getMessage() });
				return response;
			}
		}

		/**
		 * step3:联动动作表t_action和联动动作参数表t_action_param中插入联动执行对象
		 */
		for (LiveLinkageInfo.ActionObj e : info.getLinkageObj()) {
			TAction actionItem = new TAction();
			actionItem.setLinkageId(linkageItem.get(0).getId());
			actionItem.setName(LinkageType.LiveLinkage.getName());
			actionItem.setParamNum(new Byte(LinkageType.LiveLinkage.getParamNum()));

			List<TActionParam> actionParamItems = new ArrayList<TActionParam>();

			TActionParam actionParam1 = new TActionParam();
			actionParam1.setParamSequence(Sequence.FIRST);
			actionParam1.setName("cameraCode");
			actionParam1.setParamValue(e.getCameraCode());

			actionParamItems.add(actionParam1);

			TActionParam actionParam2 = new TActionParam();
			actionParam2.setParamSequence(Sequence.SECOND);
			actionParam2.setName("userCode");
			actionParam2.setParamValue(e.getUserCode());

			actionParamItems.add(actionParam2);

			TActionParam actionParam3 = new TActionParam();
			actionParam3.setParamSequence(Sequence.THIRD);
			actionParam3.setName("paneIndex");
			actionParam3.setParamValue(Integer.toString(e.getPaneIndex()));

			actionParamItems.add(actionParam3);

			try {
				if (0 != alarmLinkageService.insertLinkageActionObj(actionItem, actionParamItems)) {
					logger.error("Fail to insert linkage action object to t_action and t_action_param.");
					response.setError(ErrorCode.ERR_DB_OPERATE_FAIL,
							"Fail to insert linkage action object to t_action and t_action_param.");
					return response;
				}
			} catch (RuntimeException ex) {
				logger.error(ex.getCause().getMessage());
				response.setErrorEx(ErrorCode.ERR_DB_OPERATE_FAIL, new String[] { ex.getCause().getMessage() });
				return response;
			}
		}

		// 如果新增了一条告警联动业务，需要向告警服务管理上报新增的告警联动
		if (isNewItemCreated) {
			alarmInnerService.pushNewLinkage(linkageItem.get(0));
		}

		return response;
	}

	@Override
	@GET
	@Path("liveLinkages")
	public ResponseResult<List<LiveLinkageActionItem>> getLiveLinkage(@QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit, @QueryParam("alarmDeviceID") String alarmDeviceID,
			@QueryParam("alarmType") Integer alarmType) {

		ResponseResult<List<LiveLinkageActionItem>> response = ResponseResult.ok();

		// step1:根据告警设备ID、告警类型和联动类型从t_action_linkage表中获取联动业务
		TActionLinkageExample example = new TActionLinkageExample();
		TActionLinkageExample.Criteria criteria = example.createCriteria();

		if (null != alarmDeviceID) {
			criteria.andDevIdEqualTo(alarmDeviceID);
		} else {
			logger.error("alarm device id is null");
			response.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return response;
		}

		if (null != alarmType) {
			criteria.andAlarmTypeEqualTo(new Long(alarmType));
		} else {
			logger.error("alarm type is null");
			response.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return response;
		}

		criteria.andActionTypeEqualTo(new Byte(LinkageType.LiveLinkage.getValue()));

		List<TActionLinkage> linkageItem = alarmLinkageService.getAlarmLinkage(example);
		if (0 == linkageItem.size()) {
			return response;
		}

		// step2:根据联动业务ID、分页信息查询t_action表中的记录
		TActionExample exampleAction = new TActionExample();
		TActionExample.Criteria criteriaAction = exampleAction.createCriteria();

		criteriaAction.andLinkageIdEqualTo(linkageItem.get(0).getId());

		exampleAction.setOffset(start);
		exampleAction.setLimit(limit);

		List<TAction> actionItems = alarmLinkageService.getAction(exampleAction);
		if (0 == actionItems.size()) {
			response.setTotal(0);
			return response;
		}

		int totalNum = alarmLinkageService.getActionCount(exampleAction);

		List<LiveLinkageActionItem> data = new ArrayList<LiveLinkageActionItem>();

		for (TAction e : actionItems) {
			TActionParamExample exampleParam = new TActionParamExample();
			TActionParamExample.Criteria criteriaParam = exampleParam.createCriteria();
			criteriaParam.andActionIdEqualTo(e.getId());

			List<TActionParam> actionParamItems = alarmLinkageService.getActionParam(exampleParam);

			String cameraCode = null;
			String userCode = null;
			Integer paneIndex = null;

			for (TActionParam ep : actionParamItems) {
				if ((Sequence.FIRST == ep.getParamSequence().intValue()) && (ep.getName().equals("cameraCode"))) {
					cameraCode = ep.getParamValue();
				} else if ((Sequence.SECOND == ep.getParamSequence().intValue()) && (ep.getName().equals("userCode"))) {
					userCode = ep.getParamValue();
				} else if ((Sequence.THIRD == ep.getParamSequence().intValue()) && (ep.getName().equals("paneIndex"))) {
					paneIndex = Integer.valueOf(ep.getParamValue());
				}
			}

			LiveLinkageActionItem element = new LiveLinkageActionItem(e.getId(), cameraCode, userCode, paneIndex);

			data.add(element);
		}

		response.setBody(data);
		response.setTotal(totalNum);

		return response;
	}

	@Override
	@DELETE
	@Path("liveLinkages")
	@SysOperateLog(serviceType = ServiceType.CONFIG_LINKAGE, operateType = OperateType.CONFIG_DEL, description = "删除告警联动配置")
	public ResponseResult deleteLiveLinkage(IdwrapperBat<Long> idSet) {

		ResponseResult response = ResponseResult.ok();

		List<Long> actionItems = new ArrayList<Long>();
		Set<Long> linkageItems = new HashSet<Long>();

		List<Long> actionList = idSet.getId();

		for (Long e : actionList) {
			actionItems.add(e);

			TActionExample example = new TActionExample();
			TActionExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(e);

			List<TAction> item = alarmLinkageService.getAction(example);
			if (item.size() == 1) {
				linkageItems.add(item.get(0).getLinkageId());
			}
		}

		// 从t_action表中删除记录
		alarmLinkageService.deleteActionItems(actionItems);

		for (Long e : linkageItems) {
			TActionExample example = new TActionExample();
			TActionExample.Criteria criteria = example.createCriteria();
			criteria.andLinkageIdEqualTo(e);

			// 如果查询结果为0，则需要在t_action_linkage表中删除对应的记录，同时向告警管理服务上报删除的告警联动配置
			if (0 == alarmLinkageService.getActionCount(example)) {
				alarmLinkageService.deleteLinkageItemByKey(e);
				alarmInnerService.pushDeletedLinkage(e);
			}
		}

		return response;
	}

	@Override
	@GET
	@Path("triggeredLiveLinkages")
	public ResponseResult<List<TriggeredLiveLinkageItem>> getTriggeredLiveLinkage() {

		ResponseResult<List<TriggeredLiveLinkageItem>> response = ResponseResult.ok();

		TUser user = UserUtil.getCurrentUser();

		String userCode = user.getLoginName();

		List<LiveLinkageActionItem> triggeredItems = alarmInnerService.getLiveLinkage(userCode);

		List<TriggeredLiveLinkageItem> data = new ArrayList<TriggeredLiveLinkageItem>();

		for (LiveLinkageActionItem e : triggeredItems) {
			String cameraCode = e.getCameraCode();
			Integer paneIndex = e.getPaneIndex();

			TriggeredLiveLinkageItem element = new TriggeredLiveLinkageItem(cameraCode, paneIndex);
			data.add(element);
		}

		int num = data.size();

		response.setBody(data);
		response.setTotal(num);

		return response;
	}
}
