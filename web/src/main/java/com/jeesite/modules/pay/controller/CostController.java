package com.jeesite.modules.pay.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.pay.service.CostService;
import com.jeesite.modules.pay.utils.CommonUtils;
import com.jeesite.modules.pay.utils.IpUtils;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.service.AccountService;
import com.jeesite.modules.test.service.TestMessageService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.util.DataStatisticsUtil;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.AitaskBackVo;
import com.jeesite.modules.test.vo.AitaskVo;
import com.jeesite.modules.test.vo.CostVo;
import com.jeesite.modules.test.vo.DataStatisticsVo;
import com.jeesite.modules.test.vo.GetUserVo;

@Controller
@RequestMapping(value = "${adminPath}/cost")
public class CostController {

	@Autowired
	private CostService costService;

	// 费用中心套餐余额等上半部分
	@ResponseBody
	@RequestMapping(value = "costlist")
	public CostVo getApplyByPage(HttpServletResponse response, Model model) {
		// 登录对象
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		// 套餐列表
		List<JsSysSetmeal> meallist = costService.findAllMeal();
		// 会员信息
		JsSysMember member = costService.findBalanceByNum(userVo.getUser().getLoginCode());
		// 坐席信息
		JsSysSeat seat = costService.findSeat("1");
		CostVo coVo = new CostVo(meallist, member, seat);
		return coVo;
	}

	/**
	 * 登录用户订单查询功能
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "getOrder")
	public CostVo getOrderByLimit(CostVo costVo, HttpServletResponse response, Model model) {
		// 登录对象
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		//
		if (!"".equals(costVo.getStartTime()) && costVo.getStartTime() != null && !"".equals(costVo.getEndTime())
				&& costVo.getEndTime() != null) {
			costVo.setStartTime(costVo.getStartTime() + " 00:00:00");
			costVo.setEndTime(costVo.getEndTime() + " 23:59:59");
		}
		// 订单列表
		Integer pageNum = costVo.getPageNum();
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 8);
		// 查询全部
		costVo.setUserCode(userVo.getUser().getUserCode());
		List<VideoOrder> orderList = costService.findOrderByLimit(costVo);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (VideoOrder videoOrder : orderList) {
			String day = sdf1.format(videoOrder.getCreateTime());
			videoOrder.setVideoImg(day);
		}
		PageInfo<VideoOrder> page = new PageInfo<VideoOrder>(orderList);
		CostVo coVo = new CostVo(pageNum, costVo.getStartTime(), costVo.getEndTime(), costVo.getCostType(), page);
		return coVo;
	}

	/**
	 * 余额支付功能
	 * 
	 * @param 金额
	 * @param 标题
	 * @param model
	 * @return 订单创建信息
	 */
	@ResponseBody
	@RequestMapping(value = "toPayment")
	public String toBalancePayment(Product product, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String openid = costService.insertPaymentByBalance(product, request, response, model);
		return openid;
	}

	/**
	 * 余额支付功能
	 * 
	 * @param 订单号
	 * @param 密码
	 * @param (Product)
	 * @return 支付成功或失败0123
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "payment")
	public Integer BalancePayment(Product product, HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {

		Integer nums = costService.toBalancePayment(product, request, response, model);
		return nums;
	}

	/**
	 * 获取升级金额
	 * 
	 * @param 级别
	 * @return 支付成功或失败0123
	 */
	@ResponseBody
	@RequestMapping(value = "upgrade")
	public Product getUpGradeMoney(Product product, HttpServletResponse response, Model model) throws ParseException {

		Product pro = costService.getUpGradeMoney(product, response, model);
		return pro;
	}

	// 数据统计
	@RequestMapping(value = "dateStat")
	@ResponseBody
	public DataStatisticsVo getDateStatistics() {

		List<AitaskBackVo> dayVo = DataStatisticsUtil.getDayStatistics();
		List<AitaskBackVo> monthVo = DataStatisticsUtil.getMonthStatistics();
		Integer memberNum = costService.findMemberNum();
		Integer memberNumMonth = costService.findMemberNumByMonth();
		Integer clueNum = costService.findClueNum();
		Integer clueNumMonth = costService.findClueNumByMonth();
		DataStatisticsVo vo = new DataStatisticsVo(memberNum, memberNumMonth, clueNum, clueNumMonth, dayVo, monthVo);
		return vo;

	}
}
