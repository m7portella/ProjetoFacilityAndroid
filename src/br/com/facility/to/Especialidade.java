package br.com.facility.to;

import java.io.Serializable;

public class Especialidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	private Atividade atividade;

	private String nome;

	public Especialidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Especialidade(int id, Atividade atividade, String nome) {
		super();
		this.id = id;
		this.atividade = atividade;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
