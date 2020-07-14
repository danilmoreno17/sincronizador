/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Combo {

    private String strIdCombo;
    private String strUnidad;
    private String strCodigo;
    private String strNombre;
    private String strPrecio;

    public String getStrCodigo() {
        return strCodigo;
    }

    public void setStrCodigo(String strCodigo) {
        this.strCodigo = strCodigo;
    }

    public String getStrIdCombo() {
        return strIdCombo;
    }

    public void setStrIdCombo(String strIdCombo) {
        this.strIdCombo = strIdCombo;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrPrecio() {
        return strPrecio;
    }

    public void setStrPrecio(String strPrecio) {
        this.strPrecio = strPrecio;
    }

    public String getStrUnidad() {
        return strUnidad;
    }

    public void setStrUnidad(String strUnidad) {
        this.strUnidad = strUnidad;
    }

    @Override
    public String toString() {
        return "Combo{" + "strIdCombo=" + strIdCombo + ", strUnidad=" + strUnidad + ", strCodigo=" + strCodigo + ", strNombre=" + strNombre + ", strPrecio=" + strPrecio + '}';
    }
}
