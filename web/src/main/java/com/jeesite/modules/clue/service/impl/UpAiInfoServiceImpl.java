package com.jeesite.modules.clue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.mapper.UpAiinfoMapper;
import com.jeesite.modules.clue.service.UpAiInfoService;
import com.jeesite.modules.clue.vo.AiInfoVo;

@Service
public class UpAiInfoServiceImpl implements UpAiInfoService {
	
	@Autowired
	private UpAiinfoMapper upAiinfoMapper;
	
	//匹配过得线索资源
	@Override
	public List getExistClue(String userId) {
		List list = upAiinfoMapper.getExistClue(userId);
		if(list!=null && !list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	//新增单条信息
	@Override
	public void addUpAiInfo(UpAiinfo uai) {
		if(uai != null) {
			upAiinfoMapper.insert(uai);
		}
	}

	//线索数据共享列表
	@Override
	public List<UpAiinfo> getUpAiInfoList(AiInfoVo uai) {
		List<UpAiinfo> list = upAiinfoMapper.getUpAiInfoList(uai);
		return list;
	}

	//根据用户编码和线索编码更新智能匹配线索信息
	@Override
	public void updateAiInfoByUserCodeUpClueCode(String upClueTaskid,String userCode, String upClueCode) {
		upAiinfoMapper.updateAiInfoByUserCodeUpClueCode(upClueTaskid,userCode,upClueCode);
	}

	//根据用户编码和线索编码获取智能匹配线索信息
	@Override
	public UpAiinfo getMatchClueByUserCodeAndClueCode(String upClueCode, String userCode) {
		UpAiinfo uai = upAiinfoMapper.getMatchClueByUserCodeAndClueCode(upClueCode,userCode);
		return uai;
	}

	@Override
	public void updateByPrimaryKey(UpAiinfo upAiInfo) {
		upAiinfoMapper.updateByPrimaryKey(upAiInfo);
	}

	@Override
	public UpAiinfo getUpAiinfoByPhone(String upAiphone,String usercode) {
		UpAiinfo uai = upAiinfoMapper.getUpAiinfoByPhone(upAiphone,usercode);
		return uai;
	}

}
