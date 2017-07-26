package com.gosun.isap.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	// 默认数据源，也就是主库
	protected DataSource masterDataSource;
	// 保存动态创建的数据源
	private static final Map targetDataSource = new HashMap<>();
	public static final String sfDatabaseIP = "databaseIP:";
	public static final String sfDatabaseName = "databaseName:";
	public static final String sfUserName = "userName:";
	public static final String sfPassword = "password:";
	@Override
	protected DataSource determineTargetDataSource() {
		// 根据数据库选择方案，拿到要访问的数据库
		String dataSourceName = determineCurrentLookupKey();
		if ("dataSource".equals(dataSourceName)) {
			// 访问默认主库
			return masterDataSource;
		}

		// 根据数据库名字，从已创建的数据库中获取要访问的数据库
		DataSource dataSource = (DataSource) targetDataSource.get(dataSourceName);
		if (null == dataSource) {
			// 从已创建的数据库中获取要访问的数据库，如果没有则创建一个
			dataSource = this.selectDataSource(dataSourceName);
		}
		return dataSource;
	}

	@Override
	protected String determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		String dataSourceName = DataSourceContextHolder.getDataSourceType();
		if (dataSourceName == null || dataSourceName == "dataSource") {
			// 默认的数据源名字
			dataSourceName = "dataSource";
		}
		return dataSourceName;
	}

	/*
	 * public void setTargetDataSource(Map targetDataSource) {
	 * this.targetDataSource = targetDataSource;
	 * super.setTargetDataSources(this.targetDataSource); }
	 */

	/*
	 * public Map getTargetDataSource() { return this.targetDataSource; }
	 */

	public void addTargetDataSource(String key, BasicDataSource dataSource) {
		this.targetDataSource.put(key, dataSource);
		// setTargetDataSources(this.targetDataSource);
	}

	/**
	 * 该方法为同步方法，防止并发创建两个相同的数据库 使用双检锁的方式，防止并发
	 * 
	 * @param dbType
	 * @return
	 */
	private synchronized DataSource selectDataSource(String dbType) {
		// 再次从数据库中获取，双检锁
		DataSource obj = (DataSource) this.targetDataSource.get(dbType);
		if (null != obj) {
			return obj;
		}
		// 为空则创建数据库
		BasicDataSource dataSource = this.getDataSource(dbType);
		if (null != dataSource) {
			// 将新创建的数据库保存到map中
			this.setDataSource(dbType, dataSource);
			return dataSource;
		}

		return null;
	}

	/**
	 * 查询对应数据库的信息
	 * 
	 * @param dbtype
	 * @return
	 */
	private BasicDataSource getDataSource(String dbtype) {
		String oriType = DataSourceContextHolder.getDataSourceType();
		
		String databaseIP = new String();
		String databaseName = new String();
		String userName = new String();
		String password = new String();
		
		databaseIP= oriType.substring(oriType.indexOf(sfDatabaseIP)+sfDatabaseIP.length(), oriType.indexOf(sfDatabaseName));
		databaseName = oriType.substring(oriType.indexOf(sfDatabaseName)+sfDatabaseName.length(), oriType.indexOf(sfUserName));
		userName = oriType.substring(oriType.indexOf(sfUserName)+sfUserName.length(), oriType.indexOf(sfPassword));
		password = oriType.substring(oriType.indexOf(sfPassword)+sfPassword.length());
		
		String url = "jdbc:jtds:sqlserver://" + databaseIP + ";DatabaseName=" + databaseName;
		BasicDataSource dataSource = createDataSource(url, userName, password);
		return dataSource;
	}

	// 创建SQLServer数据源
	private BasicDataSource createDataSource(String url, String userName, String password) {
		return createDataSource("net.sourceforge.jtds.jdbc.Driver", url, userName, password);
	}

	// 创建数据源
	private BasicDataSource createDataSource(String driverClassName, String url, String username, String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setTestWhileIdle(true);

		return dataSource;
	}

	public void setDataSource(String type, BasicDataSource dataSource) {
		this.addTargetDataSource(type, dataSource);
		DataSourceContextHolder.setDataSourceTypeString(type);
	}

	/*
	 * @Override public void setTargetDataSources(Map targetDataSources) { //
	 * TODO Auto-generated method stub
	 * super.setTargetDataSources(targetDataSources); //
	 * 重点：通知container容器数据源发生了变化 afterPropertiesSet(); }
	 */

	/**
	 * 该方法重写为空，因为AbstractRoutingDataSource类中会通过此方法将，targetDataSources变量中保存的数据源交给resolvedDefaultDataSource变量
	 * 在本方案中动态创建的数据源保存在了本类的targetDataSource变量中。如果不重写该方法为空，会因为targetDataSources变量为空报错
	 * 如果仍然想要使用AbstractRoutingDataSource类中的变量保存数据源，则需要在每次数据源变更时，调用此方法来为resolvedDefaultDataSource变量更新
	 */
	@Override
	public void afterPropertiesSet() {
	}

	public DataSource getMasterDataSource() {
		return masterDataSource;
	}

	public void setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

}
