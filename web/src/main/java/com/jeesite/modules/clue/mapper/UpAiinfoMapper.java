package com.jeesite.modules.clue.mapper;

import java.util.List;

import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.vo.AiInfoVo;

public interface UpAiinfoMapper {
	int deleteByPrimaryKey(String upId);

    int insert(UpAiinfo record);

    int insertSelective(UpAiinfo record);

    UpAiinfo selectByPrimaryKey(String upId);

    int updateByPrimaryKeySelective(UpAiinfo record);

    int updateByPrimaryKey(UpAiinfo record);
    
    //匹配过得线索资源
    public List getExistClue(String userId);

    //线索数据共享列表
	List<UpAiinfo> getUpAiInfoList(AiInfoVo uai);

	//根据用户编码和线索编码更新智能匹配线索信息
	void updateAiInfoByUserCodeUpClueCode(String upClueTaskid,String userCode, String upClueCode);
	
	//根据用户编码和线索编码获取智能匹配线索信息
	UpAiinfo getMatchClueByUserCodeAndClueCode(String upClueCode, String userCode);

	//根据用户编码和线索编码更新智能匹配线索信息
	void updateByUserCodeAndClueCode(UpAiinfo upAiInfo);
}