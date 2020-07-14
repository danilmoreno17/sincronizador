/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.sincronizador.pedidos.sql;

import com.proffline.sincronizador.bean.BeanVentaCruzada;
import com.proffline.sincronizador.bean.Material;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface SqlMaterial {

    public abstract List obtenerMaterialesPorClaseMaterial(String s, String s1);

    public abstract List buscarMaterialPorMarca(String s, String s1);

    public abstract List obtenerTodosMateriales(List list, String s);

    public abstract List<ClsQueries> migrarMaterialesTopCliente2(List list, List<Material> list1, String s,List<ClsQueries> queries);

    public abstract List<ClsQueries> eliminarMaterialTopCliente(String s,List<ClsQueries> queries);

    public abstract List<ClsQueries> eliminarMaterialTopTipologia(String s,List<ClsQueries> queries);

    public abstract List<ClsQueries> migrarMaterialesTopTipologia2(List list, List<Material> list1, String s,List<ClsQueries> queries);

    public abstract List<ClsQueries>  migrarMaterialesVentaCruzada(List<List<Material>> listm, List<Material> listaMaterial, String s,List<ClsQueries> queries);
    
    public abstract List obtenerTodosMateriales2();

    public abstract void migrarMaterialConsultaDinamica1(List list, String s);

    public abstract void migrarMaterialConsultaDinamica2(List list, String s);

    public abstract void migrarMaterialConsultaDinamica3(List list, String s);

    public abstract void eliminarMaterialConsultaDinamica1(String s);

    public abstract void eliminarMaterialConsultaDinamica2(String s);

    public abstract void eliminarMaterialConsultaDinamica3(String s);

    public int getCountRowPromoOferta(String codigoVendedor);

    public int getCountRowDsctoPolitica(String codigoVendedor);

    public int getCountRowDsctoManual(String codigoVendedor);

    public int getCountRowTopCliente(String codigoVendedor);

    public int getCountRowTopTipologia(String codigoVendedor);

	public abstract List<ClsQueries> setInsertarActualizarVentaCruzada(List<BeanVentaCruzada> listm, List<ClsQueries> queries);

}
