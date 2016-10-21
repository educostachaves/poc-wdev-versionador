package com.versionador.spring.model;

import java.util.List;

public class GdResponse {
	
	private List<GdJson> gd;
	private String errorMessage;
	private Boolean success = true;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public List<GdJson> getGd() {
		return gd;
	}
	
	public void setGd(List<GdJson> list) {
		this.gd = list;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
