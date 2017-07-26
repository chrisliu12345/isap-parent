package com.gosun.isap.face.api.service;

import java.util.List;

import com.gosun.isap.dao.po.face.TFaceServer;

public interface HostBlacklistGroupsService {

	int saveDepartmentListGroup(List<String> departmentIDList, List<Integer> listGroupIDList,
			List<TFaceServer> faceServerList);

	int delectDepartmentListGroup(List<String> departmentIDList, List<Integer> listGroupIDList,
			List<TFaceServer> faceServerList);
}
