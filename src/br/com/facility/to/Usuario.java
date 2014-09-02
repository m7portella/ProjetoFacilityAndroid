package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

import br.com.facility.enums.StatusUsuario;
import br.com.facility.enums.TipoPessoa;
import br.com.facility.enums.TipoUsuario;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4823278236257619268L;

	private int id;
	
	private String username;
	
	private String senha;
	
	private String email;
	
	private TipoUsuario tipo;
	
	private TipoPessoa tipoPessoa;

	private Calendar dataCadastro;
	
	private String tokenApi;
	
	private StatusUsuario status;
	
	private Calendar dataStatus;

	public Usuario() {
		super();
	}

	public Usuario(int id, String username, String senha, String email,
			TipoUsuario tipo, TipoPessoa tipoPessoa, Calendar dataCadastro,
			String tokenApi, StatusUsuario status, Calendar dataStatus) {
		super();
		this.id = id;
		this.username = username;
		this.senha = senha;
		this.email = email;
		this.tipo = tipo;
		this.tipoPessoa = tipoPessoa;
		this.dataCadastro = dataCadastro;
		this.tokenApi = tokenApi;
		this.status = status;
		this.dataStatus = dataStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getTokenApi() {
		return tokenApi;
	}

	public void setTokenApi(String tokenApi) {
		this.tokenApi = tokenApi;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	public Calendar getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Calendar dataStatus) {
		this.dataStatus = dataStatus;
	}
	
}
