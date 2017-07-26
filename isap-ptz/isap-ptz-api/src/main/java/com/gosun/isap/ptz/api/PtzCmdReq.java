package com.gosun.isap.ptz.api;
/**
 * ptz命令请求 
 * @author 1
 *
 */
public class PtzCmdReq {
private String deviceId;
private int operation;
private int cmd;
private int step;

public int getOperation() {
	return operation;
}
public void setOperation(int operation) {
	this.operation = operation;
}
public int getCmd() {
	return cmd;
}
public void setCmd(int cmd) {
	this.cmd = cmd;
}
public int getStep() {
	return step;
}
public void setStep(int step) {
	this.step = step;
}
public String getDeviceId() {
	return deviceId;
}
public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
}

}
