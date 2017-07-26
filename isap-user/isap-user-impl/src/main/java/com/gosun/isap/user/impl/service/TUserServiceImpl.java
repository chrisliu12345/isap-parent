package com.gosun.isap.user.impl.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.dao.mapper.TUserBelongMapper;
import com.gosun.isap.dao.mapper.TUserMapper;
import com.gosun.isap.dao.mapper.customer.TUserMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.TUserBelong;
import com.gosun.isap.dao.po.TUserBelongExample;
import com.gosun.isap.dao.po.TUserExample;
import com.gosun.isap.dao.po.customer.TUserCustomer;
import com.gosun.isap.user.service.TUserService;

@Service
public class TUserServiceImpl implements TUserService {
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TUserMapperCustomer userMapperCustomer;
	@Autowired
	private TUserBelongMapper userBelongMapper;
	private static final Logger log = LoggerFactory.getLogger(TUserServiceImpl.class);

	@Override
	public TUser findByLoginName(String loginName) {
		TUserExample example = new TUserExample();
		// 构造查询条件criteria
		TUserExample.Criteria criteria = example.createCriteria();
		criteria.andLoginNameEqualTo(loginName);
		List<TUser> userList = userMapper.selectByExample(example);
		if (userList.size() > 0) {
			log.debug("查询到一条数据");
			return userList.get(0);
		}
		log.debug("根据用户名{}没有查到数据", loginName);
		return null;
	}

	@Override
	public void saveOrUpdate(TUser user) {
		if (user.getId() != null) {
			userMapper.updateByPrimaryKey(user);
		} else {
			userMapper.insert(user);
		}
	}

	@Override
	public List<TUserCustomer> findList(TUserExample example) {
		return userMapperCustomer.selectByExample(example);
	}

	@Override
	public TUserCustomer get(int id) {
		return userMapperCustomer.get(id);

	}

	@Override
	public void batchDelete(List<Long> intIds) {
		TUserExample example = new TUserExample();
		// 构造查询条件criteria
		TUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(intIds);
		userMapper.deleteByExample(example);
	}

	@Override
	public List<TUser> findList(String content, String orderSqlString, int start, int pageSize) {
		TUserExample example = new TUserExample();
		example.setOffset(start);
		example.setLimit(pageSize);
		// 构造查询条件criteria
		TUserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(orderSqlString)) {
			example.setOrderByClause(orderSqlString);
		}
		example.setLimit(pageSize);
		example.setOffset(start);
		if (StringUtils.isNotEmpty(content)) {
			criteria.andLoginNameLike("%" + content + "%");
		}
		return userMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(TUser user, int roleId) {
		if (user.getId() != null) {
			userMapper.updateByPrimaryKey(user);
		} else {
			userMapper.insert(user);
		}
		// 更新用户角色信息
		TUserBelongExample example = new TUserBelongExample();
		TUserBelongExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(user.getId());
		TUserBelong userBelong = new TUserBelong();
		userBelong.setUserId(user.getId());
		userBelong.setRoleId((long) roleId);
		List<TUserBelong> userBelongs = userBelongMapper.selectByExample(example);
		if (userBelongs.size() <= 0) {
			userBelongMapper.insert(userBelong);
		} else {
			userBelong.setId(userBelongs.get(0).getId());
			userBelongMapper.updateByExample(userBelong, example);
		}
	}

	@Override
	public List<TUserCustomer> findUserCustomerList(String content, String orderSqlString, int start, int pageSize) {
		TUserExample example = new TUserExample();
		example.setOffset(start);
		example.setLimit(pageSize);
		// 构造查询条件criteria
		TUserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(orderSqlString)) {
			example.setOrderByClause(orderSqlString);
		}
		example.setLimit(pageSize);
		example.setOffset(start);
		if (StringUtils.isNotEmpty(content)) {
			criteria.andLoginNameLike("%" + content + "%");
		}
		return userMapperCustomer.selectByExample(example);
	}

	@Override
	public int countByExample(TUserExample example) {
		return userMapper.countByExample(example);
	}

}
