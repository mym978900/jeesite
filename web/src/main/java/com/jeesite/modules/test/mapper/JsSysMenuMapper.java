package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysMenu;

public interface JsSysMenuMapper {
    int deleteByPrimaryKey(String menuCode);

    int insert(JsSysMenu record);

    int insertSelective(JsSysMenu record);

    JsSysMenu selectByPrimaryKey(String menuCode);

    int updateByPrimaryKeySelective(JsSysMenu record);

    int updateByPrimaryKey(JsSysMenu record);
}