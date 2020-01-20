package com.jeesite.modules.pay.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jeesite.modules.pay.config.WxPayConfig;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.pay.service.IAliPayService;
import com.jeesite.modules.pay.service.VideoOrderService;
import com.jeesite.modules.pay.utils.IpUtils;
import com.jeesite.modules.pay.utils.QRUtil;
import com.jeesite.modules.pay.utils.TimeUtil;
import com.jeesite.modules.pay.utils.VideoOrderDto;
import com.jeesite.modules.pay.utils.WXPayUtil;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.JumpVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedMap;

/**
 * 订单接口
 */
@RestController
@RequestMapping(value = "${adminPath}/wxpay")
public class WxPayController {

	@Autowired
	private WxPayConfig weChatConfig;

	@Autowired
	private VideoOrderService videoOrderService;
	@Autowired
	private IAliPayService aliPayService;

	/**
	 * 用户点击购买下单接口
	 */
	@ResponseBody
	@RequestMapping(value = "buy", method = RequestMethod.POST)
	public JumpVo saveOrder(Product product, HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		// 2、保存订单同时返回codeUrl
		JumpVo vo = videoOrderService.save(response, model, request, product);
		if (vo.getCodeUrl() == null) {
			throw new NullPointerException();
		}
		return vo;

		/*
		 * // 3、通过google工具生成二维码供用户扫码支付 try { // 3、1生成二维码配置 //Map<EncodeHintType,
		 * Object>hints = new HashMap<>(); Hashtable<EncodeHintType, Object> hints =new
		 * Hashtable(); // 3、2设置纠错等级
		 * hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
		 * 
		 * // 3、3编码类型 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		 * 
		 * BitMatrix bitMatrix = new
		 * MultiFormatWriter().encode(codeUrl,BarcodeFormat.QR_CODE, 400, 400, hints);
		 * OutputStream out =response.getOutputStream();
		 * 
		 * MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	/**
	 * 微信支付回调 该链接是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。
	 * notify_url不能有参数，外网可以直接访问，不能有访问控制（比如必须要登录才能操作）。示例：notify_url：“https://pay.weixin.qq.com/wxpay/pay.action”
	 * 支付完成后，微信会把相关支付结果和用户信息发送给商户，商户需要接收处理，并返回应答。
	 * 对后台通知交互时，如果微信收到商户的应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。
	 * （通知频率为15/15/30/180/1800/1800/1800/1800/3600，单位：秒）
	 * 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
	 * 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
	 * 特别提醒：商户系统对于支付结果通知的内容一定要做签名验证，防止数据泄漏导致出现“假通知”，造成资金损失。
	 */
	@ResponseBody
	@RequestMapping(value = "callback")
	public void orderCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("微信支付回调开启！！");
		InputStream inputStream = request.getInputStream();

		// BufferedReader是包装设计模式，性能更搞
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		StringBuffer sb = new StringBuffer();
		// 1、将微信回调信息转为字符串
		String line;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		inputStream.close();

		// 2、将xml格式字符串格式转为map集合
		Map<String, String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
		System.out.println(callbackMap.toString());

		// 3、转为有序的map
		SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

		// 4、判断签名是否正确
		if (WXPayUtil.isCorrectSign(sortedMap, weChatConfig.getKey())) {

			// 5、判断回调信息是否成功
			if ("SUCCESS".equals(sortedMap.get("result_code"))) {

				// 获取商户订单号
				// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
				String outTradeNo = sortedMap.get("out_trade_no");
				System.out.println("outtradeno" + outTradeNo);

				// 6、数据库查找订单,如果存在则根据订单号更新该订单
				VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
				System.out.println(dbVideoOrder);
				if (dbVideoOrder != null && dbVideoOrder.getState() == 0) { // 判断逻辑看业务场景
					//VideoOrder videoOrder = new VideoOrder();
					//videoOrder.setOpenid(outTradeNo);
					//videoOrder.setNotifyTime(new Date());
					// 修改支付状态，之前生成的订单支付状态是未支付，这里表面已经支付成功的订单
					//videoOrder.setState(1);
					/**
					 * 会员表编辑
					 */
					//查询订单
					VideoOrder order = aliPayService.findOrderByTradeNo(outTradeNo);
					//查询会员信息
					JsSysMember member = aliPayService.findMemberByLoginCode(order.getVideoImg());
					// 标题
					String title = order.getOutTradeNo();
					// 会员等级
					String grade = member.getMemberGrade();
					// 到期时间
					String overTime = member.getMemberOvertime();
					// 余额
					String balance = member.getReserveField1();
					// 坐席数量
					String seat = member.getReserveDield2();
					//订单类型
					String videoType=null;
					if (title.length() > 2) {
						String lei = title.substring(0, 2);

						if (lei.equals("开通")) {
							videoType="消费";
							String type = title.substring(2, 5);
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							overTime = formatter.format(new Date());
							if (type.equals("基础版")) {
								grade = "1";
								String time = title.substring(5, 7);
								if (time.equals("半年"))
									overTime = TimeUtil.getBanYear(overTime);
								else
									overTime = TimeUtil.getZhenYear(overTime);
							} else if (type.equals("升级版")) {
								grade = "2";
								String time = title.substring(5, 7);
								if (time.equals("半年"))
									overTime = TimeUtil.getBanYear(overTime);
								else
									overTime = TimeUtil.getZhenYear(overTime);
							} else {
								grade = "3";
								String time = title.substring(5, 7);
								if (time.equals("半年"))
									overTime = TimeUtil.getBanYear(overTime);
								else
									overTime = TimeUtil.getZhenYear(overTime);
							}
						} else if (lei.equals("续费")) {
							videoType="消费";
							String type = title.substring(5, 7);
							if (type.equals("半年"))
								overTime = TimeUtil.getBanYear(overTime);
							else
								overTime = TimeUtil.getZhenYear(overTime);
						} else if (lei.equals("升级")) {
							videoType="消费";
							String type = title.substring(2, 5);
							if (type.equals("基础版")) {
								grade="1";
							}else if(type.equals("升级版")) {
								grade="2";
							}else {
								grade="3";
							}
						} else {
						}
					} else {
						String lei = title.substring(0, 2);
						if (lei.equals("充值")) {
							videoType="充值";
							balance = String.format("%.2f",
									(Double.valueOf(balance) + Double.valueOf(order.getTotalFee())));
						}
						else if (lei.equals("坐席")) {
							videoType="消费";
							seat = (Integer.parseInt(seat) + 1) + "";
						}
						else {
						}

					}
					JsSysMember mem = new JsSysMember();
					mem.setSerialNumber(member.getSerialNumber());
					mem.setMemberGrade(grade);
					mem.setMemberOvertime(overTime);
					mem.setReserveField1(balance);
					mem.setReserveDield2(seat);
					// 需要修改订单的信息
					VideoOrder videoOrder = new VideoOrder(order.getOpenid(), 1, new Date(),videoType);

					// 根据商户订单号更新订单
					int nums = videoOrderService.updateOrderAndMember(videoOrder, mem);

					System.out.println(nums);
					// 7、通知微信订单处理成功
					if (nums == 1) {
						System.out.println("微信支付成功！！");
						response.setContentType("text/xml");
						response.getWriter().println("success");
						return;
					}
				}
			}
		}
		// 7、通知微信订单处理失败
		response.setContentType("text/xml");
		response.getWriter().println("fail");

	}

	/**
	 * 用户购买成功跳转接口
	 */
	@ResponseBody
	@RequestMapping(value = "toJump", method = RequestMethod.POST)
	public Integer toJump(Product product) throws Exception {
		System.out.println("跳转查询中！！！！！");
		VideoOrder order = videoOrderService.findByOutTradeNo(product.getOutTradeNo());
		System.out.println(order.getState());
		if (order.getState() == 1) {
			return 1;
		}
		return 0;
	}
}
