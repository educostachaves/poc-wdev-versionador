package com.versionador.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.versionador.spring.dao.GdDAO;
import com.versionador.spring.model.Gd;
import com.versionador.spring.model.GdJson;

@Service
public class GdServiceImpl implements GdService {
	
	private GdDAO gdDAO;
	
	public GdDAO getGdDAO() {
		return gdDAO;
	}

	public void setGdDAO(GdDAO gdDAO) {
		this.gdDAO = gdDAO;
	}

	@Override
	@Transactional
	public void insertGd(Gd gd) {
		this.gdDAO.insertGd(gd);
	}

	@Override
	@Transactional
	public List<Gd> listGds() {
		return this.gdDAO.listGds();
	}

	@Override
	@Transactional
	public Gd getGdById(int id) {
		return this.gdDAO.getGdById(id);
	}

	@Override
	@Transactional
	public void removeGd(int id) {
		this.gdDAO.removeGd(id);
	}

	@Override
	@Transactional
	public GdJson fetchGdById(int id) {
		return this.gdDAO.fetchGdById(id);
	}
	
	@Override
	@Transactional
	public List<GdJson> fetchAllGds() {
		return this.gdDAO.fetchAllGds();
	}

}
