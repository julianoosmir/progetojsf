package com.mycompany.paginacpf.model;

import java.util.List;

public class SeachMock {

    private String cpf;
    private List<ContextModel> contextModelList;

    public SeachMock() {

    }

    public SeachMock(String cpf, List<ContextModel> contextModelList) {
        this.cpf = cpf;
        this.contextModelList = contextModelList;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ContextModel> getContextModelList() {
        return contextModelList;
    }

    public void setContextModelList(List<ContextModel> contextModelList) {
        this.contextModelList = contextModelList;
    }
}
