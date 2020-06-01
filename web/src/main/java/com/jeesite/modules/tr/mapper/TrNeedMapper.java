package com.jeesite.modules.tr.mapper;

import java.util.List;

import com.jeesite.modules.tr.entity.TrAbility;
import com.jeesite.modules.tr.entity.TrNeed;
import com.jeesite.modules.tr.vo.TrNeedVo;

public interface TrNeedMapper {
    int deleteByPrimaryKey(String trId);

    int insert(TrNeed record);

    int insertSelective(TrNeed record);

    TrNeed selectByPrimaryKey(String trId);

    int updateByPrimaryKeySelective(TrNeed record);

    int updateByPrimaryKey(TrNeed record);

	List<TrNeed> getTrNeedList(TrNeedVo trv);

}