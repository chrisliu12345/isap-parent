package com.gosun.isap.face.api.Config;

/**
 * 配置文件赋值常量类<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class ConfigConstants {

	// 超级管理员ID
	public static final Long ADMINISTRATORSID = 1L;
	// 基础平台数据库识别符号
	public static final String MYSQL = "Isap";
	// jsontime前缀
	public static final String JSONTIMEPREFIX = "\\/Date(";
	// jsontime后缀
	public static final String JSONTIMESUFFIX = "+0800)\\/";
	// jsontime截取开始位置
	public static final int JSONTIME_START = 6;
	// jsontime截取结束位置
	public static final int JSONTIME_END = 19;
	// 时间格式
	public static final String TIME_FORMAT = "	yyyy-MM-dd hh:mm:ss";
	// 特征值长度
	public static final int FEATUREBASE64LENGTH = 2732;
	// Base64解码调整异常数据值
	public static final int BASE64 = 256;
	// 设置服务器取得流的大小
	public static final int BUFFERSIZE = 1024;

	// 小写ok
	public static final String LOWERCASEOK = "ok";
	// 数字0
	public static final String NUMBER_0 = "0";

	// 状态
	public static final Integer STATE_1 = 1;
	public static final Integer STATE_2 = 2;
	// 使用标志
	public static final Integer USEDFLAG_0 = 0;
	public static final Integer USEDFLAG_1 = 1;
	public static final Integer USEDFLAG_2 = 2;
	// 抓拍相机类型
	public static final Integer CAPFACE_CAMERAS_TYPE = 1;
	/**
	 * 相机是否与人脸服务器绑定FALG
	 */
	public static final Integer FACEFALG_0 = 0;
	public static final Integer FACEFALG_1 = 1;
	
	/**
	 * 身份证认证
	 */
	// 身份证长度
	public static final Integer IDCODE_LENGTH_15 = 15;
	public static final Integer IDCODE_LENGTH_18 = 18;
	// 身份证正则表达式
	public static final String IDCODE_REGEX_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	public static final String IDCODE_REGEX_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	// 相似度的正则表达式
	public static final String REGEX_SIMILARITY = "^(((\\d|[1-9]\\d)(\\.\\d{1,2})?)|100|100.0|100.00)$";
	// 数据的正则表达式
	public static final String REGEX_MAXRETURN = "^\\+?[1-9][0-9]*$";
	/**
	 * 名单类型
	 */
	// 黑名单
	public static final Integer BLACKLIST = 1;
	// 白名单
	public static final Integer WHITELIST = 2;
	// 误报名单
	public static final Integer FILTERLIST = 3;
	// 导入
	public static final Integer IMPORT = 0;

	// 黑名单
	public static final String BLACKLISTNAME = "黑名单";
	// 白名单
	public static final String WHITELISTNAME = "白名单";

	// 黑名单路径
	public static final String BLACKLISTPATH = "blacklist";
	// 白名单路径
	public static final String WHITELISPATH = "whitelist";
	// 误报路径
	public static final String FILTERPATH = "filter";
	// 导入照片路径
	public static final String PATH = "tmp";

	/**
	 * 统计类型
	 */
	// 总人数
	public static final Integer STATISTICS_TOTAL = 1;
	// 新增人数
	public static final Integer STATISTICS_ADD = 2;
	// 未出现人数
	public static final Integer STATISTICS_NOTAPPEAR = 3;

	/**
	 * URl基本信息
	 */

	public static final String HTTP = "http://";
	// 冒号
	public static final String COLON = ":";
	// 斜杠
	public static final String SLASH = "/";
	// 反斜杠
	public static final String SLASH_1 = "\\";
	// 百分号
	public static final String PER_CENT = "%";
	// 空格
	public static final String SPACE = " ";
	/**
	 * URL后缀
	 */
	// 取得FTP服务器信息
	public static final String GETFTPCONFIG = "SysConfig/GetFtpConfig";
	// 对比人脸并得到数据
	public static final String GETCAPFACE = "CapFace/CompareCapFace";
	// 得到特征值
	public static final String GETFEATURE = "SysConfig/GetFeature2";
	/**
	 * errcode
	 */
	// 2001 = 查询人脸失败!
	public static final Integer COMPARE_CAPFACE_ERR = 2001;
	// 2002 = 特征值取得失败!
	public static final Integer GET_FEATURE_ERR = 2002;
	// 2003 = 时间类型转换错误!
	public static final Integer TIME_TYPE_CONVERSION_ERR = 2003;
	// 2004 = 图片上传失败!
	public static final Integer PICTURE_UPLOAD_ERR = 2004;

	// 2101 = 误报增加失败!
	public static final Integer ADD_MISINFORMATION_ERR = 2101;
	// 2102 = 误报增加失败,告警信息不存在!
	public static final Integer ADD_MISINFORMATION_ERR_ALARM = 2102;
	// 2103 = 误报恢复失败,告警信息不存在!
	public static final Integer MISINFORMATION_RECOVER_ERR_ALARM = 2103;
	// 2104 = 误报恢复失败,误报信息不存在!
	public static final Integer MISINFORMATION_RECOVER_ERR = 2104;

	// 2201 = 名单存在名单组中，不能删除!
	public static final Integer LIST_EXISTS_LISTGROUP = 2201;
	// 2202 = 名单组中存在名单，不能删除!
	public static final Integer LISTGROUP_EXISTS_LIST = 2202;
	// 2203 = 数据不存在，名单增加失败!
	public static final Integer ADD_LISTGROUP_ERR = 2203;
	// 2204 = 社区与名单组关联失败!
	public static final Integer ADD_DEPARTMENT_LISTGROUP_ERR = 2204;
	// 2205 = 社区与名单组关联解除失败!
	public static final Integer DEL_DEPARTMENT_LISTGROUP_ERR = 2205;

	// 2301 = 社区没有绑定人脸服务器!
	public static final Integer FACE_SERVER_DEPARTMENT_NOT_EXISTS = 2301;
	// 2302 = 人脸服务器与社区关联失败!
	public static final Integer ADD_FACE_SERVER_DEPARTMENT_ERR = 2302;

	// 2401 = 身份证ID异常
	public static final Integer IDCODE_ERR = 2401;
	// 2402 = 身份证ID异常
	public static final Integer UPD_IDCODE_ERR = 2402;
	// 2403 = 相似度格式不正确
	public static final Integer SIMILARITY_ERR = 2403;
	// 2403 = 最大返回数格式不正确
	public static final Integer MAXRETURN_ERR = 2404;
	
	// 2901 = 增加数据重复!
	public static final Integer ADD_DATA_EXISTS = 2901;
	// 2902 = 删除数据不存在!
	public static final Integer DEL_DATA_NOT_EXISTS = 2902;
	// 2903 = 更新数据不存在!
	public static final Integer UPD_DATA_NOT_EXISTS = 2903;
	// 2904 = 数据不能为空!
	public static final Integer EMPTY_DATA = 2904;
	// 2905 = 更新的数据表中已经存在!
	public static final Integer UPD_DATA_EXISTS = 2905;
	// 2906 = 数据为空或者名字格式错误!
	public static final Integer DATA_ERR = 2906;

}
