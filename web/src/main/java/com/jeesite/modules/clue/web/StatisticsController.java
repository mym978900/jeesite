package com.jeesite.modules.clue.web;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.clue.service.StatisticsService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.service.MemberService;

@Controller
@RequestMapping(value = "${adminPath}/statistics")
public class StatisticsController extends BaseController{
	
	@Autowired
	private StatisticsService iStatisticsService;
	
	@Autowired
	private MemberService iMemberService;
	
	//AI外呼统计
	@RequestMapping(value="loginOrganDialStatistics", method = RequestMethod.POST)
	@ResponseBody
	public String loginOrganDialStatistics(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data","会话超时请重新登录..");
			return ServletUtils.renderObject(response, model);
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data","会话超时请重新登录..");
			return ServletUtils.renderObject(response, model);
		}
		//所有通话
		int allCount = iStatisticsService.loginOrganDialStatistics(user.getUserCode(),"");
		//2代表已完成的通话
		int ywcCount = iStatisticsService.loginOrganDialStatistics(user.getUserCode(),"2");
		
		//已完成/所有通话
		model.addAttribute("aiywcall",ywcCount+"/"+allCount);
		
		//接听总量
		int jtCount = iStatisticsService.loginOrganRecivedCount(user.getUserCode());
		
		//统计登陆机构接听总量
		model.addAttribute("aijtcount",jtCount);
		
		//已完成接通数量
		double ywcjtCount = iStatisticsService.loginOrganConnectRate(user.getUserCode(),"0");
		
		double ywcallCount = iStatisticsService.loginOrganConnectRate(user.getUserCode(),"");
		
		
		String jtl="";
		
		String reserveField1 = "";
		
		//统计登陆机构接通率
		if(ywcjtCount != 0 ) {
			DecimalFormat decimalFormat=new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			jtl = decimalFormat.format(Double.valueOf(ywcjtCount/ywcallCount)*100);
			model.addAttribute("aijtl",jtl+"%");
		}else {
			model.addAttribute("aijtl","0.0%");
		}
		
		//通话总时长
		double totalTime = iStatisticsService.loginOrganTotalCallTime(user.getUserCode(),0);
		
		model.addAttribute("aitotaltime",totalTime);
		
		//通话总时长
		String averTime = "";
		
		//平均通话时长
		if(ywcjtCount!=0) {
			DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			averTime = decimalFormat.format(totalTime/ywcjtCount);
		}
		
		model.addAttribute("aiaverTime",averTime);
		
		JsSysMember member = iMemberService.getMemberByAccountCode(user.getUserCode());
		reserveField1 = member.getReserveField1();
		model.addAttribute("reserveField1", reserveField1);
		
		model.addAttribute("result",true);
		
		return ServletUtils.renderObject(response, model);
	}
	
	
	//概况
	@RequestMapping(value="loginOrganAICallCustomCount", method = RequestMethod.POST)
	@ResponseBody
	public String loginOrganAICallCustomCount(HttpServletRequest request, HttpServletResponse response, Model model) {
	
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data","会话超时请重新登录..");
			return ServletUtils.renderObject(response, model);
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data","会话超时请重新登录..");
			return ServletUtils.renderObject(response, model);
		}
		
		int customCount = 0;
		String source = request.getParameter("source");
		StatisticsVo sv;
		//昨日
		if("1".equals(source)) {
			customCount = iStatisticsService.loginOrganAICallCustomCount(user.getUserCode(),1);
		//累计
		}else {
			customCount = iStatisticsService.loginOrganAICallCustomCount(user.getUserCode(),-1);
		}
		
		model.addAttribute("gkcustomCount",customCount);
		
		//统计登陆机构昨日、累计用户意向用户总量
		int intentionCount = 0;
		
		if("1".equals(source)) {
			intentionCount = iStatisticsService.loginOrganTotalIntentionCount(user.getUserCode(),1);
		}else{
			intentionCount = iStatisticsService.loginOrganTotalIntentionCount(user.getUserCode(),-1);
		}
		
		model.addAttribute("gkintentionCount",intentionCount);
		
		//统计登录机构昨日、累计客户接听数量
		int yjtcount = 0;
		
		if("1".equals(source)) {
			yjtcount = iStatisticsService.loginOrganAnswerdCount(user.getUserCode(),1);
		}else{
			yjtcount = iStatisticsService.loginOrganAnswerdCount(user.getUserCode(),0);
		}
		
		model.addAttribute("gkyjtcount",yjtcount);
		
		//统计登录机构通话总时长、话费和剩余话费
		double usingPhoneBill = 0.00;
		double residualPhoneBill = 0.00;
		String reserveField1 = "";
		int totalTime = 0;
		
		totalTime = iStatisticsService.loginOrganTotalCallTime(user.getUserCode(),0);
		usingPhoneBill = iStatisticsService.loginOrganUsingPhoneBill(user.getUserCode(),0);
		
		JsSysMember member = iMemberService.getMemberByAccountCode(user.getUserCode());
		reserveField1 = member.getReserveField1();
		
		model.addAttribute("gktotalTime",totalTime);
		model.addAttribute("gkusingPhoneBill",usingPhoneBill);
		model.addAttribute("gkreserveField1", reserveField1);
		
		//统计登陆机构转化率：意向用户数量/AI外呼总数量
		double customCountChange = 0;
		double intentionCountChange = 0;
		String conversionRate = "";
		List list = new ArrayList();
		JSONObject js;

		Calendar cal= null;
		SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
		String day;
		String dayOfMonth;
		DecimalFormat decimalFormat=new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		for(int i = 6;i>-1;i--) {
			sv = new StatisticsVo();
			cal = Calendar.getInstance();
			customCountChange = iStatisticsService.loginOrganAICallCustomCount(user.getUserCode(),i);
			intentionCountChange = iStatisticsService.loginOrganTotalIntentionCount(user.getUserCode(),i);
			
			cal.add(Calendar.DATE,-i);
	        Date d=cal.getTime();
	        
	        day=sp.format(d);//获取几号
//	        dayOfMonth = day.substring(8);
			
	        if(customCountChange != 0) {
				conversionRate = decimalFormat.format(Double.valueOf(intentionCountChange/customCountChange));;
				sv.setDate(day);
				sv.setConversionRate(conversionRate);
				list.add(sv);
	        }else {
	        	sv.setDate(day);
				sv.setConversionRate("0.0");
	        	list.add(sv);
	        }
		}
		model.addAttribute("gkconversionRate",list);
		
		model.addAttribute("result",true);
		
		return ServletUtils.renderObject(response, model);
	}
	
	
	public static class StatisticsVo{
		private String date;
		private String conversionRate;
		
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getConversionRate() {
			return conversionRate;
		}
		public void setConversionRate(String conversionRate) {
			this.conversionRate = conversionRate;
		}
	}
	
	
}