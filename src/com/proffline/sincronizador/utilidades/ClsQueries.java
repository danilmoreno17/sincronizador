package com.proffline.sincronizador.utilidades;

import com.proffline.sincronizador.gui.VentanaPrincipal;

public class ClsQueries {
	
	private String strTabla;
	private String strQuery;
	private String strTipo;
	public ClsQueries(){
		this.strTabla ="";
		this.strQuery="";
		this.strTipo="";
	}
	public ClsQueries(String _pStrTabla, String _pStrQuery,String _pStrTipo){
		this.strTabla = _pStrTabla;
		this.strQuery=_pStrQuery;
		this.strTipo=_pStrTipo;
	}
	public String getStrTabla() {
		return strTabla;
	}
	public void setStrTabla(String strTabla) {
		this.strTabla = strTabla;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrTipo() {
		return strTipo;
	}
	public void setStrTipo(String strTipo) {
		this.strTipo = strTipo;
	}
	public void mostrarMensajeExitoso(String strCodigoVendedor){
		
		if(this.strTabla.equals("PROFFLINE_TB_SEDE"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL(new StringBuilder().append("DESTINATARIO ").append(strCodigoVendedor).toString())));
		if(this.strTabla.equals("PROFFLINE_TB_CLIENTE"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSAP(new StringBuilder().append("CLIENTE ").append(strCodigoVendedor).toString())));
		if(this.strTabla.equals("PROFFLINE_TB_MATERIAL_TOP_CLIENTE"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL(new StringBuilder().append("MATERIAL TOP CLIENTE ").append(strCodigoVendedor).toString())));		
		if(this.strTabla.equals("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL(new StringBuilder().append("MATERIAL TOP TIPOLOGIA ").append(strCodigoVendedor).toString())));   
		if(this.strTabla.equals("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_BANCO_CLIENTE"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("BANCO CLIENTE ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_HISTORIAL_PAGO"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("HISTORIAL PAGO ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_VALOR_POR_VENCER"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("VALOR POR VENCER ").append(strCodigoVendedor).toString()))).toString());			 
		if(this.strTabla.equals("PROFFLINE_TB_NOTA_CREDITO"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("NOTA CREDITO ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_PROTESTO"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("PROTESTO ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_PARTIDA_INDIVIDUAL"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(strCodigoVendedor).toString()))).toString());
		if(this.strTabla.equals("PROFFLINE_TB_PRESUPUESTO"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PRESUPUESTO")));
		//if(this.strTabla.equals("PROFFLINE_TB_SINCRONIZACION"))
			//VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeLimpiarDatosExitosaSQL((new StringBuilder()).append("TABLA SINCRONIZACION ").append(strCodigoVendedor).toString())));
		if(this.strTabla.equals("PROFFLINE_TB_INDICADORES"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_INDICADORES")));		       
		if(this.strTabla.equals("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA")));
		if(this.strTabla.equals("PROFFLINE_TB_MARCA_ESTRATEGICA"))
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA")));
		
	}
	public void mostarMensajeFallido(String strCodigoVendedor){

		if(this.strTabla.equals("PROFFLINE_TB_SEDE")){			
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("DESTINATARIO ").append(strCodigoVendedor).toString())));
			else
				 VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL(new StringBuilder().append("DESTINATARIO ").append(strCodigoVendedor).toString())));	
		}
		if(this.strTabla.equals("PROFFLINE_TB_CLIENTE")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("CLIENTE ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeCargaDatosNoExitosaSQL(new StringBuilder().append("CLIENTE ").append(strCodigoVendedor).toString())));
		}
		if(this.strTabla.equals("PROFFLINE_TB_MATERIAL_TOP_CLIENTE")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("MATERIAL TOP CLIENTE ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL(new StringBuilder().append("MATERIAL TOP CLIENTE ").append(strCodigoVendedor).toString())));

		}
		if(this.strTabla.equals("PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("MATERIAL TOP TIPOLOGIA ").append(strCodigoVendedor).toString())));
			else
			    VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL(new StringBuilder().append("MATERIAL TOP TIPOLOGIA ").append(strCodigoVendedor).toString())));   

		}
		if(this.strTabla.equals("PROFFLINE_TB_PARTIDA_INDIVIDUAL_ABIERTA")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ABIERTA ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_BANCO_CLIENTE")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("BANCO CLIENTE ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("BANCO CLIENTE ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_CABECERA_HOJA_MAESTRA_CREDITO")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("CABECERA HOJA MAESTRA CREDITO ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("CABECERA HOJA MAESTRA CREDITO ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_HISTORIAL_PAGO")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("HISTORIAL PAGO ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("HISTORIAL PAGO ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_VALOR_POR_VENCER")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("VALOR POR VENCER ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("VALOR POR VENCER ").append(strCodigoVendedor).toString()))).toString());			 

		}
		if(this.strTabla.equals("PROFFLINE_TB_NOTA_CREDITO")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("NOTA CREDITO ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("NOTA CREDITO ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_PROTESTO")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("PROTESTO ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("PROTESTO ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_PARTIDA_INDIVIDUAL")){
			if(this.strTipo.equals("DELETE"))
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL(new StringBuilder().append("PARTIDA INDIVIDUAL ").append(strCodigoVendedor).toString())));
			else
				VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL((new StringBuilder()).append("PARTIDA INDIVIDUAL ").append(strCodigoVendedor).toString()))).toString());

		}
		if(this.strTabla.equals("PROFFLINE_TB_PRESUPUESTO")){
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL("PRESUPUESTO")));
		}
		if(this.strTabla.equals("PROFFLINE_TB_SINCRONIZACION")){
		//VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola((new StringBuilder()).append(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola()).append(Util.convierteTextoAFormatoHTML(3, Util.mensajeLimpiarDatosNoExitosaSQL((new StringBuilder()).append("TABLA SINCRONIZACION ").append(strCodigoVendedor).toString()))).toString());
		}
		if(this.strTabla.equals("PROFFLINE_TB_INDICADORES")){
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_INDICADORES")));		       

		}
		if(this.strTabla.equals("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA")){			
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_NOMBRE_MARCA_ESTRATEGICA")));
		}
		if(this.strTabla.equals("PROFFLINE_TB_MARCA_ESTRATEGICA")){
			VentanaPrincipal.obtenerInstancia().agregarTextoAEditorConsola(VentanaPrincipal.obtenerInstancia().obtenerTextoDeEditorConsola() + Util.convierteTextoAFormatoHTML(1, Util.mensajeCargaDatosNoExitosaSQL("PROFFLINE_TB_MARCA_ESTRATEGICA")));
			
		}
		

	}
	
}
