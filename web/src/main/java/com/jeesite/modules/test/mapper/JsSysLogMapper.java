package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysLog;
import com.jeesite.modules.test.entity.JsSysLogWithBLOBs;

public interface JsSysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysLogWithBLOBs record);

    int insertSelective(JsSysLogWithBLOBs record);

    JsSysLogWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JsSysLogWithBLOBs record);

    int updateByPrimaryKey(JsSysLog record);
}