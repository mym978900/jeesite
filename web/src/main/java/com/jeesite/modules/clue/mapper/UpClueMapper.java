package com.jeesite.modules.clue.mapper;

import java.util.List;


import com.jeesite.modules.clue.base.BaseMapper;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.vo.ClueVo;

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
    
}