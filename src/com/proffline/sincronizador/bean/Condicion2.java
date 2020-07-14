/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Condicion2 {

    private int intPrioridadGrupo;
    private int intPrioridadInterna;
    private String strCliente;
    private String strGrupoCliente;
    private String strCanal;
    private String strTipo;
    private Double dblDscto;

    public Double getDblDscto() {
        return dblDscto;
    }

    public void setDblDscto(Double dblDscto) {
        this.dblDscto = dblDscto;
    }

    public int getIntPrioridadGrupo() {
        return intPrioridadGrupo;
    }

    public void setIntPrioridadGrupo(int intPrioridadGrupo) {
        this.intPrioridadGrupo = intPrioridadGrupo;
    }

    public int getIntPrioridadInterna() {
        return intPrioridadInterna;
    }

    public void setIntPrioridadInterna(int intPrioridadInterna) {
        this.intPrioridadInterna = intPrioridadInterna;
    }

    public String getStrCanal() {
        return strCanal;
    }

    public void setStrCanal(String strCanal) {
        this.strCanal = strCanal;
    }

    public String getStrCliente() {
        return strCliente;
    }

    public void setStrCliente(String strCliente) {
        this.strCliente = strCliente;
    }

    public String getStrGrupoCliente() {
        return strGrupoCliente;
    }

    public void setStrGrupoCliente(String strGrupoCliente) {
        this.strGrupoCliente = strGrupoCliente;
    }

    public String getStrTipo() {
        return strTipo;
    }

    public void setStrTipo(String strTipo) {
        this.strTipo = strTipo;
    }
}
