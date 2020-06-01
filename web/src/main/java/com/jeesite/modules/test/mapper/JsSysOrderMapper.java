package com.jeesite.modules.test.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.jeesite.modules.test.entity.JsSysOrder;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.vo.FlowingWaterVo;

public interface JsSysOrderMapper {
    int deleteByPrimaryKey(String orderNum);

    int insert(JsSysOrder record);

    int insertSelective(JsSysOrder record);

    JsSysOrder selectByPrimaryKey(String orderNum);

    int updateByPrimaryKeySelective(JsSysOrder record);

    int updateByPrimaryKey(JsSysOrder record);

	List<VideoOrder> findOrderByLimit(FlowingWaterVo vo);

	BigDecimal selectMoneyByTime(FlowingWaterVo vo);
}