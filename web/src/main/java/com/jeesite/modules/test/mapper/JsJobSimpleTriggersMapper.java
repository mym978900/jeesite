package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobSimpleTriggers;
import com.jeesite.modules.test.entity.JsJobSimpleTriggersKey;

public interface JsJobSimpleTriggersMapper {
    int deleteByPrimaryKey(JsJobSimpleTriggersKey key);

    int insert(JsJobSimpleTriggers record);

    int insertSelective(JsJobSimpleTriggers record);

    JsJobSimpleTriggers selectByPrimaryKey(JsJobSimpleTriggersKey key);

    int updateByPrimaryKeySelective(JsJobSimpleTriggers record);

    int updateByPrimaryKey(JsJobSimpleTriggers record);
}