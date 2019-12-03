package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsGenTable;

public interface JsGenTableMapper {
    int deleteByPrimaryKey(String tableName);

    int insert(JsGenTable record);

    int insertSelective(JsGenTable record);

    JsGenTable selectByPrimaryKey(String tableName);

    int updateByPrimaryKeySelective(JsGenTable record);

    int updateByPrimaryKey(JsGenTable record);
}