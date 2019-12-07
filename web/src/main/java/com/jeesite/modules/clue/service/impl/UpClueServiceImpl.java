package com.jeesite.modules.clue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.mapper.UpClueMapper;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.vo.ClueVo;

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

	//相同的手机号线索资源 - 姓名性别验证 - 可以上传不删除上传自动去重
	@Override
	public int effectiveClue(UpClue clue) {
		int count = upClueMapper.effectiveClue(clue);
		return count;
	}
}
