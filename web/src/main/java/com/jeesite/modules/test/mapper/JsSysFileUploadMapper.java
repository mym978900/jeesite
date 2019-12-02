package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysFileUpload;

public interface JsSysFileUploadMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysFileUpload record);

    int insertSelective(JsSysFileUpload record);

    JsSysFileUpload selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysFileUpload record);

    int updateByPrimaryKey(JsSysFileUpload record);
}