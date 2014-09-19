package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

import br.com.facility.enums.Sexo;

public class ClienteFisico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1616563104437479198L;

	private int id;
	
	private Usuario usuario;
	
	private String nome;

	private String sobrenome;

	private String cpf;

	private Sexo sexo;

	private Calendar dataNascimento;

	public ClienteFisico() {
		super();
	}

	public ClienteFisico(int id, Usuario usuario, String nome,
			String sobrenome, String cpf, Sexo sexo, Calendar dataNascimento) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
