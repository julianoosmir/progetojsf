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
import javax.servlet.http.HttpSession;

@Named
@ManagedBean
@SessionScoped
public class LoginMB {

    private String esperanca = "esperança";

    private String cpfCnpj;

    private String cpf;
    private Boolean campoCpf;
    private Map<String, Boolean> erros;
    private Map<String, String> mensagens;
    private boolean redirecionar;
    private String url;

    private String erroMenssagem;

    private String btnMenssage;


    @PostConstruct
    public void init() {
        this.campoCpf = false;
        this.redirecionar = false;
      //  setTelaErroMsg();
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
    private void setTelaErroMsg(){
        setErroMenssagem("Desculpe, o serviço de identificação de CNPJ"
                + " está temporariamente indisponível no momento."
                + " Por favor, tente novamente mais tarde");

        setUrl("index.xhtml");
        setBtnMenssage("voltar");
    }
    public String verificarCampo() {
        this.esperanca = this.cpfCnpj.replaceAll("\\.|-|/", "");
        ;
        if (this.validarDadosCnpj()) {
            boolean whitelist = temWhitlelist();
            this.campoCpf = whitelist;
            if (!whitelist) {
                doEfetuarLoginAsIs();
            }
        }
        if (this.cpf != null && validarDadosCpf() && validarFormatocnpj(this.cpfCnpj)) {
            redirecinarLoginID();
        }
        return "sucesso";
    }
    private void telaDeErroWhiteList() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            setErroMenssagem("Desculpe, o serviço de identificação de CNPJ"
                    + " está temporariamente indisponível no momento."
                    + " Por favor, tente novamente mais tarde");


            setUrl("index.xhtml");
            setBtnMenssage("voltar");
            context.getExternalContext().redirect("tela_erro_whitelist.xhtml");
        } catch (Exception e) {

        }
    }
    private void redirecinarLoginID() {
        if (firstLogin()) {
            redirectToEmail();
        } else {
            doEfetuarLoginID();
        }

    }

    private void redirectToEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("tela_email.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean firstLogin() {
        return false;
    }

    public boolean validarDadosCnpj() {
        boolean dadosValidos = true;
        final String cpfcnpjName = "cpfcnpj";
        if (StringUtils.isEmpty(this.cpfCnpj)) {
            this.erros.put(cpfcnpjName, true);
            this.mensagens.put(cpfcnpjName, "Campo CPF/CNPJ é obrigatório.");
            dadosValidos = false;
            this.setEsperanca("Campo CPF/CNPJ é obrigatório.");
        }

        if (!validarFormatocnpj(this.cpfCnpj)) {
            this.erros.put(cpfcnpjName, true);
            this.mensagens.put(cpfcnpjName, "Formato CPF/CNPJ inválido.");
            dadosValidos = false;
        } else {
            this.erros.clear();
            this.mensagens.clear();
            dadosValidos = true;
        }
        return dadosValidos;
    }

    public boolean validarDadosCpf() {
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
        this.setUrl("https://login.dsv.bradescoseguros.com.br/nidp/idff/sso?id=secure_name_pasword_form_pneg&sid=0&option=credential&sid=0&target=https%3A%2F%2Fwwwn.dsv.bradescoseguros.com.br%2Fpnegocios%2Fwps%2Fmyportal%2Fportalnegocios%2Farealogada%2F");
        this.redirecionar = this.campoCpf;
        try {
            context.getExternalContext().redirect("redirecinar_login.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doEfetuarLoginAsIs() {

        this.telaDeErroWhiteList();
    }

    private void telaDeErro() {
        FacesContext context = FacesContext.getCurrentInstance();
        ErroMB erroMB = new ErroMB();

        try {
            erroMB.setErroMenssagem("Desculpe, o serviço de identificação de CNPJ"
                    + " está temporariamente indisponível no momento."
                    + " Por favor, tente novamente mais tarde");

            erroMB.setUrl("index.xhtml");
            erroMB.setBtnMenssage("voltar");
            context.getExternalContext().redirect("tela_erro.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean temWhitlelist() {
        WhiteListService whiteListService = new WhiteListService();
        return whiteListService.
                runRequest(this.setDataForm()).getBody() instanceof Boolean
                ? (Boolean) whiteListService.runRequest(this.setDataForm()).getBody()
                : false;
    }

    public void limparTela() {

        this.setCpf(StringUtils.EMPTY);
        this.setCpfCnpj(StringUtils.EMPTY);
        this.campoCpf = false;
        this.erros.clear();
        this.mensagens.clear();
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

    public boolean isRedirecionar() {
        return redirecionar;
    }

    public void setRedirecionar(boolean redirecionar) {
        this.redirecionar = redirecionar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getErroMenssagem() {
        return erroMenssagem;
    }

    public void setErroMenssagem(String erroMenssagem) {
        this.erroMenssagem = erroMenssagem;
    }

    public String getBtnMenssage() {
        return btnMenssage;
    }

    public void setBtnMenssage(String btnMenssage) {
        this.btnMenssage = btnMenssage;
    }
}
