package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsGenTableColumn;

public interface JsGenTableColumnMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsGenTableColumn record);

    int insertSelective(JsGenTableColumn record);

    JsGenTableColumn selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsGenTableColumn record);

    int updateByPrimaryKey(JsGenTableColumn record);
}