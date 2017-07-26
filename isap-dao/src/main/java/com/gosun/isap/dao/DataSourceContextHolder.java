package com.gosun.isap.dao;

import com.gosun.isap.dao.po.face.TFaceServer;

public class DataSourceContextHolder {
	private static final ThreadLocal<String> local = new ThreadLocal<String>();
	public static final String sfDatabaseIP = "databaseIP:";
	public static final String sfDatabaseName = "databaseName:";
	public static final String sfUserName = "userName:";
	public static final String sfPassword = "password:";

	public static String getDataSourceType() {
		return local.get();
	}

	public static void setDataSourceType(TFaceServer faceServer) {
		String dbName = new String();
		dbName = sfDatabaseIP + faceServer.getDatabaseip() + sfDatabaseName + faceServer.getDatabasename() 
			+ sfUserName + faceServer.getUsername() + sfPassword + faceServer.getPassword();
		local.set(dbName);
	}

	public static void setDataSourceTypeString(String dbName) {
		local.set(dbName);
	}

	public static void clear() {
		local.remove();
	}
}