/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.planificacion.sql.impl;

import com.proffline.sincronizador.bean.Cliente;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.sqlite.ResultExecuteQuery;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
import com.promesa.planificacion.sql.SqlCliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class SqlClienteImpl implements SqlCliente {

    private HashMap column;
    private HashMap mapResultado;
    private static String TABLA_EMPLEADO_CLIENTE = "PROFFLINE_TB_EMPLEADO_CLIENTE";
    private static String ID_EMPLEADO = "txtIdEmpleado";
    private static String TABLA_CLIENTE = "PROFFLINE_TB_CLIENTE";
    private static String ID_CLIENTE = "txtIdCliente";
    private static String COMPANIA_CLIENTE = "txtCompaniaCliente";
    private static String NOMBRE_CLIENTE = "txtNombreCliente";
    private static String APELLIDO_CLIENTE = "txtApellidosCliente";
    private static String EMAIL_CLIENTE = "txtEmailCliente";
    private static String TELEFONO_CLIENTE = "txtTelefonoPrivadoCliente";
    private static String TELEFONO_TRABAJO = "txtTelefonoTrabajoCliente";
    private static String TELEFONO_MOVIL = "txtTelefonoMovilCliente";
    private static String FAX_CLIENTE = "txtNumeroFaxCliente";
    private static String DIRECCION_CLIENTE = "txtDireccionCliente";
    private static String CIUDAD_CLIENTE = "txtCiudadCliente";
    private static String ESTADO_PROVINCIA_CLIENTE = "txtEstadoProvinciaCliente";
    private static String CODIGO_POSTAL_CLIENTE = "txtCodigoPostalCliente";
    private static String MARCA_BLOQUEO_ALMACEN = "txtMarcaBloqueoAlmacen";
    private static String CODIGO_TIPOLOGIA = "txtCodigoTipologia";
    private static String DESCRIPCION_TIPOLOGIA = "txtDescripcionTipologia";
    private static String STR_COD_TIPOLOGIA = "txtStrCodTipologia";
    private static String STR_DESC_TIPOLOGIA = "txtStrDescTipologia";
    private static String TXTCODORGVENTAS = "txtCodOrgVentas";
    private static String TXTCODCANALDIST = "txtCodCanalDist";
    private static String TXTCODSECTOR = "txtCodSector";
    private static String INDICADOR_IMPUESTO = "txtIndicadorImpuesto";
    private static String OFICINA_VENTAS = "txtOficinaVentas";
    private static String CLASE = "txtClase";
    private static String GRUPO_VENTAS = "txtGrupoVentas";

    public SqlClienteImpl() {
        column = new HashMap();
        mapResultado = new HashMap();
    }

    public List listarClientes(String strCodigoVendedor) {
        List listaClientes = new ArrayList();
        column = new HashMap();
        column.put("String:0", ID_CLIENTE);
        column.put("String:1", NOMBRE_CLIENTE);
        String sqlCliente = (new StringBuilder()).append("SELECT ").append(ID_CLIENTE).append(",").append(NOMBRE_CLIENTE).append(" FROM ").append(TABLA_CLIENTE).toString();
        mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlCliente, column, strCodigoVendedor);
        Cliente cliente;
        for (Iterator i$ = mapResultado.values().iterator(); i$.hasNext(); listaClientes.add(cliente)) {
            HashMap res = (HashMap) i$.next();
            cliente = new Cliente();
            cliente.setStrIdCliente(res.get(ID_CLIENTE).toString());
            cliente.setStrNombreCliente(res.get(NOMBRE_CLIENTE).toString());
        }

        /*  38*/ return listaClientes;
    }



    

	@Override
	public List<ClsQueries> insertarCliente2(String s, List<Cliente> list, List<ClsQueries> queries) {
        String sqlEmpCli = "";
        for (Cliente c : list) {
            String sqlCliente = "INSERT INTO "+TABLA_CLIENTE                + " (" 
                    +ID_CLIENTE             +", "+ COMPANIA_CLIENTE         +", "
                    +NOMBRE_CLIENTE         +", "+APELLIDO_CLIENTE          +", "
                    +EMAIL_CLIENTE          +", "+TELEFONO_CLIENTE          +", "
                    +TELEFONO_TRABAJO       +", "+TELEFONO_MOVIL            +", "
                    +FAX_CLIENTE            +", "+DIRECCION_CLIENTE         +", "
                    +CIUDAD_CLIENTE         +", "+ESTADO_PROVINCIA_CLIENTE  +", "
                    +CODIGO_POSTAL_CLIENTE  +", "+MARCA_BLOQUEO_ALMACEN     +", "
                    +CODIGO_TIPOLOGIA       +", "+STR_COD_TIPOLOGIA         +", "
                    +DESCRIPCION_TIPOLOGIA  +", "+TXTCODORGVENTAS           +", "
                    +TXTCODCANALDIST        +", "+TXTCODSECTOR              +", "
                    +INDICADOR_IMPUESTO     +", "+OFICINA_VENTAS            +", "
                    +GRUPO_VENTAS           +", "+ CLASE +", txtDescripcionCanal) VALUES ('"
                    +c.getStrIdCliente().replace("'", "''")                 +"', '"+c.getStrCompaniaCliente().replaceAll("'", "''")         +"', '"
                    +c.getStrNombreCliente().replaceAll("'", "''")          +"', '"+c.getStrApellidosCliente().replaceAll("'", "''")        +"', '"
                    +c.getStrEmailCliente().replaceAll("'", "''")           +"', '"+c.getStrTelefonoPrivadoCliente().replaceAll("'", "''")  +"', '"
                    +c.getStrTelefonoTrabajoCliente().replaceAll("'", "''") +"', '"+c.getStrTelefonoMovilCliente().replaceAll("'", "''")    +"', '"
                    +c.getStrNumeroFaxCliente().replaceAll("'", "''")       +"', '"+c.getStrDireccionCliente().replaceAll("'", "''")        +"', '"
                    +c.getStrCiudadCliente().replaceAll("'", "''")          +"', '"+c.getStrEstadoProvinciaCliente().replaceAll("'", "''")  +"', '"
                    +c.getStrCodigoPostalCliente().replaceAll("'", "''")    +"', '"+c.getStrMarcaBloqueoAlmacen().replaceAll("'", "''")     +"', '"
                    +c.getStrCodigoTipologia().replaceAll("'", "''")        +"', '"+c.getStrCodigoTipologia().replaceAll("'", "''")         +"', '"
                    +c.getStrDescripcionTipologia().replaceAll("'", "''")   +"', '"+c.getStrCodOrgVentas().replaceAll("'", "''")            +"', '"
                    +c.getStrCodCanalDist().replaceAll("'", "''")           +"', '"+c.getStrCodSector().replaceAll("'", "''")               +"', '"
                    +c.getIndicadorIva().replaceAll("'", "''")              +"', '"+c.getStrOficinaVentas().replaceAll("'", "''")           +"', '"
                    +c.getStrGrupoVentas().replaceAll("'", "''")            +"', '"+c.getStrClase().replaceAll("'", "''") 					+"', '"
            		+c.getStrCanal().replaceAll("'", "''") 					+"'); ";
            queries.add(new ClsQueries(TABLA_CLIENTE,sqlCliente,"INSERT"));
            sqlEmpCli = "INSERT INTO " + TABLA_EMPLEADO_CLIENTE+ " VALUES('"+s+"', '"+c.getStrIdCliente()+"');";
            queries.add(new ClsQueries(TABLA_CLIENTE,sqlEmpCli,"INSERT"));
        }
        	return queries;
    }

	@Override
	public List<ClsQueries> eliminarCliente(String s, List<ClsQueries> queries) {
		String sqlCliente = " DELETE FROM PROFFLINE_TB_CLIENTE";
        String sqlClienteVendedor = "DELETE FROM PROFFLINE_TB_EMPLEADO_CLIENTE";
        queries.add(new ClsQueries(TABLA_CLIENTE,sqlCliente,"DELETE"));
        queries.add(new ClsQueries(TABLA_EMPLEADO_CLIENTE,sqlClienteVendedor,"DELETE"));
        return queries;
        
	}

}
