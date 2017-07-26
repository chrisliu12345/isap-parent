package com.gosun.isap.resource.api.wrapper;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class DevicesWrapper {
	
	/*
	device_id	int	是	设备id
	plat_code	string	是	平台编码
	device_code	string	是	设备编码
	device_name	string	是	设备名称
	device_type	int	是	设备类型
	department_code	string	是	需要添加的组织
	*/
	
	int deviceId;	//	设备id
	String platCode;	//	平台编码
	String deviceCode;		//	设备编码
	String deviceName;		//  设备名称
	int deviceType	;		//  设备类型
	String departmentCode;	//	需要添加的组织
	
	@NotNull(message = "设备ID不能为空")
	public int getDeviceid() {
		return deviceId;
	}
	
	public int setDeviceid(int device_id) {
		return this.deviceId  = device_id;
	}
	
	@NotNull(message = "平台编码不能为空")
	@Length(min = 1, max = 50)
	public String getPlatcode() {
		return platCode;
	}
	
	public String setPlatcode(String plat_code ) { 
		return this.platCode = plat_code;
	}
	
	
	@NotNull(message = "设备编码不能为空")
	@Length(min = 1, max = 50)
	public String getDevicecode() {
		return deviceCode;
	}
	
	public String setDevicecode(String device_code ) { 
		return this.deviceCode = device_code;
	}
	
	@NotNull(message = "用户名不能为空")
	@Length(min = 1, max = 50)
	public String getDevicename() {
		return deviceName;
	}
	
	public String setDevicename(String device_name ) { 
		return this.deviceName = device_name;
	}
	
	//int device_type	
	
	@NotNull(message = "设备类型不能为空")
	public int getDevicetype() {
		return deviceType;
	}
	
	public int setDevicetype(int device_type ) { 
		return this.deviceType = device_type;
	}
	//String department_code;
	
	@NotNull(message = "需要添加的组织不能为空")
	@Length(min = 1, max = 50)
	public String getDepartmentcode() {
		return departmentCode;
	}
	
	public String setDepartmentcode(String department_code ) { 
		return this.departmentCode = department_code;
	}

}
