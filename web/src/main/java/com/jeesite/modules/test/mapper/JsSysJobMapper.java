package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysJob;
import com.jeesite.modules.test.entity.JsSysJobKey;

public interface JsSysJobMapper {
    int deleteByPrimaryKey(JsSysJobKey key);

    int insert(JsSysJob record);

    int insertSelective(JsSysJob record);

    JsSysJob selectByPrimaryKey(JsSysJobKey key);

    int updateByPrimaryKeySelective(JsSysJob record);

    int updateByPrimaryKey(JsSysJob record);
}