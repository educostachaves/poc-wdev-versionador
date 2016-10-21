package com.versionador.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Estrutura da Tabela
 * CREATE TABLE IF NOT EXISTS `gd` (
 *   `id` int(10) NOT NULL,
 *   `nome_arquivo` varchar(100) NOT NULL,
 *   `gd` varchar(20) NOT NULL,
 *   `descricao` varchar(200) NOT NULL,
 *   `data_criacao` date NOT NULL,
 *   `versao` varchar(10) NOT NULL,
 *   `arquivo` longblob NOT NULL
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
 *
 */
@Entity
@Table(name="GD")
public class Gd {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nomeArquivo;
	private String gd;
	private String descricao;
	private String dataCriacao;
	private String versao;
	private byte[] arquivo;
	
	public int getId() {
		return id;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getGd() {
		return gd;
	}
	
	public void setGd(String gd) {
		this.gd = gd;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public byte[] getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
}
