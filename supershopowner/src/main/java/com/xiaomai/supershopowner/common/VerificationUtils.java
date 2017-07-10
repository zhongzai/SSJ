package com.xiaomai.supershopowner.common;


import org.springframework.util.Assert;

public class VerificationUtils {

	private VerificationUtils() {
	}

	public static void validNotNull(Object... objects) {

		for (Object object : objects) {
			Assert.notNull(object, object.toString() + ":" + "can not be null");
		}

	}

}
