package com.gosun.isap.authority.impl.jwt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.common.utils.JsonUtils;
public class JWTChecker {
	private boolean dateCheck = false;// 是否验证时间

	public JWTChecker(boolean dateCheck) {
		super();
		this.dateCheck = dateCheck;
	}

	public boolean checkJWTFromHeader(HttpServletRequest request) throws Exception {
		String authString = request.getHeader(JWTConstance.JWT_HEAD_KEY);
		if (StringUtils.isNotEmpty(authString)) {
			String token = authString.replace(JWTConstance.BEGIN_OF_HEADER, "").trim();
			String[] JWTparts = token.split("\\.");
			// 判断token是否相同，
			if (JWTparts[2].equals(EncodeStringGenerator.getJWTSign(JWTparts[0], JWTparts[1]))
					&& dateValidate(JWTparts[1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否到了失效时间
	 * 
	 * @param payloadEncodeString
	 * @return
	 * @throws Exception
	 */
	private boolean dateValidate(String payloadEncodeString) throws Exception {
		String payloadJson = Encodes.decodeBase64String(payloadEncodeString);
		Payload payload = (Payload) JsonUtils.json2pojo(payloadJson, Payload.class);
		if (dateCheck) {
			DateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
			Date deadDate = simpleDateFormat.parse(payload.getExp());
			return new Date().after(deadDate);
		} else {
			return true;
		}
	}

}
