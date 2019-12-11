package com.jeesite.modules.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;
import com.jeesite.modules.test.service.HelpService;
import com.jeesite.modules.test.vo.AccountVo;

@Controller
@RequestMapping(value = "${adminPath}/help")
public class HelpController {

	@Autowired
	private HelpService helpService;

	// 建议反馈信息添加
	@RequestMapping(value = "insertPp")
	@ResponseBody
	public Integer insertPp(@RequestBody JsSysProposal pro) {

		return helpService.insertProposal(pro);

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
	public Integer deletePpBySerial(@RequestBody String delStr) {

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
}
