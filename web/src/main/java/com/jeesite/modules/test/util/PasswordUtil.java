package com.jeesite.modules.test.util;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.codec.Sha1Utils;

public class PasswordUtil {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	//获取用户密文密码
	public static String getPassword(String plaintext) {
		String plain = EncodeUtils.decodeHtml(plaintext);
		byte[] salt = Sha1Utils.genSalt(SALT_SIZE);
		byte[] hashPassword = Sha1Utils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword);
	}
	//验证用户登录密码
	public static boolean checkPassword(String plainPassword, String password) {
		try {
			String plain = EncodeUtils.decodeHtml(plainPassword);
			byte[] salt = EncodeUtils.decodeHex(password.substring(0, 16));
			byte[] hashPassword = Sha1Utils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
			return password.equals(EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword));
		} catch (Exception e) {
			return false;
		}
	}
}
