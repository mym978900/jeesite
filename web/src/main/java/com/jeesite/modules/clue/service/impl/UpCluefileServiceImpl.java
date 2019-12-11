package com.jeesite.modules.clue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpCluefile;
import com.jeesite.modules.clue.mapper.UpCluefileMapper;
import com.jeesite.modules.clue.service.UpCluefileService;

/**
 * 线索文件管理Service
 * @author xf
 * @version 2019.12.10
 */
@Service
public class UpCluefileServiceImpl implements UpCluefileService {

	@Autowired
	private UpCluefileMapper upCluefileMapper;
	
	@Override
	public void addUpClueFile(UpCluefile clue) {
		if(clue != null) {
			upCluefileMapper.insert(clue);
		}
	}

}
