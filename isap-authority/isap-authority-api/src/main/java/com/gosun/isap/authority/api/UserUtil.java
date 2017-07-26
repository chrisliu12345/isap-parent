package com.gosun.isap.authority.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.alibaba.dubbo.rpc.RpcContext;
import com.gosun.isap.authority.api.responsebody.LoginBody;
import com.gosun.isap.dao.mapper.TMenuAuthdefMapper;
import com.gosun.isap.dao.mapper.TRoleMapper;
import com.gosun.isap.dao.mapper.customer.TRoleDevAuthMapperCustomer;
import com.gosun.isap.dao.mapper.customer.TRoleMenuAuthMapperCustomer;
import com.gosun.isap.dao.mapper.customer.TUserDeviceAuthMapperCustomer;
import com.gosun.isap.dao.mapper.customer.TUserMapperCustomer;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.customer.DeviceDevAuthInfo;
import com.gosun.isap.dao.po.customer.TRoleMenuAuthCustomer;
import com.gosun.isap.dao.po.customer.TUserCustomer;

/**
 * 当前用户的信息，权限信息查找
 * 
 * @author lyf
 *
 */
@Component
public class UserUtil {

	private static TRoleMapper roleMapper;
	private static TMenuAuthdefMapper menuAuthdefMapper;
	private static TUserMapperCustomer userMapper;
	private static TRoleDevAuthMapperCustomer roleDevAuthMapper;
	private static TRoleMenuAuthMapperCustomer roleMenuAuthMapper;
	private static TUserDeviceAuthMapperCustomer userDeviceAuthMapper;

	@Autowired(required = true)
	public void setRoleMapper(TRoleMapper roleMapper) {
		UserUtil.roleMapper = roleMapper;
	}

	@Autowired(required = true)
	public void setMenuAuthdefMapper(TMenuAuthdefMapper menuAuthdefMapper) {
		UserUtil.menuAuthdefMapper = menuAuthdefMapper;
	}

	@Autowired(required = true)
	public void setUserMapper(TUserMapperCustomer userMapper) {
		UserUtil.userMapper = userMapper;
	}

	@Autowired(required = true)
	public void setRoleDevAuthMapper(TRoleDevAuthMapperCustomer roleDevAuthMapper) {
		UserUtil.roleDevAuthMapper = roleDevAuthMapper;
	}

	@Autowired(required = true)
	public void setRoleMenuAuthMapper(TRoleMenuAuthMapperCustomer roleMenuAuthMapper) {
		UserUtil.roleMenuAuthMapper = roleMenuAuthMapper;
	}

	@Autowired(required = true)
	public void setUserDeviceAuthMapper(TUserDeviceAuthMapperCustomer userDeviceAuthMapper) {
		UserUtil.userDeviceAuthMapper = userDeviceAuthMapper;
	}

	/**
	 * user info of session
	 * 
	 * @return
	 */
	public static TUser getCurrentUser() {
		HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
		UserInfo user = (UserInfo) request.getSession().getAttribute(LoginBody.USER_IFNO_KEY);
		return user.getUser();
	};

	/**
	 * user ip of session s
	 * 
	 * @return
	 */
	public static String getCurrentUserIp() {
		HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
		UserInfo user = (UserInfo) request.getSession().getAttribute(LoginBody.USER_IFNO_KEY);
		return user.getIp();
	};

	/**
	 * judge whether current user is admin
	 * 
	 * @return
	 */
	private static boolean isAdmin() {
		if (getCurrentUser().getId() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 分别从用户临时设备权限和角色设备权限表里取出设备权限和设备的一些信息
	 * 
	 * @return
	 */
	private static List<DeviceDevAuthInfo> getDeviceDevAuthInfoList() {
		if (isAdmin()) {
			// 如果是admin用户，返回空集合，因为后台权限判断逻辑是admin用户就不判断
			return new ArrayList<DeviceDevAuthInfo>();
		}
		List<DeviceDevAuthInfo> devAuthInfo = new ArrayList<>();
		TUserCustomer userHasRole = userMapper.get(getCurrentUser().getId());// 取到拥有角色的用户
		// 循环获取设备列表数据
		List<TRole> rolelist = userHasRole.getRoleList();
		if (rolelist != null && rolelist.size() > 0) {
			for (TRole role : rolelist) {
				// 取到该角色下面的角色权限列表
				devAuthInfo.addAll(roleDevAuthMapper.getRoleDevAuthList(role.getId()));
			}
		}
		List<DeviceDevAuthInfo> tempDevAuthInfo = userDeviceAuthMapper.findUserTempDevAuth(getCurrentUser().getId());
		for (DeviceDevAuthInfo deviceDevAuthInfo : tempDevAuthInfo) {
			if (null != deviceDevAuthInfo.getEndDate() && deviceDevAuthInfo.getEndDate().before(new Date())) {
				devAuthInfo.add(deviceDevAuthInfo);
			}
		}
		return devAuthInfo;
	}

	/**
	 * get device list of user
	 * 
	 * @return
	 */
	public static List<TDevice> getDeviceList() {
		List<TDevice> devices = new ArrayList<>();
		List<DeviceDevAuthInfo> devAuthInfo = getDeviceDevAuthInfoList();
		for (DeviceDevAuthInfo deviceDevAuthInfo : devAuthInfo) {
			if (!devices.contains(deviceDevAuthInfo.getDevice())) {
				devices.add(deviceDevAuthInfo.getDevice());
			}
		}
		return devices;
	}

	/**
	 * menu list of user
	 */
	private static List<TRoleMenuAuthCustomer> getMenuList() {
		if (isAdmin()) {
			return new ArrayList<TRoleMenuAuthCustomer>();
		}
		List<TRoleMenuAuthCustomer> menuAuthdefs = new ArrayList<TRoleMenuAuthCustomer>();
		List<TRole> roles = new ArrayList<>();
		TUserCustomer userHasRole = userMapper.get(getCurrentUser().getId());
		roles = userHasRole.getRoleList();
		if (roles != null && roles.size() > 0) {
			for (TRole role : roles) {
				menuAuthdefs.addAll(roleMenuAuthMapper.getRoleMenuAuthList(role.getId()));
			}
		}
		return menuAuthdefs;
	}

	public static List<String> getMenuPermissionList() {
		List<String> pemissionList = new ArrayList<String>();
		List<TRoleMenuAuthCustomer> menuAuthCustomers = getMenuList();
		if (menuAuthCustomers.size() > 0) {
			for (TRoleMenuAuthCustomer t : menuAuthCustomers) {
				pemissionList.add(t.getPermission());
			}
		}
		return pemissionList;
	}

	public static List<DeviceAuthListVO> getDeviceAuthListVOList() {
		List<DeviceAuthListVO> deviceAuthListVOs = new ArrayList<>();
		Map<String, List<String>> devAuthdefs = getUserDeviceAuthMap();
		// 迭代map
		Iterator<Entry<String, List<String>>> it = devAuthdefs.entrySet().iterator();
		DeviceAuthListVO vo = new DeviceAuthListVO();
		if (it.hasNext()) {
			Entry<String, List<String>> entry = it.next();
			vo.setDeviceId(entry.getKey());
			vo.setAuthList(entry.getValue());
			deviceAuthListVOs.add(vo);
		}
		return deviceAuthListVOs;

	}

	/**
	 * user device authority of map data structure
	 * 
	 * @return
	 */
	private static Map<String, List<String>> getUserDeviceAuthMap() {
		List<DeviceDevAuthInfo> devAuthInfos = getDeviceDevAuthInfoList();
		Map<String, List<String>> devAuthdefs = new HashMap<>();
		if (devAuthInfos.size() > 0) {
			for (DeviceDevAuthInfo rd : devAuthInfos) {
				String devId = rd.getDevId();
				// 判断当前map里是否有该设备id对应得权限标识列表
				if (devAuthdefs.get(devId) != null) {
					// 得到该设备id对应得权限标识列表
					List<String> permissions = devAuthdefs.get(devId);
					// 判断是否已存在该标识
					if (!permissions.contains(rd.getPermission())) {
						permissions.add(rd.getPermission());
					}
				} else {
					List<String> permissions = new ArrayList<>();
					permissions.add(rd.getPermission());
					devAuthdefs.put(devId, permissions);
				}
			}
		}
		return devAuthdefs;
	}

	/**
	 * check current user's permission to access a single device resource
	 * 
	 * @param code
	 *            :auth code
	 * @param device
	 * @return
	 */
	public static boolean hasPermission(String code, int devId) {
		// 如果是超级管理员
		if (getCurrentUser().getId() == 1) {
			return true;
		}
		if (getUserDeviceAuthMap().get(devId).contains(code)) {
			return true;
		}
		return false;
	}

	/**
	 * check current user's permission to access a single menu resource
	 * 
	 * @param code
	 *            :auth code
	 * @param device
	 * @return
	 */
	public static boolean hasPermission(String code) {
		if (getCurrentUser().getId() == 1) {
			return true;
		}
		if (getMenuPermissionList().contains(code)) {
			return true;
		}
		return false;

	}
}
