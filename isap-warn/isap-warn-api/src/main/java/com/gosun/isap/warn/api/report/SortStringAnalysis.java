package com.gosun.isap.warn.api.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 解析转换sort排序字符串类
 * 
 * @author lucf
 *
 */
public class SortStringAnalysis {

	private static final String REGEX = ",";

	private static final String DESC = " DESC";

	private Map<String, String> translateStringMap = null;

	private List<String> subStringList = null;

	public SortStringAnalysis(Map<String, String> map) {
		this.translateStringMap = map;

		subStringList = new ArrayList<String>();
	}

	/**
	 * 
	 * @param sortString
	 *            排序字符串参数
	 * @return 转换后的排序字符串
	 */
	public String translate(String sortString) {
		toSubStringList(sortString);

		return build();
	}

	private void toSubStringList(String sortString) {
		String[] afterSplited = sortString.split(REGEX);

		for (String e : afterSplited) {
			subStringList.add(e);
		}
	}

	private String build() {
		StringBuilder build = new StringBuilder();

		for (String e : subStringList) {
			String valueString = null;
			boolean isDesc = false;

			if (e.charAt(0) == '-') {
				isDesc = true;
				valueString = translateStringMap.get(e.substring(1));
			} else if (e.charAt(0) == '+') {
				valueString = translateStringMap.get(e.substring(1));
			} else {
				valueString = translateStringMap.get(e.substring(0));
			}

			if (valueString != null) {
				if (isDesc) {
					build.append(valueString).append(DESC).append(",");
				} else {
					build.append(valueString).append(",");
				}
			} else {
				continue;
			}
		}

		// 除去最后一个逗号
		build.deleteCharAt(build.length() - 1);

		return build.toString();
	}
}
