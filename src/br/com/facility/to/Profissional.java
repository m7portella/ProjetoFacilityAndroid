package br.com.facility.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.facility.enums.StatusProfissional;
import br.com.facility.enums.StatusValidacao;
import br.com.facility.enums.TipoPessoa;

public class Profissional implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7592017709913800349L;

	private int id;
	
	private TipoPessoa tipo;
	
	private String nome;
	
	private ClienteFisico clienteFisico;
	
	private ClienteJuridico clienteJuridico;
	
	private StatusValidacao statusValidacao;
	
	private String codigoValidacao;
	
	private Calendar dataCadastro;
	
	private int rank;
	
	private StatusProfissional status;
	
	private Calendar dataStatus;
	
	private boolean localizavel;
	
	private double altitude;
	
	private double longitude;
	
	private List<LocalAtendimento> locaisAtendimento = new ArrayList<LocalAtendimento>();

	public Profissional(int id, TipoPessoa tipo, String nome,
			ClienteFisico clienteFisico, ClienteJuridico clienteJuridico,
			StatusValidacao statusValidacao, String codigoValidacao,
			Calendar dataCadastro, int rank, StatusProfissional status,
			Calendar dataStatus, boolean localizavel, double altitude,
			double longitude, List<LocalAtendimento> locaisAtendimento) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.clienteFisico = clienteFisico;
		this.clienteJuridico = clienteJuridico;
		this.statusValidacao = statusValidacao;
		this.codigoValidacao = codigoValidacao;
		this.dataCadastro = dataCadastro;
		this.rank = rank;
		this.status = status;
		this.dataStatus = dataStatus;
		this.localizavel = localizavel;
		this.altitude = altitude;
		this.longitude = longitude;
		this.locaisAtendimento = locaisAtendimento;
	}

	public Profissional() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public ClienteFisico getClienteFisico() {
		return clienteFisico;
	}

	public void setClienteFisico(ClienteFisico clienteFisico) {
		this.clienteFisico = clienteFisico;
	}

	public ClienteJuridico getClienteJuridico() {
		return clienteJuridico;
	}

	public void setClienteJuridico(ClienteJuridico clienteJuridico) {
		this.clienteJuridico = clienteJuridico;
	}

	public StatusValidacao getStatusValidacao() {
		return statusValidacao;
	}

	public void setStatusValidacao(StatusValidacao statusValidacao) {
		this.statusValidacao = statusValidacao;
	}

	public String getCodigoValidacao() {
		return codigoValidacao;
	}

	public void setCodigoValidacao(String codigoValidacao) {
		this.codigoValidacao = codigoValidacao;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public StatusProfissional getStatus() {
		return status;
	}

	public void setStatus(StatusProfissional status) {
		this.status = status;
	}

	public Calendar getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Calendar dataStatus) {
		this.dataStatus = dataStatus;
	}

	public boolean isLocalizavel() {
		return localizavel;
	}

	public void setLocalizavel(boolean localizavel) {
		this.localizavel = localizavel;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<LocalAtendimento> getLocaisAtendimento() {
		return locaisAtendimento;
	}

	public void setLocaisAtendimento(List<LocalAtendimento> locaisAtendimento) {
		this.locaisAtendimento = locaisAtendimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
