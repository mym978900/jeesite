package com.jeesite.modules.test.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jeesite.modules.pay.utils.CommonUtils;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.mapper.JsSysOfficeMapper;
import com.jeesite.modules.test.mapper.JsSysOrderMapper;
import com.jeesite.modules.test.mapper.JsSysUserMapper;
import com.jeesite.modules.test.mapper.VideoOrderMapper;
import com.jeesite.modules.test.service.MemberService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.util.PasswordUtil;
import com.jeesite.modules.test.vo.FlowingWaterVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private JsSysOfficeMapper jsSysOfficeMapper;
	@Autowired
	private JsSysOrderMapper jsSysOrderMapper;
	@Autowired
	private JsSysUserMapper jsSysUserMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;
	@Autowired
	private VideoOrderMapper videoOrderMapper;

	@Override
	public JsSysOffice getOffice(User user) {
		// TODO Auto-generated method stub
		List<JsSysOffice> officeList = jsSysOfficeMapper.selectByUser(user);
		return officeList.get(0);
	}

	@Override
	public int updateOffice(JsSysOffice office) {
		// TODO Auto-generated method stub

		return jsSysOfficeMapper.updateByPrimaryKeySelective(office);
	}

	@Override
	public List<VideoOrder> findOrderByLimit(FlowingWaterVo vo) {
		// TODO Auto-generated method stub

		return videoOrderMapper.findOrderByLimitBackStage(vo);
	}

	@Override
	public BigDecimal selectMoneyByTime(FlowingWaterVo vo) {
		// TODO Auto-generated method stub
		return videoOrderMapper.selectMoneyByTime(vo);
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer toGetMessage(HttpServletRequest req, String phone) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// HttpServletRequest req = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		DefaultProfile profile = DefaultProfile.getProfile("", "",
				"");
		IAcsClient client = new DefaultAcsClient(profile);

		String password = new Random().nextInt(899999) + 100000 + "";
		String pass=PasswordUtil.getPassword(password);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "奥力格科技");
        request.putQueryParameter("TemplateCode", "SMS_182682180");
        request.putQueryParameter("TemplateParam", "{\"phone\":\""+phone+"\",\"password\":\""+password+"\"}");
		String json = JSONUtils.toJSONString(password);
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
			if (response != null && response.getHttpStatus() == 200) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String uuid = CommonUtils.generateUUID();
				JsSysUser user = new JsSysUser(uuid, phone, phone, pass, "0", "0", "system", new Date());
				int num = jsSysUserMapper.insertSelective(user);
				if (num == 1) {
					JsSysMember member = new JsSysMember();
					member.setAccountNumber(phone);
					member.setSerialNumber(DailyUtil.getUuid());
					member.setMemberGrade("0");
					member.setReserveField1("0");
					member.setReserveDield2("1");
					member.setMemberCreatetime(new Date());
					member.setMemberOvertime(sdf1.format(new Date()));
					member.setOrganAddress("无,无");
					JsSysUser user2 = jsSysUserMapper.selectByLoginCode(phone);
					member.setUserCode(user2.getUserCode());
					int num2 = jsSysMemberMapper.insertSelective(member);
					if (num2 == 1) {
						return 1;
					}
					return 0;
				} else {
					return 0;
				}
			}
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public JsSysMember findMemberByOrganName(String organName) {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.findMemberByOrganName(organName);
	}

}
