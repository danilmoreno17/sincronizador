/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class MaterialStock {

    private String codigo;
    private String stock;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "MaterialStock{" + "codigo=" + codigo + ", stock=" + stock + '}';
    }
}
