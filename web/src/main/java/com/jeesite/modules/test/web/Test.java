package com.jeesite.modules.test.web;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.util.ClassUtils;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.codec.Sha1Utils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class Test {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = sdf.format(new Date());
		System.out.println(newDate);
		String dateStr = Long.toString(System.currentTimeMillis() / 1L);
		String a = "17633603265";
		System.out.println(a.substring(0,1));
		System.out.println(dateStr.substring(6));
		System.out.println(new Random().nextInt(9999));
		System.out.println(generateOrder("3", a));
		int bb=(int) Math.floor(Double.valueOf("100.00"));
		String bbb=(int) (Math.floor(Double.valueOf("25"))*0.9)+"";
		System.out.println(String.format("%.2f",(Double.valueOf("55.00") + Double.valueOf("88.00"))));
		System.out.println("vvvvvvvvv"+bbb);
		System.out.println(getPassword("admin"));
		String outRequestNo=new Random().nextInt(999999999)+"";
		System.out.println(outRequestNo+"aaaaaaaaaaa");
		Double b=668.00;
		System.out.println(b.intValue());
		Double aa=Double.valueOf("500.55");
		Double cc=Double.valueOf("500.55");
		if (aa<cc) {
			System.out.println("shazi");
		}else {
			System.out.println("zhu");
		}
		
		System.out.println(getPassword("0bd97486d16834b8af15079447bfd741f5170ec5a4255d076e09eb7a"));
	}

	public static String generateOrder(String type, String userId) {
		String dateStr = Long.toString(System.currentTimeMillis() / 1L);
		return type + dateStr.substring(4) + userId.substring(7) + new Random().nextInt(9999);
	}

	// 获取用户密文密码
	public static String getPassword(String plaintext) {
		String plain = EncodeUtils.decodeHtml(plaintext);
		byte[] salt = Sha1Utils.genSalt(SALT_SIZE);
		byte[] hashPassword = Sha1Utils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword);
	}
}
