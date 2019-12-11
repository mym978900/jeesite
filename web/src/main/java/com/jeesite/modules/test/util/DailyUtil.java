package com.jeesite.modules.test.util;

import java.util.UUID;

public class DailyUtil {

	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
