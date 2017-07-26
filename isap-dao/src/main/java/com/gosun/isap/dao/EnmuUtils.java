package com.gosun.isap.dao;

import java.util.List;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.dao.mapper.TEnumMapper;
import com.gosun.isap.dao.po.TEnum;
import com.gosun.isap.dao.po.TEnumExample;

/**
 * 枚举工具类,获取枚举标签值
 * 
 * @author lyf
 *
 */
public class EnmuUtils {
	private static TEnumMapper enmuDao = SpringContainer.getContext().getBean(TEnumMapper.class);

	/**
	 * @param emnuValueInteface
	 *            常量接口类
	 * @param value
	 *            枚举值
	 * @return 枚举标签值
	 */
	public static String getEnmuLabel(Class<?> emnuValueInteface, int value) {
		EnmuKey enmuKey = (EnmuKey) emnuValueInteface.getAnnotation(EnmuKey.class);
		if (enmuKey != null) {
			String key = enmuKey.value();
			TEnumExample example = new TEnumExample();
			TEnumExample.Criteria criteria = example.createCriteria();
			criteria.andEnumKeyEqualTo(key);
			criteria.andEnumValueEqualTo(value);
			List<TEnum> enmus = enmuDao.selectByExample(example);
			if (enmus != null && enmus.size() > 0) {
				return enmus.get(0).getLabel();
			}
			return null;
		}
		return null;
	}
}
