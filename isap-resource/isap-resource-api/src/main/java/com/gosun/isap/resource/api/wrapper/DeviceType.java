package com.gosun.isap.resource.api.wrapper;

public interface DeviceType {
	
	int DEVICE_TYPE_UNKNOWN =0;   //未知设备
	int DEVICE_TYPE_NVR = 1; //主设备nvr
	int DEVICE_TYPE_ENCODER =2;   //主设备编码器
	int DEVICE_TYPE_DVR=3;   //主设备DVR
	int DEVICE_TYPE_IPC=4;   //主设备IPC
	int CHANNEL_TYPE_ENC_NORMAL=5; // 枪机
	int CHANNEL_TYPE_ENC_SD=6;// 球机
	int CHANNEL_TYPE_ENC_HALFSD=7;// 半球

}
