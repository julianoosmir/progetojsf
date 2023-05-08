package com.mycompany.paginacpf.resources;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ManagedBean
@ViewScoped
public class EmailMB {

    private Map<String, Boolean> erros;
    private Map<String, String> mensagens;
    private String email;

    @PostConstruct
    public void init() {

        try {

            this.erros = new HashMap<String, Boolean>();
            this.mensagens = new HashMap<String, String>();

            Field[] fields = this.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.getType().equals(String.class)) {
                    this.erros.put(field.getName(), false);
                    this.mensagens.put(field.getName(), null);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar mensagens" + e);
        }
    }

    public String verificarCampo() {

        return "sucesso";
    }

    public Map<String, Boolean> getErros() {
        return erros;
    }

    public void setErros(Map<String, Boolean> erros) {
        this.erros = erros;
    }

    public Map<String, String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(Map<String, String> mensagens) {
        this.mensagens = mensagens;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
