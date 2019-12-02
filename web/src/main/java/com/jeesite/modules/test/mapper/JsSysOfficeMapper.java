package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysOffice;

public interface JsSysOfficeMapper {
    int deleteByPrimaryKey(String officeCode);

    int insert(JsSysOffice record);

    int insertSelective(JsSysOffice record);

    JsSysOffice selectByPrimaryKey(String officeCode);

    int updateByPrimaryKeySelective(JsSysOffice record);

    int updateByPrimaryKey(JsSysOffice record);
}