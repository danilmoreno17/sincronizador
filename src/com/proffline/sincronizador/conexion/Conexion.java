/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.conexion;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import util.ConexionSAP;

/**
 *
 * @author Administrador
 */
public class Conexion {

    private static String strMandanteSAP;
    private static String strUsuarioSAP;
    private static String strPasswordSAP;
    private static String strIdiomaSAP;
    private static String strHostSAP;
    private static String strSistemaSAP;

    public static ConexionSAP obtenerConexionSAP() throws Exception {
        ConexionSAP con = null;
        Properties props = new Properties();
        InputStreamReader in = null;
        in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
        props.load(in);
        strMandanteSAP = props.getProperty("sap.mandante").trim();
        strUsuarioSAP = props.getProperty("sap.usuario").trim();
        strPasswordSAP = props.getProperty("sap.password").trim();
        strIdiomaSAP = props.getProperty("sap.idioma").trim();
        strHostSAP = props.getProperty("sap.host").trim();
        strSistemaSAP = props.getProperty("sap.sistema").trim();
        in.close();
        con = new ConexionSAP(strMandanteSAP, strUsuarioSAP, strPasswordSAP, strIdiomaSAP, strHostSAP, strSistemaSAP);
        return con;
    }
}
