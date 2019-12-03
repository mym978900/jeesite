package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobBlobTriggers;
import com.jeesite.modules.test.entity.JsJobBlobTriggersKey;

public interface JsJobBlobTriggersMapper {
    int deleteByPrimaryKey(JsJobBlobTriggersKey key);

    int insert(JsJobBlobTriggers record);

    int insertSelective(JsJobBlobTriggers record);

    JsJobBlobTriggers selectByPrimaryKey(JsJobBlobTriggersKey key);

    int updateByPrimaryKeySelective(JsJobBlobTriggers record);

    int updateByPrimaryKeyWithBLOBs(JsJobBlobTriggers record);
}