/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class ComboDetalle {

    private int cantidad;
    private String codigoMaterial;
    private String descripcionMaterial;
    private String idCombo;
    private String unidad;
    private String precioNeto;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public String getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(String idCombo) {
        this.idCombo = idCombo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getPrecioNeto() {
        return precioNeto;
    }

    public void setPrecioNeto(String precioNeto) {
        this.precioNeto = precioNeto;
    }

    @Override
    public String toString() {
        return "ComboDetalle{" + "cantidad=" + cantidad + ", codigoMaterial=" + codigoMaterial + ", descripcionMaterial=" + descripcionMaterial + ", idCombo=" + idCombo + ", unidad=" + unidad + '}';
    }
}
