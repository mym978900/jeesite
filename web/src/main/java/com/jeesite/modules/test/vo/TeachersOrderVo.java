package com.jeesite.modules.test.vo;

import com.github.pagehelper.PageInfo;
import com.jeesite.modules.test.entity.NeedTime;
import com.jeesite.modules.test.entity.VideoOrder;

public class TeachersOrderVo {

	private Integer pageNum;
	private PageInfo<OrderNotHaveAbilityVo> orderPageNoA;
	private PageInfo<OrderHaveAbilityVo> orderPageHavaA;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	public PageInfo<OrderNotHaveAbilityVo> getOrderPageNoA() {
		return orderPageNoA;
	}
	public void setOrderPageNoA(PageInfo<OrderNotHaveAbilityVo> orderPageNoA) {
		this.orderPageNoA = orderPageNoA;
	}
	public PageInfo<OrderHaveAbilityVo> getOrderPageHavaA() {
		return orderPageHavaA;
	}
	public void setOrderPageHavaA(PageInfo<OrderHaveAbilityVo> orderPageHavaA) {
		this.orderPageHavaA = orderPageHavaA;
	}
	
	public TeachersOrderVo(Integer pageNum, PageInfo<OrderNotHaveAbilityVo> orderPageNoA,
			PageInfo<OrderHaveAbilityVo> orderPageHavaA) {
		super();
		this.pageNum = pageNum;
		this.orderPageNoA = orderPageNoA;
		this.orderPageHavaA = orderPageHavaA;
	}
	public TeachersOrderVo() {
		super();
		// TODO Auto-generated constructor stub
	}
}
