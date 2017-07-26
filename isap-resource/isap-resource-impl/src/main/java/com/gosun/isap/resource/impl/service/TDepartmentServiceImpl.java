package com.gosun.isap.resource.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.dao.mapper.TDepartmentMapper;
import com.gosun.isap.dao.mapper.customer.TDepartmentMapperCustomer;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDepartmentExample;
import com.gosun.isap.dao.po.customer.TDepartmentCustomer;
import com.gosun.isap.resource.api.service.TDepartmentService;

@Service
public class TDepartmentServiceImpl implements TDepartmentService {
	@Autowired
	private TDepartmentMapper departmentMapper;
	@Autowired
	private TDepartmentMapperCustomer departmentMapperCustomer;

	// 1.
	/*
	 * @Override public void saveOrUpdate(TDepartment department) { String id =
	 * IdGen.uuid();
	 * 
	 * if (id==null) { departmentMapper.insert(department);
	 * 
	 * } else { departmentMapper.updateByPrimaryKey(department); }
	 * 
	 * }
	 */

	@Override
	public void batchDelete(List<String> stringIds) {
		TDepartmentExample example = new TDepartmentExample();
		TDepartmentExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andIdIn(stringIds);
		departmentMapper.deleteByExample(example);
		// departmentMapperCustomer.batchDelete(intIds);
	}

	@Override
	public List<TDepartment> findList(TDepartmentExample tDepartmentExample) {

		return departmentMapper.selectByExample(tDepartmentExample);
	}

	@Override
	public TDepartment findDepartmentInfo(Integer id) {

		return departmentMapper.selectByPrimaryKey(String.valueOf(id));
	}

	@Override
	public List<TDepartment> findList(String content, String orderSqlString, int start, int limit) {
		TDepartmentExample example = new TDepartmentExample();
		example.setOffset(start);
		example.setLimit(limit);
		TDepartmentExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(orderSqlString)) {
			example.setOrderByClause(orderSqlString);
		}
		if (StringUtils.isNotEmpty(content)) {
			criteria.andNameLike("%" + content + "%");
		}

		return departmentMapper.selectByExample(example);
	}

	@Override
	public void addDepart(TDepartment department) {
		departmentMapper.insert(department);

	}

	@Override
	public void updateDepart(TDepartment department) {
		departmentMapper.updateByPrimaryKeySelective(department);

	}

	@Override
	public List<TDepartment> getChilds(String parent) {

		return departmentMapperCustomer.findChildsByParent(parent);
	}

	@Override
	public TDepartment get(String id) {

		return departmentMapper.selectByPrimaryKey(id);

	}

	@Override
	public List<TDepartmentCustomer> findDepartByName(String name) {

		return departmentMapperCustomer.selectByName(name);
	}

	@Override
	public List<TDepartmentCustomer> findDepartByCommunity(Integer communityflag) {

		return departmentMapperCustomer.selectByCommunityFlag(communityflag);
	}

	@Override
	public List<TDepartment> findByFlag(byte content, String orderSqlString, int start, int limit) {
		TDepartmentExample example = new TDepartmentExample();
		example.setOffset(start);
		example.setLimit(limit);
		TDepartmentExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(orderSqlString)) {
			example.setOrderByClause(orderSqlString);
		}
		if (content >= 0) {
			criteria.andCommunityFlagEqualTo(content);
		}

		return departmentMapper.selectByExample(example);
	}

}
