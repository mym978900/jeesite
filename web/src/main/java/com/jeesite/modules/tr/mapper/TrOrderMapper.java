package com.jeesite.modules.tr.mapper;

import com.jeesite.modules.tr.entity.TrOrder;

public interface TrOrderMapper {
    int deleteByPrimaryKey(String trCommonditycode);

    int insert(TrOrder record);

    int insertSelective(TrOrder record);

    TrOrder selectByPrimaryKey(String trCommonditycode);

    int updateByPrimaryKeySelective(TrOrder record);

    int updateByPrimaryKey(TrOrder record);
}