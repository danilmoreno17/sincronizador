package com.proffline.sincronizador.tareas;

import com.proffline.sincronizador.constantes.Constante;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.mail.MotorEnvioCorreo;
import com.proffline.sincronizador.sqlite.DAO;
import com.proffline.sincronizador.utilidades.Util;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramadorTareas {

    public static boolean estaOcupado = false;
    private static Integer frecuencia;

    static {
        try {
            Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            frecuencia = Integer.parseInt(props.getProperty("server.frecuenciaSegundos").trim()) * 1000;
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void activarTiempoConexion() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                try {
                    if (!estaOcupado) {
                        Properties props = new Properties();
                        InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
                        props.load(in);
                        String strHoraConexion = props.getProperty("server.horaSistema").trim();
                        frecuencia = Integer.parseInt(props.getProperty("server.frecuenciaSegundos").trim()) * 1000;
                        in.close();
                        String strHoraSistema = Util.convierteFechaHoyAFormatoHHMM(new Date());
                        if (strHoraConexion.equals(strHoraSistema)) {
//                        if (true) {
                            iniciarProcesoSincronizacion();// Aqui se inicia la sincronización Hacia proffline.sqlite de todos los vendedores
                            //  Aqui debe ir el metodo en el cual se debe crear(copiar o renombrar) para cada vendedor
                            //  una base de dato SQLite con nombre de la BBDD con el codigo del vendedor(db80053.sqlite)
                            //  este metodo tiene que se un loop.
                            //  iniciarProcesoInformacionPorVendedor();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void iniciarProcesoSincronizacion() {
//                Util.reiniciarBaseDatosRespaldo();
                Util.reiniciarBaseDatos();
                Calendar inicioCalendario = Calendar.getInstance();
                long inicio = inicioCalendario.getTimeInMillis();
                Util.limpiarArchivoLog();
                int cantidadTotalDeSincronizaciones = 26;
                VentanaPrincipal.obtenerInstancia().setMaximoProgresoTotal(cantidadTotalDeSincronizaciones);
                int contador = 0;
                estaOcupado = true;
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola("<html>");
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("USUARIO");
                Sincronizador.sincronizarTablaUsuario();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                
                //crearBasesPorVendedor(contador);
                
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("BLOQUEO ENTREGA");
                Sincronizador.sincronizarTablaBloqueoEntrega();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CLASE MATERIAL");
                Sincronizador.sincronizarTablaClaseMaterial();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);

                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("COMBO");
                Sincronizador.sincronizarTablaCombo();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONDICION 1");
                Sincronizador.sincronizarTablaCondicion1();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONDICION 2");
                Sincronizador.sincronizarTablaCondicion2();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONDICION PAGO");
                Sincronizador.sincronizarTablaCondicionPago();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONDICION PAGO DETALLE");
                Sincronizador.sincronizarTablaCondicionPagoDetalle();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);

                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("DISPOSITIVO");
                Sincronizador.sincronizarTablaDispositivo();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);

                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("FERIADO");
                Sincronizador.sincronizarTablaFeriado();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("JERARQUIA");
                Sincronizador.sincronizarTablaJerarquia();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("MATERIAL");
                Sincronizador.sincronizarTablaMaterial();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("MATERIAL STOCK");
                Sincronizador.sincronizarTablaMaterialStock();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("ROL");
                Sincronizador.sincronizarTablaRol();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("TIPOLOGIA");
                Sincronizador.sincronizarTablaTipologia();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("USUARIO ROL");
                Sincronizador.sincronizarTablaUsuarioRol();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("TIPO GESTION");
                Sincronizador.sincronizarTablaTipoGestion();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("VISTA");
                Sincronizador.sincronizarTablaVista();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("VISTA ROL");
                Sincronizador.sincronizarTablaVistaRol();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("FORMA PAGO COBRANZA");
                Sincronizador.sincronizarTablaFormaPagoCobranza();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("FORMA PAGO ANTICIPO");
                Sincronizador.sincronizarTablaFormaPagoAnticipo();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("BANCO PROMESA");
                Sincronizador.sincronizarTablaBancoPromesa();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONSULTA DINAMICA");
                Sincronizador.sincronizarTablaConsultaDinamica();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONDICIONES COMERCIALES");
                Sincronizador.sincronizarTablaCondicionesComerciales();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("MATERIAL NUEVO");
                Sincronizador.sincronizarTablaMaterialNuevo();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("MERCADEO");
                Sincronizador.sincronizarMercadeo();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("CONSTANTES");
                Sincronizador.sincronizadorTablaConstantes();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(cantidadTotalDeSincronizaciones);
                VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("");
                ProgramadorTareas.estaOcupado = false;
//                Util.generarToProffline();
//                Util.reiniciarDbTemplate();

                try {
                    for (int i = 0; i < Constante.ID_SINCRONIZADOR.length; i++) {
                        Sincronizador.activaSincronizacionDatoMaestra(Constante.ID_SINCRONIZADOR[i]);
                    }
                    VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_SINCRONIZACION ")));
                } catch (Exception e) {
                    VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_SINCRONIZACION ")));
                }

                crearBasesPorVendedor(contador);
                estaOcupado = false;
                Calendar finCalendario = Calendar.getInstance();
                long fin = finCalendario.getTimeInMillis();
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(4, Util.convierteSegundosAMinutos(fin - inicio)));
                VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(false);
                String mensaje = Util.convierteSegundosAMinutos(fin - inicio) + "\n\n" + Util.leerArchivoLog();
                try {
                    final Properties props = new Properties();
                    InputStreamReader in = null;
                    in = new InputStreamReader(new FileInputStream("smtp.properties"), "UTF-8");
                    props.load(in);
//                    if (MotorEnvioCorreo.enviarCorreo(props.getProperty("mail.smtp.asunto").trim(), mensaje, props.getProperty("mail.smtp.correoDestinatario").trim(), null, null, Util.NOMBRE_ARCHIVO)) {
                    if (MotorEnvioCorreo.envioCorreoElectronico(mensaje)) {
                        System.out.println("Se envia con exito el correo.");
                    } else {
                        System.out.println("No se envia con exito el correo.");
                    }
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void crearBasesPorVendedor(int contador) {
                List listaIdUsuario = DAO.obtenerListaIdUsuarios();
                if (listaIdUsuario != null) {
                    VentanaPrincipal.obtenerInstancia().setMaximoProgresoTotal(listaIdUsuario.size());
                    Util.generarBasesPorVendedor(listaIdUsuario, contador);
                    VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("");
                    Sincronizador.sincronizarPorVendedor(listaIdUsuario);
                    VentanaPrincipal.obtenerInstancia().establecerTextoABarraDeProgresoParcial("");
                }
            }

        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, frecuencia);
    }
}
