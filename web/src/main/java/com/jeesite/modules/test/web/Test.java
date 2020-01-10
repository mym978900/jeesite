package com.jeesite.modules.test.web;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.ClassUtils;

public class Test {
	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2020-02-30 10:10:10");
		// 获取相差的天数
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		long timeInMillis1 = calendar.getTimeInMillis();
		calendar.setTime(date);
		long timeInMillis2 = calendar.getTimeInMillis();

		long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
		float num= (float)(5003-1034)/4;   
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
		String s = df.format(num);//返回的是String类型
		System.out.println(s);
		long l=Math.round(Double.valueOf(s)*33);
		System.out.println(Double.valueOf(s));
	    System.out.println(Math.round(Double.valueOf(s)*33)+".00");
	    
	}
}
