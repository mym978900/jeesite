package com.jeesite.modules.tr.service;

import java.util.List;

import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.tr.entity.TrAbility;
import com.jeesite.modules.tr.entity.TrNeed;
import com.jeesite.modules.tr.vo.TrNeedVo;

public interface TrService {
	
	//用户能力新增
	public void addTrAbility(TrAbility tb);

	//用户能力修改
	public void updateTrAbility(TrAbility tb);
	
	//查询用户能力
	public TrAbility getTrAbility(String userCode);
	
	//查询匹配能力
	public TrAbility getTrAbilityById(String trId);

	//用户新增需求
	public void addTrNeed(TrNeed tn);

	//用户需求修改
	public void updateTrNeed(TrNeed tn);

	//用户需求列表
	public List<TrNeed> getTrNeedList(TrNeedVo trv);

	//获取用户需求
	public TrNeed getTrNeed(String trId);

	//为需求匹配能力
	public List<TrAbility> matchTrAbilityForNeed(TrNeed tn);

	//获取所有创建的订单
	public List<VideoOrder> getAllCreatOrder(String userCode);

	//获取所有接受的订单
	public List<VideoOrder> getAllAcceptOrder(String userCode);

	public void updateVideoOrder(VideoOrder vo);

}
