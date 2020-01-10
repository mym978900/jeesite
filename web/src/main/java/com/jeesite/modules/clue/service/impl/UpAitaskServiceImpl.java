package com.jeesite.modules.clue.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.clue.mapper.UpAitaskMapper;
import com.jeesite.modules.clue.service.UpAitaskService;
import com.jeesite.modules.clue.vo.AiInfoVo;

@Service
public class UpAitaskServiceImpl implements UpAitaskService {

	@Autowired
	private UpAitaskMapper upAitaskMapper;
	
	//新增外呼任务记录
	@Override
	public void addUpAitask(UpAitask ut) {
		upAitaskMapper.insert(ut);
	}

	//获取外呼任务记录通过任务id，线索id，用户id
	@Override
	public UpAitask getUpAitaskByUpCodeTaskId(String taskId, String upClueCode) {
		UpAitask uat = upAitaskMapper.getUpAitaskByUpCodeTaskId(taskId,upClueCode);
		return uat;
	}

	@Override
	public void updateByPrimaryKey(UpAitask upAitask) {
		upAitaskMapper.updateByPrimaryKey(upAitask);
	}


}
