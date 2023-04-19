package com.mycompany.paginacpf.resources;

import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.service.WhiteListService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@ManagedBean
@SessionScoped
public class LoginMB {

    private String esperanca = "esperança";

    private String cpfCnpj;

    private String cpf;
    private Boolean campoCpf;
    private Boolean isloading;
    private Map<String, Boolean> erros;
    private Map<String, String> mensagens;

    @PostConstruct
    public void init() {
        this.campoCpf = false;
        this.isloading = true;
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
        if (this.validarDadosCnpj()) {
            boolean whitelist = temWhitlelist();
            this.campoCpf = whitelist;
            if (!whitelist) {
                doEfetuarLoginAsIs();
            }
        }
        if (this.cpf != null && validarDadosCpf() && validarFormatocnpj(this.cpfCnpj)) {
            doEfetuarLoginID();
        }
        return "sucesso";
    }


    public boolean validarDadosCnpj(){
        boolean dadosValidos = true;
        final String cpfcnpjName = "cpfcnpj";
        if (StringUtils.isEmpty(this.cpfCnpj)) {
            this.erros.put(cpfcnpjName, true);
            this.mensagens.put(cpfcnpjName, "Campo CPF/CNPJ é obrigatório.");
            dadosValidos = false;
            this.setEsperanca("Campo CPF/CNPJ é obrigatório.");
        }
        this.setEsperanca(this.cpfCnpj);
        if (!validarFormatocnpj(this.cpfCnpj)) {
            this.erros.put(cpfcnpjName, true);
            this.mensagens.put(cpfcnpjName, "Formato CPF/CNPJ inválido.");
            dadosValidos = false;
        }else {
            this.erros.clear();
            this.mensagens.clear();
            dadosValidos = true;
        }
        return dadosValidos;
    }

    public boolean validarDadosCpf(){
        boolean dadosValidos = true;
        final String cpfName = "cpf";
        if (!this.validarFormatocpf(this.cpf)) {
            this.erros.put(cpfName, true);
            this.mensagens.put(cpfName, "Formato CPF inválido.");
            dadosValidos = false;
        }
        return dadosValidos;
    }
    private void doEfetuarLoginID() {
        FacesContext context = FacesContext.getCurrentInstance();
        final String cpfnaoencontrado = "cpfnaoencontrado";
        this.erros.put(cpfnaoencontrado, true);
        this.mensagens.put(cpfnaoencontrado, "CPF não encontrado, por favor insira um CPF valido");


    }

    private void doEfetuarLoginAsIs() {

        this.telaDeErro();
    }
    private void telaDeErro(){
        FacesContext context = FacesContext.getCurrentInstance();
        final String errowhilist = "errowhilist";
        try {
            this.erros.put(errowhilist, true);
            this.mensagens.put(errowhilist, "Desculpe, o serviço de identificação de CNPJ" +
                    " está temporariamente indisponivel no momento." +
                    " Por favor, tente novamente mais tarde");

            context.getExternalContext().redirect("erro_verificacao_whitelist.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean temWhitlelist() {
        WhiteListService whiteListService = new WhiteListService();
        return whiteListService.
                runRequest(this.setDataForm()).getBody() instanceof Boolean ?
                (Boolean) whiteListService.runRequest(this.setDataForm()).getBody()
                : false;
    }

    public String voltar(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.esperanca;
    }

    private DataForm setDataForm() {
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
    public Boolean getCampoCpf() {
        return campoCpf;
    }
    public void setCampoCpf(Boolean campoCpf) {
        this.campoCpf = campoCpf;
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

    public Boolean getIsloading() {
        return isloading;
    }

    public void setIsloading(Boolean isloading) {
        this.isloading = isloading;
    }
}
