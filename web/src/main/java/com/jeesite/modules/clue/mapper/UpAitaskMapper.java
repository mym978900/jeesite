package com.jeesite.modules.clue.mapper;

import com.jeesite.modules.clue.entity.UpAitask;

public interface UpAitaskMapper {
    int insert(UpAitask record);

    int insertSelective(UpAitask record);

    //通过id获取任务对象
	UpAitask getUpAitaskBytaskId(String taskId);

	//通过任务id更新任务对象
	void updateAitask(UpAitask upAitask);
}