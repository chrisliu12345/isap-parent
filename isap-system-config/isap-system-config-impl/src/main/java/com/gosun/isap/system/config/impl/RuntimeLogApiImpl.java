package com.gosun.isap.system.config.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.system.config.api.IRuntimeLogApi;

/**
 * 运行日志api接口实现
 * 
 * @author liuzk
 *
 */
public class RuntimeLogApiImpl implements IRuntimeLogApi {
	private static Logger logger = LoggerFactory.getLogger(RuntimeLogApiImpl.class);
	private final static String DEFAULT_LOG_LEVEL = "INFO";

	@Override
	public ResponseResult modifyLogLevel(String level) {
		ResponseResult response = ResponseResult.ok();

		if (StringUtils.isEmpty(level)) {
			level = DEFAULT_LOG_LEVEL;
		}
		logger.warn("Modify log level to " + level);
		Level logLevel = Level.toLevel(level);
		LogManager.getRootLogger().setLevel(logLevel);

		return response;
	}
}
