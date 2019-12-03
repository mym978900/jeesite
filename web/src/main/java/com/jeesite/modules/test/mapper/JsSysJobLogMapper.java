package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysJobLog;

public interface JsSysJobLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysJobLog record);

    int insertSelective(JsSysJobLog record);

    JsSysJobLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysJobLog record);

    int updateByPrimaryKeyWithBLOBs(JsSysJobLog record);

    int updateByPrimaryKey(JsSysJobLog record);
}