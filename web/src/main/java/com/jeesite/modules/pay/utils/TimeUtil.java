package com.jeesite.modules.pay.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getBanYear(String year) throws ParseException {
		Date date = format.parse(year);

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, 6);// 把日期往后增加一个月.整数往后推,负数往前移动
		// return format.format(date);
		return format.format(calendar.getTime());
	}

	public static String getZhenYear(String year) throws ParseException {
		Date date = format.parse(year);

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.YEAR, 1);// 把日期往后增加一年.整数往后推,负数往前移动
		return format.format(calendar.getTime());

	}

}
