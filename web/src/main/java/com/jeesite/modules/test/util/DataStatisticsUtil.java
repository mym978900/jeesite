package com.jeesite.modules.test.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeesite.common.utils.SpringUtils;
import com.jeesite.modules.pay.service.CostService;
import com.jeesite.modules.test.vo.AitaskBackVo;
import com.jeesite.modules.test.vo.AitaskVo;

@Component
public class DataStatisticsUtil {
	@Autowired
	private static CostService costService = SpringUtils.getBean(CostService.class);

	public static List<AitaskBackVo> getDayStatistics() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		List<AitaskVo> aitaskVos = costService.getDateStatistics();
		List<AitaskBackVo> vo=new ArrayList<AitaskBackVo>();
		for (int i = 11; i >= 0; i--) {
			String dateStr = DailyUtil.getDayAgo(i);
			Integer timeNum = 0;
			for (int j = 0; j < aitaskVos.size(); j++) {
				AitaskVo aitaskVo = aitaskVos.get(j);
				if (dateStr.equals(aitaskVo.getDay())) {
					timeNum = aitaskVo.getTime();
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
			String dateStr = DailyUtil.getMonthAgo(i);
			Integer timeNum = 0;
			for (int j = 0; j < aitaskVos.size(); j++) {
				AitaskVo aitaskVo = aitaskVos.get(j);
				if (dateStr.equals(aitaskVo.getDay())) {
					timeNum = aitaskVo.getTime();
					break;
				}
			}
			AitaskBackVo v=new AitaskBackVo(dateStr, timeNum);
			vo.add(v);
		}

		return vo;
	}
}
