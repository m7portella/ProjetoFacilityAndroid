package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

public class Mensagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3126185605830001355L;

	private long codigo;

	private Negociacao negociacao;
	
	private String texto;
	
	private Calendar dataEnvio;

	public Mensagem() {
		super();
	}

	public Mensagem(int codigo, Negociacao negociacao, String texto,
			Calendar dataEnvio) {
		super();
		this.codigo = codigo;
		this.negociacao = negociacao;
		this.texto = texto;
		this.dataEnvio = dataEnvio;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Calendar getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Calendar dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

}
