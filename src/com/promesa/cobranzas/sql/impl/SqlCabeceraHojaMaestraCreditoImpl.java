/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.CabeceraHojaMaestraCredito;
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
public class SqlCabeceraHojaMaestraCreditoImpl {

    public List<ClsQueries> eliminarCabeceraHojaMaestraCredito(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            //List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO;";
            //listaSql = new ArrayList();
            queries.add(new ClsQueries("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO",sql,"DELETE"));
            //listaSql.add(sql);
            return queries;
            /*resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return true;
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
    }

    public List<ClsQueries> insertarListaCabeceraHojaMaestraCredito(List lstCabeceraHojaMaestraCredito, String strCodigoVendedor,List<ClsQueries> queries){
        List listaCabeceraHojaMaestraCredito = new ArrayList();
        String sql;
        for (Iterator i$ = lstCabeceraHojaMaestraCredito.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO",sql,"INSERT"))) {
            CabeceraHojaMaestraCredito cabeceraHojaMaestraCredito = (CabeceraHojaMaestraCredito) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO (codigoCliente, codigoVendedor, nombreCompletoCliente,limiteCredito, claseRiesgo, cupoDisponible, valorVencido, fuds, notaCredito, protestante, bloqueoCredito) VALUES ('").append(cabeceraHojaMaestraCredito.getCodigoCliente()).append("','").append(cabeceraHojaMaestraCredito.getCodigoVendedor()).append("','").append(cabeceraHojaMaestraCredito.getNombreCompletoCliente()).append("','").append(cabeceraHojaMaestraCredito.getLimiteCredito()).append("','").append(cabeceraHojaMaestraCredito.getClaseRiesgo()).append("','").append(cabeceraHojaMaestraCredito.getCupoDisponible()).append("','").append(cabeceraHojaMaestraCredito.getValorVencido()).append("','").append(cabeceraHojaMaestraCredito.getFuds()).append("','").append(cabeceraHojaMaestraCredito.getNotaCredito()).append("','").append(cabeceraHojaMaestraCredito.getProtestante()).append("','").append(cabeceraHojaMaestraCredito.getBloqueoCredito()).append("');").toString();
        }
        return queries;

        /*try {
            resultExecute.ejecutarConsultaPorVendedor(listaCabeceraHojaMaestraCredito, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(strCodigoVendedor).toString()))).toString());
        }*/
    }

}
