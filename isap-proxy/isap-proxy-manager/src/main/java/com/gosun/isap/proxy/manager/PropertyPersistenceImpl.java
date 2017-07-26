package com.gosun.isap.proxy.manager;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * 属性持久化实现
 * 
 * @author liuzk
 *
 */
public class PropertyPersistenceImpl implements IPropertyPersistence {
	private ZkClient zk;
	private String homePath;

	public PropertyPersistenceImpl(String homePath, ZkClient zk) {
		this.zk = zk;
		this.homePath = homePath;
		if (!zk.exists(homePath)) {
			zk.createPersistent(homePath, true);
		}
	}

	@Override
	public void save(ProxyProperty property) {
		String proxyId = property.getProxyId();
		// 不存在创建，存在更新节点数据
		if (!zk.exists(getProxyPropertyPath(proxyId))) {
			zk.create(getProxyPropertyPath(proxyId), property, CreateMode.PERSISTENT);
		} else {
			zk.writeData(getProxyPropertyPath(proxyId), property);
		}
	}

	@Override
	public void delete(String proxyId) {
		// 删除property节点
		zk.delete(getProxyPropertyPath(proxyId));

		// 删除根目录下的数据(dubbo注册中心生成)
		zk.deleteRecursive("/" + proxyId);
	}

	@Override
	public List<ProxyProperty> loadAll() {
		List<ProxyProperty> properties = new ArrayList<ProxyProperty>();
		List<String> nodes = zk.getChildren(homePath);
		for (String n : nodes) {
			properties.add(load(n));
		}
		return properties;
	}

	@Override
	public ProxyProperty load(String proxyId) {
		return zk.readData(getProxyPropertyPath(proxyId), new Stat());
	}

	private String getProxyPropertyPath(String proxyId) {
		return homePath + "/" + proxyId;
	}
}