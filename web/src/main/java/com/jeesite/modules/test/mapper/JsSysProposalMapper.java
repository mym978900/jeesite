package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.test.entity.JsSysProposal;

public interface JsSysProposalMapper {
    int deleteByPrimaryKey(String serialNum);

    int insert(JsSysProposal record);

    int insertSelective(JsSysProposal record);

    JsSysProposal selectByPrimaryKey(String serialNum);

    int updateByPrimaryKeySelective(JsSysProposal record);

    int updateByPrimaryKey(JsSysProposal record);
    
    List<JsSysProposal> findPpByLimit();
}