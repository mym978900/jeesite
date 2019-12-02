package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.TestTree;

public interface TestTreeMapper {
    int deleteByPrimaryKey(String treeCode);

    int insert(TestTree record);

    int insertSelective(TestTree record);

    TestTree selectByPrimaryKey(String treeCode);

    int updateByPrimaryKeySelective(TestTree record);

    int updateByPrimaryKey(TestTree record);
}