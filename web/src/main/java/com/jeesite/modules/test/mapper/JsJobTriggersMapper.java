package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobTriggers;
import com.jeesite.modules.test.entity.JsJobTriggersKey;

public interface JsJobTriggersMapper {
    int deleteByPrimaryKey(JsJobTriggersKey key);

    int insert(JsJobTriggers record);

    int insertSelective(JsJobTriggers record);

    JsJobTriggers selectByPrimaryKey(JsJobTriggersKey key);

    int updateByPrimaryKeySelective(JsJobTriggers record);

    int updateByPrimaryKeyWithBLOBs(JsJobTriggers record);

    int updateByPrimaryKey(JsJobTriggers record);
}