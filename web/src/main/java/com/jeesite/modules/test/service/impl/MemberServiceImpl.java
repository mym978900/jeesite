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
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
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
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;
	
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

	//获取需要匹配线索的会员
		@Override
		public List<JsSysMember> getClueMatchUser() {
			List<JsSysMember> list = jsSysMemberMapper.getClueMatchUser();
			if(list !=null && !list.isEmpty()) {
				return list;
			}
			return null;
		}

		//获取机构品类
		@Override
		public String getDeptType(String loginCode) {
			String deptType = jsSysMemberMapper.getDeptType(loginCode);
			return deptType;
		}

		//更新初次匹配时间
		@Override
		public void updateOnedate(Date date,String userCode) {
			jsSysMemberMapper.updateOnedate(date,userCode);
		}

		//更新用户最新匹配批次
		@Override
		public void updateAiTimes(String userCode, int times) {
			jsSysMemberMapper.updateAiTimes(userCode,times);
		}

		//获取未标注经纬度的会员
		@Override
		public List<JsSysMember> getNoConfigAddress() {
			List<JsSysMember> list = jsSysMemberMapper.getNoConfigAddress();
			if(list !=null && !list.isEmpty()) {
				return list;
			}
			return null;
		}

		@Override
		public void updateByPrimaryKey(JsSysMember jsm) {
			jsSysMemberMapper.updateByPrimaryKey(jsm);
		}

		@Override
		public JsSysMember getMemberByAccountCode(String code) {
			JsSysMember jsm = jsSysMemberMapper.getMemberByAccountCode(code);
			return jsm;
		}


}
