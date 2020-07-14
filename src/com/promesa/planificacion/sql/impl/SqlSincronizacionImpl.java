/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.planificacion.sql.impl;

import com.proffline.sincronizador.bean.BeanSincronizacion;
import com.proffline.sincronizador.bean.BeanSincronizar;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.sqlite.ResultExecuteQuery;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
import com.promesa.planificacion.sql.SqlSincronizacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class SqlSincronizacionImpl implements SqlSincronizacion {

    private static String NUM_ID_SINC = "numIdeSinc";
    private static String INFO_SINC = "txtInfSinc";
    private static String CANT_REG = "numCantReg";
    private static String FECH_HOR = "txtFecHor";
    private static String TIEMPO = "numTie";
    private static String HORA_INI = "txtHorIni";
    private static String FRECUENCIA = "numFec";
    private static String TABLA_SINCRONIZACION = "PROFFLINE_TB_SINCRONIZACION";

    private String codigoVendedor;

    public SqlSincronizacionImpl(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    @Override
    public List<BeanSincronizacion> getSincronizacion() {
        HashMap column;
        column = new HashMap();
        List<BeanSincronizacion> since = new ArrayList<BeanSincronizacion>();
        column.put("String:0", NUM_ID_SINC);
        column.put("String:1", INFO_SINC);
        column.put("String:2", CANT_REG);
        column.put("String:3", FECH_HOR);
        column.put("String:4", TIEMPO);
        String sqlSinc = "SELECT * FROM PROFFLINE_TB_SINCRONIZACION";
        HashMap mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlSinc, column, codigoVendedor);
        HashMap res = null;
        for (int i = 0; i < mapResultado.size(); i++) {
            BeanSincronizacion objS = new BeanSincronizacion();
            res = (HashMap) mapResultado.get(i);
            objS.setStrIdeSinc(res.get(NUM_ID_SINC).toString());
            objS.setStrInfSinc(res.get(INFO_SINC).toString());
            objS.setStrNumCantReg((res.get(CANT_REG) == null ? "0" : res.get(CANT_REG)).toString());
            objS.setStrFecHor(res.get(FECH_HOR).toString());
            objS.setStrNumTie(res.get(TIEMPO).toString());
            since.add(objS);
        }
        return since;
    }

    @Override
    public int filasTabla(String tabla) {
        HashMap column = new HashMap();
        int filas = 0;
        column.put("String:0", "filas");
        String sqlSinc = "SELECT COUNT(*) as filas FROM " + tabla;
        HashMap mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlSinc, column, codigoVendedor);
        HashMap res = (HashMap) mapResultado.get(0);
        filas = Integer.parseInt(res.get("filas").toString());
        return filas;
    }

    public List<ClsQueries> actualizarSincronizar(BeanSincronizar sincronizar,List<ClsQueries> queries) {
        try {
            List<String> lista = new ArrayList<String>();
            String sqlSincronizar = "UPDATE PROFFLINE_TB_SINCRONIZACION SET " + " numCantReg=" + sincronizar.getStrCantReg()
                    + " ,txtFecHor='" + sincronizar.getStrFecHor() + "' " + " ,numTie='" + sincronizar.getStrTie() + "'"
                    + " WHERE numIdeSinc=" + sincronizar.getStrIdeSinc() + ";";
           
            queries.add(new ClsQueries("PROFFLINE_TB_SINCRONIZACION",sqlSincronizar,"UPDATE"));
            //resultExecute.ejecutarConsultaPorVendedor(lista, codigoVendedor);
        } catch (Exception ex) {
        }
        return queries;
    }

}
