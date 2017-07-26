package com.gosun.isap.authority.api.responsebody;

import java.util.List;

import com.gosun.isap.authority.api.DeviceAuthListVO;

public class LoginBody {
	private static final String DEV_AUTHS_KEY = "devAuthList";
	private static final String USER_ID_KEY = "currentUserId";
	private static final String MENU_AUTHS_KEY = "menuAuthList";
	private static final String USER_NAME_KEY = "userLoginName";
	public static final String USER_IFNO_KEY = "session_current_user";// 当前用户
	public static final String SESSION_ID_NAME = "jsessionId";
	private List<DeviceAuthListVO> devAuthList;
	private long userId;
	private List<String> menuAuthList;
	private String userLoginName;
	private String jsessionId;

	public LoginBody(List<DeviceAuthListVO> devAuthList, long userId, List<String> menuAuthList, String userLoginName,
			String sessionId) {
		super();
		this.devAuthList = devAuthList;
		this.userId = userId;
		this.menuAuthList = menuAuthList;
		this.userLoginName = userLoginName;
		this.jsessionId = sessionId;
	}

	public List<DeviceAuthListVO> getDevAuthList() {
		return devAuthList;
	}

	public void setDevAuthList(List<DeviceAuthListVO> devAuthList) {
		this.devAuthList = devAuthList;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<String> getMenuAuthList() {
		return menuAuthList;
	}

	public void setMenuAuthList(List<String> menuAuthList) {
		this.menuAuthList = menuAuthList;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getJsessionId() {
		return jsessionId;
	}

	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

}
