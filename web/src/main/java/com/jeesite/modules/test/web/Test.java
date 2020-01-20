package com.jeesite.modules.test.web;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	public static void main(String[] args) {

		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FwvuFWVLTCdpgirTHD2",
				"4bvT0wNF90llCp4ArRlWPRyYIE6f1Z");
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "13681394120");
        request.putQueryParameter("SignName", "奥力格科技");
        request.putQueryParameter("TemplateCode", "SMS_182682628");
        request.putQueryParameter("TemplateParam", "{\"name\":\"犟闪闪\",\"phone\":\"13681394120\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
