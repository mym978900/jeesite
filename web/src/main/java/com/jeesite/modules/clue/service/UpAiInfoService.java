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
}
