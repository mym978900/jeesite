package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobCalendars;
import com.jeesite.modules.test.entity.JsJobCalendarsKey;

public interface JsJobCalendarsMapper {
    int deleteByPrimaryKey(JsJobCalendarsKey key);

    int insert(JsJobCalendars record);

    int insertSelective(JsJobCalendars record);

    JsJobCalendars selectByPrimaryKey(JsJobCalendarsKey key);

    int updateByPrimaryKeySelective(JsJobCalendars record);

    int updateByPrimaryKeyWithBLOBs(JsJobCalendars record);
}