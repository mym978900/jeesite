package com.jeesite.modules.clue.utils;

import com.jeesite.common.utils.SpringUtils;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.service.UpClueService;

public class ClueUtils {

	private static final class Static {
		private static UpClueService iUpClueService = SpringUtils.getBean(UpClueService.class);
	}
	
	//相同的手机号线索资源 - 姓名性别验证 - 可以上传不删除上传自动去重
	public static Boolean effectiveClue(UpClue up){
		int count = Static.iUpClueService.effectiveClue(up);
		if(count > 0) {
			return false;
		}
		return true;
	}
	
	
}
