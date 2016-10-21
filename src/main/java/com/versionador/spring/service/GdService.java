package com.versionador.spring.service;

import java.util.List;

import com.versionador.spring.model.Gd;
import com.versionador.spring.model.GdJson;

public interface GdService {

	public void insertGd(Gd gd);
	public List<Gd> listGds();
	public Gd getGdById(int id);	
	public void removeGd(int id);
	public GdJson fetchGdById(int id);
	public List<GdJson> fetchAllGds();
	
}
