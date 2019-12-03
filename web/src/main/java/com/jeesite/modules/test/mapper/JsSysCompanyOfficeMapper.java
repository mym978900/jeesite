package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysCompanyOfficeKey;

public interface JsSysCompanyOfficeMapper {
    int deleteByPrimaryKey(JsSysCompanyOfficeKey key);

    int insert(JsSysCompanyOfficeKey record);

    int insertSelective(JsSysCompanyOfficeKey record);
}