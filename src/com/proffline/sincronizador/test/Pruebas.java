/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.test;

import com.proffline.sincronizador.utilidades.Util;


/**
 *
 * @author Administrador
 */
public class Pruebas {

    public static void main(String[] args) throws Exception {
//        Util.reiniciarBaseDatosRespaldo();
//        Util.reiniciarBaseDatos();
        Util.generarToProffline();
        Util.reiniciarDbTemplate();
//        
    }
    
}
