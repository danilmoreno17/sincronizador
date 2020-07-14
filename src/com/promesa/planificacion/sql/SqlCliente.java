/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.promesa.planificacion.sql;

import com.proffline.sincronizador.bean.Cliente;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface SqlCliente {
    
    public abstract List listarClientes(String s);

    public abstract List<ClsQueries> eliminarCliente(String s,List<ClsQueries> queries);

    public abstract List<ClsQueries> insertarCliente2(String s, List<Cliente> list, List<ClsQueries> queries);
    
}
