package com.jeesite.modules.clue.base;


public interface BaseMapper<T> {

	int deleteByPrimaryKey(String upClueCode);

    int insert(T UpClue);

    int insertSelective(T UpClue);

    T selectByPrimaryKey(String upClueCode);

    int updateByPrimaryKeySelective(T UpClue);

    int updateByPrimaryKey(T UpClue);
}
