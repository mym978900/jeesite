package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.service.HelpService;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.SeatAndMealVo;

@Controller
@RequestMapping(value = "${adminPath}/help")
public class HelpController {

	@Autowired
	private HelpService helpService;

	// 建议反馈信息添加
	@RequestMapping(value = "insertPp")
	@ResponseBody
	public Integer insertPp(
			@RequestBody(required = false) JsSysProposal pro, HttpServletResponse response, Model model) {

		return helpService.insertProposal(pro, response, model);

	}

	// 建议反馈信息查看
	@RequestMapping(value = "selectPp")
	@ResponseBody
	public AccountVo selectAllPpByPage(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum) {

		return helpService.selectAllPpByPage(pageNum);

	}

	// 建议反馈信息删除
	@RequestMapping(value = "deletePp")
	@ResponseBody
	public Integer deletePpBySerial(String delStr) {

		return helpService.deletePpBySerial(delStr);
	}

	// 常见问题的添加
	@RequestMapping(value = "insertPro")
	@ResponseBody
	public Integer insertProblem(@RequestBody JsSysProblem problem) {

		return helpService.insertProblem(problem);
	}

	// 常见问题的修改
	@RequestMapping(value = "updatePro")
	@ResponseBody
	public Integer updateProblem(@RequestBody JsSysProblem problem) {

		return helpService.updateProblem(problem);
	}

	// 常见问题信息查看
	@RequestMapping(value = "selectPro")
	@ResponseBody
	public AccountVo selectAllProByPage(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum) {

		return helpService.selectAllProByPage(pageNum);

	}

	// 常见问题的删除
	@RequestMapping(value = "deletePro")
	@ResponseBody
	public Integer deleteProblem(String delStr) {

		return helpService.deleteProblem(delStr);
	}

	// 费用管理信息展示
	@RequestMapping(value = "selectSmeal")
	@ResponseBody
	public SeatAndMealVo selectSeatAndMeal() {

		return helpService.selectSeatAndMeal();

	}

	// 坐席的修改
	@RequestMapping(value = "updateSeat")
	@ResponseBody
	public Integer updateSeat(@RequestBody JsSysSeat seat) {

		return helpService.updateSeat(seat);
	}

	// 服务套餐的添加
	@RequestMapping(value = "insertMeal")
	@ResponseBody
	public Integer insertMeal(@RequestBody JsSysSetmeal meal) {

		return helpService.insertMeal(meal);
	}

	// 服务套餐的修改
	@RequestMapping(value = "updateMeal")
	@ResponseBody
	public Integer updateMeal(@RequestBody JsSysSetmeal meal) {

		return helpService.updateMeal(meal);
	}

	// 服务套餐信息删除
	@RequestMapping(value = "deleteMeal")
	@ResponseBody
	public Integer deleteMealByPkey(String delStr) {

		return helpService.deleteMealByPkey(delStr);
	}
	
}
