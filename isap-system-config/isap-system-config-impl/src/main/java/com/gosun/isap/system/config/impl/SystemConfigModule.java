package com.gosun.isap.system.config.impl;

import cn.jiguang.common.utils.StringUtils;
import com.alibaba.dubbo.container.Container;

import java.io.File;

/**
 * Created by admin on 2017/5/2.
 */
public class SystemConfigModule implements Container {
	public final static String ISAP_APP_HOME = "isap.application.home";

	public void start() {
		String appHome = System.getProperty(ISAP_APP_HOME);
		if (!StringUtils.isEmpty(appHome)) {
			String libraryPath = appHome + File.separator + "lib" + File.separator + "dll";
			System.setProperty("java.library.path", libraryPath);
		}
	}

	public void stop() {

	}
}
