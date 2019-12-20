package com.jeesite.modules.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.entity.JsSysUser;
import com.jeesite.modules.test.mapper.JsSysProblemMapper;
import com.jeesite.modules.test.mapper.JsSysProposalMapper;
import com.jeesite.modules.test.mapper.JsSysSeatMapper;
import com.jeesite.modules.test.mapper.JsSysSetmealMapper;
import com.jeesite.modules.test.service.HelpService;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.GetUserVo;
import com.jeesite.modules.test.vo.SeatAndMealVo;

@Service
public class HelpServiceImpl implements HelpService {

	@Autowired
	private JsSysProposalMapper jsSysProposalMapper;
	@Autowired
	private JsSysProblemMapper jsSysProblemMapper;
	@Autowired
	private JsSysSeatMapper jsSysSeatMapper;
	@Autowired
	private JsSysSetmealMapper jsSysSetmealMapper;

	@Override
	public Integer insertProposal(JsSysProposal pro, HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		pro.setSerialNum(DailyUtil.getUuid());
		pro.setCreateTime(new Date());
		pro.setStatus("0");
		pro.setProposalMan(userVo.getUser().getLoginCode());
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
		return new AccountVo(null, null, null, pageNum, null, page, null);

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

	@Override
	public AccountVo selectAllProByPage(Integer pageNum) {
		// TODO Auto-generated method stub
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 10);
		// 查询全部
		List<JsSysProblem> PbList = jsSysProblemMapper.findProByLimit();
		PageInfo<JsSysProblem> page = new PageInfo<JsSysProblem>(PbList);
		return new AccountVo(null, null, null, pageNum, null, null, page);
	}

	@Override
	public SeatAndMealVo selectSeatAndMeal() {
		// TODO Auto-generated method stub
		JsSysSeat seat = jsSysSeatMapper.selectAllSeat();
		List<JsSysSetmeal> mealList = jsSysSetmealMapper.selectAllMeal();
		return new SeatAndMealVo(seat, mealList);
	}

	@Override
	public Integer updateSeat(JsSysSeat seat) {
		// TODO Auto-generated method stub
		int num = jsSysSeatMapper.updateByPrimaryKeySelective(seat);
		if (num == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	public Integer insertMeal(JsSysSetmeal meal) {
		// TODO Auto-generated method stub
		meal.setSerialNumber(DailyUtil.getUuid());
		return jsSysSetmealMapper.insertSelective(meal);
	}

	@Override
	public Integer updateMeal(JsSysSetmeal meal) {
		// TODO Auto-generated method stub
		return jsSysSetmealMapper.updateByPrimaryKeySelective(meal);
	}

	@Override
	public Integer deleteMealByPkey(String delStr) {
		// TODO Auto-generated method stub
		return jsSysSetmealMapper.deleteByPrimaryKey(delStr);
	}

}
