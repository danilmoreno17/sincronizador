/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Usuario {

    private String strMandante;
    private String strIdUsuario;
    private String strNomUsuario;
    private String strClaUsuario;
    private String strFecCre;
    private String strFecUltAccSis;
    private String strHorUltAccSis;
    private String strBloqueado;
    private String strCambioClave;
    private String strIntento;
    private String strUsuarioBloqueado;
    private String strIdentificacion;
    private String strUsuario;
    private String strDivision;

    public String getStrBloqueado() {
        return strBloqueado;
    }

    public void setStrBloqueado(String strBloqueado) {
        this.strBloqueado = strBloqueado;
    }

    public String getStrCambioClave() {
        return strCambioClave;
    }

    public void setStrCambioClave(String strCambioClave) {
        this.strCambioClave = strCambioClave;
    }

    public String getStrClaUsuario() {
        return strClaUsuario;
    }

    public void setStrClaUsuario(String strClaUsuario) {
        this.strClaUsuario = strClaUsuario;
    }

    public String getStrFecCre() {
        return strFecCre;
    }

    public void setStrFecCre(String strFecCre) {
        this.strFecCre = strFecCre;
    }

    public String getStrFecUltAccSis() {
        return strFecUltAccSis;
    }

    public void setStrFecUltAccSis(String strFecUltAccSis) {
        this.strFecUltAccSis = strFecUltAccSis;
    }

    public String getStrHorUltAccSis() {
        return strHorUltAccSis;
    }

    public void setStrHorUltAccSis(String strHorUltAccSis) {
        this.strHorUltAccSis = strHorUltAccSis;
    }

    public String getStrIdUsuario() {
        return strIdUsuario;
    }

    public void setStrIdUsuario(String strIdUsuario) {
        this.strIdUsuario = strIdUsuario;
    }

    public String getStrIdentificacion() {
        return strIdentificacion;
    }

    public void setStrIdentificacion(String strIdentificacion) {
        this.strIdentificacion = strIdentificacion;
    }

    public String getStrIntento() {
        return strIntento;
    }

    public void setStrIntento(String strIntento) {
        this.strIntento = strIntento;
    }

    public String getStrMandante() {
        return strMandante;
    }

    public void setStrMandante(String strMandante) {
        this.strMandante = strMandante;
    }

    public String getStrNomUsuario() {
        return strNomUsuario;
    }

    public void setStrNomUsuario(String strNomUsuario) {
        this.strNomUsuario = strNomUsuario;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public String getStrUsuarioBloqueado() {
        return strUsuarioBloqueado;
    }

    public void setStrUsuarioBloqueado(String strUsuarioBloqueado) {
        this.strUsuarioBloqueado = strUsuarioBloqueado;
    }

    public String getStrDivision() {
        return strDivision;
    }

    public void setStrDivision(String strDivision) {
        this.strDivision = strDivision;
    }

    @Override
    public String toString() {
        return "Usuario{" + "strMandante=" + strMandante + ", strIdUsuario=" + strIdUsuario + ", strNomUsuario=" + strNomUsuario + ", strClaUsuario=" + strClaUsuario + ", strFecCre=" + strFecCre + ", strFecUltAccSis=" + strFecUltAccSis + ", strHorUltAccSis=" + strHorUltAccSis + ", strBloqueado=" + strBloqueado + ", strCambioClave=" + strCambioClave + ", strIntento=" + strIntento + ", strUsuarioBloqueado=" + strUsuarioBloqueado + ", strIdentificacion=" + strIdentificacion + ", strUsuario=" + strUsuario + '}';
    }
}
