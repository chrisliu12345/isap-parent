package com.gosun.isap.face.impl;

import com.gosun.isap.dao.DataSourceContextHolder;
/**
 * AOP<br>
 * <p>
 * 创建时间：2017/05/03
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class DataSource {
	/**
	 * 动态数据库恢复
	 */
	public void clearDataSourceType() {
		DataSourceContextHolder.clear();
	}
}
