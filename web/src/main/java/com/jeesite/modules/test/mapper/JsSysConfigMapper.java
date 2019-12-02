package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysConfig;

public interface JsSysConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysConfig record);

    int insertSelective(JsSysConfig record);

    JsSysConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysConfig record);

    int updateByPrimaryKey(JsSysConfig record);
}