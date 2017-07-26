package com.gosun.isap.dao.mapper.face.customer;

import java.util.List;

import com.gosun.isap.dao.po.face.Hosts;

public interface HostsMapperCustomer {
	List<Hosts> selectRoleByListGroupID(Integer listGroupID);
	
	Hosts selectbyRemarkToID(String remark);
}
