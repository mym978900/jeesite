package com.jeesite.modules.pay.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信配置类
 */
@Configuration
@PropertySource(value = "classpath:wxinfo.properties")
@Data
public class WxPayConfig {

	/**
	 * 公众号appid
	 */
	@Value("${APP_ID}")
	private String appId;

	/**
	 * 公众号秘钥
	 */
	@Value("${APP_SECRET}")
	private String appsecret;

	/**
	 * 商户号id
	 */
	@Value("${MCH_ID}")
	private String mchId;

	/**
	 * 支付key
	 */
	@Value("${API_KEY}")
	private String key;

	/**
	 * 微信支付回调url
	 */
	@Value("${WXPAY_CALLBACK}")
	private String payCallbackUrl;

	/**
	 * 统一下单url
	 */
	private static final String UNIFIED_ORDER_URL = "http://api.xdclass.net:8081/pay/unifiedorder";

	public static String getUnifiedOrderUrl() {
		return UNIFIED_ORDER_URL;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPayCallbackUrl() {
		return payCallbackUrl;
	}

	public void setPayCallbackUrl(String payCallbackUrl) {
		this.payCallbackUrl = payCallbackUrl;
	}

}
