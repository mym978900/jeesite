package com.jeesite.modules.tr.mapper;

import java.util.List;

import com.jeesite.modules.tr.entity.TrAbility;
import com.jeesite.modules.tr.entity.TrNeed;

public interface TrAbilityMapper {
    int deleteByPrimaryKey(String trId);

    int insert(TrAbility record);

    int insertSelective(TrAbility record);

    TrAbility selectByPrimaryKey(String trId);

    int updateByPrimaryKeySelective(TrAbility record);

    int updateByPrimaryKey(TrAbility record);

	TrAbility getTrAbility(String userCode);
	
	List<TrAbility> matchTrAbilityForNeed(TrNeed tn);
}