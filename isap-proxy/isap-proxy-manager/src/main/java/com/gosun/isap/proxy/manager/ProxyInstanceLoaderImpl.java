package com.gosun.isap.proxy.manager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.manager.AbstractProxyInstanceLoader;

/**
 * proxy实例加载器实现
 * 
 * @author liuzk
 *
 */
public class ProxyInstanceLoaderImpl extends AbstractProxyInstanceLoader {
	private static Logger logger = LoggerFactory.getLogger(ProxyInstanceLoaderImpl.class);

	private static final String PROXY_START_CMD_CONFIG = "isap.proxy.start_cmd";
	private static final String SPACE = " ";

	private boolean isWindows = true;
	private String proxyStartCmd;

	public ProxyInstanceLoaderImpl(String proxyStartCmd) throws FileNotFoundException {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			isWindows = true;
		} else {
			isWindows = false;
		}

		// 再从参数获取，优先使用配置参数
		String startCmd = System.getProperty(PROXY_START_CMD_CONFIG);
		if (null != startCmd && !startCmd.isEmpty()) {
			proxyStartCmd = startCmd;
		} else {
			startCmd = proxyStartCmd;
		}

		this.proxyStartCmd = startCmd;

		logger.info("proxyStartCmd:" + startCmd);

		File f = new File(startCmd);
		if (!f.exists()) {
			throw new FileNotFoundException(startCmd);
		}
	}

	@Override
	public void start() throws Exception {
		StringBuilder proxyCmd = new StringBuilder();

		if (isWindows) {
			// windows运行时，必须要加cmd /C来执行，否则有问题
			proxyCmd.append("cmd /C").append(SPACE);
		}

		ProxyProperty property = this.getProperty();

		proxyCmd.append(proxyStartCmd).append(SPACE);
		proxyCmd.append(property.getProxyId()).append(SPACE);
		proxyCmd.append(property.getProtocolPort()).append(SPACE);
		proxyCmd.append(property.getPlatformType().getType()).append(SPACE);
		proxyCmd.append(property.getAccessIpAddress()).append(SPACE);
		proxyCmd.append(property.getAccessPort()).append(SPACE);
		proxyCmd.append(property.getAccessUser()).append(SPACE);
		proxyCmd.append(property.getAccessPassword()).append(SPACE);

		logger.info("execute command : " + proxyCmd.toString());

		CommandLine commandline = CommandLine.parse(proxyCmd.toString());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor exec = new DefaultExecutor();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);

		exec.setExitValues(null);
		exec.setStreamHandler(streamHandler);
		try {
			exec.execute(commandline, resultHandler);
		} catch (ExecuteException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public void stop() {
		Long proxyPid = getRunningInfo().getPid();
		StringBuilder cmd = new StringBuilder();

		if (0 == proxyPid) {
			return;
		}

		if (isWindows) {
			cmd.append("taskkill /F /PID " + proxyPid.toString());
		} else {
			cmd.append("kill " + proxyPid.toString());
		}

		try {
			Process p = Runtime.getRuntime().exec(cmd.toString());
			p.waitFor();
		} catch (IOException e1) {

		} catch (InterruptedException e2) {

		}
	}

}
