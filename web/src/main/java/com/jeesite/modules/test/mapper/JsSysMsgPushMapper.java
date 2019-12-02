package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysMsgPush;
import com.jeesite.modules.test.entity.JsSysMsgPushWithBLOBs;

public interface JsSysMsgPushMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysMsgPushWithBLOBs record);

    int insertSelective(JsSysMsgPushWithBLOBs record);

    JsSysMsgPushWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysMsgPushWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JsSysMsgPushWithBLOBs record);

    int updateByPrimaryKey(JsSysMsgPush record);
}