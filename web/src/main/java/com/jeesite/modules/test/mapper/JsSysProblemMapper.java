package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysProblem;

public interface JsSysProblemMapper {
    int deleteByPrimaryKey(String serialNum);

    int insert(JsSysProblem record);

    int insertSelective(JsSysProblem record);

    JsSysProblem selectByPrimaryKey(String serialNum);

    int updateByPrimaryKeySelective(JsSysProblem record);

    int updateByPrimaryKey(JsSysProblem record);
}