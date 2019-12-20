package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.test.entity.JsSysSetmeal;

public interface JsSysSetmealMapper {
    int deleteByPrimaryKey(String serialNumber);

    int insert(JsSysSetmeal record);

    int insertSelective(JsSysSetmeal record);

    JsSysSetmeal selectByPrimaryKey(String serialNumber);

    int updateByPrimaryKeySelective(JsSysSetmeal record);

    int updateByPrimaryKey(JsSysSetmeal record);

	List<JsSysSetmeal> selectAllMeal();
}