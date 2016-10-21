package com.versionador.spring.dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.versionador.spring.model.Gd;
import com.versionador.spring.model.GdJson;

@Repository
public class GdDAOImpl implements GdDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(GdDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void insertGd(Gd gd) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(gd);
		logger.info("Gd saved successfully, Gd Details=" + gd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gd> listGds() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Gd> gdList = session.createQuery("from Gd").list();
		for(Gd gd : gdList){
			logger.info("Gd List::" + gd);
		}
		return gdList;
	}

	@Override
	public Gd getGdById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Gd gd = (Gd) session.load(Gd.class, new Integer(id));
		logger.info("Gd loaded successfully, Gd details=" + gd);
		return gd;
	}

	@Override
	public void removeGd(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Gd gd = (Gd) session.load(Gd.class, new Integer(id));
		if(null != gd){
			session.delete(gd);
		}
		logger.info("Gd deleted successfully, gd details=" + gd);
	}

	@Override
	public GdJson fetchGdById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		GdJson gdJson = new GdJson();
		
		Gd gd = (Gd) session.load(Gd.class, new Integer(id));
		
		gdJson.setArquivo(Base64.getEncoder().encodeToString(gd.getArquivo()));
		gdJson.setDataCriacao(gd.getDataCriacao());
		gdJson.setDescricao(gd.getDescricao());
		gdJson.setGd(gd.getGd());
		gdJson.setNomeArquivo(gd.getNomeArquivo());
		gdJson.setVersao(gd.getVersao());
		gdJson.setId(id);
		
		return gdJson;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GdJson> fetchAllGds() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Gd> gdList = session.createQuery("from Gd").list();
		List<GdJson> gdJsonList = new ArrayList<GdJson>();
		GdJson gdJson = new GdJson();
		
		for(Gd gd : gdList){
			gdJson.setArquivo(Base64.getEncoder().encodeToString(gd.getArquivo()));
			gdJson.setDataCriacao(gd.getDataCriacao());
			gdJson.setDescricao(gd.getDescricao());
			gdJson.setGd(gd.getGd());
			gdJson.setNomeArquivo(gd.getNomeArquivo());
			gdJson.setVersao(gd.getVersao());
			gdJson.setId(gd.getId());
			gdJsonList.add(gdJson);
		}

		return gdJsonList;
	}

}
