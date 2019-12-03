package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.UpClue;

public interface UpClueMapper {
    int deleteByPrimaryKey(String upClueCode);

    int insert(UpClue record);

    int insertSelective(UpClue record);

    UpClue selectByPrimaryKey(String upClueCode);

    int updateByPrimaryKeySelective(UpClue record);

    int updateByPrimaryKey(UpClue record);
}