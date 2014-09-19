package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

import br.com.facility.enums.StatusAmigo;
import br.com.facility.enums.TipoAmigo;

public class Amigo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4388084866727932768L;

	private Usuario usuario;
	
	private Usuario amigo;
	
	private TipoAmigo tipo;
	
	private boolean favorito;
	
	private Calendar dataAdicionado;
	
	private StatusAmigo status;
	
	private Calendar dataStatus;

	public Amigo(Usuario usuario, Usuario amigo, TipoAmigo tipo,
			boolean favorito, Calendar dataAdicionado, StatusAmigo status,
			Calendar dataStatus) {
		super();
		this.usuario = usuario;
		this.amigo = amigo;
		this.tipo = tipo;
		this.favorito = favorito;
		this.dataAdicionado = dataAdicionado;
		this.status = status;
		this.dataStatus = dataStatus;
	}

	public Amigo() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getAmigo() {
		return amigo;
	}

	public void setAmigo(Usuario amigo) {
		this.amigo = amigo;
	}

	public TipoAmigo getTipo() {
		return tipo;
	}

	public void setTipo(TipoAmigo tipo) {
		this.tipo = tipo;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public Calendar getDataAdicionado() {
		return dataAdicionado;
	}

	public void setDataAdicionado(Calendar dataAdicionado) {
		this.dataAdicionado = dataAdicionado;
	}

	public StatusAmigo getStatus() {
		return status;
	}

	public void setStatus(StatusAmigo status) {
		this.status = status;
	}

	public Calendar getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Calendar dataStatus) {
		this.dataStatus = dataStatus;
	}
			
}
