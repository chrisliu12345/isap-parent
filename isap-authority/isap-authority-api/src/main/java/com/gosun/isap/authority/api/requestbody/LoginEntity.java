package com.gosun.isap.authority.api.requestbody;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 接收前台登陆请求参数
 * 
 * @author lyf
 *
 */
public class LoginEntity {
	private String loginName;
	private String password;
	private String ip;
	private String loginType;// 访问类型

	@NotEmpty(message = "登陆名不能为空")
	@Length(min = 1, max = 50)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@NotEmpty(message = "密码不能为空")
	@Length(min = 1, max = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@NotEmpty(message = "登陆类型不能为空")
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}
