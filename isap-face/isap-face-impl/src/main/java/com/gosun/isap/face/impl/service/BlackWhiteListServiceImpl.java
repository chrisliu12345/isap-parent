package com.gosun.isap.face.impl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.face.BlacklistsMapper;
import com.gosun.isap.dao.mapper.face.GroupblacklistsMapper;
import com.gosun.isap.dao.mapper.face.WhitelistsMapper;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Blacklists;
import com.gosun.isap.dao.po.face.BlacklistsExample;
import com.gosun.isap.dao.po.face.Groupblacklists;
import com.gosun.isap.dao.po.face.GroupblacklistsExample;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.Whitelists;
import com.gosun.isap.dao.po.face.WhitelistsExample;
import com.gosun.isap.face.api.service.BlackWhiteListService;

@Service
public class BlackWhiteListServiceImpl implements BlackWhiteListService {

	@Autowired
	private GroupblacklistsMapper groupblacklistsMapper;
	@Autowired
	private BlacklistsMapper blacklistsMapper;
	@Autowired
	private WhitelistsMapper whitelistsMapper;

	/**
	 * 名单删除
	 */
	@Override
	public int deleteBlackWhiteList(List<TFaceServer> faceServerList, Integer listType, Integer listID) {
		if (listType == 1) {
			// 检查数据是否在数据库中
			java.util.List<Groupblacklists> GroupblacklistsList = new ArrayList<Groupblacklists>();
			GroupblacklistsExample groupblacklistsExample = new GroupblacklistsExample();
			GroupblacklistsExample.Criteria criteria = groupblacklistsExample.createCriteria();
			criteria.andBlacklistidEqualTo(listID);
			GroupblacklistsList = groupblacklistsMapper.selectByExample(groupblacklistsExample);
			if (GroupblacklistsList != null && !GroupblacklistsList.isEmpty()) {
				// 名单存在名单组中
				return 1;
			}
			// 检索数据是否存在
			Blacklists blacklists = new Blacklists();
			blacklists = blacklistsMapper.selectByPrimaryKey(listID);
			if (blacklists == null) {
				// 数据不存在
				return 2;
			} else {
				// 数据存在,删除数据
				blacklistsMapper.deleteByPrimaryKey(listID);
			}
		} else {
			Whitelists whitelists = new Whitelists();
			// 检索数据是否存在
			whitelists = whitelistsMapper.selectByPrimaryKey(listID);
			if (whitelists == null) {
				// 数据不存在
				return 2;
			} else {
				// 数据存在,删除数据
				whitelistsMapper.deleteByPrimaryKey(listID);
			}
		}

		return 0;
	}

	/**
	 * 更新黑名单
	 */
	@Override
	public int updateBlackList(List<TFaceServer> faceServerList, Blacklists blacklists) {
		// 检索数据是否存在
		Blacklists existenceBlacklists = blacklistsMapper.selectByPrimaryKey(blacklists.getId());

		if (existenceBlacklists == null) {
			// 数据不存在
			return 1;
		} else {
			// 更新的身份证与数据库已有的冲突
			BlacklistsExample blacklistsExample = new BlacklistsExample();
			BlacklistsExample.Criteria blacklistsCriteria = blacklistsExample.createCriteria();
			blacklistsCriteria.andIdcardEqualTo(blacklists.getIdcard());
			blacklistsCriteria.andIdNotEqualTo(blacklists.getId());
			List<Blacklists> whitelistsList = blacklistsMapper.selectByExample(blacklistsExample);
			if (whitelistsList != null && !whitelistsList.isEmpty()) {
				return 2;
			}
			// 将人员信息更新到数据库中
			blacklistsMapper.updateByPrimaryKeySelective(blacklists);
		}
		return 0;
	}

	/**
	 * 更新白名单
	 */
	@Override
	public int updateWhiteList(List<TFaceServer> faceServerList, Whitelists whitelists) {

		// 检索数据是否存在
		Whitelists existenceWhitelists = whitelistsMapper.selectByPrimaryKey(whitelists.getId());

		if (existenceWhitelists == null) {
			// 数据不存在
			return 1;
		} else {
			// 更新的身份证与数据库已有的冲突
			WhitelistsExample whitelistsExample = new WhitelistsExample();
			WhitelistsExample.Criteria whitelistsCriteria = whitelistsExample.createCriteria();
			whitelistsCriteria.andIdcardEqualTo(whitelists.getIdcard());
			whitelistsCriteria.andIdNotEqualTo(whitelists.getId());
			List<Whitelists> whitelistsList = whitelistsMapper.selectByExample(whitelistsExample);
			if (whitelistsList != null && !whitelistsList.isEmpty()) {
				return 2;
			}
			// 将人员信息更新到数据库中
			whitelistsMapper.updateByPrimaryKeySelective(whitelists);
		}
		return 0;
	}

	/**
	 * 保存黑名单
	 */
	@Override
	public int saveBlackList(String fileNamePath, Blacklists blacklists, TUser user) {

		// 设置信息
		blacklists.setImgurl(fileNamePath);
		// 查询数据库中是否存在数据
		BlacklistsExample blacklistsExample = new BlacklistsExample();
		BlacklistsExample.Criteria blacklistsCriteria = blacklistsExample.createCriteria();
		blacklistsCriteria.andIdcardEqualTo(blacklists.getIdcard());
		blacklistsCriteria.andCreatorNotEqualTo(blacklists.getCreator());
		List<Blacklists> whitelistsList = blacklistsMapper.selectByExample(blacklistsExample);
		if (whitelistsList != null && !whitelistsList.isEmpty()) {
			return 1;
		}
		// 将人员信息增加到数据库中
		blacklistsMapper.insertSelective(blacklists);
		return 0;
	}

	/**
	 * 保存白名单
	 */
	@Override
	public int saveWhiteList(String fileNamePath, Whitelists whitelists, TUser user) {
		// 设置信息
		whitelists.setImgurl(fileNamePath);
		// 查询数据库中是否存在数据
		WhitelistsExample whitelistsExample = new WhitelistsExample();
		WhitelistsExample.Criteria whitelistsCriteria = whitelistsExample.createCriteria();
		whitelistsCriteria.andIdcardEqualTo(whitelists.getIdcard());
		whitelistsCriteria.andCreatorNotEqualTo(whitelists.getCreator());
		List<Whitelists> whitelistsList = whitelistsMapper.selectByExample(whitelistsExample);
		if (whitelistsList != null && !whitelistsList.isEmpty()) {
			return 1;
		}
		// 将人员信息增加到数据库中
		whitelistsMapper.insertSelective(whitelists);
		return 0;
	}
}
