package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysUserDataScopeKey;

public interface JsSysUserDataScopeMapper {
    int deleteByPrimaryKey(JsSysUserDataScopeKey key);

    int insert(JsSysUserDataScopeKey record);

    int insertSelective(JsSysUserDataScopeKey record);
}