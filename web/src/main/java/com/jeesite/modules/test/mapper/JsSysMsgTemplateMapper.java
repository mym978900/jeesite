package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysMsgTemplate;

public interface JsSysMsgTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysMsgTemplate record);

    int insertSelective(JsSysMsgTemplate record);

    JsSysMsgTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysMsgTemplate record);

    int updateByPrimaryKeyWithBLOBs(JsSysMsgTemplate record);

    int updateByPrimaryKey(JsSysMsgTemplate record);
}