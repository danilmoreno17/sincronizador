/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.sincronizador.pedidos.sql.impl;

import com.proffline.sincronizador.bean.BeanVentaCruzada;
import com.proffline.sincronizador.bean.Material;
import com.proffline.sincronizador.conexion.ConexionJDBC;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.sqlite.ResultExecuteQuery;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;
import com.promesa.sincronizador.pedidos.sql.SqlMaterial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class SqlMaterialImpl implements SqlMaterial {

    private HashMap column;
    private HashMap mapResultado;

    public SqlMaterialImpl() {
        column = new HashMap();
        mapResultado = new HashMap();
    }

    public List obtenerMaterialesPorClaseMaterial(String strCodClaseMaterial, String strCodigoVendedor) {
        List listaMateriales = new ArrayList();
        column = new HashMap();
        column.put("String:0", "TXTCODIGOMATERIAL");
        String sqlMaterial = (new StringBuilder()).append(" select TXTCODIGOMATERIAL  from proffline_tb_clase_material  where txtDescripcionClase='").append(strCodClaseMaterial).append("' ").toString();
        try {
            mapResultado = mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, strCodigoVendedor);
            HashMap res;
            Material bean;
            int ii = 0;
            for (int i = 0; i < mapResultado.size(); i++) {
                res = (HashMap) mapResultado.get(Integer.valueOf(i));
                bean = new Material();
                ii = Integer.parseInt(res.get("TXTCODIGOMATERIAL").toString());
                bean.setStrMATNR((new StringBuilder()).append("").append(ii).toString());
                listaMateriales.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
        }
        return listaMateriales;
    }

    public List buscarMaterialPorMarca(String strCodigoMarca, String strCodigoVendedor) {
        List listaMaterial = new ArrayList();
        Material material;
        column = new HashMap();
        column.put("String:0", "MATNR");
        String sqlMaterial = (new StringBuilder()).append(" SELECT MATNR FROM PROFFLINE_TB_MATERIAL where NORMT='").append(strCodigoMarca).append("'").toString();
        try {
            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, strCodigoVendedor);
            HashMap res = null;
            for (int i = 0; i < mapResultado.size(); i++) {
                res = (HashMap) mapResultado.get(Integer.valueOf(i));
                material = new Material();
                material.setStrMATNR(res.get("MATNR").toString());
                listaMaterial.add(material);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
        }
        return listaMaterial;
    }

    public List obtenerTodosMateriales(List listaMateriales, String strCodigoVendedor) {
        try {
            List materiales;
            materiales = new ArrayList();
            ConexionJDBC conn = new ConexionJDBC();
            Connection connection = conn.obtenerConexionPorVendedor(strCodigoVendedor);
            Statement stmt = connection.createStatement();
            ResultSet res = null;
            for (Iterator i$ = listaMateriales.iterator(); i$.hasNext();) {
                Material material = (Material) i$.next();
                String sql = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL WHERE MATNR = '").append(material.getStrMATNR()).append("';").toString();
                res = stmt.executeQuery(sql);
                while (res.next()) {
                    Material m = new Material();
                    m.setStrMATNR(res.getString("MATNR").toString());
                    m.setStrStock(res.getString("STOCK").toString());
                    m.setStrSU(res.getString("S_U").toString());
                    m.setStrShortText(res.getString("SHORT_TEXT").toString().replaceAll("'", ""));
                    m.setStrTextLine(res.getString("TEXT_LINE").toString().replaceAll("'", ""));
                    m.setStrTargetQTY(res.getString("TARGET_QTY").toString());
                    m.setStrPrice1(res.getString("PRICE_1").toString());
                    m.setStrPrice2(res.getString("PRICE_2").toString());
                    m.setStrPrice3(res.getString("PRICE_3").toString());
                    m.setStrPrice4(res.getString("PRICE_4").toString());
                    m.setStrPRDHA(res.getString("PRDHA").toString());
                    m.setTipoMaterial(res.getString("HER").toString());
                    m.setStrNORMT(res.getString("NORMT").toString());
                    m.setStrZZORDCO(res.getString("ZZORDCO").toString());
                    m.setStrCellDesign(res.getString("CELL_DESIGN").toString());
                    m.setMTART(res.getString("MTART").toString());
                    m.setStrTypeMat(res.getString("TYPEMAT").toString());
                    m.setStrGrupoCompra(res.getString("GRUPO_COMPRA").toString());
                    m.setStrSt1(res.getString("ST_1").toString());
                    m.setStrVentasAcumulado(material.getStrVentasAcumulado());
                    m.setStrVentasPromedio(material.getStrVentasPromedio());
                    m.setStrCliente(material.getStrCliente());
                    materiales.add(m);
                }
            }

            if (res != null) {
                res.close();
            }
            stmt.close();
            connection.close();
            conn.close();
            return materiales;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
            return null;
        }
    }

    public List<ClsQueries> migrarMaterialesTopCliente2(List listt, List<Material> listaMateriales, String strCodigoVendedor,List<ClsQueries> queries) {
        String cadenaSQL;
        for (Material m : listaMateriales) {
//            Material m = (Material) i$.next();
            cadenaSQL = (new StringBuilder()).append(" INSERT INTO PROFFLINE_TB_MATERIAL_TOP_CLIENTE(MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, VENTAS_ACUMULADO, VENTAS_PROMEDIO, CLIENTE) VALUES ('").append(m.getStrMATNR()).append("', '").append(m.getStrStock()).append("','").append(m.getStrSU()).append("','").append(m.getStrShortText()).append("','").append(m.getStrTextLine()).append("','").append(m.getStrTargetQTY()).append("','").append(m.getStrPrice1()).append("','").append(m.getStrPrice2()).append("','").append(m.getStrPrice3()).append("','").append(m.getStrPrice4()).append("','").append(m.getStrPRDHA()).append("','").append(m.getTipoMaterial()).append("','").append(m.getStrNORMT()).append("','").append(m.getStrZZORDCO()).append("','").append(m.getStrCellDesign()).append("','").append(m.getMTART()).append("','").append(m.getStrTypeMat()).append("','").append(m.getStrGrupoCompra()).append("','").append(m.getStrSt1()).append("','").append(m.getStrVentasAcumulado()).append("','").append(m.getStrVentasPromedio()).append("','").append(m.getStrCliente()).append("');").toString();
            
            queries.add(new ClsQueries("PROFFLINE_TB_MATERIAL_TOP_CLIENTE",cadenaSQL,"INSERT"));
        }
         return queries;
    }

    public List<ClsQueries> eliminarMaterialTopCliente(String strCodigoVendedor,List<ClsQueries> queries) {
        String sqlMaterial = "DELETE FROM PROFFLINE_TB_MATERIAL_TOP_CLIENTE";
        queries.add(new ClsQueries("PROFFLINE_TB_MATERIAL_TOP_CLIENTE",sqlMaterial,"DELETE"));
        //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL TOP CLIENTE ").append(strCodigoVendedor).toString()))).toString());
        return queries;
    }

    public List<ClsQueries> migrarMaterialesTopTipologia2(List listt, List<Material> listaMateriales, String strCodigoVendedor,List<ClsQueries> queries) {
        int i = 0;
        String cadenaSQL;
        for (Material m : listaMateriales) {
//            Material m = (Material) i$.next();
            String tipologia = ((Material) ((List) listt.get(0)).get(i)).getStrCliente();
            cadenaSQL = (new StringBuilder()).append(" INSERT INTO PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA(MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, VENTAS_ACUMULADO, VENTAS_PROMEDIO, CLIENTE) VALUES ('").append(m.getStrMATNR()).append("', '").append(m.getStrStock()).append("','").append(m.getStrSU()).append("','").append(m.getStrShortText()).append("','").append(m.getStrTextLine()).append("','").append(m.getStrTargetQTY()).append("','").append(m.getStrPrice1()).append("','").append(m.getStrPrice2()).append("','").append(m.getStrPrice3()).append("','").append(m.getStrPrice4()).append("','").append(m.getStrPRDHA()).append("','").append(m.getTipoMaterial()).append("','").append(m.getStrNORMT()).append("','").append(m.getStrZZORDCO()).append("','").append(m.getStrCellDesign()).append("','").append(m.getMTART()).append("','").append(m.getStrTypeMat()).append("','").append(m.getStrGrupoCompra()).append("','").append(m.getStrSt1()).append("','").append(m.getStrVentasAcumulado()).append("','").append(m.getStrVentasPromedio()).append("','").append(tipologia).append("');").toString();
            i++;
            queries.add(new ClsQueries("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA",cadenaSQL,"INSERT"));
            
        }
        //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("MATERIAL TOP TIPOLOGIA ").append(strCodigoVendedor).toString()))).toString());
        return queries;
    }
    
	@Override
	public List<ClsQueries> setInsertarActualizarVentaCruzada(List<BeanVentaCruzada> listm, List<ClsQueries> queries) {
		List<String> listaSQL = new ArrayList<String>();
		for (BeanVentaCruzada m : listm) {
			String cadenaSQL = 	" INSERT INTO PROFFLINE_TB_VENTA_CRUZADA("
					+ "txtIdCliente, "
					+ "txtDescripcionCategoria, "
					+ "txtAnio, "
					+ "txtVentaReal, "
					+ "txtOportunidad, "
					+ "txtObjetivo, " 
					+"txtCumplimiento) VALUES ("
					+ "'" + m.getStrCodCliente() + "', "
					+ "'" + m.getStrCategoria() + "', "
					+ "'" + m.getStrAnio() + "', "
					+ "'" + m.getDblVentaReal() + "', "
					+ "'" + m.getDblOportunidad() + "', "
					+ "'" + m.getDblObjetivo() + "', "
					+ "'" + m.getDblCumplimiento() + "');";
			queries.add(new ClsQueries("PROFFLINE_TB_VENTA_CRUZADA",cadenaSQL,"INSERT"));
		}	
		return queries;
	}	
    
    public List<ClsQueries>  migrarMaterialesVentaCruzada(List<List<Material>> listm, List<Material> listaMaterial, String s,List<ClsQueries> queries){
        int i = 0;
        String cadenaSQL;
        for (Material m : listaMaterial) {
//            Material m = (Material) i$.next();
            cadenaSQL = (new StringBuilder()).append(" INSERT INTO PROFFLINE_TB_MATERIAL_VENTA_CRUZADA(MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, VENTAS_ACUMULADO, VENTAS_PROMEDIO, CLIENTE, VENTA_REAL) VALUES ('").append(m.getStrMATNR()).append("', '").append(m.getStrStock()).append("','").append(m.getStrSU()).append("','").append(m.getStrShortText()).append("','").append(m.getStrTextLine()).append("','").append(m.getStrTargetQTY()).append("','").append(m.getStrPrice1()).append("','").append(m.getStrPrice2()).append("','").append(m.getStrPrice3()).append("','").append(m.getStrPrice4()).append("','").append(m.getStrPRDHA()).append("','").append(m.getTipoMaterial()).append("','").append(m.getStrNORMT()).append("','").append(m.getStrZZORDCO()).append("','").append(m.getStrCellDesign()).append("','").append(m.getMTART()).append("','").append(m.getStrTypeMat()).append("','").append(m.getStrGrupoCompra()).append("','").append(m.getStrSt1()).append("','").append(m.getStrVentasAcumulado()).append("','").append(m.getStrVentasPromedio()).append("','").append(m.getStrCliente()).append("','").append(m.getStrVentaReal()).append("');").toString();
            i++;
            queries.add(new ClsQueries("PROFFLINE_TB_MATERIAL_VENTA_CRUZADA",cadenaSQL,"INSERT"));
            
        }
        //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("MATERIAL TOP TIPOLOGIA ").append(strCodigoVendedor).toString()))).toString());
        return queries;
    }

    public List<ClsQueries> eliminarMaterialTopTipologia(String strCodigoVendedor,List<ClsQueries> queries) {
        String sqlMaterial = "DELETE FROM PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA ";
        queries.add(new ClsQueries("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA",sqlMaterial,"DELETE"));
        return queries;
    }

    @Override
    public List obtenerTodosMateriales2() {
        List materiales = new ArrayList();
        column = new HashMap();
        column.put("String:0", "MATNR");
        column.put("String:1", "STOCK");
        column.put("String:2", "S_U");
        column.put("String:3", "SHORT_TEXT");
        column.put("String:4", "TEXT_LINE");
        column.put("String:5", "TARGET_QTY");
        column.put("String:6", "PRICE_1");
        column.put("String:7", "PRICE_2");
        column.put("String:8", "PRICE_3");
        column.put("String:9", "PRICE_4");
        column.put("String:10", "PRDHA");
        column.put("String:11", "HER");
        column.put("String:12", "NORMT");
        column.put("String:13", "ZZORDCO");
        column.put("String:14", "CELL_DESIGN");
        column.put("String:15", "MTART");
        column.put("String:16", "TYPEMAT");
        column.put("String:17", "GRUPO_COMPRA");
        column.put("String:18", "ST_1");
        String sql = "SELECT * FROM PROFFLINE_TB_MATERIAL";
        try {
            mapResultado.clear();
            mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sql, column, 1);
//            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sql, column, strCodigoVendedor);
            if (mapResultado.size() > 0) {
                for (int i = 0; i < mapResultado.size(); i++) {
                    HashMap res = (HashMap) mapResultado.get(Integer.valueOf(i));
                    Material material = new Material();
                    material.setStrMATNR(res.get("MATNR").toString());
                    material.setStrStock(res.get("STOCK").toString());
                    material.setStrSU(res.get("S_U").toString());
                    material.setStrShortText(res.get("SHORT_TEXT").toString());
                    material.setStrTextLine(res.get("TEXT_LINE").toString());
                    material.setStrTargetQTY(res.get("TARGET_QTY").toString());
                    material.setStrPrice1(res.get("PRICE_1").toString());
                    material.setStrPrice2(res.get("PRICE_2").toString());
                    material.setStrPrice3(res.get("PRICE_3").toString());
                    material.setStrPrice4(res.get("PRICE_4").toString());
                    material.setStrPRDHA(res.get("PRDHA").toString());
                    material.setTipoMaterial(res.get("HER").toString());
                    material.setStrNORMT(res.get("NORMT").toString());
                    material.setStrZZORDCO(res.get("ZZORDCO").toString());
                    material.setStrCellDesign(res.get("CELL_DESIGN").toString());
                    material.setMTART(res.get("MTART").toString());
                    material.setMTART(res.get("MTART").toString());
                    material.setMTART(res.get("MTART").toString());
                    material.setStrTypeMat(res.get("TYPEMAT").toString());
                    material.setStrGrupoCompra(res.get("GRUPO_COMPRA").toString());
                    material.setStrSt1(res.get("ST_1").toString());
                    materiales.add(material);
                }
            }
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append("ERROR DE CARGE DE MATERIAL").toString());
            return null;
        }
        return materiales;
    }

    public void eliminarMaterialConsultaDinamica1(String strCodigoVendedor) {
        String sqlMaterial = " DELETE FROM PROFFLINE_TB_MATERIAL_PROMO_OFERTA";
        ResultExecute resultExecute = new ResultExecute();
        List sql = new ArrayList();
        sql.add(sqlMaterial);
        try {
        	resultExecute.CrearConexionPorVendedor(strCodigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(sql, strCodigoVendedor);
            resultExecute.CerrarConexion();
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL PROMO OFERTA ").append(strCodigoVendedor).toString()))).toString());
        }
    }

    public void eliminarMaterialConsultaDinamica2(String strCodigoVendedor) {
        String sqlMaterial = " DELETE FROM PROFFLINE_TB_MATERIAL_DSCTO_POLITICA";
        ResultExecute resultExecute = new ResultExecute();
        List sql = new ArrayList();
        sql.add(sqlMaterial);
        try {
        	resultExecute.CrearConexionPorVendedor(strCodigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(sql, strCodigoVendedor);
            resultExecute.CerrarConexion();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeLimpiarDatosExitosaSQL((new StringBuilder()).append("MATERIAL DESC POLITICA ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL DESC POLITICA ").append(strCodigoVendedor).toString()))).toString());
        }
    }

    public void eliminarMaterialConsultaDinamica3(String strCodigoVendedor) {
        String sqlMaterial = " DELETE FROM PROFFLINE_TB_MATERIAL_DSCTO_MANUAL";
        ResultExecute resultExecute = new ResultExecute();
        List sql = new ArrayList();
        sql.add(sqlMaterial);
        try {
        	resultExecute.CrearConexionPorVendedor(strCodigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(sql, strCodigoVendedor);
            resultExecute.CerrarConexion();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeLimpiarDatosExitosaSQL((new StringBuilder()).append("MATERIAL DSCTO MANUAL ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL DSCTO MANUAL ").append(strCodigoVendedor).toString()))).toString());
        }
    }

    public void migrarMaterialConsultaDinamica1(List listm, String strCodigoVendedor) {
        List listaSQL = new ArrayList();
        String cadenaSQL = "";
        for (Iterator i$ = listm.iterator(); i$.hasNext(); listaSQL.add(cadenaSQL)) {
            Material m = (Material) i$.next();
            cadenaSQL = (new StringBuilder()).append(" INSERT INTO PROFFLINE_TB_MATERIAL_PROMO_OFERTA(MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, VENTAS_ACUMULADO, VENTAS_PROMEDIO, CLIENTE) VALUES ('").append(m.getStrMATNR()).append("', '").append(m.getStrStock()).append("','").append(m.getStrSU()).append("','").append(m.getStrShortText()).append("','").append(m.getStrTextLine()).append("','").append(m.getStrTargetQTY()).append("','").append(m.getStrPrice1()).append("','").append(m.getStrPrice2()).append("','").append(m.getStrPrice3()).append("','").append(m.getStrPrice4()).append("','").append(m.getStrPRDHA()).append("','").append(m.getTipoMaterial()).append("','").append(m.getStrNORMT()).append("','").append(m.getStrZZORDCO()).append("','").append(m.getStrCellDesign()).append("','").append(m.getMTART()).append("','").append(m.getStrTypeMat()).append("','").append(m.getStrGrupoCompra()).append("','").append(m.getStrSt1()).append("','").append("").append("','").append("").append("','").append("").append("');").toString();
        }

        ResultExecute resultExecute = new ResultExecute();
        try {
        	resultExecute.CrearConexionPorVendedor(strCodigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(listaSQL, strCodigoVendedor);
            resultExecute.CerrarConexion();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("MATERIAL PROMO OFERTA ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL PROMO OFERTA ").append(strCodigoVendedor).toString()))).toString());
        }
    }

    public void migrarMaterialConsultaDinamica2(List listm, String strCodigoVendedor) {
        List listaSQL = new ArrayList();
        String cadenaSQL = "";
        for (Iterator i$ = listm.iterator(); i$.hasNext(); listaSQL.add(cadenaSQL)) {
            Material m = (Material) i$.next();
            cadenaSQL = (new StringBuilder()).append(" INSERT INTO PROFFLINE_TB_MATERIAL_DSCTO_POLITICA(MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, VENTAS_ACUMULADO, VENTAS_PROMEDIO, CLIENTE) VALUES ('").append(m.getStrMATNR()).append("', '").append(m.getStrStock()).append("','").append(m.getStrSU()).append("','").append(m.getStrShortText()).append("','").append(m.getStrTextLine()).append("','").append(m.getStrTargetQTY()).append("','").append(m.getStrPrice1()).append("','").append(m.getStrPrice2()).append("','").append(m.getStrPrice3()).append("','").append(m.getStrPrice4()).append("','").append(m.getStrPRDHA()).append("','").append(m.getTipoMaterial()).append("','").append(m.getStrNORMT()).append("','").append(m.getStrZZORDCO()).append("','").append(m.getStrCellDesign()).append("','").append(m.getMTART()).append("','").append(m.getStrTypeMat()).append("','").append(m.getStrGrupoCompra()).append("','").append(m.getStrSt1()).append("','").append("").append("','").append("").append("','").append("").append("');").toString();
        }

        ResultExecute resultExecute = new ResultExecute();
        try {
        	resultExecute.CrearConexionPorVendedor(strCodigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(listaSQL, strCodigoVendedor);
            resultExecute.CerrarConexion();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("MATERIAL DESC POLITICA ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL DESC POLITICA ").append(strCodigoVendedor).toString()))).toString());
        }
    }

    public void migrarMaterialConsultaDinamica3(List listm, String strCodigoVendedor) {
        List listaSQL = new ArrayList();
        String cadenaSQL = "";
        for (Iterator i$ = listm.iterator(); i$.hasNext(); listaSQL.add(cadenaSQL)) {
            Material m = (Material) i$.next();
            cadenaSQL = (new StringBuilder()).append(" INSERT INTO PROFFLINE_TB_MATERIAL_DSCTO_MANUAL(MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, VENTAS_ACUMULADO, VENTAS_PROMEDIO, CLIENTE) VALUES ('").append(m.getStrMATNR()).append("', '").append(m.getStrStock()).append("','").append(m.getStrSU()).append("','").append(m.getStrShortText()).append("','").append(m.getStrTextLine()).append("','").append(m.getStrTargetQTY()).append("','").append(m.getStrPrice1()).append("','").append(m.getStrPrice2()).append("','").append(m.getStrPrice3()).append("','").append(m.getStrPrice4()).append("','").append(m.getStrPRDHA()).append("','").append(m.getTipoMaterial()).append("','").append(m.getStrNORMT()).append("','").append(m.getStrZZORDCO()).append("','").append(m.getStrCellDesign()).append("','").append(m.getMTART()).append("','").append(m.getStrTypeMat()).append("','").append(m.getStrGrupoCompra()).append("','").append(m.getStrSt1()).append("','").append("").append("','").append("").append("','").append("").append("');").toString();
        }

        ResultExecute resultExecute = new ResultExecute();
        try {
        	resultExecute.CrearConexionPorVendedor(strCodigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(listaSQL, strCodigoVendedor);
            resultExecute.CerrarConexion();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("MATERIAL DSCTO MANUAL ").append(strCodigoVendedor).toString()))).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("MATERIAL DSCTO MANUAL ").append(strCodigoVendedor).toString()))).toString());
        }
    }

    @Override
    public int getCountRowPromoOferta(String codigoVendedor) {
        column = new HashMap();
        column.put("String:0", "filas");
        int f = 0;
        String sqlMaterial = " SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_PROMO_OFERTA";
        try {
            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, codigoVendedor);
            HashMap res = null;
            res = (HashMap) mapResultado.get(0);
            f = Integer.parseInt(res.get("filas").toString().trim());
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e) + codigoVendedor);
        }
        return f;
    }

    @Override
    public int getCountRowDsctoPolitica(String codigoVendedor) {
        column = new HashMap();
        column.put("String:0", "filas");
        int f = 0;
        String sqlMaterial = " SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_DSCTO_POLITICA";
        try {
            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, codigoVendedor);
            HashMap res = null;
            res = (HashMap) mapResultado.get(0);
            f = Integer.parseInt(res.get("filas").toString().trim());
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e) + codigoVendedor);
        }
        return f;
    }

    @Override
    public int getCountRowDsctoManual(String codigoVendedor) {
        column = new HashMap();
        column.put("String:0", "filas");
        int f = 0;
        String sqlMaterial = " SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_DSCTO_MANUAL";
        try {
            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, codigoVendedor);
            HashMap res = null;
            res = (HashMap) mapResultado.get(0);
            f = Integer.parseInt(res.get("filas").toString().trim());
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e) + codigoVendedor);
        }
        return f;
    }

    @Override
    public int getCountRowTopCliente(String codigoVendedor) {
        column = new HashMap();
        column.put("String:0", "filas");
        int f = 0;
        String sqlMaterial = " SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_TOP_CLIENTE";
        try {
            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, codigoVendedor);
            HashMap res = null;
            res = (HashMap) mapResultado.get(0);
            f = Integer.parseInt(res.get("filas").toString().trim());
            
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e) + codigoVendedor);
        }
        return f;
    }

    @Override
    public int getCountRowTopTipologia(String codigoVendedor) {
        column = new HashMap();
        column.put("String:0", "filas");
        int f = 0;
        String sqlMaterial = " SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA";
        try {
            mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlMaterial, column, codigoVendedor);
            HashMap res = null;
            res = (HashMap) mapResultado.get(0);
            f = Integer.parseInt(res.get("filas").toString().trim());
            
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e) + codigoVendedor);
        }
        return f;
    }

	

}
