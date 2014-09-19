package br.com.facility.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocalAtendimento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3497619573265914131L;

	private int id;
	
	private List<Profissional> profissionais = new ArrayList<Profissional>();
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String pais;

	public LocalAtendimento() {
		super();
	}

	public LocalAtendimento(int id, List<Profissional> profissionais,
			String bairro, String cidade, String estado, String pais) {
		super();
		this.id = id;
		this.setProfissionais(profissionais);
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}
	
}
