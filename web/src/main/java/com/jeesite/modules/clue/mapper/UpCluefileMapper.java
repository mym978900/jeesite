package com.jeesite.modules.clue.mapper;

import com.jeesite.modules.clue.entity.UpCluefile;

public interface UpCluefileMapper {
    int insert(UpCluefile record);

    int insertSelective(UpCluefile record);
}