package com.mycompany.paginacpf.resources;

import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.model.PersonalDataForm;
import com.mycompany.paginacpf.service.Verificador;
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
public class LoadLoginMB {

    private Boolean isloading;

    private boolean erro;

    @PostConstruct
    public void init() {
        this.erro = false;
        this.isloading = true;
    }

    private Boolean verificarContexto() throws IOException {
        Verificador verificador = new Verificador();
        try {
            if (erro) {
                return null;
            } else {
                return verificador.verificarServiceContext(setPersonal());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void telaErro() {
        ErroMB erroMB = new ErroMB();
        erroMB.setErroMenssagem("Sistema indisponível no momento."
                + " Por favor tente novamente mais tarde");

        erroMB.setUrl("loading.xhtml");
        erroMB.setBtnMenssage("Tentar novamente");

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("tela_erro.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoadLoginMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void evento() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            Boolean contextoVerificado = verificarContexto();
            Thread.sleep(5000);
            if (Boolean.TRUE.equals(contextoVerificado)) {
                context.getExternalContext().redirect("cronometro.xhtml");
            } else {
                context.getExternalContext().redirect("index.xhtml");
            }

        } catch (Exception e) {
            telaErro();
        }

    }

    public DataForm setPersonal() {
        PersonalDataForm personalDataForm = new PersonalDataForm();
        personalDataForm.setCpfUsuario("08418621842");
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
