package com.jeesite.modules.pay.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.vo.CostVo;

public interface CostService {

	List<JsSysSetmeal> findAllMeal();

	JsSysMember findBalanceByNum(String loginCode);

	List<VideoOrder> findOrderByLimit(CostVo vo);

	JsSysSeat findSeat(String i);

	String insertPaymentByBalance(Product product, HttpServletRequest request, HttpServletResponse response,
			Model model);

	Integer toBalancePayment(Product product, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException;

	String getUpGradeMoney(Product product, HttpServletResponse response, Model model) throws ParseException;

}
