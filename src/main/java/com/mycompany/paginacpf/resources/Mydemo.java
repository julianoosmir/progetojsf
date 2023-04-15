/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.paginacpf.resources;

public class Mydemo  {

    private String cnpj;
    private String cpf;

    public Mydemo(String cnpj, String cpf) {
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    public Mydemo() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
