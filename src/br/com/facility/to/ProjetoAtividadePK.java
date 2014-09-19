package br.com.facility.to;

import java.io.Serializable;

public class ProjetoAtividadePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1705306801399656679L;
	private Long projeto;
	private Long item;
	
	public int hashCode(){
		return getProjeto().hashCode() + getItem().hashCode();
	}
	
	public boolean equals(Object o){
		ProjetoAtividadePK myId = (ProjetoAtividadePK) o;
		
		if((o instanceof ProjetoAtividadePK)
			&& (getProjeto() == myId.getProjeto())
			&& (getItem() == myId.getItem())){
			return true;
		}
		return false;
	}

	public Long getProjeto() {
		return projeto;
	}

	public void setProjeto(Long projeto) {
		this.projeto = projeto;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}
	
}
