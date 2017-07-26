package com.gosun.isap.common;

import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Assert;
import org.junit.Test;

public class BeanValidateTest {

	@Test
	public void testValidate() {
		Hello h = new Hello();
		String msg = BeanValidate.validateModel(h);
		System.out.println(msg);
		Assert.assertEquals(msg, "");

	}

	public static class Hello {
		@NotEmpty(message = "不能为空")
		String value;
	}
}
