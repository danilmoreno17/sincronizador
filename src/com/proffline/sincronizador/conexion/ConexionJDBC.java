/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.conexion;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rolando
 */
public class ConexionJDBC {

    private Connection connection = null;

    public Connection obtenerConexionSQL() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Properties props = new Properties();
        InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
        props.load(in);
        connection = DriverManager.getConnection("jdbc:sqlite:" + props.getProperty("local.directorio").trim());
//        connection = DriverManager.getConnection("jdbc:sqlite:" + props.getProperty("local.plantilla").trim());
        in.close();
        return connection;
    }
    
    public Connection obtenerConexionSQLTemporal() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Properties props = new Properties();
        InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
        props.load(in);
        String db= props.getProperty("local.plantilla").trim();
        connection = DriverManager.getConnection("jdbc:sqlite:" + db);
        in.close();
        return connection;
    }

    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection obtenerConexionPorVendedor(String strCodigoVendedor) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Properties props = new Properties();
        InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
        props.load(in);
        String strDbPorVendedor = (new StringBuilder()).append("jdbc:sqlite:").append(props.getProperty("local.directorio")).append("_").append(strCodigoVendedor).toString();
        if(strCodigoVendedor.equalsIgnoreCase("")){
            strDbPorVendedor = (new StringBuilder()).append("jdbc:sqlite:").append(props.getProperty("local.directorio")).toString();
        }
        
        connection = DriverManager.getConnection(strDbPorVendedor);
        in.close();
        return connection;
    }

    public Connection obtenerConexionSQLPorVendedor() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Properties props = new Properties();
        InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
        props.load(in);
        connection = DriverManager.getConnection((new StringBuilder()).append("jdbc:sqlite:").append(props.getProperty("local.directorio").trim()).toString());
        in.close();
        return connection;
    }
}
