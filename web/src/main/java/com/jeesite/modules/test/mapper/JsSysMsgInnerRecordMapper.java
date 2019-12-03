package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysMsgInnerRecord;

public interface JsSysMsgInnerRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysMsgInnerRecord record);

    int insertSelective(JsSysMsgInnerRecord record);

    JsSysMsgInnerRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysMsgInnerRecord record);

    int updateByPrimaryKey(JsSysMsgInnerRecord record);
}