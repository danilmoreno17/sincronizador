/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class ClaseMaterial {

    private String strCodigoClaseMaterial;
    private String strDescripcionClaseMaterial;
    private String strCodigoMaterial;

    public String getStrCodigoClaseMaterial() {
        return strCodigoClaseMaterial;
    }

    public void setStrCodigoClaseMaterial(String strCodigoClaseMaterial) {
        this.strCodigoClaseMaterial = strCodigoClaseMaterial;
    }

    public String getStrCodigoMaterial() {
        return strCodigoMaterial;
    }

    public void setStrCodigoMaterial(String strCodigoMaterial) {
        this.strCodigoMaterial = strCodigoMaterial;
    }

    public String getStrDescripcionClaseMaterial() {
        return strDescripcionClaseMaterial;
    }

    public void setStrDescripcionClaseMaterial(String strDescripcionClaseMaterial) {
        this.strDescripcionClaseMaterial = strDescripcionClaseMaterial;
    }

    @Override
    public String toString() {
        return "ClaseMaterial{" + "strCodigoClaseMaterial=" + strCodigoClaseMaterial + ", strDescripcionClaseMaterial=" + strDescripcionClaseMaterial + ", strCodigoMaterial=" + strCodigoMaterial + '}';
    }
}
