package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysDictType;

public interface JsSysDictTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysDictType record);

    int insertSelective(JsSysDictType record);

    JsSysDictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysDictType record);

    int updateByPrimaryKey(JsSysDictType record);
}