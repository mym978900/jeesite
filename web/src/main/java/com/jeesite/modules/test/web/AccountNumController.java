package com.jeesite.modules.test.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsSysApplyMapper;
import com.jeesite.modules.test.service.AccountService;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.MemberVo;

@Controller
@RequestMapping(value = "${adminPath}/Account")
public class AccountNumController {

	@Autowired
	private AccountService accountService;

	// 分页查询申请信息
	@RequestMapping(value = "selectApply")
	@ResponseBody
	public AccountVo getApplyByPage(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestBody(required = false) AccountVo vo) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		List<JsSysApply> applyList = accountService.findApplyByLimit(vo);
		PageInfo<JsSysApply> page = new PageInfo<JsSysApply>(applyList);
		return new AccountVo(vo.getStartTime(), vo.getEndTime(), vo.getFollowState(), pageNum, page);

	}

	// 分页查询会员信息
	@RequestMapping(value = "selectMember")
	@ResponseBody
	public MemberVo getMemberByPage(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestBody(required = false) MemberVo vo) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		List<JsSysMember> memberList = accountService.findMemberByLimit(vo);
		PageInfo<JsSysMember> page = new PageInfo<JsSysMember>(memberList);
		return new MemberVo(vo.getOrganName(), vo.getMemberGrade(), vo.getStartTime(), vo.getEndTime(), pageNum, page);

	}

	// 编辑申请信息
	@RequestMapping(value = "updateApply")
	@ResponseBody
	public Integer updateApplyByKey(@RequestBody JsSysApply apply) {
		Integer num = accountService.updateApplyByKey(apply);
		if (num != 1) {
			return 0;// 编辑失败
		}
		return 1;// 成功

	}

	// 编辑会员信息
	@RequestMapping(value = "updateMember")
	@ResponseBody
	public Integer updateMemberByKey(@RequestBody JsSysMember member) {
		Integer num = accountService.updateMemberByKey(member);
		if (num != 1) {
			return 0;// 编辑失败
		}
		return 1;// 成功

	}

	// 添加申请信息
	@RequestMapping(value = "insertApply")
	@ResponseBody
	public Integer insertApply(@RequestBody JsSysApply apply) {
		Integer num = accountService.insertApply(apply);
		if (num != 1) {
			return 0;// 添加失败
		}
		return 1;// 成功

	}

	// 添加会员信息
	@RequestMapping(value = "insertMember")
	@ResponseBody
	public Integer insertMember(@RequestBody JsSysMember member) {
		Integer num = accountService.insertMember(member);
		if (num != 1) {
			return 0;// 添加失败
		}
		return 1;// 成功

	}
}