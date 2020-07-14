/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.ValorPorVencer;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class SqlValorPorVencerImpl {

    public List<ClsQueries> eliminarListaValorPorVencer(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            //List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_VALOR_POR_VENCER;";
            //listaSql = new ArrayList();
            //listaSql.add(sql);
            queries.add(new ClsQueries("PROFFLINE_TB_VALOR_POR_VENCER",sql,"DELETE"));
            //resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return queries;
        /*} catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("VALOR POR VENCER ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
    }

    public List<ClsQueries> insertarListaValorPorVencer(List lstValorPorVencer, String strCodigoVendedor, List<ClsQueries> queries) {
        //List listaValorPorVencer = new ArrayList();
        String sql;
        for (Iterator i$ = lstValorPorVencer.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_VALOR_POR_VENCER",sql,"INSERT"))) {
            ValorPorVencer valorPorVencer = (ValorPorVencer) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_VALOR_POR_VENCER (codigoCliente, codigoVendedor, mesAnio, cantidad) VALUES ('").append(Long.parseLong(valorPorVencer.getCodigoCliente())).append("','").append(valorPorVencer.getCodigoVendedor()).append("','").append(valorPorVencer.getMesAnio()).append("','").append(valorPorVencer.getCantidad()).append("');").toString();
        }
        return queries;
        /*try {
            resultExecute.ejecutarConsultaPorVendedor(listaValorPorVencer, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("VALOR POR VENCER ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("VALOR POR VENCER ").append(strCodigoVendedor).toString()))).toString());
        }*/
    }

}
