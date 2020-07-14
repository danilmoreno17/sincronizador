/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.promesa.sincronizador.pedidos.sql;

import java.util.List;

import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;

/**
 *
 * @author Administrador
 */
public interface SqlSede {
    
    public abstract List<ClsQueries> setEliminarSede(String s,List<ClsQueries> q);

    public abstract List<ClsQueries> setInsertarSede(List list, String s,List<ClsQueries> q);
    
}
