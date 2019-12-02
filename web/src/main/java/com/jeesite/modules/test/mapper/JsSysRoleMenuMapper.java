package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysRoleMenuKey;

public interface JsSysRoleMenuMapper {
    int deleteByPrimaryKey(JsSysRoleMenuKey key);

    int insert(JsSysRoleMenuKey record);

    int insertSelective(JsSysRoleMenuKey record);
}