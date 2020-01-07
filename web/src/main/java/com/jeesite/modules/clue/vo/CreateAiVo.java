package com.jeesite.modules.clue.vo;

import java.util.List;

import com.jeesite.modules.clue.entity.UpClue;

import lombok.Data;

public class CreateAiVo {
	
	private List<Object> list;
	private Boolean result;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public CreateAiVo(List<Object> list, Boolean result, String message) {
		super();
		this.list = list;
		this.result = result;
		this.message = message;
	}
	public CreateAiVo() {
		super();
	}
	
	
}
