package com.gosun.isap.authority.api;

import com.gosun.isap.dao.EnmuKey;

@EnmuKey(value = "auth_type")
public interface AuthType {
	 Integer TEMP = 1;//临时
	 Integer NORMAL = 0;//普通
}
