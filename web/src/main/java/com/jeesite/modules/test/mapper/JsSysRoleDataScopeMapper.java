package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysRoleDataScopeKey;

public interface JsSysRoleDataScopeMapper {
    int deleteByPrimaryKey(JsSysRoleDataScopeKey key);

    int insert(JsSysRoleDataScopeKey record);

    int insertSelective(JsSysRoleDataScopeKey record);
}