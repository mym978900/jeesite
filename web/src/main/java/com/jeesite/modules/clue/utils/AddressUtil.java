package com.jeesite.modules.clue.utils;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;

import com.alibaba.fastjson.JSONObject;


public class AddressUtil {

	final static Logger logger = (Logger) LoggerFactory.getLogger(AddressUtil.class);
	/**
	 * http://lbsyun.baidu.com/apiconsole/key
	 * <百度开发者>用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key” 申请ak
	 */
	final static String AK = "yqpEOQoKwc64x5fSmAiIRoEjcF2gssOx";


	/**
	 * 地理编码 URL
	 */
	final static String ADDRESS_TO_LONGITUDEA_URL = "http://api.map.baidu.com/geocoding/v3/?output=json&location=showLocation";

	/**
	 * 地理编码
	 * @param address 
	 *         详细的位置信息获取经纬度
	 * @return
	 */
	public static Object[] AddressTolongitudea(String address) throws Exception {

//		address = "北京市朝阳区新华科技大厦";
		String param1 = URLEncoder.encode(address, "UTF-8");
		String url = ADDRESS_TO_LONGITUDEA_URL + "&ak=" + AK + "&address="+ param1;
		logger.info("请求url:" + url);
		HttpClient client = HttpClients.createDefault(); // 创建默认http连接
		HttpPost post = new HttpPost(url);// 创建一个post请求
		Double lng;//经度
		Double lat;//纬度
		Object[] obj;
		try {
			HttpResponse response = client.execute(post);// 用http连接去执行get请求并且获得http响应
			HttpEntity entity = response.getEntity();// 从response中取到响实体
			String html = EntityUtils.toString(entity);// 把响应实体转成文本
			logger.info("返回信息：" + html);
			// JSON转对象
			JSONObject jsonObject = JSONObject.parseObject(html);
			String status = jsonObject.getString("status");
			//未查到用户地址
			if("1".equals(status)) {
				return null;
			}
			String result = jsonObject.getString("result");
			JSONObject jsonObject1 = JSONObject.parseObject(result);
			String location = jsonObject1.getString("location");
			JSONObject jsonObject2 = JSONObject.parseObject(location);
			System.out.println(jsonObject2.getString("lng")+"------"+jsonObject2.getString("lat"));
			lng = Double.parseDouble(jsonObject2.getString("lng"));
			lat = Double.parseDouble(jsonObject2.getString("lat"));
			obj = new Object[2];
			obj[0] = lng;
			obj[1] = lat;
			return obj;
		} catch (Exception e) {
			
			logger.error("地理编码[异常],", e);
		}
		return null;
	}
	
	
	/**
	 * 获取范围经纬度
	 * @param address 
	 *         详细的位置信息获取范围经纬度
	 * @return
	 */
	public static Object[] findNeighPosition(double longitude,double latitude,int n) {
		//先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dis = n*5;//5千米距离
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;        
        double minlat =latitude-dlat;
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;
        double maxlng = longitude + dlng;
        
        Object[] values = {minlng,maxlng,minlat,maxlat};
        return values;
	}
	
//	public static void main(String[] args) {
//		AddressUtil au = new AddressUtil();
//		String address = "京旺南街与京旺东路交叉口";
//		Object[] html;
//		try {
//			html = au.AddressTolongitudea(address);
//			System.out.println(html[0]);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] args) {
//		String s = "{\"status\":0,\"result\":{\"location\":{\"lng\":116.87243317374606,\"lat\":38.32036671947827},\"precise\":0,\"confidence\":20,\"comprehension\":57,\"level\":\"区县\"}}";
//		JSONObject jsonObject = JSONObject.parseObject(s);
//		String result = jsonObject.getString("result");
//		JSONObject jsonObject1 = JSONObject.parseObject(result);
//		String location = jsonObject1.getString("location");
//		JSONObject jsonObject2 = JSONObject.parseObject(location);
//		System.out.println(jsonObject2.getString("lng")+"------"+jsonObject2.getString("lat"));
//	}
}
