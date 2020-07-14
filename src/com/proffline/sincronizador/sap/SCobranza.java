/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.sap;

import com.proffline.sincronizador.bean.Presupuesto;
import com.proffline.sincronizador.bean.Secuencia;
import com.proffline.sincronizador.cobranza.bean.CabeceraHojaMaestraCredito;
import com.proffline.sincronizador.cobranza.bean.DatoCredito;
import com.proffline.sincronizador.cobranza.bean.DetallePagoPartidaIndividualAbierta;
import com.proffline.sincronizador.cobranza.bean.DiaDemoraTrasVencimiento;
import com.proffline.sincronizador.cobranza.bean.HistorialPago;
import com.proffline.sincronizador.cobranza.bean.HojaMaestraCredito;
import com.proffline.sincronizador.cobranza.bean.PartidaIndividual;
import com.proffline.sincronizador.cobranza.bean.PartidaIndividualAbierta;
import com.proffline.sincronizador.cobranza.bean.ValorPorVencer;
import com.proffline.sincronizador.conexion.Conexion;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import util.ConexionSAP;

/**
 *
 * @author Administrador
 */
public class SCobranza {

    public static List obtenerBancosAsociadosXVendedor(String codigoVendedor) {
        try {
            List lstBanco = new ArrayList();
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("BANCO CLIENTE ").append(codigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_VEND_GET_BNK_ACCT");
                con.IngresarDatosInput("", "IV_BANKN");
                con.IngresarDatosInput(codigoVendedor, "IV_CUST_ID");
                con.EjecutarRFC();
                con.CreaTabla("T_BNK_ACCOUNT_VND");
                lstBanco = con.ObtenerDatosTabla();
                con.DesconectarSAP();
                return lstBanco;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("obtenerBancosAsociadosXVendedor SAP ").append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("BANCO CLIENTE ").append(codigoVendedor).toString()))).toString());
                return null;
            }

        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("BANCO CLIENTE ").append(codigoVendedor).toString()))).toString());
            return null;
        }
    }

    /**
     *
     * @param codigoVendedor
     * @return
     */
    public HojaMaestraCredito obtenerHojaMaestraCreditoXVendedor(String codigoVendedor) {
        try {
            ConexionSAP con;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("HOJA MAESTRA DE CREDITO ").append(codigoVendedor).toString()))).toString());
                HojaMaestraCredito hojaMaestraCredito;
                hojaMaestraCredito = new HojaMaestraCredito();
                con.RegistrarRFC("ZSD_RFC_VEND_GET_DETAIL");
                con.IngresarDatosInput(codigoVendedor, "IV_CUST_ID");
                con.EjecutarRFC();
                List cabeceraHojaMaestraCredito = obtenerListaCabeceraHojaMaestraCreditoXVendedor(con, codigoVendedor);
                List listaDiaDemoraTrasVencimiento = obtenerListaDiaDemoraTrasVencimiento(con, codigoVendedor);
                List listaHistorialPago = obtenerListaHistorialPagoXVendedor(con, codigoVendedor);
                List listaValorPorVencer = obtenerListaValorPorVencerXVendedor(con, codigoVendedor);
                List listaNotaCredito = obtenerListaNotaCreditoXVendedor(con, codigoVendedor);
                List listaProtesto = obtenerListaProtestoXVendedor(con, codigoVendedor);
                hojaMaestraCredito.setListaCabeceraHojaMaestraCredito(cabeceraHojaMaestraCredito);
                hojaMaestraCredito.setListaDiaDemoraTrasVencimiento(listaDiaDemoraTrasVencimiento);
                hojaMaestraCredito.setListaHistorialPago(listaHistorialPago);
                hojaMaestraCredito.setListaValorPorVencer(listaValorPorVencer);
                hojaMaestraCredito.setListaNotaCredito(listaNotaCredito);
                hojaMaestraCredito.setListaProtesto(listaProtesto);
                con.DesconectarSAP();
                return hojaMaestraCredito;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("obtenerHojaMaestraCreditoXVendedor en sap ").append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("HOJA MAESTRA DE CREDITO ").append(codigoVendedor).toString()))).toString());
                return null;
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("HOJA MAESTRA DE CREDITO ").append(codigoVendedor).toString()))).toString());
            return null;
        }
    }

    private List obtenerListaCabeceraHojaMaestraCreditoXVendedor(ConexionSAP con, String codigoVendedor) {
        try {
            List listaCabecerasHojasMaestrasCreditos;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaCabecerasHojasMaestrasCreditos = new ArrayList();
            con.CreaTabla("ES_CUST_INFO");
            List listaCabeceraHojaMaestraCredito = con.ObtenerDatosTabla();
            if (listaCabeceraHojaMaestraCredito.size() > 0) {
                for (int i = 0; i < listaCabeceraHojaMaestraCredito.size(); i++) {
                    CabeceraHojaMaestraCredito cabeceraHojaMaestraCredito = new CabeceraHojaMaestraCredito();
                    String valores[] = String.valueOf(listaCabeceraHojaMaestraCredito.get(i)).split("[¬]");
                    cabeceraHojaMaestraCredito.setCodigoCliente(valores[1].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setCodigoVendedor(codigoVendedor);
                    cabeceraHojaMaestraCredito.setNombreCompletoCliente(valores[2].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setLimiteCredito(valores[9].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setClaseRiesgo(valores[10].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setCupoDisponible(valores[11].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setValorVencido(valores[12].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setFuds(valores[13].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setNotaCredito(valores[14].trim().replace("'", ""));
                    cabeceraHojaMaestraCredito.setProtestante(valores[15].trim().replace("'", ""));
                    if (valores.length > 16) {
                    	
                    	try{
                    		cabeceraHojaMaestraCredito.setBloqueoCredito(valores[17].trim());       		
                    	} catch (IndexOutOfBoundsException indexEx){                   		
                    		cabeceraHojaMaestraCredito.setBloqueoCredito("");
                    	}
                        
                    } else {
                        cabeceraHojaMaestraCredito.setBloqueoCredito("");
                    }
                    listaCabecerasHojasMaestrasCreditos.add(cabeceraHojaMaestraCredito);
                }
                return listaCabecerasHojasMaestrasCreditos;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("obtenerListaCabeceraHojaMaestraCreditoXVendedor en sap ").append(codigoVendedor).toString());
                return null;
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            return null;
        }
    }

    private List obtenerListaDiaDemoraTrasVencimiento(ConexionSAP con, String codigoVendedor) {
        try {
            List listaDiaDemoraTrasVencimiento;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaDiaDemoraTrasVencimiento = new ArrayList();
            con.CreaTabla("ET_ARREARS");
            List lstDiaDemoraTrasVencimiento = con.ObtenerDatosTabla();
            if (lstDiaDemoraTrasVencimiento.size() > 0) {
                for (int i = 0; i < lstDiaDemoraTrasVencimiento.size(); i++) {
                    DiaDemoraTrasVencimiento diaDemoraTrasVencimiento = new DiaDemoraTrasVencimiento();
                    String valores[] = String.valueOf(lstDiaDemoraTrasVencimiento.get(i)).split("[¬]");
                    diaDemoraTrasVencimiento.setId(i);
                    diaDemoraTrasVencimiento.setCodigoCliente("".equals(valores[1].trim()) ? "" : (new StringBuilder()).append(Integer.parseInt(valores[1].trim())).append("").toString());
                    diaDemoraTrasVencimiento.setCodigoVendedor(codigoVendedor);
                    diaDemoraTrasVencimiento.setSociedad(valores[2].trim().replace("'", ""));
                    diaDemoraTrasVencimiento.setMoneda(valores[3].trim().replace("'", ""));
                    diaDemoraTrasVencimiento.setCuadro(valores[4].trim().replace("'", ""));
                    diaDemoraTrasVencimiento.setPartidasVencidas(valores[5].trim());
                    diaDemoraTrasVencimiento.setNoVencidas(valores[6].trim().replace("'", ""));
                    listaDiaDemoraTrasVencimiento.add(diaDemoraTrasVencimiento);
                }
            }
            return listaDiaDemoraTrasVencimiento;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            return null;
        }
    }

    private List obtenerListaHistorialPagoXVendedor(ConexionSAP con, String codigoVendedor) {
        try {
            List listaHistorialPago;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaHistorialPago = new ArrayList();
            con.CreaTabla("ET_PAYMENTS");
            List lstHistorialPago = con.ObtenerDatosTabla();
            if (lstHistorialPago.size() > 0) {
                for (int i = 0; i < lstHistorialPago.size(); i++) {
                    HistorialPago historialPago = new HistorialPago();
                    String valores[] = String.valueOf(lstHistorialPago.get(i)).split("[¬]");
                    historialPago.setId(i);
                    historialPago.setCodigoCliente(valores[1].trim().replace("'", ""));
                    historialPago.setCodigoVendedor(codigoVendedor);
                    historialPago.setMoneda(valores[2].trim().replace("'", ""));
                    historialPago.setEjercicio(valores[3].trim().replace("'", ""));
                    historialPago.setPeriodo(valores[4].trim().replace("'", ""));
                    historialPago.setConDPP(valores[5].trim().replace("'", ""));
                    historialPago.setDiasDemora1(valores[6].trim().replace("'", ""));
                    historialPago.setSinDescuento(valores[7].trim().replace("'", ""));
                    historialPago.setDiasDemora2(valores[8].trim().replace("'", ""));
                    historialPago.setCtd(valores[9].trim().replace("'", ""));
                    listaHistorialPago.add(historialPago);
                }
            }
            return listaHistorialPago;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            return null;
        }
    }

    private List obtenerListaValorPorVencerXVendedor(ConexionSAP con, String codigoVendedor) {
        try {
            List listaValorPorVencer;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaValorPorVencer = new ArrayList();
            con.CreaTabla("ET_OPEN_ITEMS_T");
            List lstValorPorVencer = con.ObtenerDatosTabla();
            if (lstValorPorVencer.size() > 0) {
                for (int i = 0; i < lstValorPorVencer.size(); i++) {
                    ValorPorVencer valorPorVencer = new ValorPorVencer();
                    String valores[] = String.valueOf(lstValorPorVencer.get(i)).split("[¬]");
                    valorPorVencer.setCodigoCliente(valores[1].trim().replace("'", ""));
                    valorPorVencer.setCodigoVendedor(codigoVendedor);
                    valorPorVencer.setMesAnio(valores[2].trim().replace("'", ""));
                    valorPorVencer.setCantidad(valores[3].trim().replace("'", ""));
                    listaValorPorVencer.add(valorPorVencer);
                }
            }
            return listaValorPorVencer;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            return null;
        }
    }

    private List obtenerListaNotaCreditoXVendedor(ConexionSAP con, String codigoVendedor) {
        try {
            List listaNotaCredito;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaNotaCredito = new ArrayList();
            con.CreaTabla("ET_C_NOTES");
            List lstNotaCredito = con.ObtenerDatosTabla();
            if (lstNotaCredito.size() > 0) {
                for (int i = 0; i < lstNotaCredito.size(); i++) {
                    DatoCredito datoCredito = new DatoCredito();
                    String valores[] = String.valueOf(lstNotaCredito.get(i)).split("[¬]");
                    datoCredito.setId(i);
                    datoCredito.setCodigoCliente(Util.eliminarCerosInicios(valores[1].trim()));
                    datoCredito.setCodigoVendedor(codigoVendedor);
                    datoCredito.setNumeroDocumento(valores[2].trim());
                    datoCredito.setFechaContable("null".equalsIgnoreCase(valores[3].trim()) ? "" : Util.convierteFecha(valores[3].trim()));
                    datoCredito.setFechaDocumento("null".equalsIgnoreCase(valores[4].trim()) ? "" : Util.convierteFecha(valores[4].trim()));
                    datoCredito.setRegistradoEl("null".equalsIgnoreCase(valores[5].trim()) ? "" : Util.convierteFecha(valores[5].trim()));
                    datoCredito.setMoneda(valores[7].trim());
                    datoCredito.setImporte(valores[8].trim());
                    datoCredito.setUnOrgRefer(valores[9].trim());
                    listaNotaCredito.add(datoCredito);
                }
            }
            return listaNotaCredito;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            return null;
        }
    }

    private List obtenerListaProtestoXVendedor(ConexionSAP con, String codigoVendedor) {
        try {
            List listaProtesto;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaProtesto = new ArrayList();
            con.CreaTabla("ET_PROTESTS");
            List lstProtesto = con.ObtenerDatosTabla();
            if (lstProtesto.size() > 0) {
                for (int i = 0; i < lstProtesto.size(); i++) {
                    DatoCredito datoCredito = new DatoCredito();
                    String valores[] = String.valueOf(lstProtesto.get(i)).split("[¬]");
                    datoCredito.setId(i);
                    datoCredito.setCodigoCliente(valores[1].trim().replace("'", ""));
                    datoCredito.setCodigoVendedor(codigoVendedor);
                    datoCredito.setNumeroDocumento(valores[2].trim());
                    datoCredito.setFechaContable("null".equalsIgnoreCase(valores[3].trim()) ? "" : Util.convierteFecha(valores[3].trim()));
                    datoCredito.setFechaDocumento("null".equalsIgnoreCase(valores[4].trim()) ? "" : Util.convierteFecha(valores[4].trim()));
                    datoCredito.setRegistradoEl("null".equalsIgnoreCase(valores[5].trim()) ? "" : Util.convierteFecha(valores[5].trim()));
                    datoCredito.setMoneda(valores[7].trim());
                    datoCredito.setImporte(valores[8].trim());
                    datoCredito.setUnOrgRefer(valores[8].trim().replace("'", ""));
                    listaProtesto.add(datoCredito);
                }
            }
            return listaProtesto;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            return null;
        }
    }

    public List obtenerListaPartidaIndividualXVendedor(String codigoVendedor, String partidasAbiertas, String partidasCompensadas, String todasPartidas, String fechaDesde, String fechaHasta) {
        try {
            List listaPartidaIndividual;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            listaPartidaIndividual = new ArrayList();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeCargaDatosExitosaSAP((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(codigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_VEND_FIND_ITEMS");
                con.CreaTabla("IS_SEARCH_OPTIONS");
                con.IngresarDatoTabla(codigoVendedor, "CUST_ID", 1);
                con.IngresarDatoTabla("", "DOC_TYPE", 1);
                con.IngresarDatoTabla(partidasAbiertas, "OPEN_ITEMS", 1);
                con.IngresarDatoTabla(partidasCompensadas, "CLEARED_ITEMS", 1);
                con.IngresarDatoTabla(todasPartidas, "ALL_ITEMS", 1);
                con.IngresarDatoTabla(fechaDesde, "DATE_FROM", 1);
                con.IngresarDatoTabla(fechaHasta, "DATE_TO", 1);
                con.IngresarDatoTabla("", "WITH_EXPIRATION_DATE", 1);
                con.EjecutarRFC();
                con.CreaTabla("ET_OPEN_ITEM_VND");
                List lstPartidaIndividual = con.ObtenerDatosTabla();
                if (lstPartidaIndividual.size() > 0) {
                    for (int i = 0; i < lstPartidaIndividual.size(); i++) {
                        String valores[] = String.valueOf(lstPartidaIndividual.get(i)).split("[¬]");
                        PartidaIndividual partidaIndividual = new PartidaIndividual();
                        partidaIndividual.setId(i);
                        partidaIndividual.setCodigoCliente(valores[1].trim());
                        partidaIndividual.setCodigoVendedor(codigoVendedor);
                        partidaIndividual.setNumeroDocumento(valores[2].trim());
                        partidaIndividual.setClaseDocumento(valores[11].trim());
                        partidaIndividual.setPosicion(valores[12].trim());
                        partidaIndividual.setFechaDocumento("null".equalsIgnoreCase(valores[4].trim()) ? "" : Util.convierteFecha(valores[4].trim()));
                        partidaIndividual.setFechaVencimiento("null".equalsIgnoreCase(valores[6].trim()) ? "" : Util.convierteFecha(valores[6].trim()));
                        partidaIndividual.setRegistradoEl("null".equalsIgnoreCase(valores[5].trim()) ? "" : Util.convierteFecha(valores[5].trim()));
                        partidaIndividual.setMoneda(valores[7].trim());
                        partidaIndividual.setImpteDePos(valores[8].trim());
                        partidaIndividual.setReferencia(valores[10].trim());
                        listaPartidaIndividual.add(partidaIndividual);
                    }
                }
                con.DesconectarSAP();
                return listaPartidaIndividual;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("obtenerListaPartidaIndividualXVendedor").append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(codigoVendedor).toString()))).toString());
                return null;
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(codigoVendedor).toString()))).toString());
            return null;
        }
    }

    public Object[] obtenerArregloDeListaPartidaIndividualAbiertaXVendedor(String codigoVendedor, String fechaHasta, long contador) {
        Object arregloListaPartidaIndividualAbierta[];
        arregloListaPartidaIndividualAbierta = new Object[3];
        try {
            List listaPartidaIndividualAbierta;
            List listaDetallePagoPartidaIndividualAbierta;
            listaPartidaIndividualAbierta = new ArrayList();
            listaDetallePagoPartidaIndividualAbierta = new ArrayList();
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("PARTIDA INDIVIDUAL ABIERTA ").append(codigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_VEND_FIND_T_ITEMS");
                con.CreaTabla("IS_SEARCH_OPTIONS");
                con.IngresarDatoTabla(codigoVendedor, "CUST_ID", 1);
                con.IngresarDatoTabla("", "DOC_TYPE", 1);
                con.IngresarDatoTabla("X", "OPEN_ITEMS", 1);
                con.IngresarDatoTabla("", "CLEARED_ITEMS", 1);
                con.IngresarDatoTabla("", "ALL_ITEMS", 1);
                con.IngresarDatoTabla("", "DATE_FROM", 1);
                con.IngresarDatoTabla(fechaHasta, "DATE_TO", 1);
                con.IngresarDatoTabla("", "WITH_EXPIRATION_DATE", 1);
                con.EjecutarRFC();
                con.CreaTabla("ET_OPEN_ITEM_VND");
                List lstPartidaIndividualAbierta = con.ObtenerDatosTabla();
                HashMap asociacion = new HashMap();
                if (lstPartidaIndividualAbierta.size() > 0) {
                    for (int i = 0; i < lstPartidaIndividualAbierta.size(); i++) {
                        String valores[] = String.valueOf(lstPartidaIndividualAbierta.get(i)).split("[¬]");
                        PartidaIndividualAbierta partidaIndividualAbierta = new PartidaIndividualAbierta();
                        partidaIndividualAbierta.setIdPartidaIndividualAbierta(contador);
                        partidaIndividualAbierta.setCodigoCliente(valores[1].trim().replace("'", ""));
                        partidaIndividualAbierta.setDocNo(valores[2].trim());
                        partidaIndividualAbierta.setPstngDate("null".equalsIgnoreCase(valores[3].trim()) ? "" : Util.convierteFecha(valores[3].trim().replace("'", "")));
                        partidaIndividualAbierta.setDocDate("null".equalsIgnoreCase(valores[4].trim()) ? "" : Util.convierteFecha(valores[4].trim().replace("'", "")));
                        partidaIndividualAbierta.setEntryDate("null".equalsIgnoreCase(valores[5].trim()) ? "" : Util.convierteFecha(valores[5].trim().replace("'", "")));
                        partidaIndividualAbierta.setExpirationDate("null".equalsIgnoreCase(valores[6].trim()) ? "" : Util.convierteFecha(valores[6].trim()));
                        partidaIndividualAbierta.setCurrency(valores[7].trim().replace("'", ""));
                        partidaIndividualAbierta.setAmtDoccur(valores[8].trim().replace("'", ""));
                        partidaIndividualAbierta.setRefOrgUn(valores[9].trim().replace("'", ""));
                        partidaIndividualAbierta.setRefDocNo(valores[10].trim().replace("'", ""));
                        partidaIndividualAbierta.setDocType(valores[11].trim().replace("'", ""));
                        partidaIndividualAbierta.setItemNum(valores[12].trim().replace("'", ""));
                        partidaIndividualAbierta.setPostKey(valores[13].trim().replace("'", ""));
                        partidaIndividualAbierta.setPsprt(valores[14].trim().replace("'", ""));
                        partidaIndividualAbierta.setPszah(valores[15].trim().replace("'", ""));
                        partidaIndividualAbierta.setPsskt(valores[16].trim().replace("'", ""));
                        partidaIndividualAbierta.setInvRef(valores[17].trim().replace("'", ""));
                        partidaIndividualAbierta.setInvItem(valores[18].trim().replace("'", ""));
                        partidaIndividualAbierta.setIsLeaf(valores[19].trim().replace("'", ""));
                        partidaIndividualAbierta.setIsExpanded(valores[20].trim().replace("'", ""));
                        partidaIndividualAbierta.setIsReadOnly(valores[21].trim().replace("'", ""));
                        partidaIndividualAbierta.setIndice(valores[22].trim().replace("'", ""));
                        partidaIndividualAbierta.setDisplayColor(valores[23].trim().replace("'", ""));
                        partidaIndividualAbierta.setFiscYear(valores[24].trim().replace("'", ""));
                        partidaIndividualAbierta.setFisPeriod(valores[25].trim().replace("'", ""));
                        partidaIndividualAbierta.setSgtxt(valores[26].trim().replace("'", ""));
                        partidaIndividualAbierta.setIsReadOnly2(valores[27].trim().replace("'", ""));
                        partidaIndividualAbierta.setDbCrInd(valores[28].trim().replace("'", ""));
                        partidaIndividualAbierta.setVerzn(valores[29].trim().replace("'", ""));
                        listaPartidaIndividualAbierta.add(partidaIndividualAbierta);
                        asociacion.put((new StringBuilder()).append(valores[2].trim().replace("'", "")).append("").append(valores[12].trim().replace("'", "")).toString(), Long.valueOf(contador));
                        contador++;
                    }

                }
                con.CreaTabla("ET_OPEN_ITEM_LEAD_VND");
                List lstDetallePagoPartidaIndividualAbierta = con.ObtenerDatosTabla();
                if (lstDetallePagoPartidaIndividualAbierta.size() > 0) {
                    for (int i = 0; i < lstDetallePagoPartidaIndividualAbierta.size(); i++) {
                        String valores[] = String.valueOf(lstDetallePagoPartidaIndividualAbierta.get(i)).split("[¬]");
                        DetallePagoPartidaIndividualAbierta detallePagoPartidaIndividualAbierta = new DetallePagoPartidaIndividualAbierta();
                        detallePagoPartidaIndividualAbierta.setIdDetallePagoPartidaIndividualAbierta(i);
                        Long valor = (Long) asociacion.get((new StringBuilder()).append(valores[17].trim()).append("").append(valores[18].trim()).toString());
                        detallePagoPartidaIndividualAbierta.setIdPartidaIndividualAbierta(valor.longValue());
                        detallePagoPartidaIndividualAbierta.setDocNo(valores[2].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setPstngDate("null".equalsIgnoreCase(valores[3].trim()) ? "" : Util.convierteFecha(valores[3].trim().replace("'", "")));
                        detallePagoPartidaIndividualAbierta.setDocDate("null".equalsIgnoreCase(valores[4].trim()) ? "" : Util.convierteFecha(valores[4].trim().replace("'", "")));
                        detallePagoPartidaIndividualAbierta.setEntryDate("null".equalsIgnoreCase(valores[5].trim()) ? "" : Util.convierteFecha(valores[5].trim().replace("'", "")));
                        detallePagoPartidaIndividualAbierta.setExpirationDate("null".equalsIgnoreCase(valores[6].trim()) ? "" : Util.convierteFecha(valores[6].trim().replace("'", "")));
                        detallePagoPartidaIndividualAbierta.setCurrency(valores[7].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setAmtDoccur(valores[8].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setRefOrgUn(valores[9].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setRefDocNo(valores[10].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setDocType(valores[11].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setItemNum(valores[12].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setPostKey(valores[13].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setPsprt(valores[14].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setPszah(valores[15].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setPsskt(valores[16].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setInvRef(valores[17].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setInvItem(valores[18].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setIsLeaf(valores[19].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setIsExpanded(valores[20].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setIsReadOnly(valores[21].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setIndice(valores[22].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setDisplayColor(valores[23].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setFiscYear(valores[24].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setFisPeriod(valores[25].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setSgtxt(valores[26].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setIsReadOnly2(valores[27].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setDbCrInd(valores[28].trim().replace("'", ""));
                        detallePagoPartidaIndividualAbierta.setVerzn(valores[29].trim().replace("'", ""));
                        listaDetallePagoPartidaIndividualAbierta.add(detallePagoPartidaIndividualAbierta);
                    }
                }
                con.DesconectarSAP();
                arregloListaPartidaIndividualAbierta[0] = listaPartidaIndividualAbierta;
                arregloListaPartidaIndividualAbierta[1] = listaDetallePagoPartidaIndividualAbierta;
                arregloListaPartidaIndividualAbierta[2] = Long.valueOf(contador);
                con.DesconectarSAP();
                return arregloListaPartidaIndividualAbierta;
            } else {
                arregloListaPartidaIndividualAbierta[0] = listaPartidaIndividualAbierta;
                arregloListaPartidaIndividualAbierta[1] = listaDetallePagoPartidaIndividualAbierta;
                arregloListaPartidaIndividualAbierta[2] = Long.valueOf(contador);
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("obtenerArregloDeListaPartidaIndividualAbiertaXVendedor ").append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("PARTIDA INDIVIDUAL ABIERTA ").append(codigoVendedor).toString()))).toString());
                return arregloListaPartidaIndividualAbierta;
            }
            
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            arregloListaPartidaIndividualAbierta[0] = null;
            arregloListaPartidaIndividualAbierta[1] = null;
            arregloListaPartidaIndividualAbierta[2] = null;
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("PARTIDA INDIVIDUAL ABIERTA ").append(codigoVendedor).toString()))).toString());
            return arregloListaPartidaIndividualAbierta;
        }
    }

    public Secuencia obtenerSecuenciaPorVendedor(String codigoVendedor) {
        try {
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            Secuencia secuencia = null;
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("SECUENCIA ").append(codigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_USER_GET_SEC");
                con.IngresarDatosInput(codigoVendedor, "IS_VENDOR_ID");
                con.EjecutarRFC();
                con.CreaTabla("T_MSG");
                secuencia = new Secuencia();
                secuencia.setCodigoVendedor(codigoVendedor);
                secuencia.setSecuenciaCobranza(con.ObtenerDato("ES_NRSECU"));
                secuencia.setSecuenciaPedido(con.ObtenerDato("ES_NPSECU"));
                con.DesconectarSAP();
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("No se cargo secuencia del vendedor ").append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("SECUENCIA ").append(codigoVendedor).toString()))).toString());
            }
            return secuencia;
            
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("SECUENCIA ").append(codigoVendedor).toString()))).toString());
            return null;
        }
    }
    
    public List<Presupuesto> getPresupuestoByVendedor(String codigoVendedor){
        try {
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("PRESUPUESTO ").append(codigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_PRESUPUESTO_CLIENTES");
                con.IngresarDatosInput(codigoVendedor, "IV_KUNN2");
                String fecha = Util.convierteFechaHoyAFormatoYYYYMMDD(new Date());//FECHA DE PRODUCTIVO
//                String fecha = "20150618";//FECHA DE PRUEBA
                con.IngresarDatosInput(fecha, "IV_DATUM");
                con.EjecutarRFC();
                con.CreaTabla("ET_PRESUP_VTA");
                List<String> respuesta = con.ObtenerDatosTabla();
                con.DesconectarSAP();
                if(respuesta != null && respuesta.size() > 0){
                    for (String str : respuesta) {
                        String [] valor = str.split("¬");
                        Presupuesto pre =  new Presupuesto();
                        pre.setCodCliente(valor[2].trim());
                        pre.setPresupuestoAnual(valor[7].trim());
                        pre.setPresupuestoReal(valor[6].trim());
                        pre.setPresupuestoMensual(valor[8].trim());
                        pre.setPresupuestoFecha(valor[9].trim());
                        pre.setVentaAnual(valor[10].trim());
                        pre.setVentaMensual(valor[11].trim());
                        pre.setVentaAcumAnioAnt(valor[12].toString());
                        pre.setPromoPlus(valor[13].toString());
                        pre.setVta_gracia(valor[14].toString());
                        pre.setFechaRegistro(Util.convierteFecha(valor[15].toString()));
                        presupuestos.add(pre);
                    }
                }
                return presupuestos;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("No se cargo secuencia del vendedor ").append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("PRESUPUESTO ").append(codigoVendedor).toString()))).toString());
            }
            return null;
            
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("PRESUPUESTO ").append(codigoVendedor).toString()))).toString());
            return null;
        }
    }
    /*public static void main(String args[]){
    	try {
    		obtenerArregloDeListaPartidaIndividualAbiertaXVendedor("0000081101", Util.convierteFechaAFormatoYYYYMMdd(new Date()), 1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
