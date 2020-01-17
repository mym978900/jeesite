package com.jeesite.modules.test.web;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.service.MemberService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.FlowingWaterVo;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

@Controller
@RequestMapping(value = "${adminPath}/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

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
	public FlowingWaterVo selectMeal(@RequestParam("pageNum") Integer pageNum,
			@RequestBody(required = false) FlowingWaterVo vo) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		List<JsSysOrder> orderList = memberService.findOrderByLimit(vo);
		PageInfo<JsSysOrder> page = new PageInfo<JsSysOrder>(orderList);
		return new FlowingWaterVo(vo.getOrganName(), vo.getOrderNum(), vo.getStartTime(), vo.getEndTime(), page, null);

	}

	// 订单流水的查询
	@RequestMapping(value = "selectMoney")
	@ResponseBody
	public FlowingWaterVo selectMoneyByTime(@RequestBody(required = false) FlowingWaterVo vo) {
		BigDecimal decimal = memberService.selectMoneyByTime(vo);
		return new FlowingWaterVo(null, null, vo.getStartTime(), vo.getEndTime(), null, decimal);
	}

	// 申请通过后添加登录用户信息
	@RequestMapping(value = "toInuser")
	@ResponseBody
	public Integer getMessage(HttpServletRequest request, String phone) {

		return memberService.toGetMessage(request, phone);
	}
}
