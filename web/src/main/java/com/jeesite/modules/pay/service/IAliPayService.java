package com.jeesite.modules.pay.service;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.alibaba.dubbo.config.annotation.Service;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
/**
 * 扫码支付以及手机H5支付
 * 创建者 科帮网
 * 创建时间	2017年7月27日
 */
@Service
public interface IAliPayService {
	/**
	 * 阿里支付预下单
	 * 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。
     * 预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。
     * 当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。
     * 2018年起，扫码支付申请需要门店拍照等等，申请流程复杂了
	 *
	 */
	String aliPay(Product product);
    /**
     * 阿里支付退款
     *
     */
	String aliRefund(Product product);
	/**
	 * 关闭订单
	 *
	 */
	String aliCloseorder(Product product);
	/**
     * 下载对账单 
	 *
	 */
	String downloadBillUrl(String billDate,String billType);
	/**
	 * 手机H5支付、腾讯相关软件下不支持、使用UC等浏览器打开
	 * 方法一：
	 * 对于页面跳转类API，SDK不会也无法像系统调用类API一样自动请求支付宝并获得结果，而是在接受request请求对象后，
	 * 为开发者生成前台页面请求需要的完整form表单的html（包含自动提交脚本），商户直接将这个表单的String输出到http response中即可。
	 * 方法二：
	 * 如果是远程调用返回消费放一个form表单 然后调用方刷新到页面自动提交即可
	 */
	String aliPayMobile(Product product);
	/**
	 * 网站支付
	 */
	String aliPayPc(HttpServletResponse response, Model model,HttpServletRequest request, Product product);
	/**
	 * APP支付

	 */
	String appPay(Product product);
	
	/**
	 * 验证签名1
	 */
	boolean rsaCheckV1(Map<String,String> params);
	/**
	 * 验证签名2
	 */
	boolean rsaCheckV2(Map<String,String> params);
	//根据订单号查询
	VideoOrder findOrderByTradeNo(String outtradeno);
	//根据账号查询会员信息
	JsSysMember findMemberByLoginCode(String loginCode);
	//支付成功修改数据
	void updateOrderAndMember(VideoOrder ord, JsSysMember mem);
}
