package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobJobDetails;
import com.jeesite.modules.test.entity.JsJobJobDetailsKey;

public interface JsJobJobDetailsMapper {
    int deleteByPrimaryKey(JsJobJobDetailsKey key);

    int insert(JsJobJobDetails record);

    int insertSelective(JsJobJobDetails record);

    JsJobJobDetails selectByPrimaryKey(JsJobJobDetailsKey key);

    int updateByPrimaryKeySelective(JsJobJobDetails record);

    int updateByPrimaryKeyWithBLOBs(JsJobJobDetails record);

    int updateByPrimaryKey(JsJobJobDetails record);
}