package br.com.facility.to;

import java.io.Serializable;

public class ClienteJuridico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8296495229515207788L;

	private int id;
	
	private Usuario usuario;

	private String razaoSocial;

	private String nomeFantasia;

	private String cnpj;

	public ClienteJuridico(int id, Usuario usuario, String razaoSocial,
			String nomeFantasia, String cnpj) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
	}

	public ClienteJuridico() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
