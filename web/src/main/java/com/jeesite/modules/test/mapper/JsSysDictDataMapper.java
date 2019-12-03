package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysDictData;

public interface JsSysDictDataMapper {
    int deleteByPrimaryKey(String dictCode);

    int insert(JsSysDictData record);

    int insertSelective(JsSysDictData record);

    JsSysDictData selectByPrimaryKey(String dictCode);

    int updateByPrimaryKeySelective(JsSysDictData record);

    int updateByPrimaryKey(JsSysDictData record);
}