package com.jeesite.modules.clue.service.impl;

import java.util.List;

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
	
	//通过id获取任务对象
	@Override
	public UpAitask getUpAitaskBytaskId(String taskId) {
		UpAitask uat = upAitaskMapper.getUpAitaskBytaskId(taskId);
		return uat;
	}

	//通过任务id更新任务对象
	@Override
	public void updateAitask(UpAitask upAitask) {
		upAitaskMapper.updateAitask(upAitask);
	}


}
