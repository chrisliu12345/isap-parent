package com.gosun.isap.proxy.backend;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.dao.mapper.TPlatMapper;
import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.dao.po.TPlatExample;
import com.gosun.isap.proxy.api.backend.IProxyCache;
import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.sdk.constants.PlatformType;
import com.gosun.isap.proxy.api.utils.IWorkerStateCallback;
import com.gosun.isap.proxy.api.utils.IWorkerKeeper;

/**
 * proxy cache 初始化器
 * 
 * @author liuzk
 *
 */
public class ProxyCacheInitiator {
	private static Logger logger = LoggerFactory.getLogger(ProxyCacheInitiator.class);

	@Autowired
	private TPlatMapper platMapper;
	@Autowired
	private IProxyCache proxyCache;

	private IWorkerKeeper keeper;

	public ProxyCacheInitiator(IWorkerKeeper keeper) {
		this.keeper = keeper;
	}

	/**
	 * 初始化
	 */
	public void init() {
		if (keeper.isWorkerExist(IProxyCache.DEFAULT_PROXY_MANAGER_ID)) {
			reload();
		}

		keeper.setEventCallback(new IWorkerStateCallback() {
			@Override
			public void onWorkerReg(String child) {
				logger.info("Proxy manager " + child + " online! ");
				reload();
			}

			@Override
			public void onWorkerUnreg(String child) {
				logger.info("Proxy manager " + child + " offline!");
			}
		});
	}

	/**
	 * 重新加载
	 */
	private void reload() {
		List<TPlat> platforms = platMapper.selectByExample(new TPlatExample());

		List<ProxyProperty> properties = new ArrayList<ProxyProperty>();
		// 启动proxy
		for (TPlat plat : platforms) {
			ProxyProperty property = new ProxyProperty();
			property.setProxyId(plat.getId().toString());
			property.setDescription(plat.getDescription());
			property.setAccessIpAddress(plat.getAccessIpAddress());
			property.setAccessPort(plat.getAccessPort());
			property.setAccessUser(plat.getLoginUser());
			property.setAccessPassword(plat.getLoginPasswd());
			property.setPlatformType(PlatformType.valueOf(plat.getVendorType()));

			properties.add(property);
		}

		// 启动一个线程来同步（因为同步可能会阻塞）
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					proxyCache.syncRemote(properties);
				} catch (Throwable e) {
					logger.error(e.getMessage());
				}
			}
		});
		t.start();
	}
}
