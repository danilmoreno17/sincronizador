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
public class SqlNotaCreditoImpl {

    public List<ClsQueries> eliminarListaNotaCredito(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            //List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_NOTA_CREDITO;";
            queries.add(new ClsQueries("PROFFLINE_TB_NOTA_CREDITO",sql,"DELETE"));
            //listaSql = new ArrayList();
            //listaSql.add(sql);
            //resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return queries;
        /*} catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("NOTA CREDITO ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
    }

    public List<ClsQueries> insertarListaNotaCredito(List lstNotaCredito, String strCodigoVendedor,List<ClsQueries> queries) {
        //List listaNotaCredito = new ArrayList();
        String sql;
        for (Iterator i$ = lstNotaCredito.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_NOTA_CREDITO",sql,"INSERT"))) {
        DatoCredito notaCredito = (DatoCredito) i$.next();
        sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_NOTA_CREDITO (codigoCliente, codigoVendedor, numeroDocumento, fechaContable,fechaDocumento, registradoEl, moneda, importe, unOrgRefer) VALUES ('").append(Long.parseLong(notaCredito.getCodigoCliente())).append("','").append(notaCredito.getCodigoVendedor()).append("','").append(notaCredito.getNumeroDocumento()).append("','").append(notaCredito.getFechaContable()).append("','").append(notaCredito.getFechaDocumento()).append("','").append(notaCredito.getRegistradoEl()).append("','").append(notaCredito.getMoneda()).append("','").append(notaCredito.getImporte()).append("','").append(notaCredito.getUnOrgRefer()).append("');").toString();
        }
        return queries;
        /*try {
        resultExecute.ejecutarConsultaPorVendedor(listaNotaCredito, strCodigoVendedor);
        VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("NOTA CREDITO ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
          ex.printStackTrace();
          Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
          VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("NOTA CREDITO ").append(strCodigoVendedor).toString()))).toString());
        }*/
    }

}
