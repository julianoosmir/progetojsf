/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.paginacpf.resources;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ManagedBean
@SessionScoped
public class ErroMB {
    
    private static String erroMenssagem;

    private  static String btnMenssage;

    private static String url;
    
    public void redirecinar(){
          FacesContext context = FacesContext.getCurrentInstance();
          try{
              context.getExternalContext().redirect(url);
          }catch(Exception e){
                throw new RuntimeException(e);
          }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getBtnMenssage() {
        return btnMenssage;
    }

    public void setBtnMenssage(String btnMenssage) {
        ErroMB.btnMenssage = btnMenssage;
    }
    
    public String getErroMenssagem() {
        return erroMenssagem;
    }

    public void setErroMenssagem(String erroMenssagem) {
        this.erroMenssagem = erroMenssagem;
    }
    
}
