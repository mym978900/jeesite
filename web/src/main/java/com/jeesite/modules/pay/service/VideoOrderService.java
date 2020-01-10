package com.jeesite.modules.pay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.vo.JumpVo;


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

}
