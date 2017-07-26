package com.gosun.isap.authority.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TMenuAuthdef;

public interface TMenuAuthService {

	List<TMenuAuthdef> fetchAllByparentId(List<TMenuAuthdef> menuAuthdefs,Long parentId);

	List<TMenuAuthdef> fetchChildByparentId(Long parentId);

	boolean isLeaf(Long id);
}
