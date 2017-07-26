package com.gosun.isap.face.api.service;

import java.util.List;

import com.gosun.isap.dao.po.face.TFaceServer;

public interface ContactsMapperService {

	int updateAlarmValue(List<TFaceServer> faceServerList,String alarmValue, Long userId);
}
