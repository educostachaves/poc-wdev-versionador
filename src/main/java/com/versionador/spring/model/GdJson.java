package com.versionador.spring.model;

public class GdJson {

	private int id;
	private String nomeArquivo;
	private String gd;
	private String descricao;
	private String dataCriacao;
	private String versao;
	private String arquivo;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getGd() {
		return gd;
	}
	
	public void setGd(String gd) {
		this.gd = gd;
	}
	
	public String getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public String getVersao() {
		return versao;
	}
	
	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	public String getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
}
