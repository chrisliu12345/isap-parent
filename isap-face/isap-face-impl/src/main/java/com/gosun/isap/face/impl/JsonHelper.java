package com.gosun.isap.face.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.TextUtils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * json序列化与反序列化<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class JsonHelper {
	private static JsonHelper util;

	public static JsonHelper getInstance() {

		if (util == null) {
			util = new JsonHelper();
		}
		return util;
	}

	private JsonHelper() {
		super();
	}

	public String createJsonString(Object value) {
		Gson gson = new Gson();
		String str = gson.toJson(value);
		return str;
	}

	public <T> T getObject(String jsonString, Class<T> cls) {
		T t = null;
		try {
			if (isGoodJson(jsonString)) {
				Gson gson = new Gson();
				t = gson.fromJson(jsonString, cls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public <T> List<T> getList(String jsonString, TypeToken<T> tt) {
		List<T> list = new ArrayList<T>();
		try {
			if (isGoodJson(jsonString)) {
				Gson gson = new Gson();
				list = gson.fromJson(jsonString, tt.getType());
			}
		} catch (Exception e) {
		}
		return list;
	}

	public boolean isBadJson(String json) {
		return !isGoodJson(json);
	}

	public boolean isGoodJson(String json) {
		if (TextUtils.isEmpty(json)) {
			return false;
		}
		try {
			new JsonParser().parse(json);

		} catch (JsonParseException e) {
			return false;
		}

		return true;
	}

}
