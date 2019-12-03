package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobCronTriggers;
import com.jeesite.modules.test.entity.JsJobCronTriggersKey;

public interface JsJobCronTriggersMapper {
    int deleteByPrimaryKey(JsJobCronTriggersKey key);

    int insert(JsJobCronTriggers record);

    int insertSelective(JsJobCronTriggers record);

    JsJobCronTriggers selectByPrimaryKey(JsJobCronTriggersKey key);

    int updateByPrimaryKeySelective(JsJobCronTriggers record);

    int updateByPrimaryKey(JsJobCronTriggers record);
}