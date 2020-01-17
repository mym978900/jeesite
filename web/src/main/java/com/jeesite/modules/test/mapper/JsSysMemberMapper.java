package com.jeesite.modules.test.mapper;

import java.util.Date;
import java.util.List;

import com.jeesite.modules.base.BaseMapper;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.vo.MemberVo;

public interface JsSysMemberMapper extends BaseMapper<JsSysMember>{
    
    
    List<JsSysMember> selectAllMember(MemberVo vo);
    
    //获取需要匹配线索的会员
    List<JsSysMember> getClueMatchUser();

    //获取机构品类
	String getDeptType(String loginCode);

	//更新初次匹配时间
	void updateOnedate(Date date,String userCode);

	//更新用户最新匹配批次
	void updateAiTimes(String userCode, int times);

	//获取未标注经纬度的会员
	List<JsSysMember> getNoConfigAddress();

	//通过登录账号获取会员信息
	JsSysMember getMemberByAccountCode(String userCode);

	JsSysMember selectMemberByNumber(String loginCode);

	Integer findMemberNum();

	Integer findMemberNumByMonth();

	Integer findClueNum();

	Integer findClueNumByMonth();

}