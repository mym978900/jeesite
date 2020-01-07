package com.jeesite.modules.clue.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.mapper.UpClueMapper;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.vo.ClueVo;
import com.jeesite.modules.clue.vo.IntentionVo;

/**
 * 线索管理Service
 * @author xf
 * @version 2019.12.03
 */
@Service
public class UpClueServiceImpl implements UpClueService{

	@Autowired
	private UpClueMapper upClueMapper;
	
	//获取线索列表数据
	@Override
	public List<UpClue> getUpClueList(ClueVo clue) {
		
		List<UpClue> list = upClueMapper.getUpClueList(clue);
		
		return list;
	}

	//新增线索
	public void addUpClue(UpClue clue) {
		if(clue != null) {
			upClueMapper.insert(clue);
		}
	}

	//相同的手机号线索资源 - 姓名验证 - 可以上传不删除上传自动去重
	@Override
	public int effectiveClue(UpClue clue) {
		int count = upClueMapper.effectiveClue(clue);
		return count;
	}

	//匹配线索资源
	@Override
	public List getMatchClue(String userId, String deptType,String minlng,String maxlng,String minlat,String maxlat) {
		List list = upClueMapper.getMatchClue(userId,deptType,minlng,maxlng,minlat,maxlat);
		return list;
	}

	//更新线索最新匹配时间
	@Override
	public void updateMatchTime(String clueCode, Date date) {
		upClueMapper.updateMatchTime(clueCode,date);
	}

	//获取未标注经纬度的线索
	@Override
	public List<UpClue> getNoConfigAddress() {
		List list = upClueMapper.getNoConfigAddress();
		return list;
	}

	@Override
	public void updateByPrimaryKey(UpClue upClue) {
		upClueMapper.updateByPrimaryKey(upClue);
	}

	@Override
	public UpClue selectByPrimaryKey(String upClueCode) {
		UpClue uc = upClueMapper.selectByPrimaryKey(upClueCode);
		return uc;
	}

	//意向用户
	@Override
	public List getIntentionClue(IntentionVo itv) {
		List list = upClueMapper.getIntentionClue(itv);
		return list;
	}
}
