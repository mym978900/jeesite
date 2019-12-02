package com.jeesite.modules.test.service.impl;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jeesite.modules.test.service.TestMessageService;

@Service
public class TestMessageServiceImpl implements TestMessageService {

	@Override
	public void toGetMessage(String phone) {
		// TODO Auto-generated method stub
		DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIIxKfL09legx7", "fbsGtBZaAxDTLM1nwOSpPWDrlZJ1dm");
        IAcsClient client = new DefaultAcsClient(profile);

        String password=new Random().nextInt(899999)+100000+"";
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "\u4fee\u914d\u8fde");
        request.putQueryParameter("TemplateCode", "SMS_172884080");
        request.putQueryParameter("TemplateParam", "{\"num\":\""+password+"\"}");
        String json = JSONUtils.toJSONString(password);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            
            //失效时间
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
	}

}
