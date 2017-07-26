package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 云台控制参数
 * 
 * @author liuzk
 *
 */
public class PtzControlParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 基础平台设备编码
	 */
	public String deviceId;
	/**
	 * 预置位名称(预置位的时候才需要填写)
	 */
	public String szName;
	/**
	 * 命令号
	 */
	public int cmdType;
	/**
	 * 参数1（如步长）
	 */
	public int param1;
	/**
	 * 参数2
	 */
	public int param2;

	public PtzControlParam() {
		deviceId = "";
		szName = ""; // 默认空，设置预知位时需要填写
	}

	/**
	 * 云台命令码
	 * 
	 * @author liuzk
	 *
	 */
	public interface PtzCmdType {
		/**
		 * 无操作
		 */
		int PTZ_NOP = 0;
		/**
		 * 上
		 */
		int PTZ_UP = 1;
		/**
		 * 下
		 */
		int PTZ_DOWN = 2;
		/**
		 * 左
		 */
		int PTZ_LEFT = 3;
		/**
		 * 右
		 */
		int PTZ_RIGHT = 4;
		/**
		 * 左上
		 */
		int PTZ_LEFTUP = 5; //
		/**
		 * 左下
		 */
		int PTZ_LEFTDOWN = 6; //
		/**
		 * 右上
		 */
		int PTZ_RIGHTUP = 7; //
		/**
		 * 右下
		 */
		int PTZ_RIGHTDOWN = 8; //
		/**
		 * 镜头拉近
		 */
		int PTZ_ZOOMIN = 9; //
		/**
		 * 镜头拉远
		 */
		int PTZ_ZOOMOUT = 10; //
		/**
		 * 调亮
		 */
		int PTZ_BRTUP = 11; //
		/**
		 * 调暗
		 */
		int PTZ_BRTDOWN = 12; //
		/**
		 * 设置预置位
		 */
		int PTZ_SET_PRESET = 13; //
		/**
		 * 删除预置位
		 */
		int PTZ_CLE_PRESET = 14; //
		/**
		 * 转到预置位
		 */
		int PTZ_GOTO_PRESET = 15; //
		/**
		 * 聚焦调远
		 */
		int PTZ_FOCUSFAR = 16; //
		/**
		 * 聚焦调近
		 */
		int PTZ_FOCUSNEAR = 17; //
		/**
		 * 停止聚焦
		 */
		int PTZ_FOCUSSTP = 18; //
		/**
		 * 停止所有动作
		 */
		int PTZ_STP = 19; //
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSzName() {
		return szName;
	}

	public void setSzName(String szName) {
		this.szName = szName;
	}

	public int getCmdType() {
		return cmdType;
	}

	public void setCmdType(int cmdType) {
		this.cmdType = cmdType;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public int getParam2() {
		return param2;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}
}
