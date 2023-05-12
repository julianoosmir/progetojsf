package com.mycompany.paginacpf.resources;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
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
        if(!emailValidator(this.email)){
            isEmailInvalido();
        };
        return "sucesso";
    }

    private void isEmailInvalido() {
        String invalido = "invalido";
        this.erros.put(invalido, true);
        this.mensagens.put(invalido, "Email Invalido.");
    }

    public boolean emailValidator(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
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
