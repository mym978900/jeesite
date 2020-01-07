package com.jeesite.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.sys.entity.SysDictData;
import com.jeesite.modules.sys.mapper.SysDictDataMapper;
import com.jeesite.modules.sys.service.SysDictDataService;

@Service
public class SysDictDataServiceImpl implements SysDictDataService {

	@Autowired
	private SysDictDataMapper sysDictDataMapper;
	
	//根据字典id获取字典数据
	@Override
	public SysDictData getDictDataById(String id) {
		SysDictData sdd = sysDictDataMapper.selectByPrimaryKey(id);
		if(sdd!= null) {
			return sdd;
		}
		return null;
	}

	//根据字典id更新字典数据
	@Override
	public void updateByPrimaryKey(SysDictData sdd) {
		sysDictDataMapper.updateByPrimaryKey(sdd);
	}
	

}
