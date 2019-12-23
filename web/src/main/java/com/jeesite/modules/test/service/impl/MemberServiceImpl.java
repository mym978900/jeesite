package com.jeesite.modules.test.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.mapper.JsSysOfficeMapper;
import com.jeesite.modules.test.mapper.JsSysOrderMapper;
import com.jeesite.modules.test.service.MemberService;
import com.jeesite.modules.test.vo.FlowingWaterVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private JsSysOfficeMapper jsSysOfficeMapper;
	@Autowired
	private JsSysOrderMapper jsSysOrderMapper;
	
	@Override
	public JsSysOffice getOffice(User user) {
		// TODO Auto-generated method stub
		List<JsSysOffice> officeList=jsSysOfficeMapper.selectByUser(user);
		return officeList.get(0);
	}

	@Override
	public int updateOffice(JsSysOffice office) {
		// TODO Auto-generated method stub
		
		return jsSysOfficeMapper.updateByPrimaryKeySelective(office);
	}

	@Override
	public List<JsSysOrder> findOrderByLimit(FlowingWaterVo vo) {
		// TODO Auto-generated method stub
		
		return jsSysOrderMapper.findOrderByLimit(vo);
	}

	@Override
	public BigDecimal selectMoneyByTime(FlowingWaterVo vo) {
		// TODO Auto-generated method stub
		return jsSysOrderMapper.selectMoneyByTime(vo);
	}

	@Override
	public List<JsSysMember> getClueMatchUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeptType(String loginCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOnedate(Date date, String userCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAiTimes(String userCode, int times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JsSysMember> getNoConfigAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByPrimaryKey(JsSysMember jsm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsSysMember getMemberByAccountCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
