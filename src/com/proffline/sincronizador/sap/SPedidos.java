package com.proffline.sincronizador.sap;

import com.proffline.sincronizador.bean.BeanMercadeo;
import com.proffline.sincronizador.bean.BeanPromocion;
import com.proffline.sincronizador.bean.BeanSede;
import com.proffline.sincronizador.bean.Cliente;
import com.proffline.sincronizador.bean.Material;
import com.proffline.sincronizador.conexion.Conexion;
import com.proffline.sincronizador.gui.VentanaPrincipal;
import com.proffline.sincronizador.utilidades.Util;
import com.promesa.sincronizador.pedidos.sql.impl.SqlDivisionImpl;
import com.promesa.sincronizador.pedidos.sql.impl.SqlMaterialImpl;




import java.util.ArrayList;
import java.util.List;

import util.ConexionSAP;

public class SPedidos {

    public static List<List<Material>> listaMaterialesTop(String strCodVendedor) {
        try {
            List<List<Material>> listaFinal;
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            listaFinal = new ArrayList();
            List<Material> listaTemp = null;
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("MATERIALES TOP: ").append(strCodVendedor).toString()))).toString());
                listaTemp = new ArrayList();
                con.RegistrarRFC("ZSD_RFC_ORDER_GET_CANTID3");
                con.IngresarDatosInput(strCodVendedor, "V_KUNRG");
                con.EjecutarRFC();
                con.CreaTabla("ET_MATERIAL");
                List ur1 = con.ObtenerDatosTabla();
                con.DesconectarSAP();
                String values[] = null;
                Material bm = null;
                for (int i = 0; i < ur1.size(); i++) {
                    String cadena = String.valueOf(ur1.get(i));
                    values = cadena.split("¬");
                    bm = new Material();
                    bm.setStrMATNR(Util.eliminarCerosInicios(values[2].trim()));
                    bm.setStrVentasAcumulado(values[3].trim().replace("'", ""));
                    bm.setStrVentasPromedio(values[4].trim().replace("'", ""));
                    bm.setStrCliente((new StringBuilder()).append("").append(Integer.parseInt(values[1])).toString());
                    bm.setCantSug(Double.parseDouble(values[5]));
                    listaTemp.add(bm);
                }
                listaFinal.add(listaTemp);
                return listaFinal;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("Error al carcar top por cliente ").append(strCodVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES TOP: ").append(strCodVendedor).toString()))).toString());
                return null;
            }
        } catch (Exception ex) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES TOP: ").append(strCodVendedor).toString()))).toString());
            return null;
        }
    }

    public static List<List<Material>> listaMaterialesTopTipol(String strCodVendedor) {
        try {
            List<List<Material>> listaFinal;
            listaFinal = new ArrayList();
            List<Material> listaTemp = new ArrayList();
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("MATERIALES TIPOLOGIA: ").append(strCodVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_GROUP_CLIENT_TOP3");
                con.IngresarDatosInput(strCodVendedor, "V_KUNNR");
                con.EjecutarRFC();
                con.CreaTabla("T_GRUOP");
                List ur1 = con.ObtenerDatosTabla();
                con.DesconectarSAP();
                String values[] = null;
                Material bm = null;
                for (int i = 0; i < ur1.size(); i++) {
                    String cadena = String.valueOf(ur1.get(i));
                    values = cadena.split("¬");
                    bm = new Material();
                    bm.setStrMATNR(Util.eliminarCerosInicios(values[2].trim()));
                    bm.setStrVentasAcumulado(values[3].trim().replace("'", ""));
                    bm.setStrVentasPromedio(values[4].trim().replace("'", ""));
                    bm.setStrCliente(values[1].trim().replace("'", ""));
                    listaTemp.add(bm);
                }

                listaFinal.add(listaTemp);
                return listaFinal;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("Error al cargar tipologia ").append(strCodVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES TIPOLOGIA: ").append(strCodVendedor).toString()))).toString());
                return null;
            }

        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES TIPOLOGIA: ").append(strCodVendedor).toString()))).toString());
            return null;
        }
    }

    public static List listaMaterialesConsultaDinamica(String strCodigoVendedor) {
        try {
            List lista = new ArrayList();
            VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("MATERIAL CONSULTA DINAMICA ").append(strCodigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_MATERIAL_BUSQCON1");
                con.EjecutarRFC();
                con.CreaTabla("ET_MAT_BUSQCON1");
                List ur1 = con.ObtenerDatosTabla();
                con.CreaTabla("ET_STRUCT_DSCTO");
                List ur2 = con.ObtenerDatosTabla();
                con.CreaTabla("ET_CLASS_MAT");
                List ur3 = con.ObtenerDatosTabla();
                con.CreaTabla("ET_MARCA_MAT");
                List ur4 = con.ObtenerDatosTabla();
                con.DesconectarSAP();
                List listaMateriales1 = new ArrayList();
                List listaMateriales2 = new ArrayList();
                List listaMateriales3 = new ArrayList();
                String codigo = "";
                String tipo = "";
                String values[] = null;
                Material bm = null;
                List l = null;
                for (int i = 0; i < ur1.size(); i++) {
                    String cadena = String.valueOf(ur1.get(i));
                    values = cadena.split("¬");
                    bm = new Material();
                    bm.setStrMATNR((new StringBuilder()).append("").append(Integer.parseInt(values[1])).toString());
                    if (values[3].trim().equals("005")) {
                        listaMateriales1.add(bm);
                    }
                    if (values[3].trim().equals("004")) {
                        listaMateriales2.add(bm);
                    }
                    if (values[3].trim().equals("006")) {
                        listaMateriales3.add(bm);
                    }
                }

                String c1 = "";
                String c2 = "";
                String c3 = "";
                String c4 = "";
                String c5 = "";
                String codigoJerarquia = "";
                SqlDivisionImpl sqlDivision = new SqlDivisionImpl();
                l = new ArrayList();
                for (int i = 0; i < ur2.size(); i++) {
                    String cadena = String.valueOf(ur2.get(i));
                    values = cadena.split("¬");
                    c1 = (new StringBuilder()).append("").append(values[2]).toString();
                    c2 = (new StringBuilder()).append("").append(values[3]).toString();
                    c3 = (new StringBuilder()).append("").append(values[4]).toString();
                    c4 = (new StringBuilder()).append("").append(values[5]).toString();
                    c5 = (new StringBuilder()).append("").append(values[6]).toString();
                    tipo = values[10];
                    codigoJerarquia = (new StringBuilder()).append(c1).append(c2).append(c3).append(c4).append(c5).toString();
                    l = sqlDivision.getListaPrecios(codigoJerarquia, strCodigoVendedor);
                    if (tipo.trim().equals("005")) {
                        listaMateriales1.addAll(l);
                    }
                    if (tipo.trim().equals("004")) {
                        listaMateriales2.addAll(l);
                    }
                    if (tipo.trim().equals("006")) {
                        listaMateriales3.addAll(l);
                    }
                }

                SqlMaterialImpl sqlMaterial = new SqlMaterialImpl();
                l = new ArrayList();
                for (int i = 0; i < ur3.size(); i++) {
                    String cadena = String.valueOf(ur3.get(i));
                    values = cadena.split("¬");
                    codigo = values[1];
                    tipo = values[3];
                    l = sqlMaterial.obtenerMaterialesPorClaseMaterial(codigo, strCodigoVendedor);
                    if (tipo.trim().equals("005")) {
                        listaMateriales1.addAll(l);
                    }
                    if (tipo.trim().equals("004")) {
                        listaMateriales2.addAll(l);
                    }
                    if (tipo.trim().equals("006")) {
                        listaMateriales3.addAll(l);
                    }
                }

                l = new ArrayList();
                for (int i = 0; i < ur4.size(); i++) {
                    String cadena = String.valueOf(ur4.get(i));
                    values = cadena.split("¬");
                    codigo = values[1];
                    tipo = values[3];
                    l = sqlMaterial.buscarMaterialPorMarca(codigo, strCodigoVendedor);
                    if (tipo.trim().equals("005")) {
                        listaMateriales1.addAll(l);
                    }
                    if (tipo.trim().equals("004")) {
                        listaMateriales2.addAll(l);
                    }
                    if (tipo.trim().equals("006")) {
                        listaMateriales3.addAll(l);
                    }
                }

                lista.add(listaMateriales1);
                lista.add(listaMateriales2);
                lista.add(listaMateriales3);
                return lista;
            } else {
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIAL CONSULTA DINAMICA ").append(strCodigoVendedor).toString()))).toString());
                return null;
            }

        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIAL CONSULTA DINAMICA ").append(strCodigoVendedor).toString()))).toString());
            return null;
        }
    }

    public static List vendedorClientes2(String strCodigoVendedor, String strCodCliente, String strNombreCiente) {
        try {
            List ure = new ArrayList();
            if (!strCodCliente.isEmpty()) {
                strCodCliente = (new StringBuilder()).append("").append(Long.parseLong(strCodCliente)).toString();
            }
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("CLIENTE ").append(strCodigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_CUSTOMER_FIND2_2");
                con.CreaTabla("IS_VENDOR");
                con.IngresarDatoTabla(strCodigoVendedor, "VENDOR_ID", 1);
                con.CreaTabla("IS_SEARCH_OPTIONS");
                con.IngresarDatoTabla(strCodCliente, "CUST_ID", 1);
                con.EjecutarRFC();
                con.CreaTabla("ET_CUSTOMER");
                List ur = con.ObtenerDatosTabla();
                for (int i = 1; i < ur.size(); i++) {
                    String cadena = String.valueOf(ur.get(i));
                    String valores[] = cadena.split("¬");
                    Cliente bean = new Cliente();
                    Integer temp = Integer.valueOf(Integer.parseInt(valores[1].trim()));
                    bean.setStrIdCliente(temp.toString());
                    bean.setStrNombreCliente(valores[2].trim().replace("'", ""));
                    bean.setStrDireccionCliente(valores[3].trim().replace("'", ""));
                    bean.setStrTelefonoTrabajoCliente(valores[4].trim());
                    bean.setStrNumeroFaxCliente(valores[5].trim());
                    bean.setStrCodOrgVentas(valores[6].trim());
                    bean.setStrCodCanalDist(valores[7].trim().replace("'", ""));
                    bean.setStrCodSector(valores[8].trim());
                    bean.setStrGrupoVentas(valores[9].trim().replace("'", ""));
                    bean.setStrCiudadCliente(valores[11]);
                    bean.setStrOficinaVentas(valores[10].trim().replace("'", ""));
                    bean.setStrCondicionPagoDefecto(valores[16].trim());
                    bean.setIndicadorIva(valores[17].trim());
                    bean.setStrMarcaBloqueoAlmacen(valores[19].trim());
                    bean.setStrCodigoTipologia(valores[20].trim());
                    bean.setStrDescripcionTipologia(valores[21].trim().replace("'", ""));
                    try{
						bean.setStrClase(valores[22]);
					} catch (IndexOutOfBoundsException indexEx){
						bean.setStrClase("");
					}
                    try{
						bean.setStrCanal(valores[23]);						
					}catch(IndexOutOfBoundsException indexEx){
						bean.setStrCanal("");
					}
                    long id_cliente = Long.parseLong(valores[1].trim());
                    strCodCliente = strCodCliente.trim();
                    strNombreCiente = strNombreCiente.trim();
                    String aux_gui = strNombreCiente.toLowerCase();
                    String aux_sap = valores[2].toLowerCase();
                    if (strCodCliente.isEmpty()) {
                        if (strNombreCiente.isEmpty()) {
                            ure.add(bean);
                            continue;
                        }
                        if (aux_sap.contains(aux_gui)) {
                            ure.add(bean);
                        }
                        continue;
                    }
                    if (strCodCliente.compareTo((new StringBuilder()).append("").append(id_cliente).toString()) != 0) {
                        continue;
                    }
                    if (strNombreCiente.isEmpty()) {
                        ure.add(bean);
                        continue;
                    }
                    if (aux_sap.contains(aux_gui)) {
                        ure.add(bean);
                    }
                }

                con.CreaTabla("ET_MSG");
                List mensaje = con.ObtenerDatosTabla();
                for (int i = 0; i < mensaje.size(); i++) {
                    String msg = String.valueOf(ur.get(i));
                }

                con.DesconectarSAP();
                return ure;
            } else {
                Util.escribirErrorAArchivoLog(strCodigoVendedor);
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("CLIENTE ").append(strCodigoVendedor).toString()))).toString());
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(ex)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("CLIENTE ").append(strCodigoVendedor).toString()))).toString());
            return null;
        }
    }

    public static List listarSucursales(String strCodigoVendedor) {
        try {
            List sucursales;
            int longitud;
            sucursales = new ArrayList();
            longitud = (new StringBuilder()).append("").append(strCodigoVendedor).toString().length();
            if (longitud <= 0) {
                return null;
            }
            ConexionSAP con = Conexion.obtenerConexionSAP();
            if (con != null) {
                VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("CLIENTE ").append(strCodigoVendedor).toString()))).toString());
                con.RegistrarRFC("ZSD_RFC_CUSTOMER_GET_SEDE2");
                con.IngresarDatosInput(strCodigoVendedor, "LV_USER_ID");
                con.EjecutarRFC();
                con.CreaTabla("ET_TEAM");
                List ur = con.ObtenerDatosTabla();
                if (ur != null && ur.size() > 0) {
                    for (int i = 0; i < ur.size(); i++) {
                        BeanSede beanSede = new BeanSede();
                        String cadena = String.valueOf(ur.get(i));
                        String values[] = cadena.split("¬");
                        String codigo = "";
                        String idCliente = "";
                        try {
                            codigo = (new StringBuilder()).append("").append(Integer.parseInt(values[2])).toString();
                        } catch (Exception e) {
                            codigo = values[2];
                        }
                        try {
                            idCliente = (new StringBuilder()).append("").append(Integer.parseInt(values[9])).toString();
                        } catch (Exception e) {
                            idCliente = values[9];
                        }
                        beanSede.setCodigo(codigo);
                        beanSede.setDireccion(values[10].replace("'", ""));
                        beanSede.setIdCliente(idCliente);
                        sucursales.add(beanSede);
                    }

                }
                con.DesconectarSAP();
                return sucursales;
            } else {
                Util.escribirErrorAArchivoLog((new StringBuilder()).append("listarSucursales ").append(strCodigoVendedor).toString());
                VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("CLIENTE ").append(strCodigoVendedor).toString()))).toString());
                return null;
            }
            
        } catch (Exception e) {
            Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).append(strCodigoVendedor).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("CLIENTE ").append(strCodigoVendedor).toString()))).toString());
            return null;
        }
    }
    
    @SuppressWarnings("rawtypes")
	public static List<BeanMercadeo> listaMaterialesMercadeo() {
		try {
			List<BeanMercadeo> listaFinal = new ArrayList<BeanMercadeo>();
			VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
			ConexionSAP con = Conexion.obtenerConexionSAP();
			if (con != null) {
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("MATERIALES MERCADEO: ").toString()))).toString());
				con.RegistrarRFC("ZSD_RFC_GEN_FOCO_MERC");
				con.EjecutarRFC();
				con.CreaTabla("ET_MATERIAL");
				List ur1 = con.ObtenerDatosTabla();
				con.DesconectarSAP();
				String[] values = null;
				BeanMercadeo bm = null;
				for (int i = 0; i < ur1.size(); i++) {
					String cadena = String.valueOf(ur1.get(i));
					values = cadena.split("¬");
					bm = new BeanMercadeo();
					bm.setStrFechaCarga(values[1]);
					bm.setStrDivisionCliente(values[2]);
					bm.setStrCanalCliente(values[3]);
					bm.setStrCodigoMaterial(""+Integer.parseInt(values[4]));
					bm.setStrDescripcion(values[5]);
					bm.setStrFechaVigenciaDesde(values[6]);
					bm.setStrFechaVigenciaHasta(values[7]);
					listaFinal.add(bm);
				}
				return listaFinal;
			} else {
				 Util.escribirErrorAArchivoLog((new StringBuilder()).append("Error al carcar materiales de mercadeo ").toString());
				 VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES MERCADEO: ").toString()))).toString());
				return null;
			}
		} catch (Exception e) {
			Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES MERCADEO: ").toString()))).toString());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static List<BeanPromocion> listaMaterialesPromocion() {
		try {
			List<BeanPromocion> listaFinal = new ArrayList<BeanPromocion>();
			VentanaPrincipal.obtenerInstancia().setModoProgresoParcial(true);
			ConexionSAP con = Conexion.obtenerConexionSAP();
			if (con != null) {
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(2, Util.mensajeConexionExitosaSAP((new StringBuilder()).append("MATERIALES MERCADEO: ").toString()))).toString());
				con.RegistrarRFC("ZSD_RFC_GEN_PROMOCION");
				con.EjecutarRFC();
				con.CreaTabla("ET_MATERIAL");
				List ur1 = con.ObtenerDatosTabla();
				con.DesconectarSAP();
				String[] values = null;
				BeanPromocion bp = null;
				for (int i = 0; i < ur1.size(); i++) {
					String cadena = String.valueOf(ur1.get(i));
					values = cadena.split("¬");
					bp = new BeanPromocion();
					bp.setStrFecha(values[1]);
					bp.setStrDivisionCliente(values[2]);
					bp.setStrCanalCliente(values[3]);
					bp.setStrCodigoCliente(values[4].equals("")?"":""+Integer.parseInt(values[4]));
					bp.setStrTitulo(values[5]);
					bp.setStrDescripcion(values[6]);
					bp.setStrFamilia1(values[7]);
					bp.setStrFamilia2(values[8]);
					bp.setStrFechaVigenciaDesde(values[9]);
					bp.setStrFechaVigenciaHasta(values[10]);
					listaFinal.add(bp);
				}
				return listaFinal;
			} else {
				Util.escribirErrorAArchivoLog((new StringBuilder()).append("Error al carcar materiales de mercadeo ").toString());
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES MERCADEO: ").toString()))).toString());
				return null;
			}
		} catch (Exception e) {
			Util.escribirErrorAArchivoLog((new StringBuilder()).append(Util.getStackTrace(e)).toString());
            VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeConexionNoExitosaSAP((new StringBuilder()).append("MATERIALES MERCADEO: ").toString()))).toString());
			return null;
		}
	}

}
