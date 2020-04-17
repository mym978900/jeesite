package com.jeesite.modules.clue.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.byai.client.auth.Token;
import com.byai.client.core.BYClient;
import com.byai.client.core.DefaultBYClient;
import com.byai.gen.v1_0_0.api.ByaiOpenapiCalljobCreate;
import com.byai.gen.v1_0_0.api.ByaiOpenapiPhoneList;
import com.byai.gen.v1_0_0.api.ByaiOpenapiRobotList;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobCreateParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobCreateResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiPhoneListParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiPhoneListResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiRobotListParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiRobotListResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiRobotListResult.RobotVO;

public class AiUtil {
	
	final static Logger logger = (Logger) LoggerFactory.getLogger(AiUtil.class);
	
	
	/**
	 * 百应科技开放接口
	 */
	final static String url = "https://open.byai.com/oauth/token";
	
	public static String getAccessToken() {
		
		CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();// 创建默认http连接
		
		HttpPost post = new HttpPost(url);// 创建一个post请求
		
		//获取ai服务秘钥
		String access_token = "";
		
		try {
			post.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
			
			List<NameValuePair> nameValuePairs = new ArrayList();
			
			post.setEntity( new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
			
			
            response = httpClient.execute(post);// 执行http连接去执行get请求并且获得http响应
			HttpEntity entity = response.getEntity();// 从response中取到响实体
			String html = EntityUtils.toString(entity);// 把响应实体转成文本
			logger.info("返回信息：" + html);
			// JSON转对象
			JSONObject jsonObject = JSONObject.parseObject(html);
			String status = jsonObject.getString("success");
			if(Boolean.parseBoolean(status)) {
				String result = jsonObject.getString("data");
				JSONObject jsonObject1 = JSONObject.parseObject(result);
				access_token = jsonObject1.getString("access_token");
				return access_token;
			}
			return "";
		}catch(Exception e) {
			logger.error("获取公钥[异常],", e);
		}
		return "";
	}
	
	public static void main(String[] args) {
		AiUtil ai = new AiUtil();
		String result = ai.getAccessToken();
		System.out.println(result);
	}
}
