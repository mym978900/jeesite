package com.jeesite.modules.sys.service;

import com.jeesite.modules.sys.entity.SysDictData;

public interface SysDictDataService {
	
	//根据字典类型获取字典数据
	public SysDictData getDictDataById(String id) ;
	
	//根据字典id更新字典数据
	public void updateByPrimaryKey(SysDictData sdd);
	
}
