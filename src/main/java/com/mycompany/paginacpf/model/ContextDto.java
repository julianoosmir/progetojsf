package com.mycompany.paginacpf.model;

public class ContextDto {
    private final String uuidContext;
    private final String profileContext;
    private final String contextBlocked;
    private final String nrDocumentPersonLink;
    private final String namePersonLink;
    private final Boolean personLinkBlocked;
    private final Boolean favoriteContext;

    public ContextDto(ContextModel contextModel) {
        this.uuidContext = contextModel.getUuidContexto();
        this.profileContext = contextModel.getPerfilContexto();
        this.contextBlocked = contextModel.getContextoBloqueado();
        this.nrDocumentPersonLink = contextModel.getNrDocumentoPessoaVinculo();
        this.namePersonLink = contextModel.getNomePessoaVinculo();
        this.personLinkBlocked = contextModel.getPessoaVinculoBloqueada();
        this.favoriteContext = contextModel.getContextoFavorito();
    }

	public String getUuidContext() {
		return uuidContext;
	}

	public String getProfileContext() {
		return profileContext;
	}

	public String getContextBlocked() {
		return contextBlocked;
	}

	public String getNrDocumentPersonLink() {
		return nrDocumentPersonLink;
	}

	public String getNamePersonLink() {
		return namePersonLink;
	}

	public Boolean getPersonLinkBlocked() {
		return personLinkBlocked;
	}

	public Boolean getFavoriteContext() {
		return favoriteContext;
	}
}
