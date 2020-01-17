package com.jeesite.modules.clue.mapper;

import com.jeesite.modules.clue.entity.UpCluefile;

public interface UpCluefileMapper {
	int deleteByPrimaryKey(String upId);

    int insert(UpCluefile record);

    int insertSelective(UpCluefile record);

    UpCluefile selectByPrimaryKey(String upId);

    int updateByPrimaryKeySelective(UpCluefile record);

    int updateByPrimaryKey(UpCluefile record);
}