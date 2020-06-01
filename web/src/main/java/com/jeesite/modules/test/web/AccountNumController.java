package com.jeesite.modules.test.web;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsJobLocksKey;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsSysApplyMapper;
import com.jeesite.modules.test.service.AccountService;
import com.jeesite.modules.test.service.TestMessageService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.MemberVo;

@Controller
@RequestMapping(value = "${adminPath}/account")
public class AccountNumController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private TestMessageService messageService;

	// 分页查询申请信息
	@RequestMapping(value = "findApply")
	@ResponseBody
	public AccountVo getApplyByPage(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestBody(required = false) AccountVo vo) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		if (vo.getStartEndTime() != null && vo.getStartEndTime().length > 0) {
			vo.setStartTime(vo.getStartEndTime()[0]);
			vo.setEndTime(vo.getStartEndTime()[1]);
		}
		List<JsSysApply> applyList = accountService.findApplyByLimit(vo);
		PageInfo<JsSysApply> page = new PageInfo<JsSysApply>(applyList);
		return new AccountVo(vo.getStartTime(), vo.getEndTime(), vo.getFollowState(), pageNum, page);

	}

	// 分页查询会员信息
	@RequestMapping(value = "findMember")
	@ResponseBody
	public MemberVo getMemberByPage(
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestBody(required = false) MemberVo vo) {
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		if (vo.getStartEndTime() != null && vo.getStartEndTime().length > 0) {
			vo.setStartTime(vo.getStartEndTime()[0]);
			vo.setEndTime(vo.getStartEndTime()[1]);
		}
		List<JsSysMember> memberList = accountService.findMemberByLimit(vo);
		for (JsSysMember jsSysMember : memberList) {
			String[] arr = jsSysMember.getOrganAddress().split(",");
			jsSysMember.setOrganAddressArr(arr);
		}
		PageInfo<JsSysMember> page = new PageInfo<JsSysMember>(memberList);
		return new MemberVo(vo.getOrganName(), vo.getMemberGrade(), vo.getStartTime(), vo.getEndTime(), pageNum, page);

	}

	// 查询登录用户机构会员信息
	@RequestMapping(value = "findMemberByCode")
	@ResponseBody
	public JsSysMember getMemberByPage(HttpServletResponse response, Model model) {
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		if(userVo!=null) {
			JsSysMember member = accountService.selectMemberByNumber(userVo.getUser().getLoginCode());
			//级联市和区
			if(member!=null) {
				String[] arr = member.getOrganAddress().split(",");
				member.setOrganAddressArr(arr);
			}
			return member;
		}
		return null;
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
	public Integer updateMemberByKey(JsSysMember member) {
		//级联市和区
		if(member!=null) {
			String[] arr = member.getOrganAddressArr();
			member.setOrganAddress(arr[0]+","+arr[1]);
		}
		Integer num = accountService.updateMemberByKey(member);
		if (num != 1) {
			return 0;// 编辑失败
		}
		return 1;// 成功

	}

	// 添加申请信息
	@RequestMapping(value = "insertApply")
	@ResponseBody
	public Integer insertApply(HttpServletRequest request,JsSysApply apply) {
		Integer num = accountService.insertApply(apply);
		if (num != 1) {
			return 0;// 添加失败
		}
//		messageService.toGetMessageByApply(request, apply,"17633603265");
		messageService.toGetMessageByApply(request, apply,"17701337353");
		return 1;// 成功

	}

	// 添加会员信息
	@RequestMapping(value = "insertMember")
	@ResponseBody
	public Integer insertMember(JsSysMember member, HttpServletResponse response, Model model) {
		//级联市和区
		if(member!=null) {
			String[] arr = member.getOrganAddressArr();
			member.setOrganAddress(arr[0]+","+arr[1]);
		}
		Integer num = accountService.insertMember(member, response, model);
		if (num != 1) {
			return 0;// 添加失败
		}
		return 1;// 成功

	}
}
