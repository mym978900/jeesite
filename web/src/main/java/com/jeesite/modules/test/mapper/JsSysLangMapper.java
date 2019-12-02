package com.jeesite.modules.test.mapper;

import com.jeesite.modules.test.entity.JsSysLang;

public interface JsSysLangMapper {
    int deleteByPrimaryKey(String id);

    int insert(JsSysLang record);

    int insertSelective(JsSysLang record);

    JsSysLang selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JsSysLang record);

    int updateByPrimaryKey(JsSysLang record);
}