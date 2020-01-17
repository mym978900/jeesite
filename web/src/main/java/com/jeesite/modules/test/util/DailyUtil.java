package com.jeesite.modules.test.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.vo.GetUserVo;

public class DailyUtil {

	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static GetUserVo getLoginUser(HttpServletResponse response, Model model) {
		// 获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if (loginInfo == null) {
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data", "会话超时请重新登录..");
			return new GetUserVo(ServletUtils.renderObject(response, model), null);
		}

		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null) {
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data", "会话超时请重新登录..");
			return new GetUserVo(ServletUtils.renderObject(response, model), null);
		}

		return new GetUserVo(null, user);
	}

	public static String getOrderIdByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			result += random.nextInt(10);
		}
		return newDate + result;
	}

	public static String getDayAgo(int day) {
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		calendar1.add(Calendar.DATE, -day);
		String three_days_ago = sdf1.format(calendar1.getTime());
		return three_days_ago;
	}

	public static String getMonthAgo(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -i);
		Date m = c.getTime();
		return sdf.format(m);
	}
}
