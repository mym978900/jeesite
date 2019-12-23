package com.jeesite.modules.clue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.mapper.UpAiinfoMapper;
import com.jeesite.modules.clue.service.UpAiInfoService;
import com.jeesite.modules.clue.vo.AiInfoVo;

@Service
public class UpAiInfoServiceImpl implements UpAiInfoService {
	
	@Autowired
	private UpAiinfoMapper upAiinfoMapper;
	
	//匹配过得线索资源
	@Override
	public List getExistClue(String userId) {
		List list = upAiinfoMapper.getExistClue(userId);
		if(list!=null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	//新增单条信息
	@Override
	public void addUpAiInfo(UpAiinfo uai) {
		if(uai != null) {
			upAiinfoMapper.insert(uai);
		}
	}

	//线索数据共享列表
	@Override
	public List<UpAiinfo> getUpAiInfoList(AiInfoVo uai) {
		List<UpAiinfo> list = upAiinfoMapper.getUpAiInfoList(uai);
		return list;
	}

}
