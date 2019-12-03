package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobSimpropTriggers;
import com.jeesite.modules.test.entity.JsJobSimpropTriggersKey;

public interface JsJobSimpropTriggersMapper {
    int deleteByPrimaryKey(JsJobSimpropTriggersKey key);

    int insert(JsJobSimpropTriggers record);

    int insertSelective(JsJobSimpropTriggers record);

    JsJobSimpropTriggers selectByPrimaryKey(JsJobSimpropTriggersKey key);

    int updateByPrimaryKeySelective(JsJobSimpropTriggers record);

    int updateByPrimaryKey(JsJobSimpropTriggers record);
}