package com.jeesite.modules.test.service;

import java.util.List;

import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.MemberVo;

public interface AccountService {

	List<JsSysApply> findApplyByLimit(AccountVo vo);

	List<JsSysMember> findMemberByLimit(MemberVo vo);

	Integer updateApplyByKey(JsSysApply apply);

	Integer updateMemberByKey(JsSysMember member);

	Integer insertApply(JsSysApply apply);

	Integer insertMember(JsSysMember member);

	
}
