package com.jeesite.modules.clue.mapper;

import java.util.List;

import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.test.vo.AitaskVo;

public interface UpAitaskMapper {
    int insert(UpAitask record);

    int insertSelective(UpAitask record);

    //通过id获取任务对象
	UpAitask getUpAitaskBytaskId(String taskId);

	//通过任务id更新任务对象
	void updateAitask(UpAitask upAitask);

	List<AitaskVo> getDateStatisticsByDay();

	List<AitaskVo> getDateStatisticsByMonth();
}