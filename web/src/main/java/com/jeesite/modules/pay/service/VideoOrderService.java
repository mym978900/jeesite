package com.jeesite.modules.pay.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.vo.JumpVo;
import com.jeesite.modules.test.vo.OrderHaveAbilityVo;
import com.jeesite.modules.test.vo.OrderNotHaveAbilityVo;


/**
 * 订单接口
 */
public interface VideoOrderService {

    /**
     * 下单接口
     * @param request 
     * @param model 
     * @param response 
     * @param product
     * @return
     */
    JumpVo save(HttpServletResponse response, Model model, HttpServletRequest request, Product product) throws Exception;


    /**
     * 根据流水号查找订单
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);


    /**
     * 根据流水号更新订单
     * @param videoOrder
     * @return
     */
    int updateVideoOderByOutTradeNo(VideoOrder videoOrder);

    //根据账号查询会员表
	JsSysMember findMemberByLoginCode(String loginCode);


	int updateOrderAndMember(VideoOrder videoOrder, JsSysMember mem);


	Integer updateVideoOrderByRefund(Product product);

	//退款状态查询
	Integer findStateByOpenid(String openid);

	//进行中订单
	List<OrderHaveAbilityVo> findConducttOrder();


	List<OrderNotHaveAbilityVo> findConductOrder();


	List<OrderHaveAbilityVo> findRefundOrder();


	List<OrderHaveAbilityVo> findCompleteOrder();


	Integer toSettlementByOrderNum(String orderNum, String settle);

}
