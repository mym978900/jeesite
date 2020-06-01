package com.jeesite.modules.tr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.mapper.VideoOrderMapper;
import com.jeesite.modules.tr.entity.TrAbility;
import com.jeesite.modules.tr.entity.TrNeed;
import com.jeesite.modules.tr.mapper.TrAbilityMapper;
import com.jeesite.modules.tr.mapper.TrNeedMapper;
import com.jeesite.modules.tr.service.TrService;
import com.jeesite.modules.tr.vo.TrNeedVo;

@Service
public class TrServiceImpl implements TrService {

	@Autowired
	private TrAbilityMapper trAbilityMapper;
	
	@Autowired
	private TrNeedMapper trNeedMapper;
	
	@Autowired
	private VideoOrderMapper videoOrderMapper;
	
	@Override
	public void addTrAbility(TrAbility tb) {
		
		trAbilityMapper.insert(tb);
		
	}

	@Override
	public void updateTrAbility(TrAbility tb) {
		
		trAbilityMapper.updateByPrimaryKeySelective(tb);
		
	}

	@Override
	public void addTrNeed(TrNeed tn) {
		
		trNeedMapper.insert(tn);
		
	}

	@Override
	public void updateTrNeed(TrNeed tn) {

		trNeedMapper.updateByPrimaryKeySelective(tn);
		
	}

	@Override
	public TrAbility getTrAbility(String userCode) {
		TrAbility tb = trAbilityMapper.getTrAbility(userCode);
		return tb;
	}
	
	@Override
	public TrAbility getTrAbilityById(String trId) {
		TrAbility tb = trAbilityMapper.selectByPrimaryKey(trId);
		return tb;
	}
	
	@Override
	public List<TrAbility> matchTrAbilityForNeed(TrNeed tn) {
		List<TrAbility> tb = trAbilityMapper.matchTrAbilityForNeed(tn);
		return tb;
	}

	@Override
	public List<TrNeed> getTrNeedList(TrNeedVo trv) {
		List<TrNeed> list = trNeedMapper.getTrNeedList(trv);
		return list;
	}

	@Override
	public TrNeed getTrNeed(String trId) {
		TrNeed trNeed = trNeedMapper.selectByPrimaryKey(trId);
		return trNeed;
	}

	@Override
	public List<VideoOrder> getAllCreatOrder(String userCode) {
		List<VideoOrder> list = videoOrderMapper.getAllCreatOrder(userCode);
		return list;
	}

	@Override
	public List<VideoOrder> getAllAcceptOrder(String userCode) {
		List<VideoOrder> list = videoOrderMapper.getAllAcceptOrder(userCode);
		return list;
	}

	@Override
	public void updateVideoOrder(VideoOrder vo) {
		videoOrderMapper.updateByPrimaryKeySelective(vo);
	}

}
