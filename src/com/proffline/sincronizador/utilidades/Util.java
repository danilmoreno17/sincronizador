package com.proffline.sincronizador.utilidades;

import com.proffline.sincronizador.bean.BeanSincronizacion;
import com.proffline.sincronizador.bean.BeanSincronizar;
import com.proffline.sincronizador.bean.Secuencia;
import com.proffline.sincronizador.conexion.ConexionJDBC;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.sqlite.ResultExecute;
import com.proffline.sincronizador.sqlite.ResultExecuteQuery;
import com.promesa.planificacion.sql.impl.SqlSincronizacionImpl;
import com.promesa.sincronizador.pedidos.sql.SqlMaterial;
import com.promesa.sincronizador.pedidos.sql.impl.SqlMaterialImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class Util {

    public static final String NOMBRE_ARCHIVO = "log.txt";

    /* CONVERSIÓN A DD.MM.YYYY */
    public static String convierteFecha(String fsf) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        try {
            String StringRecogido = fsf;
            Date datehora = sdf1.parse(StringRecogido);
            return sdf2.format(datehora);
        } catch (Exception e) {
            return null;
        }
    }

    public static String completarCeros(int cantidad, String cadena) {
        int restante = cantidad - cadena.length();
        String justificar = "";
        for (int i = 0; i < restante; i++) {
            justificar += "0";
        }
        return justificar + cadena;
    }

    /* CONVERSIÓN A YYYYMMDD */
    public static String convierteFechaHoyAFormatoYYYYMMDD(Date fechaHoy) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        return formato.format(fechaHoy);
    }
    public static void pasarGarbageCollector(){
        Runtime garbage = Runtime.getRuntime();
        garbage.gc();
    }
    /* CONVERSIÓN A HHMM */
    public static String convierteFechaHoyAFormatoHHMM(Date fechaHoy) {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(fechaHoy);
    }

    /* MENSAJE DE CONEXION EXITOSA A SAP */
    public static String mensajeConexionExitosaSAP(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " Se conecto a SAP correctamente para la carga de datos de la tabla " + nombreTabla + ".";
    }

    /* MENSAJE DE CONEXION NO EXITOSA A SAP */
    public static String mensajeConexionNoExitosaSAP(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " No se conecto a SAP correctamente para la carga de datos de la tabla " + nombreTabla + ".";
    }

    /* MENSAJE DE CARGA DE DATOS EXITOSA DE SAP */
    public static String mensajeCargaDatosExitosaSAP(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " Se cargo correctamente los datos de SAP para la tabla " + nombreTabla + ".";
    }

    /* MENSAJE DE LIMPIAR DATOS EXITOSA DE SQL */
    public static String mensajeLimpiarDatosExitosaSQL(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " Se limpio correctamente los datos de la tabla " + nombreTabla + ".";
    }

    /* MENSAJE DE LIMPIAR DATOS NO EXITOSA DE SQL */
    public static String mensajeLimpiarDatosNoExitosaSQL(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " No se limpio correctamente los datos de la tabla " + nombreTabla + ".";
    }

    /* MENSAJE DE CARGA DE DATOS EXITOSA DE SQL */
    public static String mensajeCargaDatosExitosaSQL(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " Se cargo correctamente los datos a la tabla " + nombreTabla + ".";
    }

    /* MENSAJE DE CARGA DE DATOS NO EXITOSA DE SQL */
    public static String mensajeCargaDatosNoExitosaSQL(String nombreTabla) {
        return "(" + Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()) + ")" + " No se cargo correctamente los datos a la tabla " + nombreTabla + ".";
    }

    public static String mensajeGeneradorToProffline() {
        return (new StringBuilder()).append("(").append(convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date())).append(")").append(" Se genero Proffline.").toString();
    }

    public static String mensajeNumerosDeIntentos(int intentos) {
        return (new StringBuilder()).append("(").append(convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date())).append(")").append(" Intento Cambiar nombre a bases: ").append(intentos).toString();
    }

    public static String mensajeInicioGeneradorBasesVendedor() {
        return (new StringBuilder()).append("(").append(convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date())).append(")").append(" Empezo a generar Base por vendedor.").toString();
    }

    public static String mensajeGeneraBasesPorCliente(String strCodigoVendedor) {
        return (new StringBuilder()).append("(").append(convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date())).append(")").append(" Se genero Base para el vendedor con codigo ").append(strCodigoVendedor).append(".").toString();
    }

    /* CONVERSIÓN DE TEXTO SIMPLE A FORMATO HTML */
    public static String convierteTextoAFormatoHTML(int codigo, String texto) {
        switch (codigo) {
            case 1:
                texto = "<font size='3' color='black' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado a la Base de Datos.
                break;
            case 2:
                texto = "<font size='3' color='black' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado a SAP.
                break;
            case 3:
                texto = "<font size='3' color='red' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado a las excepciones.
                break;
            case 4:
                texto = "<font size='3' color='black' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado al tiempo de duraciòn.
                break;
        }
        return texto;
    }

    /* CONVERSIÓN A DDMMYYYYHHMMSSAA */
    public static String convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(Date fechaHoy) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
        return formato.format(fechaHoy);
    }

    /* CONVERSIÓN DE SEGUNDOS A MINUTOS */
    public static String convierteSegundosAMinutos(long milisegundos) {
        int minutos = 0;
        long segundos = milisegundos / 1000l;
        while (segundos >= 60) {
            segundos -= 60;
            minutos++;
        }
        if (minutos == 0) {
            return "Tiempo total de sincronizacion : " + segundos + " segundos.";
        } else if (minutos == 1) {
            if (segundos != 0) {
                return "Tiempo total de sincronizacion : " + minutos + " minuto y " + segundos + " segundos.";
            } else {
                return "Tiempo total de sincronizacion : " + minutos + " minuto.";
            }
        } else {
            if (segundos != 0) {
                return "Tiempo total de sincronizacion : " + minutos + " minutos y " + segundos + " segundos.";
            } else {
                return "Tiempo total de sincronizacion : " + minutos + " minutos.";
            }
        }
    }

    /* OBTIENE LA TRAZA DEL ERROR LANZADO EN EL CATCH */
    public static String getStackTrace(Throwable e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        return writer.toString();
    }

    /* LEE EL ARCHIVO log.txt */
    public static String leerArchivoLog() {
        String mensaje = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(NOMBRE_ARCHIVO);
            if (!archivo.exists()) {
                escribirErrorAArchivoLog("");
                archivo = new File(NOMBRE_ARCHIVO);
            }
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                mensaje += linea.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mensaje;
    }

    /* LIMPIA EL ARCHIVO log.txt */
    public static void limpiarArchivoLog() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(NOMBRE_ARCHIVO);
            pw = new PrintWriter(fichero);
            pw.print("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* ESCRIBE EL ARCHIVO log.txt */
    public static void escribirErrorAArchivoLog(String error) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(NOMBRE_ARCHIVO, true);
            pw = new PrintWriter(fichero);
            pw.println(error);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String eliminarCerosInicios(String cadena) {
        String subCadena = "";
        int pos = 0;
        int i = 0;
        do {
            if (i >= cadena.length()) {
                break;
            }
            if (cadena.charAt(i) != '0') {
                pos = i;
                break;
            }
            i++;
        } while (true);
        subCadena = cadena.substring(pos, cadena.length());
        return subCadena;
    }

//    public static List<String> obtenerListaNombreArchivosFTP() {
//        List<String> listaNombreArchivosFTP = new ArrayList<String>();
//        FTPClient client = new FTPClient();
//        String sFTP = "10.10.10.19";
//        String sUser = "proffline";
//        String sPassword = "sunday*8000";
//        int reply = 0;
//        try {
//            client.connect("10.10.10.19");
//            boolean login = client.login("proffline", "sunday*8000");
//            if (login) {
//            reply = client.getReplyCode();
//            if (FTPReply.isPositiveCompletion(reply)) {
//                client.changeWorkingDirectory("/instalador-proffline");
//                FTPFile[] archivosFTP = client.listFiles();
//                if (archivosFTP.length > 0){
//                    for(FTPFile arch : archivosFTP){
//                        listaNombreArchivosFTP.add(arch.getName());
//                    }
//                }
//            } else {
//                client.disconnect();
//            }
//        } else {
//            System.out.println("No se logueo.");
//        }
//        client.logout();
//        client.disconnect();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        return listaNombreArchivosFTP;
//    }
    public static void reiniciarBaseDatosRespaldo() {
        try {
            Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            File ficheroOrigen = new File(props.getProperty("local.plantilla").trim());
            File fichecoRespaldo = new File(props.getProperty("local.respaldo").trim());
            if (!fichecoRespaldo.exists()) {
                InputStream inS = new FileInputStream(ficheroOrigen);
                OutputStream outS = new FileOutputStream(fichecoRespaldo);
                byte buf[] = new byte[1024];
                int len;
                while ((len = inS.read(buf)) > 0) {
                    outS.write(buf, 0, len);
                }
                inS.close();
                outS.close();
            }
        } catch (Exception e) {
            escribirErrorAArchivoLog(getStackTrace(e));
            e.printStackTrace();
        }
    }

    public static void reiniciarBaseDatos() {
        try {
            Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            File ficheroOrigen = new File(props.getProperty("local.plantilla").trim());
            File fichecoRespaldo = new File(props.getProperty("local.directorio").trim());
            InputStream inS = new FileInputStream(ficheroOrigen);
            OutputStream outS = new FileOutputStream(fichecoRespaldo);
            byte buf[] = new byte[1024];
            int len;
            while ((len = inS.read(buf)) > 0) {
                outS.write(buf, 0, len);
            }
            inS.close();
            outS.close();
        } catch (Exception e) {
            escribirErrorAArchivoLog(getStackTrace(e));
            e.printStackTrace();
        }
    }

    public static boolean generarToProffline() {
        InputStreamReader in = null;
        try {
            File ficheroProffline;
            File ficheroTemplate;
            int i;
            Properties props = new Properties();
            in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            ficheroProffline = new File(props.getProperty("local.directorio").trim());
            ficheroTemplate = new File(props.getProperty("local.plantilla").trim());
            i = 0;
            boolean flag = false;

            while (!flag && i < 3) {
                if (ficheroProffline.exists()) {
                    ficheroProffline.delete();
                    flag = ficheroTemplate.renameTo(ficheroProffline);
                } else {
                    flag = ficheroTemplate.renameTo(ficheroProffline);
                }
                if (flag) {
                    VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(convierteTextoAFormatoHTML(1, mensajeGeneradorToProffline())).toString());
                    return true;
                }
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(convierteTextoAFormatoHTML(1, mensajeNumerosDeIntentos(i))).toString());
                i++;
                Thread.sleep(3000L);
            }
            return false;
        } catch (Exception ex) {
            escribirErrorAArchivoLog(getStackTrace(ex));
            return false;
        }
    }

    public static void reiniciarDbTemplate() {
        try {
            Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            File ficheroOrigen = new File(props.getProperty("local.respaldo").trim());
            File fichecoDestino = new File(props.getProperty("local.plantilla").trim());
            InputStream inS = new FileInputStream(ficheroOrigen);
            OutputStream outS = new FileOutputStream(fichecoDestino);
            byte buf[] = new byte[1024];
            int len;
            while ((len = inS.read(buf)) > 0) {
                outS.write(buf, 0, len);
            }
            inS.close();
            outS.close();
            ficheroOrigen.delete();
        } catch (Exception e) {
            escribirErrorAArchivoLog(getStackTrace(e));
            e.printStackTrace();
        }
    }

    public static boolean generarBasesPorVendedor(List listaIdentificacion, int contador) {
        try {
            Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("conexion.properties"), "UTF-8");
            props.load(in);
            File ficheroOrigen = new File(props.getProperty("local.directorio").trim());
            String pathGen = props.getProperty("local.directorio").trim();
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(convierteTextoAFormatoHTML(1, mensajeInicioGeneradorBasesVendedor())).toString());
            OutputStream outS;
            for (int i = 0; i < listaIdentificacion.size(); i++) {
//            for (int i = 0; i < 5; i++) {
//            for (Iterator i$ = listaIdentificacion.iterator(); i$.hasNext(); outS.close()) {
//                String strIdentificacion = (String) i$.next();
                String strIdentificacion = listaIdentificacion.get(i).toString();
                String strFilePathVendedor = (new StringBuilder()).append(pathGen).append("_").append(strIdentificacion).toString();
                VentanaPrincipal.obtenerInstancia().setProgresoTotal(contador++);
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(convierteTextoAFormatoHTML(1, mensajeGeneraBasesPorCliente(strIdentificacion))).toString());
                File fileVendedor = new File(strFilePathVendedor);
                InputStream inS = new FileInputStream(ficheroOrigen);
                outS = new FileOutputStream(fileVendedor);
                byte buf[] = new byte[1024];
                int len;
                while ((len = inS.read(buf)) > 0) {
                    outS.write(buf, 0, len);
                }
                inS.close();
            }

        } catch (Exception ex) {
            escribirErrorAArchivoLog(getStackTrace(ex));
            return false;
        }
        return true;
    }
    public static String convierteCadenaAFormatoYYYYMMDD(String strFecha) {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.US);
		Date date = null;
		try {
			date = formatoDelTexto.parse(strFecha);
		} catch (ParseException ex) {
			date = null;
		}
		return "" + formatoDeFecha.format(date);
	}
    public static String convierteFechaAFormatoYYYYMMdd(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return (new StringBuilder()).append("").append(formatter.format(fecha)).toString();
    }

    public static void grabarSecuenciaPorVendedor(Secuencia sec) {
        try {
            if (verificarVendedorEnTablaSecuencia(sec.getCodigoVendedor())) {
                actualizarSecuencia(sec);
            } else {
                insertarSecuenciaPorVendedor(sec);
            }
        } catch (Exception e) {
            e.printStackTrace();
            escribirErrorAArchivoLog(getStackTrace(e));
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(convierteTextoAFormatoHTML(3, mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("SECUENCIA ").append(sec.getCodigoVendedor()).toString()))).toString());
        }
    }

    public static void actualizarSecuencia(Secuencia sec) {
        ConexionJDBC conn = new ConexionJDBC();
        try {
            Connection connection = conn.obtenerConexionPorVendedor(sec.getCodigoVendedor());
            Statement stmt = null;
//            String sql = (new StringBuilder()).append("UPDATE PROFFLINE_TB_SECUENCIA SET TXTSECUENCIA = '").append(secuenciaStock).append("' ").append("WHERE TXTIDEMPLEADO = '").append(codigoVendedor).append("';").toString();
            String sql = "UPDATE PROFFLINE_TB_SECUENCIA SET TXTSECUENCIA = '" + sec.getSecuenciaCobranza() + "', SEC_PEDIDO='" + sec.getSecuenciaPedido() + "' WHERE TXTIDEMPLEADO='" + sec.getCodigoVendedor() + "';";
            stmt = connection.createStatement();
            stmt.execute(sql);
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            escribirErrorAArchivoLog(getStackTrace(ex));
        }
    }

    public static void insertarSecuenciaPorVendedor(Secuencia sec) throws Exception {
        ConexionJDBC conn = new ConexionJDBC();
        Connection connection = conn.obtenerConexionPorVendedor(sec.getCodigoVendedor());
        Statement stmt = null;
//        String sql = (new StringBuilder()).append("INSERT INTO PROFFLINE_TB_SECUENCIA (txtIdEmpleado, txtSecuencia) VALUES ('").append(codigoVendedor).append("','").append(secuencia).append("');").toString();
        String sql = "INSERT INTO PROFFLINE_TB_SECUENCIA (txtIdEmpleado, txtSecuencia, sec_pedido) VALUES ('" + sec.getCodigoVendedor() + "','" + sec.getSecuenciaCobranza() + "','" + sec.getSecuenciaPedido() + "');";
        stmt = connection.createStatement();
        stmt.execute(sql);
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static boolean verificarVendedorEnTablaSecuencia(String codigoVendedor) {
        boolean resultado = false;
        HashMap mapResultado = new HashMap();
        HashMap column = new HashMap();
        HashMap res = null;
        ResultExecuteQuery resultExecuteQuery = null;
        String codigo = "";
        column.put("String:0", "TXTIDEMPLEADO");
        String sql = (new StringBuilder()).append("SELECT TXTIDEMPLEADO FROM PROFFLINE_TB_SECUENCIA WHERE TXTIDEMPLEADO = '").append(codigoVendedor).append("'").toString();
        resultExecuteQuery = new ResultExecuteQuery();
        mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sql, column, codigoVendedor);
        if (mapResultado.size() > 0) {
            res = (HashMap) mapResultado.get(Integer.valueOf(0));
            codigo = res.get("TXTIDEMPLEADO").toString();
        }
        if (!codigo.equals("")) {
            resultado = true;
        }
        return resultado;
    }

    public static List<ClsQueries> ActualizarNumeroRegistros(String codigoVendedor,List<ClsQueries> queries) {
        List<BeanSincronizacion> sincronizaciones;
        SqlSincronizacionImpl sqlSincronizacionImpl = new SqlSincronizacionImpl(codigoVendedor);
        sincronizaciones = sqlSincronizacionImpl.getSincronizacion();
        for (BeanSincronizacion beanSincronizacion : sincronizaciones) {
            int cantidadRegistros = 0;
            Calendar inicioCalendario = Calendar.getInstance();
            long inicio = inicioCalendario.getTimeInMillis();
            int opcion = 0;
            try {
                opcion = Integer.parseInt(beanSincronizacion.getStrIdeSinc());
                switch (opcion) {
                    case 1:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_USUARIO");
                        break;
                    case 2:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_ROL");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_USUARIO_ROL");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_VISTA_ROL");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_VISTA");
                        break;
                    case 3:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_AGENDA");
                        break;
                    case 4:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_BLOQUEO_ENTREGA");
                        break;
                    case 5:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CLASE_MATERIAL");
                        break;
                    case 6:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CLIENTE");
                        break;
                    case 7:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_COMBO");
                        break;
                    case 8:
                        int cant1 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_1X");
                        int cant2 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_2X");
                        int cant3 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_3X");
                        int cant4 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_4X");
                        int cant5 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_5X");
                        int cant6 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_ESCALAS");
                        int cant7 = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICION_ZPR1");
                        cantidadRegistros = cant1 + cant2 + cant3 + cant4 + cant5
                                + cant6 + cant7;
                        break;
                    case 9:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CONDICIONES_PAGO");
                        break;
                    case 10:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_SEDE");
                        break;
                    case 11:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_DISPOSITIVO");
                        break;
                    case 12:
                        break;
                    case 13:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_FERIADO");
                        break;
                    case 14:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_JERARQUIA");
                        break;
                    case 15:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_MATERIAL");
                        break;
                    case 16:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_MATERIAL_STOCK");
                        break;
                    case 17:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_TIPOLOGIA");
                        break;
                    case 18:
                        SqlMaterial sqlMaterial = new SqlMaterialImpl();
                        cantidadRegistros = (/*sqlMaterial.getCountRowPromoOferta(codigoVendedor)
                                + sqlMaterial.getCountRowDsctoPolitica(codigoVendedor)
                                + sqlMaterial.getCountRowDsctoManual(codigoVendedor)
                                + */
                        		sqlMaterial.getCountRowTopCliente(codigoVendedor)
                                + sqlMaterial.getCountRowTopTipologia(codigoVendedor));
                        //Agregar Tabla Top por cliente y top por tipologia
                        break;
                    case 19:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_BANCO_CLIENTE");
                        break;
                    case 20:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_DIA_DEMORA_TRAS_VENCIMIENTO");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_HISTORIAL_PAGO");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_VALOR_POR_VENCER");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_NOTA_CREDITO");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_PROTESTO");
                        break;
                    case 21:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_PARTIDA_INDIVIDUAL");
                        break;
                    case 23:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_FORMA_PAGO_COBRANZA");
                        break;
                    case 24:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_FORMA_PAGO_ANTICIPO");
                        break;
                    case 25:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_BANCO_PROMESA");
                        break;
                    case 26:
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA");
                        cantidadRegistros += sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_DETALLE_PAGO_PARTIDA_INDIVIDUAL_ABIERTA");
                        break;
                    case 27:// mARCELO mOYANO Secuencia sincronizacion 03/04/2013 -
                        // 14:38
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_SECUENCIA");
                        break;
                    case 28:// mARCELO mOYANO Secuencia sincronizacion 03/04/2013 -
                        // 14:38;
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_ZTCONSTANTE");
                        break;
                    case 31://** Jonatan Aviles Arnao **//                  
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_PRESUPUESTO");
                        break;
                    case 32://** Jonatan Aviles Arnao **//    
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_MARCA_ESTRATEGICA");
                        break;    
                    case 33://** Jonatan Aviles Arnao **//    
                        cantidadRegistros = sqlSincronizacionImpl.filasTabla("PROFFLINE_TB_MATERIAL_NUEVO");
                        break;    
                    default:
                        cantidadRegistros = 0;
                        break;
                }
                Calendar finCalendario = Calendar.getInstance();
                long fin = finCalendario.getTimeInMillis();
                String strTie = Util.convierteMilisegundosAFormatoMMSS(fin - inicio);
                BeanSincronizar beanSincronizar = new BeanSincronizar();
                beanSincronizar.setStrIdeSinc(beanSincronizacion.getStrIdeSinc());
                beanSincronizar.setStrTie(strTie);
                beanSincronizar.setStrFecHor(Util.convierteFechaHoyAFormatoDDMMYYYYHHMMSSAA(new Date()));
                beanSincronizar.setStrCantReg(cantidadRegistros + "");
                queries = sqlSincronizacionImpl.actualizarSincronizar(beanSincronizar,queries);
            } catch (Exception e) {
                opcion = 0;
                /*Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(codigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("TABLA SINCRONIZACION ").append(codigoVendedor).toString()))).toString());*/
            }
        }
        return queries;
        //VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("TABLA SINCRONIZACION ").append(codigoVendedor).toString()))).toString());
    }

    public static String convierteMilisegundosAFormatoMMSS(long milisegundos) {
        int minutos = 0;
        long segundos = milisegundos / 1000l;
        while (segundos >= 60) {
            segundos -= 60;
            minutos++;
        }
        return formateaCeroALaIzquierda(minutos) + ":"
                + formateaCeroALaIzquierda(segundos);
    }

    public static String formateaCeroALaIzquierda(int numero) {
        if (numero >= 0 && numero < 10) {
            return "0" + numero;
        }
        return numero + "";
    }

    public static String formateaCeroALaIzquierda(long numero) {
        if (numero >= 0 && numero < 10) {
            return "0" + numero;
        }
        return numero + "";
    }

    public static String getFechaActualDDMMYYYY(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(fecha);
    }
}
