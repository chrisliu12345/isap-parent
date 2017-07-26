package com.gosun.isap.authority.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.authority.api.service.TMenuAuthService;
import com.gosun.isap.dao.mapper.TMenuAuthdefMapper;
import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TMenuAuthdefExample;

@Service
public class TMenuAuthServiceImpl implements TMenuAuthService {
	@Autowired
	private TMenuAuthdefMapper menuAuthMapper;

	/**
	 * 找出所有子节点
	 */
	@Override
	public List<TMenuAuthdef> fetchAllByparentId(List<TMenuAuthdef> menuAuthdefs,Long parentId) {
		if (parentId > 0) {
			TMenuAuthdefExample example = new TMenuAuthdefExample();
			TMenuAuthdefExample.Criteria criteria = example.createCriteria();
			criteria.andParentEqualTo(Long.valueOf(parentId));
			List<TMenuAuthdef> menuAuthdefsList = menuAuthMapper.selectByExample(example);
			if (menuAuthdefsList.size() > 0) {
				for (TMenuAuthdef m : menuAuthdefsList) {
					menuAuthdefs.add(m);
					if (!isLeaf(m.getId())) {
						fetchAllByparentId(menuAuthdefs,m.getId());
					}
				}
			}
		} else {
			return menuAuthMapper.selectByExample(null);
		}
		return menuAuthdefs;
	}

	/**
	 * 只是找下一级的子节点
	 */
	@Override
	public List<TMenuAuthdef> fetchChildByparentId(Long parentId) {
		TMenuAuthdefExample example = new TMenuAuthdefExample();
		TMenuAuthdefExample.Criteria criteria = example.createCriteria();
		criteria.andParentEqualTo(Long.valueOf(parentId));
		List<TMenuAuthdef> menuAuthdefsList = menuAuthMapper.selectByExample(example);
		return menuAuthdefsList;
	}

	/**
	 * 是否是叶子节点
	 */
	@Override
	public boolean isLeaf(Long id) {
		List<TMenuAuthdef> menuAuthdefsList = fetchChildByparentId(id);
		if (menuAuthdefsList != null && menuAuthdefsList.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

}
