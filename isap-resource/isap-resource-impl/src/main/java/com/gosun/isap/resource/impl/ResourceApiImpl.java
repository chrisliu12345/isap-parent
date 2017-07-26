package com.gosun.isap.resource.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.dao.mapper.customer.TResourceMapper;
import com.gosun.isap.dao.po.customer.TResource;
import com.gosun.isap.dao.po.customer.TResourceExample;
import com.gosun.isap.resource.api.IResourceApi;

@SuppressWarnings("rawtypes")
public class ResourceApiImpl implements IResourceApi {
	private static Logger logger = LoggerFactory.getLogger(ResourceApiImpl.class);

	@Autowired
	TResourceMapper resourceMapper;

	@Override
	public ResponseResult getResourceList(String departmentId, Integer start, Integer limit, String sort,
			String fuzzyField, String fuzzyValue) {
		ResponseResult response = ResponseResult.ok();

		if (null == departmentId) {
			response.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return response;
		}

		logger.info("departmentId:" + departmentId);
		logger.info("page:" + start + ", " + limit);
		logger.info("fuzzy:" + fuzzyField + ", " + fuzzyValue);

		TResourceExample example = new TResourceExample();
		TResourceExample.Criteria c = example.createCriteria();
		// 如果部门ID传*，表示通配，则不按照部门id过滤
		if (!"*".equals(departmentId)) {
			c.andDepartmentIdEqualTo(departmentId);
		}
		example.setLimit(limit);
		example.setOffset(start);

		if (!StringUtils.isEmpty(sort)) {
			String sqlOrder = OrderSqlUtil.getOrderSqlStringBySort(sort);
			example.setOrderByClause(sqlOrder);
		}
		if (!StringUtils.isEmpty(fuzzyField) && !StringUtils.isEmpty(fuzzyValue)) {
			c.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}

		int total = resourceMapper.countByExample(example);
		if (total > 0) {
			response.setTotal(total);

			List<TResource> resList = resourceMapper.selectByExample(example);
			response.setBody(resList);
		}

		return response;
	}

}
