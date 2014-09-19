package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

import br.com.facility.enums.StatusProjeto;

public class Projeto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2916462180126193876L;

	private long id;

	private Usuario usuario;
	
	private String titulo;
	
	private String descricao;
	
	private Calendar dataAbertura;
	
	private Calendar dataLimite;
	
	private StatusProjeto status;
	
	private Calendar dataStatus;
	
	public Projeto(long id, Usuario usuario, String titulo, String descricao,
			Calendar dataAbertura, Calendar dataLimite, StatusProjeto status,
			Calendar dataStatus) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataAbertura = dataAbertura;
		this.dataLimite = dataLimite;
		this.status = status;
		this.dataStatus = dataStatus;
	}

	public Projeto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Calendar getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Calendar dataLimite) {
		this.dataLimite = dataLimite;
	}

	public StatusProjeto getStatus() {
		return status;
	}

	public void setStatus(StatusProjeto status) {
		this.status = status;
	}

	public Calendar getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Calendar dataStatus) {
		this.dataStatus = dataStatus;
	}
	
}
