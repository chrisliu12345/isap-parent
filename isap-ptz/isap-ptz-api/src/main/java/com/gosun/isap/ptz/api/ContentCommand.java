package com.gosun.isap.ptz.api;

public class ContentCommand {
 public static final int ADD_PRESET_CMD = 0;//添加预置位 命令 
 public static final int DEL_PRESET_CMD = 1;//删除预置位命令 
 public static final int DIRECTION_CONTROL_CMD =0;//方向控制命令
 public static final int SCENE_CONTROL_CMD =1 ;//镜头控制命令 
 public static final int STOP_ALL_CMD =2;//停止所有操作 
 public static final int ERROR_GET_DEVICE =5000;//获取设备失败
 public static final int ERROR_OPERATE_AUTH = 5001;//获取操作权限失败
 public static final int ERROR_CALL_SDK = 5002;//调用SDK异常 
 public static final int NO_ERROR = 0;//返回成功 
 public static final String  MSG_OK = "ok";//返回ok
 public static final String  MSG_DEVICE_ERROR = "获取设备错误";//获取设备失败 
 public static final String  MSG_OPERATE_ERROR ="无云台控制权限";//不能进行ptz控制操作
 public static final String  CURRENT_USER_KEY = "current_user";//当前用户
 public static final String  MSG_CALL_SDK_ERROR= "调用SDK失败";//调用SDK失败 
 public static final int  MILLISECOND = 1000;//1000毫米
 public static final int  TIMEINTERVAL = 5 ;//5s间隔
 public static final int PTZ_ZOOMIN  = 9; //变倍+
 public static final int PTZ_FOCUSNEAR = 10;//变焦+
 public static final int PTZ_BRTUP = 11;//光圈 +
 public static final int PTZ_ZOOMOUT = 12;//变倍 -
 public static final int PTZ_FOCUSFAR =13;//变焦 -
 public static final int PTZ_BRTDOWN =14;//光圈 -
 public static final int PTZ_DIRECTION = 8;//ptz 右下
}
