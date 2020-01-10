package com.jeesite.modules.clue.utils;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.utils.SpringUtils;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.service.UpClueService;

public class ClueUtils {

	private static final class Static {
		private static UpClueService iUpClueService = SpringUtils.getBean(UpClueService.class);
	}
	
	//相同的手机号线索资源 - 姓名验证 - 可以上传不删除上传自动去重
	public static Boolean effectiveClue(UpClue up){
		int count = Static.iUpClueService.effectiveClue(up);
		if(count > 0) {
			return false;
		}
		return true;
	}
	
	
	/**
     * [中文姓名] 只显示姓氏，其他隐藏为星号<例子：欧阳娜娜  ： 欧阳**>
     * 
     * @param familyName  姓氏
     * @param givenName   名字
     * @return
     */
    public static String chineseName(String familyName, String givenName) {
        if (StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {
            return "";
        }
        if(familyName.length()>1){
            String name = StringUtils.left(familyName, familyName.length());
            return StringUtils.rightPad(name, StringUtils.length(familyName+givenName), "*");
        }
        return chineseName(familyName + givenName);
    }
    
    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为星号<例子：李**>
     * 
     * @param name 姓名
     * @return
     */
    public static String chineseName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }
	
	
	/**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     * 
     * @param num
     * @return
     */
	public static String mobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
    }
	
//	public static void main(String[] args) {
//		String userName ="东风";
//		String userName1 = ClueUtils.mobilePhone("13888888888");
//		System.out.println(userName1);
//	}
}
