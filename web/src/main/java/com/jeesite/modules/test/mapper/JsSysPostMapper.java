package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysPost;

public interface JsSysPostMapper {
    int deleteByPrimaryKey(String postCode);

    int insert(JsSysPost record);

    int insertSelective(JsSysPost record);

    JsSysPost selectByPrimaryKey(String postCode);

    int updateByPrimaryKeySelective(JsSysPost record);

    int updateByPrimaryKey(JsSysPost record);
}