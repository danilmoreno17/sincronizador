/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.cobranzas.sql.impl;

import com.proffline.sincronizador.bean.BancoCliente;
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
public class SqlBancoClienteImpl {

    public List<ClsQueries> eliminarBancoCliente(String strCodigoVendedor,List<ClsQueries> queries) {
        //try {
            String sql = "DELETE FROM PROFFLINE_TB_BANCO_CLIENTE;";
            queries.add(new ClsQueries("PROFFLINE_TB_BANCO_CLIENTE",sql,"DELETE"));
            
        /*} catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("BANCO CLIENTE ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }*/
            return queries;
    }

    public List<ClsQueries> insertarListaBancoCliente(List lstBancoCliente, String strCodigoVendedor,List<ClsQueries> queries){
        List listaBancoCliente = new ArrayList();
        String sql;
        for (Iterator i$ = lstBancoCliente.iterator(); i$.hasNext(); listaBancoCliente.add(sql)) {
            BancoCliente bancoCliente = (BancoCliente) i$.next();
            sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_BANCO_CLIENTE (codigoCliente, nroCtaBcoCliente, idBancoCliente, descripcionBancoCliente) VALUES ('").append(bancoCliente.getCodigoCliente()).append("','").append(bancoCliente.getNroCtaBcoCliente()).append("','").append(bancoCliente.getIdBancoCliente()).append("','").append(bancoCliente.getDescripcionBancoCliente()).append("');").toString();
            queries.add(new ClsQueries("PROFFLINE_TB_BANCO_CLIENTE",sql,"INSERT"));
        }
        return queries;
        //resultExecute.ejecutarConsultaPorVendedor(listaBancoCliente, strCodigoVendedor);
       
    }

}
