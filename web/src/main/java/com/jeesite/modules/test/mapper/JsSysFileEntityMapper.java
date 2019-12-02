package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysFileEntity;

public interface JsSysFileEntityMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(JsSysFileEntity record);

    int insertSelective(JsSysFileEntity record);

    JsSysFileEntity selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(JsSysFileEntity record);

    int updateByPrimaryKey(JsSysFileEntity record);
}