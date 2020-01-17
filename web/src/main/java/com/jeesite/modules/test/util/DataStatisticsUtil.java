package com.jeesite.modules.test.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeesite.modules.pay.service.CostService;
import com.jeesite.modules.test.vo.AitaskBackVo;
import com.jeesite.modules.test.vo.AitaskVo;

public class DataStatisticsUtil {
	@Autowired
	private static CostService costService;
	
	public static List<AitaskBackVo> getDayStatistics() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		List<AitaskVo> aitaskVos = costService.getDateStatistics();
		List<AitaskBackVo> vo=new ArrayList<AitaskBackVo>();
		for (int i = 11; i >= 0; i--) {
			String dateStr=DailyUtil.getDayAgo(i);
			System.out.println("sssssssssss"+dateStr);
			Integer timeNum=0;
			for (int j = 0; j < aitaskVos.size(); j++) {
				AitaskVo aitaskVo = aitaskVos.get(j);
				String day = sdf1.format(aitaskVo.getDay());
				if (dateStr.equals(day)) {
					timeNum=aitaskVo.getTime();
					break;
				}
			}
			AitaskBackVo v=new AitaskBackVo(dateStr, timeNum);
			vo.add(v);
		}

		return vo; 
	}

	public static List<AitaskBackVo> getMonthStatistics() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		List<AitaskVo> aitaskVos = costService.getMonthStatistics();
		List<AitaskBackVo> vo=new ArrayList<AitaskBackVo>();
		for (int i = 11; i >= 0; i--) {
			String dateStr=DailyUtil.getMonthAgo(i);
			System.out.println("sssssssssss"+dateStr);
			Integer timeNum=0;
			for (int j = 0; j < aitaskVos.size(); j++) {
				AitaskVo aitaskVo = aitaskVos.get(j);
				String day = sdf1.format(aitaskVo.getDay());
				if (dateStr.equals(day)) {
					timeNum=aitaskVo.getTime();
					break;
				}
			}
			AitaskBackVo v=new AitaskBackVo(dateStr, timeNum);
			vo.add(v);
		}

		return vo;
	}
}
