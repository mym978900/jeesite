package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysArea;

public interface JsSysAreaMapper {
    int deleteByPrimaryKey(String areaCode);

    int insert(JsSysArea record);

    int insertSelective(JsSysArea record);

    JsSysArea selectByPrimaryKey(String areaCode);

    int updateByPrimaryKeySelective(JsSysArea record);

    int updateByPrimaryKey(JsSysArea record);
}