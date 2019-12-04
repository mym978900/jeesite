package com.jeesite.modules.clue.mapper;

import java.util.List;

import com.jeesite.modules.clue.entity.UpClue;

public interface UpClueMapper {
    int deleteByPrimaryKey(String upClueCode);

    int insert(UpClue record);

    int insertSelective(UpClue record);

    UpClue selectByPrimaryKey(String upClueCode);

    int updateByPrimaryKeySelective(UpClue record);

    int updateByPrimaryKey(UpClue record);
    
    //获取用户上传线索列表
    //xf
    //2019.12.03
    List<UpClue> getUpClueList();
}