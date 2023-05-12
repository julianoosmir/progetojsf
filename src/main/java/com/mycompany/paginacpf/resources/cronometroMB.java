package com.mycompany.paginacpf.resources;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private void telaErro() {
        ErroMB erroMB = new ErroMB();
        erroMB.setErroMenssagem("Não foi possível finalizar o atendimento. "
                + "Por favor tente novamente mais tarde");

        erroMB.setUrl("cronometro.xhtml");
        erroMB.setBtnMenssage("Tentar novamente");

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("tela_erro.xhtml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void finalizar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("loading_finalizar.xhtml");
        } catch (IOException e) {
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
