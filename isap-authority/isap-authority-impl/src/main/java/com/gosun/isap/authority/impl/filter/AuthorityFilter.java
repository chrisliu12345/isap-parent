package com.gosun.isap.authority.impl.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcResult;
import com.gosun.isap.authority.api.AuthorityErrorCode;
import com.gosun.isap.authority.api.responsebody.LoginBody;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.utils.JsonUtils;

/**
 * session中用户的信息检验过滤器
 * 
 * @author lyf
 *
 */
public class AuthorityFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) {
		HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) RpcContext.getContext().getResponse();
		String reqUrl = request.getRequestURL().toString();
		LOGGER.debug("访问的api路径为：{}", reqUrl);
		// 判断session是否有用户信息
		if (reqUrl.indexOf("login") < 0 && reqUrl.indexOf("logout") < 0
				&& request.getSession().getAttribute(LoginBody.USER_IFNO_KEY) == null) {
			// 返回信息提示重新登录
			@SuppressWarnings("rawtypes")
			ResponseResult responseResult = ResponseResult.ok();
			responseResult.setErrorEx(AuthorityErrorCode.SESSION_TIME_OUT, null);
			try {
				String repJson = JsonUtils.obj2json(responseResult);
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
		return invoker.invoke(invocation);
	}

}