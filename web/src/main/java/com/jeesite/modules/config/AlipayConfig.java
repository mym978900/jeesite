package com.jeesite.modules.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100200644408";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCaGX0gfPFJ/mjLDmJcZZpkOM/kkrZ2FZjCQINr0Zs+6Ft7wlEEBsL6TswzIYhZOwbEkOU5Y5b2BE9pAPFZ3YtqSCrdgZ2xzW9Ub7+aP7VoopMOKOSiN+Jn88/L42V/8Uq8oS93AYgr5R2tHNstLZo8tzEhwNOiTmpR0knZbFKgZF0yQK7AGh5updxEgQ6UMylIxcE0G7TLXco8uo/BT7ubvaC6qjd1X+oYqSFwzB12Ci2H7bnO8ANbTprOgffe4tJ9aZEoohztRBsub7lc9sgpgQVzsiTq2hVBJUjTewIdi428SGw+3Y4k+ykjhh2KCraBg/pZh2TR8Ml7HzyTM8ffAgMBAAECggEAB6fsh4itlDeHjU8Ro/WITiJkcb64QfWguUXrQZgQVnfE08k7rS+I0FbJ3mB0HbrJA0sPmGJSRbbBGz5ov0Y6fvVG6+pco1DJjzBeHJO9t40cMT/m3Ahavz9lu4N4r3qVc/muGgOBeGzRpKAiY4Q4fXu8fSjPWuj6b0Y7VQoEsOM3jxCf/s2gojynlo4JtHi3XOmEohH+BMD9CY3EYStELvLPBrz+VQgQD4xIjJIAQ2DKchHjjobNI6sQWPOUteTOTldHGRPyI4Bv5k8jrjobP1kYobqcuHzxFQzh1QUp2cPhRfd5bpc0IRAg2D95Tipi+QMRipJ5HN2exzi2HLNdwQKBgQDt82qc37vshV+VEPW2RAkk4cf0hT3fo30OmJc30FzxAgj0fvPKl/XkxvvZ5UBhTKnoFLWwKCr9yGO7hdJKp1DCKT7lXOMZVgzdmtsZiRYjCm02bib+R95z7cWLVwpr6Zz4Y/vN8oUUBOaI8hJ8FEFOBr7EwXRDjfGZ9m/raX78JQKBgQClydRBuY6r8ksqpPMbu4gQiiVN+TIcVJ1KOP0jMIEm42CMCHK5cVqGSwue6G+t5p62cZ7ABtHxQIDivf++FXxZC2S9YTUDAVAh/9fFJTEKRlzF/l6dRcLlurBWz4u44bZdOClxKbN3lqActEHRcvsCUMgnUA3O62iR47Pt/E1yswKBgFYEV3bsHhfrQtk1L027HH8bq1fGTs6oYA3FtWx0wxRQgwI3XlPPSRFxqRtklzhVKVp/GgrbCmeBaCQixRk9kKEPne/1+xZul+oID8oUV09FdqNlgG0Nd+n6ex3a0iaeZaMZKa6SS+8nslqw/2OhQtY88/tWX3wINN1OS7kTAteNAoGAbnD3TxlXq7/qxuu/b7gLukjs9PaOpUTQy0MPjsOfJdLO3Do8gsC6dz/C7AcXbpd1H0CtamqgrMA1m9bKaUxS29a1M1twPyI+yYdvq8tM0fVxlAwR518zqGonUo1MHMtv+sWRBeurvHzBCJ4kQm0APdimnVm8+RzlOlqf4Gf1B/UCgYB4KE7/dsvw1Fbc8eU6L5lmhYJMibta7tA3vdFl5CaJhpQn9z3Cn6OpBoOFQvEQJQYKM4yLD+uUkgzVCAwwuxe81VGy02gK9cYBc9yVVqlbpCvJznXNDsV6zcOgY3GdWhBZL579Z0Q1vSChfLy1HXPK1FhAKSwL6cW/O1KxgHmZYw==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlKyU8Lc02LTMmha5hBlmqRtoRSZAW5xqjyFbwjh75HYKR9KHh9+NffK4oimL2cLoKlUR5xfRaIhiPbAk1MPk18F8SHU+iZdXfIrCddHfuaNwoNe7tjRcyz5B99xHnFu3VnQDiNAgkctdCYOy/+lnAKaDL4h5VFNOkpAVFYL1Rnu7ujCxpq3R6g9rjDO0efQ3XmgIkQDlYAD2LsPtyvHsSGovzprMDFhV+coul7Wu7Ffc3FtvCIoMK00hIyro7MGiUy/z4LbRs5L/lNL6OxUYoNwjkWMzazjuEMB+jsHBmq81V/w7oVHbXCDOYkKsZshW9qeFo2dXngZvtoOcqD3G0wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

