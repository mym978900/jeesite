package com.jeesite.modules.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsSysApplyMapper;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.mapper.JsSysUserMapper;
import com.jeesite.modules.test.service.AccountService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.MemberVo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private JsSysApplyMapper jsSysApplyMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;
	@Autowired
	private JsSysUserMapper jsSysUserMapper;

	@Override
	public List<JsSysApply> findApplyByLimit(AccountVo vo) {
		// TODO Auto-generated method stub

		return jsSysApplyMapper.selectAllApply(vo);
	}

	@Override
	public List<JsSysMember> findMemberByLimit(MemberVo vo) {
		// TODO Auto-generated method stub

		return jsSysMemberMapper.selectAllMember(vo);
	}

	@Override
	public Integer updateApplyByKey(JsSysApply apply) {
		// TODO Auto-generated method stub

		return jsSysApplyMapper.updateByPrimaryKeySelective(apply);
	}

	@Override
	public Integer updateMemberByKey(JsSysMember member) {
		// TODO Auto-generated method stub

		return jsSysMemberMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public Integer insertApply(JsSysApply apply) {
		// TODO Auto-generated method stub
		apply.setApplyTime(new Date());
		apply.setFollowState("0");
		apply.setSerialNumber(DailyUtil.getUuid());
		return jsSysApplyMapper.insertSelective(apply);
	}

	@Override
	public Integer insertMember(JsSysMember member, HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		member.setAccountNumber(userVo.getUser().getLoginCode());
		member.setSerialNumber(DailyUtil.getUuid());
		member.setMemberGrade("0");
		member.setReserveField1("0");
		member.setReserveDield2("1");
		member.setMemberCreatetime(new Date());
		member.setMemberOvertime("今日");
		JsSysUser user = jsSysUserMapper.selectByLoginCode(userVo.getUser().getLoginCode());
		member.setUserCode(user.getUserCode());
		return jsSysMemberMapper.insertSelective(member);
	}

	@Override
	public JsSysMember selectMemberByNumber(String loginCode) {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.selectMemberByNumber(loginCode);
	}

}
