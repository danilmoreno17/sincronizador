/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Empleado {

    private String strIdEmpleado;
    private String strNombreEmpleado;
    private String strIdSupervisor;

    public String getStrIdEmpleado() {
        return strIdEmpleado;
    }

    public void setStrIdEmpleado(String strIdEmpleado) {
        this.strIdEmpleado = strIdEmpleado;
    }

    public String getStrIdSupervisor() {
        return strIdSupervisor;
    }

    public void setStrIdSupervisor(String strIdSupervisor) {
        this.strIdSupervisor = strIdSupervisor;
    }

    public String getStrNombreEmpleado() {
        return strNombreEmpleado;
    }

    public void setStrNombreEmpleado(String strNombreEmpleado) {
        this.strNombreEmpleado = strNombreEmpleado;
    }
}
