/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.utilidades;

import java.util.Date;

/**
 *
 * @author Rolando
 */
public class Test2 {
    
    public static void  main(String[] args) {
//        String fecha = Util.convierteFechaHoyAFormatoYYYYMMDD(new Date());
        
//        String fecha = Util.convierteFecha("Thu Jan 01 10:13:13 COT 1970");
//        System.out.println("Fecha: " + fecha);
        
        Util.reiniciarBaseDatos();
        Util.generarToProffline();
    }
    
}
