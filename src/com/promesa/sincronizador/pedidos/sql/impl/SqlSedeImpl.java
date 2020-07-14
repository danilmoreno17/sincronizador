/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.sincronizador.pedidos.sql.impl;

import com.proffline.sincronizador.bean.BeanSede;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
import com.promesa.sincronizador.pedidos.sql.SqlSede;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class SqlSedeImpl implements SqlSede {

    private static String TABLA_SEDE = "PROFFLINE_TB_SEDE";
    private static String IDCLIENTE = "txtIdCliente";
    private static String CODIGO = "txtCodigo";
    private static String DIRECCION = "txtDireccion";

    public List<ClsQueries> setInsertarSede(List lists, String strCodigoVendedor,List<ClsQueries> queries) {
        BeanSede s;
        for (Iterator i$ = lists.iterator(); i$.hasNext(); queries.add(new ClsQueries(TABLA_SEDE,(new StringBuilder()).append("INSERT INTO ").append(TABLA_SEDE).append(" (").append(IDCLIENTE).append(",").append(CODIGO).append(",").append(DIRECCION).append(")").append(" VALUES ('").append(s.getIdCliente()).append("','").append(s.getCodigo()).append("','").append(s.getDireccion()).append("')").toString(),"INSERT"))) {
            s = (BeanSede) i$.next();
        }
        return queries;
    }

    public List<ClsQueries> setEliminarSede(String strCodigoVendedor,List<ClsQueries> queries) {
        String sqlSede = (new StringBuilder()).append(" DELETE FROM ").append(TABLA_SEDE).toString();
        queries.add(new ClsQueries(TABLA_SEDE,sqlSede,"DELETE"));
        return queries;
    }

}
