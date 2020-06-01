package com.jeesite.modules.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.jeesite.modules.pay.config.WxPayConfig;
import com.jeesite.modules.pay.mapper.UserMapper;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.pay.model.User;
import com.jeesite.modules.pay.model.Video;
import com.jeesite.modules.pay.service.VideoOrderService;
import com.jeesite.modules.pay.utils.CommonUtils;
import com.jeesite.modules.pay.utils.HttpUtils;
import com.jeesite.modules.pay.utils.IpUtils;
import com.jeesite.modules.pay.utils.VideoOrderDto;
import com.jeesite.modules.pay.utils.WXPayUtil;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.mapper.VideoOrderMapper;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.JumpVo;
import com.jeesite.modules.test.vo.OrderHaveAbilityVo;
import com.jeesite.modules.test.vo.OrderNotHaveAbilityVo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

	@Autowired
	private WxPayConfig weChatConfig;
	@Autowired
	private VideoOrderMapper videoOrderMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JumpVo save(HttpServletResponse response, Model model, HttpServletRequest request,Product product) throws Exception {
		//当前登录用户
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		// 3、生成订单，插入数据库
		VideoOrder order = new VideoOrder();
		String openid = CommonUtils.generateOrder("2", userVo.getUser().getLoginCode());
		order.setOpenid(openid);
		order.setOutTradeNo(product.getSubject());
		order.setState(0);
		order.setCreateTime(new Date());
		order.setTotalFee(product.getTotalFee());
		order.setNickname("微信");
		order.setHeadImg(userVo.getUser().getUserCode());
		order.setIp(IpUtils.getIpAddr(request));
		order.setDel(0);
		order.setVideoImg(userVo.getUser().getLoginCode());
		
		videoOrderMapper.insertSelective(order);

		// 4、获取codeurl
		String codeUrl = unifiedOrder(response,model,order);
		JumpVo vo=new JumpVo();
		vo.setCodeUrl(codeUrl);
		vo.setOpenid(openid);

		return vo;
	}

	/**
	 * 统一下单方法
	 * 
	 * @return
	 */
	private String unifiedOrder(HttpServletResponse response, Model model,VideoOrder videoOrder) throws Exception {
		
		//当前登录用户
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		// 4.1、生成签名 按照开发文档需要按字典排序，所以用SortedMap
		SortedMap<String, String> params = new TreeMap<>();
		params.put("appid", weChatConfig.getAppId()); // 公众账号ID
		params.put("mch_id", weChatConfig.getMchId()); // 商户号
		params.put("nonce_str", CommonUtils.generateUUID()); // 随机字符串
		params.put("body", videoOrder.getOutTradeNo()); // 商品描述
		params.put("out_trade_no", videoOrder.getOpenid());// 商户订单号,商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
		params.put("total_fee", (int)Math.floor(Double.valueOf(videoOrder.getTotalFee()))+"");// 标价金额 分
		params.put("spbill_create_ip", videoOrder.getIp());
		params.put("notify_url", weChatConfig.getPayCallbackUrl()); // 通知地址
		params.put("trade_type", "NATIVE"); // 交易类型 JSAPI 公众号支付 NATIVE 扫码支付 APP APP支付

		// 4.2、sign签名 具体规则:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3
		String sign = WXPayUtil.createSign(params, weChatConfig.getKey());
		params.put("sign", sign);

		// 4.3、map转xml （ WXPayUtil工具类）
		String payXml = WXPayUtil.mapToXml(params);

		// 4.4、回调微信的统一下单接口(HttpUtil工具类）
		String orderStr = HttpUtils.doPost(WxPayConfig.getUnifiedOrderUrl(), payXml, 4000);
		if (null == orderStr) {
			return null;
		}
		// 4.5、xml转map （WXPayUtil工具类）
		Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
		System.out.println(unifiedOrderMap.toString());

		// 4.6、获取最终code_url
		if (unifiedOrderMap != null) {
			return unifiedOrderMap.get("code_url");
		}

		return null;
	}

	@Override
	public VideoOrder findByOutTradeNo(String outTradeNo) {

		return videoOrderMapper.findOrderByTradeNo(outTradeNo);
	}

	@Override
	public int updateVideoOderByOutTradeNo(VideoOrder videoOrder) {

		return videoOrderMapper.updateByPrimaryKeySelective(videoOrder);
	}

	@Override
	public JsSysMember findMemberByLoginCode(String loginCode) {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.selectMemberByNumber(loginCode);
	}

	@Transactional
	@Override
	public int updateOrderAndMember(VideoOrder videoOrder, JsSysMember mem) {
		// TODO Auto-generated method stub
		int num1=videoOrderMapper.updateByPrimaryKeySelective(videoOrder);
		int num2=jsSysMemberMapper.updateByPrimaryKeySelective(mem);
		if (num1==1&&num2==1) {
			return 1;
		}
		return 0;
	}

	@Override
	public Integer updateVideoOrderByRefund(Product product) {
		// TODO Auto-generated method stub
		VideoOrder order = videoOrderMapper.selectByState(product.getOutTradeNo());
		if (order != null) {
			BigDecimal orderFee = new BigDecimal(order.getTotalFee());
			BigDecimal refundFee = new BigDecimal(product.getTotalFee());
			if (refundFee.intValue() > orderFee.intValue()) {
				return 1;// 退款金额大于订单金额
			}
			order.setVideoId(product.getTotalFee());
			order.setUserId(product.getBody());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setReserve1(sdf.format(new Date()));
			order.setState(2);
			int num = videoOrderMapper.updateByPrimaryKeySelective(order);
			if (num != 1) {
				return 2;// 失败
			}
			return 3;// 退款中
		} else
			return 0;// 无该订单或未支付成功
	}

	@Override
	public Integer findStateByOpenid(String openid) {
		// TODO Auto-generated method stub
		VideoOrder order = videoOrderMapper.selectByPrimaryKey(openid);
		return order.getState();
	}

	@Override
	public List<OrderNotHaveAbilityVo> findConductOrder() {
		// TODO Auto-generated method stub
		return videoOrderMapper.findConductOrder();
	}

	@Override
	public List<OrderHaveAbilityVo> findConducttOrder() {
		// TODO Auto-generated method stub

		return videoOrderMapper.findSettlementOrder();
	}

	@Override
	public List<OrderHaveAbilityVo> findRefundOrder() {
		// TODO Auto-generated method stub
		return videoOrderMapper.findRefundOrder();
	}

	@Override
	public List<OrderHaveAbilityVo> findCompleteOrder() {
		// TODO Auto-generated method stub
		return videoOrderMapper.findCompleteOrder();
	}

	@Override
	public Integer toSettlementByOrderNum(String orderNum,String settle) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		VideoOrder order=new VideoOrder();
		order.setOpenid(orderNum);
		order.setState(2);
		order.setVideoId(settle);
		order.setReserve1(sdf.format(new Date()));
		return videoOrderMapper.updateByPrimaryKeySelectiveAndDel(order);
	}

}
