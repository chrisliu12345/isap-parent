package com.gosun.isap.face.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.face.ContactsMapper;
import com.gosun.isap.dao.po.face.Contacts;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.service.ContactsMapperService;

@Service
public class ContactsMapperServiceImpl implements ContactsMapperService {
	@Autowired
	private ContactsMapper contactsMapper;

	/**
	 * 告警阈值设定
	 */
	@Override
	public int updateAlarmValue(List<TFaceServer> faceServerList, String alarmValue, Long userId) {

		Contacts record = new Contacts();
		record.setId(1);
		record.setAlarmvalue(Double.valueOf(alarmValue));
		contactsMapper.updateByPrimaryKeySelective(record);
		return 0;
	}
}
