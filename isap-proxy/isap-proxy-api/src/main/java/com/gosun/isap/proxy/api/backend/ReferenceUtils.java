package com.gosun.isap.proxy.api.backend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.gosun.isap.proxy.api.instance.ISdkAdapter;

/**
 * 引用工具
 * 
 * @author liuzk
 *
 */
public class ReferenceUtils {
	private static Logger logger = LoggerFactory.getLogger(ReferenceUtils.class);

	private static ApplicationConfig application = new ApplicationConfig("sdk_proxy");
	// 注册中心信息缓存
	private static Map<String, RegistryConfig> registryCache = new ConcurrentHashMap<>();
	// sdk ReferenceConfig缓存
	private static Map<String, ReferenceConfig<ISdkAdapter>> referenceCache = new ConcurrentHashMap<>();

	/**
	 * 获取引用配置
	 * 
	 * @param business
	 *            业务名称
	 * @param address
	 *            注册中心地址
	 * @param group
	 *            注册中心分组
	 * @return 引用对象
	 */
	public static ReferenceConfig<ISdkAdapter> getReferenceConfig(String business, String address, String group) {
		String referenceKey = business;
		ReferenceConfig<ISdkAdapter> referenceConfig = referenceCache.get(referenceKey);
		if (null == referenceConfig) {
			referenceConfig = new ReferenceConfig<>();
			referenceConfig.setApplication(application);
			referenceConfig.setRegistry(getRegistryConfig(address, group));
			referenceConfig.setInterface(ISdkAdapter.class);
			referenceConfig.setLazy(true);
			referenceCache.putIfAbsent(referenceKey, referenceConfig);
			logger.info("Cache new reference, business " + business + ", address " + address + ", group " + group);
		}
		return referenceConfig;
	}

	/**
	 * 销毁引用配置
	 * 
	 * @param business
	 *            业务名称
	 */
	public static void destroyReferenceConfig(String business) {
		String referenceKey = business;
		ReferenceConfig<ISdkAdapter> referenceConfig = referenceCache.get(referenceKey);
		if (null == referenceConfig) {
			return;
		}
		logger.info("Destroy reference, business " + business);
		referenceConfig.destroy();
		referenceCache.remove(referenceKey);
	}

	/**
	 * 获取注册中心配置
	 * 
	 * @param address
	 *            注册中心地址
	 * @param group
	 *            注册中心组
	 * @return 注册中心配置
	 */
	private static RegistryConfig getRegistryConfig(String address, String group) {
		String key = address + "-" + group;
		RegistryConfig registryConfig = registryCache.get(key);
		if (null == registryConfig) {
			registryConfig = new RegistryConfig();
			registryConfig.setId(group);
			registryConfig.setAddress(address);
			registryConfig.setGroup(group);
			registryCache.putIfAbsent(key, registryConfig);
		}
		return registryConfig;
	}
}
