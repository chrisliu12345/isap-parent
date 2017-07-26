package com.gosun.isap.common.error;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 消息模板实现
 * 
 * @author liuzk
 *
 */
public class MessageTemplateImpl implements IMessageTemplate {

	private ResourceBundle bundle = null;

	MessageTemplateImpl(String resource) {
		loadResource(resource);
	}

	@Override
	public String getMessage(Integer err) {
		return getMessage(err, null);
	}

	@Override
	public String getMessage(Integer err, String[] params) {
		String s;
		try {
			String t = bundle.getString(String.valueOf(err));
			s = print(t, params);
		} catch (MissingResourceException e) {
			s = e.getMessage();
		}
		return s;
	}

	private void loadResource(String resource) {
		bundle = ResourceBundle.getBundle(resource);
	}

	private String print(String message, String[] values) {
		if ((values == null) || (values.length == 0)) {
			return message;
		}

		String msg = message;
		for (int i = 0; i < values.length; ++i) {
			msg = msg.replace("{" + i + "}", values[i]);
		}

		return msg;
	}

}
