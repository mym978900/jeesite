package com.jeesite.modules.clue.mapper;


import java.util.List;

import java.util.Map;


import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.clue.vo.AiTaskVo;
import com.jeesite.modules.test.vo.AitaskVo;

public interface UpAitaskMapper {
	int deleteByPrimaryKey(String upId);

    int insert(UpAitask record);

    int insertSelective(UpAitask record);

    UpAitask selectByPrimaryKey(String upId);


	//通过任务id更新任务对象
	void updateAitask(UpAitask upAitask);

	List<AitaskVo> getDateStatisticsByDay();

	List<AitaskVo> getDateStatisticsByMonth();

    int updateByPrimaryKeySelective(UpAitask record);

    int updateByPrimaryKey(UpAitask record);

	//获取外呼任务记录通过任务id，线索id
	UpAitask getUpAitaskByUpCodeTaskId(String taskId, String upClueCode);

	//更新外呼状态
	void updateByTaskId(String status, String taskId);

	List<UpAitask> getAiTask(AiTaskVo atv);

	List<UpAitask> getAitaskList(String userCode);

	List<UpAitask> getAiTaskBytaskId(String taskId);
}