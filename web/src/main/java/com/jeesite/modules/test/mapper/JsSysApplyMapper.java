package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.base.BaseMapper;
import com.jeesite.modules.test.entity.JsSysApply;
import com.jeesite.modules.test.vo.AccountVo;


public interface JsSysApplyMapper extends BaseMapper<JsSysApply>{
    
    
    List<JsSysApply> selectAllApply(AccountVo vo);
}