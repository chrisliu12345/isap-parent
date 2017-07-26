package com.gosun.isap.authority.impl.jwt;


public class JWTTest {
	public static void main(String[] args) throws Exception {
		Payload payload = new Payload("2017-05-13 09:08:33", "2017-05-13 09:08:33", "isap", "web", "admin", "", "");
		String sign = EncodeStringGenerator.getJWTToken(payload);
		System.err.println(sign);
	}
}