/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Condicion1 {

    private int intPrioridadGrupo;
    private int intPrioridadInterna;
    private String strClaseMaterial;
    private String strCliente;
    private String strCondicionPago;
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

    public String getStrClaseMaterial() {
        return strClaseMaterial;
    }

    public void setStrClaseMaterial(String strClaseMaterial) {
        this.strClaseMaterial = strClaseMaterial;
    }

    public String getStrCliente() {
        return strCliente;
    }

    public void setStrCliente(String strCliente) {
        this.strCliente = strCliente;
    }

    public String getStrCondicionPago() {
        return strCondicionPago;
    }

    public void setStrCondicionPago(String strCondicionPago) {
        this.strCondicionPago = strCondicionPago;
    }

    public String getStrTipo() {
        return strTipo;
    }

    public void setStrTipo(String strTipo) {
        this.strTipo = strTipo;
    }
}
