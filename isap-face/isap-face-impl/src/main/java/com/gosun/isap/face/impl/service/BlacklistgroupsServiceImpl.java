package com.gosun.isap.face.impl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.face.BlacklistgroupsMapper;
import com.gosun.isap.dao.mapper.face.GroupblacklistsMapper;
import com.gosun.isap.dao.po.face.Blacklistgroups;
import com.gosun.isap.dao.po.face.BlacklistgroupsExample;
import com.gosun.isap.dao.po.face.Groupblacklists;
import com.gosun.isap.dao.po.face.GroupblacklistsExample;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.service.BlacklistgroupsService;

@Service
public class BlacklistgroupsServiceImpl implements BlacklistgroupsService {
	@Autowired
	private BlacklistgroupsMapper blacklistgroupsMapper;
	@Autowired
	private GroupblacklistsMapper groupblacklistsMapper;

	/**
	 * 保存名单组
	 */
	@Override
	public int saveListGroup(List<TFaceServer> faceServerList, Blacklistgroups blacklistgroups) {

			List<Blacklistgroups> blacklistgroupsList = new ArrayList<Blacklistgroups>();
			BlacklistgroupsExample example = new BlacklistgroupsExample();
			BlacklistgroupsExample.Criteria criteria = example.createCriteria();

			criteria.andNameEqualTo(blacklistgroups.getName());
			criteria.andRemarkEqualTo(blacklistgroups.getRemark());
			criteria.andCreatorEqualTo(blacklistgroups.getCreator());

			// 检索数据是否存在
			blacklistgroupsList = blacklistgroupsMapper.selectByExample(example);

			if (blacklistgroupsList == null || blacklistgroupsList.isEmpty()) {
				blacklistgroupsMapper.insertSelective(blacklistgroups);
			} else {
				// 数据存在
				return 1;
			}
		return 0;
	}

	/**
	 * 名单组删除
	 */
	@Override
	public int deleteListGroup(List<TFaceServer> faceServerList, Integer listGroupID) {

			List<Groupblacklists> groupblacklistsList = new ArrayList<Groupblacklists>();
			GroupblacklistsExample groupblacklistsExample = new GroupblacklistsExample();
			GroupblacklistsExample.Criteria criteria = groupblacklistsExample.createCriteria();
			criteria.andBlacklistgroupidEqualTo(listGroupID);
			groupblacklistsList = groupblacklistsMapper.selectByExample(groupblacklistsExample);

			if (groupblacklistsList != null && !groupblacklistsList.isEmpty()) {
				// 名单存在名单组中
				return 1;
			}
			Blacklistgroups blacklistgroups = new Blacklistgroups();
			blacklistgroups = blacklistgroupsMapper.selectByPrimaryKey(listGroupID);
			if (blacklistgroups == null) {
				// 数据不存在
				return 2;
			} else {
				// 数据存在,删除数据
				blacklistgroupsMapper.deleteByPrimaryKey(listGroupID);
			}
		return 0;
	}

	/**
	 * 更新名单组
	 */
	@Override
	public int updateListGroup(List<TFaceServer> faceServerList, Blacklistgroups blacklistgroups) {

		List<Blacklistgroups> blacklistgroupsList = new ArrayList<Blacklistgroups>();
		BlacklistgroupsExample example = new BlacklistgroupsExample();
		BlacklistgroupsExample.Criteria criteria = example.createCriteria();

		criteria.andNameEqualTo(blacklistgroups.getName());
		criteria.andRemarkEqualTo(blacklistgroups.getRemark());
		criteria.andStateEqualTo(blacklistgroups.getState());
		criteria.andCreatorEqualTo(blacklistgroups.getCreator());

		// 检索数据是否存在
		blacklistgroupsList = blacklistgroupsMapper.selectByExample(example);
		if (blacklistgroupsList != null && !blacklistgroupsList.isEmpty()) {
			// 更新数据与表中数据重复
			return 1;
		}

		Blacklistgroups existenceBlacklistgroups = new Blacklistgroups();
		existenceBlacklistgroups = blacklistgroupsMapper.selectByPrimaryKey(blacklistgroups.getId());
		if (existenceBlacklistgroups == null) {
			// 数据不存在
			return 2;
		}
		blacklistgroupsMapper.updateByPrimaryKeySelective(blacklistgroups);

		return 0;
	}

}
