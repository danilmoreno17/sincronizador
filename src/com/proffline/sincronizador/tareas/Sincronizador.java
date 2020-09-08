package com.proffline.sincronizador.tareas;

import com.proffline.sincronizador.bean.BancoCliente;
import com.proffline.sincronizador.bean.BancoPromesa;
import com.proffline.sincronizador.bean.BeanMarcaIndicador;
import com.proffline.sincronizador.bean.BeanMercadeo;
import com.proffline.sincronizador.bean.BeanParametro;
import com.proffline.sincronizador.bean.BeanPromocion;
import com.proffline.sincronizador.bean.BeanVentaCruzada;
import com.proffline.sincronizador.bean.BloqueoEntrega;
import com.proffline.sincronizador.bean.ClaseMaterial;
import com.proffline.sincronizador.bean.Cliente;
import com.proffline.sincronizador.bean.Condicion1;
import com.proffline.sincronizador.bean.Condicion2;
import com.proffline.sincronizador.bean.CondicionPago;
import com.proffline.sincronizador.bean.CondicionPagoDetalle;
import com.proffline.sincronizador.bean.Dispositivo;
import com.proffline.sincronizador.bean.Feriado;
import com.proffline.sincronizador.bean.FormaPago;
import com.proffline.sincronizador.bean.Jerarquia;
import com.proffline.sincronizador.bean.Material;
import com.proffline.sincronizador.bean.MaterialNuevo;
import com.proffline.sincronizador.bean.MaterialStock;
import com.proffline.sincronizador.bean.Presupuesto;
import com.proffline.sincronizador.bean.Rol;
import com.proffline.sincronizador.bean.Secuencia;
import com.proffline.sincronizador.bean.SincronizadorPedido;
import com.proffline.sincronizador.bean.Tipologia;
import com.proffline.sincronizador.bean.Usuario;
import com.proffline.sincronizador.bean.UsuarioRol;
import com.proffline.sincronizador.bean.Vista;
import com.proffline.sincronizador.bean.VistaRol;
import com.proffline.sincronizador.bean.TipoGestion;
import com.proffline.sincronizador.cobranza.bean.HojaMaestraCredito;
import com.proffline.sincronizador.conexion.Conexion;
import com.proffline.sincronizador.constantes.Constante;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sap.RFC;
import com.proffline.sincronizador.sap.SCobranza;
import com.proffline.sincronizador.sap.SPedidos;
import com.proffline.sincronizador.sqlite.DAO;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.utilidades.ClsQueries;
import com.proffline.sincronizador.utilidades.Util;

import static com.proffline.sincronizador.utilidades.Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA;

import com.promesa.cobranzas.sql.impl.SqlBancoClienteImpl;
import com.promesa.cobranzas.sql.impl.SqlCabeceraHojaMaestraCreditoImpl;
import com.promesa.cobranzas.sql.impl.SqlDetallePagoPartidaIndividualAbiertaImpl;
import com.promesa.cobranzas.sql.impl.SqlDiaDemoraTrasVencimientoImpl;
import com.promesa.cobranzas.sql.impl.SqlHistorialPagoImpl;
import com.promesa.cobranzas.sql.impl.SqlNotaCreditoImpl;
import com.promesa.cobranzas.sql.impl.SqlPartidaIndividualAbiertaImpl;
import com.promesa.cobranzas.sql.impl.SqlPartidaIndividualImpl;
import com.promesa.cobranzas.sql.impl.SqlProtestoImpl;
import com.promesa.cobranzas.sql.impl.SqlValorPorVencerImpl;
import com.promesa.planificacion.sql.impl.SqlClienteImpl;
import com.promesa.sincronizador.pedidos.sql.SqlMaterial;
import com.promesa.sincronizador.pedidos.sql.impl.SqlMaterialImpl;
import com.promesa.sincronizador.pedidos.sql.impl.SqlSedeImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.ConexionSAP;

public class Sincronizador {

    private static List<Material> listaTodosMaterial = null;
    
    public static void sincronizarTablaBloqueoEntrega() {
        boolean ocurrioError = false;
        List<BloqueoEntrega> listaBloqueoEntrega = new ArrayList<BloqueoEntrega>();
        try {
            listaBloqueoEntrega = RFC.obtenerListaBloqueoEntrega();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaBloqueoEntrega(listaBloqueoEntrega);
                listaBloqueoEntrega.clear();
            }
        }
    }

    public static void sincronizarTablaClaseMaterial() {
        boolean ocurrioError = false;
        List<ClaseMaterial> listaClaseMaterial = new ArrayList<ClaseMaterial>();
        try {
            listaClaseMaterial = RFC.obtenerListaClaseMaterial();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaClaseMaterial(listaClaseMaterial);
                listaClaseMaterial.clear();
            }
        }

    }

//    public static void sincronizarTablaCliente() {
//        boolean ocurrioError = false;
//        List<String> listaIdUsuarios = DAO.obtenerListaIdUsuarios();
//        List<Cliente> listaClienteGeneral = new ArrayList<Cliente>();
//        for (String idUsuario : listaIdUsuarios) {
//            List<Cliente> listaCliente = new ArrayList<Cliente>();
//            try {
//                listaCliente = RFC.obtenerListaCliente(idUsuario, "", "");
//            } catch (Exception e) {
//                Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
//                ocurrioError = true;
//            } finally {
//                if (!ocurrioError) {
//                    listaClienteGeneral.addAll(listaCliente);
//                }
//            }
//        }
//        DAO.insertarListaCliente(listaClienteGeneral);
//    }
    public static void sincronizarTablaCombo() {
        boolean ocurrioError = false;
        List<List> listaCombo = new ArrayList<List>();
        try {
            listaCombo = RFC.obtenerListaCombo();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaCombo(listaCombo);
                listaCombo.clear();
            }
        }
    }

    public static void sincronizarTablaCondicion1() {
        boolean ocurrioError = false;
        List<Condicion1> listaCondicion1 = new ArrayList<Condicion1>();
        try {
            listaCondicion1 = RFC.obtenerListaCondicion1();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaCondicion1(listaCondicion1);
                listaCondicion1.clear();
            }
        }
    }

    public static void sincronizarTablaCondicion2() {
        boolean ocurrioError = false;
        List<Condicion2> listaCondicion2 = new ArrayList<Condicion2>();
        try {
            listaCondicion2 = RFC.obtenerListaCondicion2();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaCondicion2(listaCondicion2);
                listaCondicion2.clear();
            }
        }
    }

    public static void sincronizarTablaCondicionPago() {
        boolean ocurrioError = false;
        List<CondicionPago> listaCondicionPago = new ArrayList<CondicionPago>();
        try {
            listaCondicionPago = RFC.obtenerListaCondicionPago();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaCondicionPago(listaCondicionPago);
                listaCondicionPago.clear();
            }
        }
    }

    public static void sincronizarTablaCondicionPagoDetalle() {
        boolean ocurrioError = false;
        List<CondicionPagoDetalle> listaCondicionPagoDetalle = new ArrayList<CondicionPagoDetalle>();
        try {
            listaCondicionPagoDetalle = RFC.obtenerListaCondicionPagoDetalle();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaCondicionPagoDetalle(listaCondicionPagoDetalle);
                listaCondicionPagoDetalle.clear();
            }
        }
    }

//    public static void sincronizarTablaDestinatario() {
//        boolean ocurrioError = false;
//        List<String> listaIdUsuarios = DAO.obtenerListaIdUsuarios();
//        List<Destinatario> listaDestinatarioGeneral = new ArrayList<Destinatario>();
//        for (String idUsuario : listaIdUsuarios) {
//            List<Destinatario> listaDestinatario = new ArrayList<Destinatario>();
//            try {
//                listaDestinatario = RFC.obtenerListaDestinatario(idUsuario);
//            } catch (Exception e) {
//                Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
//                ocurrioError = true;
//            } finally {
//                if (!ocurrioError) {
//                    listaDestinatarioGeneral.addAll(listaDestinatario);
//                }
//            }
//        }
//        DAO.insertarListaDestinatario(listaDestinatarioGeneral);
//    }
    public static void sincronizarTablaDispositivo() {
        boolean ocurrioError = false;
        List<Dispositivo> listaDispositivo = new ArrayList<Dispositivo>();
        try {
            listaDispositivo = RFC.obtenerListaDispositivo();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaDispositivo(listaDispositivo);
                listaDispositivo.clear();
            }
        }
    }

//    public static void sincronizarTablaEmpleado() {
//        boolean ocurrioError = false;
//        List<Empleado> listaEmpleadoGeneral = new ArrayList<Empleado>();
//        List<String> listaIdUsuarios = DAO.obtenerListaIdUsuarios();
//        for (String idUsuario : listaIdUsuarios) {
//            List<Empleado> listaEmpleado = new ArrayList<Empleado>();
//            try {
//                listaEmpleado = RFC.obtenerListaEmpleado(idUsuario);
//            } catch (Exception e) {
//                Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
//                ocurrioError = true;
//            } finally {
//                if (!ocurrioError) {
//                    listaEmpleadoGeneral.addAll(listaEmpleado);
//                }
//            }
//        }
//        DAO.insertarListaEmpleado(listaEmpleadoGeneral);
//    }
//    public static void sincronizarTablaEmpleadoCliente() {
//        boolean ocurrioError = false;
//        List<String> listaIdUsuarios = DAO.obtenerListaIdUsuarios();
//        List<EmpleadoCliente> listaEmpleadoClienteGeneral = new ArrayList<EmpleadoCliente>();
//        for (String idUsuario : listaIdUsuarios) {
//            List<EmpleadoCliente> listaEmpleadoCliente = new ArrayList<EmpleadoCliente>();
//            try {
//                listaEmpleadoCliente = RFC.obtenerListaEmpleadoCliente(idUsuario);
//            } catch (Exception e) {
//                Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
//                ocurrioError = true;
//            } finally {
//                if (!ocurrioError) {
//                    listaEmpleadoClienteGeneral.addAll(listaEmpleadoCliente);
//                }
//            }
//        }
//        DAO.insertarListaEmpleadoCliente(listaEmpleadoClienteGeneral);
//    }
    public static void sincronizarTablaFeriado() {
        boolean ocurrioError = false;
        List<Feriado> listaFeriado = new ArrayList<Feriado>();
        try {
            listaFeriado = RFC.obtenerListaFeriado();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaFeriado(listaFeriado);
                listaFeriado.clear();
            }
        }
    }

    public static void sincronizarTablaJerarquia() {
        boolean ocurrioError = false;
        List<Jerarquia> listaJerarquia = new ArrayList<Jerarquia>();
        try {
            listaJerarquia = RFC.obtenerListaJerarquia();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaJerarquia(listaJerarquia);
                listaJerarquia.clear();
            }
        }
    }

    public static void sincronizarTablaMaterial() {
        boolean ocurrioError = false;
        List<Material> listaMaterial = new ArrayList<Material>();
        try {
            listaMaterial = RFC.obtenerListaMaterial("");
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaMaterial(listaMaterial);
                listaMaterial.clear();
            }
        }
    }

    public static void sincronizarTablaMaterialStock() {
        boolean ocurrioError = false;
        List<MaterialStock> listaMaterialStock = new ArrayList<MaterialStock>();
        try {
            listaMaterialStock = RFC.obtenerListaMaterialStock();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaMaterialStock(listaMaterialStock);
                listaMaterialStock.clear();
            }
        }
    }

    public static void sincronizarTablaRol() {
        boolean ocurrioError = false;
        List<Rol> listaRol = new ArrayList<Rol>();
        try {
            listaRol = RFC.obtenerListaRol();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaRol(listaRol);
                listaRol.clear();
            }
        }
    }

    public static void sincronizarTablaTipologia() {
        boolean ocurrioError = false;
        List<Tipologia> listaTipologia = new ArrayList<Tipologia>();
        try {
            listaTipologia = RFC.obtenerListaTipologia();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaTipologia(listaTipologia);
                listaTipologia.clear();
            }
        }
    }

    public static void sincronizarTablaUsuario() {
        boolean ocurrioError = false;
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        try {
            listaUsuario = RFC.obtenerListaUsuario("");
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaUsuario(listaUsuario);
                listaUsuario.clear();
            }
        }
    }

    public static void sincronizarTablaUsuarioRol() {
        boolean ocurrioError = false;
        List<UsuarioRol> listaUsuarioRol = new ArrayList<UsuarioRol>();
        try {
            listaUsuarioRol = RFC.obtenerListaUsuarioRol();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaUsuarioRol(listaUsuarioRol);
                listaUsuarioRol.clear();
            }
        }
    }
    
    public static void sincronizarTablaTipoGestion() {
        boolean ocurrioError = false;
        List<TipoGestion> listaTipoGestion = new ArrayList<TipoGestion>();
        try {
        	listaTipoGestion = RFC.obtenerListaTipoGestion();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaTipoGestion(listaTipoGestion);
                listaTipoGestion.clear();
            }
        }
    }

    public static void sincronizarTablaVista() {
        boolean ocurrioError = false;
        List<Vista> listaVista = new ArrayList<Vista>();
        try {
            listaVista = RFC.obtenerListaVista();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaVista(listaVista);
                listaVista.clear();
            }
        }
    }

    public static void sincronizarTablaVistaRol() {
        boolean ocurrioError = false;
        List<VistaRol> listaVistaRol = new ArrayList<VistaRol>();
        try {
            listaVistaRol = RFC.obtenerListaVistaRol();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaVistaRol(listaVistaRol);
                listaVistaRol.clear();
            }
        }
    }

    public static void sincronizarTablaConsultaDinamica() {
        boolean ocurrioError = false;
        List<List<Material>> materiales = new ArrayList<List<Material>>();
        try {
            materiales = RFC.obtenerListaMaterialConsultaDinamica();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaMaterialConsultaDinamica(materiales);
                materiales.clear();
            }
        }
    }

    public static void sincronizarTablaFormaPagoCobranza() {
        boolean ocurrioError = false;
        List<FormaPago> listaFormaPagoCobranza = new ArrayList<FormaPago>();
        try {
            listaFormaPagoCobranza = RFC.obtenerListaFormaPago("01");
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaFormaPagoCobranza(listaFormaPagoCobranza);
                listaFormaPagoCobranza.clear();
            }
        }
    }

    public static void sincronizarTablaFormaPagoAnticipo() {
        boolean ocurrioError = false;
        List<FormaPago> listaFormaPagoAnticipo = new ArrayList<FormaPago>();
        try {
            listaFormaPagoAnticipo = RFC.obtenerListaFormaPago("02");
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaFormaPagoAnticipo(listaFormaPagoAnticipo);
                listaFormaPagoAnticipo.clear();
            }
        }
    }

    public static void sincronizarTablaBancoPromesa() {
        boolean ocurrioError = false;
        List<BancoPromesa> listaBancoPromesa = new ArrayList<BancoPromesa>();
        try {
            listaBancoPromesa = RFC.obtenerListaBancoPromesa();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaBancoPromesa(listaBancoPromesa);
                listaBancoPromesa.clear();
            }
        }
    }

   
    public static void sincronizarTablaMaterialNuevo(){
    	boolean ocurrioError = false;
        List<MaterialNuevo> listaMaterialNuevo = new ArrayList<MaterialNuevo>();
        try {
            listaMaterialNuevo = RFC.obtenerListaMaterialNuevo("");
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                DAO.insertarListaMaterialNuevo(listaMaterialNuevo);
                listaMaterialNuevo.clear();
            }
        }
    }
    public static void sincronizadorTablaConstantes(){
    	boolean ocurrioError = false;
        List<BeanParametro> listaParametro = new ArrayList<BeanParametro>();
        try {
            listaParametro = RFC.obtenerParametrosConstantes();
        } catch (NoClassDefFoundError e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } catch (ExceptionInInitializerError e) {
            ocurrioError = true;
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            ocurrioError = true;
        } finally {
            if (!ocurrioError) {
                try {
					DAO.insertarBloquePedido(listaParametro);
				} catch (Exception e) {
					e.printStackTrace();
				}
                listaParametro.clear();
            }
        }
    }
    public static void sincronizarTablaCondicionesComerciales() {
        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_1X");
        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_2X");
        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_3X");
        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_4X");
        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_ZPR1");

        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_5X");
        DAO.limpiarTabla("PROFFLINE_TB_CONDICION_ESCALAS");
        try {
            ConexionSAP con = Conexion.obtenerConexionSAP();
            String[] tablas = {"ZTSD_ZD01", "ZTSD_ZD02", "ZTSD_ZD3X", "ZTSD_ZD4X", "ZTSD_ZD05", "ZTSD_SCALES"};
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP("CONDICIONES COMERCIALES")));
                for (String tabla1 : tablas) {
                    List<String> listaConsultas = new ArrayList<String>();
                    String tabla = tabla1;
                    con.RegistrarRFC("ZSD_RFC_MIGRA_CONDICIONES");
                    con.IngresarDatosInput(tabla, "TABLE_NAME");
                    con.EjecutarRFC();
                    if (tabla.compareTo("ZTSD_ZD01") == 0) {
                        con.CreaTabla("T_ZD01");
                        List resultado = con.ObtenerDatosTabla();
                        String sql = "DELETE FROM PROFFLINE_TB_CONDICION_1X;";
                        ResultExecute rs = new ResultExecute();
                        listaConsultas.add(sql);
                        for (int i = 0; i < resultado.size(); i++) {
                            String[] v = String.valueOf(resultado.get(i)).split("[¬]");
                            String nivel = v[2].isEmpty() ? "*" : v[2];
                            String claseCond = v[3].isEmpty() ? "*" : v[3];
                            String acceso = v[4].isEmpty() ? "*" : v[4];
                            String tablaCond = v[5].isEmpty() ? "*" : v[5];
                            String prioridad = v[6].isEmpty() ? "*" : v[6];
                            String claseMaterial = v[7].isEmpty() ? "*" : v[7];
                            String condicionPago = v[9].isEmpty() ? "*" : v[9];
                            String cliente = v[8].isEmpty() ? "*" : "" + Long.parseLong(v[8]);
                            String unidad = v[10].isEmpty() ? "*" : v[10];
                            String importe = v[11].isEmpty() ? "*" : v[11];
                            String escala = v[12];
                            String nroRegCond = v[13].isEmpty() ? "*" : "" + Long.parseLong(v[13]);
                            String num = v[14].isEmpty() ? "*" : v[14];

                            sql = "insert into PROFFLINE_TB_CONDICION_1X values('"
                                    + nivel + "','" + claseCond + "','" + acceso
                                    + "','" + tablaCond + "','" + prioridad + "','"
                                    + claseMaterial + "','" + condicionPago + "','"
                                    + cliente + "','" + unidad + "','" + importe
                                    + "','" + escala + "','" + nroRegCond + "','" + num
                                    + "');";
                            listaConsultas.add(sql);
                        }
                        rs.ejecutarConsulta(listaConsultas);
                    } else if (tabla.compareTo("ZTSD_ZD02") == 0) {
                        con.CreaTabla("T_ZD02");
                        List resultado = con.ObtenerDatosTabla();
                        String sql = "DELETE FROM PROFFLINE_TB_CONDICION_2X;";
                        ResultExecute rs = new ResultExecute();
                        listaConsultas.add(sql);
                        for (int i = 0; i < resultado.size(); i++) {
                            String[] v = String.valueOf(resultado.get(i)).split("[¬]");
                            String nivel = v[2].isEmpty() ? "*" : v[2];
                            String claseCond = v[3].isEmpty() ? "*" : v[3];
                            String acceso = v[4].isEmpty() ? "*" : v[4];
                            String tablaCond = v[5].isEmpty() ? "*" : v[5];
                            String prioridad = v[6].isEmpty() ? "*" : v[6];
                            String cliente = v[7].isEmpty() ? "*" : "" + Long.parseLong(v[7]);
                            String grupoCliente = v[8].isEmpty() ? "*" : v[8];
                            String canal = v[9].isEmpty() ? "*" : v[9];
                            String unidad = v[10].isEmpty() ? "*" : v[10];
                            String importe = v[11].isEmpty() ? "*" : v[11];
                            String escala = v[12];
                            String nroRegCond = v[13].isEmpty() ? "*" : "" + Long.parseLong(v[13]);
                            String num = v[14].isEmpty() ? "*" : v[14];
                            String matnr = "*";
        					if(v.length==16)
        						matnr = v[15].isEmpty() ? "*" : ""+Integer.parseInt(v[15]);
                            sql = "insert into PROFFLINE_TB_CONDICION_2X values('"
                                    + nivel + "','" + claseCond + "','" + acceso + "','" + tablaCond
                                    + "','" + prioridad + "','" + cliente + "','" + grupoCliente
                                    + "','" + canal + "','" + unidad + "','" + importe
                                    + "','" + escala + "','" + nroRegCond + "','" + num
                                    + "','"+matnr+"');";
                            listaConsultas.add(sql);
                        }
                        rs.ejecutarConsulta(listaConsultas);
                    } else if (tabla.compareTo("ZTSD_ZD3X") == 0) {
                        con.CreaTabla("T_ZD3X");
                        List resultado = con.ObtenerDatosTabla();
                        String sql = "DELETE FROM PROFFLINE_TB_CONDICION_3X;";
                        ResultExecute rs = new ResultExecute();
                        listaConsultas.add(sql);
                        sql = "DELETE FROM PROFFLINE_TB_CONDICION_ZPR1;";
                        listaConsultas.add(sql);
                        for (int i = 0; i < resultado.size(); i++) {
                            String[] v = String.valueOf(resultado.get(i)).split("[¬]");
//					String material = v[7].isEmpty() ? "*" : "" + Long.parseLong(v[7]);
                            String nivel = v[2].isEmpty() ? "*" : v[2];
                            String claseCond = v[3].isEmpty() ? "*" : v[3];
                            String Acceso = v[4].isEmpty() ? "*" : v[4];
                            String TablaCond = v[5].isEmpty() ? "*" : v[5];
                            String Prioridad = v[6].isEmpty() ? "*" : v[6];
                            String Material = v[7].isEmpty() ? "*" : "" + Long.parseLong(v[7]);
                            String DivisionC = v[8].isEmpty() ? "*" : "" + Long.parseLong(v[8]);
                            String DivisionCom = v[9].isEmpty() ? "*" : "" + Long.parseLong(v[9]);
                            String CatProd = v[10].isEmpty() ? "*" : "" + Long.parseLong(v[10]);
                            String Familia = v[11].isEmpty() ? "*" : "" + Long.parseLong(v[11]);
                            String Linea = v[12].isEmpty() ? "*" : "" + Long.parseLong(v[12]);
                            String Grupo = v[13].isEmpty() ? "*" : "" + Long.parseLong(v[13]);
                            String Cliente = v[14].isEmpty() ? "*" : "" + Long.parseLong(v[14]);
                            String ZonaPromesa = v[15].isEmpty() ? "*" : v[15];
                            String Clase = v[16].isEmpty() ? "*" : v[16];
                            String GrupoCliente = v[17].isEmpty() ? "*" : v[17];
                            String Unidad = v[18].isEmpty() ? "*" : v[18];
                            String Importe = v[19].isEmpty() ? "*" : v[19];
                            String Escala = v[20];
                            String NroRegCond = v[21].isEmpty() ? "*" : "" + Long.parseLong(v[21]);
                            String Num = v[22].isEmpty() ? "*" : v[22];
                            String tablaCondicion = "PROFFLINE_TB_CONDICION_3X";
                            if (claseCond.equalsIgnoreCase("ZPR1")) {
                                tablaCondicion = "PROFFLINE_TB_CONDICION_ZPR1";
                            }
                            sql = "insert into " + tablaCondicion + " values('"
                                    + nivel + "','" + claseCond + "','" + Acceso + "','" + TablaCond
                                    + "','" + Prioridad + "','" + Material + "','" + DivisionC
                                    + "','" + DivisionCom + "','" + CatProd + "','" + Familia
                                    + "','" + Linea + "','" + Grupo + "','" + Cliente
                                    + "','" + ZonaPromesa + "','" + Clase + "','" + GrupoCliente
                                    + "','" + Unidad + "','" + Importe + "','" + Escala
                                    + "','" + NroRegCond + "','" + Num + "');";
                            listaConsultas.add(sql);
                        }
                        rs.ejecutarConsulta(listaConsultas);
                    } else if (tabla.compareTo("ZTSD_ZD4X") == 0) {
                        con.CreaTabla("T_ZD4X");
                        List resultado = con.ObtenerDatosTabla();
                        String sql = "DELETE FROM PROFFLINE_TB_CONDICION_4X;";
                        ResultExecute rs = new ResultExecute();
                        listaConsultas.add(sql);
                        for (int i = 0; i < resultado.size(); i++) {
                            String[] v = String.valueOf(resultado.get(i)).split("[¬]");
                            String Nivel = v[2].isEmpty() ? "*" : v[2];
                            String ClaseCond = v[3].isEmpty() ? "*" : v[3];
                            String Acceso = v[4].isEmpty() ? "*" : v[4];
                            String TablaCond = v[5].isEmpty() ? "*" : v[5];
                            String Prioridad = v[6].isEmpty() ? "*" : v[6];
                            String Material = v[7].isEmpty() ? "*" : "" + Long.parseLong(v[7]);
                            String DivisionC = v[8].isEmpty() ? "*" : "" + Long.parseLong(v[8]);
                            String DivisionCom = v[9].isEmpty() ? "*" : "" + Long.parseLong(v[9]);
                            String CatProd = v[10].isEmpty() ? "*" : "" + Long.parseLong(v[10]);
                            String Familia = v[11].isEmpty() ? "*" : "" + Long.parseLong(v[11]);
                            String Linea = v[12].isEmpty() ? "*" : "" + Long.parseLong(v[12]);
                            String Grupo = v[13].isEmpty() ? "*" : "" + Long.parseLong(v[13]);
                            String Cliente = v[14].isEmpty() ? "*" : "" + Long.parseLong(v[14]);
                            String ZonaPromesa = v[15].isEmpty() ? "*" : v[15];
                            String Clase = v[16].isEmpty() ? "*" : v[16];
                            String GrupoCliente = v[17].isEmpty() ? "*" : v[17];
                            String Unidad = v[18].isEmpty() ? "*" : v[18];
                            String Importe = v[19].isEmpty() ? "*" : v[19];
                            String Escala = v[20];
                            String NroRegCond = v[21].isEmpty() ? "*" : "" + Long.parseLong(v[21]);
                            String Num = v[22].isEmpty() ? "*" : v[22];
                            sql = "insert into PROFFLINE_TB_CONDICION_4X values('"
                                    + Nivel + "','" + ClaseCond + "','" + Acceso + "','" + TablaCond
                                    + "','" + Prioridad + "','" + Material + "','" + DivisionC
                                    + "','" + DivisionCom + "','" + CatProd + "','" + Familia
                                    + "','" + Linea + "','" + Grupo + "','" + Cliente
                                    + "','" + ZonaPromesa + "','" + Clase + "','" + GrupoCliente
                                    + "','" + Unidad + "','" + Importe + "','" + Escala
                                    + "','" + NroRegCond + "','" + Num + "');";
                            listaConsultas.add(sql);
                        }
                        rs.ejecutarConsulta(listaConsultas);
                    } else if (tabla.compareTo("ZTSD_ZD05") == 0) {
                        con.CreaTabla("T_ZD05");
                        List resultado = con.ObtenerDatosTabla();
                        String sql = "DELETE FROM PROFFLINE_TB_CONDICION_5X;";
                        listaConsultas.add(sql);
                        ResultExecute rs = new ResultExecute();
                        for (int i = 0; i < resultado.size(); i++) {
                            String[] v = String.valueOf(resultado.get(i)).split("[¬]");
                            String Nivel = v[2].isEmpty() ? "*" : v[2];
                            String ClaseCond = v[3].isEmpty() ? "*" : v[3];
                            String Acceso = v[4].isEmpty() ? "*" : v[4];
                            String TablaCond = v[5].isEmpty() ? "*" : v[5];
                            String Prioridad = v[6].isEmpty() ? "*" : v[6];

                            String DivisionCom = v[7].isEmpty() ? "*" : "" + Long.parseLong(v[7]);
                            String CatProd = v[8].isEmpty() ? "*" : "" + Long.parseLong(v[8]);
                            String Familia = v[9].isEmpty() ? "*" : "" + Long.parseLong(v[9]);
                            String Cliente = v[10].isEmpty() ? "*" : "" + Long.parseLong(v[10]);

                            String Unidad = v[11].isEmpty() ? "*" : v[11];
                            String Importe = v[12].isEmpty() ? "*" : v[12];
                            String Escala = v[13];
                            String NroRegCond = v[14].isEmpty() ? "*" : "" + Long.parseLong(v[14]);
                            String Num = v[15].isEmpty() ? "*" : v[15];
                            sql = "insert into PROFFLINE_TB_CONDICION_5X values('"
                                    + Nivel + "','" + ClaseCond + "','" + Acceso + "','" + TablaCond
                                    + "','" + Prioridad + "','" + DivisionCom + "','" + CatProd + "','" + Familia
                                    + "','" + Cliente
                                    + "','" + Unidad + "','" + Importe + "','" + Escala
                                    + "','" + NroRegCond + "','" + Num + "');";
                            listaConsultas.add(sql);

                        }
                        rs.ejecutarConsulta(listaConsultas);
                    } else if (tabla.compareTo("ZTSD_SCALES") == 0) {
                        con.CreaTabla("T_SCALES");
                        List resultado = con.ObtenerDatosTabla();
                        String sql = "DELETE FROM PROFFLINE_TB_CONDICION_ESCALAS;";
                        ResultExecute rs = new ResultExecute();
                        listaConsultas.add(sql);
                        for (int i = 0; i < resultado.size(); i++) {
                            String[] v = String.valueOf(resultado.get(i)).split("[¬]");
                            String ClaseCond = v[2].isEmpty() ? "*" : v[2];
                            String TipoEscala = v[3].isEmpty() ? "*" : v[3];
                            String NroRegCond = v[4].isEmpty() ? "*" : "" + Long.parseLong(v[4]);
                            String NumActCond = v[5].isEmpty() ? "*" : v[5];
                            String NumLinea = v[6].isEmpty() ? "*" : v[6];
                            String CantEscala = v[7];
                            String ValorEscala = v[8];
                            String Importe = v[9].isEmpty() ? "*" : v[9];
                            sql = "insert into PROFFLINE_TB_CONDICION_ESCALAS values('"
                                    + ClaseCond + "','" + TipoEscala + "','" + NroRegCond + "','" + NumActCond
                                    + "','" + NumLinea + "','" + CantEscala + "','" + ValorEscala
                                    + "','" + Importe + "');";
                            listaConsultas.add(sql);
                        }
                        rs.ejecutarConsulta(listaConsultas);
                    }
                }
                con.DesconectarSAP();
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("CONDICIONES COMERCIALES")));
            }
        } catch (Exception e) {
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("CONDICIONES COMERCIALES")));
        }

    }

    public static void sincronizarPorVendedor(List<String> listaIdUsuario) {
        int contador = 0;
        SqlMaterial sqlMaterial = new SqlMaterialImpl();
        /*listaTodosMaterial = new ArrayList<Material>();
        listaTodosMaterial.clear();*/
        if(listaTodosMaterial==null){
        	listaTodosMaterial = new ArrayList<Material>();
        	listaTodosMaterial = sqlMaterial.obtenerTodosMateriales2();
        }
//        for (int j = 0; j < 5; j++) {
//            String codigoVendedor = listaIdUsuario.get(j);
        ResultExecute resultExecute = new ResultExecute();
        for (String codigoVendedor : listaIdUsuario ) {
        	
        	//** Verifico por vendedor (debug)**//
        	//if(codigoVendedor.equals("0000080648")){
        		//System.out.println("Debug");
        	
            VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
            try {
            	List<ClsQueries> queries = new ArrayList();            	 
            	queries = sincronizarTablaCliente(codigoVendedor,queries);
            	if(!queries.isEmpty()){
            		resultExecute.ejecutarConsultaPorVendedor2(queries, codigoVendedor);
		            queries.clear();
					queries = sincronizaDestinatario(codigoVendedor,queries);				
					//queries = sincronizaMaterialConsultaDinamica(codigoVendedor,queries);
		            queries = sincronizaTablaPartidasIndividualesAbiertas(codigoVendedor,queries);
		            queries = sincronizaBancoCliente(codigoVendedor,queries);
		            queries = sincronizaHojaMaestraCredito(codigoVendedor,queries);
		            queries = sincronizaPartidaIndividual(codigoVendedor,queries);
		            queries = sincronisaPresupuestoClientes(codigoVendedor,queries);
		            queries = sincronizarMarcaEstrategica(codigoVendedor,queries);
		            //queries = sincronizarMarcaVendedor(codigoVendedor,queries);
		            sincronizacionSecuenciaPorVendedor(codigoVendedor);	            
		            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CARGANDO TABLAS A BASE DE DATOS LOCAL");
		            resultExecute.ejecutarConsultaPorVendedor2(queries, codigoVendedor);
		            queries.clear();	            
		            queries = sincronizaMaterialConsultaDinamica(codigoVendedor,queries);
		            queries = sincronizarVentaCruzada(codigoVendedor,queries);
		            resultExecute.ejecutarConsultaPorVendedor2(queries, codigoVendedor);
		            queries.clear();
		            queries = sincronizarTablaSincronizador(codigoVendedor,queries);
		            resultExecute.ejecutarConsultaPorVendedor2(queries, codigoVendedor);
		            queries.clear();
		            if(resultExecute.getCountRow("SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_TOP_CLIENTE", codigoVendedor)<1||resultExecute.getCountRow("SELECT COUNT(*) AS filas FROM PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA", codigoVendedor)<1){
		            	VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CARGANDO TABLAS INCOMPLETAS");
		            	List<ClsQueries> sql = new ArrayList();
		            	sql = sincronizaMaterialConsultaDinamica(codigoVendedor,sql);
			            sql = sincronizarTablaSincronizador(codigoVendedor,sql);
			            resultExecute.ejecutarConsultaPorVendedor2(sql, codigoVendedor);
			            sql.clear();
		            }
	            }	            
            } catch (Exception e1) {
            	VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_SINCRONIZACION " + codigoVendedor)));
			}
            
        //}
            try {
                for (int i = 0; i < Constante.ID_SINCRONIZADOR.length; i++) {
                    activaSincronizacion(Constante.ID_SINCRONIZADOR[i], codigoVendedor);
                }
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_SINCRONIZACION " + codigoVendedor)));
            } catch (Exception e) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_SINCRONIZACION " + codigoVendedor)));
            }

        }
        listaTodosMaterial.clear();
        VentanaPrincipal.obtenerInstancia().setProgresoTotal(listaIdUsuario.size());
    }
    

    
    private static List<ClsQueries> sincronizarVentaCruzada(String codigoVendedor,List<ClsQueries> queries){
    	List<List<Material>> listaVtaCrz = null;
    	List<BeanVentaCruzada> listaVentaCruzada = null;
    	SqlMaterial sqlMaterial = new SqlMaterialImpl();
    	try {
			listaTodosMaterial = sqlMaterial.obtenerTodosMateriales2();
            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("MATERIAL VENTA CRUZADA POR VENDEDOR ").append(codigoVendedor).toString());
            listaVtaCrz = RFC.listaMaterialesVentaCruzada(codigoVendedor);
            if (listaVtaCrz != null && listaVtaCrz.size() > 0&& listaVtaCrz.get(0) != null) {
            	List<Material> listaMaterial = buscarJerarquiaMateriales(listaTodosMaterial, listaVtaCrz.get(0), "Consultando por Venta Cruzada.");           	
				queries = sqlMaterial.migrarMaterialesVentaCruzada(listaVtaCrz, listaMaterial,codigoVendedor, queries);
				listaMaterial.clear();
			}
                       
            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("VENTA CRUZADA POR VENDEDOR ").append(codigoVendedor).toString());
            listaVentaCruzada = RFC.listaCategoriaVentaCruzada(codigoVendedor);
            if (listaVentaCruzada != null) {
				queries = sqlMaterial.setInsertarActualizarVentaCruzada(listaVentaCruzada, queries);
			}
        } catch (Exception ex) {
        	System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA & PROFFLINE_TB_INDICADORES  " + codigoVendedor)));
        }
        return queries;
    }
    
    public static void sincronizarMercadeo(){
		List<BeanMercadeo> listMercadeo = null;
		List<BeanPromocion> listPromocion = null;
    	SqlMaterial sqlMaterial = new SqlMaterialImpl();
    	try {

            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("MATERIAL MERCADEO ").toString());
            listMercadeo = SPedidos.listaMaterialesMercadeo();
    		if(listMercadeo != null && listMercadeo.size() > 0){
            	sqlMaterial.insertarMaterialesMercadeo(listMercadeo);
				listMercadeo.clear();
			}
                       
            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("MATERIAL PROMOCION ").toString());
            listPromocion = SPedidos.listaMaterialesPromocion();
            if(listPromocion != null && listPromocion.size() > 0){
				sqlMaterial.insertarMaterialesPromocion(listPromocion);
				listPromocion.clear();
			}
        } catch (Exception ex) {
        	System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA & PROFFLINE_TB_INDICADORES  " + codigoVendedor)));
        }
    }
    
    private static List<ClsQueries> sincronizarMarcaEstrategica(String codigoVendedor,List<ClsQueries> queries){
        try {
            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("MARCA ESTRATEGICA E INDICADORES POR VENDEDOR: " + codigoVendedor);
            BeanMarcaIndicador bean = RFC.getMarcaIndicador(codigoVendedor);
            if(bean != null){
                queries = DAO.insertarIndicadores(bean.getIndicadores(), codigoVendedor,queries);
                queries = DAO.insertarNombreMarcaEstrategica(bean.getNombres(), codigoVendedor,queries);
                queries = DAO.insertarMarcaEstrategica(bean.getMarcas(), codigoVendedor,queries);
                queries = DAO.insertarMarcaVendedor(bean.getMarcavendedor(), codigoVendedor, queries);
                //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA & PROFFLINE_TB_INDICADORES " + codigoVendedor)));
            } else {
                //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA & PROFFLINE_TB_INDICADORES  " + codigoVendedor)));
            }    
        } catch (Exception ex) {
          //  Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA & PROFFLINE_TB_INDICADORES  " + codigoVendedor)));
        }
        return queries;
    }

    private static List<ClsQueries> sincronizarTablaSincronizador(String codigoVendedor,List<ClsQueries> queries) {
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("ACTUALIZANDO TABLA SINCRONIZADOR");
        VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + "Actualizando Cantidad en tabla de Sincronizacion Vendedor: " + codigoVendedor));
        queries = Util.ActualizarNumeroRegistros(codigoVendedor,queries);
        return queries;
    }

    private static List<ClsQueries> sincronizaPartidaIndividual(String codigoVendedor,List<ClsQueries> queries) {
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("PARTIDA INDIVIDUAL POR VENDEDOR ").append(codigoVendedor).toString());
        List lstPartidaIndividual = new ArrayList();
        SCobranza sCobranzas = new SCobranza();
        String fechaHasta = Util.convierteFechaAFormatoYYYYMMdd(new Date());
        List listaPartidaIndividual = sCobranzas.obtenerListaPartidaIndividualXVendedor(codigoVendedor, "X", "", "", "", fechaHasta);
        if (listaPartidaIndividual.size() > 0) {
            lstPartidaIndividual.addAll(listaPartidaIndividual);
        }
        if (lstPartidaIndividual.size() > 0) {
            SqlPartidaIndividualImpl sqlPartidaIndividualImpl = new SqlPartidaIndividualImpl();
            queries = sqlPartidaIndividualImpl.eliminarListaPartidaIndividual(codigoVendedor,queries);
            queries = sqlPartidaIndividualImpl.insertarListaPartidaIndividual(lstPartidaIndividual, codigoVendedor,queries);
        }
        return queries;
    }
    
    private static List<ClsQueries> sincronisaPresupuestoClientes(String codigoVendedor,List<ClsQueries> queries){
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("PRESUPUESTO CLIENTE POR VENDEDOR " + codigoVendedor);
        SCobranza sCobranzas = new SCobranza();
        List<Presupuesto> presupuestos = sCobranzas.getPresupuestoByVendedor(codigoVendedor);
        queries = DAO.insertPresupuesto(presupuestos, codigoVendedor,queries);
        return queries;
    }

    @SuppressWarnings("null")
    public static List<ClsQueries> sincronizaHojaMaestraCredito(String codigoVendedor,List<ClsQueries> queries) {
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("HOJA MAESTRA DE CREDITO ").append(codigoVendedor).toString());
        List lstCabeceraHojaMaestraCredito = new ArrayList();
        List lstDiaDemoraTrasVencimiento = new ArrayList();
        List lstHistorialPago = new ArrayList();
        List lstValorPorVencer = new ArrayList();
        List lstNotaCredito = new ArrayList();
        List lstProtesto = new ArrayList();
        SCobranza sCobranzas = new SCobranza();
        try {
            HojaMaestraCredito hojaMaestraCredito = sCobranzas.obtenerHojaMaestraCreditoXVendedor(codigoVendedor);
            if (hojaMaestraCredito != null) {
                if (hojaMaestraCredito.getListaCabeceraHojaMaestraCredito() != null && hojaMaestraCredito.getListaCabeceraHojaMaestraCredito().size() > 0) {
                    lstCabeceraHojaMaestraCredito.addAll(hojaMaestraCredito.getListaCabeceraHojaMaestraCredito());
                }
                if (hojaMaestraCredito.getListaDiaDemoraTrasVencimiento() != null && hojaMaestraCredito.getListaDiaDemoraTrasVencimiento().size() > 0) {
                    lstDiaDemoraTrasVencimiento.addAll(hojaMaestraCredito.getListaDiaDemoraTrasVencimiento());
                }
                if (hojaMaestraCredito.getListaHistorialPago() != null && hojaMaestraCredito.getListaHistorialPago().size() > 0) {
                    lstHistorialPago.addAll(hojaMaestraCredito.getListaHistorialPago());
                }
                if (hojaMaestraCredito.getListaValorPorVencer() != null && hojaMaestraCredito.getListaValorPorVencer().size() > 0) {
                    lstValorPorVencer.addAll(hojaMaestraCredito.getListaValorPorVencer());
                }
                if (hojaMaestraCredito.getListaNotaCredito() != null && hojaMaestraCredito.getListaNotaCredito().size() > 0) {
                    lstNotaCredito.addAll(hojaMaestraCredito.getListaNotaCredito());
                }
                if (hojaMaestraCredito.getListaProtesto() != null && hojaMaestraCredito.getListaProtesto().size() > 0) {
                    lstProtesto.addAll(hojaMaestraCredito.getListaProtesto());
                }
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("No se cargo Hoja maestra de credito para el vendedor ").append(codigoVendedor).toString());
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
            Util.escribirErrorAArchivoLog((new StringBuilder()).append("No se cargo Hoja maestra de credito para el vendedor ").append(codigoVendedor).toString());
        }
        if (lstCabeceraHojaMaestraCredito != null && lstCabeceraHojaMaestraCredito.size() > 0) {
            SqlCabeceraHojaMaestraCreditoImpl sqlCabeceraHojaMaestraCreditoImpl = new SqlCabeceraHojaMaestraCreditoImpl();
            queries = sqlCabeceraHojaMaestraCreditoImpl.eliminarCabeceraHojaMaestraCredito(codigoVendedor,queries);
            try {
                queries = sqlCabeceraHojaMaestraCreditoImpl.insertarListaCabeceraHojaMaestraCredito(lstCabeceraHojaMaestraCredito, codigoVendedor,queries);
                //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(codigoVendedor).toString()))).toString());
            } catch (Exception e) {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
                //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(codigoVendedor).toString()))).toString());
            }
        }
        if (lstDiaDemoraTrasVencimiento != null && lstDiaDemoraTrasVencimiento.size() > 0) {
            SqlDiaDemoraTrasVencimientoImpl sqlDiaDemoraTrasVencimientoImpl = new SqlDiaDemoraTrasVencimientoImpl();
            queries = sqlDiaDemoraTrasVencimientoImpl.eliminarListaDiaDemoraTrasVencimiento(codigoVendedor,queries);
            queries = sqlDiaDemoraTrasVencimientoImpl.insertarListaDiaDemoraTrasVencimiento(lstDiaDemoraTrasVencimiento, codigoVendedor,queries);
        }
        if (lstHistorialPago != null && lstHistorialPago.size() > 0) {
            SqlHistorialPagoImpl sqlHistorialPagoImpl = new SqlHistorialPagoImpl();
            queries = sqlHistorialPagoImpl.eliminarListaHistorialPago(codigoVendedor,queries);
            queries = sqlHistorialPagoImpl.insertarListaHistorialPago(lstHistorialPago, codigoVendedor,queries);
        }
        if (lstValorPorVencer != null && lstValorPorVencer.size() > 0) {
            SqlValorPorVencerImpl sqlValorPorVencerImpl = new SqlValorPorVencerImpl();
            queries = sqlValorPorVencerImpl.eliminarListaValorPorVencer(codigoVendedor, queries);
            queries = sqlValorPorVencerImpl.insertarListaValorPorVencer(lstValorPorVencer, codigoVendedor,queries);
        }
        if (lstNotaCredito != null && lstNotaCredito.size() > 0) {
            SqlNotaCreditoImpl sqlNotaCreditoImpl = new SqlNotaCreditoImpl();
            queries = sqlNotaCreditoImpl.eliminarListaNotaCredito(codigoVendedor,queries);
            queries = sqlNotaCreditoImpl.insertarListaNotaCredito(lstNotaCredito, codigoVendedor,queries);
        }
        if (lstProtesto != null && lstProtesto.size() > 0) {
            SqlProtestoImpl sqlProtestoImpl = new SqlProtestoImpl();
            queries = sqlProtestoImpl.eliminarListaProtesto(codigoVendedor,queries);
            queries = sqlProtestoImpl.insertarListaProtesto(lstProtesto, codigoVendedor,queries);
        }
        return queries;
    }

    public static List<ClsQueries> sincronizaBancoCliente(String codigoVendedor,List<ClsQueries> queries) {
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("BANCO CLIENTE ").append(codigoVendedor).toString());
        List lstBancoCliente = new ArrayList();
        try {
            List lstBanco = SCobranza.obtenerBancosAsociadosXVendedor(Util.completarCeros(10, codigoVendedor));
            if (lstBanco.size() > 0) {
                BancoCliente bancoCliente;
                for (Iterator i$ = lstBanco.iterator(); i$.hasNext(); lstBancoCliente.add(bancoCliente)) {
                    String banco = (String) i$.next();
                    String arregloBanco[] = banco.trim().split("[\254]");
                    bancoCliente = new BancoCliente();
                    bancoCliente.setCodigoCliente(Util.eliminarCerosInicios(arregloBanco[1].trim()));
                    bancoCliente.setNroCtaBcoCliente(arregloBanco[4]);
                    bancoCliente.setIdBancoCliente(arregloBanco[3]);
                    bancoCliente.setDescripcionBancoCliente(arregloBanco[5]);
                }

            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
        }
        if (lstBancoCliente.size() > 0) {
            SqlBancoClienteImpl sqlBancoClienteImpl = new SqlBancoClienteImpl();
            queries = sqlBancoClienteImpl.eliminarBancoCliente(codigoVendedor,queries);
            //try {
            queries = sqlBancoClienteImpl.insertarListaBancoCliente(lstBancoCliente, codigoVendedor, queries);
            /*    VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("BANCO CLIENTE ").append(codigoVendedor).toString()))).toString());
            } catch (Exception e) {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("BANCO CLIENTE ").append(codigoVendedor).toString()))).toString());
                resultado = false;
            }*/
        }
        return queries;
    }

    public static boolean sincronizacionSecuenciaPorVendedor(String strCodigoVendedor) {
        try {
            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("SECUENCIA POR VENDEDOR ").append(strCodigoVendedor).toString());
            SCobranza sCobranzas = new SCobranza();
            Secuencia secuencia = sCobranzas.obtenerSecuenciaPorVendedor(strCodigoVendedor);
            if (secuencia != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("SECUENCIA ").append(strCodigoVendedor).toString()))).toString());
                Util.grabarSecuenciaPorVendedor(secuencia);
            }
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("SECUENCIA ").append(strCodigoVendedor).toString()))).toString());
            return false;
        }
        return true;
    }

    public static List<ClsQueries> sincronizaTablaPartidasIndividualesAbiertas(String strCodigoVendedor,List<ClsQueries> queries) {
        List lstPartidaIndividualAbierta = new ArrayList();
        List lstDetallePagoPartidaIndividualAbierta = new ArrayList();
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString());
        SCobranza sCobranzas = new SCobranza();
        long contador = 1L;
        Object listas[] = sCobranzas.obtenerArregloDeListaPartidaIndividualAbiertaXVendedor(strCodigoVendedor, Util.convierteFechaAFormatoYYYYMMdd(new Date()), contador);
        List listaPartidaIndividualAbierta = (List) listas[0];
        lstPartidaIndividualAbierta.addAll(listaPartidaIndividualAbierta);
        List listaDetallePagoPartidaIndividualAbierta = (List) listas[1];
        lstDetallePagoPartidaIndividualAbierta.addAll(listaDetallePagoPartidaIndividualAbierta);
        if (lstPartidaIndividualAbierta.size() > 0) {
            SqlPartidaIndividualAbiertaImpl sqlPartidaIndividualAbiertaImpl = new SqlPartidaIndividualAbiertaImpl();
            queries = sqlPartidaIndividualAbiertaImpl.eliminarListaPartidaIndividualAbierta(strCodigoVendedor,queries);
            queries = sqlPartidaIndividualAbiertaImpl.insertarListaPartidaIndividualAbierta(lstPartidaIndividualAbierta, strCodigoVendedor,queries);
        }
        if (lstDetallePagoPartidaIndividualAbierta.size() > 0) {
            SqlDetallePagoPartidaIndividualAbiertaImpl sqlDetallePagoPartidaIndividualAbiertaImpl = new SqlDetallePagoPartidaIndividualAbiertaImpl();
            queries = sqlDetallePagoPartidaIndividualAbiertaImpl.eliminarListaDetallePagoPartidaIndividualAbierta(strCodigoVendedor,queries);
            queries = sqlDetallePagoPartidaIndividualAbiertaImpl.insertarListaDetallePagoPartidaIndividualAbierta(lstDetallePagoPartidaIndividualAbierta, strCodigoVendedor,queries);
        }
        return queries;
    }

    
    //TODO
    public static List<ClsQueries> sincronizaMaterialConsultaDinamica(String strCodigoVendedor,List<ClsQueries> queries) {
        List<List<Material>> listaTopCli;
        List<List<Material>> listaTopTipo;
        SqlMaterial sqlMaterial = new SqlMaterialImpl();
        try {
        	if(listaTodosMaterial.size()==0){
        		listaTodosMaterial = sqlMaterial.obtenerTodosMateriales2();
        	}
            VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("CONSULTA DINAMICA POR VENDEDOR ").append(strCodigoVendedor).toString());
            listaTopCli = SPedidos.listaMaterialesTop(strCodigoVendedor);
            if (listaTopCli != null && listaTopCli.size() > 0 && listaTopCli.get(0) != null) {
                List<Material> listaMaterial = buscarJerarquiaMateriales(listaTodosMaterial, (List) listaTopCli.get(0), "Consultando por cliente.");
                //queries = sqlMaterial.eliminarMaterialTopCliente(strCodigoVendedor,queries);
                queries = sqlMaterial.migrarMaterialesTopCliente2(listaTopCli, listaMaterial, strCodigoVendedor,queries);
                listaMaterial.clear();
            }
            listaTopTipo = SPedidos.listaMaterialesTopTipol(strCodigoVendedor);
            if (listaTopTipo != null && listaTopTipo.size() > 0 && listaTopTipo.get(0) != null) {
                List<Material> listaMaterialtipologia = buscarJerarquiaMateriales(listaTodosMaterial, (List) listaTopTipo.get(0), "Consultando por tipologia.");
                //queries = sqlMaterial.eliminarMaterialTopTipologia(strCodigoVendedor,queries);
                queries = sqlMaterial.migrarMaterialesTopTipologia2(listaTopTipo, listaMaterialtipologia, strCodigoVendedor, queries);
                listaMaterialtipologia.clear();
            }

        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
        }

        
        return queries;
    }

    private static List<ClsQueries> sincronizaDestinatario(String strCodigoVendedor, List<ClsQueries> queries) {
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("DESTINATARIO ").append(strCodigoVendedor).toString());
        try {
            List listaDestinatario = SPedidos.listarSucursales(strCodigoVendedor);
            if (listaDestinatario != null && listaDestinatario.size() > 0) {
                SqlSedeImpl sqlsede = new SqlSedeImpl();
                queries = sqlsede.setEliminarSede(strCodigoVendedor,queries);
                queries = sqlsede.setInsertarSede(listaDestinatario, strCodigoVendedor, queries);                
            }
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("CLIENTE POR VENDEDOR ").append(strCodigoVendedor).toString()))).toString());
        }
        Util.pasarGarbageCollector();
        return queries;
    }

    
    public static List<ClsQueries> sincronizarTablaCliente(String strCodigoVendedor,List<ClsQueries> queries) {
        VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial((new StringBuilder()).append("CLIENTE POR VENDEDOR ").append(strCodigoVendedor).toString());
        try {
            List<Cliente> listaClientePorVendedor = RFC.obtenerListaCliente(strCodigoVendedor, "", "");
            if (listaClientePorVendedor.size() > 0) {
                SqlClienteImpl sqlCliente = new SqlClienteImpl();
                queries = sqlCliente.eliminarCliente(strCodigoVendedor, queries);
                queries = sqlCliente.insertarCliente2(strCodigoVendedor, listaClientePorVendedor, queries);
            }
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("CLIENTE POR VENDEDOR ").append(strCodigoVendedor).toString()))).toString());
        }
        Util.pasarGarbageCollector();
        return queries;
    }

    public static List<Material> buscarJerarquiaMateriales(List<Material> listaTodosMateriales, List<Material> listaMateriales, String mensaje) {
        List<Material> listaMaterialFinal = new ArrayList();
        for (Material material : listaMateriales) {
            for (Material m : listaTodosMateriales) {
                if (material.getStrMATNR().equals(m.getStrMATNR())) {
                    material.setStrMATNR(m.getStrMATNR());
                    material.setStrStock(m.getStrStock());
                    material.setStrSU(m.getStrSU());
                    material.setStrShortText(m.getStrShortText().replaceAll("'", ""));
                    material.setStrTextLine(m.getStrTextLine().replaceAll("'", ""));
                    material.setStrTargetQTY(m.getStrTargetQTY());
                    material.setStrPrice1(m.getStrPrice1());
                    material.setStrPrice2(m.getStrPrice2());
                    material.setStrPrice3(m.getStrPrice3());
                    material.setStrPrice4(m.getStrPrice4());
                    material.setStrPRDHA(m.getStrPRDHA());
                    material.setTipoMaterial(m.getTipoMaterial());
                    material.setStrNORMT(m.getStrNORMT());
                    material.setStrZZORDCO(m.getStrZZORDCO());
                    material.setStrCellDesign(m.getStrCellDesign());
                    material.setMTART(m.getMTART());
                    material.setStrTypeMat(m.getStrTypeMat());
                    material.setStrGrupoCompra(m.getStrGrupoCompra());
                    material.setStrSt1(m.getStrSt1());
                    listaMaterialFinal.add(material);
                }
            }
        }

        return listaMaterialFinal;
    }

    private static void activaSincronizacion(int idSincronizador, String codigoVendedor) throws Exception {
        int cantidadRegistros = 0;
        Calendar inicioCalendario = Calendar.getInstance();
        long inicio = inicioCalendario.getTimeInMillis();
        switch (idSincronizador) {
            case 1:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_USUARIO", codigoVendedor);
                break;
            case 2:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_ROL", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_USUARIO_ROL", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VISTA_ROL", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VISTA", codigoVendedor);
                break;
            case 3:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_AGENDA", codigoVendedor);
                break;
            case 4:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_BLOQUEO_ENTREGA", codigoVendedor);
                break;
            case 5:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CLASE_MATERIAL", codigoVendedor);
                break;
            case 6:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CLIENTE", codigoVendedor);
                break;
            case 7:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_COMBO", codigoVendedor);
                break;
            case 8:
                int cant1 = DAO.filasTabla("PROFFLINE_TB_CONDICION_1X", codigoVendedor);
                int cant2 = DAO.filasTabla("PROFFLINE_TB_CONDICION_2X", codigoVendedor);
                int cant3 = DAO.filasTabla("PROFFLINE_TB_CONDICION_3X", codigoVendedor);
                int cant4 = DAO.filasTabla("PROFFLINE_TB_CONDICION_4X", codigoVendedor);
                int cant5 = DAO.filasTabla("PROFFLINE_TB_CONDICION_5X", codigoVendedor);
                int cant6 = DAO.filasTabla("PROFFLINE_TB_CONDICION_ESCALAS", codigoVendedor);
                int cant7 = DAO.filasTabla("PROFFLINE_TB_CONDICION_ZPR1", codigoVendedor);
                cantidadRegistros = cant1 + cant2 + cant3 + cant4 + cant5 + cant6 + cant7;
                break;
            case 9:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CONDICIONES_PAGO", codigoVendedor);
                break;
            case 10:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_SEDE", codigoVendedor);
                break;
            case 11:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_DISPOSITIVO", codigoVendedor);
                break;
            case 12:
                break;
            case 13:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_FERIADO", codigoVendedor);
                break;
            case 14:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_JERARQUIA", codigoVendedor);
                break;
            case 15:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL", codigoVendedor);
                break;
            case 16:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_STOCK", codigoVendedor);
                break;
            case 17:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_TIPOLOGIA", codigoVendedor);
                break;
            case 18:
                cantidadRegistros = (DAO.filasTabla("PROFFLINE_TB_MATERIAL_PROMO_OFERTA", codigoVendedor)
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_DSCTO_POLITICA", codigoVendedor)
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_DSCTO_MANUAL", codigoVendedor)
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_TOP_CLIENTE", codigoVendedor)
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA", codigoVendedor));
               
                break;
            case 19:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_BANCO_CLIENTE", codigoVendedor);
                break;
            case 20:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_HISTORIAL_PAGO", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VALOR_POR_VENCER", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_NOTA_CREDITO", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_PROTESTO", codigoVendedor);
                break;
            case 21:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PARTIDA_INDIVIDUAL", codigoVendedor);
                break;
            case 22:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PEDIDO_PENDIENTE_DEVOLUCION", codigoVendedor);
                break;
            case 23:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_FORMA_PAGO_COBRANZA", codigoVendedor);
                break;
            case 24:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_FORMA_PAGO_ANTICIPO", codigoVendedor);
                break;
            case 25:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_BANCO_PROMESA", codigoVendedor);
                break;
            case 26:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA", codigoVendedor);
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA", codigoVendedor);
                break;
            case 27:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_SECUENCIA", codigoVendedor);
                break;
            case 28:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_ZTCONSTANTE", codigoVendedor);
                break;
            case 31:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PRESUPUESTO", codigoVendedor);
                break;
            case 32:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MARCA_ESTRATEGICA", codigoVendedor);
                break;
            case 33:
            	cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_NUEVO","");
            	break;
            case 34:
            	cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_VENTA_CRUZADA","");
            	cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VENTA_CRUZADA","");
            	break;
            case 35:
            	cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_MERCADEO","");
            	cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_MATERIAL_PROMOCION","");
            	break;
            default:
                cantidadRegistros = 0;
                break;
        }
        Calendar finCalendario = Calendar.getInstance();
        long fin = finCalendario.getTimeInMillis();
        String strTie = Util.convierteMilisegundosAFormatoMMSS(fin - inicio);
        SincronizadorPedido sincronizadorPedido = new SincronizadorPedido();
        sincronizadorPedido.setNumIdeSinc("" + idSincronizador);
        sincronizadorPedido.setNumTie(strTie);
        sincronizadorPedido.setTxtFecHor(Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()));
        sincronizadorPedido.setNumCantReg(cantidadRegistros + "");
        DAO.actualizarSincronizar(sincronizadorPedido, codigoVendedor);
    }
    
    public static void activaSincronizacionDatoMaestra(int idSincronizador) throws Exception {
        int cantidadRegistros = 0;
        Calendar inicioCalendario = Calendar.getInstance();
        long inicio = inicioCalendario.getTimeInMillis();

        switch (idSincronizador) {
            case 1:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_USUARIO", "");
                break;
            case 2:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_ROL", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_USUARIO_ROL", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VISTA_ROL", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VISTA", "");
                break;
            case 3:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_AGENDA", "");
                break;
            case 4:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_BLOQUEO_ENTREGA", "");
                break;
            case 5:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CLASE_MATERIAL", "");
                break;
            case 6:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CLIENTE", "");
                break;
            case 7:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_COMBO", "");
                break;
            case 8:
                int cant1 = DAO.filasTabla("PROFFLINE_TB_CONDICION_1X", "");
                int cant2 = DAO.filasTabla("PROFFLINE_TB_CONDICION_2X", "");
                int cant3 = DAO.filasTabla("PROFFLINE_TB_CONDICION_3X", "");
                int cant4 = DAO.filasTabla("PROFFLINE_TB_CONDICION_4X", "");
                int cant5 = DAO.filasTabla("PROFFLINE_TB_CONDICION_5X", "");
                int cant6 = DAO.filasTabla("PROFFLINE_TB_CONDICION_ESCALAS", "");
                int cant7 = DAO.filasTabla("PROFFLINE_TB_CONDICION_ZPR1", "");
                cantidadRegistros = cant1 + cant2 + cant3 + cant4 + cant5 + cant6 + cant7;
                break;
            case 9:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CONDICIONES_PAGO", "");
                break;
            case 10:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_SEDE", "");
                break;
            case 11:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_DISPOSITIVO", "");
                break;
            case 12:
                break;
            case 13:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_FERIADO", "");
                break;
            case 14:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_JERARQUIA", "");
                break;
            case 15:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL", "");
                break;
            case 16:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_STOCK", "");
                break;
            case 17:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_TIPOLOGIA", "");
                break;
            case 18:
                cantidadRegistros = (DAO.filasTabla("PROFFLINE_TB_MATERIAL_PROMO_OFERTA", "")
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_DSCTO_POLITICA", "")
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_DSCTO_MANUAL", "")
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_TOP_CLIENTE", "")
                        + DAO.filasTabla("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA", ""));
                break;
            case 19:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_BANCO_CLIENTE", "");
                break;
            case 20:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_HISTORIAL_PAGO", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VALOR_POR_VENCER", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_NOTA_CREDITO", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_PROTESTO", "");
                break;
            case 21:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PARTIDA_INDIVIDUAL", "");
                break;
            case 22:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PEDIDO_PENDIENTE_DEVOLUCION", "");
                break;
            case 23:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_FORMA_PAGO_COBRANZA", "");
                break;
            case 24:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_FORMA_PAGO_ANTICIPO", "");
                break;
            case 25:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_BANCO_PROMESA", "");
                break;
            case 26:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA", "");
                cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA", "");
                break;
            case 27:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_SECUENCIA", "");
                break;
            case 28:
                cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_ZTCONSTANTE", "");
                break;
            case 33:
            	cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_NUEVO","");
            	break;
            case 34:
            	cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_VENTA_CRUZADA","");
            	cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_VENTA_CRUZADA","");
            	break;
            case 35:
            	cantidadRegistros = DAO.filasTabla("PROFFLINE_TB_MATERIAL_MERCADEO","");
            	cantidadRegistros += DAO.filasTabla("PROFFLINE_TB_MATERIAL_PROMOCION","");
            	break;
            default:
                cantidadRegistros = 0;
                break;
        }
        Calendar finCalendario = Calendar.getInstance();
        long fin = finCalendario.getTimeInMillis();
        String strTie = Util.convierteMilisegundosAFormatoMMSS(fin - inicio);
        SincronizadorPedido sincronizadorPedido = new SincronizadorPedido();
        sincronizadorPedido.setNumIdeSinc("" + idSincronizador);
        sincronizadorPedido.setNumTie(strTie);
        sincronizadorPedido.setTxtFecHor(Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()));
        sincronizadorPedido.setNumCantReg(cantidadRegistros + "");
        DAO.actualizarSincronizar(sincronizadorPedido, "");
    }
    public static void main(String args[]){
//		System.out.println("MES ACTUAL: " + getMesActual());
//		System.out.println("ANIO ACTUAL: " + getAnioActual());
    	try {
    		sincronizarVentaCruzada("0000080327",new ArrayList());
		} catch (Exception e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		}
	}

}
