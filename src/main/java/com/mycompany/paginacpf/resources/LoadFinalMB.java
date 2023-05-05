package com.mycompany.paginacpf.resources;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ManagedBean
@SessionScoped
public class LoadFinalMB {
    private Boolean isloadingFinalizar;

    @PostConstruct
    public void init() {
        this.isloadingFinalizar = true;
    }

    public void redirecionarIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Thread.sleep(5000);
            if (this.isloadingFinalizar) {
                context.getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean getIsloadingFinalizar() {
        return isloadingFinalizar;
    }

    public void setIsloadingFinalizar(Boolean isloadingFinalizar) {
        this.isloadingFinalizar = isloadingFinalizar;
    }
}
