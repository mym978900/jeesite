package com.jeesite.modules.test.service.impl;

import java.util.Random;

import javax.servlet.http.Cookie;
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
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.JsJobLocksKey;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsJobLocksMapper;
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
	@Autowired
	private JsJobLocksMapper jsJobLocksMapper;

	@Override
	public Integer toGetMessage(HttpServletRequest req, HttpServletResponse rep,String phone) {
		// TODO Auto-generated method stub
		// HttpServletRequest req = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		DefaultProfile profile = DefaultProfile.getProfile("", "", "");
		IAcsClient client = new DefaultAcsClient(profile);

		String password = new Random().nextInt(899999) + 100000 + "";
		
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "奥力格科技");
        request.putQueryParameter("TemplateCode", "SMS_182860651");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+password+"\"}");
		
		String json = JSONUtils.toJSONString(password);
		JSONObject js = null;
		try {
			CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
			if (response != null && response.getHttpStatus() == 200) {
//				HttpSession session = req.getSession();
				// 使用fastJson从放
//				js = new JSONObject();
//				js.put(phone, password);
//				js.put("createTime", System.currentTimeMillis());
//				// 将验证码存入SESSION
//				session.setAttribute(phone, js);
//				JSONObject json2 = (JSONObject) req.getSession().getAttribute(phone);
				System.out.println("cccccccccccccccccccccc" + password);
				String createTime  = System.currentTimeMillis()+"";
				Cookie cookie = new Cookie("createTime",createTime);//保存账号数据
				Cookie cookie2 = new Cookie("dxpassword",password);//保存账号数据
				cookie.setMaxAge(300);//cookie存在本地的有效时长（单位为妙）默认为-1 表示页面关闭 cookie就失效
				rep.addCookie(cookie);//添加到response中
				rep.addCookie(cookie2);
				
				return 1;
			}
//			 失效时间
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
		boolean boo = PasswordUtil.checkPassword(vo.getPassword(), userVo.getUser().getPassword());
		boolean i = false;
		if (boo == i) {
			return 3;// 密码错误
		}
		JsSysUser user = new JsSysUser();
		user.setLoginCode(vo.getNewphone());
		user.setUserCode(userVo.getUser().getUserCode());
		int num = jsSysUserMapper.updateByPrimaryKeySelective(user);
		JsSysMember mem = new JsSysMember();
		mem.setAccountNumber(vo.getNewphone());
		mem.setSerialNumber(vo.getSerialNumber());
		int now = jsSysMemberMapper.updateByPrimaryKeySelective(mem);
		if (num != 1 || now != 1) {
			return 4;// 绑定失败
		}
		UserUtils.clearCache(userVo.getUser());
		return 5;// 绑定成功
	}

	@Override
	public Integer toUpdatePass(HttpServletResponse response, Model model, UpdatePhoneVo vo) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		boolean boo = PasswordUtil.checkPassword(vo.getPassword(), userVo.getUser().getPassword());
		boolean i = false;
		if (boo == i) {
			return 0;// 密码输入错误
		}
		JsSysUser user = new JsSysUser();
		String newPassword = PasswordUtil.getPassword(vo.getNewpassword());
		user.setPassword(newPassword);
		user.setUserCode(userVo.getUser().getUserCode());
		int num = jsSysUserMapper.updateByPrimaryKeySelective(user);
		if (num != 1) {
			return 2;// 修改失败
		}
		UserUtils.clearCache(userVo.getUser());
		return 1;// 修改成功
	}

	@Override
	public Integer checkUserIsOld(HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		JsSysMember member = jsSysMemberMapper.selectMemberByNumber(userVo.getUser().getLoginCode());
		if (member.getOrganName() == null) {
			return 0;
		}
		return 1;
	}

	@Override
	public JsSysMember getMemberByLoginCode(HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		if(userVo!=null) {
			if(userVo.getUser()!=null) {
				JsSysMember member = jsSysMemberMapper.selectMemberByNumber(userVo.getUser().getLoginCode());
				return member;
			}
		}
		return null;
	}

	@Override
	public Integer toUpdatePassByLogin(GetUserVo userVo, UpdatePhoneVo vo) {
		// TODO Auto-generated method stub
		boolean boo = PasswordUtil.checkPassword(vo.getPassword(), userVo.getUser().getPassword());
		boolean i = false;
		if (boo == i) {
			return 3;// 密码输入错误
		}
		JsSysUser user = new JsSysUser();
		String newPassword = PasswordUtil.getPassword(vo.getNewpassword());
		user.setPassword(newPassword);
		user.setUserCode(userVo.getUser().getUserCode());
		int num = jsSysUserMapper.updateByPrimaryKeySelective(user);
		if (num != 1) {
			return 4;// 修改失败
		}
		UserUtils.clearCache(userVo.getUser());
		return 5;// 修改成功
	}

	@Override
	public void toGetMessageByApply(HttpServletRequest req,JsSysApply apply, String phone) {
		// TODO Auto-generated method stub
		// HttpServletRequest req = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		DefaultProfile profile = DefaultProfile.getProfile("", "", "");
		IAcsClient client = new DefaultAcsClient(profile);

		String password = new Random().nextInt(899999) + 100000 + "";
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "奥力格科技");
        request.putQueryParameter("TemplateCode", "SMS_182682628");
        request.putQueryParameter("TemplateParam", "{\"name\":\""+apply.getApplyName()+"\",\"phone\":\""+apply.getApplyPhone()+"\"}");
		String json = JSONUtils.toJSONString(password);
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
			
			// 失效时间
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer toUpdatePassByForget(GetUserVo userVo, UpdatePhoneVo vo) {
		// TODO Auto-generated method stub
		JsSysUser user = new JsSysUser();
		String newPassword = PasswordUtil.getPassword(vo.getNewpassword());
		user.setPassword(newPassword);
		user.setUserCode(userVo.getUser().getUserCode());
		int num = jsSysUserMapper.updateByPrimaryKeySelective(user);
		if (num != 1) {
			return 3;// 修改失败
		}
//		UserUtils.clearCache(userVo.getUser());
		return 4;// 修改成功
	}

	@Override
	public JsSysUser findUserByLoginCode(String newphone) {
		// TODO Auto-generated method stub
		return jsSysUserMapper.selectByLoginCode(newphone);
	}

	@Override
	public JsJobLocksKey getApplyPhone(String string) {
		// TODO Auto-generated method stub
		return jsJobLocksMapper.selectByPrimaryKey(string);
	}

}
