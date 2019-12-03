package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysRole;

public interface JsSysRoleMapper {
    int deleteByPrimaryKey(String roleCode);

    int insert(JsSysRole record);

    int insertSelective(JsSysRole record);

    JsSysRole selectByPrimaryKey(String roleCode);

    int updateByPrimaryKeySelective(JsSysRole record);

    int updateByPrimaryKey(JsSysRole record);
}