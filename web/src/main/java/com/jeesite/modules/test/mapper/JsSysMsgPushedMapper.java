package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysMsgPushed;
import com.jeesite.modules.test.entity.JsSysMsgPushedWithBLOBs;

public interface JsSysMsgPushedMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysMsgPushedWithBLOBs record);

    int insertSelective(JsSysMsgPushedWithBLOBs record);

    JsSysMsgPushedWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysMsgPushedWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JsSysMsgPushedWithBLOBs record);

    int updateByPrimaryKey(JsSysMsgPushed record);
}