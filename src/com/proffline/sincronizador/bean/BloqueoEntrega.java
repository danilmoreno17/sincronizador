/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class BloqueoEntrega {

    private String strSincronizado;
    private String strCodigo;
    private String strDescripcion;

    public String getStrCodigo() {
        return strCodigo;
    }

    public void setStrCodigo(String strCodigo) {
        this.strCodigo = strCodigo;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public String getStrSincronizado() {
        return strSincronizado;
    }

    public void setStrSincronizado(String strSincronizado) {
        this.strSincronizado = strSincronizado;
    }
}
