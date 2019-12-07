package com.jeesite.modules.clue.service;

import java.util.List;

import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.vo.ClueVo;


/**
 * 线索管理Service
 * @author xf
 * @version 2019.12.03
 */
public interface UpClueService {

	//获取上传线索列表
	public List<UpClue> getUpClueList(ClueVo clue);
	
	//新增单条线索
	public void addUpClue(UpClue clue);

	//相同的手机号线索资源 - 姓名性别验证 - 可以上传不删除上传自动去重
	public int effectiveClue(UpClue clue);
}
