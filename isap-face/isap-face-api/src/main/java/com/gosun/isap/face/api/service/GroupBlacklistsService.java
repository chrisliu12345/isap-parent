package com.gosun.isap.face.api.service;

import java.util.List;

import com.gosun.isap.dao.po.face.TFaceServer;

public interface GroupBlacklistsService {
	int saveGroupList(List<Integer> listGroupIDList, List<Integer> listIDList, List<TFaceServer> faceServerList);
	
	int delectGroupList(List<Integer> listGroupIDList, List<Integer> listIDList,List<TFaceServer> faceServerList);
}
