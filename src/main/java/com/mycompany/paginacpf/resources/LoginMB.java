package com.mycompany.paginacpf.resources;

import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.service.WhiteListService;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ManagedBean
@SessionScoped
public class LoginMB {

    private String esperanca = "esperança";

    private String cpfCnpj;

    private String cpf;

    private Boolean campoCpf;

    private WhiteListService whiteListService;
    private Map<String, Boolean> erros;
    private Map<String, String> mensagens;

    @PostConstruct
    public void init() {
        this.campoCpf = false;
        this.whiteListService = new WhiteListService();
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
        this.setEsperanca("cpf : " + this.cpf + " e cnpj : " + this.cpfCnpj);

        this.campoCpf = true;
    }

    public void listener() {
        this.setEsperanca("funfou listener ");
    }

    public String doEfetuarLogin() throws IOException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        String url = context.getExternalContext().getRequestContextPath();
        try {
            if (validarFormatocnpj(this.cpfCnpj)) {
                this.campoCpf = this.whiteListService.runRequest(this.setDataForm()).getBody() instanceof Boolean ?
                        (Boolean) this.whiteListService.runRequest(this.setDataForm()).getBody() : this.campoCpf;
            }

            if (validarFormatocpf(this.cpfCnpj)) {
                context.getExternalContext().redirect(url + "/novapagina.html");
                return "funfou";
            }

            if (this.cpf != null && validarFormatocpf(this.cpf) && validarFormatocnpj(this.cpfCnpj)) {
                this.setEsperanca(this.cpfCnpj + " e cpf  : " + this.cpf);
                context.getExternalContext().redirect(url + "/outrapagina.xhtml");
                return this.getEsperanca();
            }
        } catch (IOException exception){
            this.setEsperanca( exception.getMessage());

        }
        return this.getEsperanca();
    }

    private DataForm setDataForm(){
        DataForm dataForm = new DataForm();
        dataForm.setCpfUsuario(this.cpf);
        dataForm.setCpfCnpjPessoaVinculo(this.getCpfCnpj());
        return dataForm;
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
