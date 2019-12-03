package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobPausedTriggerGrpsKey;

public interface JsJobPausedTriggerGrpsMapper {
    int deleteByPrimaryKey(JsJobPausedTriggerGrpsKey key);

    int insert(JsJobPausedTriggerGrpsKey record);

    int insertSelective(JsJobPausedTriggerGrpsKey record);
}