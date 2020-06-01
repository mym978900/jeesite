package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsJobLocksKey;

public interface JsJobLocksMapper {
    int deleteByPrimaryKey(JsJobLocksKey key);

    int insert(JsJobLocksKey record);

    int insertSelective(JsJobLocksKey record);

	JsJobLocksKey selectByPrimaryKey(String string);
}