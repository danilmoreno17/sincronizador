/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.promesa.sincronizador.pedidos.sql;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface SqlDivision {
    
    public abstract void setListaJerarquia();

    public abstract List getListaJerarquia();

    public abstract List[] jerarquiaMateriales();

    public abstract List getListaPrecios(String s, String s1);
    
}
