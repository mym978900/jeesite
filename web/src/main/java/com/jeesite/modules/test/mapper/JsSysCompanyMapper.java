package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysCompany;

public interface JsSysCompanyMapper {
    int deleteByPrimaryKey(String companyCode);

    int insert(JsSysCompany record);

    int insertSelective(JsSysCompany record);

    JsSysCompany selectByPrimaryKey(String companyCode);

    int updateByPrimaryKeySelective(JsSysCompany record);

    int updateByPrimaryKey(JsSysCompany record);
}