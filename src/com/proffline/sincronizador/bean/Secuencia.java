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
public class Secuencia {
    
    private String codigoVendedor;
    private String secuenciaCobranza;
    private String secuenciaPedido;

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getSecuenciaCobranza() {
        return secuenciaCobranza;
    }

    public void setSecuenciaCobranza(String secuenciaCobranza) {
        this.secuenciaCobranza = secuenciaCobranza;
    }

    public String getSecuenciaPedido() {
        return secuenciaPedido;
    }

    public void setSecuenciaPedido(String secuenciaPedido) {
        this.secuenciaPedido = secuenciaPedido;
    }
    
}
