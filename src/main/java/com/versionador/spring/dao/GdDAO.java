package com.versionador.spring.dao;

import java.util.List;

import com.versionador.spring.model.Gd;
import com.versionador.spring.model.GdJson;

public interface GdDAO {

	public void insertGd(Gd gd);
	public List<Gd> listGds();
	public Gd getGdById(int id);
	public void removeGd(int id);
	public GdJson fetchGdById(int id);
	public List<GdJson> fetchAllGds();
}
