/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.planificacion.sql;

import com.proffline.sincronizador.bean.BeanSincronizacion;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface SqlSincronizacion {
    
    public List<BeanSincronizacion> getSincronizacion();

    public int filasTabla(String tabla);

}
