package com.jeesite.modules.clue.mapper;

import java.util.Date;
import java.util.List;


import com.jeesite.modules.clue.base.BaseMapper;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.vo.ClueVo;
import com.jeesite.modules.clue.vo.IntentionVo;

public interface UpClueMapper extends BaseMapper<UpClue>{    
    /*
	          获取用户上传线索列表
	    xf
	    2019.12.03
    */
    List<UpClue> getUpClueList(ClueVo clue);
    
    
    /*
		相同的手机号线索资源 - 姓名性别验证 - 可以上传不删除上传自动去重
		xf
		2019.12.03
	*/
    int effectiveClue(UpClue record) ;
    
    /*
	  	获取用户上传线索列表
		xf
		2019.12.03
	*/
    List getMatchClue(String userId, String deptType,String addressCity,String minlng,String maxlng,String minlat,String maxlat);

    
    /*
	  	更新线索最新匹配时间
		xf
		2019.12.03
     */
	void updateMatchTime(String clueCode, Date date);

	
	/*
  		获取未标注经纬度的会员
		xf
		2019.12.16
	 */
	List<UpClue> getNoConfigAddress();

	
	List getIntentionClue(IntentionVo itv);


	UpClue getUpClueByPhone(String upAiphone,String usercode);
    
}