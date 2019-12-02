package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsFilemanagerShared;

public interface JsFilemanagerSharedMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsFilemanagerShared record);

    int insertSelective(JsFilemanagerShared record);

    JsFilemanagerShared selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsFilemanagerShared record);

    int updateByPrimaryKey(JsFilemanagerShared record);
}