package br.com.facility.to;

import java.io.Serializable;
import java.util.Calendar;

import br.com.facility.enums.TipoPreco;

public class EspecialidadeProfissional implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1284394092370415420L;

	private Profissional profissional;

	private Especialidade especialidade;
	
	private TipoPreco tipoPreco;
	
	private double preco;
	
	private int rank;
	
	private String experiencia;
	
	private Calendar dataExperiencia;
	
	private int estrelas;

	public EspecialidadeProfissional() {
		super();
	}

	public EspecialidadeProfissional(Profissional profissional,
			Especialidade especialidade, TipoPreco tipoPreco, double preco,
			int rank, String experiencia, Calendar dataExperiencia, int estrelas) {
		super();
		this.profissional = profissional;
		this.especialidade = especialidade;
		this.tipoPreco = tipoPreco;
		this.preco = preco;
		this.rank = rank;
		this.experiencia = experiencia;
		this.dataExperiencia = dataExperiencia;
		this.estrelas = estrelas;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public Calendar getDataExperiencia() {
		return dataExperiencia;
	}

	public void setDataExperiencia(Calendar dataExperiencia) {
		this.dataExperiencia = dataExperiencia;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}
	
}
