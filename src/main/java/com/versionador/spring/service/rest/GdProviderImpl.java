package com.versionador.spring.service.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.versionador.spring.model.GdResponse;
import com.versionador.spring.service.GdService;

@Component
@Configurable
public class GdProviderImpl implements GdProviderService{
	
	@Autowired
	private GdService gdService;

	public GdService getGdService() {
		return gdService;
	}

	public void setGdService(GdService gdService) {
		this.gdService = gdService;
	}
	
    public GdResponse fetchGdById(int id) {
		GdResponse response = new GdResponse();
		try {
			response.setGd(Arrays.asList(gdService.fetchGdById(id)));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@Override
	public GdResponse fetchAllGds() {
		GdResponse response = new GdResponse();
		try {
			response.setGd(gdService.fetchAllGds());
		} catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}
	
}
