package com.mycompany.paginacpf.model;

public class ContextModel {
    private String uuidContexto;
    private String perfilContexto;
    private String contextoBloqueado;
    private String nrDocumentoPessoaVinculo;
    private String nomePessoaVinculo;
    private Boolean pessoaVinculoBloqueada;
    private Boolean contextoFavorito;
    
	public ContextModel() {
	}

	public ContextModel(String perfilContexto) {
		this.perfilContexto = perfilContexto;
	}

	public String getUuidContexto() {
		return uuidContexto;
	}

	public void setUuidContexto(String uuidContexto) {
		this.uuidContexto = uuidContexto;
	}

	public String getPerfilContexto() {
		return perfilContexto;
	}

	public void setPerfilContexto(String perfilContexto) {
		this.perfilContexto = perfilContexto;
	}

	public String getContextoBloqueado() {
		return contextoBloqueado;
	}

	public void setContextoBloqueado(String contextoBloqueado) {
		this.contextoBloqueado = contextoBloqueado;
	}

	public String getNrDocumentoPessoaVinculo() {
		return nrDocumentoPessoaVinculo;
	}

	public void setNrDocumentoPessoaVinculo(String nrDocumentoPessoaVinculo) {
		this.nrDocumentoPessoaVinculo = nrDocumentoPessoaVinculo;
	}

	public String getNomePessoaVinculo() {
		return nomePessoaVinculo;
	}

	public void setNomePessoaVinculo(String nomePessoaVinculo) {
		this.nomePessoaVinculo = nomePessoaVinculo;
	}

	public Boolean getPessoaVinculoBloqueada() {
		return pessoaVinculoBloqueada;
	}

	public void setPessoaVinculoBloqueada(Boolean pessoaVinculoBloqueada) {
		this.pessoaVinculoBloqueada = pessoaVinculoBloqueada;
	}

	public Boolean getContextoFavorito() {
		return contextoFavorito;
	}

	public void setContextoFavorito(Boolean contextoFavorito) {
		this.contextoFavorito = contextoFavorito;
	}
}
