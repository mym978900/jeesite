package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobFiredTriggers;
import com.jeesite.modules.test.entity.JsJobFiredTriggersKey;

public interface JsJobFiredTriggersMapper {
    int deleteByPrimaryKey(JsJobFiredTriggersKey key);

    int insert(JsJobFiredTriggers record);

    int insertSelective(JsJobFiredTriggers record);

    JsJobFiredTriggers selectByPrimaryKey(JsJobFiredTriggersKey key);

    int updateByPrimaryKeySelective(JsJobFiredTriggers record);

    int updateByPrimaryKey(JsJobFiredTriggers record);
}