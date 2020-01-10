package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysSeat;

public interface JsSysSeatMapper {
    int deleteByPrimaryKey(String serialNumber);

    int insert(JsSysSeat record);

    int insertSelective(JsSysSeat record);

    JsSysSeat selectByPrimaryKey(String string);

    int updateByPrimaryKeySelective(JsSysSeat record);

    int updateByPrimaryKey(JsSysSeat record);
    
}