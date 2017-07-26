package com.gosun.isap.proxy.api.utils;

import java.util.List;

/**
 * 子服务状态观察器
 * 
 * @author liuzk
 *
 */
public interface IWorkerKeeper {

	/**
	 * 判断worker是否存在
	 * 
	 * @param workerId
	 *            worker id
	 * @return true/false
	 */
	boolean isWorkerExist(String workerId);

	/**
	 * 获取所有的子worker
	 * 
	 * @return 子服务列表
	 */
	List<String> getAllWorkers();

	/**
	 * 获取子服务节点的数据
	 * 
	 * @param workerId
	 *            worker id
	 * @return 子服务节点数据
	 */
	Object getWorkerData(String workerId);

	/**
	 * 设置事件回调
	 * 
	 * @param callback
	 *            回调接口
	 */
	void setEventCallback(IWorkerStateCallback callback);
}
