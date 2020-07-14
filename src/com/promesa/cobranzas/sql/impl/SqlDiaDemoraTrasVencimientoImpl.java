/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.DiaDemoraTrasVencimiento;
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
public class SqlDiaDemoraTrasVencimientoImpl {

    public List<ClsQueries> eliminarListaDiaDemoraTrasVencimiento(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            //List listaSql;
            String sql = "DELETE FROM PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO;";
            queries.add(new ClsQueries("PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO",sql,"DELETE"));
            //listaSql = new ArrayList();
            //listaSql.add(sql);
            //resultExecute.ejecutarConsultaPorVendedor(listaSql, strCodigoVendedor);
            return queries;
        /*} catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("DIA DEMORA TRAS VENCIMIENTO ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
    }

    public List<ClsQueries> insertarListaDiaDemoraTrasVencimiento(List lstDiaDemoraTrasVencimiento, String strCodigoVendedor,List<ClsQueries> queries) {
        List listaDiaDemoraTrasVencimiento = new ArrayList();
        String sql;
        for (Iterator i$ = lstDiaDemoraTrasVencimiento.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO",sql,"INSERT"))) {
            DiaDemoraTrasVencimiento diaDemoraTrasVencimiento = (DiaDemoraTrasVencimiento) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO (codigoCliente, codigoVendedor, sociedad, moneda,cuadro, partidasVencidas, noVencidas) VALUES ('").append(diaDemoraTrasVencimiento.getCodigoCliente()).append("','").append(diaDemoraTrasVencimiento.getCodigoVendedor()).append("','").append(diaDemoraTrasVencimiento.getSociedad()).append("','").append(diaDemoraTrasVencimiento.getMoneda()).append("','").append(diaDemoraTrasVencimiento.getCuadro()).append("','").append(diaDemoraTrasVencimiento.getPartidasVencidas()).append("','").append(diaDemoraTrasVencimiento.getNoVencidas()).append("');").toString();
        }
        return queries;
        /*try {
            resultExecute.ejecutarConsultaPorVendedor(listaDiaDemoraTrasVencimiento, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("DIA DEMORA TRAS VENCIMIENTO ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("DIA DEMORA TRAS VENCIMIENTO ").append(strCodigoVendedor).toString()))).toString());
        }*/
    }
}
