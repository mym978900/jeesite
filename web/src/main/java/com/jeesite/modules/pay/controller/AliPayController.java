package com.jeesite.modules.pay.controller;

import io.swagger.annotations.Api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.pay.service.IAliPayService;
import com.jeesite.modules.pay.utils.TimeUtil;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.tr.service.TrService;
import com.jeesite.modules.pay.config.AliPayConfig;

/**
 * 支付宝支付
 */
@Api(tags = "支付宝支付")
@Controller
@RequestMapping(value = "${adminPath}/alipay")
public class AliPayController {
	private static final Logger logger = LoggerFactory.getLogger(AliPayController.class);
	@Autowired
	private IAliPayService aliPayService;
	@Autowired
	private TrService iTrService;
	// 支付宝PC支付
	@RequestMapping(value = "pcPay", method = RequestMethod.POST)
	public void pcPay(
			@RequestBody(required = false)Product product, ModelMap map, HttpServletResponse response, Model model,
			HttpServletRequest request) throws IOException {
		logger.info("PC支付");
		String form = aliPayService.aliPayPc(response, model, request, product);
		System.out.println(form);
		// map.addAttribute("form", form);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(form);

	}

	// 支付宝异步回调
	@RequestMapping(value = "pay", method = RequestMethod.POST)
	public void alipay_notify(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String message = "success";
		Map<String, String> params = new HashMap<String, String>();
		// 取出所有参数是为了验证签名
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			params.put(parameterName, request.getParameter(parameterName));
		}
		// 验证签名 校验签名
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "utf-8", "RSA2");
			// 各位同学这里可能需要注意一下,2018/01/26
			// 以后新建应用只支持RSA2签名方式，目前已使用RSA签名方式的应用仍然可以正常调用接口，注意下自己生成密钥的签名算法
			// signVerified = AlipaySignature.rsaCheckV1(params,
			// Configs.getAlipayPublicKey(), "UTF-8","RSA2");
			// 有些同学通过 可能使用了这个API导致验签失败，特此说明一下
			// signVerified = AlipaySignature.rsaCheckV2(params,
			// Configs.getAlipayPublicKey(), "UTF-8");//正式环境
		} catch (AlipayApiException e) {
			e.printStackTrace();
			message = "failed";
		}
		if (signVerified) {
			logger.info("支付宝验证签名成功！");
			// 若参数中的appid和填入的appid不相同，则为异常通知
			if (!Configs.getAppid().equals(params.get("app_id"))) {
				logger.info("与付款时的appid不同，此为异常通知，应忽略！");
				message = "failed";
			} else {
				String outtradeno = params.get("out_trade_no");
				String totalFee = params.get("total_amount");
				System.out.println(totalFee);
				// 在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
				VideoOrder order = aliPayService.findOrderByTradeNo(outtradeno);
				System.out.println("outtradeno"+outtradeno);
				if (!(order.getTotalFee()).equals(totalFee)) {
					return;
				}
				//System.out.println(String.format("%.2f",Double.valueOf(order.getTotalFee())).toString());
				String status = params.get("trade_status");
				if (status.equals("WAIT_BUYER_PAY")) { // 如果状态是正在等待用户付款
					logger.info(outtradeno + "订单的状态正在等待用户付款");
				} else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
					logger.info(outtradeno + "订单的状态已经关闭");
				} else if (status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
					if("0".equals(order.getTrPaycommodity())) {
						logger.info("(支付宝订单号:" + outtradeno + "付款成功)");
						// 这里 根据实际业务场景 做相应的操作
						// 当前登录用户信息
						//GetUserVo userVo = DailyUtil.getLoginUser(response, model);
						// 会员信息
						String body = params.get("body");
						System.out.println("body"+body);
						JsSysMember member = aliPayService.findMemberByLoginCode(body);
						
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
							// String time=title.substring(2, 4);
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
							} else if(lei.equals("升级")){
								videoType="消费";
								String type = title.substring(2, 5);
								if (type.equals("基础版")) {
									grade="1";
								}else if(type.equals("升级版")) {
									grade="2";
								}else {
									grade="3";
								}
							}else {}
						} else {
							String lei = title.substring(0, 2);
							if (lei.equals("充值")){
								videoType="充值";
								balance = String.format("%.2f",(Double.valueOf(balance) + Double.valueOf(order.getTotalFee())));
							}else if(lei.equals("坐席")) {
								videoType="消费";
								seat = (Integer.parseInt(seat) + 1) + "";
							}
							 else {}
	
						}
						JsSysMember mem = new JsSysMember();
						mem.setSerialNumber(member.getSerialNumber());
						mem.setMemberGrade(grade);
						mem.setMemberOvertime(overTime);
						mem.setReserveField1(balance);
						mem.setReserveDield2(seat);
						// 需要修改订单的信息
						VideoOrder ord = new VideoOrder(order.getOpenid(), 1, new Date(),videoType);
						aliPayService.updateOrderAndMember(ord, mem);
					}else if("1".equals(order.getTrPaycommodity())) {
						//订单类型
						String videoType="消费";
						VideoOrder ord = new VideoOrder(order.getOpenid(), 1, new Date(),videoType);
						iTrService.updateVideoOrder(ord);
					}
				} else {
				}
			}
		} else { // 如果验证签名没有通过
			message = "failed";
			logger.info("验证签名失败！");
		}
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(message.getBytes());
		out.flush();
		out.close();
	}

	// 支付宝支付PC端前台回调
	@ResponseBody
	@RequestMapping("/frontRcvResponse")
	public String frontRcvResponse(HttpServletRequest request,HttpServletResponse response) {
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 商户订单号
			String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 前台回调验证签名 v1 or v2
			boolean signVerified = aliPayService.rsaCheckV1(params);
			if (signVerified) {
				logger.info("订单号" + orderNo + "验证签名结果[成功].");
				// 处理业务逻辑
			} else {
				logger.info("订单号" + orderNo + "验证签名结果[失败].");
			}
			//重定向到费用中心页面
//			response.sendRedirect("http://www.aolige.cn/#/Expense");
			response.sendRedirect("http://28x3836517.goho.co:20895/#/DemandOrder");
		} catch (Exception e) {
			e.printStackTrace();
			// 处理异常信息
		}
		// 支付成功、跳转到成功页面
		return "abc";
	}

}
