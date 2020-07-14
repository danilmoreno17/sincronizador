/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.PartidaIndividual;
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
public class SqlPartidaIndividualImpl {

    public List<ClsQueries> eliminarListaPartidaIndividual(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            //List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_PARTIDA_INDIVIDUAL;";
            queries.add(new ClsQueries("PROFFLINE_TB_PARTIDA_INDIVIDUAL",sql,"DELETE"));
            //listaSql = new ArrayList();
            //listaSql.add(sql);
            //resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return queries;
        /*} catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
    }

    public List<ClsQueries> insertarListaPartidaIndividual(List lstPartidaIndividual, String strCodigoVendedor,List<ClsQueries> queries) {
        //List listaPartidaIndividual = new ArrayList();
        String sql;
        for (Iterator i$ = lstPartidaIndividual.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_PARTIDA_INDIVIDUAL",sql,"INSERT"))) {
            PartidaIndividual partidaIndividual = (PartidaIndividual) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_PARTIDA_INDIVIDUAL (codigoCliente, codigoVendedor, numeroDocumento, claseDocumento,posicion, fechaDocumento, fechaVencimiento, registradoEl, moneda, impteDePos, referencia) VALUES ('").append(Long.parseLong(partidaIndividual.getCodigoCliente())).append("','").append(partidaIndividual.getCodigoVendedor()).append("','").append(partidaIndividual.getNumeroDocumento()).append("','").append(partidaIndividual.getClaseDocumento()).append("','").append(partidaIndividual.getPosicion()).append("','").append(partidaIndividual.getFechaDocumento()).append("','").append(partidaIndividual.getFechaVencimiento()).append("','").append(partidaIndividual.getRegistradoEl()).append("','").append(partidaIndividual.getMoneda()).append("','").append(partidaIndividual.getImpteDePos()).append("','").append(partidaIndividual.getReferencia()).append("');").toString();
        }
        return queries;
        /*try {
            resultExecute.ejecutarConsultaPorVendedor(listaPartidaIndividual, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(strCodigoVendedor).toString()))).toString());
        }*/
    }

}
