package com.jeesite.modules.clue.service;

import java.util.List;
import java.util.Map;

import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.vo.AiTaskVo;

public interface UpAitaskService {

	//新增外呼任务记录
	void addUpAitask(UpAitask ut);

	//获取外呼任务记录通过任务id，线索id，用户id
	UpAitask getUpAitaskByUpCodeTaskId(String taskId, String upClueCode);

	void updateByPrimaryKey(UpAitask upAitask);

	//更新任务状态
	void updateByTaskId(String status, String taskId);

	//查询ai外呼和上传机构已拨打数据
	List<UpAitask> getAiTask(AiTaskVo atv);

	List getAitaskList(String userCode);

	//根据任务Id查看用户userCode
	String getAiTaskBytaskId(String taskId);

	//获取当天拨打的用户手机号和数量
	List getTodayAitask();

}
