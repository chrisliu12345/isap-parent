package com.gosun.isap.face.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Blacklists;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.Whitelists;


public interface BlackWhiteListService {

	int deleteBlackWhiteList(List<TFaceServer> faceServerList,Integer listType,Integer listID);
	
	int updateBlackList(List<TFaceServer> faceServerList,Blacklists blacklists);
	
	int updateWhiteList(List<TFaceServer> faceServerList,Whitelists whitelists);
	
	int saveBlackList(String fileNamePath, Blacklists blacklists, TUser user);
	
	int saveWhiteList(String fileNamePath, Whitelists whitelists, TUser user);
}
