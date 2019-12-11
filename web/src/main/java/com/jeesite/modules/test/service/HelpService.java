package com.jeesite.modules.test.service;

import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;
import com.jeesite.modules.test.vo.AccountVo;

public interface HelpService {

	Integer insertProposal(JsSysProposal pro);

	AccountVo selectAllPpByPage(Integer pageNum);

	Integer deletePpBySerial(String delStr);

	Integer insertProblem(JsSysProblem problem);

	Integer updateProblem(JsSysProblem problem);

}
