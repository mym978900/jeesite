package com.jeesite.modules.sys.mapper;

import com.jeesite.modules.sys.entity.SysDictType;

public interface SysDictTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);
}