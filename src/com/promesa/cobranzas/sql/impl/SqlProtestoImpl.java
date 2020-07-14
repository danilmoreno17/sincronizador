/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.DatoCredito;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class SqlProtestoImpl {

    public List<ClsQueries> eliminarListaProtesto(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            //List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_PROTESTO;";
            queries.add(new ClsQueries("PROFFLINE_TB_PROTESTO",sql,"DELETE"));
            //listaSql = new ArrayList();
            //listaSql.add(sql);
            //resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return queries;
        /*} catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("PROTESTO ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
    }

    public List<ClsQueries> insertarListaProtesto(List lstProtesto, String strCodigoVendedor,List<ClsQueries> queries) {
        List listaProtesto = new ArrayList();
        String sql;
        for (Iterator i$ = lstProtesto.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_PROTESTO",sql,"INSERT"))) {
            DatoCredito protesto = (DatoCredito) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_PROTESTO (codigoCliente, codigoVendedor, numeroDocumento, fechaContable,fechaDocumento, registradoEl, moneda, importe, unOrgRefer) VALUES ('").append(Long.parseLong(protesto.getCodigoCliente())).append("','").append(protesto.getCodigoVendedor()).append("','").append(protesto.getNumeroDocumento()).append("','").append(protesto.getFechaContable()).append("','").append(protesto.getFechaDocumento()).append("','").append(protesto.getRegistradoEl()).append("','").append(protesto.getMoneda()).append("','").append(protesto.getImporte()).append("','").append(protesto.getUnOrgRefer()).append("');").toString();
        }
        return queries;
        /*try {
            resultExecute.ejecutarConsultaPorVendedor(listaProtesto, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("PROTESTO ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("PROTESTO ").append(strCodigoVendedor).toString()))).toString());
        }*/
    }

}
