package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysEmployee;

public interface JsSysEmployeeMapper {
    int deleteByPrimaryKey(String empCode);

    int insert(JsSysEmployee record);

    int insertSelective(JsSysEmployee record);

    JsSysEmployee selectByPrimaryKey(String empCode);

    int updateByPrimaryKeySelective(JsSysEmployee record);

    int updateByPrimaryKey(JsSysEmployee record);
}