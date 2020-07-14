/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.HistorialPago;
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
public class SqlHistorialPagoImpl {

    public List<ClsQueries> eliminarListaHistorialPago(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
          //  List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_HISTORIAL_PAGO;";
            //listaSql = new ArrayList();
            queries.add(new ClsQueries("PROFFLINE_TB_HISTORIAL_PAGO",sql,"DELETE"));
            //listaSql.add(sql);
            /*resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("HISTORIAL PAGO ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
            return queries;
    }

    public List<ClsQueries> insertarListaHistorialPago(List lstHistorialPago, String strCodigoVendedor,List<ClsQueries> queries) {
        //List listaHistorialPago = new ArrayList();
        String sql;
        for (Iterator i$ = lstHistorialPago.iterator(); i$.hasNext();queries.add(new ClsQueries("PROFFLINE_TB_HISTORIAL_PAGO",sql,"INSERT"))) {
            HistorialPago historialPago = (HistorialPago) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_HISTORIAL_PAGO (codigoCliente, codigoVendedor, moneda, ejercicio,periodo, conDPP, diasDemora1, sinDescuento, diasDemora2, ctd) VALUES ('").append(Long.parseLong(historialPago.getCodigoCliente())).append("','").append(historialPago.getCodigoVendedor()).append("','").append(historialPago.getMoneda()).append("','").append(historialPago.getEjercicio()).append("','").append(historialPago.getPeriodo()).append("','").append(historialPago.getConDPP()).append("','").append(historialPago.getDiasDemora1()).append("','").append(historialPago.getSinDescuento()).append("','").append(historialPago.getDiasDemora2()).append("','").append(historialPago.getCtd()).append("');").toString();
        }
        return queries;
        /*try {
            resultExecute.ejecutarConsultaPorVendedor(listaHistorialPago, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("HISTORIAL PAGO ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("HISTORIAL PAGO ").append(strCodigoVendedor).toString()))).toString());
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
        }*/
    }

}
