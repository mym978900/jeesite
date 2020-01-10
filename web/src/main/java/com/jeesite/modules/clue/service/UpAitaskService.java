package com.jeesite.modules.clue.service;

import com.jeesite.modules.clue.entity.UpAitask;

public interface UpAitaskService {

	//通过任务id获取任务对象
	UpAitask getUpAitaskBytaskId(String taskId);

	//通过任务id更新任务对象
	void updateAitask(UpAitask upAitask);

}
