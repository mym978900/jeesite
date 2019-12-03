package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysUser;

public interface JsSysUserMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(JsSysUser record);

    int insertSelective(JsSysUser record);

    JsSysUser selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(JsSysUser record);

    int updateByPrimaryKey(JsSysUser record);
}