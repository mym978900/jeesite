package com.jeesite.modules.clue.mapper;

import java.util.Map;

import com.jeesite.modules.clue.entity.UpAitask;

public interface UpAitaskMapper {
	int deleteByPrimaryKey(String upId);

    int insert(UpAitask record);

    int insertSelective(UpAitask record);

    UpAitask selectByPrimaryKey(String upId);

    int updateByPrimaryKeySelective(UpAitask record);

    int updateByPrimaryKey(UpAitask record);

	//获取外呼任务记录通过任务id，线索id
	UpAitask getUpAitaskByUpCodeTaskId(String taskId, String upClueCode);
}