package com.jeesite.modules.tr.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.common.config.Global;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.utils.AddressUtil;
import com.jeesite.modules.clue.utils.ClueUtils;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.service.MemberService;
import com.jeesite.modules.tr.entity.NeedTime;
import com.jeesite.modules.tr.entity.TrAbility;
import com.jeesite.modules.tr.entity.TrNeed;
import com.jeesite.modules.tr.entity.TrOrder;
import com.jeesite.modules.tr.service.TrService;
import com.jeesite.modules.tr.vo.TrAbilityVo;
import com.jeesite.modules.tr.vo.TrNeedVo;
import com.jeesite.modules.tr.vo.VideoOrderVo;

@Controller
@RequestMapping(value = "${adminPath}/tr")
public class TrController {
	
	private static final Logger logger = LoggerFactory.getLogger(TrController.class);
	
	@Autowired
	private TrService trService;
	
	@Autowired
	private MemberService iMeberService;
	
	//新增能力
	@RequestMapping(value="addTrAbility", method = RequestMethod.POST)
	@ResponseBody
	public TrAbility addTrAbility (@RequestBody(required = false) TrAbility tb){
		
		TrAbility tab = new TrAbility();
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tab.setResult(false);
			return tab;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tab.setResult(false);
			return tab;
		}
		//机构经度
		Double longitude = null;
		//机构纬度
		Double latitude = null;
		//经纬度值
		Object[] values = null;
		if(tb != null) {
			String trId = UUID.randomUUID().toString().replace("-", "");
			tb.setTrId(trId);
			
			try {
				//处理培训时间
				Date beginDate;
				int begintime;
				Date endDate;
				int endtime;
				String beginDates;
				String endDates;
				String[] begindatetimeArr;
				String[] enddatetimeArr;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
				SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss");//注意月份是MM
				if(tb.getTimeArr()!=null && tb.getTimeArr().length>0) {
					beginDates = tb.getTimeArr()[0];
					begindatetimeArr = beginDates.split(" ");
					beginDate = simpleDateFormat.parse(begindatetimeArr[0]);
					begintime = Integer.parseInt(begindatetimeArr[1].replace(":", ""));
					endDates = tb.getTimeArr()[1];
					enddatetimeArr = endDates.split(" ");
					endDate = simpleDateFormat.parse(enddatetimeArr[0]);
					endtime = Integer.parseInt(enddatetimeArr[1].replace(":", ""));
					tb.setTrAllbegintime(sdf.parse(beginDates));
					tb.setTrAllendtime(sdf.parse(endDates));
					tb.setTrBegindate(beginDate);
					tb.setTrBegintime(begintime);
					tb.setTrEnddate(endDate);
					tb.setTrEndtime(endtime);
				}
				String[] addressArr = tb.getAddressArr();
				tb.setTrAddressCity(addressArr[0]);
				tb.setTrAddressArea(addressArr[1]);
				values = AddressUtil.AddressTolongitudea(addressArr[0]+addressArr[1]+tb.getTrAddress());
				if(values != null) {
					longitude = (Double) values[0];
					latitude = (Double) values[1];
					tb.setTrLongitude(longitude);
					tb.setTrLatitude(latitude);
					tb.setTrIseffective("1");
				}else {
					tab.setResult(false);
					return tab;
				}
				tb.setTrCreatetime(new Date());
				tb.setTrUsercode(user.getUserCode());
				trService.addTrAbility(tb);
			}catch(Exception ex){
				ex.printStackTrace();
				tab.setResult(false);
				return tab;
			}
		}else {
			tab.setResult(false);
			return tab;
		}
		tab.setResult(true);
		return tab;
	}
	
	//能力修改
	@RequestMapping(value="updateTrAbility", method = RequestMethod.POST)
	@ResponseBody
	public TrAbility updateTrAbility (@RequestBody(required = false) TrAbility tb){
		
		TrAbility tab = new TrAbility();
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tab.setResult(false);
			return tab;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tab.setResult(false);
			return tab;
		}
		//机构经度
		Double longitude = null;
		//机构纬度
		Double latitude = null;
		//经纬度值
		Object[] values = null;
		try {
			if(tb != null) {
				//处理培训时间
				Date beginDate;
				int begintime;
				Date endDate;
				int endtime;
				String beginDates;
				String endDates;
				String[] begindatetimeArr;
				String[] enddatetimeArr;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
				if(tb.getTimeArr()!=null && tb.getTimeArr().length>0) {
					beginDates = tb.getTimeArr()[0];
					begindatetimeArr = beginDates.split(" ");
					beginDate = simpleDateFormat.parse(begindatetimeArr[0]);
					begintime = Integer.parseInt(begindatetimeArr[1].replace(":", ""));
					endDates = tb.getTimeArr()[1];
					enddatetimeArr = endDates.split(" ");
					endDate = simpleDateFormat.parse(enddatetimeArr[0]);
					endtime = Integer.parseInt(enddatetimeArr[1].replace(":", ""));
					tb.setTrAllbegintime(sdf.parse(beginDates));
					tb.setTrAllendtime(sdf.parse(endDates));
					tb.setTrBegindate(beginDate);
					tb.setTrBegintime(begintime);
					tb.setTrEnddate(endDate);
					tb.setTrEndtime(endtime);
				}
				String[] addressArr = tb.getAddressArr();
				tb.setTrAddressCity(addressArr[0]);
				tb.setTrAddressArea(addressArr[1]);
				values = AddressUtil.AddressTolongitudea(addressArr[0]+addressArr[1]+tb.getTrAddress());
				if(values != null) {
					longitude = (Double) values[0];
					latitude = (Double) values[1];
					tb.setTrLongitude(longitude);
					tb.setTrLatitude(latitude);
					tb.setTrIseffective("1");
				}else {
					tab.setResult(false);
					return tab;
				}
				tb.setTrUpdatetime(new Date());
				trService.updateTrAbility(tb);
			}else {
				tab.setResult(false);
				return tab;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			tab.setResult(false);
			return tab;
		}
		tab.setResult(true);
		return tab;
	}
	
	//能力查询
	@RequestMapping(value="getTrAbility", method = RequestMethod.POST)
	@ResponseBody
	public TrAbility getTrAbility (HttpServletRequest request,HttpServletResponse response,Model model){
		
		TrAbility tb = new TrAbility();
		
		//时间数组
		String[] timearr = new String[2];
		
		//选择区域
		String[] addressArr = new String[2];
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tb.setResult(false);
			return tb;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tb.setResult(false);
			return tb;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tb = trService.getTrAbility(user.getUserCode());
		if(tb == null) {
			tb = new TrAbility(); 
			tb.setResult(false);
		}else{
			addressArr[0] = tb.getTrAddressCity();
			addressArr[1] = tb.getTrAddressArea();
			timearr[0] = sdf.format(tb.getTrAllbegintime());
			timearr[1] = sdf.format(tb.getTrAllendtime());
			tb.setAddressArr(addressArr);
			tb.setTimeArr(timearr);
			tb.setResult(true);
		}
		return tb;
	}
	
	//为需求条件匹配能力
	@RequestMapping(value="matchTrAbilityForNeed", method = RequestMethod.POST)
	@ResponseBody
	public TrAbilityVo matchTrAbilityForNeed (
			@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) TrNeed tn){
		//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 4);
		TrAbilityVo trv = new TrAbilityVo();
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			trv.setResult(false);
			return trv;
		}
				
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			trv.setResult(false);
			return trv;
		}
		
		if(tn == null) {
			trv.setResult(false);
			return trv;
		}
		List<TrAbility> list = new ArrayList<TrAbility>();
		list = trService.matchTrAbilityForNeed(tn);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begindate = "";
		String enddate = "";
		String[] begindatetimeArr;
		String[] enddatetimeArr;
		String needdatelistasc;
		//匹配的范围
		Object[] valuess;
		int n= 0;
		int p = 2;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				begindate = sdf.format(list.get(i).getTrAllbegintime());
				enddate = sdf.format(list.get(i).getTrAllendtime());
				begindatetimeArr = begindate.split(" ");
				enddatetimeArr = enddate.split(" ");
				list.get(i).setMatchdate(begindatetimeArr[0] + " " + enddatetimeArr[0]);
				list.get(i).setMatchtime(begindatetimeArr[1] + "-" + enddatetimeArr[1]);
			}
		}else{
			//找出日期集合从后往前找最近日期
			needdatelistasc = tn.getTrNeeddatelistasc();
			List<Date> trNeeddatelist1 = JSONArray.parseArray(needdatelistasc, Date.class);
			if(trNeeddatelist1!= null && !trNeeddatelist1.isEmpty()) {
				if(trNeeddatelist1.size()>=1) {
					n = trNeeddatelist1.size();
					tn.setTrBegintime(null);
					tn.setTrEndtime(null);
					while(n-1>=0) {
						tn.setTrNeedenddate(trNeeddatelist1.get(n-1));
						PageHelper.startPage(pageNum, 4);
						list = trService.matchTrAbilityForNeed(tn);
						if(list != null && !list.isEmpty()) {
							for(int i=0;i<list.size();i++) {
								begindate = sdf.format(list.get(i).getTrAllbegintime());
								enddate = sdf.format(list.get(i).getTrAllendtime());
								begindatetimeArr = begindate.split(" ");
								enddatetimeArr = enddate.split(" ");
								list.get(i).setMatchdate(begindatetimeArr[0] + " " + enddatetimeArr[0]);
								list.get(i).setMatchtime(begindatetimeArr[1] + "-" + enddatetimeArr[1]);
							}
							break;
						}else {
							n--;
							continue;
						}
					}
					if(list.size()==0) {
						tn.setTrNeedbegindate(null);
						tn.setTrNeedenddate(null);
						//扩展匹配地理范围从5到30km
						while(list.size()<=0) {
							valuess = AddressUtil.findNeighPosition(tn.getTrLatitude(), tn.getTrLongitude(), p);
							tn.setTrMinlng((Double)valuess[0]);
							tn.setTrMaxlng((Double)valuess[1]);
							tn.setTrMinlat((Double)valuess[2]);
							tn.setTrMaxlat((Double)valuess[3]);
							PageHelper.startPage(pageNum, 4);
							list = trService.matchTrAbilityForNeed(tn);
							if(list != null && !list.isEmpty()) {
								for(int i=0;i<list.size();i++) {
									begindate = sdf.format(list.get(i).getTrAllbegintime());
									enddate = sdf.format(list.get(i).getTrAllendtime());
									begindatetimeArr = begindate.split(" ");
									enddatetimeArr = enddate.split(" ");
									list.get(i).setMatchdate(begindatetimeArr[0] + " " + enddatetimeArr[0]);
									list.get(i).setMatchtime(begindatetimeArr[1] + "-" + enddatetimeArr[1]);
								}
							}
							if(list.size()>0) {
								break;
							}
							p++;
							if(p == 7) {
								break;
							}
						}
						//全部条件无满足只匹配相同教学类型数据
						if(list.size()==0) {
							tn.setTrMaxlat(null);
							tn.setTrMinlat(null);
							tn.setTrMaxlng(null);
							tn.setTrMinlng(null);
							PageHelper.startPage(pageNum, 4);
							list = trService.matchTrAbilityForNeed(tn);
							if(list != null && !list.isEmpty()) {
								for(int i=0;i<list.size();i++) {
									begindate = sdf.format(list.get(i).getTrAllbegintime());
									enddate = sdf.format(list.get(i).getTrAllendtime());
									begindatetimeArr = begindate.split(" ");
									enddatetimeArr = enddate.split(" ");
									list.get(i).setMatchdate(begindatetimeArr[0] + " " + enddatetimeArr[0]);
									list.get(i).setMatchtime(begindatetimeArr[1] + "-" + enddatetimeArr[1]);
								}
							}
						}
					}
				}
			}
		}
		PageInfo<TrAbility> page = new PageInfo<TrAbility>(list);
		trv.setPageNum(pageNum);
		trv.setPageInfo(page);
		trv.setResult(true);
		
		return trv;
	}
	
	//查看匹配能力详情
	@RequestMapping(value="getMatchTrAbility", method = RequestMethod.POST)
	@ResponseBody
	public TrAbility getMatchTrAbility (
			@RequestParam(required = false,  value="trAbilityId") String trId,
			@RequestParam(required = false,  value="trNeedId") String trNeedId){
		
		TrAbility tb = new TrAbility();
		
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tb.setResult(false);
			return tb;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tb.setResult(false);
			return tb;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begindate = "";
		String enddate = "";
		String[] begindatetimeArr;
		String[] enddatetimeArr;
		tb = trService.getTrAbilityById(trId);
		if(tb == null) {
			tb = new TrAbility(); 
			tb.setResult(false);
		}else{
			begindate = sdf.format(tb.getTrAllbegintime());
			enddate = sdf.format(tb.getTrAllendtime());
			begindatetimeArr = begindate.split(" ");
			enddatetimeArr = enddate.split(" ");
			tb.setMatchdate(begindatetimeArr[0] + " " + enddatetimeArr[0]);
			tb.setMatchtime(begindatetimeArr[1] + "-" + enddatetimeArr[1]);
		}
		tb.setTrNeedId(trNeedId);
		tb.setResult(true);
		return tb;
	}
	
	//需求列表查询
	@RequestMapping(value="getTrNeedList", method = RequestMethod.POST)
	@ResponseBody
	public TrNeedVo getTrNeedList (
			@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) TrNeedVo trv){
		
		//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 6);
		TrNeedVo trnv = new TrNeedVo();
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			trnv.setResult(false);
			return trnv;
		}
				
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			trnv.setResult(false);
			return trnv;
		}
		
		List<TrNeed> list = new ArrayList<TrNeed>();
		if(trv == null) {
			trv = new TrNeedVo();
		}
		NeedTime[] needTime;
		NeedTime nt = null;
		String[] timearr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		trv.setUserCode(user.getUserCode());
		list = trService.getTrNeedList(trv);
		//组装needtime
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				String trNeeddatelist = list.get(i).getTrNeeddatelist();
				String trNeedtimelist = list.get(i).getTrNeedtimelist();
				List<Date> trNeeddatelist1 = JSONArray.parseArray(trNeeddatelist, Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(trNeedtimelist, String.class);
				if(trNeeddatelist1!=null && trNeeddatelist1.size()>0  ) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for(int j=0;j<trNeeddatelist1.size();j++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(j));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(j*2);
						timearr[1] = trNeedtimelist1.get(j*2+1);
						nt.setTimeArr(timearr);
						needTime[j] = nt;
					}
					list.get(i).setNeedTime(needTime);
				}
			}
		}
		PageInfo<TrNeed> page = new PageInfo<TrNeed>(list);
		trnv.setPageNum(pageNum);
		trnv.setPageInfo(page);
		trnv.setResult(true);
		
		return trnv;
	}
	
	//需求查询
	@RequestMapping(value="getTrNeed", method = RequestMethod.POST)
	@ResponseBody
	public TrNeed getTrNeed (HttpServletRequest request,HttpServletResponse response,Model model){
		
		TrNeed tb = new TrNeed();
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tb.setResult(false);
			return tb;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tb.setResult(false);
			return tb;
		}
		
		tb = trService.getTrNeed(request.getParameter("trId"));
//		int begintime = tb.getTrBegintime();
//		int endtime = tb.getTrEndtime();
//		String[] timeArr = new String[2];
//		if(begintime<999) {
//			StringBuilder sb = new StringBuilder("0"+begintime);
//			sb.insert(2, ":");
//			timeArr[0] = sb.toString();
//		}else {
//			StringBuilder sb = new StringBuilder(begintime+"");
//			sb.insert(2, ":");
//			timeArr[0] = sb.toString();
//		}
//		if(endtime<999) {
//			StringBuilder sb = new StringBuilder("0"+endtime);
//			sb.insert(2, ":");
//			timeArr[1] = sb.toString();
//		}else {
//			StringBuilder sb = new StringBuilder(endtime+"");
//			sb.insert(2, ":");
//			timeArr[1] = sb.toString();
//		}
//		tb.setTimeArr(timeArr);
		//日期对象
		try {
			NeedTime[] needTime;
			NeedTime nt = null;
			String[] timearr = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String trNeeddatelist = tb.getTrNeeddatelist();
			String trNeedtimelist = tb.getTrNeedtimelist();
			List<Date> trNeeddatelist1 = JSONArray.parseArray(trNeeddatelist, Date.class);
			List<String> trNeedtimelist1 = JSONArray.parseArray(trNeedtimelist, String.class);
			if(trNeeddatelist1.size()>0) {
				needTime = new NeedTime[trNeeddatelist1.size()];
				for(int i=0;i<trNeeddatelist1.size();i++) {
					nt = new NeedTime();
					nt.setNeedDate(trNeeddatelist1.get(i));
					timearr = new String[2];
					timearr[0] = trNeedtimelist1.get(i*2);
					timearr[1] = trNeedtimelist1.get(i*2+1);
					nt.setTimeArr(timearr);
					needTime[i] = nt;
				}
				tb.setNeedTime(needTime);
			}
			String[] addressArr = new String[2];
			addressArr[0] = tb.getTrAddressCity();
			addressArr[1] = tb.getTrAddressArea();
			tb.setAddressArr(addressArr);
			tb.setResult(true);
			return tb;
		}catch(Exception e) {
			e.printStackTrace();
			tb.setResult(false);
			return tb;
		}
	}
	
	//新增需求
	@RequestMapping(value="addTrNeed", method = RequestMethod.POST)
	@ResponseBody
	public TrNeed addTrNeed (@RequestBody(required = false) TrNeed tn){
		
		TrNeed tns = new TrNeed(); 
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tns.setResult(false);
			return tns;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tns.setResult(false);
			return tns;
		}
		
		if(tn != null) {
			//机构经度
			Double longitude = null;
			//机构纬度
			Double latitude = null;
			//经纬度值
			Object[] values;
			//匹配的范围
			Object[] valuess;
			//需求时间
			NeedTime[] needTime;
			//具体需求时间
			NeedTime nt;
			//全量日期集合
			List<Date> alldatelist = new ArrayList<Date>();
			List<Date> alldatelistasc;
			List alldatelistasource = new ArrayList();
			List alldatelistascformat = new ArrayList();
			//全量时间集合
			List alltimelist = new ArrayList();
			Object[] alltimelistasc;
			List alltimelistascint = new ArrayList();
			List allimelistasource = new ArrayList();
			//以n为5km倍数
			int n = 1;
			try {
				String trId = UUID.randomUUID().toString().replace("-", "");
				tns.setTrId(trId);
				
				JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
				if(jsm != null) {
					tns.setTrLongitude(jsm.getMatchLongitude());
					tns.setTrLatitude(jsm.getMatchLatitude());
					tns.setTrIseffective("1");
				}else {
					tns.setResult(false);
					return tns;
				}
				//机构地址
				valuess = AddressUtil.findNeighPosition(jsm.getMatchLongitude(), jsm.getMatchLatitude(), n);
				tns.setTrMinlng((Double)valuess[0]);
				tns.setTrMaxlng((Double)valuess[1]);
				tns.setTrMinlat((Double)valuess[2]);
				tns.setTrMaxlat((Double)valuess[3]);
				
				//市、区
				tns.setTrAddressCity(tn.getAddressArr()[0]);
				tns.setTrAddressArea(tn.getAddressArr()[1]);
				
				
				//日期
//				tns.setTrNeeddate(tn.getTrNeeddate());
				needTime = tn.getNeedTime();
				String[] timearr;
				double value = 0;
				double begintime;
				double endtime;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(needTime!=null && needTime.length>0) {
					for(int i=0;i<needTime.length;i++) {
						nt = needTime[i];
						timearr = nt.getTimeArr();
						alldatelist.add(nt.getNeedDate());
						alldatelistasource.add(nt.getNeedDate());
						allimelistasource.add(timearr[0]);
						allimelistasource.add(timearr[1]);
						alltimelist.add(Integer.parseInt(timearr[0].replace(":", "")));
						alltimelist.add(Integer.parseInt(timearr[1].replace(":", "")));
						begintime = Integer.parseInt(timearr[0].split(":")[0])*3600+Integer.parseInt(timearr[0].split(":")[1])*60 + 0.00;
						endtime = Integer.parseInt(timearr[1].split(":")[0])*3600+Integer.parseInt(timearr[1].split(":")[1])*60 + 0.00;
						value += endtime - begintime; 
					}
					//计算时长
					//利用BigDecimal来实现四舍五入.保留一位小数
					double result = new BigDecimal(value/3600.00).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					//1代表保留1位小数,保留两位小数就是2,依此累推
			        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
					tns.setTrTotaltime(result);
					System.out.println("alldatelistasource---------------"+alldatelistasource.toString());
					//原始日期
					tns.setTrNeeddatelist(JSON.toJSONString(alldatelistasource));
					//日期做升序
					alldatelistasc = ClueUtils.getd(alldatelist);
//					for(int i=0;i<alldatelistasc.size();i++) {
//						alldatelistascformat.add(sdf.format(alldatelistasc.get(i)));
//					}
//					System.out.println("alldatelistascformat---------------"+alldatelistascformat.toString());
					tns.setTrNeeddatelistasc(JSON.toJSONString(alldatelistasc));
					//最小需求日期
					tns.setTrNeedbegindate(alldatelistasc.get(0));
					//最大需求日期
					if(alldatelist.size()>1) {
						tns.setTrNeedenddate(alldatelistasc.get(alldatelistasc.size()-1));
					}else {
						tns.setTrNeedenddate(alldatelistasc.get(0));
					}
					System.out.println("allimelistasource---------------"+allimelistasource.toString());
					//原始时间
					tns.setTrNeedtimelist(JSON.toJSONString(allimelistasource));
					//时间做升序
					alltimelistasc = ClueUtils.gett(alltimelist.toArray());
					if(alltimelistasc != null && alltimelistasc.length>0) {
						for(int i=0;i<alltimelistasc.length;i++) {
							alltimelistascint.add(Integer.parseInt(alltimelistasc[i].toString()));
						}
					}
					System.out.println("alltimelistasc---------------"+alltimelistascint.toString());
					tns.setTrNeedtimelistasc(JSON.toJSONString(alltimelistascint));
					//最小开始时间
					tns.setTrBegintime(Integer.parseInt(alltimelistasc[0].toString()));
					//最大开始时间
					if(alltimelistasc.length>1) {
						tns.setTrEndtime(Integer.parseInt(alltimelistasc[alltimelistasc.length-1].toString()));
					}else {
						tns.setTrEndtime(Integer.parseInt(alltimelistasc[0].toString()));
					}
					
					
				}
				
				//其他信息
				tns.setTrType(tn.getTrType());
				tns.setTrCount(tn.getTrCount());
				tns.setTrUnitprice(tn.getTrUnitprice());
				tns.setTrAddress(tn.getTrAddress());
				tns.setTrRemark(tn.getTrRemark());
				tns.setTrUsercode(user.getUserCode());
				tns.setTrCreatetime(new Date());
				
				trService.addTrNeed(tns);
			}catch(Exception ex){
				ex.printStackTrace();
				tns.setResult(false);
				return tns;
			}
		}
		tns.setResult(true);
		return tns;
	}
	
	//需求修改
	@RequestMapping(value="updateTrNeed", method = RequestMethod.POST)
	@ResponseBody
	public TrNeed updateTrNeed (@RequestBody(required = false) TrNeed tn){
		
		TrNeed tns = new TrNeed(); 
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			tns.setResult(false);
			return tns;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			tns.setResult(false);
			return tns;
		}
		if(tn != null) {
			//机构经度
			Double longitude = null;
			//机构纬度
			Double latitude = null;
			//经纬度值
			Object[] values;
			//匹配的范围
			Object[] valuess;
			//需求时间
			NeedTime[] needTime;
			//具体需求时间
			NeedTime nt;
			//全量日期集合
			List<Date> alldatelist = new ArrayList<Date>();
			List<Date> alldatelistasc;
			List alldatelistasource = new ArrayList();
			List alldatelistascformat = new ArrayList();
			//全量时间集合
			List alltimelist = new ArrayList();
			Object[] alltimelistasc;
			List alltimelistascint = new ArrayList();;
			List allimelistasource = new ArrayList();
			//以n为5km倍数
			int n = 1;
			try {
				JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
				if(jsm != null) {
					tns.setTrLongitude(jsm.getMatchLongitude());
					tns.setTrLatitude(jsm.getMatchLatitude());
					tns.setTrIseffective("1");
				}else {
					tns.setResult(false);
					return tns;
				}
				//机构地址
				valuess = AddressUtil.findNeighPosition(jsm.getMatchLongitude(), jsm.getMatchLatitude(), n);
				tns.setTrMinlng((Double)valuess[0]);
				tns.setTrMaxlng((Double)valuess[1]);
				tns.setTrMinlat((Double)valuess[2]);
				tns.setTrMaxlat((Double)valuess[3]);
				
				//市、区
				tns.setTrAddressCity(tn.getAddressArr()[0]);
				tns.setTrAddressArea(tn.getAddressArr()[1]);
				
				
				//日期
//					tns.setTrNeeddate(tn.getTrNeeddate());
				needTime = tn.getNeedTime();
				String[] timearr;
				double value = 0;
				double begintime;
				double endtime;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(needTime!=null && needTime.length>0) {
					for(int i=0;i<needTime.length;i++) {
						nt = needTime[i];
						timearr = nt.getTimeArr();
						alldatelist.add(nt.getNeedDate());
						alldatelistasource.add(nt.getNeedDate());
						allimelistasource.add(timearr[0]);
						allimelistasource.add(timearr[1]);
						alltimelist.add(Integer.parseInt(timearr[0].replace(":", "")));
						alltimelist.add(Integer.parseInt(timearr[1].replace(":", "")));
						begintime = Integer.parseInt(timearr[0].split(":")[0])*3600+Integer.parseInt(timearr[0].split(":")[1])*60 + 0.00;
						endtime = Integer.parseInt(timearr[1].split(":")[0])*3600+Integer.parseInt(timearr[1].split(":")[1])*60 + 0.00;
						value += endtime - begintime; 
					}
					//计算时长
					//利用BigDecimal来实现四舍五入.保留一位小数
					double result = new BigDecimal(value/3600.00).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					//1代表保留1位小数,保留两位小数就是2,依此累推
			        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
					tns.setTrTotaltime(result);
					System.out.println("alldatelistasource---------------"+alldatelistasource.toString());
					//原始日期
					tns.setTrNeeddatelist(JSON.toJSONString(alldatelistasource));
					//日期做升序
					alldatelistasc = ClueUtils.getd(alldatelist);
//					for(int i=0;i<alldatelistasc.size();i++) {
//						alldatelistascformat.add(sdf.format(alldatelistasc.get(i)));
//					}
//					System.out.println("alldatelistascformat---------------"+alldatelistascformat.toString());
					tns.setTrNeeddatelistasc(JSON.toJSONString(alldatelistasc));
					//最小需求日期
					tns.setTrNeedbegindate(alldatelistasc.get(0));
					//最大需求日期
					if(alldatelist.size()>1) {
						tns.setTrNeedenddate(alldatelistasc.get(alldatelistasc.size()-1));
					}else {
						tns.setTrNeedenddate(alldatelistasc.get(0));
					}
					System.out.println("allimelistasource---------------"+allimelistasource.toString());
					//原始时间
					tns.setTrNeedtimelist(JSON.toJSONString(allimelistasource));
					//时间做升序
					alltimelistasc = ClueUtils.gett(alltimelist.toArray());
					if(alltimelistasc != null && alltimelistasc.length>0) {
						for(int i=0;i<alltimelistasc.length;i++) {
							alltimelistascint.add(Integer.parseInt(alltimelistasc[i].toString()));
						}
					}
					System.out.println("alltimelistasc---------------"+alltimelistascint.toString());
					tns.setTrNeedtimelistasc(alltimelistascint.toString());
					//最小开始时间
					tns.setTrBegintime(Integer.parseInt(alltimelistasc[0].toString()));
					//最大开始时间
					if(alltimelistasc.length>1) {
						tns.setTrEndtime(Integer.parseInt(alltimelistasc[alltimelistasc.length-1].toString()));
					}else {
						tns.setTrEndtime(Integer.parseInt(alltimelistasc[0].toString()));
					}
					
					
				}
				
				//其他信息
				tns.setTrType(tn.getTrType());
				tns.setTrCount(tn.getTrCount());
				tns.setTrUnitprice(tn.getTrUnitprice());
				tns.setTrAddress(tn.getTrAddress());
				tns.setTrRemark(tn.getTrRemark());
				tns.setTrUsercode(user.getUserCode());
				tns.setTrCreatetime(tn.getTrCreatetime());
				tns.setTrUpdatetime(new Date());
				tns.setTrId(tn.getTrId());
				
				trService.updateTrNeed(tns);
			}catch(Exception ex){
				ex.printStackTrace();
				tns.setResult(false);
				return tns;
			}
		}	
		tns.setResult(true);
		return tns;
	}
	
	
	//创建订单
	@RequestMapping(value="createTrOrder", method = RequestMethod.POST)
	@ResponseBody
	public TrOrder createTrOrder(
			@RequestParam(required = false,  value="trNeedId") String trNeedId,
			@RequestParam(required = false,  value="trAbilityId") String trAbilityId) {
		TrOrder trOrder = new TrOrder();
		NeedTime[] needTime;
		NeedTime nt = null;
		String[] timearr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			trOrder.setResult(false);
			return trOrder;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			trOrder.setResult(false);
			return trOrder;
		}
		TrAbility trAbility = trService.getTrAbilityById(trAbilityId);
		TrNeed trNeed = trService.getTrNeed(trNeedId);
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
		JsSysMember jsm1 = iMeberService.getMemberByAccountCode(trAbility.getTrUsercode());
		String trId = UUID.randomUUID().toString().replace("-", "");
		trOrder.setTrCommonditycode(trId);
		trOrder.setProductTitle("教师复用-乙方机构"+jsm1.getOrganName());
		trOrder.setTrPartaname(jsm.getOrganName());
		trOrder.setTrPartaphone(jsm.getOrganPhone());
		trOrder.setTrPartbname(jsm1.getOrganName());
		trOrder.setTrPartbphone(jsm1.getOrganPhone());
		trOrder.setTrPartaaddress(trNeed.getTrAddress());
		trOrder.setTrPartbaddress(trAbility.getTrAddress());
		trOrder.setTrType(trNeed.getTrType());
		trOrder.setTrCount(trNeed.getTrCount());
		trOrder.setTrNeedbegindate(trNeed.getTrNeedbegindate());
		trOrder.setTrNeedenddate(trNeed.getTrNeedenddate());
		trOrder.setTrBegintime(trNeed.getTrBegintime());
		trOrder.setTrEndtime(trNeed.getTrEndtime());
		String trNeeddatelist = trNeed.getTrNeeddatelist();
		String trNeedtimelist = trNeed.getTrNeedtimelist();
		List<Date> trNeeddatelist1 = JSONArray.parseArray(trNeeddatelist, Date.class);
		List<String> trNeedtimelist1 = JSONArray.parseArray(trNeedtimelist, String.class);
		if(trNeeddatelist1.size()>0) {
			needTime = new NeedTime[trNeeddatelist1.size()];
			for(int i=0;i<trNeeddatelist1.size();i++) {
				nt = new NeedTime();
				nt.setNeedDate(trNeeddatelist1.get(i));
				timearr = new String[2];
				timearr[0] = trNeedtimelist1.get(i*2);
				timearr[1] = trNeedtimelist1.get(i*2+1);
				nt.setTimeArr(timearr);
				needTime[i] = nt;
			}
			trOrder.setNeedTime(needTime);
		}
		trOrder.setTrNeeddatelist(trNeed.getTrNeeddatelist());
		trOrder.setTrNeedtimelist(trNeed.getTrNeedtimelist());
		trOrder.setTrDuration(trNeed.getTrTotaltime());
		trOrder.setTrUnitprice(trAbility.getTrUnitprice());
		trOrder.setTrTotalprice(trNeed.getTrTotaltime()*trAbility.getTrUnitprice()*trNeed.getTrCount());
		trOrder.setTrRemark(trNeed.getTrRemark());
		trOrder.setResult(true);
		trOrder.setPartBCode(jsm1.getUserCode());
		trOrder.setNeedId(trNeedId);
		return trOrder;
	}
	
	//创建的订单列表
	@RequestMapping(value="getAllCreatOrder", method = RequestMethod.POST)
	@ResponseBody
	public VideoOrderVo getAllCreatOrder(
			@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) VideoOrderVo vo){
		VideoOrderVo vov = new VideoOrderVo();
		//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 4);
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			vov.setResult(false);
			return vov;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			vov.setResult(false);
			return vov;
		}
		
		List<VideoOrder> list = trService.getAllCreatOrder(user.getUserCode());
		TrOrder to = new TrOrder();
		if(list!=null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				to = list.get(i).getTrOrder();
				NeedTime[] needTime = null;
				NeedTime nt = null;
				String[] timearr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String trNeeddatelist = to.getTrNeeddatelist();
				String trNeedtimelist = to.getTrNeedtimelist();
				List<Date> trNeeddatelist1 = JSONArray.parseArray(trNeeddatelist, Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(trNeedtimelist, String.class);
				if(trNeeddatelist1.size()>0) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for(int j=0;j<trNeeddatelist1.size();j++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(j));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(j*2);
						timearr[1] = trNeedtimelist1.get(j*2+1);
						nt.setTimeArr(timearr);
						needTime[j] = nt;
					}
					to.setNeedTime(needTime);
				}
			}
		}
		if(list != null && !list.isEmpty()) {
			PageInfo<VideoOrder> page = new PageInfo<VideoOrder>(list);
			vov.setPageNum(pageNum);
			vov.setPageInfo(page);
			vov.setResult(true);
		}else {
			vov.setResult(false);
		}
		
		return vov;
	}
	
	//接受的订单列表
	@RequestMapping(value="getAllAcceptOrder", method = RequestMethod.POST)
	@ResponseBody
	public VideoOrderVo getAllAcceptOrder(
			@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) VideoOrderVo vo){
		VideoOrderVo vov = new VideoOrderVo();
		//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 4);
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			vov.setResult(false);
			return vov;
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			vov.setResult(false);
			return vov;
		}
		
		List<VideoOrder> list = trService.getAllAcceptOrder(user.getUserCode());
		TrOrder to = new TrOrder();
		if(list!=null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				to = list.get(i).getTrOrder();
				NeedTime[] needTime = null;
				NeedTime nt = null;
				String[] timearr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String trNeeddatelist = to.getTrNeeddatelist();
				String trNeedtimelist = to.getTrNeedtimelist();
				List<Date> trNeeddatelist1 = JSONArray.parseArray(trNeeddatelist, Date.class);
				List<String> trNeedtimelist1 = JSONArray.parseArray(trNeedtimelist, String.class);
				if(trNeeddatelist1.size()>0) {
					needTime = new NeedTime[trNeeddatelist1.size()];
					for(int j=0;j<trNeeddatelist1.size();j++) {
						nt = new NeedTime();
						nt.setNeedDate(trNeeddatelist1.get(j));
						timearr = new String[2];
						timearr[0] = trNeedtimelist1.get(j*2);
						timearr[1] = trNeedtimelist1.get(j*2+1);
						nt.setTimeArr(timearr);
						needTime[j] = nt;
					}
					to.setNeedTime(needTime);
				}
			}
		}
		if(list != null && !list.isEmpty()) {
			PageInfo<VideoOrder> page = new PageInfo<VideoOrder>(list);
			vov.setPageNum(pageNum);
			vov.setPageInfo(page);
			vov.setResult(true);
		}else {
			vov.setResult(false);
		}
		
		return vov;
	}
	
	//确认完成/退款申请等状态变更
	@RequestMapping(value="finishVideoOrder", method = RequestMethod.POST)
	@ResponseBody
	public VideoOrderVo finishVideoOrder(
			@RequestBody(required = false) VideoOrder vo){
		VideoOrderVo vov = new VideoOrderVo();
		trService.updateVideoOrder(vo);
		vov.setResult(true);
		return vov;
	}
	
}
