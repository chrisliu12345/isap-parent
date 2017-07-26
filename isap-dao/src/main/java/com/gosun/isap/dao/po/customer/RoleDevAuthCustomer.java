package com.gosun.isap.dao.po.customer;

import java.util.List;

import com.gosun.isap.dao.po.TDevAuthdef;
import com.gosun.isap.dao.po.customer.DeviceAndAuth;

/**
 * 加个设备id 来区分是哪个设备的权限 封装了设备权限跟设备的关系
 * @author lyf
 *
 */
public class RoleDevAuthCustomer extends TDevAuthdef {

	private int devId;

	public int getDevId() {
		return devId;
	}

	public void setDevId(int devId) {
		this.devId = devId;
	}

}
