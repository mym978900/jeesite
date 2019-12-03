package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysModule;

public interface JsSysModuleMapper {
    int deleteByPrimaryKey(String moduleCode);

    int insert(JsSysModule record);

    int insertSelective(JsSysModule record);

    JsSysModule selectByPrimaryKey(String moduleCode);

    int updateByPrimaryKeySelective(JsSysModule record);

    int updateByPrimaryKey(JsSysModule record);
}