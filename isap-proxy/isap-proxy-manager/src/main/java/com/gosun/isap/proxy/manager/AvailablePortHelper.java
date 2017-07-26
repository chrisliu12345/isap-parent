package com.gosun.isap.proxy.manager;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 可用端口工具类
 * 
 * @author liuzk
 *
 */
public class AvailablePortHelper {
	private static final Short MIN_PORT = 20000;
	private static final Short MAX_PORT = 30000;
	private static Short currentPort = MIN_PORT;

	/**
	 * 获取可用端口
	 * 
	 * @return 可用端口
	 */
	public static synchronized Short getAvailablePort() {
		Short availablePort = 0;
		while (true) {
			if (currentPort++ >= MAX_PORT) {
				currentPort = MIN_PORT;
			}

			if (isPortAvailable(currentPort)) {
				availablePort = currentPort;
				break;
			}
		}
		return availablePort;
	}

	private static void bindPort(String host, int port) throws Exception {
		Socket s = new Socket();
		s.bind(new InetSocketAddress(host, port));
		s.close();
	}

	private static boolean isPortAvailable(int port) {
		try {
			bindPort("0.0.0.0", port);
			bindPort(InetAddress.getLocalHost().getHostAddress(), port);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
