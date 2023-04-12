package com.mycompany.paginacpf.resources;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
    public class LoginMB {

    private String esperanca = "esperança";

    private String cpfCnpj;

    private String cpf;
    
    private Boolean campoCpf;
    private Map<String, Boolean> erros;
    private Map<String, String> mensagens;

    FacesContext context = FacesContext.getCurrentInstance();
    String url = context.getExternalContext().getRequestContextPath();
    
    @PostConstruct
    public void init() {
        this.campoCpf = false;
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

    public void searchWhiteList() {
        this.setEsperanca("funfou button searchWhiteList " + this.cpf);

        this.campoCpf = true;
    }

    public void listener() {    
        this.setEsperanca("funfou listener ");
    }

    public void doEfetuarLogin() throws IOException {
               
        if (validarFormatocnpj(this.cpfCnpj)) {
           this.setEsperanca("funfou cpf  "  + this.cpf );
          context.getExternalContext().redirect(url + "/outrapagina.xhtml");
            
        } 
        if(validarFormatocpf(this.cpfCnpj)) {
            this.setEsperanca("funfou of");
            context.getExternalContext().redirect(url + "/novapagina.html");
        }
          
         this.setEsperanca("funfou cpf  "  + this.cpf );
      
    }
    
    private boolean validarFormatocpf(final String cpf) {

        final String cpfcnpj_PATTERN = "([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";

        final Pattern pattern = Pattern.compile(cpfcnpj_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    private boolean validarFormatocnpj(final String cpfcnpj) {

        final String cpfcnpj_PATTERN = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})";

        final Pattern pattern = Pattern.compile(cpfcnpj_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(cpfcnpj);
        return matcher.matches();
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEsperanca() {
        return esperanca;
    }

    public void setEsperanca(String esperanca) {
        this.esperanca = esperanca;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isCampoCpf() {
        return campoCpf;
    }

    public void setCampoCpf(boolean campoCpf) {
        this.campoCpf = campoCpf;
    }

}
