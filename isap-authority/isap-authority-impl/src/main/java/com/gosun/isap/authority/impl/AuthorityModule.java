package com.gosun.isap.authority.impl;

import com.alibaba.dubbo.container.Container;

public class AuthorityModule implements Container {
	public static int AUTH_MODUL_START_FLAG;

	public void start() {
		AUTH_MODUL_START_FLAG = 1;
	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}
