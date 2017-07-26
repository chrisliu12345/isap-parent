package com.gosun.isap.main;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * pid工具类
 * 
 * @author liuzk
 *
 */
public class PidHelper {
	private static final String PIDFILE_CONFIG = "isap.pid_file";

	private String pidFile;

	public PidHelper() {
		String f = System.getProperty(PIDFILE_CONFIG);
		if (f != null && !f.isEmpty()) {
			setPidFile(f);
		}
	}

	/**
	 * 保存pid
	 */
	public void savePid() {
		if (null != pidFile) {
			try {
				FileWriter writter = new FileWriter(pidFile);
				writter.write(getPid().toString());
				writter.close();
			} catch (IOException e) {
				// 不处理
			}
		}
	}

	public String getPidFile() {
		return pidFile;
	}

	public void setPidFile(String pidFile) {
		this.pidFile = pidFile;
	}

	private Long getPid() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		// get pid
		String pid = name.split("@")[0];
		return Long.parseLong(pid);
	}
}
