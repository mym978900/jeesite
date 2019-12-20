package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.vo.MemberVo;

public interface JsSysMemberMapper {
    int deleteByPrimaryKey(String serialNumber);

    int insert(JsSysMember record);

    int insertSelective(JsSysMember record);

    JsSysMember selectByPrimaryKey(String serialNumber);

    int updateByPrimaryKeySelective(JsSysMember record);

    int updateByPrimaryKey(JsSysMember record);

	List<JsSysMember> selectAllMember(MemberVo vo);

	JsSysMember selectMemberByNumber(String loginCode);
}