package com.jeesite.modules.test.service.impl;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.mapper.JsSysUserMapper;
import com.jeesite.modules.test.service.TestMessageService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.util.PasswordUtil;
import com.jeesite.modules.test.util.SHA1Util;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.UpdatePhoneVo;

@Service
public class TestMessageServiceImpl implements TestMessageService {

	@Autowired
	private JsSysUserMapper jsSysUserMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;

	@Override
	public Integer toGetMessage(HttpServletRequest req,String phone) {
		// TODO Auto-generated method stub
		//HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIIxKfL09legx7",
				"fbsGtBZaAxDTLM1nwOSpPWDrlZJ1dm");
		IAcsClient client = new DefaultAcsClient(profile);

		String password = new Random().nextInt(899999) + 100000 + "";
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "default");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("SignName", "\u4fee\u914d\u8fde");
		request.putQueryParameter("TemplateCode", "SMS_172884080");
		request.putQueryParameter("TemplateParam", "{\"num\":\"" + password + "\"}");
		String json = JSONUtils.toJSONString(password);
		JSONObject js = null;
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
//			if (response != null && response.getHttpStatus() == 200) {
//				HttpSession session = req.getSession();
//				// 使用fastJson从放
//				js = new JSONObject();
//				js.put("password", password);
//				js.put("createTime", System.currentTimeMillis());
//				// 将验证码存入SESSION
//				session.setAttribute("password", js);
//				JSONObject json2 = (JSONObject) req.getSession().getAttribute("password");
//				System.out.println("cccccccccccccccccccccc"+json2.getString("password"));
//				return 1;
//			}

			// 失效时间
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer toUpdatePhone(HttpServletResponse response, Model model, UpdatePhoneVo vo) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		boolean boo=PasswordUtil.checkPassword(vo.getPassword(), userVo.getUser().getPassword());
		boolean i=false;
		if (boo==i) {
			return 3;//密码错误
		}
		JsSysUser user = new JsSysUser();
		user.setLoginCode(vo.getNewphone());
		user.setUserCode(userVo.getUser().getUserCode());
		int num = jsSysUserMapper.updateByPrimaryKeySelective(user);
		JsSysMember mem=new JsSysMember();
		mem.setAccountNumber(vo.getNewphone());
		mem.setSerialNumber(vo.getSerialNumber());
		int now=jsSysMemberMapper.updateByPrimaryKeySelective(mem);
		if (num != 1||now !=1) {
			return 4;//绑定失败
		}
		return 5;//绑定成功
	}

	@Override
	public Integer toUpdatePass(HttpServletResponse response, Model model, UpdatePhoneVo vo) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		boolean boo=PasswordUtil.checkPassword(vo.getPassword(), userVo.getUser().getPassword());
		boolean i=false;
		if (boo==i) {
			return 0;// 密码输入错误
		}
		JsSysUser user = new JsSysUser();
		String newPassword=PasswordUtil.getPassword(vo.getNewpassword());
		user.setPassword(newPassword);
		user.setUserCode(userVo.getUser().getUserCode());
		int num = jsSysUserMapper.updateByPrimaryKeySelective(user);
		if (num != 1) {
			return 2;// 修改失败
		}
		return 1;// 修改成功
	}

	@Override
	public Integer checkUserIsOld(HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		JsSysMember member=jsSysMemberMapper.selectMemberByNumber(userVo.getUser().getLoginCode());
		if (member==null) {
			return 0;
		}
		return 1;
	}

	@Override
	public JsSysMember getMemberByLoginCode(HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		JsSysMember member=jsSysMemberMapper.selectMemberByNumber(userVo.getUser().getLoginCode());
		return member;
	}

}
