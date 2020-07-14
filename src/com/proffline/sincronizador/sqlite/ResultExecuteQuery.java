/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.sqlite;

import com.proffline.sincronizador.conexion.ConexionJDBC;
import com.proffline.sincronizador.utilidades.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Rolando
 */
public class ResultExecuteQuery {

    private static final int BASEVENDEDOR = 0;
    private static final int BASEMAESTRA = 1;

    public static HashMap<Integer, HashMap> obtenerDatosConsulta(String sqlQuery, HashMap column, int conector) {
        HashMap<Integer, HashMap> map = new HashMap<Integer, HashMap>();
        ConexionJDBC conn = new ConexionJDBC();
        int fila = 0;
        String tipo[] = null;
        HashMap<String, String> setMap = null;
        Connection connection = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            if (conector == BASEMAESTRA) {
                connection = conn.obtenerConexionSQL();
            } else if (conector == BASEVENDEDOR) {
                connection = conn.obtenerConexionSQLPorVendedor();
            }
//            connection = conn.obtenerConexionSQL();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                setMap = new HashMap<String, String>();
                Iterator it2 = column.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry e = (Map.Entry) it2.next();
                    tipo = e.getKey().toString().trim().split(":");
                    if (tipo[0].trim().equals("String")) {
                        setMap.put(e.getValue().toString(),
                                rs.getString(e.getValue().toString()));
                    }
                }
                map.put(fila, setMap);
                fila++;
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            return map;
        }
    }

    public static HashMap obtenerDatosConsultaPorVendedor(String sqlQuery, HashMap column, String strCodigoVendedor) {
        HashMap map;
        ConexionJDBC conn;
        int fila;
        Connection connection;
        ResultSet rs;
        Statement stmt;
        map = new HashMap();
        conn = new ConexionJDBC();
        fila = 0;
        String tipo[] = null;
        HashMap setMap = null;
        connection = null;
        rs = null;
        stmt = null;
        try {
            connection = conn.obtenerConexionPorVendedor(strCodigoVendedor);
            stmt = connection.createStatement();
            for (rs = stmt.executeQuery(sqlQuery); rs.next();) {
                setMap = new HashMap();
                Iterator it2 = column.entrySet().iterator();
                do {
                    if (!it2.hasNext()) {
                        break;
                    }
                    java.util.Map.Entry e = (java.util.Map.Entry) it2.next();
                    tipo = e.getKey().toString().trim().split(":");
                    if (tipo[0].trim().equals("String")) {
                        setMap.put(e.getValue().toString(), rs.getString(e.getValue().toString()));
                    }
                } while (true);
                map.put(Integer.valueOf(fila), setMap);
                fila++;
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } // Misplaced declaration of an exception variable
                catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
                }
            }
            return map;
        }

    }
}
