package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.test.entity.JsSysOffice;
import com.jeesite.modules.test.entity.JsSysUser;

public interface JsSysOfficeMapper {
    int deleteByPrimaryKey(String officeCode);

    int insert(JsSysOffice record);

    int insertSelective(JsSysOffice record);

    JsSysOffice selectByPrimaryKey(String officeCode);

    int updateByPrimaryKeySelective(JsSysOffice record);

    int updateByPrimaryKey(JsSysOffice record);
    
    List<JsSysOffice> selectByUser(User user);
}