package com.jeesite.modules.test.vo;

import java.util.List;

import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;

public class SeatAndMealVo {

	private JsSysSeat jsSysSeat;
	private List<JsSysSetmeal> setMeals;
	public JsSysSeat getJsSysSeat() {
		return jsSysSeat;
	}
	public void setJsSysSeat(JsSysSeat jsSysSeat) {
		this.jsSysSeat = jsSysSeat;
	}
	public List<JsSysSetmeal> getSetMeals() {
		return setMeals;
	}
	public void setSetMeals(List<JsSysSetmeal> setMeals) {
		this.setMeals = setMeals;
	}
	public SeatAndMealVo(JsSysSeat jsSysSeat, List<JsSysSetmeal> setMeals) {
		super();
		this.jsSysSeat = jsSysSeat;
		this.setMeals = setMeals;
	}
	public SeatAndMealVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
