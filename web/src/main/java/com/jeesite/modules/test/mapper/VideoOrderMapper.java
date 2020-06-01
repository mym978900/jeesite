package com.jeesite.modules.test.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.vo.CostVo;
import com.jeesite.modules.test.vo.FlowingWaterVo;
import com.jeesite.modules.test.vo.OrderHaveAbilityVo;
import com.jeesite.modules.test.vo.OrderNotHaveAbilityVo;

public interface VideoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoOrder record);

    int insertSelective(VideoOrder record);

    VideoOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VideoOrder record);

    int updateByPrimaryKey(VideoOrder record);
    
    List<VideoOrder> findOrderByLimit(CostVo vo);

	VideoOrder findOrderByTradeNo(String outtradeno);

	VideoOrder selectByState(String outTradeNo);

	List<OrderNotHaveAbilityVo> findConductOrder();

	List<OrderHaveAbilityVo> findSettlementOrder();

	List<OrderHaveAbilityVo> findRefundOrder();

	List<OrderHaveAbilityVo> findCompleteOrder();

	BigDecimal selectMoneyByTime(FlowingWaterVo vo);

	List<VideoOrder> findOrderByLimitBackStage(FlowingWaterVo vo);

	Integer updateByPrimaryKeySelectiveAndDel(VideoOrder order);

	void updateByOpenIdAndState(VideoOrder orders);

	int updateByOpenIdAndStateOne(VideoOrder order);
	
	List<VideoOrder> getAllCreatOrder(String headImg);

	List<VideoOrder> getAllAcceptOrder(String trPartbusercode);
}