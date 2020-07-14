package com.proffline.sincronizador.sap;

import com.proffline.sincronizador.bean.Agenda;
import com.proffline.sincronizador.bean.BancoPromesa;
import com.proffline.sincronizador.bean.BeanMarcaIndicador;
import com.proffline.sincronizador.bean.BeanParametro;
import com.proffline.sincronizador.bean.BeanVentaCruzada;
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
import com.proffline.sincronizador.bean.Rol;
import com.proffline.sincronizador.bean.Tipologia;
import com.proffline.sincronizador.bean.Usuario;
import com.proffline.sincronizador.bean.UsuarioRol;
import com.proffline.sincronizador.bean.Vista;
import com.proffline.sincronizador.bean.VistaRol;
import com.proffline.sincronizador.bean.TipoGestion;
import com.proffline.sincronizador.conexion.Conexion;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.DAO;
import com.proffline.sincronizador.utilidades.Util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import util.ConexionSAP;

public class RFC {

    //REVISADO
    public static List<Agenda> obtenerListaAgenda(String strIdVendedor, String strFechaFin) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Agenda> listaAgenda = new ArrayList<Agenda>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("AGENDA")));
            con.RegistrarRFC("ZSD_RFC_AGENDA_DATE_NEXT");
            con.IngresarDatosInput(strIdVendedor, "IV_VENDOR_ID");
            con.IngresarDatosInput(strFechaFin, "IV_DATE");
            con.EjecutarRFC();
            con.CreaTabla("ET_AGENDA");
            List<String> lstAgenda = con.ObtenerDatosTabla();
            for (int i = 0; i < lstAgenda.size(); i++) {
                String[] valores = String.valueOf(lstAgenda.get(i)).split("¬");
                Agenda agenda = new Agenda();
                agenda.setStrVendorId(valores[2]);
                agenda.setStrVendorName(valores[3]);
                agenda.setStrBegda(valores[4]);
                agenda.setStrEndda(valores[5]);
                agenda.setStrHora(valores[6]);
                agenda.setStrCustId(valores[7]);
                agenda.setStrCustName(valores[8]);
                agenda.setStrCustAddres(valores[9]);
                agenda.setStrCustTelef(valores[10]);
                agenda.setStrCustTelefx(valores[11]);
                agenda.setStrCust1(valores[12]);
                agenda.setStrCu1(valores[13]);
                agenda.setStrCu2(valores[14]);
                agenda.setStrCustKlimk(valores[15]);
                agenda.setStrCus(valores[16]);
                agenda.setStrCustAvailable(valores[17]);
                agenda.setStrDescription(valores[18]);
                agenda.setStrST(valores[19]);
                agenda.setStrSTAT(valores[20]);
                agenda.setStrTY(valores[21]);
                agenda.setStrTYPE(valores[22]);
                agenda.setStrCust2(valores[23]);
                agenda.setStrTypeDescription(valores[24]);
                agenda.setStrEstado("000");
                listaAgenda.add(agenda);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("AGENDA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("AGENDA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaAgenda;
    }

    //REVISADO
    public static List<BloqueoEntrega> obtenerListaBloqueoEntrega() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<BloqueoEntrega> listaBloqueoEntrega = new ArrayList<BloqueoEntrega>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("BLOQUEO ENTREGA")));
            con.RegistrarRFC("ZSD_RFC_ORDER_GET_BLOQUEO");
            con.EjecutarRFC();
            con.CreaTabla("ET_TVLST");
            List<String> lstBloqueoEntrega = con.ObtenerDatosTabla();
            for (int i = 0; i < lstBloqueoEntrega.size(); i++) {
                String[] valores = String.valueOf(lstBloqueoEntrega.get(i)).split("¬");
                BloqueoEntrega bloqueoEntrega = new BloqueoEntrega();
                bloqueoEntrega.setStrCodigo(valores[3]);
                bloqueoEntrega.setStrDescripcion(valores[4]);
                listaBloqueoEntrega.add(bloqueoEntrega);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("BLOQUEO ENTREGA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("BLOQUEO ENTREGA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaBloqueoEntrega;
    }

    //REVISADO
    public static List<ClaseMaterial> obtenerListaClaseMaterial() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<ClaseMaterial> listaClaseMaterial = new ArrayList<ClaseMaterial>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CLASE MATERIAL")));
            con.RegistrarRFC("ZSD_RFC_MATERIAL_CLASES_MAT");
            con.EjecutarRFC();
            con.CreaTabla("ET_MATERIALES_CLASES");
            List<String> lstClaseMaterial = con.ObtenerDatosTabla();
            HashMap<String, String> hm = DAO.obtenerMapaHashClaseMaterial();
            for (int i = 0; i < lstClaseMaterial.size(); i++) {
                ClaseMaterial claseMaterial = new ClaseMaterial();
                String[] valores = String.valueOf(lstClaseMaterial.get(i)).split("¬");
                String codigoClase = "";
                String descripcion = valores[2];
                String codigoMaterial = "";
                try {
                    codigoClase = "" + Long.parseLong(valores[1]);
                } catch (Exception e) {
                    codigoClase = valores[1];
                }
                try {
                    codigoMaterial = "" + Long.parseLong(valores[3]);
                } catch (Exception e) {
                    codigoMaterial = valores[3];
                }
                String key = hm.get(codigoClase + "," + codigoMaterial);
                if (key == null) {
                    claseMaterial.setStrCodigoClaseMaterial(codigoClase);
                    claseMaterial.setStrDescripcionClaseMaterial(descripcion);
                    claseMaterial.setStrCodigoMaterial(codigoMaterial);
                    listaClaseMaterial.add(claseMaterial);
                }
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("CLASE MATERIAL")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("CLASE MATERIAL")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaClaseMaterial;
    }

    //REVISADO
    public static List<Cliente> obtenerListaCliente(String strIdVendedor, String strCodigoCliente, String strNombreCliente) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Cliente> listaCliente = new ArrayList<Cliente>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (!strCodigoCliente.isEmpty()) {
            strCodigoCliente = "" + Long.parseLong(strCodigoCliente);
        }
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CLIENTE")));
            con.RegistrarRFC("ZSD_RFC_CUSTOMER_FIND2_2");
            con.CreaTabla("IS_VENDOR");
            con.IngresarDatoTabla(strIdVendedor, "VENDOR_ID", 1);
            con.CreaTabla("IS_SEARCH_OPTIONS");
            con.IngresarDatoTabla(strCodigoCliente, "CUST_ID", 1);
            con.EjecutarRFC();
            con.CreaTabla("ET_CUSTOMER");
            
            List<String> lstCliente = con.ObtenerDatosTabla();
            for (int i = 1; i < lstCliente.size(); i++) {
                String[] valores = String.valueOf(lstCliente.get(i)).split("¬");
                Cliente cliente = new Cliente();
                Integer temp = Integer.parseInt(valores[1]);
                cliente.setStrIdCliente(temp.toString());
                cliente.setStrCompaniaCliente("");
                cliente.setStrNombreCliente(valores[2]);
                cliente.setStrApellidosCliente("");
                cliente.setStrEmailCliente(valores[16]);
                cliente.setStrTelefonoPrivadoCliente("");
                cliente.setStrTelefonoTrabajoCliente(valores[4]);
                cliente.setStrTelefonoMovilCliente("");
                cliente.setStrNumeroFaxCliente(valores[5]);
                cliente.setStrDireccionCliente(valores[3]);
                cliente.setStrCiudadCliente(valores[11]);
                cliente.setStrEstadoProvinciaCliente("");
                cliente.setStrCodigoPostalCliente("");
                cliente.setStrCodigoTipologia(valores[20]);
                cliente.setStrOficinaVentas(valores[10]);
                cliente.setStrGrupoVentas(valores[9]);
                cliente.setStrDescripcionTipologia(valores[21]);
                cliente.setStrMarcaBloqueoAlmacen(valores[19]);
                cliente.setIndicadorIva(valores[17]);
                cliente.setStrCodOrgVentas(valores[6]);
                cliente.setStrCodCanalDist(valores[7]);
                cliente.setStrCodSector(valores[8]);
                
                //if(valores.length <23)
                //	System.out.println("index");
                
                try{
                	 cliente.setStrClase(valores[22]);                	
                } catch (IndexOutOfBoundsException indexE){                	
                	 cliente.setStrClase("");
                }
            	try{
            		cliente.setStrCanal(valores[23]);						
				}catch(IndexOutOfBoundsException indexEx){
					cliente.setStrCanal("");
				}
                
               

                long id_cliente = Long.parseLong(valores[1]);
                strCodigoCliente = strCodigoCliente.trim();
                strNombreCliente = strNombreCliente.trim();
                String aux_gui = strNombreCliente.toLowerCase();
                String aux_sap = valores[2].toLowerCase();
                if (strCodigoCliente.isEmpty()) {
                    if (strNombreCliente.isEmpty()) {
                        listaCliente.add(cliente);
                    } else {
                        if (aux_sap.contains(aux_gui)) {
                            listaCliente.add(cliente);
                        }
                    }
                } else {
                    if (strCodigoCliente.compareTo("" + id_cliente) == 0) {
                        if (strNombreCliente.isEmpty()) {
                            listaCliente.add(cliente);
                        } else {
                            if (aux_sap.contains(aux_gui)) {
                                listaCliente.add(cliente);
                            }
                        }
                    }
                }
            }
            con.CreaTabla("ET_MSG");
            con.DesconectarSAP();
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("CLIENTE")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("CLIENTE")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaCliente;
    }

    //REVISADO
    public static List<List> obtenerListaCombo() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<List> listaCombo = new ArrayList<List>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("COMBO")));
            con.RegistrarRFC("ZSD_RFC_COMB_SELECT");
            con.EjecutarRFC();
            con.CreaTabla("T_COM");
            List combos = con.ObtenerDatosTabla();
            con.CreaTabla("T_MATCOM");
            List combosHijos = con.ObtenerDatosTabla();
            List<Combo> listaCombos = new ArrayList<Combo>();
            List<ComboDetalle> listaComboDetalles = new ArrayList<ComboDetalle>();
            for (int i = 0; i < combosHijos.size(); i++) {
                String[] valores = String.valueOf(combosHijos.get(i)).split("¬");
                String strCodCombo = "";
                try {
                    Long l = Long.parseLong(valores[1]);
                    strCodCombo = "" + l;
                } catch (Exception e) {
                    strCodCombo = valores[1];
                }
                String matnrCombo = "" + strCodCombo;
                String strCod = "";
                try {
                    Long l = Long.parseLong(valores[3]);
                    strCod = "" + l;
                } catch (Exception e) {
                    strCod = valores[3];
                }
                String matnr = "" + strCod;
                int menge = 0;
                try {
                    menge = (int) Double.parseDouble(valores[4]);
                } catch (Exception e) {
                    menge = 0;
                }
                String meins = "" + valores[5];
                ComboDetalle comboDetalle = new ComboDetalle();
                comboDetalle.setIdCombo(matnrCombo);
                comboDetalle.setCodigoMaterial(matnr);
                comboDetalle.setCantidad(menge);
                comboDetalle.setUnidad(meins);
                comboDetalle.setDescripcionMaterial("");
                comboDetalle.setPrecioNeto("0.0");
                listaComboDetalles.add(comboDetalle);
            }
            for (int i = 0; i < combos.size(); i++) {
                String[] valores = String.valueOf(combos.get(i)).split("¬");
                String strCodCombo = "";
                try {
                    Long l = Long.parseLong(valores[1]);
                    strCodCombo = "" + l;
                } catch (Exception e) {
                    strCodCombo = valores[1];
                }
                String matnr = "" + strCodCombo;
                String shortText = "" + valores[2];
                String meins = "" + valores[3];
                Combo combo = new Combo();
                combo.setStrCodigo(matnr);
                combo.setStrNombre(shortText);
                combo.setStrUnidad(meins);
                combo.setStrPrecio("0.0");
                listaCombos.add(combo);
            }
            listaCombo.add(listaCombos);
            listaCombo.add(listaComboDetalles);
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("COMBO")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("COMBO")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaCombo;
    }

    //REVISADO
    public static List<Condicion1> obtenerListaCondicion1() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Condicion1> listaCondicion1 = new ArrayList<Condicion1>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CONDICION 1")));
            con.RegistrarRFC("ZSD_RFC_DESCUENTO_PAGO");
            con.EjecutarRFC();
            con.CreaTabla("ET_DESCUENTO_PAGO");
            List<String> lstCondiciones1 = con.ObtenerDatosTabla();
            for (int i = 0; i < lstCondiciones1.size(); i++) {
                Condicion1 condicion1 = new Condicion1();
                String[] valores = String.valueOf(lstCondiciones1.get(i)).split("¬");
                int intPrioridad1 = 0;
                int intPrioridad2 = 0;
                try {
                    intPrioridad1 = Integer.parseInt(valores[2]);
                } catch (Exception e) {
                    intPrioridad1 = 0;
                }
                try {
                    intPrioridad2 = Integer.parseInt(valores[3]);
                } catch (Exception e) {
                    intPrioridad2 = 0;
                }
                condicion1.setIntPrioridadGrupo(intPrioridad1);
                condicion1.setIntPrioridadInterna(intPrioridad2);
                condicion1.setStrClaseMaterial(valores[4]);
                if (condicion1.getStrClaseMaterial().trim().equals("")) {
                    condicion1.setStrClaseMaterial("*");
                }
                condicion1.setStrCliente(valores[5]);
                if (condicion1.getStrCliente().trim().equals("")) {
                    condicion1.setStrCliente("*");
                }
                condicion1.setStrCondicionPago(valores[6]);
                if (condicion1.getStrCondicionPago().trim().equals("")) {
                    condicion1.setStrCondicionPago("*");
                }
                if (valores[8].indexOf("-") != -1) {
                    condicion1.setStrTipo("D");
                } else {
                    condicion1.setStrTipo("C");
                }
                String strCifra = "" + valores[8].substring(0, valores[8].length());
                Double d = 0d;
                if (!strCifra.isEmpty()) {
                    try {
                        d = Double.parseDouble(strCifra);
                    } catch (Exception e) {
                        d = 0d;
                    }
                }
                condicion1.setDblDscto(d);
                listaCondicion1.add(condicion1);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("CONDICION 1")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("CONDICION 1")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaCondicion1;
    }

    //REVISADO
    public static List<Condicion2> obtenerListaCondicion2() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Condicion2> listaCondicion2 = new ArrayList<Condicion2>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CONDICION 2")));
            con.RegistrarRFC("ZSD_RFC_DESCUENTO_CANAL");
            con.EjecutarRFC();
            con.CreaTabla("ET_DESCUENTO_CANAL");
            List<String> lstCondiciones2 = con.ObtenerDatosTabla();
            for (int i = 0; i < lstCondiciones2.size(); i++) {
                Condicion2 condicion2 = new Condicion2();
                String[] valores = String.valueOf(lstCondiciones2.get(i)).split("¬");
                int intPrioridad1 = 0;
                int intPrioridad2 = 0;
                try {
                    intPrioridad1 = Integer.parseInt(valores[2]);
                } catch (Exception e) {
                    intPrioridad1 = 0;
                }
                try {
                    intPrioridad2 = Integer.parseInt(valores[3]);
                } catch (Exception e) {
                    intPrioridad2 = 0;
                }
                condicion2.setIntPrioridadGrupo(intPrioridad1);
                condicion2.setIntPrioridadInterna(intPrioridad2);
                condicion2.setStrCliente(valores[4]);
                if (condicion2.getStrCliente().trim().equals("")) {
                    condicion2.setStrCliente("*");
                }
                condicion2.setStrGrupoCliente(valores[5]);
                if (condicion2.getStrGrupoCliente().trim().equals("")) {
                    condicion2.setStrGrupoCliente("*");
                }
                condicion2.setStrCanal(valores[6]);
                if (condicion2.getStrCanal().trim().equals("")) {
                    condicion2.setStrCanal("*");
                }
                if (valores[8].indexOf("-") != -1) {
                    condicion2.setStrTipo("D");
                } else {
                    condicion2.setStrTipo("C");
                }
                String strCifra = "" + valores[8].substring(0, valores[8].length());
                Double d = 0d;
                if (strCifra != null && !strCifra.isEmpty()) {
                    try {
                        d = Double.parseDouble(strCifra);
                    } catch (Exception e) {
                        d = 0d;
                    }
                }
                condicion2.setDblDscto(d);
                listaCondicion2.add(condicion2);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("CONDICION 2")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("CONDICION 2")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaCondicion2;
    }

    //REVISADO
    public static List<CondicionPago> obtenerListaCondicionPago() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<CondicionPago> listaCondicionPago = new ArrayList<CondicionPago>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CONDICION PAGO")));
            con.RegistrarRFC("ZSD_RFC_ORDER_CONDICION_PAGO");
            con.EjecutarRFC();
            con.CreaTabla("ET_COND_PAGO");
            List<String> lstCondicionPago = con.ObtenerDatosTabla();
            for (int i = 0; i < lstCondicionPago.size(); i++) {
                String[] valores = String.valueOf(lstCondicionPago.get(i)).split("¬");
                if (valores.length > 3) {
                    CondicionPago condicionPago = new CondicionPago();
                    condicionPago.setStrZTERM(valores[2]);
                    condicionPago.setStrVTEXT(valores[3]);
                    listaCondicionPago.add(condicionPago);
                }
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("CONDICION PAGO")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("CONDICION PAGO")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaCondicionPago;
    }

    //REVISADO
    public static List<CondicionPagoDetalle> obtenerListaCondicionPagoDetalle() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<CondicionPagoDetalle> listaCondicionPagoDetalle = new ArrayList<CondicionPagoDetalle>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CONDICION PAGO DETALLE")));
            con.RegistrarRFC("ZSD_RFC_REL_COND_PAGO");
            con.EjecutarRFC();
            con.CreaTabla("ET_ZTCONSTANTES");
            List<String> lstCondicionPagoDetalle = con.ObtenerDatosTabla();
            for (int i = 0; i < lstCondicionPagoDetalle.size(); i++) {
                String[] valores = String.valueOf(lstCondicionPagoDetalle.get(i)).split("¬");;
                CondicionPagoDetalle condicionPagoDetalle = new CondicionPagoDetalle();
                condicionPagoDetalle.setStrZTERM(valores[4]);
                condicionPagoDetalle.setStrZTERD(valores[7]);
                listaCondicionPagoDetalle.add(condicionPagoDetalle);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("CONDICION PAGO DETALLE")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("CONDICION PAGO DETALLE")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaCondicionPagoDetalle;
    }

//    Exception in thread "Timer-0" java.lang.OutOfMemoryError: Java heap space
//	at com.sap.mw.jco.JCO$Record.ensureBufferCapacity(JCO.java:11639)
//	at com.sap.mw.jco.rfc.MiddlewareRFC$Client.nativeExecute(Native Method)
//	at com.sap.mw.jco.rfc.MiddlewareRFC$Client.execute(MiddlewareRFC.java:1244)
//	at com.sap.mw.jco.JCO$Client.execute(JCO.java:3842)
//	at com.sap.mw.jco.JCO$Client.execute(JCO.java:3287)
//	at util.ConexionSAP.EjecutarRFC(ConexionSAP.java:179)
//	at com.proffline.sap.RFC.obtenerListaDestinatario(RFC.java:497)
//	at com.proffline.tareas.Sincronizador.sincronizarTablaDestinatario(Sincronizador.java:98)
//	at com.proffline.tareas.ProgramadorTareas$1.iniciarProcesoSincronizacion(ProgramadorTareas.java:93)
//	at com.proffline.tareas.ProgramadorTareas$1.run(ProgramadorTareas.java:45)
//	at java.util.TimerThread.mainLoop(Timer.java:555)
//	at java.util.TimerThread.run(Timer.java:505)
    public static List<Destinatario> obtenerListaDestinatario(String idVendedor) throws Exception {
//        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Destinatario> listaDestinatario = new ArrayList<Destinatario>();
        int longitud = ("" + idVendedor).length();
        if (longitud > 0) {
            idVendedor = Util.completarCeros(10, idVendedor);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
//                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("DESTINATARIO")));
                con.RegistrarRFC("ZSD_RFC_CUSTOMER_GET_SEDE2");
                con.IngresarDatosInput(idVendedor, "LV_USER_ID");
                con.EjecutarRFC();
                con.CreaTabla("ET_TEAM");
                List<String> lstDestinatario = con.ObtenerDatosTabla();
                for (int i = 0; i < lstDestinatario.size(); i++) {
                    Destinatario destinatario = new Destinatario();
                    String[] valores = String.valueOf(lstDestinatario.get(i)).split("¬");
                    destinatario.setCodigo(valores[2]);
                    destinatario.setDireccion(valores[10]);
                    destinatario.setIdCliente(valores[9]);
                    listaDestinatario.add(destinatario);
                }
                con.DesconectarSAP();
//                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("DESTINATARIO")));
            } else {
//                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("DESTINATARIO")));
            }
        }
//        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaDestinatario;
    }

    //REVISADO
    public static List<Dispositivo> obtenerListaDispositivo() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Dispositivo> listaDispositivo = new ArrayList<Dispositivo>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("DISPOSITIVO")));
            con.RegistrarRFC("ZSD_RFC_DISPOSIT_LIST_DIS_USU");
            con.IngresarDatosInput("", "V_USU");
            con.EjecutarRFC();
            con.CreaTabla("T_LIST_DISPO_USU");
            @SuppressWarnings("rawtypes")
            List<String> lstDispositivo = con.ObtenerDatosTabla();
            for (int i = 0; i < lstDispositivo.size(); i++) {
                String[] valores = String.valueOf(lstDispositivo.get(i)).split("¬");
                Dispositivo dispositivo = new Dispositivo();
                dispositivo.setStrIdDispositivo(valores[2]);
                dispositivo.setStrTipoDispositivo(valores[3]);
                dispositivo.setStrNumeroSerieDispositivo(valores[4]);
                dispositivo.setStrCodigoActivo(valores[5]);
                dispositivo.setStrSimm(valores[6]);
                dispositivo.setStrImei(valores[7]);
                dispositivo.setStrNumeroTelefono(valores[8]);
                dispositivo.setStrNumeroSeguro(valores[9]);
                dispositivo.setStrIdUsuario(valores[10]);
                dispositivo.setStrNomUsuario(valores[11]);
                dispositivo.setStrDispositivoRelacionado(valores[12]);
                dispositivo.setStrEstado(valores[13]);
                dispositivo.setStrObservacion(valores[14]);
                dispositivo.setStrUsuReg(valores[15]);
                dispositivo.setStrFecReg(Util.convierteFecha(valores[16]));
                dispositivo.setStrHorReg(valores[17].substring(11, 19));
                if (valores[18].length() > 0) {
                    dispositivo.setStrUsuUpd(valores[18]);
                } else {
                    dispositivo.setStrUsuUpd("");
                }
                if (valores[19].equals("null")) {
                    dispositivo.setStrFecUpd("");
                } else {
                    dispositivo.setStrFecUpd(Util.convierteFecha(valores[19]));
                }
                if (valores[20].equals("Thu Jan 01 00:00:00 COT 1970")) {
                    dispositivo.setStrHorUpd("00:00:00");
                } else {
                    dispositivo.setStrHorUpd(valores[20].substring(11, 19));
                }
                listaDispositivo.add(dispositivo);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("DISPOSITIVO")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("DISPOSITIVO")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaDispositivo;
    }

    //REVISADO
    public static List<Empleado> obtenerListaEmpleado(String idSupervisor) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("EMPLEADO")));
            con.RegistrarRFC("ZSD_RFC_USER_GET_TEAM");
            con.CreaTabla("IS_USER_INFO");
            con.IngresarDatoTabla(idSupervisor, "VENDOR_ID", 1);
            con.EjecutarRFC();
            con.CreaTabla("ET_TEAM");
            @SuppressWarnings("rawtypes")
            List<String> lstEmpleado = con.ObtenerDatosTabla();
            for (int i = 0; i < lstEmpleado.size(); i++) {
                String[] valores = String.valueOf(lstEmpleado.get(i)).split("¬");
                Empleado empleado = new Empleado();
                empleado.setStrIdEmpleado(valores[21]);
                empleado.setStrNombreEmpleado(valores[4]);
                empleado.setStrIdSupervisor(idSupervisor);
                listaEmpleado.add(empleado);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("EMPLEADO")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("EMPLEADO")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaEmpleado;
    }

    public static List<EmpleadoCliente> obtenerListaEmpleadoCliente(String idSupervisor) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<EmpleadoCliente> listaEmpleadoCliente = new ArrayList<EmpleadoCliente>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("EMPLEADO CLIENTE")));
            con.RegistrarRFC("ZSD_RFC_OLD_CUSTOMER_FIND2");
            con.IngresarDatosInput(idSupervisor, "ID_SUPERVISOR");
            con.EjecutarRFC();
            con.CreaTabla("ET_KNVP");
            List<String> lstEmpleadoCliente = con.ObtenerDatosTabla();
            for (int i = 0; i < lstEmpleadoCliente.size(); i++) {
                String cadena = String.valueOf(lstEmpleadoCliente.get(i)) + "¬";
                String[] valores = cadena.split("¬");
                EmpleadoCliente empleadoCliente = new EmpleadoCliente();
                empleadoCliente.setStrIdCliente(valores[1]);
                empleadoCliente.setStrIdEmpleado(valores[3]);
                listaEmpleadoCliente.add(empleadoCliente);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("EMPLEADO CLIENTE")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("EMPLEADO CLIENTE")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaEmpleadoCliente;
    }

    //REVISADO
    public static List<Feriado> obtenerListaFeriado() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Feriado> listaFeriado = new ArrayList<Feriado>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("FERIADO")));
            con.RegistrarRFC("ZSD_RFC_VISIT_FERIADOS");
            con.EjecutarRFC();
            con.CreaTabla("T_FERIADO");
            @SuppressWarnings("rawtypes")
            List<String> lstFeriado = con.ObtenerDatosTabla();
            for (int i = 0; i < lstFeriado.size(); i++) {
                String cadena = String.valueOf(lstFeriado.get(i)) + "¬";
                String[] valores = cadena.split("¬");
                Feriado feriado = new Feriado();
                feriado.setStrSigFeriado(valores[2]);
                feriado.setStrMesFeriado(valores[3]);
                feriado.setStrDiaFeriado(valores[4]);
                feriado.setStrOriFeriado(valores[10]);
                listaFeriado.add(feriado);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("FERIADO")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("FERIADO")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaFeriado;
    }

    //REVISADO
    public static List<Jerarquia> obtenerListaJerarquiaPorNivel(String nivel) throws Exception {
        List<Jerarquia> listaJerarquia = new ArrayList<Jerarquia>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            con.RegistrarRFC("ZSD_RFC_HIERARCHY_GET");
            con.IngresarDatosInput("", "IV_HERARCHY_PARENT_ID");
            con.IngresarDatosInput(nivel, "IV_LEVEL");
            con.EjecutarRFC();
            con.CreaTabla("T_HERARCHY");
            @SuppressWarnings("rawtypes")
            List<String> lstJerarquia = con.ObtenerDatosTabla();
            for (int i = 0; i < lstJerarquia.size(); i++) {
                String[] valores = String.valueOf(lstJerarquia.get(i)).split("¬");
                String PRODH = valores[1] == null ? "" : valores[1];
                String S = valores[2] == null ? "" : valores[2];
                String VTEXT = valores[3] == null ? "" : valores[3];
                String ZZSEQ = valores[4] == null ? "" : valores[4];
                String ICON = valores[5] == null ? "" : valores[5];
                String I = valores[6] == null ? "" : valores[6];
                String CELL_DESING = valores[7] == null ? "" : valores[7];
                Jerarquia jerarquia = new Jerarquia();
                jerarquia.setStrPRODH(PRODH);
                jerarquia.setStrS(S);
                jerarquia.setStrVTEXT(VTEXT);
                jerarquia.setStrZZSEQ(ZZSEQ);
                jerarquia.setStrICON(ICON);
                jerarquia.setStrI(I);
                jerarquia.setStrCellDesign(CELL_DESING);
                listaJerarquia.add(jerarquia);
            }
            con.DesconectarSAP();
        }
        return listaJerarquia;
    }

    //REVISADO
    public static List<Jerarquia> obtenerListaJerarquia() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Jerarquia>[] arregloJerarquia = new List[5];
        List<Jerarquia> listJerarquia = new ArrayList<Jerarquia>();
        List<Jerarquia> listPadreTemp = null;
        for (int i = 0; i < arregloJerarquia.length; i++) {
            arregloJerarquia[i] = obtenerListaJerarquiaPorNivel(i + "");
        }
        boolean bandera = false;
        boolean hereda = false;
        if (arregloJerarquia != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("JERARQUIA")));
            for (List<Jerarquia> nivelJerarquia : arregloJerarquia) {
                if (bandera) {
                    for (Jerarquia beanHijo : nivelJerarquia) {
                        for (Jerarquia beanPadre : listPadreTemp) {
                            if (beanHijo.getStrPRODH().startsWith(
                                    beanPadre.getStrPRODH())) {
                                hereda = true;
                                beanHijo.setStrIdPadre(beanPadre.getStrPRODH());
                                break;
                            }
                        }
                        if (hereda) {
                            listJerarquia.add(beanHijo);
                            hereda = false;
                        }
                    }
                    listPadreTemp = nivelJerarquia;
                } else {
                    for (Jerarquia bean : nivelJerarquia) {
                        String prodh = bean.getStrPRODH();
                        bean.setStrPRODH(prodh);
                        bean.setStrIdPadre("");
                        listJerarquia.add(bean);
                    }
                    listPadreTemp = nivelJerarquia;
                    bandera = true;
                }
            }
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("JERARQUIA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("JERARQUIA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listJerarquia;
    }

    //REVISADO
    public static List<Material> obtenerListaMaterial(String jerarquia) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Material> listaMaterial = new ArrayList<Material>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("MATERIAL")));
            con.RegistrarRFC("ZSD_RFC_MATERIAL_GET_PRI_LIST5");
            con.CreaTabla("IS_SEARCH_OPTIONS");
            con.IngresarDatoTabla("", "ID", 1);
            con.IngresarDatoTabla(jerarquia + "*", "HIERARCHY", 1);
            con.IngresarDatoTabla("", "SHORT_TEXT", 1);
            con.IngresarDatoTabla("", "NORMT", 1);
            con.EjecutarRFC();
            con.CreaTabla("ET_MATERIAL");
            List ur = con.ObtenerDatosTabla();
            HashMap<String, String> hm = DAO.obtenerMapaHashMaterial();
            for (int i = 0; i < ur.size(); i++) {
                String cadena = String.valueOf(ur.get(i));
                String[] valores = cadena.split("¬");
                String LAEDA = valores[20];
                Material material = new Material();
                material.setStrMATNR("" + Integer.parseInt(valores[1]));
                material.setStrStock("" + Double.parseDouble(valores[2]));
                material.setStrSU(valores[3]);
                material.setStrShortText(valores[4].replaceAll("'", "''"));
                material.setStrTextLine(valores[5].replaceAll("'", "''"));
                material.setStrTargetQTY("" + Double.parseDouble(valores[6]));
                material.setStrPrice1("" + Double.parseDouble(valores[7]));
                material.setStrPrice2("" + Double.parseDouble(valores[8]));
                material.setStrPrice3("" + Double.parseDouble(valores[9]));
                material.setStrPrice4("" + Double.parseDouble(valores[10]));
                material.setStrPRDHA(valores[11]);
                material.setStrHER(valores[12]);
                material.setStrNORMT(valores[13]);
                material.setStrZZORDCO(LAEDA);
                material.setStrCellDesign(valores[15]);
                material.setMTART(valores[16]);
                material.setStrTypeMat(valores[17]);
                material.setStrGrupoCompra(valores[18]);
                material.setStrSt1(valores[19]);
                material.setStrFec_Ing(valores[21]);
                material.setStrCosto(valores[22]);
                material.setStrMargen_Obj(valores[23]);
                String temporal = hm.get(material.getStrMATNR());
                if (temporal == null) {
                    material.setAccionEnBaseDatos(1);//AGREGAR MATERIAL
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    Date fechaSQLite = sdf.parse(temporal, new ParsePosition(0));
                    Date fechaSAP = sdf.parse(LAEDA, new ParsePosition(0));
                    if (fechaSQLite == null || fechaSQLite.before(fechaSAP)) {
                        material.setAccionEnBaseDatos(0);//ACTUALIZA MATERIAL
                    }
                }
                listaMaterial.add(material);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("MATERIAL")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaMaterial;
    }
    public static List<MaterialNuevo> obtenerListaMaterialNuevo(String jerarquia) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<MaterialNuevo> listaMaterialNuevo = new ArrayList<MaterialNuevo>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("MATERIAL NUEVO")));
            con.RegistrarRFC("ZSD_RFC_MATERIAL_NEW");
            
            con.EjecutarRFC();
            con.CreaTabla("ET_MATERIAL");//TODO cambiar 
            List<String> ur = con.ObtenerDatosTabla();
            for (int i = 0; i < ur.size(); i++) {
                String cadena = String.valueOf(ur.get(i));
                String[] valores = cadena.split("¬");
                MaterialNuevo material = new MaterialNuevo();
                material.setStrMATNR("" + Integer.parseInt(valores[2]));
                material.setStrZZORDCO(Util.convierteCadenaAFormatoYYYYMMDD(valores[3]));
                material.setStrNORMT(valores[4]);
                listaMaterialNuevo.add(material);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("MATERIAL")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaMaterialNuevo;
    }
    public static List<BeanParametro> obtenerParametrosConstantes(){
    	VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
    	List<BeanParametro> listaParametro = new ArrayList<BeanParametro>();
    	BeanParametro bp;
    	try {
			ConexionSAP con = Conexion.obtenerConexionSAP();
			if (con != null) {
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CONSTANTES")));
				con.RegistrarRFC("ZSD_RFC_GET_CONST_PROFFLINE");
				con.EjecutarRFC();
				con.CreaTabla("ET_CONST");
				List ur = con.ObtenerDatosTabla();
				if(ur.size() > 0){
					for (int i = 0; i < ur.size(); i++) {
						String cadena = String.valueOf(ur.get(i));
						String[] values = cadena.split("¬");
						bp = new BeanParametro();
						bp.setStrModulo(values[2].trim());
						bp.setStrNombrePrograma(values[3].trim());
						bp.setStrNombreCampo(values[4].trim());
						bp.setSecuencia(Integer.parseInt(values[5].trim()));
						bp.setStrOpcion(values[6].trim());
						bp.setStrValorUno(values[7].trim());
						bp.setStrValorDos(values[8].trim());
						bp.setStrNombreUsuario(values[9].trim());
						bp.setStrFecha("");
						listaParametro.add(bp);
					}
					con.DesconectarSAP();
					return listaParametro;
				}else {
					return null;
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    //REVISADO
    public static List<MaterialStock> obtenerListaMaterialStock() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<MaterialStock> listaMaterialStock = new ArrayList<MaterialStock>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("MATERIAL STOCK")));
            con.RegistrarRFC("ZSD_RFC_MATERIAL_GET_MAT_STOCK");
            con.IngresarDatosInput("", "P_MATNR");
            con.EjecutarRFC();
            con.CreaTabla("ET_MATERIAL_STOCK");
            List<String> lstMaterialStock = con.ObtenerDatosTabla();
            for (int i = 0; i < lstMaterialStock.size(); i++) {
                MaterialStock materialStock = new MaterialStock();
                String[] valores = String.valueOf(lstMaterialStock.get(i)).split("¬");
                String MATNR = "" + Integer.parseInt(valores[1]);
                String STOCK = "" + Double.parseDouble(valores[2]);
                materialStock.setCodigo(MATNR);
                materialStock.setStock(STOCK);
                listaMaterialStock.add(materialStock);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL STOCK")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("MATERIAL STOCK")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaMaterialStock;
    }

    //REVISADO
    public static List<Rol> obtenerListaRol() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Rol> listaRol = new ArrayList<Rol>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("ROL")));
            con.RegistrarRFC("ZSD_RFC_PROFILE_LIST_PRO");
            con.EjecutarRFC();
            con.CreaTabla("T_PRO_LIST");
            @SuppressWarnings("rawtypes")
            List<String> lstRol = con.ObtenerDatosTabla();
            for (int i = 0; i < lstRol.size(); i++) {
                Rol rol = new Rol();
                String[] valores = String.valueOf(lstRol.get(i)).split("¬");
                rol.setStrMandante(valores[1]);
                rol.setStrIdRol(valores[2]);
                rol.setStrNomRol(valores[3]);
                listaRol.add(rol);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("ROL")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("ROL")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaRol;
    }

    //REVISADO
    public static List<Tipologia> obtenerListaTipologia() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Tipologia> listaTipologia = new ArrayList<Tipologia>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("TIPOLOGIA")));
            con.RegistrarRFC("ZSD_RFC_ORDER_GET_TIPO_MAT");
            con.EjecutarRFC();
            con.CreaTabla("ET_T134T");
            List<String> lstTipologia = con.ObtenerDatosTabla();
            for (int i = 0; i < lstTipologia.size(); i++) {
                Tipologia tipologia = new Tipologia();
                String[] valores = String.valueOf(lstTipologia.get(i)).split("¬");
                tipologia.setStrMTAR(valores[3]);
                tipologia.setStrMTBEZ(valores[4]);
                listaTipologia.add(tipologia);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("TIPOLOGIA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("TIPOLOGIA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaTipologia;
    }

    //REVISADO
    public static List<Usuario> obtenerListaUsuario(String strIdUsuario) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("USUARIO")));

            con.RegistrarRFC("ZSD_RFC_USER_GET_VENDOR");
            con.EjecutarRFC();
            con.CreaTabla("TI_DIVISION");
            @SuppressWarnings("rawtypes")
            List lstDivision = con.ObtenerDatosTabla();
            HashMap<String, String> mapResultadoUsuarioDivision = new HashMap<String, String>();
            for (int i = 0; i < lstDivision.size(); i++) {
                String cadena = String.valueOf(lstDivision.get(i));
                String[] temporal = cadena.split("¬");
                mapResultadoUsuarioDivision.put(Long.parseLong(temporal[1]) + "", temporal[2]);
            }

            con.RegistrarRFC("ZSD_RFC_USER_FIND_USER_V1");
            con.IngresarDatosInput(strIdUsuario, "IV_USER_ID");
            con.EjecutarRFC();
            con.CreaTabla("T_USER");
            @SuppressWarnings("rawtypes")
            List<String> lstUsuario = con.ObtenerDatosTabla();
            for (int i = 0; i < lstUsuario.size(); i++) {
                String[] valores = String.valueOf(lstUsuario.get(i)).split("¬");
                Usuario usuario = new Usuario();
                usuario.setStrMandante(valores[1]);
                usuario.setStrIdUsuario(valores[2]);
                usuario.setStrNomUsuario(valores[3]);
                usuario.setStrClaUsuario(valores[5]);
                usuario.setStrFecCre(valores[6]);
                usuario.setStrFecUltAccSis(valores[7]);
                usuario.setStrHorUltAccSis(valores[8]);
                usuario.setStrIntento(valores[15]);
                usuario.setStrBloqueado(valores[16]);
                if (valores[9].trim().equals("X")) {
                    usuario.setStrCambioClave("0");
                } else {
                    usuario.setStrCambioClave("1");
                }
                usuario.setStrIdentificacion(valores[21]);
                usuario.setStrUsuario(valores[23]);
                usuario.setStrDivision(mapResultadoUsuarioDivision.get(Long.parseLong(valores[21]) + ""));
                listaUsuario.add(usuario);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("USUARIO")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("USUARIO")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaUsuario;
    }

    //REVISADO
    public static List<UsuarioRol> obtenerListaUsuarioRol() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<UsuarioRol> listaUsuarioRol = new ArrayList<UsuarioRol>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("USUARIO ROL")));
            con.RegistrarRFC("ZSD_RFC_USER_LIST_UROLE");
            con.EjecutarRFC();
            con.CreaTabla("T_LIST_USER_ROLE");
            @SuppressWarnings("rawtypes")
            List<String> lstUsuarioRol = con.ObtenerDatosTabla();
            for (int i = 0; i < lstUsuarioRol.size(); i++) {
                UsuarioRol usuarioRol = new UsuarioRol();
                String[] valores = String.valueOf(lstUsuarioRol.get(i)).split("¬");
                usuarioRol.setStrMandante(valores[1]);
                usuarioRol.setStrIdUsu(valores[2]);
                usuarioRol.setStrIdRol(valores[3]);
                listaUsuarioRol.add(usuarioRol);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("USUARIO ROL")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("USUARIO ROL")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaUsuarioRol;
    }
    
    //REVISADO
    public static List<TipoGestion> obtenerListaTipoGestion() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<TipoGestion> tipoGestion = new ArrayList<TipoGestion>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("TIPO GESTION")));
            con.RegistrarRFC("ZSD_RFC_GET_TPO_GESTION");
            con.EjecutarRFC();
            con.CreaTabla("ET_T176T");
            @SuppressWarnings("rawtypes")
            List<String> lstTipoGestion = con.ObtenerDatosTabla();
            for (int i = 0; i < lstTipoGestion.size(); i++) {
                TipoGestion tipoGestionObj = new TipoGestion();
                String[] valores = String.valueOf(lstTipoGestion.get(i)).split("¬");
                tipoGestionObj.setMan(valores[1]);
                tipoGestionObj.setSp(valores[2]);
                tipoGestionObj.setBsar(valores[3]);
                tipoGestionObj.setVtext(valores[4]);
                tipoGestion.add(tipoGestionObj);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("TIPO GESTION")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("TIPO GESTION")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return tipoGestion;
    }//End RFC tipo Gestion

    //REVISADO
    public static List<Vista> obtenerListaVista() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<Vista> listaVista = new ArrayList<Vista>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("VISTA")));
            con.RegistrarRFC("ZSD_RFC_VIEWS_LIST_VIEW");
            con.IngresarDatosInput("", "IV_PRO_ID");
            con.EjecutarRFC();
            con.CreaTabla("T_VIEW_LIST");
            @SuppressWarnings("rawtypes")
            List<String> lstVista = con.ObtenerDatosTabla();
            final Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            for (int i = 0; i < lstVista.size(); i++) {
                Vista vista = new Vista();
                String[] valores = String.valueOf(lstVista.get(i)).split("¬");
                vista.setStrNomVis(valores[1]);
                vista.setStrDesVis(valores[2]);
                vista.setStrMandante(props.getProperty("sap.mandante").trim());
                listaVista.add(vista);
            }
            in.close();
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("VISTA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("VISTA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaVista;
    }

    //REVISADO
    public static List<VistaRol> obtenerListaVistaRol() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<VistaRol> listaVistaRol = new ArrayList<VistaRol>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("VISTA ROL")));
            con.RegistrarRFC("ZSD_RFC_VIEW_LIST");
            con.EjecutarRFC();
            con.CreaTabla("T_LIST_VIEW_LIST");
            List<String> lstVistaRol = con.ObtenerDatosTabla();
            for (int i = 0; i < lstVistaRol.size(); i++) {
                String[] valores = String.valueOf(lstVistaRol.get(i)).split("¬");
                VistaRol vistaRol = new VistaRol();
                vistaRol.setMandante(valores[1]);
                vistaRol.setIdRol(valores[2]);
                vistaRol.setNombreVista(valores[3]);
                listaVistaRol.add(vistaRol);
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("VISTA ROL")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("VISTA ROL")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaVistaRol;
    }

    
    //TODO
    @SuppressWarnings("rawtypes")
	public static List<List<Material>> listaMaterialesVentaCruzada(String strCodVendedor) throws Exception {
    	VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
		List<List<Material>> listaFinal = new ArrayList<List<Material>>();
		List<Material> listaTemp = null;
		ConexionSAP con = Conexion.obtenerConexionSAP();
		if (con != null) {
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("MATERIAL VENTA CRUZADA")));	
			listaTemp = new ArrayList<Material>();
			con.RegistrarRFC("ZSD_RFC_VTACRUZ_MATNR");
			con.IngresarDatosInput(strCodVendedor, "V_KUNRG");
			con.EjecutarRFC();
			con.CreaTabla("ET_MATERIAL");
			List ur1 = con.ObtenerDatosTabla();
			con.DesconectarSAP();
			String[] values = null;
			Material bm = null;
			for (int i = 0; i < ur1.size(); i++) {
				String cadena = String.valueOf(ur1.get(i));
				values = cadena.split("¬");
				bm = new Material();
				bm.setStrMATNR("" + Integer.parseInt(values[2]));
				bm.setStrVentasAcumulado("" + Double.parseDouble(values[3]));
				bm.setStrVentasPromedio("" + Double.parseDouble(values[4]));
				bm.setStrCliente("" + Integer.parseInt(values[1]));
				bm.setStrVentaReal("" + Double.parseDouble(values[5]));
				listaTemp.add(bm);
			}
			listaFinal.add(listaTemp);
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL VENTA CRUZADA")));
		} else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL VENTA CRUZADA")));
		}
		return listaFinal;
	}
	
	public static List<BeanVentaCruzada> listaCategoriaVentaCruzada(String strCodVendedor) throws Exception{	
		VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
		List<BeanVentaCruzada> listaTemp = new ArrayList();
		ConexionSAP con = Conexion.obtenerConexionSAP();
		if (con != null) {
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("VENTA CRUZADA")));	
			con.RegistrarRFC("ZSD_RFC_VTACRUZ_CATEG");
			con.IngresarDatosInput(strCodVendedor, "V_KUNRG");
			con.EjecutarRFC();
			con.CreaTabla("ET_MATERIAL");
			List ur1 = con.ObtenerDatosTabla();
			con.DesconectarSAP();
			String[] values = null;
			BeanVentaCruzada bm = null;
			for (int i = 0; i < ur1.size(); i++) {
				String cadena = String.valueOf(ur1.get(i));
				values = cadena.split("¬");
				bm = new BeanVentaCruzada();
				bm.setStrAnio("" + Integer.parseInt(values[1]));
				bm.setStrCodCliente("" + Integer.parseInt(values[2]));
				bm.setStrCategoria(values[3]);
				bm.setDblOportunidad(Double.parseDouble(values[4]));
				bm.setDblObjetivo(Double.parseDouble(values[5]));
				bm.setDblVentaReal(Double.parseDouble(values[6]));
				Double cump = bm.getDblVentaReal()/bm.getDblObjetivo();
				bm.setDblCumplimiento(cump);
				listaTemp.add(bm);
			}
			
			
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL VENTA CRUZADA")));
		}else{
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL VENTA CRUZADA")));
		}
		return listaTemp;
	}
    
    public static List<List<Material>> obtenerListaMaterialConsultaDinamica() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<List<Material>> listMaterialConsultaDinamica = new ArrayList<List<Material>>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("MATERIAL CONSULTA DINAMICA")));
            con.RegistrarRFC("ZSD_RFC_MATERIAL_BUSQCON1");
            con.EjecutarRFC();
            con.CreaTabla("ET_MAT_BUSQCON1");
            List lstMaterial1 = con.ObtenerDatosTabla();
            con.CreaTabla("ET_STRUCT_DSCTO");
            List lstMaterial2 = con.ObtenerDatosTabla();
            con.CreaTabla("ET_CLASS_MAT");
            List lstMaterial3 = con.ObtenerDatosTabla();
            con.CreaTabla("ET_MARCA_MAT");
            List lstMaterial4 = con.ObtenerDatosTabla();
            List<Material> listaMateriales1 = new ArrayList<Material>();
            List<Material> listaMateriales2 = new ArrayList<Material>();
            List<Material> listaMateriales3 = new ArrayList<Material>();
            String codigo = "";
            String tipo = "";
            Material m = null;
            List<Material> lstMaterial = null;
            for (int i = 0; i < lstMaterial1.size(); i++) {
                String[] valores = String.valueOf(lstMaterial1.get(i)).split("¬");
                m = new Material();
                String strCodigoMaterial = "0";
                try {
                    strCodigoMaterial = "" + Integer.parseInt(valores[1]);
                } catch (Exception e) {
                    strCodigoMaterial = valores[1];
                }
                m.setStrMATNR(strCodigoMaterial);
                if (valores[3].trim().equals("005")) {
                    listaMateriales1.add(m);
                }
                if (valores[3].trim().equals("004")) {
                    listaMateriales2.add(m);
                }
                if (valores[3].trim().equals("006")) {
                    listaMateriales3.add(m);
                }
            }
            String c1 = "";
            String c2 = "";
            String c3 = "";
            String c4 = "";
            String c5 = "";
            String codigoJerarquia = "";
            lstMaterial = new ArrayList<Material>();
            for (int i = 0; i < lstMaterial2.size(); i++) {
                String[] valores = String.valueOf(lstMaterial2.get(i)).split("¬");
                c1 = "" + valores[2];
                c2 = "" + valores[3];
                c3 = "" + valores[4];
                c4 = "" + valores[5];
                c5 = "" + valores[6];
                tipo = valores[10];
                codigoJerarquia = c1 + c2 + c3 + c4 + c5;
                lstMaterial = DAO.obtenerListaDePrecios(codigoJerarquia);
                if (tipo.trim().equals("005")) {
                    listaMateriales1.addAll(lstMaterial);
                }
                if (tipo.trim().equals("004")) {
                    listaMateriales2.addAll(lstMaterial);
                }
                if (tipo.trim().equals("006")) {
                    listaMateriales3.addAll(lstMaterial);
                }
            }
            lstMaterial = new ArrayList<Material>();
            for (int i = 0; i < lstMaterial3.size(); i++) {
                String[] valores = String.valueOf(lstMaterial3.get(i)).split("¬");
                codigo = valores[1];
                tipo = valores[3];
                lstMaterial = DAO.obtenerListaMaterialesPorClaseMaterial(codigo);
                if (tipo.trim().equals("005")) {
                    listaMateriales1.addAll(lstMaterial);
                }
                if (tipo.trim().equals("004")) {
                    listaMateriales2.addAll(lstMaterial);
                }
                if (tipo.trim().equals("006")) {
                    listaMateriales3.addAll(lstMaterial);
                }
            }
            lstMaterial = new ArrayList<Material>();
            for (int i = 0; i < lstMaterial4.size(); i++) {
                String[] valores = String.valueOf(lstMaterial4.get(i)).split("¬");
                codigo = valores[1];
                tipo = valores[3];
                lstMaterial = DAO.obtenerListaMaterialPorMarca(codigo);
                if (tipo.trim().equals("005")) {
                    listaMateriales1.addAll(lstMaterial);
                }
                if (tipo.trim().equals("004")) {
                    listaMateriales2.addAll(lstMaterial);
                }
                if (tipo.trim().equals("006")) {
                    listaMateriales3.addAll(lstMaterial);
                }
            }
            listMaterialConsultaDinamica.add(listaMateriales1);
            listMaterialConsultaDinamica.add(listaMateriales2);
            listMaterialConsultaDinamica.add(listaMateriales3);
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MATERIAL CONSULTA DINAMICA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("MATERIAL CONSULTA DINAMICA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listMaterialConsultaDinamica;
    }

    //ENVIAR 01 PARA LISTAR TODAS LAS FORMAS DE PAGO
    //ENVIAR 02 PARA ANTICIPOS
    public static List<FormaPago> obtenerListaFormaPago(String tipoFormasPago) throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<FormaPago> listaFormaPago = new ArrayList<FormaPago>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            if (tipoFormasPago.equals("01")) { // Forma de Pago Cobranzas
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("FORMA PAGO COBRANZA")));
            } else { // Forma de Pago Anticipos
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("FORMA PAGO ANTICIPO")));
            }
            con.RegistrarRFC("ZSD_RFC_PAYMENT_TYPES_GET_LIST");
            con.IngresarDatosInput(tipoFormasPago, "IV_PAYMENT");
            con.EjecutarRFC();
            con.CreaTabla("T_PAYMENT_TYPES");
            @SuppressWarnings("unchecked")
            List<String> lstFormaPago = con.ObtenerDatosTabla();
            if (lstFormaPago.size() > 0) {
                for (int i = 0; i < lstFormaPago.size(); i++) {
                    String[] valores = String.valueOf(lstFormaPago.get(i)).split("[¬]");
                    FormaPago formaPago = new FormaPago();
                    if (valores.length == 0) {
                        formaPago.setIdFormaPago("");
                        formaPago.setDescripcionFormaPago("");
                    } else {
                        formaPago.setIdFormaPago(valores[1].trim());
                        formaPago.setDescripcionFormaPago(valores[2].trim());
                    }
                    listaFormaPago.add(formaPago);
                }
            }
            con.DesconectarSAP();
            if (tipoFormasPago.equals("01")) { // Forma de Pago Cobranzas
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("FORMA PAGO COBRANZA")));
            } else { // Forma de Pago Anticipos
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("FORMA PAGO ANTICIPO")));
            }
        } else {
            if (tipoFormasPago.equals("01")) { // Forma de Pago Cobranzas
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("FORMA PAGO COBRANZA")));
            } else { // Forma de Pago Anticipos
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("FORMA PAGO ANTICIPO")));
            }
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaFormaPago;
    }

    public static List<BancoPromesa> obtenerListaBancoPromesa() throws Exception {
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
        List<BancoPromesa> listaBancoPromesa = new ArrayList<BancoPromesa>();
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("BANCO PROMESA")));
            con.RegistrarRFC("ZSD_RFC_COMPANY_GET_BNK_ACCOUN");
            con.EjecutarRFC();
            con.CreaTabla("T_BNK_ACCOUNT");
            @SuppressWarnings("unchecked")
            List<String> lstBancoPromesa = con.ObtenerDatosTabla();
            if (lstBancoPromesa.size() > 0) {
                for (int i = 0; i < lstBancoPromesa.size(); i++) {
                    String[] valores = String.valueOf(lstBancoPromesa.get(i)).split("[¬]");
                    BancoPromesa bancoPromesa = new BancoPromesa();
                    if (valores.length == 0) {
                        bancoPromesa.setIdBancoPromesa("");
                        bancoPromesa.setDescripcionBancoPromesa("");
                        bancoPromesa.setTipoRecaudo("N");
                    } else if (valores.length == 6) {
                        bancoPromesa.setIdBancoPromesa(valores[2].trim());
                        bancoPromesa.setDescripcionBancoPromesa(valores[4].trim());
                        bancoPromesa.setTipoRecaudo(valores[5].trim());
                    } else {
                        bancoPromesa.setIdBancoPromesa(valores[2].trim());
                        bancoPromesa.setDescripcionBancoPromesa(valores[4].trim());
                        bancoPromesa.setTipoRecaudo("");
                    }
                    listaBancoPromesa.add(bancoPromesa);
                }
            }
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("BANCO PROMESA")));
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("BANCO PROMESA")));
        }
        VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
        return listaBancoPromesa;
    }

    public static BeanMarcaIndicador getMarcaIndicador(String vendedor) throws Exception {
        ConexionSAP con = Conexion.obtenerConexionSAP();
        if (con != null) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("MARCA ESTRATEGICA E INDICADORES")));
            con.RegistrarRFC("ZSD_RFC_GET_MARCA_ESTRATEGICA");
            con.IngresarDatosInput(vendedor, "PI_VENDEDOR");
//            con.IngresarDatosInput("20160320", "PI_FECHA");//YYYYMMDD
            con.EjecutarRFC();
            con.CreaTabla("PT_MARCAS");
            List<String> listMarcas = con.ObtenerDatosTabla();
            con.CreaTabla("PT_INDICADORES");
            List<String> listIndicadores = con.ObtenerDatosTabla();
            con.CreaTabla("PT_DENOMINACION");
            List<String> listNombres = con.ObtenerDatosTabla();
            con.CreaTabla("PT_MARCAVENDEDOR");
            List<String> listMarcaVendedor = con.ObtenerDatosTabla();
            con.DesconectarSAP();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP("MARCA ESTRATEGICA E INDICADORES")));
            return obtenerData(listMarcas, listIndicadores, listNombres, listMarcaVendedor);
        } else {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP("MARCA ESTRATEGICA E INDICADORES")));
        }
        return null;
    }

    private static BeanMarcaIndicador obtenerData(List<String> listMarcas, List<String> listIndicadores, List<String> listNombres, List<String> listMarcaVendedor) {
        if (listMarcas.size() > 0 || listIndicadores.size() > 0) {
            List<MarcaEstrategica> marcas = new ArrayList<MarcaEstrategica>();
            for (String s : listMarcas) {
                MarcaEstrategica ms = new MarcaEstrategica();
                String[] values = s.split("¬");
                ms.setCodigoCliente(values[1]);
                ms.setMarca(values[2]);
                ms.setPresupuesto(values[3]);
                ms.setAcumulado(values[4]);
                ms.setFecha(Util.convierteFecha(values[5]));
                marcas.add(ms);
            }
            List<Indicador> indicadores = new ArrayList<Indicador>();
            for (String s : listIndicadores) {
                Indicador i = new Indicador();
                String[] values = s.split("¬");
                i.setCodigoCliente(values[1]);
                i.setMonto(values[2]);
                i.setAcumudalo(values[3]);
                i.setEstatus(values[4]);
                indicadores.add(i);
            }
            List<NombreMarcaEstrategica> nombres = new ArrayList<NombreMarcaEstrategica>();
            for (String s : listNombres) {
                NombreMarcaEstrategica n = new NombreMarcaEstrategica();
                String[] value = s.split("¬");
                n.setMarca(value[1]);
                n.setNombre(value[2]);
                nombres.add(n);
            }
            List<MarcaVendedor> marcavend = new ArrayList<MarcaVendedor>();
            for (String s : listMarcaVendedor) {
                MarcaVendedor ms = new MarcaVendedor();
                String[] values = s.split("¬");
                ms.setCodigoCliente(values[1]);
                ms.setMarca(values[2]);
                ms.setPresupuestoMes(values[3]);
                ms.setVentaMes(values[4]);
                ms.setPresupuestoAcumulado(values[5]);
                ms.setVentaAcumulado(values[6]);
                ms.setFecha(Util.convierteFecha(values[7]));
                marcavend.add(ms);
            }
            BeanMarcaIndicador bean = new BeanMarcaIndicador();
            bean.setMarcas(marcas);
            bean.setIndicadores(indicadores);
            bean.setNombres(nombres);
            bean.setMarcavendedor(marcavend);
            return bean;
        }
        return null;
    }
    public static void main(String args[]){
    	try {
			List<MaterialNuevo> ex = obtenerListaMaterialNuevo("");
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

}
