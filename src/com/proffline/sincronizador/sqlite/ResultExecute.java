/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.sqlite;

import com.proffline.sincronizador.conexion.ConexionJDBC;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
//import com.promesa.dao.ResultExecuteQuery;
//import com.promesa.util.Constante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rolando
 */
public class ResultExecute {

    ConexionJDBC conn = new ConexionJDBC();
    Connection connection = null;
    ResultSet rs = null;
    Statement stmt = null;
    public int getCountRow(String sql, String Vendedor) {
		ResultExecuteQuery resultExecuteQuery = null;
		HashMap column = new HashMap();
		column.put("String:0", "filas");
		int f = 0;
		try {
			resultExecuteQuery = new ResultExecuteQuery();
			HashMap mapResultado = resultExecuteQuery.obtenerDatosConsultaPorVendedor(sql, column, Vendedor);
			HashMap res = null;
			res = (HashMap) mapResultado.get(0);
			f = Integer.parseInt(res.get("filas").toString().trim());
			
		} catch (Exception e) {
		}
		return f;
	}
    public void ejecutarConsulta(List<String> queries) throws Exception {
        connection = conn.obtenerConexionSQL();
//        connection = conn.obtenerConexionSQLTemporal();
        final boolean oldAutoCommit = connection.getAutoCommit();
        stmt = connection.createStatement();
        connection.setAutoCommit(false);
        rs = null;
        try {
            VentanaPrincipal.obtenerInstancia().setMaximoProgresoParcial(queries.size());
            int contador = 0;
            for (String query : queries) {
                stmt.execute(query);
                VentanaPrincipal.obtenerInstancia().setProgresoParcial(contador++);
            }
            VentanaPrincipal.obtenerInstancia().setProgresoParcial(queries.size());
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    throw e;
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw e;
                }
            }
            if (conn != null) {
                try {
                    connection.commit();
                    connection.setAutoCommit(oldAutoCommit);
                    conn.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }
    public void CrearConexionPorVendedor(String strCodigoVendedor) throws Exception{
    	connection = conn.obtenerConexionPorVendedor(strCodigoVendedor);
    	stmt = connection.createStatement();
        connection.setAutoCommit(false);
        rs = null;
    }
    public void ejecutarConsultaPorVendedor2(List<ClsQueries> queries, String strCodigoVendedor) throws Exception {
    	connection = conn.obtenerConexionPorVendedor(strCodigoVendedor);
        connection.setAutoCommit(false);
        rs = null;
        boolean oldAutoCommit;
        oldAutoCommit = connection.getAutoCommit();
        Statement stmt=connection.createStatement();;
        try {
            VentanaPrincipal.obtenerInstancia().setMaximoProgresoParcial(queries.size());
            int contador = 0;
            String tipo = "";
            String tabla = "";
            for (contador=0; contador<queries.size(); VentanaPrincipal.obtenerInstancia().setProgresoParcial(contador++)) {
            	ClsQueries clsquery = null;
            	clsquery = queries.get(contador);
            	String query = clsquery.getStrQuery();
            	try{
            		
            	if(!tabla.equals(clsquery.getStrTabla())){
                    	if(clsquery.getStrTabla().equals("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO")||clsquery.getStrTabla().equals("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO"))
                    		
                    	clsquery.mostrarMensajeExitoso(strCodigoVendedor);
                    	tipo=clsquery.getStrTipo();
                    	tabla=clsquery.getStrTabla();
                }	
                	
                stmt.execute(query);
                
                
                
                if(!tabla.equals(clsquery.getStrTabla())){
                	if(clsquery.getStrTabla().equals("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA")||clsquery.getStrTabla().equals("PROFFLINE_TB_MATERIAL_TOP_CLIENTE"))
                		
                	clsquery.mostrarMensajeExitoso(strCodigoVendedor);
                	tipo=clsquery.getStrTipo();
                	tabla=clsquery.getStrTabla();
                }
                }catch(Exception e){
                	Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
                	if(!tipo.equals(clsquery.getStrTipo())||!tabla.equals(clsquery.getStrTabla())){
                    	clsquery.mostarMensajeFallido(strCodigoVendedor);
                    	tipo=clsquery.getStrTipo();
                    	tabla=clsquery.getStrTabla();
                    }
                }
            }
            //int o[] = stmt.executeBatch();
            VentanaPrincipal.obtenerInstancia().setProgresoParcial(queries.size());
        } catch (Exception e) {
        	Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    throw e;
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw e;
                }
            }
            if (conn != null) {
                try {
                    connection.commit();
                    connection.setAutoCommit(oldAutoCommit);
                    connection.close();
                    conn.close();
                } catch (Exception e) {
                    throw e;
                }
                try {
                    stmt.close();
                } catch (Exception e) {
                    throw e;
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e) {
                        throw e;
                    }
                }
            }   
        }
    }
    public void ejecutarConsultaPorVendedor(List queries, String strCodigoVendedor) throws Exception {
    	boolean oldAutoCommit;
        oldAutoCommit = connection.getAutoCommit();
        try {
            VentanaPrincipal.obtenerInstancia().setMaximoProgresoParcial(queries.size());
            int contador = 0;
            for (Iterator i$ = queries.iterator(); i$.hasNext(); VentanaPrincipal.obtenerInstancia().setProgresoParcial(contador++)) {
                String query = (String) i$.next();
                stmt.execute(query);
            }

            VentanaPrincipal.obtenerInstancia().setProgresoParcial(queries.size());
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    throw e;
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw e;
                }
            }
               
        }
    }
    public void CerrarConexion() throws Exception{
    	if (conn != null) {
            try {
                connection.commit();
                //connection.setAutoCommit(oldAutoCommit);
                connection.close();
                conn.close();
            } catch (Exception e) {
                throw e;
            }
            try {
                stmt.close();
            } catch (Exception e) {
                throw e;
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }
}
