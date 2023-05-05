package com.mycompany.paginacpf.resources;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ManagedBean
@SessionScoped
public class cronometroMB {
    private Integer segundos;

    @PostConstruct
    public void init() {
        this.segundos = 10;
    }

    public void voltar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("loading_finalizar.xhtml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer adicionar() {
        this.segundos = segundos + 60;
        return this.segundos;
    }

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }
}
