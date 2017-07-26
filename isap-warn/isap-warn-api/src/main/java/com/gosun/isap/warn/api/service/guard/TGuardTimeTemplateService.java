package com.gosun.isap.warn.api.service.guard;

import java.util.List;

import com.gosun.isap.dao.po.TGuardSingleTime;
import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.dao.po.TGuardTimeTemplateExample;
import com.gosun.isap.dao.po.TGuardWeekTime;

public interface TGuardTimeTemplateService {
	boolean saveSingleTemplate(TGuardTimeTemplate template, TGuardSingleTime time);

	boolean modifySingleTemplate(TGuardTimeTemplate template, TGuardSingleTime time);

	boolean saveWeekTemplate(TGuardTimeTemplate template, TGuardWeekTime time);

	boolean modifyWeekTemplate(TGuardTimeTemplate template, TGuardWeekTime time);

	boolean deleteTemplateBatch(List<Long> ids);

	boolean isTimeTemplateExist(String templateName);

	TGuardTimeTemplate getTemplate(Long templateId);

	TGuardSingleTime getSingleTime(Long templateId);

	TGuardWeekTime getWeekTime(Long templateId);

	List<TGuardTimeTemplate> listTimeTemplateByExample(TGuardTimeTemplateExample example);

	int countByExample(TGuardTimeTemplateExample example);

	void increaseRefCount(Long templateId);

	void decreaseRefCount(Long templateId);

	Integer getRefCount(Long templateId);
}
