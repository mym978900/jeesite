package com.jeesite.modules.clue.service;

import java.util.List;

import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.vo.AiInfoVo;
import com.jeesite.modules.clue.vo.ClueVo;

/**
 * 线索匹配管理Service
 * @author xf
 * @version 2019.12.13
 */
public interface UpAiInfoService {

	//匹配过得线索资源
	public List getExistClue(String userId);
	
	//新增单条信息
	public void addUpAiInfo(UpAiinfo uai);
	
	//线索数据共享列表
	public List<UpAiinfo> getUpAiInfoList(AiInfoVo aif);

	//根据用户编码和线索编码更新智能匹配线索信息
	public void updateAiInfoByUserCodeUpClueCode(String upClueTaskid,String userCode, String upClueCode);

	//根据用户编码和线索编码获取智能匹配线索信息
	public UpAiinfo getMatchClueByUserCodeAndClueCode(String upClueCode, String userCode);

	//根据用户编码和线索编码更新智能匹配线索信息
	public void updateByUserCodeAndClueCode(UpAiinfo upAiInfo);
}
