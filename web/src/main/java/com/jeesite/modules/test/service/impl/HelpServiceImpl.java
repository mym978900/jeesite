package com.jeesite.modules.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsSysProblemMapper;
import com.jeesite.modules.test.mapper.JsSysProposalMapper;
import com.jeesite.modules.test.service.HelpService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.AccountVo;

@Service
public class HelpServiceImpl implements HelpService {

	@Autowired
	private JsSysProposalMapper jsSysProposalMapper;
	@Autowired
	private JsSysProblemMapper jsSysProblemMapper;

	@Override
	public Integer insertProposal(JsSysProposal pro) {
		// TODO Auto-generated method stub
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = req.getSession();
		JsSysUser user = (JsSysUser) session.getAttribute("loginUser");
		pro.setSerialNum(DailyUtil.getUuid());
		pro.setCreateTime(new Date());
		pro.setStatus("0");
		pro.setProposalMan(user.getLoginCode());
		return jsSysProposalMapper.insertSelective(pro);
	}

	@Override
	public AccountVo selectAllPpByPage(Integer pageNum) {
		// TODO Auto-generated method stub
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		List<JsSysProposal> ProList = jsSysProposalMapper.findPpByLimit();
		PageInfo<JsSysProposal> page = new PageInfo<JsSysProposal>(ProList);
		return new AccountVo(null, null, null, pageNum, null, page);

	}

	@Override
	public Integer deletePpBySerial(String delStr) {
		// TODO Auto-generated method stub

		return jsSysProposalMapper.deleteByPrimaryKey(delStr);
	}

	@Override
	public Integer insertProblem(JsSysProblem problem) {
		// TODO Auto-generated method stub
		problem.setCreateTime(new Date());
		problem.setSerialNum(DailyUtil.getUuid());
		return jsSysProblemMapper.insertSelective(problem);
	}

	@Override
	public Integer updateProblem(JsSysProblem problem) {
		// TODO Auto-generated method stub

		return jsSysProblemMapper.updateByPrimaryKeySelective(problem);
	}

}
