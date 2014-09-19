package br.com.facility.to;

import java.io.Serializable;

import br.com.facility.enums.TipoAtividadeEspecialidade;

public class NegociacaoAtividade implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8174351763945752314L;

	private long id;

	private Negociacao negociacao;

	private Atividade atividade;

	private Especialidade especialidade;

	private int item;

	private TipoAtividadeEspecialidade tipo;

	private double valor;

	public NegociacaoAtividade() {
		super();
	}

	public NegociacaoAtividade(long id, Negociacao negociacao,
			Atividade atividade, Especialidade especialidade, int item,
			TipoAtividadeEspecialidade tipo, double valor) {
		super();
		this.id = id;
		this.negociacao = negociacao;
		this.atividade = atividade;
		this.especialidade = especialidade;
		this.item = item;
		this.tipo = tipo;
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public TipoAtividadeEspecialidade getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtividadeEspecialidade tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
	
}
