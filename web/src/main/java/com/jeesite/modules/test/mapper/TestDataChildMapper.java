package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.TestDataChild;

public interface TestDataChildMapper {
    int deleteByPrimaryKey(String id);

    int insert(TestDataChild record);

    int insertSelective(TestDataChild record);

    TestDataChild selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TestDataChild record);

    int updateByPrimaryKey(TestDataChild record);
}