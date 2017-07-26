package com.gosun.isap.proxy.api.utils.support;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.proxy.api.utils.IWorkerStateCallback;
import com.gosun.isap.proxy.api.utils.IWorkerKeeper;

/**
 * 子服务状态观察器
 * 
 * @author liuzk
 *
 */
public class AbstractWorkerKeeper implements IWorkerKeeper {
	private static Logger logger = LoggerFactory.getLogger(AbstractWorkerKeeper.class);

	private ZkClient zk;
	private String keeperHome;
	private IWorkerStateCallback callback;
	private List<String> lastChilds = null;

	public AbstractWorkerKeeper(String keeperHome, ZkClient zk) {
		this.setZk(zk);
		this.keeperHome = keeperHome;

		// 如果目录不存在，则创建目录
		if (!zk.exists(keeperHome)) {
			zk.createPersistent(keeperHome, true);
		}
		// 初始化已经存在的子节点
		lastChilds = zk.getChildren(keeperHome);

		logger.info("Keeper home " + keeperHome);
		logger.info("Last childs " + lastChilds);
	}

	@Override
	public boolean isWorkerExist(String child) {
		return zk.exists(getSubChildPath(child));
	}

	@Override
	public Object getWorkerData(String child) {
		return zk.readData(getSubChildPath(child));
	}

	@Override
	public List<String> getAllWorkers() {
		return zk.getChildren(keeperHome);
	}

	@Override
	public void setEventCallback(IWorkerStateCallback callback) {
		setCallback(callback);

		zk.subscribeChildChanges(keeperHome, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				logger.info("Clildren of path " + parentPath + ":" + currentChilds);

				// 取出增加和减少的子节点
				List<String> decreaseChilds;
				List<String> increaseChilds;
				synchronized (lastChilds) {
					decreaseChilds = new ArrayList<String>(lastChilds);
					increaseChilds = new ArrayList<String>(currentChilds);
					decreaseChilds.removeAll(currentChilds);
					increaseChilds.removeAll(lastChilds);
					// 更新当前
					lastChilds.clear();
					lastChilds = currentChilds;
				}

				logger.info("Decrease childs: " + decreaseChilds.toString());
				logger.info("Increase childs: " + increaseChilds.toString());

				if (null != getCallback()) {
					for (String s : increaseChilds) {
						getCallback().onWorkerReg(s);
					}
					for (String s : decreaseChilds) {
						getCallback().onWorkerUnreg(s);
					}
				}
			}
		});
	}

	private IWorkerStateCallback getCallback() {
		return callback;
	}

	private void setCallback(IWorkerStateCallback callback) {
		this.callback = callback;
	}

	public ZkClient getZk() {
		return zk;
	}

	public void setZk(ZkClient zk) {
		this.zk = zk;
	}

	private String getSubChildPath(String serviceId) {
		return this.keeperHome + "/" + serviceId;
	}
}
