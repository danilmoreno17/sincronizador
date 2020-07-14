/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.cobranza.bean.PartidaIndividualAbierta;
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
public class SqlPartidaIndividualAbiertaImpl {

    private ResultExecute resultExecute;

    public SqlPartidaIndividualAbiertaImpl() {
        resultExecute = null;
    }

    public List<ClsQueries> eliminarListaPartidaIndividualAbierta(String strCodigoVendedor,List<ClsQueries> queries) {
        String sqlpartida = "DELETE FROM PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA;";
        queries.add(new ClsQueries("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA",sqlpartida,"DELETE"));
        return queries;
       }

    public List<ClsQueries> insertarListaPartidaIndividualAbierta(List lstPartidaIndividualAbierta, String strCodigoVendedor,List<ClsQueries> queries) {
        String strsql;
        for (Iterator i$ = lstPartidaIndividualAbierta.iterator(); i$.hasNext(); queries.add(new ClsQueries("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA",strsql,"INSERT"))) {
            PartidaIndividualAbierta partidaIndividualAbierta = (PartidaIndividualAbierta) i$.next();
            strsql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA (codigoCliente, docNo, pstngDate, docDate, entryDate, expirationDate,currency, amtDoccur, refOrgUn, refDocNo, docType, itemNum, postKey, psprt, pszah, psskt, invRef, invItem, isLeaf,isExpanded, isReadOnly, indice, displayColor, fiscYear, fisPeriod, sgtxt, isReadOnly2, dbCrInd, verzn) VALUES ('").append(partidaIndividualAbierta.getCodigoCliente()).append("','").append(partidaIndividualAbierta.getDocNo()).append("','").append(partidaIndividualAbierta.getPstngDate()).append("','").append(partidaIndividualAbierta.getDocDate()).append("','").append(partidaIndividualAbierta.getEntryDate()).append("','").append(partidaIndividualAbierta.getExpirationDate()).append("','").append(partidaIndividualAbierta.getCurrency()).append("','").append(partidaIndividualAbierta.getAmtDoccur()).append("','").append(partidaIndividualAbierta.getRefOrgUn()).append("','").append(partidaIndividualAbierta.getRefDocNo()).append("','").append(partidaIndividualAbierta.getDocType()).append("','").append(partidaIndividualAbierta.getItemNum()).append("','").append(partidaIndividualAbierta.getPostKey()).append("','").append(partidaIndividualAbierta.getPsprt()).append("','").append(partidaIndividualAbierta.getPszah()).append("','").append(partidaIndividualAbierta.getPsskt()).append("','").append(partidaIndividualAbierta.getInvRef()).append("','").append(partidaIndividualAbierta.getInvItem()).append("','").append(partidaIndividualAbierta.getIsLeaf()).append("','").append(partidaIndividualAbierta.getIsExpanded()).append("','").append(partidaIndividualAbierta.getIsReadOnly()).append("','").append(partidaIndividualAbierta.getIndice()).append("','").append(partidaIndividualAbierta.getDisplayColor()).append("','").append(partidaIndividualAbierta.getFiscYear()).append("','").append(partidaIndividualAbierta.getFisPeriod()).append("','").append(partidaIndividualAbierta.getSgtxt()).append("','").append(partidaIndividualAbierta.getIsReadOnly2()).append("','").append(partidaIndividualAbierta.getDbCrInd()).append("','").append(partidaIndividualAbierta.getVerzn()).append("');").toString();
        }
        return queries;
    }

}
