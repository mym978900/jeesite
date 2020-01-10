package com.jeesite.modules.clue.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jeesite.common.utils.SpringUtils;
import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.service.UpAiInfoService;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.utils.AddressUtil;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.service.MemberService;

//定时任务
//xf
//2019.12.11
@Component
public class ClueMatchTask {
	
	private static Logger logger = LoggerFactory.getLogger(ClueMatchTask.class);
	
	private static final class Static {
		//会员信息处理
		private static MemberService iMeberService = SpringUtils.getBean(MemberService.class);
		//线索信息处理
		private static UpClueService iUpClueService = SpringUtils.getBean(UpClueService.class);
		//匹配信息处理
		private static UpAiInfoService iUpAiInfoService = SpringUtils.getBean(UpAiInfoService.class);
	}
	
	
	//定时处理智能线索匹配
	@SuppressWarnings("deprecation")
	@Scheduled(cron="30 02 18 * * ?")
	public void clueMatch() {
		logger.info("数据共享-----开启匹配线索数据筛选任务");
		//会员等级2、3级支持匹配
		List<JsSysMember> list = Static.iMeberService.getClueMatchUser();
		if(list != null && !list.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			//系统时间
			String sysdate = df.format(date);
			//首次创建线索时间
			Date update = new Date();
			//首次匹配线索时间
			Date onedate = new Date();
			//会员等级
			String grade = "";
			//会员匹配数量
			int matchCount = 0;
			//匹配时间
			String updateStr = "";
			//机构经度
			Double longitude;
			//机构纬度
			Double latitude;
			//机构线索数量
			int count = 0;
			//用户号
			String userCode = "";
			//用户线索匹配批次
			int times = 0;
			//匹配范围第n个0.5km
			int n = 0;
			//匹配的线索信息
			List matchList = new ArrayList();
			List matchListxx = new ArrayList();
			//已经匹配的线索信息
			List exitsMatchList = new ArrayList();
			//匹配的范围
			Object[] values;
			//线索信息
			UpClue uc;
			//匹配线索对象
			UpAiinfo uai = new UpAiinfo();
			HashMap hm;
			
			if(list !=null && !list.isEmpty()) {
				for(int i=0;i<list.size();i++) {
					JsSysMember jsm = list.get(i);
					grade = jsm.getMemberGrade();
					update = jsm.getMatchUpdate();
					onedate = jsm.getMatchOnedate();
					count = jsm.getClueCount();
					longitude = jsm.getMatchLongitude();
					latitude = jsm.getMatchLatitude();
					userCode = jsm.getAccountNumber();
					times = jsm.getAiTimes();
					
					//已经上传线索数据
					if(count>0) {
						//初次匹配
						if(onedate==null) {
							//首次14天匹配
							updateStr = df.format(update.getTime()+14 * 24 * 60 * 60 * 1000);
							if(sysdate.equals(updateStr)) {
								if(grade.equals("2")) {
									//会员等级2 匹配数等于上传总数
									matchCount = count;
								}else if(grade.equals("3")) {
									//会员等级3 匹配数等于上传总数3倍
									matchCount = 3*count;
								}
								//当前用户匹配的线索
								exitsMatchList = Static.iUpAiInfoService.getExistClue(userCode);
								while(matchList.size()<matchCount) {
									n+=1;
									values = AddressUtil.findNeighPosition(longitude, latitude, n);
									matchList = Static.iUpClueService.getMatchClue(jsm.getAccountNumber(),jsm.getOrganClass(),values[0].toString(),values[1].toString(),values[2].toString(),values[3].toString());
									//去掉匹配过得线索
									if(matchList != null && !matchList.isEmpty() && exitsMatchList != null && !exitsMatchList.isEmpty()) {
										matchList.removeAll(exitsMatchList);
									}
									if(n==20) {
										break;
									}
								}
								//截取匹配数的线索
								matchListxx = matchList.subList(0, matchCount);
								for(int j=0;j<matchListxx.size();j++) {
									hm = (HashMap) matchListxx.get(i);
									uai.setUpAicode(hm.get("up_clue_code").toString());//线索编号
									uai.setUpAiphone(hm.get("up_clue_tel").toString());//线索手机号
									uai.setUpAiappraise("0");//意向状态
									uai.setUpAicreatetime(date);//创建时间
									uai.setUpUsercode(userCode);//用户编码
									uai.setUpAitimes(1);//批次
									
									//更新线索最新匹配时间
									Static.iUpClueService.updateMatchTime(hm.get("up_clue_code").toString(), date);
									
									//新增匹配线索
									Static.iUpAiInfoService.addUpAiInfo(uai);
								}
								
								//更新用户表首次匹配时间，最新批次
								jsm.setMatchOnedate(date);
								jsm.setAiTimes(1);
								Static.iMeberService.updateByPrimaryKey(jsm);
								
								logger.info("用户["+userCode+"]线索匹配完成");
							}
						}
						//不是初次匹配
						else {
							if(date.getDay()==onedate.getDay()) {
								if(grade.equals("2")) {
									//会员等级2 匹配数等于上传总数
									matchCount = count;
								}else if(grade.equals("3")) {
									//会员等级3 匹配数等于上传总数3倍
									matchCount = 3*count;
								}
								//当前用户匹配的线索
								exitsMatchList = Static.iUpAiInfoService.getExistClue(userCode);
								while(matchList.size()<matchCount) {
									n+=1;
									values = AddressUtil.findNeighPosition(longitude, latitude, n);
									matchList = Static.iUpClueService.getMatchClue(jsm.getAccountNumber(),jsm.getOrganClass(),values[0].toString(),values[1].toString(),values[2].toString(),values[3].toString());
									//去掉匹配过得线索
									if(matchList != null && !matchList.isEmpty() && exitsMatchList != null && !exitsMatchList.isEmpty()) {
										matchList.removeAll(exitsMatchList);
									}
									if(n==20) {
										break;
									}
								}
								//截取匹配数的线索
								matchListxx = matchList.subList(0, matchCount);
								for(int j=0;j<matchListxx.size();j++) {
									hm = (HashMap) matchListxx.get(i);
									uai.setUpAicode(hm.get("up_aicode").toString());//线索编号
									uai.setUpAiphone(hm.get("up_aiphone").toString());//线索手机号
									uai.setUpAiappraise("0");//意向状态
									uai.setUpAicreatetime(date);//创建时间
									uai.setUpUsercode(userCode);//用户编码
									uai.setUpAitimes(times+1);//批次
									
									//更新线索最新匹配时间
									Static.iUpClueService.updateMatchTime(hm.get("up_clue_code").toString(), date);
									
									//新增匹配线索
									Static.iUpAiInfoService.addUpAiInfo(uai);
								}
								
								//更新用户表最新批次
								jsm.setAiTimes(times+1);
								Static.iMeberService.updateByPrimaryKey(jsm);
								
								logger.info("用户["+userCode+"]线索匹配完成");
							}
						}
					}
				}
			}
		}
	}
	
	//为会员和线索标注经纬度
	@SuppressWarnings("deprecation")
	@Scheduled(cron="20 56 11 * * ?")
	public void autoConfigAddress() {
		
		//设置会员经纬度
		List<JsSysMember> list = Static.iMeberService.getNoConfigAddress();
		//会员信息
		JsSysMember jsm = null;
		//线索信息
		UpClue uc = null;
		//会员机构地址
		String address = "";
		//机构经度
		Double longitude = null;
		//机构纬度
		Double latitude = null;
		//经纬度值
		Object[] values;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				jsm = list.get(i);
				address = jsm.getOrganAddress();
				//调用百度地图API获取逆地理编码
				try {
					values = AddressUtil.AddressTolongitudea(address);
					if(values != null) {
						longitude = (Double) values[0];
						latitude = (Double) values[1];
					}else {
						longitude = 0.0;
						latitude = 0.0;
					}
					jsm.setMatchLongitude(longitude);
					jsm.setMatchLatitude(latitude);
					
					//更新会员机构经纬度
					Static.iMeberService.updateByPrimaryKey(jsm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		//设置线索经纬度
		List<UpClue> listUp = Static.iUpClueService.getNoConfigAddress();
		if(listUp != null && !listUp.isEmpty()) {
			for(int i=0;i<listUp.size();i++) {
				uc = listUp.get(i);
				address = uc.getUpClueAddre();
				//调用百度地图API获取逆地理编码
				try {
					values = AddressUtil.AddressTolongitudea(address);
					if(values != null) {
						longitude = (Double) values[0];
						latitude = (Double) values[1];
					}else {
						longitude = 0.0;
						latitude = 0.0;
					}
					uc.setUpClueLongitude(longitude);
					uc.setUpClueLatitude(latitude);
					
					//更新线索经纬度
					Static.iUpClueService.updateByPrimaryKey(uc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
package com.jeesite.modules.clue.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jeesite.common.utils.SpringUtils;
import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.service.UpAiInfoService;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.utils.AddressUtil;
import com.jeesite.modules.clue.utils.AiUtil;
import com.jeesite.modules.clue.utils.PropertiesListenerConfig;
import com.jeesite.modules.sys.entity.SysDictData;
import com.jeesite.modules.sys.service.SysDictDataService;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.service.MemberService;

//定时任务
//xf
//2019.12.11
@Component
public class ClueMatchTask {
	
	private static Logger logger = LoggerFactory.getLogger(ClueMatchTask.class);
	
	private static final class Static {
		//会员信息处理
		private static MemberService iMeberService = SpringUtils.getBean(MemberService.class);
		//线索信息处理
		private static UpClueService iUpClueService = SpringUtils.getBean(UpClueService.class);
		//匹配信息处理
		private static UpAiInfoService iUpAiInfoService = SpringUtils.getBean(UpAiInfoService.class);
		//AI外呼信息处理
		private static SysDictDataService iSysDictDataService = SpringUtils.getBean(SysDictDataService.class);
	}
	
	
	//定时处理智能线索匹配
	@SuppressWarnings("deprecation")
	@Scheduled(cron="10 50 14 * * ?")
	public void clueMatch() {
		logger.info("数据共享-----开启匹配线索数据筛选任务");
		//会员等级2、3级支持匹配
		List<JsSysMember> list = Static.iMeberService.getClueMatchUser();
		if(list != null && !list.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			//系统时间
			String sysdate = df.format(date);
			//首次创建线索时间
			Date update = new Date();
			//首次匹配线索时间
			Date onedate = new Date();
			//会员等级
			String grade = "";
			//会员匹配数量
			int matchCount = 0;
			//匹配时间
			String updateStr = "";
			//机构经度
			Double longitude;
			//机构纬度
			Double latitude;
			//机构线索数量
			int count = 0;
			//用户号
			String userCode = "";
			//用户线索匹配批次
			int times = 0;
			//匹配范围第n个0.5km
			int n = 0;
			//匹配的线索信息
			List matchList = new ArrayList();
			List matchListxx = new ArrayList();
			//已经匹配的线索信息
			List exitsMatchList = new ArrayList();
			//匹配的范围
			Object[] values;
			//线索信息
			UpClue uc;
			//匹配线索对象
			UpAiinfo uai = new UpAiinfo();
			//外呼记录id
	    	String id="";
			HashMap hm = new HashMap();
			//是否达到匹配线索天数
			long c = 0;
			
			if(list !=null && !list.isEmpty()) {
				for(int i=0;i<list.size();i++) {
					JsSysMember jsm = list.get(i);
					grade = jsm.getMemberGrade();
					update = jsm.getMatchUpdate();
					onedate = jsm.getMatchOnedate();
					count = jsm.getClueCount();
					longitude = jsm.getMatchLongitude();
					latitude = jsm.getMatchLatitude();
					userCode = jsm.getAccountNumber();
					times = jsm.getAiTimes();
					
					//已经上传线索数据
					if(count>0) {
						//初次匹配
						if(onedate==null) {
							//首次14天匹配
							updateStr = df.format(update.getTime()+14 * 24 * 60 * 60 * 1000);
							if(sysdate.equals(updateStr)) {
								if(grade.equals("2")) {
									//会员等级2 匹配数等于上传总数
									matchCount = count;
								}else if(grade.equals("3")) {
									//会员等级3 匹配数等于上传总数3倍
									matchCount = 3*count;
								}
								//当前用户匹配的线索
								exitsMatchList = Static.iUpAiInfoService.getExistClue(userCode);
								while(matchList.size()<matchCount) {
									n+=1;
									values = AddressUtil.findNeighPosition(longitude, latitude, n);
									matchList = Static.iUpClueService.getMatchClue(jsm.getAccountNumber(),jsm.getOrganClass(),values[0].toString(),values[1].toString(),values[2].toString(),values[3].toString());
									//去掉匹配过得线索
									if(matchList != null && !matchList.isEmpty() && exitsMatchList != null && !exitsMatchList.isEmpty()) {
										matchList.removeAll(exitsMatchList);
									}
									if(n==2) {
										break;
									}
								}
								//截取匹配数的线索-----------------------报错为空的话怎么办
								if(matchList.size()>=matchCount) {
									matchListxx = matchList.subList(0, matchCount);
								}else if(matchList.size() < matchCount && matchList.size() != 0 ) {
									matchListxx = matchList;
								}else if(matchList.size()==0) {
									continue;
								}
								for(int j=0;j<matchListxx.size();j++) {
									hm = (HashMap) matchListxx.get(j);
									id = UUID.randomUUID().toString().replace("-", "");
									uai.setUpId(id);
									uai.setUpAicode(hm.get("up_aicode").toString());//线索编号
									uai.setUpAiphone(hm.get("up_aiphone").toString());//线索手机号
									uai.setUpCluename(hm.get("up_cluename").toString());//线索名称
									uai.setUpAiappraise("0");//意向状态
									uai.setUpAistatus("2");//未拨打
									uai.setUpAicreatetime(date);//创建时间
									uai.setUpUsercode(userCode);//用户编码
									uai.setUpAitimes(1);//批次
									
									//更新线索最新匹配时间
									Static.iUpClueService.updateMatchTime(hm.get("up_aicode").toString(), date);
									
									//新增匹配线索
									Static.iUpAiInfoService.addUpAiInfo(uai);
								}
								
								//更新用户表首次匹配时间，最新批次
								jsm.setMatchOnedate(date);
								jsm.setAiTimes(1);
								Static.iMeberService.updateByPrimaryKey(jsm);
								
								logger.info("用户["+userCode+"]线索匹配完成");
							}
						}
						//不是初次匹配
						else {
							//系统时间与上传时间差
							try {
								Date d1 = df.parse(sysdate);
								String firstMatchDate = df.format(onedate);
								Date d2 = df.parse(firstMatchDate);
								long days = (d1.getTime()-d2.getTime()+1000000)/(60*60*24*1000);
								c = days % 30;
							} catch (ParseException e) {
								e.printStackTrace();
							}
							if( c == 0) {
								if(grade.equals("2")) {
									//会员等级2 匹配数等于上传总数
									matchCount = count;
								}else if(grade.equals("3")) {
									//会员等级3 匹配数等于上传总数3倍
									matchCount = 3*count;
								}
								//当前用户匹配的线索
								exitsMatchList = Static.iUpAiInfoService.getExistClue(userCode);
								while(matchList.size()<matchCount) {
									n+=1;
									values = AddressUtil.findNeighPosition(longitude, latitude, n);
									matchList = Static.iUpClueService.getMatchClue(jsm.getAccountNumber(),jsm.getOrganClass(),values[0].toString(),values[1].toString(),values[2].toString(),values[3].toString());
									//去掉匹配过得线索
									if(matchList != null && !matchList.isEmpty() && exitsMatchList != null && !exitsMatchList.isEmpty()) {
										matchList.removeAll(exitsMatchList);
									}
								}
								//截取匹配数的线索
								if(matchList.size()>=matchCount) {
									matchListxx = matchList.subList(0, matchCount);
								}else if(matchList.size() < matchCount && matchList.size() != 0 ) {
									matchListxx = matchList;
								}else if(matchList.size()==0) {
									continue;
								}
								for(int j=0;j<matchListxx.size();j++) {
									hm = (HashMap) matchListxx.get(i);
									id = UUID.randomUUID().toString().replace("-", "");
									uai.setUpId(id);
									uai.setUpAicode(hm.get("up_aicode").toString());//线索编号
									uai.setUpAiphone(hm.get("up_aiphone").toString());//线索手机号
									uai.setUpCluename(hm.get("up_cluename").toString());//线索名称
									uai.setUpAiappraise("0");//意向状态
									uai.setUpAistatus("2");//未拨打
									uai.setUpAicreatetime(date);//创建时间
									uai.setUpUsercode(userCode);//用户编码
									uai.setUpAitimes(times+1);//批次
									
									//更新线索最新匹配时间
									Static.iUpClueService.updateMatchTime(hm.get("up_aicode").toString(), date);
									
									//新增匹配线索
									Static.iUpAiInfoService.addUpAiInfo(uai);
								}
								
								//更新用户表最新批次
								jsm.setAiTimes(times+1);
								Static.iMeberService.updateByPrimaryKey(jsm);
								
								logger.info("用户["+userCode+"]线索匹配完成");
							}
						}
					}
				}
			}
		}
	}
	
	//为会员和线索标注经纬度
	@SuppressWarnings("deprecation")
	@Scheduled(cron="20 56 11 * * ?")
	public void autoConfigAddress() {
		
		//设置会员经纬度
		List<JsSysMember> list = Static.iMeberService.getNoConfigAddress();
		//会员信息
		JsSysMember jsm = null;
		//线索信息
		UpClue uc = null;
		//会员机构地址
		String address = "";
		//机构经度
		Double longitude = null;
		//机构纬度
		Double latitude = null;
		//经纬度值
		Object[] values;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				jsm = list.get(i);
				address = jsm.getOrganAddress();
				//调用百度地图API获取逆地理编码
				try {
					values = AddressUtil.AddressTolongitudea(address);
					if(values != null) {
						longitude = (Double) values[0];
						latitude = (Double) values[1];
					}else {
						longitude = 0.0;
						latitude = 0.0;
					}
					jsm.setMatchLongitude(longitude);
					jsm.setMatchLatitude(latitude);
					jsm.setUpIseffective("1");
					
					//更新会员机构经纬度
					Static.iMeberService.updateByPrimaryKey(jsm);
				} catch (Exception e) {
					e.printStackTrace();
					//设置为无效用户，需重新录入地址
					jsm.setUpIseffective("0");
					Static.iMeberService.updateByPrimaryKey(jsm);
					continue;
				}
			}
		}
		
		
		//设置线索经纬度
		List<UpClue> listUp = Static.iUpClueService.getNoConfigAddress();
		if(listUp != null && !listUp.isEmpty()) {
			for(int i=0;i<listUp.size();i++) {
				uc = listUp.get(i);
				address = uc.getUpClueAddre();
				//调用百度地图API获取逆地理编码
				try {
					values = AddressUtil.AddressTolongitudea(address);
					if(values != null) {
						longitude = (Double) values[0];
						latitude = (Double) values[1];
					}else {
						longitude = 0.0;
						latitude = 0.0;
					}
					uc.setUpClueLongitude(longitude);
					uc.setUpClueLatitude(latitude);
					uc.setUpIseffective("1");
					
					//更新线索经纬度
					Static.iUpClueService.updateByPrimaryKey(uc);
				} catch (Exception e) {
					e.printStackTrace();
					//设置为无效线索，需重新录入地址
					uc.setUpIseffective("0");
					Static.iUpClueService.updateByPrimaryKey(uc);
					continue;
				}
			}
		}
	}
	
	//获取AI外呼公钥
	@SuppressWarnings("deprecation")
	@Scheduled(cron="40 30 17 * * ?")
	public void getAIToken() {
		
		logger.info("获取新的外呼公钥开始！");
		
		String token = AiUtil.getAccessToken();
		if(token != null && !"".equals(token)) {
			SysDictData sdd = Static.iSysDictDataService.getDictDataById("1");
			if(sdd !=null) {
				logger.info("当前token:"+sdd.getDictValue());
				sdd.setDictValue(token);
				logger.info("新token:"+sdd.getDictValue());
			}
			Static.iSysDictDataService.updateByPrimaryKey(sdd);
			
			logger.info("获取新的外呼公钥成功！");
		}else {
			logger.info("获取新的外呼公钥失败！未获取到公钥");
		}
	}
}
