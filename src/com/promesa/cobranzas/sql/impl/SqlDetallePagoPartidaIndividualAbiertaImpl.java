/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.DetallePagoPartidaIndividualAbierta;
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
public class SqlDetallePagoPartidaIndividualAbiertaImpl {

    private ResultExecute resultExecute;

    public SqlDetallePagoPartidaIndividualAbiertaImpl() {
        /*  21*/ resultExecute = null;
    }

    public List<ClsQueries> eliminarListaDetallePagoPartidaIndividualAbierta(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            String sqlDelete = "DELETE FROM PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA;";
            queries.add(new ClsQueries("PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA",sqlDelete,"DELETE"));
            /*resultExecute.ejecutarConsultaPorVendedor(sql, strCodigoVendedor);
            return true;
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("DETALLE PAGO PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
        return queries;
    }

    public List<ClsQueries> insertarListaDetallePagoPartidaIndividualAbierta(List lstDetallePagoPartidaIndividualAbierta, String strCodigoVendedor,List<ClsQueries> queries) {
        //List listaDetallePagoPartidaIndividualAbierta = new ArrayList();
        String sqli;
        for (Iterator i$ = lstDetallePagoPartidaIndividualAbierta.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA",sqli,"INSERT"))) {
            DetallePagoPartidaIndividualAbierta detallePagoPartidaIndividualAbierta = (DetallePagoPartidaIndividualAbierta) i$.next();
            sqli = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA (idPartidaIndividualAbierta, docNo, pstngDate, docDate,entryDate, expirationDate, currency, amtDoccur, refOrgUn, refDocNo, docType, itemNum, postKey, psprt, pszah, psskt, invRef, invItem,isLeaf, isExpanded, isReadOnly, indice, displayColor, fiscYear, fisPeriod, sgtxt, isReadOnly2, dbCrInd, verzn) VALUES (").append(detallePagoPartidaIndividualAbierta.getIdPartidaIndividualAbierta()).append(",'").append(detallePagoPartidaIndividualAbierta.getDocNo()).append("','").append(detallePagoPartidaIndividualAbierta.getPstngDate()).append("','").append(detallePagoPartidaIndividualAbierta.getDocDate()).append("','").append(detallePagoPartidaIndividualAbierta.getEntryDate()).append("','").append(detallePagoPartidaIndividualAbierta.getExpirationDate()).append("','").append(detallePagoPartidaIndividualAbierta.getCurrency()).append("','").append(detallePagoPartidaIndividualAbierta.getAmtDoccur()).append("','").append(detallePagoPartidaIndividualAbierta.getRefOrgUn()).append("','").append(detallePagoPartidaIndividualAbierta.getRefDocNo()).append("','").append(detallePagoPartidaIndividualAbierta.getDocType()).append("','").append(detallePagoPartidaIndividualAbierta.getItemNum()).append("','").append(detallePagoPartidaIndividualAbierta.getPostKey()).append("','").append(detallePagoPartidaIndividualAbierta.getPsprt()).append("','").append(detallePagoPartidaIndividualAbierta.getPszah()).append("','").append(detallePagoPartidaIndividualAbierta.getPsskt()).append("','").append(detallePagoPartidaIndividualAbierta.getInvRef()).append("','").append(detallePagoPartidaIndividualAbierta.getInvItem()).append("','").append(detallePagoPartidaIndividualAbierta.getIsLeaf()).append("','").append(detallePagoPartidaIndividualAbierta.getIsExpanded()).append("','").append(detallePagoPartidaIndividualAbierta.getIsReadOnly()).append("','").append(detallePagoPartidaIndividualAbierta.getIndice()).append("','").append(detallePagoPartidaIndividualAbierta.getDisplayColor()).append("','").append(detallePagoPartidaIndividualAbierta.getFiscYear()).append("','").append(detallePagoPartidaIndividualAbierta.getFisPeriod()).append("','").append(detallePagoPartidaIndividualAbierta.getSgtxt()).append("','").append(detallePagoPartidaIndividualAbierta.getIsReadOnly2()).append("','").append(detallePagoPartidaIndividualAbierta.getDbCrInd()).append("','").append(detallePagoPartidaIndividualAbierta.getVerzn()).append("');").toString();
        }

        //try {
         //   resultExecute.ejecutarConsultaPorVendedor(listaDetallePagoPartidaIndividualAbierta, strCodigoVendedor);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("DETALLE PAGO PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString()))).toString());
        /*} catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("DETALLE PAGO PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString()))).toString());
        }*/
        return queries;
        
    }

}
