package com.gosun.isap.resource.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.resource.api.IRoleApi;
import com.gosun.isap.resource.api.service.TDepartmentService;
import com.gosun.isap.resource.api.service.TRoleService;
import com.gosun.isap.resource.api.wrapper.AddRoleReceivePara;
import com.gosun.isap.resource.api.wrapper.ContentCommand;
import com.gosun.isap.resource.api.wrapper.DeviceAndAuth;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import io.netty.util.Constant;

import com.gosun.isap.common.BeanValidate;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.mapper.TRoleDepMapper;
import com.gosun.isap.dao.mapper.TRoleDevAuthMapper;
import com.gosun.isap.dao.mapper.TRoleMapper;
import com.gosun.isap.dao.mapper.TRoleMenuAuthMapper;
import com.gosun.isap.dao.mapper.customer.TDepartmentMapperCustomer;
import com.gosun.isap.dao.mapper.customer.TResourceMapper;
import com.gosun.isap.dao.mapper.customer.TRoleMapperCustomer;
import com.gosun.isap.dao.po.*;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.dao.po.customer.TResource;
import com.gosun.isap.dao.po.customer.TResourceExample;

@SuppressWarnings("rawtypes")
@Path("role")
public class RoleApiImpl implements IRoleApi {

	@Autowired
	private TRoleService roleService;
	
	@Autowired
	private TResourceMapper resourceMapper;
	  
	@Autowired
	private TDeviceMapper deviceMapper;

	@Autowired
	private TRoleDevAuthMapper roleDevAuthMapper;

	@Autowired
	private TRoleMenuAuthMapper roleMenuAuthMapper;

	@Autowired
	private TRoleMapperCustomer roleMapperCustomer;

	@Autowired
	private TRoleDepMapper roleDepMapper;

	@Autowired
	private TRoleMapper roleMapper;
	
	@Autowired
	private TDepartmentMapperCustomer departmentMapperCustomer;
	
	@Autowired
	private TDepartmentService departmentService;

	/*
	 * role 列表查询
	 * 
	 */
	@GET
	@Path("roles/collection")
	@Produces({ "application/json" })
	public ResponseResult<List<TRole>> queryRole(@QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort) {
		/*
		 * ResponseResult<List<TRole>> responseResult = ResponseResult.ok();
		 * TRoleExample example=new TRoleExample(); List<TRole> roleList =
		 * roleService.queryRole(example); responseResult.setBody(roleList);
		 */

		ResponseResult responseResult = ResponseResult.ok();
		if (start < 0 || limit <= 0) {
			responseResult.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			// OperateLogWriter.fail(ServiceType.ROLE, OperateType.ROLE,
			// "role操作",ContentCommand.MSG_FINDROLE_ERROR);

		} else {
			// PageHelper.startPage(start, pageSize);
			TRoleExample roleExample = new TRoleExample();
			responseResult.setBody(roleService.queryRole(
					StringUtils.isNotEmpty(sort) ? OrderSqlUtil.getOrderSqlStringBySort(sort) : "", start, limit));
			responseResult.setTotal(roleMapper.countByExample(roleExample));
			// OperateLogWriter.success(ServiceType.ROLE, OperateType.ROLE,
			// "role获取列表成功");
		}
		/*
		 * if (responseResult.getHead().getErrorCode()==ErrorCode.ERR_OK){
		 * OperateLogWriter.success(ServiceType.ROLE, OperateType.ROLE,
		 * "role获取列表成功"); } else { OperateLogWriter.fail(ServiceType.ROLE,
		 * OperateType.ROLE,
		 * "role获取列表失败",responseResult.getHead().getMessage()); }
		 */

		return responseResult;
	}

	/*
	 * role 名称查询
	 * 
	 */
	@GET
	@Path("roles")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_ROLE,operateType=OperateType.CONFIG_QUERY ,description="角色名称查询")
	public ResponseResult<List<TRole>> getRolePage(@QueryParam("fuzzyField") String fuzzyField,
			@QueryParam("fuzzyValue") String fuzzyValue, @QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort) {

		ResponseResult<List<TRole>> responseResult = ResponseResult.ok();
		if (fuzzyValue == null || fuzzyValue == "") {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
		} else {

			// List<TRole> roleList = roleService.getRolePage(name);
			// responseResult.setBody(roleList);
			/*
			 * TRoleExample roleExample = new TRoleExample();
			 * TRoleExample.Criteria criteria = roleExample.createCriteria();
			 * roleExample.setOffset(start); roleExample.setLimit(limit); if
			 * (!StringUtils.isBlank(name) && !StringUtils.isBlank(fuzzyValue))
			 * { criteria.andGeneralLike(name, "%" + fuzzyValue + "%"); } if
			 * (StringUtils.isNotEmpty(sort)) {
			 * roleExample.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort
			 * (sort)); }
			 */
			List<TRole> roles = roleService.getRolePage(fuzzyField, fuzzyValue,
					StringUtils.isNotEmpty(sort) ? OrderSqlUtil.getOrderSqlStringBySort(sort) : "", start, limit);
			responseResult.setBody(roles);

			// responseResult.setBody(roleService.getRolePage(name,StringUtils.isNotEmpty(sort)
			// ? OrderSqlUtil.getOrderSqlStringBySort(sort) : "", start,
			// limit));
		}

		/*
		 * if (responseResult.getHead().getErrorCode()==ErrorCode.ERR_OK){
		 * OperateLogWriter.success(ServiceType.ROLE, OperateType.ROLE,
		 * "role根据名称查询成功"); } else { OperateLogWriter.fail(ServiceType.ROLE,
		 * OperateType.ROLE,
		 * "role根据名称查询失败",responseResult.getHead().getMessage()); }
		 */
		return responseResult;
	}

	/*
	 * role 删除操作
	 * 
	 */
	@DELETE
	@Path("roles")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_ROLE,operateType=OperateType.CONFIG_DEL,description="角色删除")
	public ResponseResult deleteRole(IdwrapperBat<Integer> ids) {

		ResponseResult responseResult = ResponseResult.ok();
		if (ids == null || ids.getId().size() < 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			//OperateLogWriter.fail(ServiceType.CONFIG_ROLE, OperateType.CONFIG_DEL, "role操作", ContentCommand.MSG_DELETE_ERROR);

		} else {
			List<Long> intIds = new ArrayList<>();
			for (Integer idwapper : ids.getId()) {
				intIds.add((long) idwapper);
			}
			roleService.deleteRole(intIds);
			//OperateLogWriter.success(ServiceType.CONFIG_ROLE, OperateType.CONFIG_DEL, "role删除成功");
		}
		/*
		 * if (responseResult.getHead().getErrorCode()==ErrorCode.ERR_OK){
		 * OperateLogWriter.success(ServiceType.ROLE, OperateType.ROLE,
		 * "role删除成功"); } else { OperateLogWriter.fail(ServiceType.ROLE,
		 * OperateType.ROLE, "role删除失败",responseResult.getHead().getMessage());
		 * }
		 */

		return responseResult;
	}

	/*
	 * role 修改操作
	 * 
	 */
	@PUT
	@Path("roles/updateMenuAuth")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_ROLE,operateType=OperateType.CONFIG_MOD,description="角色及菜单权限修改")
	public ResponseResult updateRoleMenuAuth(AddRoleReceivePara receivePara) {
		ResponseResult responseResult = ResponseResult.ok();
		

			/*
			 * TRoleDepExample roleDepExample=new TRoleDepExample();
			 * TRoleDepExample.Criteria
			 * criteria=roleDepExample.createCriteria();
			 * criteria.andRoleIdEqualTo(receivePara.getRoleId());
			 * roleDepMapper.deleteByExample(roleDepExample);
			 */


			TRoleMenuAuthExample roleMenuAuthExample = new TRoleMenuAuthExample();
			TRoleMenuAuthExample.Criteria criteria3 = roleMenuAuthExample.createCriteria();
			criteria3.andRoleIdEqualTo(receivePara.getRoleId());
			roleMenuAuthMapper.deleteByExample(roleMenuAuthExample);

			TRole role = new TRole();
			
			TRoleMenuAuth roleMenuAuth = new TRoleMenuAuth();
			TRoleDep roleDep = new TRoleDep();

			role.setId(receivePara.getRoleId());
			role.setName(StringUtils.isNotEmpty(receivePara.getRoleName()) ? receivePara.getRoleName() : null);
			roleService.updateRole(role);

			List<Integer> menuAuthIndex = receivePara.getMenuAuthId();
			for (Integer i : menuAuthIndex) {
				Long menuAuth = i.longValue();
				roleMenuAuth.setRoleId(receivePara.getRoleId());
				roleMenuAuth.setAuthId(menuAuth);
				roleMenuAuthMapper.insert(roleMenuAuth);
			}
			
			
		return responseResult;
	}
		
		//OperateLogWriter.success(ServiceType.CONFIG_ROLE, OperateType.CONFIG_MOD, "role修改成功");
		/*
		 * if (responseResult.getHead().getErrorCode()==ErrorCode.ERR_OK){
		 * OperateLogWriter.success(ServiceType.ROLE, OperateType.ROLE,
		 * "role修改成功"); } else { OperateLogWriter.fail(ServiceType.ROLE,
		 * OperateType.ROLE, "role修改失败",responseResult.getHead().getMessage());
		 * }
		 */
	@PUT
	@Path("roles/updateDevAuth")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_ROLE,operateType=OperateType.CONFIG_MOD,description="角色d的设备权限修改")
	public ResponseResult updateRoleDevAuth(AddRoleReceivePara receivePara) {
		ResponseResult responseResult = ResponseResult.ok();
		
		
		TRoleDevAuth roleDevAuth = new TRoleDevAuth();
		
		
		
		List<String> devId=receivePara.getDevId();
    	for(String dev:devId){
    		TRoleDevAuthExample roleDevAuthExample = new TRoleDevAuthExample();
    		TRoleDevAuthExample.Criteria criteria2 = roleDevAuthExample.createCriteria();
    		criteria2.andRoleIdEqualTo(receivePara.getRoleId());
    		criteria2.andDevIdEqualTo(dev);
    		roleDevAuthMapper.deleteByExample(roleDevAuthExample);
    		
		    List<Integer> auths=receivePara.getDevAuthId();
            for(Integer auth:auths){
        	
        		Long devAuth=auth.longValue();	
        		roleDevAuth.setDevId(dev);
        		roleDevAuth.setAuthId(devAuth);
        		roleDevAuth.setRoleId(receivePara.getRoleId());
        		roleDevAuthMapper.insert(roleDevAuth);
        	}
        }
		
		
		return responseResult;
	}
		
	//String msg = BeanValidate.validateModel(receivePara);
			/*if (StringUtils.isNotEmpty(msg)) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
				//OperateLogWriter.fail(ServiceType.CONFIG_ROLE, OperateType.CONFIG_ADD, "role操作", ContentCommand.MSG_ADD_ERROR);
			} else {
	*/
	
	
	/*
	 * role 添加操作
	 * 
	 */
	@POST
	@Path("roles/addMenuAuth")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_ROLE,operateType=OperateType.CONFIG_ADD,description="角色添加菜单权限")
	public ResponseResult addRoleMenuAuth(AddRoleReceivePara receivePara) {
		ResponseResult responseResult = ResponseResult.ok();
		
		TRole role = new TRole();
		
		TRoleMenuAuth roleMenuAuth=new TRoleMenuAuth();
		TRoleDep roleDep=new TRoleDep();
 
		role.setName(StringUtils.isNotEmpty(receivePara.getRoleName())?receivePara.getRoleName():null);
		roleService.addRole(role);
        Long roleId=role.getId();
        
        List<Integer> menuAuthIndex=receivePara.getMenuAuthId();
        for(Integer i:menuAuthIndex){
       	     Long menuAuth=i.longValue();	
       	     roleMenuAuth.setRoleId(roleId);
    		 roleMenuAuth.setAuthId(menuAuth);
    		 roleMenuAuthMapper.insert(roleMenuAuth);        	 
        }  
      return responseResult;
	}
        
	
	@POST
	@Path("roles/addDevAuth")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_ROLE,operateType=OperateType.CONFIG_ADD,description="角色添加设备权限")
	public ResponseResult addRoleDevAuth(AddRoleReceivePara receivePara) {

		ResponseResult responseResult = ResponseResult.ok();
		
		TRoleDevAuth roleDevAuth=new TRoleDevAuth();
		List<Integer> auths=receivePara.getDevAuthId();
        for(Integer auth:auths){
        	List<String> devId=receivePara.getDevId();
        	for(String dev:devId){
        		Long devAuth=auth.longValue();	
        		roleDevAuth.setDevId(dev);
        		roleDevAuth.setAuthId(devAuth);
        		roleDevAuth.setRoleId(receivePara.getRoleId());
        		roleDevAuthMapper.insert(roleDevAuth);
        	}
        }
		return responseResult;
	}

	
      
		

	@GET
	@Path("roles/auth")
	@Produces({ "application/json" }) 
	public ResponseResult findMenuAuth(@QueryParam("id") long id) {
		ResponseResult responseResult = ResponseResult.ok();
		AddRoleReceivePara addRoleReceivePara=new AddRoleReceivePara();
		TRole role=roleMapper.selectByPrimaryKey(id);
	    String roleName;
	    roleName=role.getName();
	    addRoleReceivePara.setRoleName(roleName);
	
	    List<Integer> menu=new ArrayList<Integer>();
        TRoleMenuAuthExample roleMenuAuthExample=new TRoleMenuAuthExample();
	    TRoleMenuAuthExample.Criteria criteria=roleMenuAuthExample.createCriteria();
	    criteria.andRoleIdEqualTo(id);
	    List<TRoleMenuAuth> roleMenuAuths=roleMenuAuthMapper.selectByExample(roleMenuAuthExample);
	    for(TRoleMenuAuth roleMenuAuth:roleMenuAuths){
	        menu.add((roleMenuAuth.getAuthId()).intValue());
	    }
	    addRoleReceivePara.setMenuAuthId(menu);
	    responseResult.setBody(addRoleReceivePara);
		return responseResult;
	}

	 
	@GET
	@Path("roles/devAuth")
	@Produces({ "application/json" })
	public ResponseResult findDevAuth(@QueryParam("id") long id, @QueryParam("devId") String devId) {
		ResponseResult responseResult = ResponseResult.ok();
		AddRoleReceivePara addRoleReceivePara=new AddRoleReceivePara();
		TRoleDevAuthExample roleDevAuthExample=new TRoleDevAuthExample();
		TRoleDevAuthExample.Criteria criteria=roleDevAuthExample.createCriteria();
		criteria.andDevIdEqualTo(devId);
		criteria.andRoleIdEqualTo(id);
		List<TRoleDevAuth> roleDevAuths=roleDevAuthMapper.selectByExample(roleDevAuthExample);
		List<Integer> auths=new ArrayList<Integer>();
		for(TRoleDevAuth roleDevAuth:roleDevAuths){
			auths.add(roleDevAuth.getAuthId().intValue());
		}
		addRoleReceivePara.setDevAuthId(auths);
		responseResult.setBody(addRoleReceivePara);
		return responseResult;
	}

	

	

	
	/*
	 * @GET
	 * 
	 * @Path("roles")
	 * 
	 * @Produces({ "application/json" }) public
	 * ResponseResult<List<TRoleCustomer>> getAllRoleAndAuth(
	 * 
	 * @QueryParam("id") Long id) {
	 * 
	 * //@Context HttpServletRequest request,
	 * ResponseResult<List<TRoleCustomer>> responseResult = ResponseResult.ok();
	 * 
	 * List<TRoleCustomer> roleCustomers =roleMapperCustomer.getRoleCustomer(id)
	 * ; responseResult.setBody(roleCustomers);
	 * 
	 * return responseResult; }
	 */

}
