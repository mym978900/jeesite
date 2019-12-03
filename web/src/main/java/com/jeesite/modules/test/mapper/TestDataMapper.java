package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.TestData;

public interface TestDataMapper {
    int deleteByPrimaryKey(String id);

    int insert(TestData record);

    int insertSelective(TestData record);

    TestData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TestData record);

    int updateByPrimaryKey(TestData record);
}