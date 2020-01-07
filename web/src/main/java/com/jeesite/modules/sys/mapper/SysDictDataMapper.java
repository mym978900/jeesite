package com.jeesite.modules.sys.mapper;

import com.jeesite.modules.sys.entity.SysDictData;

public interface SysDictDataMapper {
    int deleteByPrimaryKey(String dictCode);

    int insert(SysDictData record);

    int insertSelective(SysDictData record);

    SysDictData selectByPrimaryKey(String dictCode);

    int updateByPrimaryKeySelective(SysDictData record);

    int updateByPrimaryKey(SysDictData record);
}