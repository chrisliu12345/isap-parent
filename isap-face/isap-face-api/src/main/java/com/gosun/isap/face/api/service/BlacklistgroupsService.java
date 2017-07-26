package com.gosun.isap.face.api.service;

import java.util.List;

import com.gosun.isap.dao.po.face.Blacklistgroups;
import com.gosun.isap.dao.po.face.TFaceServer;

public interface BlacklistgroupsService {

	int saveListGroup(List<TFaceServer> faceServerList,Blacklistgroups blacklistgroups);
	
	int deleteListGroup(List<TFaceServer> faceServerList,Integer listGroupID);
	
	int updateListGroup(List<TFaceServer> faceServerList,Blacklistgroups blacklistgroups);
}
