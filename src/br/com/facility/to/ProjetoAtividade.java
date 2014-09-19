package br.com.facility.to;

import java.io.Serializable;

import br.com.facility.enums.TipoAtividadeEspecialidade;

public class ProjetoAtividade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2033727583047232868L;

	private Projeto projeto;
	
	private int item;
	
	private Atividade atividade;
	
	private Especialidade especialidade;
	
	private TipoAtividadeEspecialidade tipo;
	
	private String descricao;

	public ProjetoAtividade() {
		super();
	}

	public ProjetoAtividade(Projeto projeto, int item, Atividade atividade,
			Especialidade especialidade, TipoAtividadeEspecialidade tipo,
			String descricao) {
		super();
		this.projeto = projeto;
		this.item = item;
		this.atividade = atividade;
		this.especialidade = especialidade;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
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

	public TipoAtividadeEspecialidade getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtividadeEspecialidade tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
