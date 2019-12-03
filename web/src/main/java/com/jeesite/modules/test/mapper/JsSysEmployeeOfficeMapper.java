package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysEmployeeOffice;
import com.jeesite.modules.test.entity.JsSysEmployeeOfficeKey;

public interface JsSysEmployeeOfficeMapper {
    int deleteByPrimaryKey(JsSysEmployeeOfficeKey key);

    int insert(JsSysEmployeeOffice record);

    int insertSelective(JsSysEmployeeOffice record);

    JsSysEmployeeOffice selectByPrimaryKey(JsSysEmployeeOfficeKey key);

    int updateByPrimaryKeySelective(JsSysEmployeeOffice record);

    int updateByPrimaryKey(JsSysEmployeeOffice record);
}