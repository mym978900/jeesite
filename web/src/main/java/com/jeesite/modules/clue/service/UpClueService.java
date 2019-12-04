package com.jeesite.modules.clue.service;

import java.util.List;

import com.jeesite.modules.clue.entity.UpClue;


/**
 * 线索管理Service
 * @author xf
 * @version 2019.12.03
 */
public interface UpClueService {

	//获取上传线索列表
	public List<UpClue> getUpClueList(UpClue clue);
	
	//新增单条线索
	public void addUpClue(UpClue clue);
}
