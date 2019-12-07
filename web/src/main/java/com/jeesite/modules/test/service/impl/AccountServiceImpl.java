package com.jeesite.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.mapper.JsSysApplyMapper;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.service.AccountService;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.MemberVo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private JsSysApplyMapper jsSysApplyMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;

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

		return jsSysApplyMapper.insertSelective(apply);
	}

	@Override
	public Integer insertMember(JsSysMember member) {
		// TODO Auto-generated method stub

		return jsSysMemberMapper.insertSelective(member);
	}

}
