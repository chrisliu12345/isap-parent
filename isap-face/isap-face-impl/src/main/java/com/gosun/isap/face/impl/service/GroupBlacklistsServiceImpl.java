package com.gosun.isap.face.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.face.BlacklistgroupsMapper;
import com.gosun.isap.dao.mapper.face.BlacklistsMapper;
import com.gosun.isap.dao.mapper.face.GroupblacklistsMapper;
import com.gosun.isap.dao.po.face.Blacklistgroups;
import com.gosun.isap.dao.po.face.Blacklists;
import com.gosun.isap.dao.po.face.Groupblacklists;
import com.gosun.isap.dao.po.face.GroupblacklistsKey;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.service.GroupBlacklistsService;

@Service
public class GroupBlacklistsServiceImpl implements GroupBlacklistsService {
	@Autowired
	private GroupblacklistsMapper groupblacklistsMapper;
	@Autowired
	private BlacklistsMapper blacklistsMapper;
	@Autowired
	private BlacklistgroupsMapper blacklistgroupsMapper;

	/**
	 * 增加名单与名单组的关联
	 */
	@Override
	public int saveGroupList(List<Integer> listGroupIDList, List<Integer> listIDList,
			List<TFaceServer> faceServerList) {
		int listGroupIDListSize = listGroupIDList.size();
		int listIDListSize = listIDList.size();

		for (int listGroupIDListIndex = 0; listGroupIDListIndex < listGroupIDListSize; listGroupIDListIndex++) {
			for (int listIDListIndex = 0; listIDListIndex < listIDListSize; listIDListIndex++) {

				Integer listID = listIDList.get(listIDListIndex);
				Integer listGroupID = listGroupIDList.get(listGroupIDListIndex);

				GroupblacklistsKey key = new GroupblacklistsKey(); // 查询主键
				Groupblacklists groupblacklists = new Groupblacklists();
				// 查询设置
				Blacklists blacklists = blacklistsMapper.selectByPrimaryKey(listID);
				Blacklistgroups blacklistgroups = blacklistgroupsMapper.selectByPrimaryKey(listGroupID);
				if (blacklists == null || blacklistgroups == null) {
					return -1;
				}

				key.setBlacklistgroupid(listGroupID);
				key.setBlacklistid(listID);
				// 检索数据是否存在
				groupblacklists = groupblacklistsMapper.selectByPrimaryKey(key);
				if (groupblacklists == null) {
					// 数据不存在
					groupblacklists = new Groupblacklists();
					groupblacklists.setBlacklistgroupid(listGroupID);
					groupblacklists.setBlacklistid(listID);
					groupblacklists.setState(1);
					groupblacklists.setUsedflag(1);
					// 插入数据
					int re = groupblacklistsMapper.insertSelective(groupblacklists);
					if (re == 0) {
						throw new RuntimeException();
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 删除名单与名单组的关联
	 */
	@Override
	public int delectGroupList(List<Integer> listGroupIDList, List<Integer> listIDList,
			List<TFaceServer> faceServerList) {
		int listGroupIDListSize = listGroupIDList.size();
		int listIDListSize = listIDList.size();

		for (int listGroupIDListIndex = 0; listGroupIDListIndex < listGroupIDListSize; listGroupIDListIndex++) {
			for (int listIDListIndex = 0; listIDListIndex < listIDListSize; listIDListIndex++) {

				Integer listGroupID = listGroupIDList.get(listGroupIDListIndex);
				Integer listID = listIDList.get(listIDListIndex);
				GroupblacklistsKey key = new GroupblacklistsKey(); // 查询主键

				key.setBlacklistgroupid(listGroupID);
				key.setBlacklistid(listID);
				// 删除数据
				groupblacklistsMapper.deleteByPrimaryKey(key);
			}
		}
		return 0;
	}
}
