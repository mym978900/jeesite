package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysUserRoleKey;

public interface JsSysUserRoleMapper {
    int deleteByPrimaryKey(JsSysUserRoleKey key);

    int insert(JsSysUserRoleKey record);

    int insertSelective(JsSysUserRoleKey record);
}