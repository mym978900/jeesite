package com.jeesite.modules.clue.web;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.clue.service.StatisticsService;
import com.jeesite.modules.clue.service.UpAitaskService;
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
	
	@Autowired
	private UpAitaskService iUpAitaskService;
	
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
		LinkedHashMap hm = new LinkedHashMap();
		//所有通话1代表进行中，空代表所有
		int allCount = 0;
		List list1 = new ArrayList();
		list1 = iStatisticsService.loginOrganDialStatistics(user.getUserCode(),"3","");
		if(list1!=null && !list1.isEmpty()) {
			hm = (LinkedHashMap) list1.get(0);
			allCount = Integer.valueOf(hm.get("count").toString());
		}
		//2代表已完成的通话
		int ywcCount = 0;
		List list2 = new ArrayList();
		list2 = iStatisticsService.loginOrganDialStatistics(user.getUserCode(),"3","2");
		if(list2!=null && !list2.isEmpty()) {
			hm = (LinkedHashMap) list2.get(0);
			ywcCount = Integer.valueOf(hm.get("count").toString());
		}
		
		if(allCount == 0) {
			//最近一次已完成通话进度2代表已完成
			int zjywcCount = 0;
			List list3 = new ArrayList();
			list3 = iStatisticsService.loginOrganDialStatistics(user.getUserCode(),"2","");
			if(list3!=null && !list3.isEmpty()) {
				hm = (LinkedHashMap) list3.get(0);
				zjywcCount = Integer.valueOf(hm.get("count").toString());
			}
			model.addAttribute("aiywcall",zjywcCount+"/"+zjywcCount);
		}else {
			//已完成/所有通话
			model.addAttribute("aiywcall",ywcCount+"/"+allCount);
		}
		
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
//		double totalTime = iStatisticsService.loginOrganTotalCallTime(user.getUserCode(),0);
		int talkTime;
		int minutes = 0;
		int remainder;
		List<UpAitask> list = iUpAitaskService.getAitaskList(user.getUserCode());
		if(list != null) {
			for(int i=0;i<list.size();i++) {
				talkTime = list.get(i).getUpTalktime();
				minutes = talkTime / 60 + minutes;
				remainder = talkTime % 60;
				if(remainder>0) {
					minutes++;
				}
			}
			model.addAttribute("aitotaltime",minutes);
		}else {
			model.addAttribute("aitotaltime",minutes);
		}
		
		//通话总时长
		String averTime = "";
		
		//平均通话时长
		if(ywcjtCount!=0) {
			DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			averTime = decimalFormat.format(minutes/ywcjtCount);
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
		
		int talkTime;
		int minutes = 0;
		int remainder;
		List<UpAitask> list = iUpAitaskService.getAitaskList(user.getUserCode());
		if(list != null) {
			for(int i=0;i<list.size();i++) {
				talkTime = list.get(i).getUpTalktime();
				minutes = talkTime / 60 + minutes;
				remainder = talkTime % 60;
				if(remainder>0) {
					minutes++;
				}
			}
			model.addAttribute("gktotalTime",minutes);
		}else {
			model.addAttribute("gktotalTime",minutes);
		}
		usingPhoneBill = iStatisticsService.loginOrganUsingPhoneBill(user.getUserCode(),0);
		JsSysMember member = iMemberService.getMemberByAccountCode(user.getUserCode());
		reserveField1 = member.getReserveField1();
		
		model.addAttribute("gkusingPhoneBill",usingPhoneBill);
		model.addAttribute("gkreserveField1", reserveField1);
		
		//统计登陆机构转化率：意向用户数量/AI外呼总数量
		double customCountChange = 0;
		double intentionCountChange = 0;
		String conversionRate = "";
		String subday = "";
		List list1 = new ArrayList();
		JSONObject js;

		Calendar cal= null;
		SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
		String day;
		String dayOfMonth;
		DecimalFormat decimalFormat=new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		for(int i = 13;i>-1;i--) {
			sv = new StatisticsVo();
			cal = Calendar.getInstance();
			customCountChange = iStatisticsService.loginOrganAICallCustomCount(user.getUserCode(),i);
			intentionCountChange = iStatisticsService.loginOrganTotalIntentionCount(user.getUserCode(),i);
			
			cal.add(Calendar.DATE,-i);
	        Date d=cal.getTime();
	        
	        day=sp.format(d);//获取几号
//	        dayOfMonth = day.substring(8);
			
	        if(customCountChange != 0) {
				conversionRate = decimalFormat.format(Double.valueOf(intentionCountChange/customCountChange));
				if(i!=13) {
					subday = day.substring(5);
					sv.setDate(subday);
					sv.setConversionRate(conversionRate);
					list1.add(sv);
				}else {
					sv.setDate(day);
					sv.setConversionRate(conversionRate);
					list1.add(sv);
				}
	        }else {
	        	if(i!=13) {
					subday = day.substring(5);
					sv.setDate(subday);
					sv.setConversionRate("0.0");
		        	list1.add(sv);
				}else {
					sv.setDate(day);
					sv.setConversionRate("0.0");
					list1.add(sv);
				}
	        }
		}
		model.addAttribute("gkconversionRate",list1);
		
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
