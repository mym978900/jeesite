package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.base.BaseMapper;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.vo.MemberVo;

public interface JsSysMemberMapper extends BaseMapper<JsSysMember>{
    
    
    List<JsSysMember> selectAllMember(MemberVo vo);
}