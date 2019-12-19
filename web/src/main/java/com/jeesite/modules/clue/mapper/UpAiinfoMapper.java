package com.jeesite.modules.clue.mapper;

import java.util.List;

import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.vo.AiInfoVo;

public interface UpAiinfoMapper {
    int insert(UpAiinfo record);

    int insertSelective(UpAiinfo record);
    
    //匹配过得线索资源
    public List getExistClue(String userId);

    
    //线索数据共享列表
	List<UpAiinfo> getUpAiInfoList(AiInfoVo uai);
}