package com.mycompany.paginacpf.resources;

import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.model.PersonalDataForm;
import com.mycompany.paginacpf.service.Verificador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ManagedBean
@SessionScoped
public class LoadLoginMB {
    private Boolean isloading;
    @PostConstruct
    public void init() {
        this.isloading = true;
    }

    public String evento() {
        FacesContext context = FacesContext.getCurrentInstance();
        Verificador verificador = new Verificador();

        Boolean contextoVerificado = verificador.verificarServiceContext(setPersonal());
        try {
            Thread.sleep(5000);
            if (contextoVerificado) {
                context.getExternalContext().redirect("cronometro.xhtml");
            } else {
                context.getExternalContext().redirect("index.xhtml");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "fodase";
    }

    public DataForm setPersonal() {
        PersonalDataForm personalDataForm = new PersonalDataForm();
        personalDataForm.setCpfUsuario("00000000272");
        DataForm dataForm = new DataForm();
        dataForm.setPersonalData(personalDataForm);
        return dataForm;
    }

    public Boolean getIsloading() {
        return isloading;
    }

    public void setIsloading(Boolean isloading) {
        this.isloading = isloading;
    }
}
