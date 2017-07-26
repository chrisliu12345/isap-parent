package com.gosun.isap.warn.impl.service.guard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gosun.isap.dao.mapper.TGuardSingleTimeMapper;
import com.gosun.isap.dao.mapper.TGuardTimeTemplateMapper;
import com.gosun.isap.dao.mapper.TGuardWeekTimeMapper;
import com.gosun.isap.dao.mapper.customer.TGuardTimeTemplateMapperCustomer;
import com.gosun.isap.dao.po.TGuardSingleTime;
import com.gosun.isap.dao.po.TGuardSingleTimeExample;
import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.dao.po.TGuardTimeTemplateExample;
import com.gosun.isap.dao.po.TGuardWeekTime;
import com.gosun.isap.dao.po.TGuardWeekTimeExample;
import com.gosun.isap.warn.api.service.guard.TGuardTimeTemplateService;

@Service
public class GuardTimeTemplateServiceImpl implements TGuardTimeTemplateService {

	@Autowired
	TGuardTimeTemplateMapper timeTemplateMapper;

	@Autowired
	TGuardSingleTimeMapper singleTimeMapper;

	@Autowired
	TGuardWeekTimeMapper weekTimeMapper;

	@Autowired
	TGuardTimeTemplateMapperCustomer timeTemplateMapperCustomer;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveSingleTemplate(TGuardTimeTemplate template, TGuardSingleTime time) {
		template.setRefCount(0); // 引用初始化为0
		timeTemplateMapper.insertSelective(template);
		time.setTemplateId(template.getId()); // 更新模板id
		return 1 == singleTimeMapper.insertSelective(time) ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean modifySingleTemplate(TGuardTimeTemplate template, TGuardSingleTime time) {
		timeTemplateMapper.updateByPrimaryKeySelective(template);
		TGuardSingleTimeExample example = new TGuardSingleTimeExample();
		TGuardSingleTimeExample.Criteria cri = example.createCriteria();
		cri.andTemplateIdEqualTo(template.getId());
		return 1 == singleTimeMapper.updateByExampleSelective(time, example) ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveWeekTemplate(TGuardTimeTemplate template, TGuardWeekTime time) {
		template.setRefCount(0); // 引用初始化为0
		timeTemplateMapper.insert(template);
		time.setTemplateId(template.getId()); // 更新模板id
		return 1 == weekTimeMapper.insertSelective(time) ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean modifyWeekTemplate(TGuardTimeTemplate template, TGuardWeekTime time) {
		timeTemplateMapper.updateByPrimaryKeySelective(template);
		TGuardWeekTimeExample example = new TGuardWeekTimeExample();
		TGuardWeekTimeExample.Criteria cri = example.createCriteria();
		cri.andTemplateIdEqualTo(template.getId());
		return 1 == weekTimeMapper.updateByExampleSelective(time, example) ? true : false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteTemplateBatch(List<Long> ids) {
		TGuardTimeTemplateExample example = new TGuardTimeTemplateExample();
		TGuardTimeTemplateExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		// 只需要删除主表就可以了，时间表数据库外键关联自动删除
		return ids.size() == timeTemplateMapper.deleteByExample(example) ? true : false;
	}

	@Override
	public TGuardTimeTemplate getTemplate(Long templateId) {
		return timeTemplateMapper.selectByPrimaryKey(templateId);
	}

	@Override
	public TGuardSingleTime getSingleTime(Long templateId) {
		TGuardSingleTimeExample e = new TGuardSingleTimeExample();
		TGuardSingleTimeExample.Criteria c = e.createCriteria();
		c.andTemplateIdEqualTo(templateId);
		return singleTimeMapper.selectByExample(e).get(0);
	}

	@Override
	public TGuardWeekTime getWeekTime(Long templateId) {
		TGuardWeekTimeExample e = new TGuardWeekTimeExample();
		TGuardWeekTimeExample.Criteria c = e.createCriteria();
		c.andTemplateIdEqualTo(templateId);
		return weekTimeMapper.selectByExample(e).get(0);
	}

	@Override
	public boolean isTimeTemplateExist(String templateName) {
		TGuardTimeTemplateExample example = new TGuardTimeTemplateExample();
		TGuardTimeTemplateExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(templateName);
		List<TGuardTimeTemplate> all = timeTemplateMapper.selectByExample(example);
		return all.isEmpty() ? false : true;
	}

	@Override
	public List<TGuardTimeTemplate> listTimeTemplateByExample(TGuardTimeTemplateExample example) {
		return timeTemplateMapper.selectByExample(example);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void increaseRefCount(Long templateId) {
		timeTemplateMapperCustomer.increaseRefCount(templateId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void decreaseRefCount(Long templateId) {
		timeTemplateMapperCustomer.decreaseRefCount(templateId);
	}

	@Override
	public Integer getRefCount(Long templateId) {
		return timeTemplateMapperCustomer.getRefCount(templateId);
	}

	@Override
	public int countByExample(TGuardTimeTemplateExample example) {
		return timeTemplateMapper.countByExample(example);
	}
}
