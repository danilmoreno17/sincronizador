/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Dispositivo {

    private String strIdDispositivo;
    private String strTipoDispositivo;
    private String strNumeroSerieDispositivo;
    private String strCodigoActivo;
    private String strSimm;
    private String strImei;
    private String strNumeroTelefono;
    private String strNumeroSeguro;
    private String strIdUsuario;
    private String strNomUsuario;
    private String strDispositivoRelacionado;
    private String strEstado;
    private String strObservacion;
    private String strUsuReg;
    private String strFecReg;
    private String strHorReg;
    private String strUsuUpd;
    private String strFecUpd;
    private String strHorUpd;

    public String getStrCodigoActivo() {
        return strCodigoActivo;
    }

    public void setStrCodigoActivo(String strCodigoActivo) {
        this.strCodigoActivo = strCodigoActivo;
    }

    public String getStrDispositivoRelacionado() {
        return strDispositivoRelacionado;
    }

    public void setStrDispositivoRelacionado(String strDispositivoRelacionado) {
        this.strDispositivoRelacionado = strDispositivoRelacionado;
    }

    public String getStrEstado() {
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

    public String getStrFecReg() {
        return strFecReg;
    }

    public void setStrFecReg(String strFecReg) {
        this.strFecReg = strFecReg;
    }

    public String getStrFecUpd() {
        return strFecUpd;
    }

    public void setStrFecUpd(String strFecUpd) {
        this.strFecUpd = strFecUpd;
    }

    public String getStrHorReg() {
        return strHorReg;
    }

    public void setStrHorReg(String strHorReg) {
        this.strHorReg = strHorReg;
    }

    public String getStrHorUpd() {
        return strHorUpd;
    }

    public void setStrHorUpd(String strHorUpd) {
        this.strHorUpd = strHorUpd;
    }

    public String getStrIdDispositivo() {
        return strIdDispositivo;
    }

    public void setStrIdDispositivo(String strIdDispositivo) {
        this.strIdDispositivo = strIdDispositivo;
    }

    public String getStrIdUsuario() {
        return strIdUsuario;
    }

    public void setStrIdUsuario(String strIdUsuario) {
        this.strIdUsuario = strIdUsuario;
    }

    public String getStrImei() {
        return strImei;
    }

    public void setStrImei(String strImei) {
        this.strImei = strImei;
    }

    public String getStrNomUsuario() {
        return strNomUsuario;
    }

    public void setStrNomUsuario(String strNomUsuario) {
        this.strNomUsuario = strNomUsuario;
    }

    public String getStrNumeroSeguro() {
        return strNumeroSeguro;
    }

    public void setStrNumeroSeguro(String strNumeroSeguro) {
        this.strNumeroSeguro = strNumeroSeguro;
    }

    public String getStrNumeroSerieDispositivo() {
        return strNumeroSerieDispositivo;
    }

    public void setStrNumeroSerieDispositivo(String strNumeroSerieDispositivo) {
        this.strNumeroSerieDispositivo = strNumeroSerieDispositivo;
    }

    public String getStrNumeroTelefono() {
        return strNumeroTelefono;
    }

    public void setStrNumeroTelefono(String strNumeroTelefono) {
        this.strNumeroTelefono = strNumeroTelefono;
    }

    public String getStrObservacion() {
        return strObservacion;
    }

    public void setStrObservacion(String strObservacion) {
        this.strObservacion = strObservacion;
    }

    public String getStrSimm() {
        return strSimm;
    }

    public void setStrSimm(String strSimm) {
        this.strSimm = strSimm;
    }

    public String getStrTipoDispositivo() {
        return strTipoDispositivo;
    }

    public void setStrTipoDispositivo(String strTipoDispositivo) {
        this.strTipoDispositivo = strTipoDispositivo;
    }

    public String getStrUsuReg() {
        return strUsuReg;
    }

    public void setStrUsuReg(String strUsuReg) {
        this.strUsuReg = strUsuReg;
    }

    public String getStrUsuUpd() {
        return strUsuUpd;
    }

    public void setStrUsuUpd(String strUsuUpd) {
        this.strUsuUpd = strUsuUpd;
    }

    @Override
    public String toString() {
        return "Dispositivo{" + "strIdDispositivo=" + strIdDispositivo + ", strTipoDispositivo=" + strTipoDispositivo + ", strNumeroSerieDispositivo=" + strNumeroSerieDispositivo + ", strCodigoActivo=" + strCodigoActivo + ", strSimm=" + strSimm + ", strImei=" + strImei + ", strNumeroTelefono=" + strNumeroTelefono + ", strNumeroSeguro=" + strNumeroSeguro + ", strIdUsuario=" + strIdUsuario + ", strNomUsuario=" + strNomUsuario + ", strDispositivoRelacionado=" + strDispositivoRelacionado + ", strEstado=" + strEstado + ", strObservacion=" + strObservacion + ", strUsuReg=" + strUsuReg + ", strFecReg=" + strFecReg + ", strHorReg=" + strHorReg + ", strUsuUpd=" + strUsuUpd + ", strFecUpd=" + strFecUpd + ", strHorUpd=" + strHorUpd + '}';
    }
}
