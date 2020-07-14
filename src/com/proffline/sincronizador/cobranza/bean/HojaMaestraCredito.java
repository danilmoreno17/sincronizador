/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.cobranza.bean;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class HojaMaestraCredito {

    private CabeceraHojaMaestraCredito cabeceraHojaMaestraCredito;
    private List listaDiaDemoraTrasVencimiento;
    private List listaHistorialPago;
    private List listaValorPorVencer;
    private List listaNotaCredito;
    private List listaProtesto;
    private List listaCabeceraHojaMaestraCredito;

    public CabeceraHojaMaestraCredito getCabeceraHojaMaestraCredito() {
        return cabeceraHojaMaestraCredito;
    }

    public void setCabeceraHojaMaestraCredito(CabeceraHojaMaestraCredito cabeceraHojaMaestraCredito) {
        this.cabeceraHojaMaestraCredito = cabeceraHojaMaestraCredito;
    }

    public List getListaDiaDemoraTrasVencimiento() {
        return listaDiaDemoraTrasVencimiento;
    }

    public void setListaDiaDemoraTrasVencimiento(List listaDiaDemoraTrasVencimiento) {
        this.listaDiaDemoraTrasVencimiento = listaDiaDemoraTrasVencimiento;
    }

    public List getListaHistorialPago() {
        return listaHistorialPago;
    }

    public void setListaHistorialPago(List listaHistorialPago) {
        this.listaHistorialPago = listaHistorialPago;
    }

    public List getListaValorPorVencer() {
        return listaValorPorVencer;
    }

    public void setListaValorPorVencer(List listaValorPorVencer) {
        this.listaValorPorVencer = listaValorPorVencer;
    }

    public List getListaNotaCredito() {
        return listaNotaCredito;
    }

    public void setListaNotaCredito(List listaNotaCredito) {
        this.listaNotaCredito = listaNotaCredito;
    }

    public List getListaProtesto() {
        return listaProtesto;
    }

    public void setListaProtesto(List listaProtesto) {
        this.listaProtesto = listaProtesto;
    }

    public List getListaCabeceraHojaMaestraCredito() {
        return listaCabeceraHojaMaestraCredito;
    }

    public void setListaCabeceraHojaMaestraCredito(List listaCabeceraHojaMaestraCredito) {
        this.listaCabeceraHojaMaestraCredito = listaCabeceraHojaMaestraCredito;
    }

}
