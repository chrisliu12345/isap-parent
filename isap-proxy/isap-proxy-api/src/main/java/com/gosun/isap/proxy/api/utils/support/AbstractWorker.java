package com.gosun.isap.proxy.api.utils.support;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.proxy.api.utils.IWorker;

/**
 * 服务状态上报器实现
 * 
 * @author liuzk
 *
 */
public class AbstractWorker implements IWorker {
	private static Logger logger = LoggerFactory.getLogger(AbstractWorker.class);
	private ZkClient zk;
	private String keeperHome;

	public AbstractWorker(String keeperHome, ZkClient zk) {
		this.setZk(zk);
		this.setKeeperHome(keeperHome);
		// 如果目录不存在，则创建目录
		if (!zk.exists(keeperHome)) {
			zk.createPersistent(keeperHome, true);
		}

		logger.info("Keeper home " + keeperHome);
	}

	@Override
	public void registerToKeeper(String workerId, Object data) {
		/**
		 * 注意：因为zookeeper是基于session的，如果在session时间内，节点没有删除，而服务重启后，是另一个session，现在自己再创建后，
		 * 以前的session会在超期后把节点删除，导致这里创建的节点被删除。所以，在创建结点前，先删除之前的结点
		 */
		if (zk.exists(getWorkerPath(workerId))) {
			zk.deleteRecursive(getWorkerPath(workerId));
			logger.info("Delete last existing service path " + getWorkerPath(workerId));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		// 不存在创建，存在更新节点数据
		if (!zk.exists(getWorkerPath(workerId))) {
			// 创建临时节点
			zk.create(getWorkerPath(workerId), data, CreateMode.EPHEMERAL);
		} else {
			zk.writeData(getWorkerPath(workerId), data);
		}
	}

	@Override
	public void unregisterFromKeeper(String workerId) {
		zk.deleteRecursive(getWorkerPath(workerId));
	}

	private void setZk(ZkClient zk) {
		this.zk = zk;
	}

	private void setKeeperHome(String keeperHome) {
		this.keeperHome = keeperHome;
	}

	private String getKeeperHome() {
		return keeperHome;
	}

	private String getWorkerPath(String workerId) {
		return this.getKeeperHome() + "/" + workerId;
	}
}
