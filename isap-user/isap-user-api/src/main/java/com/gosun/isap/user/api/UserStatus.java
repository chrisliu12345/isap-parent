package com.gosun.isap.user.api;

import com.gosun.isap.dao.EnmuKey;

@EnmuKey(value = "user_status")
public interface UserStatus {
	 Byte ONLINE = 1;
	 Byte OUTLINE = 0;
}
