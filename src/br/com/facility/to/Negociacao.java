package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

import br.com.facility.enums.StatusNegociacao;

public class Negociacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6496230603364191111L;

	private long protocolo;
	
	private Projeto projeto;
	
	private Profissional profissional;
	
	private Usuario usuario;
	
	private Calendar dataSolicitacao;
	
	private StatusNegociacao status;
	
	private Calendar dataStatus;

	public Negociacao() {
		super();
	}

	public Negociacao(long protocolo, Projeto projeto,
			Profissional profissional, Usuario usuario,
			Calendar dataSolicitacao, StatusNegociacao status,
			Calendar dataStatus) {
		super();
		this.protocolo = protocolo;
		this.projeto = projeto;
		this.profissional = profissional;
		this.usuario = usuario;
		this.dataSolicitacao = dataSolicitacao;
		this.status = status;
		this.dataStatus = dataStatus;
	}

	public long getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(long protocolo) {
		this.protocolo = protocolo;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Calendar dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public StatusNegociacao getStatus() {
		return status;
	}

	public void setStatus(StatusNegociacao status) {
		this.status = status;
	}

	public Calendar getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Calendar dataStatus) {
		this.dataStatus = dataStatus;
	}

}
