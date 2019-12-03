package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysMsgInner;
import com.jeesite.modules.test.entity.JsSysMsgInnerWithBLOBs;

public interface JsSysMsgInnerMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysMsgInnerWithBLOBs record);

    int insertSelective(JsSysMsgInnerWithBLOBs record);

    JsSysMsgInnerWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysMsgInnerWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JsSysMsgInnerWithBLOBs record);

    int updateByPrimaryKey(JsSysMsgInner record);
}