package com.jeesite.modules.clue.service;

import java.util.Date;
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
	
	//匹配线索资源
	public List getMatchClue(String userId, String deptType,String minlng,String maxlng,String minlat,String maxlat);
	
	//更新线索最新匹配时间
	public void updateMatchTime(String clueCode,Date date);

	//获取未标注经纬度的会员
	public List<UpClue> getNoConfigAddress();

	public void updateByPrimaryKey(UpClue upClue);
	
	public UpClue selectByPrimaryKey(String upClueCode);
}
