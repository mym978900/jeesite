package com.jeesite.modules.test.web;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.ClassUtils;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.codec.Sha1Utils;

public class Test {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");

	public static void main(String[] args) {
		Double a=10.9;
		//Double b=10;
		System.out.println(0.15*100);
		System.out.println(getPassword("qweqwe"));
		
	}

	// 获取用户密文密码
	public static String getPassword(String plaintext) {
		String plain = EncodeUtils.decodeHtml(plaintext);
		byte[] salt = Sha1Utils.genSalt(SALT_SIZE);
		byte[] hashPassword = Sha1Utils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword);
	}
}
