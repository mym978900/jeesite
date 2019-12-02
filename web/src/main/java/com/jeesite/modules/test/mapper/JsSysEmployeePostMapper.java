package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysEmployeePostKey;

public interface JsSysEmployeePostMapper {
    int deleteByPrimaryKey(JsSysEmployeePostKey key);

    int insert(JsSysEmployeePostKey record);

    int insertSelective(JsSysEmployeePostKey record);
}