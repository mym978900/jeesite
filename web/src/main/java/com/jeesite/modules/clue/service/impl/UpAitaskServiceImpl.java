package com.jeesite.modules.clue.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.mapper.UpAitaskMapper;
import com.jeesite.modules.clue.service.UpAitaskService;
import com.jeesite.modules.clue.vo.AiInfoVo;
import com.jeesite.modules.clue.vo.AiTaskVo;

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

	//更新任务状态
	@Override
	public void updateByTaskId(String status,String taskId) {
		upAitaskMapper.updateByTaskId(status,taskId);
	}

	//查询ai外呼和上传机构已拨打数据
	@Override
	public List<UpAitask> getAiTask(AiTaskVo atv) {
		List list = upAitaskMapper.getAiTask(atv);
		return list;
	}

	//获取数据集
	@Override
	public List<UpAitask> getAitaskList(String userCode) {
		List<UpAitask> list = upAitaskMapper.getAitaskList(userCode);
		return list;
	}

	//根据任务Id查看用户userCode
	@Override
	public String getAiTaskBytaskId(String taskId) {
		List<UpAitask> list = upAitaskMapper.getAiTaskBytaskId(taskId);
		String userCode ="";
		if(list != null) {
			userCode = list.get(0).getUpCreateusercode();
		}
		return userCode;
	}


}
