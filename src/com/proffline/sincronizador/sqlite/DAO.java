/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.sqlite;

import com.proffline.sincronizador.bean.Agenda;
import com.proffline.sincronizador.bean.BancoPromesa;
import com.proffline.sincronizador.bean.BeanParametro;
import com.proffline.sincronizador.bean.BloqueoEntrega;
import com.proffline.sincronizador.bean.ClaseMaterial;
import com.proffline.sincronizador.bean.Cliente;
import com.proffline.sincronizador.bean.Combo;
import com.proffline.sincronizador.bean.ComboDetalle;
import com.proffline.sincronizador.bean.Condicion1;
import com.proffline.sincronizador.bean.Condicion2;
import com.proffline.sincronizador.bean.CondicionPago;
import com.proffline.sincronizador.bean.CondicionPagoDetalle;
import com.proffline.sincronizador.bean.Destinatario;
import com.proffline.sincronizador.bean.Dispositivo;
import com.proffline.sincronizador.bean.Empleado;
import com.proffline.sincronizador.bean.EmpleadoCliente;
import com.proffline.sincronizador.bean.Feriado;
import com.proffline.sincronizador.bean.FormaPago;
import com.proffline.sincronizador.bean.Indicador;
import com.proffline.sincronizador.bean.Jerarquia;
import com.proffline.sincronizador.bean.MarcaEstrategica;
import com.proffline.sincronizador.bean.MarcaVendedor;
import com.proffline.sincronizador.bean.Material;
import com.proffline.sincronizador.bean.MaterialNuevo;
import com.proffline.sincronizador.bean.MaterialStock;
import com.proffline.sincronizador.bean.NombreMarcaEstrategica;
import com.proffline.sincronizador.bean.Presupuesto;
import com.proffline.sincronizador.bean.Rol;
import com.proffline.sincronizador.bean.SincronizadorPedido;
import com.proffline.sincronizador.bean.Tipologia;
import com.proffline.sincronizador.bean.Usuario;
import com.proffline.sincronizador.bean.TipoGestion;
import com.proffline.sincronizador.bean.UsuarioRol;
import com.proffline.sincronizador.bean.Vista;
import com.proffline.sincronizador.bean.VistaRol;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonatan Aviles Arnao
 * 
 * Last Update: 20 Agosto 2018 
 *  
 */

public class DAO {

    public static List<Material> obtenerListaMaterialPorMarca(String strCodigoMarca) {
        List<Material> listaMaterial = new ArrayList<Material>();
        HashMap column = null;
        column = new HashMap();
        column.put("String:0", "MATNR");
        String consulta = " SELECT MATNR FROM PROFFLINE_TB_MATERIAL where NORMT='" + strCodigoMarca + "'";
        try {
            HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
            HashMap res = null;
            for (int i = 0; i < mapResultado.size(); i++) {
                res = (HashMap) mapResultado.get(i);
                Material material = new Material();
                material.setStrMATNR(res.get("MATNR").toString());
                listaMaterial.add(material);
            }

        } catch (Exception e) {
        }
        return listaMaterial;
    }
    public static Material obtenerMaterialporCodigo(String Codigo){
    	Material material = new Material();
    	HashMap column = null;
        column = new HashMap();
        column.put("String:0", "SHORT_TEXT");
        column.put("String:1", "TEXT_LINE");
        column.put("String:2", "PRICE_1");
        String consulta = " SELECT SHORT_TEXT, TEXT_LINE, PRICE_1 FROM PROFFLINE_TB_MATERIAL where MATNR='" + Codigo + "'";
        try {
            HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
            HashMap res = null;
            res = (HashMap) mapResultado.get(0);
            material.setStrShortText(res.get("SHORT_TEXT").toString());
            material.setStrTextLine(res.get("TEXT_LINE").toString());
            material.setStrPrice1(res.get("PRICE_1").toString());
        } catch (Exception e) {
        	material.setStrShortText("");
            material.setStrTextLine("");
            material.setStrPrice1("");
        }
    	return material;
    }

    public static List<Material> obtenerListaMaterialesPorClaseMaterial(
            String strCodigoClaseMaterial) {
        List<Material> listaMateriales = new ArrayList<Material>();
        HashMap column = null;
        column = new HashMap();
        column.put("String:0", "TXTCODIGOMATERIAL");
        String consulta = " select TXTCODIGOMATERIAL "
                + " from proffline_tb_clase_material "
                + " where txtDescripcionClase='" + strCodigoClaseMaterial + "' ";
        HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
        HashMap res = null;
        String ii = "";
        for (int i = 0; i < mapResultado.size(); i++) {
            res = (HashMap) mapResultado.get(i);
            Material bean = new Material();
            try {
                ii = "" + Integer.parseInt("" + res.get("TXTCODIGOMATERIAL"));
            } catch (Exception e) {
                ii = "" + res.get("TXTCODIGOMATERIAL");
            }

            bean.setStrMATNR(ii);
            listaMateriales.add(bean);
        }
        return listaMateriales;
    }

    public static List<Material> obtenerListaDePrecios(String jerarquia) {
        List<Material> listaPrecios = new ArrayList<Material>();
        HashMap column = null;
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
        String consulta = "SELECT * FROM PROFFLINE_TB_MATERIAL WHERE PRDHA LIKE '"
                + jerarquia + "%';";
        HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
        HashMap res = null;
        for (int i = 0; i < mapResultado.size(); i++) {
            res = (HashMap) mapResultado.get(i);
            Material material = new Material();
            material.setStrMATNR(res.get("MATNR").toString());
            material.setStrSU(res.get("S_U").toString());
            material.setStrShortText(res.get("SHORT_TEXT").toString());
            material.setStrPrice1(res.get("PRICE_1").toString());
            material.setTipoMaterial(res.get("HER").toString());
            material.setStrNORMT(res.get("NORMT").toString());
            material.setStrTextLine(res.get("TEXT_LINE").toString());
            material.setStrPRDHA(res.get("PRDHA").toString());
            listaPrecios.add(material);

        }
        return listaPrecios;
    }

    public static List<String> obtenerListaIdUsuarios() {
        HashMap column = null;
        column = new HashMap();
        column.put("String:0", "txtIdentificacion");
        List<String> listaIdUsuarios = new ArrayList<String>();
        String consulta = "SELECT txtIdentificacion FROM PROFFLINE_TB_USUARIO;";
        try {
            HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
            if (mapResultado != null) {
                for (HashMap res : mapResultado.values()) {
                    listaIdUsuarios.add(res.get("txtIdentificacion") + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaIdUsuarios;
    }

    public static List<String> obtenerListaIdRoles() {
        HashMap column = null;
        column = new HashMap();
        column.put("String:0", "txtIdRol");
        List<String> listaIdRoles = new ArrayList<String>();
        String consulta = "SELECT txtIdRol FROM PROFFLINE_TB_ROL;";
        try {
            HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
            if (mapResultado != null) {
                for (HashMap res : mapResultado.values()) {
                    listaIdRoles.add(res.get("txtIdRol") + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaIdRoles;
    }

    public static HashMap<String, String> obtenerMapaHashClaseMaterial() {
        HashMap column = null;
        HashMap<String, String> hm = new HashMap<String, String>();
        column = new HashMap();
        column.put("String:0", "txtCodigoClase");
        column.put("String:1", "txtCodigoMaterial");
        String consulta = "SELECT txtCodigoClase, txtCodigoMaterial FROM PROFFLINE_TB_CLASE_MATERIAL;";
        try {
            HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
            for (HashMap res : mapResultado.values()) {
                String key = "" + res.get("txtCodigoClase");
                String value = "" + res.get("txtCodigoMaterial");
                hm.put(key + "," + value, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, String> obtenerMapaHashMaterial() {
        HashMap column = null;
        column = new HashMap();
        column.put("String:0", "MATNR");
        column.put("String:1", "ZZORDCO");
        String consulta = "SELECT ZZORDCO, MATNR FROM PROFFLINE_TB_MATERIAL";
        HashMap<String, String> hm = new HashMap<String, String>();
        try {
            HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
            if (mapResultado != null) {
                for (HashMap res : mapResultado.values()) {
                    String key = "" + res.get("MATNR");
                    String value = "" + res.get("ZZORDCO");
                    hm.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return hm;
        }
    }

    public static void limpiarTabla(String tabla) {
        try {
            List<String> queries = new ArrayList<String>();
            String consulta = "DELETE FROM " + tabla + ";";
            queries.add(consulta);
            ResultExecute resultExecute = new ResultExecute();
            resultExecute.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeLimpiarDatosExitosaSQL(tabla)));
        } catch (Exception e) {
            e.printStackTrace();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(tabla)));
        }
    }

    public static void insertarListaVistaRol(List<VistaRol> listaVistaRol) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_VISTA_ROL");
        for (VistaRol vistaRol : listaVistaRol) {
            String consulta = "INSERT INTO PROFFLINE_TB_VISTA_ROL (txtMandante,txtIdRol,txtNombreVista) VALUES ('" + vistaRol.getMandante().replaceAll("'", "''") + "',"
                    + "'" + vistaRol.getIdRol().replaceAll("'", "''") + "',"
                    + "'" + vistaRol.getNombreVista().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("VISTA ROL")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("VISTA ROL")));
        }
    }

    public static void insertarListaUsuario(List<Usuario> listaUsuario) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_USUARIO");
        for (Usuario usuario : listaUsuario) {
            String consulta = "INSERT INTO PROFFLINE_TB_USUARIO (txtMandante, "
                    + " txtIdUsuario, " + " txtNombreUsuario, "
                    + " txtClaveUsuario, " + " txtFechaRegistroUsuario, "
                    + " txtFechaUltimoAccesoUsuario, "
                    + " txtHoraUltimoAccesoUsuario, " + " numCambioClave, "
                    + " numIntento, " + " numUsuarioBloqueado,"
                    + " txtIdentificacion," + " txtUsuario, txtDivision) "
                    + " VALUES ('" + usuario.getStrMandante().replaceAll("'", "''") + "','"
                    + usuario.getStrIdUsuario().replaceAll("'", "''") + "','"
                    + usuario.getStrNomUsuario().replaceAll("'", "''") + "','"
                    + usuario.getStrClaUsuario().replaceAll("'", "''") + "','"
                    + usuario.getStrFecCre().replaceAll("'", "''") + "','"
                    + usuario.getStrFecUltAccSis().replaceAll("'", "''") + "','"
                    + usuario.getStrHorUltAccSis().replaceAll("'", "''") + "','"
                    + usuario.getStrCambioClave().replaceAll("'", "''") + "','"
                    + Integer.parseInt(usuario.getStrIntento()) + "','"
                    + usuario.getStrBloqueado().replaceAll("'", "''") + "','"
                    + usuario.getStrIdentificacion().replaceAll("'", "''") + "','"
                    + usuario.getStrUsuario().replaceAll("'", "''") + "','" + usuario.getStrDivision() + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("USUARIO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("USUARIO")));
        }
    }

    public static void insertarListaBloqueoEntrega(List<BloqueoEntrega> listaBloqueoEntrega) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_BLOQUEO_ENTREGA");
        for (BloqueoEntrega bloqueoEntrega : listaBloqueoEntrega) {
            String consulta = "INSERT INTO PROFFLINE_TB_BLOQUEO_ENTREGA (txtCodigo, txtDescripcion) " + " VALUES ('"
                    + bloqueoEntrega.getStrCodigo().replaceAll("'", "''") + "','" + bloqueoEntrega.getStrDescripcion().replaceAll("'", "''") + "') ";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("BLOQUEO ENTREGA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("BLOQUEO ENTREGA")));
        }
    }

    public static void insertarListaCondicionPago(List<CondicionPago> listaCondicionPago) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_CONDICIONES_PAGO");
        for (CondicionPago condicionPago : listaCondicionPago) {
            String consulta = "INSERT INTO PROFFLINE_TB_CONDICIONES_PAGO (txtZTERM, txtVTEXT) VALUES ('" + condicionPago.getStrZTERM().replaceAll("'", "''")
                    + "','" + condicionPago.getStrVTEXT().replaceAll("'", "''") + "') ";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CONDICION PAGO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CONDICION PAGO")));
        }
    }

    public static void insertarListaCondicionPagoDetalle(List<CondicionPagoDetalle> listaCondicionPagoDetalle) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_CONDICIONES_PAGO_DETALLE");
        for (CondicionPagoDetalle condicionPagoDetalle : listaCondicionPagoDetalle) {
            String consulta = "INSERT INTO PROFFLINE_TB_CONDICIONES_PAGO_DETALLE (txtZTERMH, txtZTERMD) VALUES ('"
                    + condicionPagoDetalle.getStrZTERM().replaceAll("'", "''") + "','" + condicionPagoDetalle.getStrZTERD().replaceAll("'", "''") + "')";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CONDICION PAGO DETALLE")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CONDICION PAGO DETALLE")));
        }
    }

    public static void insertarListaFeriado(List<Feriado> listaFeriado) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_FERIADO");
        for (Feriado feriado : listaFeriado) {
            String consulta = "INSERT INTO PROFFLINE_TB_FERIADO VALUES ('" + feriado.getStrSigFeriado().replaceAll("'", "''") + "',"
                    + "'" + feriado.getStrMesFeriado().replaceAll("'", "''") + "',"
                    + "'" + feriado.getStrDiaFeriado().replaceAll("'", "''") + "',"
                    + "'" + feriado.getStrOriFeriado().replaceAll("'", "''") + "')";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("FERIADO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("FERIADO")));
        }
    }

    public static void insertarListaClaseMaterial(List<ClaseMaterial> listaClaseMaterial) {
        List<String> queries = new ArrayList<String>();
        for (ClaseMaterial claseMaterial : listaClaseMaterial) {
            String consulta = "INSERT INTO PROFFLINE_TB_CLASE_MATERIAL ("
                    + "TXTCODIGOCLASE, TXTDESCRIPCIONCLASE, TXTCODIGOMATERIAL" + ")" + " VALUES (" + "'"
                    + claseMaterial.getStrCodigoClaseMaterial().replaceAll("'", "''") + "'" + "," + "'"
                    + claseMaterial.getStrDescripcionClaseMaterial().replaceAll("'", "''") + "'" + ",'"
                    + claseMaterial.getStrCodigoMaterial().replaceAll("'", "''") + "'" + ");";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CLASE MATERIAL")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CLASE MATERIAL")));
        }
    }

    public static void insertarListaJerarquia(List<Jerarquia> listaJerarquia) {
        List<String> consultas = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_JERARQUIA");
        for (Jerarquia jerarquia : listaJerarquia) {
            String consulta = "INSERT INTO PROFFLINE_TB_JERARQUIA (txtIdPadre, txtPRODH, txtS, txtVTEXT, txtZZSEQ, txtICON,txtI, txtCELLDESIGN) VALUES ('"
                    + jerarquia.getStrIdPadre().replaceAll("'", "''") + "','" + jerarquia.getStrPRODH().replaceAll("'", "''") + "','"
                    + jerarquia.getStrS().replaceAll("'", "''") + "','" + jerarquia.getStrVTEXT().replaceAll("'", "''") + "','"
                    + jerarquia.getStrZZSEQ().replaceAll("'", "''") + "','" + jerarquia.getStrICON().replaceAll("'", "''") + "','"
                    + jerarquia.getStrI().replaceAll("'", "''") + "','" + jerarquia.getStrCellDesign().replaceAll("'", "''") + "') ";
            consultas.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(consultas);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("JERARQUIA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("JERARQUIA")));
        }
    }

    public static void insertarListaMaterial(List<Material> listaMaterial) {
        List<String> consultas = new ArrayList<String>();
        for (Material material : listaMaterial) {
            int tipo = material.getAccionEnBaseDatos();
            switch (tipo) {
                case 1:
                    consultas.add("INSERT INTO PROFFLINE_TB_MATERIAL (MATNR, STOCK, S_U, SHORT_TEXT, TEXT_LINE, TARGET_QTY, PRICE_1, PRICE_2, PRICE_3, PRICE_4, PRDHA, HER, NORMT, ZZORDCO, CELL_DESIGN, MTART, TYPEMAT, GRUPO_COMPRA, ST_1, FEC_ING, COSTO, MARGEN_OBJ) VALUES ('"
                            + material.getStrMATNR().replaceAll("'", "''") + "','" + material.getStrStock().replaceAll("'", "''") + "','"
                            + material.getStrSU().replaceAll("'", "''") + "','" + material.getStrShortText().replaceAll("'", "''") + "','"
                            + material.getStrTextLine().replaceAll("'", "''") + "','" + material.getStrTargetQTY().replaceAll("'", "''") + "','"
                            + material.getStrPrice1().replaceAll("'", "''") + "','" + material.getStrPrice2().replaceAll("'", "''") + "','"
                            + material.getStrPrice3().replaceAll("'", "''") + "','" + material.getStrPrice4().replaceAll("'", "''") + "','"
                            + material.getStrPRDHA().replaceAll("'", "''") + "','" + material.getStrHER().replaceAll("'", "''") + "','"
                            + material.getStrNORMT().replaceAll("'", "''") + "','" + material.getStrZZORDCO().replaceAll("'", "''") + "','"
                            + material.getStrCellDesign().replaceAll("'", "''") + "','" + material.getMTART().replaceAll("'", "''") + "','"
                            + material.getStrTypeMat().replaceAll("'", "''") + "','" + material.getStrGrupoCompra().replaceAll("'", "''") + "','"
                      		+ material.getStrSt1().replaceAll("'", "''") + "','" + material.getStrFec_Ing().replaceAll("'", "''") + "','"
                            + material.getStrCosto().replaceAll("'", "''") + "','" + material.getStrMargen_Obj().replaceAll("'", "''") + "')");
                    break;
                case 0:
                    consultas.add("UPDATE PROFFLINE_TB_MATERIAL SET STOCK='"
                            + material.getStrStock().replaceAll("'", "''") + "', S_U='" + material.getStrSU().replaceAll("'", "''")
                            + "', SHORT_TEXT='" + material.getStrShortText().replaceAll("'", "''")
                            + "', TEXT_LINE='" + material.getStrTextLine().replaceAll("'", "''")
                            + "', TARGET_QTY='" + material.getStrTargetQTY().replaceAll("'", "''")
                            + "', PRICE_1='" + material.getStrPrice1().replaceAll("'", "''")
                            + "', PRICE_2='" + material.getStrPrice2().replaceAll("'", "''")
                            + "', PRICE_3='" + material.getStrPrice3().replaceAll("'", "''")
                            + "', PRICE_4='" + material.getStrPrice4().replaceAll("'", "''")
                            + "', PRDHA='" + material.getStrPRDHA().replaceAll("'", "''")
                            + "', HER='" + material.getStrHER().replaceAll("'", "''")
                            + "', NORMT='" + material.getStrNORMT().replaceAll("'", "''")
                            + "', ZZORDCO='" + material.getStrZZORDCO().replaceAll("'", "''")
                            + "', CELL_DESIGN='" + material.getStrCellDesign().replaceAll("'", "''")
                            + "', MTART='" + material.getMTART().replaceAll("'", "''")
                            + "', TYPEMAT='" + material.getStrTypeMat().replaceAll("'", "''")
                            + "', GRUPO_COMPRA='" + material.getStrGrupoCompra().replaceAll("'", "''")
                            + "', ST_1='" + material.getStrSt1().replaceAll("'", "''")
                            + "', FEC_ING='" + material.getStrFec_Ing().replaceAll("'", "''")
                            + "', COSTO='" + material.getStrCosto().replaceAll("'", "''")
                            + "', MARGEN_OBJ='" + material.getStrMargen_Obj().replaceAll("'", "''")
                            + "' WHERE MATNR='" + material.getStrMATNR().replaceAll("'", "''") + "'");
                    break;
            }
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(consultas);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("MATERIAL")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("MATERIAL")));
        }
    }
    public static void insertarListaMaterialNuevo(List<MaterialNuevo> listaMaterialNuevo) {
        List<String> consultas = new ArrayList<String>();
        for (MaterialNuevo material : listaMaterialNuevo) {
        	//TODO
        	Material m = obtenerMaterialporCodigo(material.getStrMATNR());
            consultas.add("INSERT INTO PROFFLINE_TB_MATERIAL_NUEVO (MATNR, SHORT_TEXT, TEXT_LINE, PRICE_1, NORMT, ZZORDCO) VALUES ('"
                    + material.getStrMATNR().replaceAll("'", "''")  + "','"
                    + m.getStrShortText().replaceAll("'", "''") + "','"
                    + m.getStrTextLine().replaceAll("'", "''") + "','"
            		+ m.getStrPrice1().replaceAll("'", "''") + "','"
                    + material.getStrNORMT().replaceAll("'", "''") + "','" 
                    + material.getStrZZORDCO().replaceAll("'", "''") + "')");     
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(consultas);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("MATERIAL NUEVO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("MATERIAL NUEVO")));
        }
    }
    public static void insertarBloquePedido(List<BeanParametro> listaParametro) throws Exception{
		List<String> listaSqlParametro = new ArrayList<String>();
		for (BeanParametro bp : listaParametro) {
			String sqlBloqueoPedido = "INSERT INTO PROFFLINE_TB_ZTCONSTANTE" +
			"(TXTMODULO, TXTNOMBREPROGRAMA, TXTNOMBRECAMPO, SECUENCIA, TXTOPCION, VALOR_1, VALOR_2, TXTNAME, TXTFECHA) " +
			"VALUES ('" + bp.getStrModulo() + "','" + bp.getStrNombrePrograma() + "','" + bp.getStrNombreCampo() + 
			"','" + bp.getSecuencia() 	+ "','" + bp.getStrOpcion() 		+ "','" + bp.getStrValorUno() + 
			"','" + bp.getStrValorDos() + "','" + bp.getStrNombreUsuario() + "','" + bp.getStrFecha() + "');";
			
			listaSqlParametro.add(sqlBloqueoPedido);
		}
		ResultExecute re = new ResultExecute();
		try {
            re.ejecutarConsulta(listaSqlParametro);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CONSTANTES")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CONSTANTES")));
        }
	}
    public static void insertarListaAgenda(List<Agenda> listaAgenda) {
        DAO.limpiarTabla("PROFFLINE_TB_AGENDA");
        List<String> consultas = new ArrayList<String>();
        for (Agenda agenda : listaAgenda) {
            String consulta = "INSERT INTO PROFFLINE_TB_AGENDA (txtVendor_Id, txtVendor_Name, "
                    + "txtBegda, txtEndda, txtHora, txtCust_Id, txtCust_Name, txtCust_Addres, "
                    + "txtCust_Telf, txtCust_Telfx, txtCust_1, txtCu_1, txtCu_2, txtCust_Klimk, "
                    + "txtCus, txtCust_Available, txtDescription, txtSt,txtStat, txtTy, txtCust_2, "
                    + "txtType_Description, txtStdRegAge) VALUES ('" + agenda.getStrVendorId().replaceAll("'", "''") + "','"
                    + agenda.getStrVendorName().replaceAll("'", "''") + "','" + agenda.getStrBegda().replaceAll("'", "''") + "','"
                    + agenda.getStrEndda().replaceAll("'", "''") + "','" + agenda.getStrHora().replaceAll("'", "''") + "','"
                    + agenda.getStrCustId().replaceAll("'", "''") + "','" + agenda.getStrCustName().replaceAll("'", "''") + "','"
                    + agenda.getStrCustAddres().replaceAll("'", "''") + "','" + agenda.getStrCustTelef().replaceAll("'", "''") + "','"
                    + agenda.getStrCustTelefx().replaceAll("'", "''") + "','" + agenda.getStrCust1().replaceAll("'", "''") + "','"
                    + agenda.getStrCu1().replaceAll("'", "''") + "','" + agenda.getStrCu2().replaceAll("'", "''") + "','"
                    + agenda.getStrCustKlimk().replaceAll("'", "''") + "','" + agenda.getStrCus().replaceAll("'", "''") + "','"
                    + agenda.getStrCustAvailable().replaceAll("'", "''") + "','" + agenda.getStrDescription().replaceAll("'", "''") + "','"
                    + agenda.getStrST().replaceAll("'", "''") + "','" + agenda.getStrSTAT().replaceAll("'", "''") + "','"
                    + agenda.getStrTY().replaceAll("'", "''") + "','" + agenda.getStrCust2().replaceAll("'", "''") + "','"
                    + agenda.getStrTypeDescription().replaceAll("'", "''") + "','" + agenda.getStrEstado().replaceAll("'", "''") + "')";
            consultas.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(consultas);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("AGENDA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("AGENDA")));
        }
    }

    public static void insertarListaCliente(List<Cliente> listaCliente) {
        List<String> consultas = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_CLIENTE");
        for (Cliente cliente : listaCliente) {
            String consulta = "INSERT INTO PROFFLINE_TB_CLIENTE (txtIdCliente, "
                    + "txtCompaniaCliente, txtNombreCliente, txtApellidosCliente, "
                    + "txtEmailCliente, txtTelefonoPrivadoCliente, txtTelefonoTrabajoCliente, "
                    + "txtTelefonoMovilCliente, txtNumeroFaxCliente, txtDireccionCliente, "
                    + "txtCiudadCliente, txtEstadoProvinciaCliente, txtCodigoPostalCliente, "
                    + "txtMarcaBloqueoAlmacen, txtCodigoTipologia, txtDescripcionTipologia, "
                    + "txtCodOrgVentas, txtCodCanalDist, txtCodSector, txtIndicadorImpuesto, "
                    + "txtOficinaVentas, txtGrupoVentas) VALUES ('"
                    + cliente.getStrIdCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrCompaniaCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrNombreCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrApellidosCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrEmailCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrTelefonoPrivadoCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrTelefonoTrabajoCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrTelefonoMovilCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrNumeroFaxCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrDireccionCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrCiudadCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrEstadoProvinciaCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrCodigoPostalCliente().replaceAll("'", "''") + "','"
                    + cliente.getStrMarcaBloqueoAlmacen().replaceAll("'", "''") + "','"
                    + cliente.getStrCodigoTipologia().replaceAll("'", "''") + "','"
                    + cliente.getStrDescripcionTipologia().replaceAll("'", "''") + "','"
                    + cliente.getStrCodOrgVentas().replaceAll("'", "''") + "','"
                    + cliente.getStrCodCanalDist().replaceAll("'", "''") + "','"
                    + cliente.getStrCodSector().replaceAll("'", "''") + "','"
                    + cliente.getIndicadorIva().replaceAll("'", "''") + "','"
                    + cliente.getStrOficinaVentas().replaceAll("'", "''") + "','"
                    + cliente.getStrGrupoVentas().replaceAll("'", "''") + "')";
            consultas.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(consultas);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CLIENTE")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CLIENTE")));
        }
    }

    public static void insertarListaUsuarioRol(List<UsuarioRol> listaUsuarioRol) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_USUARIO_ROL");
        for (UsuarioRol usuarioRol : listaUsuarioRol) {
            String consulta = "INSERT INTO PROFFLINE_TB_USUARIO_ROL (txtMandante, "
                    + "txtIdUsuario, txtIdRoL) VALUES ('" + usuarioRol.getStrMandante().replaceAll("'", "''")
                    + "','" + usuarioRol.getStrIdUsu().replaceAll("'", "''") + "','" + usuarioRol.getStrIdRol().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("USUARIO ROL")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("USUARIO ROL")));
        }
    }
    
    public static void insertarListaTipoGestion(List<TipoGestion> listaTipoGestion) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_TIPO_GESTION");
        for (TipoGestion tipoGestion : listaTipoGestion) {
            String consulta = "INSERT INTO PROFFLINE_TB_TIPO_GESTION (man, "
                    + "sp, bsar, vtext) VALUES ('" + tipoGestion.getMan().replaceAll("'", "''")
                    + "','" + tipoGestion.getSp().replaceAll("'", "''")  + "','" + tipoGestion.getBsar().replaceAll("'", "''") + "','" + tipoGestion.getVtext().replaceAll("'", "''") + "');" ;
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("TIPO GESTION")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("TIPO GESTION")));
        }
    }

    public static void insertarListaTipologia(List<Tipologia> listaTipologia) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_TIPOLOGIA");
        for (Tipologia tipologia : listaTipologia) {
            String consulta = "INSERT INTO PROFFLINE_TB_TIPOLOGIA (MTAR, MTBEZ) "
                    + "VALUES ('" + tipologia.getStrMTAR().replaceAll("'", "''") + "','" + tipologia.getStrMTBEZ().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("TIPOLOGIA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("TIPOLOGIA")));
        }
    }

    public static void insertarListaRol(List<Rol> listaRol) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_ROL");
        for (Rol rol : listaRol) {
            String consulta = "INSERT INTO  PROFFLINE_TB_ROL (txtMandante, txtIdRol, txtNombreRol) VALUES ('"
                    + rol.getStrMandante().replaceAll("'", "''") + "','"
                    + rol.getStrIdRol().replaceAll("'", "''") + "','"
                    + rol.getStrNomRol().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("ROL")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("ROL")));
        }
    }

    public static void insertarListaVista(List<Vista> listaVista) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_VISTA");
        for (Vista vista : listaVista) {
            String consulta = "INSERT INTO PROFFLINE_TB_VISTA (txtMandante, txtNombreVista, txtDescVista) VALUES ('"
                    + vista.getStrMandante().replaceAll("'", "''") + "','"
                    + vista.getStrNomVis().replaceAll("'", "''") + "','"
                    + vista.getStrDesVis().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("VISTA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("VISTA")));
        }
    }

    public static void insertarListaCombo(List<List> listaCombosYDetalles) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_COMBO");
        limpiarTabla("PROFFLINE_TB_DETALLE_COMBO");
        if (listaCombosYDetalles != null && listaCombosYDetalles.size() == 2) {
            List<Combo> combos = listaCombosYDetalles.get(0);
            List<ComboDetalle> comboDetalles = listaCombosYDetalles.get(1);
            for (Combo combo : combos) {
                String consulta = "INSERT INTO PROFFLINE_TB_COMBO(UNIDAD, CODIGO, "
                        + "NOMBRE, PRECIO) VALUES ('"
                        + combo.getStrUnidad().replaceAll("'", "''") + "','"
                        + combo.getStrCodigo().replaceAll("'", "''") + "','"
                        + combo.getStrNombre().replaceAll("'", "''") + "','"
                        + combo.getStrPrecio().replaceAll("'", "''") + "')";
                queries.add(consulta);
            }
            for (ComboDetalle comboDetalle : comboDetalles) {
                String consulta = "INSERT INTO PROFFLINE_TB_DETALLE_COMBO(CANTIDAD, "
                        + "CODIGO, NOMBRE, PRECIO, IDCOMBO) VALUES ('"
                        + comboDetalle.getCantidad() + "','"
                        + comboDetalle.getCodigoMaterial().replaceAll("'", "''") + "','"
                        + comboDetalle.getDescripcionMaterial().replaceAll("'", "''") + "','"
                        + comboDetalle.getPrecioNeto().replaceAll("'", "''") + "','"
                        + comboDetalle.getIdCombo().replaceAll("'", "''") + "')";
                queries.add(consulta);
            }
            ResultExecute re = new ResultExecute();
            try {
                re.ejecutarConsulta(queries);
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("COMBO")));
            } catch (Exception e) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("COMBO")));
            }
        }
    }

    public static void insertarListaCondicion1(List<Condicion1> listaCondicion1) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_CONDICION_1");
        for (Condicion1 condicion1 : listaCondicion1) {
            String consulta = "INSERT INTO PROFFLINE_TB_CONDICION_1(PRIORIDADGRUPO, "
                    + "PRIORIDADCONDICION, CLASEMATERIAL, CONDICIONPAGO, CLIENTE, "
                    + "TIPO, PORCENTAJE) VALUES ("
                    + condicion1.getIntPrioridadGrupo() + ","
                    + condicion1.getIntPrioridadInterna() + ",'"
                    + condicion1.getStrClaseMaterial().replaceAll("'", "''") + "','"
                    + condicion1.getStrCondicionPago().replaceAll("'", "''") + "','"
                    + condicion1.getStrCliente().replaceAll("'", "''") + "','"
                    + condicion1.getStrTipo().replaceAll("'", "''") + "','"
                    + condicion1.getDblDscto() + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CONDICION 1")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CONDICION 1")));
        }
    }

    public static void insertarListaCondicion2(List<Condicion2> listaCondicion2) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_CONDICION_2");
        for (Condicion2 condicion2 : listaCondicion2) {
            String consulta = "INSERT INTO PROFFLINE_TB_CONDICION_2 (PRIORIDADGRUPO, PRIORIDADCONDICION, CLIENTE, GRUPOCLIENTE, CANAL, TIPO, PORCENTAJE) VALUES ("
                    + +condicion2.getIntPrioridadGrupo() + ","
                    + condicion2.getIntPrioridadInterna() + ",'"
                    + condicion2.getStrCliente().replaceAll("'", "''") + "','"
                    + condicion2.getStrGrupoCliente().replaceAll("'", "''") + "','"
                    + condicion2.getStrCanal().replaceAll("'", "''") + "','"
                    + condicion2.getStrTipo().replaceAll("'", "''") + "','"
                    + condicion2.getDblDscto() + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CONDICION 2")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CONDICION 2")));
        }
    }

    public static void insertarListaMaterialStock(List<MaterialStock> listaMaterialStock) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_MATERIAL_STOCK");
        for (MaterialStock materialStock : listaMaterialStock) {
            String consulta = "INSERT INTO PROFFLINE_TB_MATERIAL_STOCK (MATNR,STOCK) VALUES ('"
                    + materialStock.getCodigo().replaceAll("'", "''") + "','"
                    + materialStock.getStock().replaceAll("'", "''") + "')";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("MATERIAL STOCK")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("MATERIAL STOCK")));
        }
    }

    public static void insertarListaDispositivo(List<Dispositivo> listaDispositivo) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_DISPOSITIVO");
        for (Dispositivo dispositivo : listaDispositivo) {
            String consulta = "INSERT INTO PROFFLINE_TB_DISPOSITIVO VALUES ('"
                    + dispositivo.getStrIdDispositivo().replaceAll("'", "''") + "','"
                    + dispositivo.getStrTipoDispositivo().replaceAll("'", "''") + "','"
                    + dispositivo.getStrNumeroSerieDispositivo().replaceAll("'", "''") + "','"
                    + dispositivo.getStrCodigoActivo().replaceAll("'", "''") + "','"
                    + dispositivo.getStrSimm().replaceAll("'", "''") + "','"
                    + dispositivo.getStrImei().replaceAll("'", "''") + "','"
                    + dispositivo.getStrNumeroTelefono().replaceAll("'", "''") + "','"
                    + dispositivo.getStrNumeroSeguro().replaceAll("'", "''") + "','"
                    + dispositivo.getStrIdUsuario().replaceAll("'", "''") + "','"
                    + dispositivo.getStrNomUsuario().replaceAll("'", "''") + "','"
                    + dispositivo.getStrDispositivoRelacionado().replaceAll("'", "''") + "',"
                    + dispositivo.getStrEstado() + ",'"
                    + dispositivo.getStrObservacion().replaceAll("'", "''") + "','"
                    + dispositivo.getStrUsuReg().replaceAll("'", "''") + "','"
                    + dispositivo.getStrFecReg().replaceAll("'", "''") + "','"
                    + dispositivo.getStrHorReg().replaceAll("'", "''") + "','"
                    + dispositivo.getStrUsuUpd().replaceAll("'", "''") + "','"
                    + dispositivo.getStrFecUpd().replaceAll("'", "''") + "','"
                    + dispositivo.getStrHorUpd().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("DISPOSITIVO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("DISPOSITIVO")));
        }
    }

    public static void insertarListaEmpleadoCliente(List<EmpleadoCliente> listaEmpleadoCliente) {
        List<String> queries = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_EMPLEADO_CLIENTE");
        for (EmpleadoCliente empleadoCliente : listaEmpleadoCliente) {
            String consulta = "INSERT INTO PROFFLINE_TB_EMPLEADO_CLIENTE VALUES ('"
                    + empleadoCliente.getStrIdEmpleado().replaceAll("'", "''")
                    + "','" + empleadoCliente.getStrIdCliente().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("EMPLEADO CLIENTE")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("EMPLEADO CLIENTE")));
        }
    }

    public static void insertarListaEmpleado(List<Empleado> listaEmpleado) {
        List<String> queries = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_EMPLEADO");
        for (Empleado empleado : listaEmpleado) {
            String consulta = "INSERT INTO PROFFLINE_TB_EMPLEADO (txtIdEmpleado, "
                    + "txtNombreEmpleado,txtIdSupervisor) VALUES('"
                    + empleado.getStrIdEmpleado().replaceAll("'", "''") + "','"
                    + empleado.getStrNombreEmpleado().replaceAll("'", "''") + "','"
                    + empleado.getStrIdSupervisor().replaceAll("'", "''")
                    + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("EMPLEADO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("EMPLEADO")));
        }
    }

//    public static void limpiarTablaDestinatario() {
//        limpiarTabla("PROFFLINE_TB_SEDE");
//    }
//    
//    public static void limpiarTablaEmpleado() {
//        limpiarTabla("PROFFLINE_TB_EMPLEADO");
//    }
    public static void insertarListaDestinatario(List<Destinatario> listaDestinatario) {
        List<String> queries = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_SEDE");
        for (Destinatario destinatario : listaDestinatario) {
            String consulta = "INSERT INTO PROFFLINE_TB_SEDE (txtIdCliente, txtCodigo, txtDireccion) VALUES ('"
                    + destinatario.getIdCliente().replaceAll("'", "''") + "','"
                    + destinatario.getCodigo().replaceAll("'", "''") + "','"
                    + destinatario.getDireccion().replaceAll("'", "''") + "')";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("DESTINATARIO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("DESTINATARIO")));
        }
    }

    public static void insertarListaMaterialConsultaDinamica(List<List<Material>> listaMaterial) {
        List<String> queries = new ArrayList<String>();
        limpiarTabla("PROFFLINE_TB_MATERIAL_PROMO_OFERTA");
        limpiarTabla("PROFFLINE_TB_MATERIAL_DSCTO_POLITICA");
        limpiarTabla("PROFFLINE_TB_MATERIAL_DSCTO_MANUAL");
        String consulta = "";
        if (listaMaterial.size() == 3) {
            List<Material> lista1 = listaMaterial.get(0);
            List<Material> lista2 = listaMaterial.get(1);
            List<Material> lista3 = listaMaterial.get(2);
            for (Material material : lista1) {
                consulta = " INSERT INTO PROFFLINE_TB_MATERIAL_PROMO_OFERTA "
                        + " SELECT * FROM PROFFLINE_TB_MATERIAL "
                        + " WHERE MATNR ='" + material.getStrMATNR().replace("'", "''") + "' ";
                queries.add(consulta);
            }
            for (Material material : lista2) {
                consulta = " INSERT INTO PROFFLINE_TB_MATERIAL_DSCTO_POLITICA "
                        + " SELECT * FROM PROFFLINE_TB_MATERIAL "
                        + " WHERE MATNR='" + material.getStrMATNR().replace("'", "''") + "' ";
                queries.add(consulta);
            }
            for (Material material : lista3) {
                consulta = " INSERT INTO PROFFLINE_TB_MATERIAL_DSCTO_MANUAL "
                        + " SELECT * FROM PROFFLINE_TB_MATERIAL "
                        + " WHERE MATNR ='" + material.getStrMATNR().replace("'", "''") + "' ";
                queries.add(consulta);
            }
        }

        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("MATERIAL CONSULTA DINAMICA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("MATERIAL CONSULTA DINAMICA")));
        }
    }

    public static void insertarListaFormaPagoCobranza(List<FormaPago> listaFormaPagoCobranza) {
        List<String> queries = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_FORMA_PAGO_COBRANZA");
        for (FormaPago formaPagoCobranza : listaFormaPagoCobranza) {
            String consulta = "INSERT INTO PROFFLINE_TB_FORMA_PAGO_COBRANZA (idFormaPago, descripcionFormaPago) VALUES ('"
                    + formaPagoCobranza.getIdFormaPago().replaceAll("'", "''") + "','"
                    + formaPagoCobranza.getDescripcionFormaPago().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("FORMA PAGO COBRANZA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("FORMA PAGO COBRANZA")));
        }
    }

    public static void insertarListaFormaPagoAnticipo(List<FormaPago> listaFormaPagoAnticipo) {
        List<String> queries = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_FORMA_PAGO_ANTICIPO");
        for (FormaPago formaPagoAnticipo : listaFormaPagoAnticipo) {
            String consulta = "INSERT INTO PROFFLINE_TB_FORMA_PAGO_ANTICIPO (idFormaPago, descripcionFormaPago) VALUES ('"
                    + formaPagoAnticipo.getIdFormaPago().replaceAll("'", "''") + "','"
                    + formaPagoAnticipo.getDescripcionFormaPago().replaceAll("'", "''") + "');";
            queries.add(consulta);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("FORMA PAGO ANTICIPO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("FORMA PAGO ANTICIPO")));
        }
    }

    public static void insertarListaBancoPromesa(List<BancoPromesa> listaBancoPromesa) {
        List<String> queries = new ArrayList<String>();
        DAO.limpiarTabla("PROFFLINE_TB_BANCO_PROMESA");
        String sql = "";
        for (BancoPromesa bancoPromesa : listaBancoPromesa) {
            if (bancoPromesa.getTipoRecaudo().equals("")) {
                sql = "INSERT INTO PROFFLINE_TB_BANCO_PROMESA (idBancoPromesa, descripcionBancoPromesa) VALUES ('"
                        + bancoPromesa.getIdBancoPromesa()
                        + "','" + bancoPromesa.getDescripcionBancoPromesa() + "');";
            } else {
                sql = "INSERT INTO PROFFLINE_TB_BANCO_PROMESA (idBancoPromesa, descripcionBancoPromesa, tipoRecaudo) VALUES ('"
                        + bancoPromesa.getIdBancoPromesa()
                        + "','" + bancoPromesa.getDescripcionBancoPromesa()
                        + "','" + bancoPromesa.getTipoRecaudo() + "');";
            }
            queries.add(sql);
        }
        ResultExecute re = new ResultExecute();
        try {
            re.ejecutarConsulta(queries);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("BANCO PROMESA")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("BANCO PROMESA")));
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static int filasTabla(String tabla, String codigoVendedor) {
        HashMap column = new HashMap();
        int filas = 0;
        column.put("String:0", "filas");
        String sqlSinc = "SELECT COUNT(*) as filas FROM " + tabla;
        HashMap<Integer, HashMap> mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlSinc, column, codigoVendedor);
        HashMap res = (HashMap) mapResultado.get(0);
        filas = Integer.parseInt(res.get("filas").toString());
        return filas;
    }

    public static void actualizarSincronizar(SincronizadorPedido sincronizar, String codigoVendedor) {
        try {
            String sqlSincronizar = "UPDATE PROFFLINE_TB_SINCRONIZACION SET " + " numCantReg=" + sincronizar.getNumCantReg()
                    + " ,txtFecHor='" + sincronizar.getTxtFecHor() + "' " + " ,numTie='" + sincronizar.getNumTie() + "'"
                    + " WHERE numIdeSinc=" + sincronizar.getNumIdeSinc() + ";";
           
            List<String> sql = new ArrayList<String>();
            sql.add(sqlSincronizar);
            ResultExecute resultExecute = new ResultExecute();
            resultExecute.CrearConexionPorVendedor(codigoVendedor);
            resultExecute.ejecutarConsultaPorVendedor(sql, codigoVendedor);
            resultExecute.CerrarConexion();
        } catch (Exception ex) {
            
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<ClsQueries> insertPresupuesto(List<Presupuesto> presupuestos, String codigoVendedor,List<ClsQueries> queries){
        try {
            //List<String> queries = new ArrayList<String>();
            queries.add(new ClsQueries("PROFFLINE_TB_PRESUPUESTO","DELETE FROM PROFFLINE_TB_PRESUPUESTO","DELETE"));
            for (Presupuesto pre : presupuestos) {
                String sql = "INSERT INTO PROFFLINE_TB_PRESUPUESTO VALUES ('" + pre.getCodCliente()
                    +"','" + pre.getPresupuestoReal()
                    +"','" + pre.getPresupuestoAnual()
                    +"','" + pre.getPresupuestoMensual()
                    +"','" + pre.getPresupuestoFecha()
                    +"','" + pre.getVentaAnual()
                    +"','" + pre.getVentaMensual() 
                    +"','" + pre.getVentaAcumAnioAnt()
                    +"','" + pre.getFechaRegistro()
                    +"','" + pre.getPromoPlus()
                    +"','" + pre.getVta_gracia()+"')";
                queries.add(new ClsQueries("PROFFLINE_TB_PRESUPUESTO",sql,"INSERT"));
            }
            //resultExecute.ejecutarConsultaPorVendedor(queries, codigoVendedor);
           // VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PRESUPUESTO")));
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PRESUPUESTO")));               
                            
        }
        return queries;
    }
    
    public static List<ClsQueries> insertarMarcaEstrategica(List<MarcaEstrategica> marcas, String codigoVendedor,List<ClsQueries> queries){
        try {
            //List<String> queries = new ArrayList<String>();
            queries.add(new ClsQueries("PROFFLINE_TB_MARCA_ESTRATEGICA","DELETE FROM PROFFLINE_TB_MARCA_ESTRATEGICA","DELETE"));
            for (MarcaEstrategica ms : marcas) {
                String sql = "INSERT INTO PROFFLINE_TB_MARCA_ESTRATEGICA VALUES ('" + ms.getCodigoCliente()
                        +"','" + ms.getMarca()
                        +"','" + ms.getPresupuesto()
                        +"','" + ms.getAcumulado() 
                        +"','" + ms.getFecha()+"')";
                queries.add(new ClsQueries("PROFFLINE_TB_MARCA_ESTRATEGICA",sql,"INSERT"));
            }
            //resultExecute.ejecutarConsultaPorVendedor(queries, codigoVendedor);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA")));
        } catch (Exception ex) {
            //Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA")));
        }
        return queries;
    }
    public static List<ClsQueries> insertarMarcaVendedor(List<MarcaVendedor> marcas, String codigoVendedor,List<ClsQueries> queries){
        try {
            //List<String> queries = new ArrayList<String>();
            queries.add(new ClsQueries("PROFFLINE_TB_MARCA_VENDEDOR","DELETE FROM PROFFLINE_TB_MARCA_VENDEDOR","DELETE"));
            for (MarcaVendedor ms : marcas) {
                String sql = "INSERT INTO PROFFLINE_TB_MARCA_VENDEDOR VALUES ('" + ms.getCodigoCliente()
                        +"','" + ms.getMarca()
                        +"','" + ms.getPresupuestoMes()
                        +"','" + ms.getVentaMes()
                        +"','" + ms.getPresupuestoAcumulado()
                        +"','" + ms.getVentaAcumulado()
                        +"','" + ms.getFecha()+"')";
                queries.add(new ClsQueries("PROFFLINE_TB_MARCA_VENDEDOR",sql,"INSERT"));
            }
            //resultExecute.ejecutarConsultaPorVendedor(queries, codigoVendedor);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA")));
        } catch (Exception ex) {
            //Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA")));
        }
        return queries;
    }
    public static List<ClsQueries> insertarIndicadores(List<Indicador> indicadores, String codigoVendedor,List<ClsQueries> queries){
        try {
            //List<String> queries = new ArrayList<String>();
            queries.add(new ClsQueries("PROFFLINE_TB_INDICADORES","DELETE FROM PROFFLINE_TB_INDICADORES","DELETE"));
            for (Indicador i : indicadores) {
                String sql = "INSERT INTO PROFFLINE_TB_INDICADORES VALUES ('" + i.getCodigoCliente()
                        +"','" + i.getMonto()
                        +"','" + i.getAcumudalo()
                        +"','" + i.getEstatus() +"')";
                queries.add(new ClsQueries("PROFFLINE_TB_INDICADORES",sql,"INSERT"));
            }
            //resultExecute.ejecutarConsultaPorVendedor(queries, codigoVendedor);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_INDICADORES")));
        } catch (Exception ex) {
            //Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_INDICADORES")));
        }
        return queries;
    }
    
    public static List<ClsQueries> insertarNombreMarcaEstrategica(List<NombreMarcaEstrategica> nombres, String codigoVendedor,List<ClsQueries> queries){
        try {
            //List<String> queries = new ArrayList<String>();
            queries.add(new ClsQueries("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA","DELETE FROM PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA","DELETE"));
            for (NombreMarcaEstrategica i : nombres) {
                String sql = "INSERT INTO PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA VALUES ('" + i.getMarca()
                        +"','" + i.getNombre() +"')";
                queries.add(new ClsQueries("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA",sql,"INSERT"));
            }
            //resultExecute.ejecutarConsultaPorVendedor(queries, codigoVendedor);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA")));
        } catch (Exception ex) {
           // Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA")));
        }
        return queries;
    }
}
