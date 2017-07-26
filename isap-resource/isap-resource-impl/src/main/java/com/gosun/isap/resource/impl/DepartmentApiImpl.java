package com.gosun.isap.resource.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.common.BeanValidate;
import com.gosun.isap.common.Idwrapper;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.dao.IdGen;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDepartmentExample;
import com.gosun.isap.dao.po.TUserExample;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.dao.po.customer.TUserCustomer;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.resource.api.IDepartmentApi;
import com.gosun.isap.resource.api.service.TDepartmentService;
import com.gosun.isap.resource.api.wrapper.ContentCommand;
import com.gosun.isap.resource.api.wrapper.DepartmentWrapper;

@SuppressWarnings("rawtypes")
@Path("department")
public class DepartmentApiImpl implements IDepartmentApi {
	@Autowired
	private TDepartmentService departmentService;

	@POST
	@Path("departments")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_ADD, description = "部门添加")
	public ResponseResult addDepartment(DepartmentWrapper departmentWrapper) {
		ResponseResult responseResult = ResponseResult.ok();
		String msg = BeanValidate.validateModel(departmentWrapper);
		if (StringUtils.isNotEmpty(msg)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
		} else if (StringUtils.isEmpty(msg)) {
			TDepartment department = new TDepartment();
			String id = IdGen.uuid();
			department.setId(id);
			responseResult.setBody(id);
			department.setName(StringUtils.isNotEmpty(departmentWrapper.getDepartName())
					? departmentWrapper.getDepartName() : null);
			department.setParent(departmentWrapper.getParent());
			department.setCommunityFlag((byte) departmentWrapper.getCommunityFlag());
			// 加入没有传父组织默认加到跟节点下面
			String parentIds = "0";
			if (StringUtils.isNotEmpty(departmentWrapper.getParent())) {
				parentIds = departmentService.get(departmentWrapper.getParent()).getParentIds();
			}
			department.setParentIds(StringUtils.isNotEmpty(departmentWrapper.getParentIds())
					? departmentWrapper.getParentIds() : (parentIds + "," + departmentWrapper.getParent()));

			departmentService.addDepart(department);
			// OperateLogWriter.success(ServiceType.ROLE, OperateType.ROLE,
			// "role获取列表成功");

		}
		return responseResult;
	}

	@DELETE
	@Path("departments")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_DEL, description = "部门删除")
	public ResponseResult deleteDepartment(IdwrapperBat<String> ids) {
		ResponseResult responseResult = ResponseResult.ok();
		if (ids == null || ids.getId().size() < 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
		} else {
			List<String> list = new ArrayList<String>();
			for (String idwapper : ids.getId()) {
				list.add(idwapper);
			}
			departmentService.batchDelete(list);
		}
		return responseResult;
	}

	@GET
	@Path("departments/collection")
	@Produces({ "application/json" })
	public ResponseResult getDepartmentPage(@QueryParam("fuzzyField") String fuzzyField,
			@QueryParam("fuzzyValue") String fuzzyValue, @QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort, @QueryParam("communityFlag") int communityFlag) {
		ResponseResult responseResult = ResponseResult.ok();
		if (start < 0 || limit <= 0) {
			responseResult.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
		} else {
			TDepartmentExample example = new TDepartmentExample();
			TDepartmentExample.Criteria criteria = example.createCriteria();
			example.setLimit(limit);
			example.setOffset(start);
			if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
				criteria.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
			}
			if (StringUtils.isNotEmpty(sort)) {
				example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
			}
			if (communityFlag != 3) {
				criteria.andCommunityFlagEqualTo((byte) communityFlag);
			}
			List<TDepartment> departments = departmentService.findList(example);
			responseResult.setBody(departments);
		}

		return responseResult;
	}

	@PUT
	@Path("departments")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_DEL, description = "部门更新")
	public ResponseResult updateDepartment(DepartmentWrapper departmentWrapper) {
		ResponseResult responseResult = ResponseResult.ok();

		// StringUtils.isNotEmpty(msg)) {
		// responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);

		// } else {
		String msg = BeanValidate.validateModel(departmentWrapper);
		if (StringUtils.isNotEmpty(msg)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
		} else if (StringUtils.isEmpty(msg)) {

			TDepartment department = new TDepartment();
			department.setId(
					StringUtils.isNotEmpty(departmentWrapper.getDepartId()) ? departmentWrapper.getDepartId() : null);
			department.setName(StringUtils.isNotEmpty(departmentWrapper.getDepartName())
					? departmentWrapper.getDepartName() : null);
			department.setCommunityFlag((byte) departmentWrapper.getCommunityFlag());
			/*
			 * department.setParent(StringUtils.isNotEmpty(departmentWrapper.
			 * getParent()) ? departmentWrapper.getParent() :
			 * departmentWrapper.getDepartId());
			 * department.setCommunityFlag((byte)departmentWrapper.
			 * getCommunityFlag( ) );
			 * department.setParentIds(StringUtils.isNotEmpty(departmentWrapper.
			 * getParentIds())?departmentWrapper.getParentIds():
			 * departmentWrapper. getDepartId());
			 */
			// department.setParent(departmentWrapper.getParent());
			// department.setCommunityFlag((byte)departmentWrapper.getCommunityFlag());
			// department.setParentIds(departmentWrapper.getParentIds());
			departmentService.updateDepart(department);
		}
		// }
		return responseResult;
	}

	/*
	 * @GET
	 * 
	 * @Path("departments/collection")
	 * 
	 * @Produces({ "application/json" }) public ResponseResult
	 * getChilds(DepartmentWrapper departmentWrapper) { ResponseResult
	 * responseResult = ResponseResult.ok(); TDepartmentCustomer
	 * departmentCustomer = new TDepartmentCustomer(); List<TDepartmentCustomer>
	 * childs = departmentService.getChilds(departmentCustomer);
	 * responseResult.setBody(childs); return responseResult; }
	 */

	@GET
	@Path("departments/list")
	@Produces({ "application/json" })
	public ResponseResult getChildsFromDepartment(@QueryParam("parent") String parent) {
		ResponseResult responseResult = ResponseResult.ok();
		List<TDepartment> childs = departmentService.getChilds(parent);
		// TDepartmentCustomer tDepartmentCustomer = childs.get(0);
		// String parentIds = tDepartmentCustomer.getParentIds();
		responseResult.setBody(childs);
		return responseResult;
	}

	@GET
	@Path("departments/codes")
	@Produces({ "application/json" })
	public ResponseResult getDepartByName(@QueryParam("name") String name) {
		ResponseResult responseResult = ResponseResult.ok();
		List<TDepartmentCustomer> list = departmentService.findDepartByName(name);
		responseResult.setBody(list);
		return responseResult;
	}

	@GET
	@Path("departments/flagss")
	@Produces({ "application/json" })
	public ResponseResult getDepartByFlag(@QueryParam("communityflag") Integer communityflag) {
		ResponseResult responseResult = ResponseResult.ok();
		List<TDepartmentCustomer> list = departmentService.findDepartByCommunity(communityflag);
		responseResult.setBody(list);
		return responseResult;
	}

	@GET
	@Path("departments/flags")
	@Produces({ "application/json" })
	public ResponseResult getDepartByFlag(@QueryParam("communityflag") byte communityflag,
			@QueryParam("start") int start, @QueryParam("limit") int limit, @QueryParam("sort") String sort) {
		ResponseResult responseResult = ResponseResult.ok();
		if (start < 0 || limit <= 0) {
			responseResult.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);

		} else {
			TDepartmentExample example = new TDepartmentExample();
			TDepartmentExample.Criteria criteria = example.createCriteria();
			example.setLimit(limit);
			example.setOffset(start);
			if (communityflag >= 0) {
				criteria.andCommunityFlagEqualTo(communityflag);
			}
			if (StringUtils.isNotEmpty(sort)) {
				example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
			}
			List<TDepartment> departments = departmentService.findList(example);
			responseResult.setBody(departments);
		}

		return responseResult;
	}

	/**
	 * wait to be tested
	 * 
	 * @param parentId
	 * @return
	 */
	@GET
	@Path("departments/childList")
	@Produces({ "application/json" })
	public ResponseResult getNextLevelListByParent(@QueryParam("parentId") String parentId) {
		ResponseResult response = ResponseResult.ok();
		if (null == parentId) {
			response.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return response;
		}
		TDepartmentExample example = new TDepartmentExample();
		TDepartmentExample.Criteria criteria = example.createCriteria();
		if (!"*".equals(parentId)) {
			criteria.andParentEqualTo(parentId);
		}
		response.setBody(departmentService.findList(example));
		return response;
	}

	/**
	 * 等待测试
	 */
	@DELETE
	@Path("departments/deletion/{id}")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_DEL, description = "部门删除")
	public ResponseResult deleteAllChildByParentId(@PathParam("id") String id) {
		ResponseResult response = ResponseResult.ok();
		if (StringUtils.isEmpty(id)) {
			response.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return response;
		}
		List<TDepartment> departments = departmentService.getChilds(id);
		TDepartment department = departmentService.get(id);
		List<String> ids = new ArrayList<>();
		ids.add(department.getId());
		if (departments.size() > 0) {
			for (TDepartment dc : departments) {
				ids.add(dc.getId());
			}
		}
		departmentService.batchDelete(ids);
		return response;
	}

}
