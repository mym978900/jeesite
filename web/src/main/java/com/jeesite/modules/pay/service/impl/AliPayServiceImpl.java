package com.jeesite.modules.pay.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.jeesite.modules.pay.utils.CommonUtil;
import com.jeesite.modules.pay.utils.CommonUtils;
import com.jeesite.modules.pay.utils.Constants;
import com.jeesite.modules.pay.utils.IpUtils;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.mapper.VideoOrderMapper;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.tr.entity.NeedTime;
import com.jeesite.modules.tr.entity.TrOrder;
import com.jeesite.modules.tr.mapper.TrOrderMapper;
import com.jeesite.modules.tr.service.TrService;
import com.jeesite.modules.clue.utils.ClueUtils;
import com.jeesite.modules.pay.config.AliPayConfig;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.pay.service.IAliPayService;

@Service
public class AliPayServiceImpl implements IAliPayService {
	private static final Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

	@Value("${alipay_notify_url}")
	private String notify_url;
	@Autowired
	private VideoOrderMapper videoOrderMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;
	@Autowired
	private TrOrderMapper trOrderMapper;

	@Override
	public String aliPay(Product product) {
		logger.info("订单号：{}生成支付宝支付码", product.getOutTradeNo());
		String message = Constants.SUCCESS;
		// 二维码存放路径
		System.out.println(Constants.QRCODE_PATH);
		String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + product.getOutTradeNo() + ".png";
		String outTradeNo = product.getOutTradeNo();
		String subject = product.getSubject();
		String totalAmount = CommonUtil.divide(product.getTotalFee(), "100").toString();
		// 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
		String sellerId = "";
		// (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
		String storeId = "test_store_id";
		// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId("2088100200300400500");
		// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
		String body = product.getBody();
		// 支付超时，定义为120分钟
		String timeoutExpress = "120m";
		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder().setSubject(subject)
				.setTotalAmount(totalAmount).setOutTradeNo(outTradeNo).setSellerId(sellerId).setBody(body)// 128长度
																											// --附加信息
				.setStoreId(storeId).setExtendParams(extendParams).setTimeoutExpress(timeoutExpress)
				.setNotifyUrl(notify_url);// 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置

		AlipayF2FPrecreateResult result = AliPayConfig.getAlipayTradeService().tradePrecreate(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			logger.info("支付宝预下单成功: )");

			AlipayTradePrecreateResponse response = result.getResponse();
			dumpResponse(response);
			ZxingUtils.getQRCodeImge(response.getQrCode(), 256, imgPath);
			break;

		case FAILED:
			logger.info("支付宝预下单失败!!!");
			message = Constants.FAIL;
			break;

		case UNKNOWN:
			logger.info("系统异常，预下单状态未知!!!");
			message = Constants.FAIL;
			break;

		default:
			logger.info("不支持的交易状态，交易返回异常!!!");
			message = Constants.FAIL;
			break;
		}
		return message;
	}

	// 简单打印应答
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
			}
			logger.info("body:" + response.getBody());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String aliRefund(Product product) {
		logger.info("订单号：" + product.getOutTradeNo() + "支付宝退款");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 返回值标识
		String message = Constants.SUCCESS;
		// 外部订单号，需要退款交易的商户外部订单号
		String outTradeNo = product.getOutTradeNo();
		// 退款金额，该金额必须小于等于订单的支付金额，单位为元
		String refundAmount = product.getTotalFee();
		// 退款原因，可以说明用户退款原因，方便为商家后台提供统计
		String refundReason = product.getBody();
		// 查询出订单信息
		VideoOrder videoOrder = videoOrderMapper.selectByPrimaryKey(outTradeNo);
		// 再次判断是否已退款
		if (videoOrder.getState() == 3) {
			return message = Constants.FAIL;
		}
		// 获取订单 及退款金额
		Double total = Double.valueOf(videoOrder.getTotalFee());
		Double retreat = Double.valueOf(refundAmount);
		// 判断是余额支付
		if ("余额支付".equals(videoOrder.getNickname())) {
			// 编辑订单信息
			VideoOrder order = new VideoOrder();
			order.setOpenid(outTradeNo);
			// 判断退款金额
			if (retreat < total) {
				order.setDel(1);
				order.setTotalFee(String.format("%.2f",
						(Double.valueOf(videoOrder.getTotalFee()) - Double.valueOf(refundAmount))));
			} else if (retreat.equals(total)) {
				order.setState(3);
				order.setReserve1(sdf.format(new Date()));
				order.setVideoId(refundAmount);
				order.setUserId(refundReason);
			} else {
				return message = Constants.FAIL;
			}
			if (retreat < total) {
				// 添加部分退款信息
				VideoOrder orders = new VideoOrder(videoOrder.getOpenid(), videoOrder.getOutTradeNo(), 3,
						videoOrder.getCreateTime(), videoOrder.getNotifyTime(), videoOrder.getTotalFee(),
						videoOrder.getNickname(), videoOrder.getHeadImg(), refundAmount, videoOrder.getVideoTitle(),
						videoOrder.getVideoImg(), refundReason, videoOrder.getIp(), 3, sdf.format(new Date()),
						videoOrder.getReserve2(), videoOrder.getReserve3(), videoOrder.getTrPaycommodity(),
						videoOrder.getTrPartbusercode());
				videoOrderMapper.insertSelective(orders);
			}
			// 编辑订单信息
			if (retreat.equals(total)) {
				videoOrderMapper.updateByPrimaryKeySelective(order);
			} else {
				videoOrderMapper.updateByOpenIdAndStateOne(order);
			}
			// 查询机构
			JsSysMember members = jsSysMemberMapper.getMemberByAccountCode(videoOrder.getHeadImg());
			// 编辑机构余额
			JsSysMember member = new JsSysMember();
			member.setUserCode(videoOrder.getHeadImg());
			member.setReserveField1(
					String.format("%.2f", (Double.valueOf(members.getReserveField1()) + Double.valueOf(refundAmount))));
			// 编辑会员余额
			int updateTwo = jsSysMemberMapper.updateMemberByUserCode(member);
			if (updateTwo == 1) {
				return message = Constants.SUCCESS;
			} else {
				return message = Constants.FAIL;
			}
		}
		// 支付宝退款
		// 部分退款
		String outRequestNo = new Random().nextInt(999999999) + "";
		// 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
		// String storeId = "2088731863898636";
		String storeId = "2088102178199444";
		// 创建退款请求builder，设置请求参数
		AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder().setOutTradeNo(outTradeNo)
				.setRefundAmount(refundAmount).setRefundReason(refundReason).setOutRequestNo(outRequestNo)
				.setStoreId(storeId);
		AlipayF2FRefundResult result = AliPayConfig.getAlipayTradeService().tradeRefund(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			logger.info("支付宝退款成功: )");
			VideoOrder order = new VideoOrder();
			order.setOpenid(outTradeNo);
			if (retreat < total) {
				order.setDel(1);
				order.setTotalFee(String.format("%.2f",
						(Double.valueOf(videoOrder.getTotalFee()) - Double.valueOf(refundAmount))));
			} else if (retreat.equals(total)) {
				order.setState(3);
				order.setReserve1(sdf.format(new Date()));
				order.setVideoId(refundAmount);
				order.setUserId(refundReason);
			} else {
				return message = Constants.FAIL;
			}
			if (retreat < total) {
				// 添加部分退款信息
				VideoOrder orders = new VideoOrder(videoOrder.getOpenid(), videoOrder.getOutTradeNo(), 3,
						videoOrder.getCreateTime(), videoOrder.getNotifyTime(), videoOrder.getTotalFee(),
						videoOrder.getNickname(), videoOrder.getHeadImg(), refundAmount, videoOrder.getVideoTitle(),
						videoOrder.getVideoImg(), refundReason, videoOrder.getIp(), 3, sdf.format(new Date()),
						videoOrder.getReserve2(), videoOrder.getReserve3(), videoOrder.getTrPaycommodity(),
						videoOrder.getTrPartbusercode());
				videoOrderMapper.insertSelective(orders);
			}
			if (retreat.equals(total)) {
				int updateNum = videoOrderMapper.updateByPrimaryKeySelective(order);
				if (updateNum == 0) {
					return message = Constants.CURRENT_USER;
				}
				break;
			} else {
				int updateNum = videoOrderMapper.updateByOpenIdAndStateOne(order);
				if (updateNum == 0) {
					return message = Constants.CURRENT_USER;
				}
				break;
			}

		case FAILED:
			logger.info("支付宝退款失败!!!");
			message = Constants.FAIL;
			break;

		case UNKNOWN:
			logger.info("系统异常，订单退款状态未知!!!");
			message = Constants.FAIL;
			break;

		default:
			logger.info("不支持的交易状态，交易返回异常!!!");
			message = Constants.FAIL;
			break;
		}
		return message;
	}

	/**
	 * 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。
	 * 预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。
	 * 当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。 =====只有支付成功后
	 * 调用此订单才可以=====
	 */
	@Override
	public String aliCloseorder(Product product) {
		logger.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单");
		String message = Constants.SUCCESS;
		try {
			String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + "alipay_" + product.getOutTradeNo()
					+ ".png";
			File file = new File(imgPath);
			if (file.exists()) {
				AlipayClient alipayClient = AliPayConfig.getAlipayClient();
				AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
				request.setBizContent("{" + "    \"out_trade_no\":\"" + product.getOutTradeNo() + "\"" + "  }");
				AlipayTradeCloseResponse response = alipayClient.execute(request);
				if (response.isSuccess()) {// 扫码未支付的情况
					logger.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单成功并删除支付二维码");
					file.delete();
				} else {
					if ("ACQ.TRADE_NOT_EXIST".equals(response.getSubCode())) {
						logger.info("订单号：" + product.getOutTradeNo() + response.getSubMsg() + "(预下单 未扫码的情况)");
					} else if ("ACQ.TRADE_STATUS_ERROR".equals(response.getSubCode())) {
						logger.info("订单号：" + product.getOutTradeNo() + response.getSubMsg());
					} else {
						logger.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单失败" + response.getSubCode()
								+ response.getSubMsg());
						message = Constants.FAIL;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = Constants.FAIL;
			logger.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单异常");
		}
		return message;
	}

	@Override
	public String downloadBillUrl(String billDate, String billType) {
		logger.info("获取支付宝订单地址:" + billDate);
		String downloadBillUrl = "";
		try {
			AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
			request.setBizContent("{" + "    \"bill_type\":\"trade\"," + "    \"bill_date\":\"2016-12-26\"" + "  }");

			AlipayDataDataserviceBillDownloadurlQueryResponse response = AliPayConfig.getAlipayClient()
					.execute(request);
			if (response.isSuccess()) {
				logger.info("获取支付宝订单地址成功:" + billDate);
				downloadBillUrl = response.getBillDownloadUrl();// 获取下载地
			} else {
				logger.info("获取支付宝订单地址失败" + response.getSubMsg() + ":" + billDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取支付宝订单地址异常:" + billDate);
		}
		return downloadBillUrl;
	}

	@Override
	public String aliPayMobile(Product product) {
		logger.info("支付宝手机支付下单");
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		String returnUrl = "回调地址 http 自定义";
		alipayRequest.setReturnUrl(returnUrl);// 前台通知
		alipayRequest.setNotifyUrl(notify_url);// 后台回调
		JSONObject bizContent = new JSONObject();
		bizContent.put("out_trade_no", product.getOutTradeNo());
		bizContent.put("total_amount", product.getTotalFee());// 订单金额:元
		bizContent.put("subject", product.getSubject());// 订单标题
		bizContent.put("seller_id", Configs.getPid());// 实际收款账号，一般填写商户PID即可
		bizContent.put("product_code", "QUICK_WAP_PAY");// 手机网页支付
		bizContent.put("body", "两个苹果五毛钱");
		String biz = bizContent.toString().replaceAll("\"", "'");
		alipayRequest.setBizContent(biz);
		logger.info("业务参数:" + alipayRequest.getBizContent());
		String form = Constants.FAIL;
		try {
			form = AliPayConfig.getAlipayClient().pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			logger.error("支付宝构造表单失败", e);
		}
		return form;
	}

	@Override
	public String aliPayPc(HttpServletResponse response, Model model, HttpServletRequest request,Product product) {
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		
		logger.info("支付宝PC支付下单");
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//		String returnUrl = "http://www.aolige.cn/apis/js/a/alipay/frontRcvResponse";
		String returnUrl = "http://28x3836517.goho.co:20895/js/a/alipay/frontRcvResponse";
		alipayRequest.setReturnUrl(returnUrl);// 前台通知
//		alipayRequest.setNotifyUrl("http://www.aolige.cn/apis/js/a/alipay/pay");// 后台回调
		alipayRequest.setNotifyUrl("http://28x3836517.goho.co:20895/js/a/alipay/pay");// 后台回调
		JSONObject bizContent = new JSONObject();
		String outTradeNo = CommonUtils.generateOrder("3", userVo.getUser().getLoginCode());
		bizContent.put("out_trade_no", outTradeNo);
		bizContent.put("total_amount", Double.valueOf(product.getTotalFee())+"");// 订单金额:元
		bizContent.put("subject", product.getSubject());// 订单标题
		bizContent.put("seller_id", Configs.getPid());// 实际收款账号，一般填写商户PID即可
		bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");// 电脑网站支付
		bizContent.put("body", userVo.getUser().getLoginCode());
		/**
		 * 这里有三种模式可供选择 如果在系统内支付，并且是弹出层支付，建议选择模式二、其他模式会跳出当前iframe(亲测有效)
		 */
		bizContent.put("qr_pay_mode", "2");
		// 创建订单
		
		VideoOrder order = null;
		if("0".equals(product.getProductId())) {
			order = new VideoOrder(outTradeNo, product.getSubject(), 0, new Date(), null,
				product.getTotalFee(), "支付宝", userVo.getUser().getUserCode(), null, null, userVo.getUser().getLoginCode(), null,
				IpUtils.getIpAddr(request), 0);
		}else if("1".equals(product.getProductId())) {
			TrOrder to = product.getTrOrder();
			//需求时间
			NeedTime[] needTime;
			//具体需求时间
			NeedTime nt;
			//全量日期集合
			List<Date> alldatelist = new ArrayList<Date>();
			List<Date> alldatelistasc;
			List alldatelistasource = new ArrayList();
			List alldatelistascformat = new ArrayList();
			//全量时间集合
			List alltimelist = new ArrayList();
			Object[] alltimelistasc;
			List alltimelistascint = new ArrayList();
			List allimelistasource = new ArrayList();
			needTime = to.getNeedTime();
			String[] timearr;
			double value = 0;
			double begintime;
			double endtime;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(needTime!=null && needTime.length>0) {
				for(int i=0;i<needTime.length;i++) {
					nt = needTime[i];
					timearr = nt.getTimeArr();
					alldatelist.add(nt.getNeedDate());
					alldatelistasource.add(nt.getNeedDate());
					allimelistasource.add(timearr[0]);
					allimelistasource.add(timearr[1]);
					alltimelist.add(Integer.parseInt(timearr[0].replace(":", "")));
					alltimelist.add(Integer.parseInt(timearr[1].replace(":", "")));
//					begintime = Integer.parseInt(timearr[0].split(":")[0])*3600+Integer.parseInt(timearr[0].split(":")[1])*60 + 0.00;
//					endtime = Integer.parseInt(timearr[1].split(":")[0])*3600+Integer.parseInt(timearr[1].split(":")[1])*60 + 0.00;
//					value += endtime - begintime; 
				}
				//计算时长
				//利用BigDecimal来实现四舍五入.保留一位小数
//				double result = new BigDecimal(value/3600.00).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				//1代表保留1位小数,保留两位小数就是2,依此累推
		        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
//				to.setTrDuration(product.getTrOrder().getTrDuration());
				System.out.println("alldatelistasource---------------"+alldatelistasource.toString());
				//原始日期
				to.setTrNeeddatelist(JSON.toJSONString(alldatelistasource));
				//日期做升序
				alldatelistasc = ClueUtils.getd(alldatelist);
//				for(int i=0;i<alldatelistasc.size();i++) {
//					alldatelistascformat.add(sdf.format(alldatelistasc.get(i)));
//				}
//				System.out.println("alldatelistascformat---------------"+alldatelistascformat.toString());
//				tns.setTrNeeddatelistasc(JSON.toJSONString(alldatelistasc));
				//最小需求日期
				to.setTrNeedbegindate(alldatelistasc.get(0));
				//最大需求日期
				if(alldatelist.size()>1) {
					to.setTrNeedenddate(alldatelistasc.get(alldatelistasc.size()-1));
				}else {
					to.setTrNeedenddate(alldatelistasc.get(0));
				}
				System.out.println("allimelistasource---------------"+allimelistasource.toString());
				//原始时间
				to.setTrNeedtimelist(JSON.toJSONString(allimelistasource));
				//时间做升序
				alltimelistasc = ClueUtils.gett(alltimelist.toArray());
				if(alltimelistasc != null && alltimelistasc.length>0) {
					for(int i=0;i<alltimelistasc.length;i++) {
						alltimelistascint.add(Integer.parseInt(alltimelistasc[i].toString()));
					}
				}
				System.out.println("alltimelistasc---------------"+alltimelistascint.toString());
//				tns.setTrNeedtimelistasc(alltimelistascint.toString());
				//最小开始时间
				to.setTrBegintime(Integer.parseInt(alltimelistasc[0].toString()));
				//最大开始时间
				if(alltimelistasc.length>1) {
					to.setTrEndtime(Integer.parseInt(alltimelistasc[alltimelistasc.length-1].toString()));
				}else {
					to.setTrEndtime(Integer.parseInt(alltimelistasc[0].toString()));
				}
				trOrderMapper.insert(to);
			}
			
			order = new VideoOrder();
			order.setOpenid(outTradeNo);
			order.setOutTradeNo(product.getSubject());
			order.setState(0);
			order.setCreateTime(new Date());
			order.setTotalFee(product.getTotalFee());
			order.setNickname("支付宝");
			order.setHeadImg(userVo.getUser().getUserCode());
			order.setVideoImg(userVo.getUser().getLoginCode());
			order.setIp(IpUtils.getIpAddr(request));
			order.setTrPaycommodity("1");
			order.setReserve2(to.getNeedId());
			order.setReserve3(to.getTrCommonditycode());
			order.setTrPartbusercode(to.getPartBCode());
			
		}
		int num=videoOrderMapper.insertSelective(order);
		if (num!=1) {
			return "服务器异常，订单创建失败";
		}
		String biz = bizContent.toString().replaceAll("\"", "'");
		alipayRequest.setBizContent(biz);
		logger.info("业务参数:" + alipayRequest.getBizContent());
		String form = Constants.FAIL;
		try {
			form = AliPayConfig.getAlipayClient().pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			logger.error("支付宝构造表单失败", e);
		}
		return form;
	}

	@Override
	public String appPay(Product product) {
		String orderString = Constants.FAIL;
		// 实例化客户端
		AlipayClient alipayClient = AliPayConfig.getAlipayClient();
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(product.getBody());
		model.setSubject(product.getSubject());
		model.setOutTradeNo(product.getOutTradeNo());
		model.setTimeoutExpress("30m");
		model.setTotalAmount(product.getTotalFee());
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl("商户外网可以访问的异步地址");
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			orderString = response.getBody();// 就是orderString 可以直接给客户端请求，无需再做处理。
			// System.out.println(response.getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return orderString;
	}

	@Override
	public boolean rsaCheckV1(Map<String, String> params) {
		// 验证签名 校验签名
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8", "RSA2");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return signVerified;
	}

	@Override
	public boolean rsaCheckV2(Map<String, String> params) {
		// 验证签名 校验签名
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "UTF-8");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return signVerified;
	}

	@Override
	public VideoOrder findOrderByTradeNo(String outtradeno) {
		// TODO Auto-generated method stub
		
		return videoOrderMapper.findOrderByTradeNo(outtradeno);
	}

	@Override
	public JsSysMember findMemberByLoginCode(String loginCode) {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.selectMemberByNumber(loginCode);
	}

	@Transactional
	@Override
	public void updateOrderAndMember(VideoOrder ord, JsSysMember mem) {
		// TODO Auto-generated method stub
		videoOrderMapper.updateByPrimaryKeySelective(ord);
		jsSysMemberMapper.updateByPrimaryKeySelective(mem);
	}
}
