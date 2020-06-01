package com.jeesite.modules.test.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.pay.service.VideoOrderService;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.tr.entity.NeedTime;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.service.MemberService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.FlowingWaterVo;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.OrderHaveAbilityVo;
import com.jeesite.modules.test.vo.OrderNotHaveAbilityVo;
import com.jeesite.modules.test.vo.TeachersOrderVo;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

@Controller
@RequestMapping(value = "${adminPath}/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private VideoOrderService videoOrderService;

	// 获取机构信息
	@RequestMapping(value = "selectMem")
	@ResponseBody
	public JsSysOffice getOfficeByUser(HttpServletResponse response, HttpServletRequest request, Model model) {
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);

		if (userVo.getUser() != null) {
			return memberService.getOffice(userVo.getUser());
		}
		return null;
	}

	// 编辑机构信息
	@RequestMapping(value = "updateMem")
	@ResponseBody
	public int updateOffice(@RequestBody JsSysOffice office) {

		int affectNum = memberService.updateOffice(office);
		if (affectNum == 1) {
			return 1;
		}
		return 0;

	}

	// 订单流水的查询
	@RequestMapping(value = "selectMeal")
	@ResponseBody
	public FlowingWaterVo selectMeal(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum, FlowingWaterVo vo) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (vo.getOrganName() != null && vo.getOrganName() != "") {
			JsSysMember mem = memberService.findMemberByOrganName(vo.getOrganName());
			if (mem == null) {
				pageNum = pageNum == null ? 1 : pageNum;
				PageHelper.startPage(pageNum, 10);
				// 查询全部
				if (vo.getStartEndTime() != null && vo.getStartEndTime().length > 0) {
					vo.setStartTime(vo.getStartEndTime()[0]);
					vo.setEndTime(vo.getStartEndTime()[1]);
				}
				List<VideoOrder> orderList = memberService.findOrderByLimit(vo);
				if (orderList != null && !orderList.isEmpty()) {
					for (VideoOrder videoOrder : orderList) {
						JsSysMember member = memberService.getMemberByAccountCode(videoOrder.getHeadImg());
						videoOrder.setHeadImg(member.getOrganName());
						String day = sdf1.format(videoOrder.getCreateTime());
						videoOrder.setVideoImg(day);
					}
				}
				PageInfo<VideoOrder> page = new PageInfo<VideoOrder>(orderList);
				return new FlowingWaterVo(vo.getOrganName(), vo.getOrderNum(), vo.getStartTime(), vo.getEndTime(), page,
						null);
			} else
				vo.setOrganName(mem.getUserCode());
		}
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		if (vo.getStartEndTime() != null && vo.getStartEndTime().length > 0) {
			vo.setStartTime(vo.getStartEndTime()[0]);
			vo.setEndTime(vo.getStartEndTime()[1]);
		}
		List<VideoOrder> orderList = memberService.findOrderByLimit(vo);
		if (orderList != null && !orderList.isEmpty()) {
			for (VideoOrder videoOrder : orderList) {
				JsSysMember member = memberService.getMemberByAccountCode(videoOrder.getHeadImg());
				videoOrder.setHeadImg(member.getOrganName());
				String day = sdf1.format(videoOrder.getCreateTime());
				videoOrder.setVideoImg(day);
			}
		}
		PageInfo<VideoOrder> page = new PageInfo<VideoOrder>(orderList);
		return new FlowingWaterVo(vo.getOrganName(), vo.getOrderNum(), vo.getStartTime(), vo.getEndTime(), page, null);

	}

	// 订单流水的查询
	@RequestMapping(value = "selectMoney")
	@ResponseBody
	public FlowingWaterVo selectMoneyByTime(FlowingWaterVo vo) {
		if (vo.getStartEndTime() != null && vo.getStartEndTime().length > 0) {
			vo.setStartTime(vo.getStartEndTime()[0]);
			vo.setEndTime(vo.getStartEndTime()[1]);
		}
		BigDecimal decimal = memberService.selectMoneyByTime(vo);
		return new FlowingWaterVo(null, null, vo.getStartTime(), vo.getEndTime(), null, decimal);
	}

	// 申请通过后添加登录用户信息
	@RequestMapping(value = "toInuser")
	@ResponseBody
	public Integer getMessage(HttpServletRequest request, String phone) {

		return memberService.toGetMessage(request, phone);
	}

	// 师资共享 进行中订单
	@RequestMapping(value = "conductOrder")
	@ResponseBody
	public TeachersOrderVo FindConductOrderNoHaveAbility(@RequestParam("pageNum") Integer pageNum) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 6);
		// 查询全部
		List<OrderNotHaveAbilityVo> orderList = videoOrderService.findConductOrder();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NeedTime[] needTime = null;
		NeedTime nt = null;
		String[] timearr = null;
		if (orderList != null && !orderList.isEmpty()) {
			for (OrderNotHaveAbilityVo videoOrder : orderList) {
				List<Date> trNeeddatelist1 = JSONArray.parseArray(videoOrder.getTrNeeddatelist(), Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(videoOrder.getTrNeedtimelist(), String.class);
				if (trNeeddatelist1.size() > 0) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for (int i = 0; i < trNeeddatelist1.size(); i++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(i));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(i * 2);
						timearr[1] = trNeedtimelist1.get(i * 2 + 1);
						nt.setTimeArr(timearr);
						needTime[i] = nt;
					}
					videoOrder.setNeedTime(needTime);
				}
				String day = sdf1.format(videoOrder.getCreateTime());
				videoOrder.setMationTime(day);
			}
		}
		PageInfo<OrderNotHaveAbilityVo> page = new PageInfo<OrderNotHaveAbilityVo>(orderList);
		return new TeachersOrderVo(pageNum, page, null);

	}

	// 师资共享 待结算
	@RequestMapping(value = "settlementOrder")
	@ResponseBody
	public TeachersOrderVo FindSettlementOrderHavaAbility(@RequestParam("pageNum") Integer pageNum) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 6);
		// 查询全部
		List<OrderHaveAbilityVo> orderList = videoOrderService.findConducttOrder();
		if (orderList != null && !orderList.isEmpty()) {
			for (OrderHaveAbilityVo videoOrder : orderList) {
				videoOrder.setIp(
						(int) (Math.floor(Double.valueOf(videoOrder.getTotalFee())) * 0.9) + ".00");
			}
		}
		NeedTime[] needTime = null;
		NeedTime nt = null;
		String[] timearr = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (orderList != null && !orderList.isEmpty()) {
			for (OrderHaveAbilityVo videoOrder : orderList) {
				List<Date> trNeeddatelist1 = JSONArray.parseArray(videoOrder.getTrNeeddatelist(), Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(videoOrder.getTrNeedtimelist(), String.class);
				if (trNeeddatelist1.size() > 0) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for (int i = 0; i < trNeeddatelist1.size(); i++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(i));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(i * 2);
						timearr[1] = trNeedtimelist1.get(i * 2 + 1);
						nt.setTimeArr(timearr);
						needTime[i] = nt;
					}
					videoOrder.setNeedTime(needTime);
				}
				String day = sdf1.format(videoOrder.getCreateTime());
				videoOrder.setVideoImg(day);
			}
		}
		PageInfo<OrderHaveAbilityVo> page = new PageInfo<OrderHaveAbilityVo>(orderList);
		return new TeachersOrderVo(pageNum, null, page);

	}

	// 师资共享 待退款
	@RequestMapping(value = "refundOrder")
	@ResponseBody
	public TeachersOrderVo FindRefundOrderHavaAbility(@RequestParam("pageNum") Integer pageNum) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 6);
		// 查询全部
		List<OrderHaveAbilityVo> orderList = videoOrderService.findRefundOrder();
		NeedTime[] needTime = null;
		NeedTime nt = null;
		String[] timearr = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (orderList != null && !orderList.isEmpty()) {
			for (OrderHaveAbilityVo videoOrder : orderList) {
				List<Date> trNeeddatelist1 = JSONArray.parseArray(videoOrder.getTrNeeddatelist(), Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(videoOrder.getTrNeedtimelist(), String.class);
				if (trNeeddatelist1.size() > 0) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for (int i = 0; i < trNeeddatelist1.size(); i++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(i));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(i * 2);
						timearr[1] = trNeedtimelist1.get(i * 2 + 1);
						nt.setTimeArr(timearr);
						needTime[i] = nt;
					}
					videoOrder.setNeedTime(needTime);
				}
				String day = sdf1.format(videoOrder.getCreateTime());
				videoOrder.setVideoImg(day);
			}
		}
		PageInfo<OrderHaveAbilityVo> page = new PageInfo<OrderHaveAbilityVo>(orderList);
		return new TeachersOrderVo(pageNum, null, page);

	}

	// 师资共享 已完成
	@RequestMapping(value = "completeOrder")
	@ResponseBody
	public TeachersOrderVo FindCompleteOrderHavaAbility(@RequestParam("pageNum") Integer pageNum) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 6);
		// 查询全部
		List<OrderHaveAbilityVo> orderList = videoOrderService.findCompleteOrder();
		NeedTime[] needTime = null;
		NeedTime nt = null;
		String[] timearr = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (orderList != null && !orderList.isEmpty()) {
			for (OrderHaveAbilityVo videoOrder : orderList) {
				List<Date> trNeeddatelist1 = JSONArray.parseArray(videoOrder.getTrNeeddatelist(), Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(videoOrder.getTrNeedtimelist(), String.class);
				if (trNeeddatelist1.size() > 0) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for (int i = 0; i < trNeeddatelist1.size(); i++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(i));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(i * 2);
						timearr[1] = trNeedtimelist1.get(i * 2 + 1);
						nt.setTimeArr(timearr);
						needTime[i] = nt;
					}
					videoOrder.setNeedTime(needTime);
				}
				String day = sdf1.format(videoOrder.getCreateTime());
				videoOrder.setVideoImg(day);
			}
		}
		PageInfo<OrderHaveAbilityVo> page = new PageInfo<OrderHaveAbilityVo>(orderList);
		return new TeachersOrderVo(pageNum, null, page);

	}  

	// 师资订单结算

	@RequestMapping(value = "tosettlement")
	@ResponseBody
	public Integer tosettlement(String orderNum,String settle) {

		return videoOrderService.toSettlementByOrderNum(orderNum,settle);
	}

}
