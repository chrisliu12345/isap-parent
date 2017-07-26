package com.gosun.isap.common.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.gosun.isap.common.IsapBaseException;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.common.utils.JsonUtils;

/**
 * restful接口统一异常处理
 * 
 * @author liuzk
 *
 */
public class RestExceptionFilter implements Filter {
	private static ResourceBundle res = ResourceBundle.getBundle("META-INF/message/TableBusiness");
	private final Logger logger;

	public RestExceptionFilter() {
		this(LoggerFactory.getLogger(RestExceptionFilter.class));
	}

	public RestExceptionFilter(Logger logger) {
		this.logger = logger;
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Result result = invoker.invoke(invocation);
		if (result.hasException() && GenericService.class != invoker.getInterface()) {

			Throwable exception = result.getException();

			// 先记录错误信息日志
			logger.error("Got unchecked and undeclared exception which called by "
					+ RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName()
					+ ", method: " + invocation.getMethodName() + ", exception: " + exception.getClass().getName()
					+ ": " + exception.getMessage(), exception);

			int errorCode = 0;
			StringBuilder sbMsg = new StringBuilder();
			if (exception instanceof IsapBaseException) {
				// 继承自isap基本异常的异常
				errorCode = ErrorCode.ERR_SYSTEM;
				sbMsg.append("系统异常! ").append(exception.getMessage());
			} else if (exception instanceof DataAccessException || exception instanceof SQLException) {
				// 数据库异常
				errorCode = ErrorCode.ERR_DB_OPERATE_FAIL;
				sbMsg.append("数据库操作异常! ");
				// 如果是数据完整性异常，需要解析详细信息
				if (exception instanceof DataIntegrityViolationException) {
					sbMsg.append(parseDataIntegrityViolationException((DataIntegrityViolationException) exception));
				} else {
					sbMsg.append(exception.getMessage());
				}
			} else if (exception instanceof IllegalArgumentException) {
				errorCode = ErrorCode.ERR_ILLEGAL_ARGUMENT;
				sbMsg.append("参数非法! ").append(exception.getMessage());
			} else {
				// 其他异常
				errorCode = ErrorCode.ERR_UNKNOWN;
				sbMsg.append("未知异常! ").append(exception.getMessage());
			}

			@SuppressWarnings("rawtypes")
			ResponseResult r = ResponseResult.ok();
			r.setError(errorCode, sbMsg.toString());

			// 设置相应消息
			if (RpcContext.getContext().getResponse() != null
					&& RpcContext.getContext().getResponse() instanceof HttpServletResponse) {
				HttpServletResponse response = (HttpServletResponse) RpcContext.getContext().getResponse();
				response.setStatus(HttpServletResponse.SC_OK);

				try {
					String repJson = JsonUtils.obj2json(r);
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(repJson);
					return new RpcResult();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						response.getWriter().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return result;
	}

	private String parseDataIntegrityViolationException(DataIntegrityViolationException e) {
		StringBuilder sb = new StringBuilder();

		String message = e.getMessage();

		// 主表
		String mainTable = null;
		// 引用表
		String refTable = null;
		Pattern pattern1 = Pattern.compile("a foreign key constraint fails \\(`[^`]*`.`([^`]*)`");
		Pattern pattern2 = Pattern.compile("\\) REFERENCES `([^`]*)`");
		Pattern pattern3 = Pattern.compile("add or update a child row");
		Matcher matcher1 = pattern1.matcher(message);
		Matcher matcher2 = pattern2.matcher(message);
		Matcher matcher3 = pattern3.matcher(message);
		boolean deleteSql = true; // 是否是delete删除sql语句
		if (matcher1.find()) {
			refTable = matcher1.group(1);
		}
		if (matcher2.find()) {
			mainTable = matcher2.group(1);
		}
		if (matcher3.find()) {
			deleteSql = false;
		}

		if (StringUtils.isNotEmpty(mainTable) && StringUtils.isNotEmpty(refTable)) {
			if (deleteSql) {
				// 读取表与业务关联配置信息
				sb.append("删除").append(res.getString(mainTable)).append("数据失败，因为关联了").append(res.getString(refTable))
						.append("，请先取消已关联的业务后再操作。");
			} else {
				sb.append("更新").append(res.getString(refTable)).append("数据失败，因为").append(res.getString(mainTable))
						.append("中数据记录不存在，请检查数据有效性。");
			}
		}

		return sb.toString();
	}
}