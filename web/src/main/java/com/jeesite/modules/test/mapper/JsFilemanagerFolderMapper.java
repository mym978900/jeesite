package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsFilemanagerFolder;

public interface JsFilemanagerFolderMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsFilemanagerFolder record);

    int insertSelective(JsFilemanagerFolder record);

    JsFilemanagerFolder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsFilemanagerFolder record);

    int updateByPrimaryKey(JsFilemanagerFolder record);
}