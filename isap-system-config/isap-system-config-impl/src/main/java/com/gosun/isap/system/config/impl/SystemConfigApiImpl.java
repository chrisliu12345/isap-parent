package com.gosun.isap.system.config.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

import com.gosun.isap.system.config.api.FileSystemData;
import com.gosun.isap.system.config.api.ISystemConfigApi;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;

import com.gosun.isap.system.config.api.IpEntity;
import com.gosun.isap.system.config.api.NetworkData;
import org.apache.commons.lang.StringUtils;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.NetInfo;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;


@Path("systemconfig")
public class SystemConfigApiImpl implements ISystemConfigApi {
	private final Integer four = 4;
	private final Integer hundred = 100;
	private final Integer start = 10;
	private final Integer skip = 3;
	private final Integer bytes = 2048;


	@Path("networkinfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType = ServiceType.SYSTEM_CONFIG, operateType = OperateType.CONFIG_QUERY, description = "网络配置获取")
	public ResponseResult<List<NetworkData>> getNetworkInfo() {
		ResponseResult<List<NetworkData>> response = ResponseResult.ok();

		try {
			Sigar sigar = new Sigar();
			String[] ifNames = sigar.getNetInterfaceList();
			NetInfo netinfo = sigar.getNetInfo();
			List<NetworkData> data = new LinkedList<>();
			for (int i = 0; i < ifNames.length; i++) {
				if (!StringUtils.containsIgnoreCase(ifNames[i], "e")) {
					continue;
				}
				NetworkData networkData = new NetworkData();
				networkData.setName(ifNames[i]);
				NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(ifNames[i]);
				networkData.setAddress(ifconfig.getAddress());
				networkData.setNetmask(ifconfig.getNetmask());

				if (!ifconfig.getNetmask().equals("0.0.0.0") && !ifconfig.getBroadcast().equals("0.0.0.0") && !ifconfig.getAddress().equals("0.0.0.0")){
					networkData.setGateway(netinfo.getDefaultGateway());
				}
				if ((ifconfig.getFlags() & 1L) <= 0L) {
					continue;
				}
				NetInterfaceStat ifstat = sigar.getNetInterfaceStat(ifNames[i]);
				networkData.setRxpackets(String.valueOf(ifstat.getRxPackets()));
				networkData.setTxpackets(String.valueOf(ifstat.getTxPackets()));
				networkData.setRxbytes(String.valueOf(ifstat.getRxBytes()));
				networkData.setTxbytes(String.valueOf(ifstat.getTxBytes()));
				networkData.setRxerrors(String.valueOf(ifstat.getRxErrors()));
				networkData.setTxerrors(String.valueOf(ifstat.getTxErrors()));
				networkData.setRxdropped(String.valueOf(ifstat.getRxDropped()));
				networkData.setTxdropped(String.valueOf(ifstat.getTxDropped()));
				data.add(networkData);
			}
			response.setBody(data);
		} catch (SigarException e) {
			response.setErrorEx(ErrorCode.ERR_SYSTEM, null);
		}
		return response;
	}

	@Path("cpuinfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType = ServiceType.SYSTEM_CONFIG, operateType = OperateType.CONFIG_QUERY, description = "CPU信息获取")
	public ResponseResult<Map<String, String>> getCpuInfo() {
		ResponseResult<Map<String, String>> response = ResponseResult.ok();
		try{
			Sigar sigar = new Sigar();
			CpuPerc perc = sigar.getCpuPerc();
			Map<String, String> cpuMap = new HashMap<>();
			cpuMap.put("idle", CpuPerc.format(perc.getIdle()));
			cpuMap.put("combined", CpuPerc.format(perc.getCombined()));
			response.setBody(cpuMap);
		}catch (SigarException e){
			response.setErrorEx(ErrorCode.ERR_SYSTEM, null);
		}
		return response;
	}

	@Path("memoryinfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType = ServiceType.SYSTEM_CONFIG, operateType = OperateType.CONFIG_QUERY, description = "内存信息获取")
	public ResponseResult<Map<String, String>> getMemoryInfo() {
		ResponseResult<Map<String, String>> response = ResponseResult.ok();
		try {
			Sigar sigar = new Sigar();
			Mem mem = sigar.getMem();
			Map<String, String> memMap = new HashMap<>();
			memMap.put("total", String.valueOf(mem.getTotal() / 1048576L) + "MB");
			memMap.put("use", String.valueOf(mem.getUsed() / 1048576L + "MB"));
			memMap.put("free", String.valueOf(mem.getFree() / 1048576L + "MB"));
			memMap.put("usepercent", String.valueOf(mem.getUsedPercent()).substring(0, four));
			response.setBody(memMap);
		} catch (SigarException e) {
			response.setErrorEx(ErrorCode.ERR_SYSTEM, null);
		}
		return response;
	}

	@Path("filesysteminfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType = ServiceType.SYSTEM_CONFIG, operateType = OperateType.CONFIG_QUERY, description = "磁盘信息获取")
	public ResponseResult<List<FileSystemData>> getFileSystemInfo() {
		ResponseResult<List<FileSystemData>> response = ResponseResult.ok();
		try {
			Sigar sigar = new Sigar();
			FileSystem[] fslist = sigar.getFileSystemList();
			List<FileSystemData> data = new LinkedList<>();
			long total = 0;
			long free = 0;
			long used = 0;
			for (int i = 0; i < fslist.length; i++) {
				FileSystem fs = fslist[i];
				FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
				if (fs.getType() == 2){
					total += usage.getTotal();
					free += usage.getFree();
					used += usage.getUsed();
				}
			}
			FileSystemData fileSystemData = new FileSystemData();
			fileSystemData.setTotal(String.valueOf(total / 1024L + "MB"));
			fileSystemData.setFree(String.valueOf(free / 1024L + "MB"));
			fileSystemData.setUsed(String.valueOf(used / 1024L + "MB"));
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			fileSystemData.setPercent(String.valueOf((decimalFormat.format((used / (double)total) * hundred) + "%")));
			data.add(fileSystemData);
			response.setBody(data);
		} catch (SigarException e) {
			response.setErrorEx(ErrorCode.ERR_SYSTEM, null);
		}
		return response;
	}

    @Path("setnetwork")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType = ServiceType.SYSTEM_CONFIG, operateType = OperateType.CONFIG_MOD, description = "修改IP")
    public ResponseResult setNetwork(IpEntity ipEntity){
		ResponseResult response = ResponseResult.ok();
		String ip = ipEntity.getIp();
		String name = ipEntity.getName();
		if(!isValidIp(ip)){
			response.setError(ErrorCode.ERR_SYSTEM,"ip 地址无效");
			return response;
		}
		String mask = ipEntity.getMask();
		String gateway = ipEntity.getGateway();
		try {
			String appHome = System.getProperty(SystemConfigModule.ISAP_APP_HOME);
			String cmd =  appHome + File.separator + "bin" + File.separator + "gs_ip_mod.sh";
			Process proc = Runtime.getRuntime().exec(cmd + " " + ip + " " + gateway);
			proc.waitFor();
		}catch (Exception e){
			response.setErrorEx(ErrorCode.ERR_SYSTEM, null);
		}

	    return response;

    }

	@Path("backupdatabase")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	//此方法返回的是流，不能通过AOP判断是否执行成功添加日志
	public Response getBackup(){
		String user = "root";
		String pass = "root";
		String db = "isap";
		String hostname = "191.191.16.120";
		String tmp;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
		user = resourceBundle.getString("jdbc.username");
		pass = resourceBundle.getString("jdbc.password");
		tmp = resourceBundle.getString("jdbc.url");
		hostname = tmp.substring(tmp.indexOf(":",start) + skip, tmp.lastIndexOf(":") - 1);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);
		try {
			Runtime runtime = Runtime.getRuntime();
			Process child = runtime.exec("mysqldump -h" + hostname + " -u" + user + " -p" + pass + " " + db);
			InputStream inputStream = child.getInputStream();

			zos.putNextEntry(new ZipEntry("db.sql"));
			int len = 0;
			byte[] buffer = new byte[bytes];
			while ((len = inputStream.read(buffer)) > 0){
				zos.write(buffer, 0, len);
			}
			zos.closeEntry();
			zos.flush();
			zos.finish();
			OperateLogWriter.success(ServiceType.BACK_UP, OperateType.BACKUP, "数据库备份成功");
			return Response.ok(bos.toByteArray(), "application/zip")
					.header("Content-Disposition", "attachment; filename=db.zip")
					.build();
		}catch (IOException e){
			//throw new RuntimeException(e);
			OperateLogWriter.fail(ServiceType.BACK_UP, OperateType.BACKUP, "数据库备份失败", e.getMessage());
			return null;
		}finally {
			try {
				zos.close();
			}catch (IOException e) {
				OperateLogWriter.fail(ServiceType.SYSTEM_CONFIG, OperateType.CONFIG_QUERY, "数据库备份失败", e.getMessage());
			}
		}
	}

	@Path("backuplog")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getLog(){
		String srcdir = "/var/log/isap";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(bos);
			byte[] buffer = new byte[bytes];
			File dir = new File(srcdir);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				FileInputStream fileInputStream = new FileInputStream(files[i]);
				zos.putNextEntry(new ZipEntry(files[i].getName()));
				int len = 0;
				while ((len = fileInputStream.read(buffer)) > 0){
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fileInputStream.close();
			}
			zos.close();
			OperateLogWriter.success(ServiceType.BACK_UP, OperateType.BACKUP, "日志备份成功");
			return Response.ok(bos.toByteArray(),"application/zip")
					.header("Content-Disposition", "attachment; filename=log.zip")
					.build();
		}catch (IOException e){
			OperateLogWriter.fail(ServiceType.BACK_UP, OperateType.BACKUP, "日志备份失败", e.getMessage());
			return null;
		}
	}

	/**
	 * @param ipAddress ip 地址
	 * @return 是否有效
	 */
	public static boolean isValidIp(String ipAddress) {
		if(StringUtils.isEmpty(ipAddress)){
			return false;
		}
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		return ipAddress.matches(regex);
	}
}
