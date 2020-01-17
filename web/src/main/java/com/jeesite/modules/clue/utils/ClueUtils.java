package com.jeesite.modules.clue.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
	
	/** 
     * 大陆号码或香港号码均可 
     */ 
	public static boolean isPhoneLegal(String str) throws PatternSyntaxException  {
		return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
	}
	
	/** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p =Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */
	public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
		String regExp="^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
//	public static void main(String[] args) {
//		String userName ="东风";
//		String userName1 = ClueUtils.mobilePhone("13888888888");
//		System.out.println(userName1);
//	}
}
