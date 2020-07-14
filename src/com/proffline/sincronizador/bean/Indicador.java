/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author MARCELO
 */
public class Indicador {

    private String codigoCliente;
    private String monto;
    private String acumudalo;
    private String estatus;

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getAcumudalo() {
        return acumudalo;
    }

    public void setAcumudalo(String acumudalo) {
        this.acumudalo = acumudalo;
    }
}
