package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobSchedulerState;
import com.jeesite.modules.test.entity.JsJobSchedulerStateKey;

public interface JsJobSchedulerStateMapper {
    int deleteByPrimaryKey(JsJobSchedulerStateKey key);

    int insert(JsJobSchedulerState record);

    int insertSelective(JsJobSchedulerState record);

    JsJobSchedulerState selectByPrimaryKey(JsJobSchedulerStateKey key);

    int updateByPrimaryKeySelective(JsJobSchedulerState record);

    int updateByPrimaryKey(JsJobSchedulerState record);
}