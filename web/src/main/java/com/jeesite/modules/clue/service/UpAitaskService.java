package com.jeesite.modules.clue.service;

import java.util.List;
import java.util.Map;

import com.jeesite.modules.clue.entity.UpAitask;

public interface UpAitaskService {

	//新增外呼任务记录
	void addUpAitask(UpAitask ut);

	//获取外呼任务记录通过任务id，线索id，用户id
	UpAitask getUpAitaskByUpCodeTaskId(String taskId, String upClueCode);

	void updateByPrimaryKey(UpAitask upAitask);

}
