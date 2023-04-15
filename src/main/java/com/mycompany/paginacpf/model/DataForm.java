package com.mycompany.paginacpf.model;
public class DataForm {

    private Boolean loggedIdentidadeDigital;
    private String codigoProdutor;
    private String cpfUsuario;
    private String cpfCnpjPessoaVinculo;

    public DataForm() {
    }

    public String getCodigoProdutor() {
        return codigoProdutor;
    }

    public void setCodigoProdutor(String codigoProdutor) {
        this.codigoProdutor = codigoProdutor;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getCpfCnpjPessoaVinculo() {
        return cpfCnpjPessoaVinculo;
    }

    public void setCpfCnpjPessoaVinculo(String cpfCnpjPessoaVinculo) {
        this.cpfCnpjPessoaVinculo = cpfCnpjPessoaVinculo;
    }

    public Boolean getLoggedIdentidadeDigital() {
        return loggedIdentidadeDigital;
    }

    public void setLoggedIdentidadeDigital(Boolean loggedIdentidadeDigital) {
        this.loggedIdentidadeDigital = loggedIdentidadeDigital;
    }

    @Override
    public String toString() {
        return "DataForm{" + "loggedIdentidadeDigital=" + loggedIdentidadeDigital + ", codigoProdutor='"
                + codigoProdutor + '\'' + ", cpfUsuario='" + cpfUsuario + '\'' + ", cpfCnpjPessoaVinculo='"
                + cpfCnpjPessoaVinculo + '\'' + '}';
    }
}
