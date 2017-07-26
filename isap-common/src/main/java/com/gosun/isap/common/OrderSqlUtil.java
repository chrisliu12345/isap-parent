package com.gosun.isap.common;

/**
 * sql排序工具类
 * 
 * @author liuzk
 *
 */
public class OrderSqlUtil {
	/**
	 * 根据排序string来生成sql排序
	 * 
	 * @param sort
	 *            排序字符串，多个排序字段用逗号隔开。默认升序，'-'表示降序，如id,-name
	 * @return sql排序语句
	 */
	public static String getOrderSqlStringBySort(String sort) {
		if (null == sort || sort.trim().equals("")) {
			return "";
		}

		StringBuffer orderSqlString = new StringBuffer();
		String[] orderStringList = sort.split(",");
		for (String s : orderStringList) {
			char orderTypeChar = s.charAt(0);
			// 降序
			if ("-".toCharArray()[0] == orderTypeChar) {
				orderSqlString.append(s.substring(1));
				orderSqlString.append(" ");
				orderSqlString.append(OrderType.DESC);
			}
			// 升序
			else {
				orderSqlString.append(s);
				orderSqlString.append(" ");
				orderSqlString.append(OrderType.ASC);
			}
			if (!s.equals(orderStringList[orderStringList.length - 1])) {
				orderSqlString.append(",");
			}
		}
		return orderSqlString.toString();
	}
}
