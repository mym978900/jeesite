package com.jeesite.modules.test.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jeesite.modules.test.entity.JsSysProblem;
import com.jeesite.modules.test.entity.JsSysProposal;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.vo.AccountVo;
import com.jeesite.modules.test.vo.SeatAndMealVo;

public interface HelpService {

	Integer insertProposal(JsSysProposal pro,HttpServletResponse response,Model model);

	AccountVo selectAllPpByPage(Integer pageNum);

	Integer deletePpBySerial(String delStr);

	Integer insertProblem(JsSysProblem problem);

	Integer updateProblem(JsSysProblem problem);

	AccountVo selectAllProByPage(Integer pageNum);

	SeatAndMealVo selectSeatAndMeal();

	Integer updateSeat(JsSysSeat seat);

	Integer insertMeal(JsSysSetmeal meal);

	Integer updateMeal(JsSysSetmeal meal);

	Integer deleteMealByPkey(String delStr);

	Integer deleteProblem(String delStr);


}
