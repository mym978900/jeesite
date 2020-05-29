package com.jeesite.modules.test.mapper;

import java.util.List;

import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.vo.CostVo;

public interface VideoOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoOrder record);

    int insertSelective(VideoOrder record);

    VideoOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoOrder record);

    int updateByPrimaryKey(VideoOrder record);
    
    List<VideoOrder> findOrderByLimit(CostVo vo);

	VideoOrder findOrderByTradeNo(String outtradeno);

	List<VideoOrder> getAllCreatOrder(String headImg);

	List<VideoOrder> getAllAcceptOrder(String trPartbusercode);
}