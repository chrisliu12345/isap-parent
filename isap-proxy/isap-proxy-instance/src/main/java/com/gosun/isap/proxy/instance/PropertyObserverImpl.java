package com.gosun.isap.proxy.instance;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * property读取器实现
 * 
 * @author liuzk
 *
 */
public class PropertyObserverImpl implements IPropertyObserver {
	private ZkClient zk;
	private IPropertyChangeCallback callback;
	private String homePath;

	public PropertyObserverImpl(String homePath, ZkClient zk) {
		this.zk = zk;
		this.homePath = homePath;
		// 创建property目录
		if (!zk.exists(homePath)) {
			zk.createPersistent(homePath, true);
		}
	}

	@Override
	public void watch(String proxyId, IPropertyChangeCallback callback) {
		this.setCallback(callback);
		zk.subscribeDataChanges(getProxyPropertyPath(proxyId), new IZkDataListener() {
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				if (null != callback) {
					callback.onChanged((ProxyProperty) data);
				}
			}

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				if (null != callback) {
					callback.onDeleted(dataPath);
				}
			}
		});
	}

	public IPropertyChangeCallback getCallback() {
		return callback;
	}

	public void setCallback(IPropertyChangeCallback callback) {
		this.callback = callback;
	}

	private String getProxyPropertyPath(String proxyId) {
		return homePath + "/" + proxyId;
	}
}
