/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesa.sincronizador.pedidos.sql.impl;

import com.proffline.sincronizador.bean.Jerarquia;
import com.proffline.sincronizador.bean.Material;
import com.proffline.sincronizador.sqlite.ResultExecuteQuery;
import com.proffline.sincronizador.utilidades.Util;
import com.promesa.sincronizador.pedidos.sql.SqlDivision;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class SqlDivisionImpl implements SqlDivision {

    private String sqlDivsion;
    private HashMap column;
    private List listaDivision;
    private HashMap mapResultado;
    private static String ID_PADRE = "txtIdPadre";
    private static String PRODH = "txtPRODH";
    private static String S = "txtS";
    private static String VTEXT = "txtVTEXT";
    private static String ZZSEQ = "txtZZSEQ";
    private static String ICON = "txtICON";
    private static String I = "txtI";
    private static String CELL_DESIGN = "txtCELLDESIGN";

    public SqlDivisionImpl() {
        /*  15*/ sqlDivsion = null;
        /*  16*/ column = new HashMap();
        /*  18*/ listaDivision = null;
        /*  19*/ mapResultado = new HashMap();
    }

    public void setListaJerarquia() {
        /*  33*/ column = new HashMap();
        /*  34*/ listaDivision = new ArrayList();
        /*  35*/ column.put("String:0", ID_PADRE);
        /*  36*/ column.put("String:1", PRODH);
        /*  37*/ column.put("String:2", S);
        /*  38*/ column.put("String:3", VTEXT);
        /*  39*/ column.put("String:4", ZZSEQ);
        /*  40*/ column.put("String:5", ICON);
        /*  41*/ column.put("String:6", I);
        /*  42*/ column.put("String:7", CELL_DESIGN);
        /*  43*/ sqlDivsion = "SELECT * FROM PROFFLINE_TB_JERARQUIA";
        /*  45*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /*  46*/ HashMap res = null;
        /*  47*/ for (int i = 0; i < mapResultado.size(); i++) {
            /*  48*/ Jerarquia division = new Jerarquia();
            /*  49*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /*  50*/ division.setStrIdPadre(res.get(ID_PADRE).toString());
            /*  51*/ division.setStrPRODH(res.get(PRODH).toString());
            /*  52*/ division.setStrS(res.get(S).toString());
            /*  53*/ division.setStrVTEXT(res.get(VTEXT).toString());
            /*  54*/ division.setStrZZSEQ(res.get(ZZSEQ).toString());
            /*  55*/ division.setStrICON(res.get(ICON).toString());
            /*  56*/ division.setStrI(res.get(I).toString());
            /*  57*/ division.setStrCellDesign(res.get(CELL_DESIGN).toString());
            /*  58*/ listaDivision.add(division);
        }

    }

    public List getListaJerarquiaPorNiveles(String nivel) {
        /*  64*/ column = new HashMap();
        /*  65*/ List lista = new ArrayList();
        /*  66*/ column.put("String:0", ID_PADRE);
        /*  67*/ column.put("String:1", PRODH);
        /*  68*/ column.put("String:2", S);
        /*  69*/ column.put("String:3", VTEXT);
        /*  70*/ column.put("String:4", ZZSEQ);
        /*  71*/ column.put("String:5", ICON);
        /*  72*/ column.put("String:6", I);
        /*  73*/ column.put("String:7", CELL_DESIGN);
        /*  74*/ sqlDivsion = (new StringBuilder()).append("select * from PROFFLINE_TB_JERARQUIA where PROFFLINE_TB_JERARQUIA.txtS='").append(nivel).append("' ORDER BY txtVTEXT").toString();
        /*  76*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /*  78*/ HashMap res = null;
        /*  79*/ for (int i = 0; i < mapResultado.size(); i++) {
            /*  80*/ Jerarquia division = new Jerarquia();
            /*  81*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /*  82*/ division.setStrPRODH(res.get(PRODH).toString());
            /*  83*/ division.setStrVTEXT(res.get(VTEXT).toString());
            /*  84*/ lista.add(division);
        }

        /*  86*/ return lista;
    }

    public List getListaJerarquia() {
        /*  91*/ return listaDivision;
    }

    public List[] jerarquiaMateriales() {
        List jerarquia[] = new List[5];
        jerarquia[0] = getListaJerarquiaPorNiveles("1");
        jerarquia[1] = getListaJerarquiaPorNiveles("2");
        jerarquia[2] = getListaJerarquiaPorNiveles("3");
        jerarquia[3] = getListaJerarquiaPorNiveles("4");
        jerarquia[4] = getListaJerarquiaPorNiveles("5");
        return jerarquia;
    }

    public Object[] buscarCantidadMateriales(int tipo, String codigo, String shortText, String longText, String codigoMaterial, String tipologia, int t,
            String marca) {
        /* 116*/ codigo = codigo.replaceAll("'", "");
        /* 117*/ shortText = shortText.replaceAll("'", "");
        /* 118*/ longText = longText.replaceAll("'", "");
        /* 119*/ codigoMaterial = codigoMaterial.replaceAll("'", "");
        /* 120*/ marca = marca.replaceAll("'", "");
        /* 121*/ column = new HashMap();
        /* 122*/ column.put("String:0", "COUNT()");
        /* 123*/ String type = "";
        /* 124*/ switch (t) {
            /* 126*/ case 0: // '\0'
/* 126*/ type = "";
                break;

            /* 129*/ case 1: // '\001'
/* 129*/ type = "TYPEMAT='N' AND";
                break;

            /* 132*/ case 2: // '\002'
/* 132*/ type = "TYPEMAT='P' AND";
                break;

            /* 135*/ case 3: // '\003'
/* 135*/ type = "TYPEMAT='R' AND";
                break;

            /* 138*/ case 4: // '\004'
/* 138*/ type = "TYPEMAT='B' AND";
                break;

            /* 141*/ default:
                /* 141*/ type = "";
                break;
        }
        /* 144*/ String sqlRetorno = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL WHERE ").append(type).append(" PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 146*/ sqlDivsion = (new StringBuilder()).append("SELECT COUNT() FROM PROFFLINE_TB_MATERIAL WHERE ").append(type).append(" PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 148*/ if (shortText != null && !shortText.isEmpty()) {
            /* 149*/ shortText = shortText.trim();
            /* 150*/ String sql_temp = "AND SHORT_TEXT LIKE '";
            /* 151*/ for (int i = 0; i < shortText.length(); i++) {
                /* 152*/ char c = shortText.charAt(i);
                /* 153*/ if (c == '*') {
                    /* 154*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 156*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 159*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
            /* 160*/ sqlRetorno = (new StringBuilder()).append(sqlRetorno).append(sql_temp).append("' ").toString();
        }
        /* 162*/ if (marca != null && !marca.isEmpty()) {
            /* 163*/ marca = marca.trim();
            /* 164*/ String sql_temp = "AND NORMT LIKE '";
            /* 165*/ for (int i = 0; i < marca.length(); i++) {
                /* 166*/ char c = marca.charAt(i);
                /* 167*/ if (c == '*') {
                    /* 168*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 170*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 173*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
            /* 174*/ sqlRetorno = (new StringBuilder()).append(sqlRetorno).append(sql_temp).append("' ").toString();
        }
        /* 177*/ if (longText != null && !longText.isEmpty()) {
            /* 178*/ longText = longText.trim();
            /* 179*/ String sql_temp = "AND TEXT_LINE LIKE '";
            /* 180*/ for (int i = 0; i < longText.length(); i++) {
                /* 181*/ char c = longText.charAt(i);
                /* 182*/ if (c == '*') {
                    /* 183*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 185*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 188*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
            /* 189*/ sqlRetorno = (new StringBuilder()).append(sqlRetorno).append(sql_temp).append("' ").toString();
        }
        /* 191*/ if (codigoMaterial != null && !codigoMaterial.isEmpty()) {
            /* 192*/ codigoMaterial = codigoMaterial.trim();
            /* 193*/ String sql_temp = "AND MATNR LIKE '";
            /* 194*/ for (int i = 0; i < codigoMaterial.length(); i++) {
                /* 195*/ char c = codigoMaterial.charAt(i);
                /* 196*/ if (c == '*') {
                    /* 197*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 199*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 202*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
            /* 203*/ sqlRetorno = (new StringBuilder()).append(sqlRetorno).append(sql_temp).append("' ").toString();
        }
        /* 205*/ if (tipo == 2 && tipologia != null) {
            /* 208*/ try {
                /* 208*/ tipologia = (new StringBuilder()).append("").append(Integer.parseInt(tipologia)).toString();
            } /* 209*/ catch (NumberFormatException e) {
                /* 210*/ Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            }
            /* 212*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(" AND GRUPO_COMPRA='").append(Util.completarCeros(3, tipologia)).append("'").toString();
            /* 213*/ sqlRetorno = (new StringBuilder()).append(sqlRetorno).append(" AND GRUPO_COMPRA='").append(Util.completarCeros(3, tipologia)).append("'").toString();
        }
        /* 216*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion.trim()).append(";").toString();
        /* 217*/ sqlRetorno = sqlRetorno.trim();
        /* 219*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /* 221*/ HashMap res = null;
        /* 222*/ long cantidadRegistros = 0L;
        /* 223*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 224*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 226*/ try {
                /* 226*/ cantidadRegistros = Long.parseLong(res.get("COUNT()").toString());
            } /* 227*/ catch (Exception e) {
                /* 228*/ cantidadRegistros = 0L;
            }
        }

        /* 231*/ Object objetos[] = new Object[2];
        /* 232*/ objetos[0] = sqlRetorno;
        /* 233*/ objetos[1] = Long.valueOf(cantidadRegistros);
        /* 234*/ return objetos;
    }

    public List buscarMateriales(String consulta, int pagina) {
        /* 240*/ List materiales = new ArrayList();
        /* 241*/ column = new HashMap();
        /* 242*/ column.put("String:0", "MATNR");
        /* 243*/ column.put("String:1", "STOCK");
        /* 244*/ column.put("String:2", "S_U");
        /* 245*/ column.put("String:3", "SHORT_TEXT");
        /* 246*/ column.put("String:4", "TEXT_LINE");
        /* 247*/ column.put("String:5", "TARGET_QTY");
        /* 248*/ column.put("String:6", "PRICE_1");
        /* 249*/ column.put("String:7", "PRICE_2");
        /* 250*/ column.put("String:8", "PRICE_3");
        /* 251*/ column.put("String:9", "PRICE_4");
        /* 252*/ column.put("String:10", "PRDHA");
        /* 253*/ column.put("String:11", "HER");
        /* 254*/ column.put("String:12", "NORMT");
        /* 255*/ column.put("String:13", "ZZORDCO");
        /* 256*/ column.put("String:14", "CELL_DESIGN");
        /* 257*/ column.put("String:15", "TYPEMAT");
        /* 258*/ consulta = (new StringBuilder()).append(consulta).append(" LIMIT ").append((pagina - 1) * 400).append(",").append(400).append(";").toString();
        /* 262*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(consulta, column, 0);
        /* 264*/ HashMap res = null;
        /* 265*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 266*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 267*/ Material material = new Material();
            /* 268*/ material.setStrMATNR(res.get("MATNR").toString());
            /* 269*/ material.setStrSU(res.get("S_U").toString());
            /* 270*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /* 271*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
            /* 272*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /* 273*/ material.setTipoMaterial(res.get("HER").toString());
            /* 274*/ material.setStrNORMT(res.get("NORMT").toString());
            /* 275*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /* 276*/ materiales.add(material);
        }

        /* 278*/ return materiales;
    }

    public List buscarMaterialesTopCliente(int tipo, String codigo, String shortText, String longText, String codigoMaterial, String tipologia, int t,
            String marca, String strCodCliente) {
        /* 287*/ codigo = codigo.replaceAll("'", "");
        /* 288*/ shortText = shortText.replaceAll("'", "");
        /* 289*/ longText = longText.replaceAll("'", "");
        /* 290*/ codigoMaterial = codigoMaterial.replaceAll("'", "");
        /* 291*/ tipologia = tipologia.replaceAll("'", "");
        /* 292*/ marca = marca.replaceAll("'", "");
        /* 293*/ List materiales = new ArrayList();
        /* 294*/ column = new HashMap();
        /* 295*/ column.put("String:0", "MATNR");
        /* 296*/ column.put("String:1", "STOCK");
        /* 297*/ column.put("String:2", "S_U");
        /* 298*/ column.put("String:3", "SHORT_TEXT");
        /* 299*/ column.put("String:4", "TEXT_LINE");
        /* 300*/ column.put("String:5", "TARGET_QTY");
        /* 301*/ column.put("String:6", "PRICE_1");
        /* 302*/ column.put("String:7", "PRICE_2");
        /* 303*/ column.put("String:8", "PRICE_3");
        /* 304*/ column.put("String:9", "PRICE_4");
        /* 305*/ column.put("String:10", "PRDHA");
        /* 306*/ column.put("String:11", "HER");
        /* 307*/ column.put("String:12", "NORMT");
        /* 308*/ column.put("String:13", "ZZORDCO");
        /* 309*/ column.put("String:14", "CELL_DESIGN");
        /* 310*/ column.put("String:15", "TYPEMAT");
        /* 311*/ column.put("String:16", "GRUPO_COMPRA");
        /* 312*/ column.put("String:17", "ST_1");
        /* 313*/ column.put("String:18", "VENTAS_ACUMULADO");
        /* 314*/ column.put("String:19", "VENTAS_PROMEDIO");
        /* 315*/ column.put("String:20", "CLIENTE");
        /* 316*/ String type = "";
        /* 317*/ switch (t) {
            /* 319*/ case 0: // '\0'
/* 319*/ type = "";
                break;

            /* 322*/ case 1: // '\001'
/* 322*/ type = "TYPEMAT='N' AND";
                break;

            /* 325*/ case 2: // '\002'
/* 325*/ type = "TYPEMAT='P' AND";
                break;

            /* 328*/ case 3: // '\003'
/* 328*/ type = "TYPEMAT='R' AND";
                break;

            /* 331*/ case 4: // '\004'
/* 331*/ type = "TYPEMAT='B' AND";
                break;

            /* 334*/ default:
                /* 334*/ type = "";
                break;
        }
        /* 337*/ sqlDivsion = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL_TOP_CLIENTE WHERE ").append(type).append(" CLIENTE='").append(strCodCliente).append("' AND PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 340*/ if (shortText != null && !shortText.isEmpty()) {
            /* 341*/ shortText = shortText.trim();
            /* 342*/ String sql_temp = "AND SHORT_TEXT LIKE '";
            /* 343*/ for (int i = 0; i < shortText.length(); i++) {
                /* 344*/ char c = shortText.charAt(i);
                /* 345*/ if (c == '*') {
                    /* 346*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 348*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 351*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 354*/ if (marca != null && !marca.isEmpty()) {
            /* 355*/ marca = marca.trim();
            /* 356*/ String sql_temp = "AND NORMT LIKE '";
            /* 357*/ for (int i = 0; i < marca.length(); i++) {
                /* 358*/ char c = marca.charAt(i);
                /* 359*/ if (c == '*') {
                    /* 360*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 362*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 365*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 368*/ if (longText != null && !longText.isEmpty()) {
            /* 369*/ longText = longText.trim();
            /* 370*/ String sql_temp = "AND TEXT_LINE LIKE '";
            /* 371*/ for (int i = 0; i < longText.length(); i++) {
                /* 372*/ char c = longText.charAt(i);
                /* 373*/ if (c == '*') {
                    /* 374*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 376*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 379*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 381*/ if (codigoMaterial != null && !codigoMaterial.isEmpty()) {
            /* 382*/ codigoMaterial = codigoMaterial.trim();
            /* 383*/ String sql_temp = "AND MATNR LIKE '";
            /* 384*/ for (int i = 0; i < codigoMaterial.length(); i++) {
                /* 385*/ char c = codigoMaterial.charAt(i);
                /* 386*/ if (c == '*') {
                    /* 387*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 389*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 392*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 394*/ if (tipo == 2 && tipologia != null) {
            /* 397*/ try {
                /* 397*/ tipologia = (new StringBuilder()).append("").append(Integer.parseInt(tipologia)).toString();
            } /* 398*/ catch (NumberFormatException e) {
                /* 399*/ Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            }
            /* 401*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(" AND GRUPO_COMPRA='").append(Util.completarCeros(3, tipologia)).append("'").toString();
        }
        /* 404*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion.trim()).append(" ORDER BY VENTAS_ACUMULADO DESC;").toString();
        /* 406*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /* 408*/ HashMap res = null;
        /* 409*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 410*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 411*/ Material material = new Material();
            /* 412*/ material.setStrMATNR(res.get("MATNR").toString());
            /* 413*/ material.setStrSU(res.get("S_U").toString());
            /* 414*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /* 415*/ material.setStrSU(res.get("SHORT_TEXT").toString());
            /* 416*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /* 417*/ material.setTipoMaterial(res.get("HER").toString());
            /* 418*/ material.setStrNORMT(res.get("NORMT").toString());
            /* 419*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /* 420*/ material.setStrVentasAcumulado((new StringBuilder()).append("").append(Double.parseDouble(res.get("VENTAS_ACUMULADO").toString())).toString());
            /* 421*/ material.setStrVentasPromedio((new StringBuilder()).append("").append(Double.parseDouble(res.get("VENTAS_PROMEDIO").toString())).toString());
            /* 422*/ material.setStrCliente(res.get("CLIENTE").toString());
            /* 423*/ materiales.add(material);
        }

        /* 425*/ return materiales;
    }

    public List buscarMaterialesTopTipologia(int tipo, String codigo, String shortText, String longText, String codigoMaterial, String tipologia, int t,
            String marca, String strCodCliente) {
        /* 434*/ codigo = codigo.replaceAll("'", "");
        /* 435*/ shortText = shortText.replaceAll("'", "");
        /* 436*/ longText = longText.replaceAll("'", "");
        /* 437*/ codigoMaterial = codigoMaterial.replaceAll("'", "");
        /* 438*/ tipologia = tipologia.replaceAll("'", "");
        /* 439*/ marca = marca.replaceAll("'", "");
        /* 440*/ List materiales = new ArrayList();
        /* 441*/ column = new HashMap();
        /* 442*/ column.put("String:0", "MATNR");
        /* 443*/ column.put("String:1", "STOCK");
        /* 444*/ column.put("String:2", "S_U");
        /* 445*/ column.put("String:3", "SHORT_TEXT");
        /* 446*/ column.put("String:4", "TEXT_LINE");
        /* 447*/ column.put("String:5", "TARGET_QTY");
        /* 448*/ column.put("String:6", "PRICE_1");
        /* 449*/ column.put("String:7", "PRICE_2");
        /* 450*/ column.put("String:8", "PRICE_3");
        /* 451*/ column.put("String:9", "PRICE_4");
        /* 452*/ column.put("String:10", "PRDHA");
        /* 453*/ column.put("String:11", "HER");
        /* 454*/ column.put("String:12", "NORMT");
        /* 455*/ column.put("String:13", "ZZORDCO");
        /* 456*/ column.put("String:14", "CELL_DESIGN");
        /* 457*/ column.put("String:15", "TYPEMAT");
        /* 458*/ column.put("String:16", "GRUPO_COMPRA");
        /* 459*/ column.put("String:17", "ST_1");
        /* 460*/ column.put("String:18", "VENTAS_ACUMULADO");
        /* 461*/ column.put("String:19", "VENTAS_PROMEDIO");
        /* 462*/ column.put("String:20", "CLIENTE");
        /* 464*/ String type = "";
        /* 465*/ switch (t) {
            /* 467*/ case 0: // '\0'
/* 467*/ type = "";
                break;

            /* 470*/ case 1: // '\001'
/* 470*/ type = "TYPEMAT='N' AND";
                break;

            /* 473*/ case 2: // '\002'
/* 473*/ type = "TYPEMAT='P' AND";
                break;

            /* 476*/ case 3: // '\003'
/* 476*/ type = "TYPEMAT='R' AND";
                break;

            /* 479*/ case 4: // '\004'
/* 479*/ type = "TYPEMAT='B' AND";
                break;

            /* 482*/ default:
                /* 482*/ type = "";
                break;
        }
        /* 485*/ sqlDivsion = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL_TOP_TIPOLOGIA WHERE ").append(type).append(" CLIENTE='").append(tipologia).append("' AND PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 488*/ if (shortText != null && !shortText.isEmpty()) {
            /* 489*/ shortText = shortText.trim();
            /* 490*/ String sql_temp = "AND SHORT_TEXT LIKE '";
            /* 491*/ for (int i = 0; i < shortText.length(); i++) {
                /* 492*/ char c = shortText.charAt(i);
                /* 493*/ if (c == '*') {
                    /* 494*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 496*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 499*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 501*/ if (marca != null && !marca.isEmpty()) {
            /* 502*/ marca = marca.trim();
            /* 503*/ String sql_temp = "AND NORMT LIKE '";
            /* 504*/ for (int i = 0; i < marca.length(); i++) {
                /* 505*/ char c = marca.charAt(i);
                /* 506*/ if (c == '*') {
                    /* 507*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 509*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 512*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 514*/ if (longText != null && !longText.isEmpty()) {
            /* 515*/ longText = longText.trim();
            /* 516*/ String sql_temp = "AND TEXT_LINE LIKE '";
            /* 517*/ for (int i = 0; i < longText.length(); i++) {
                /* 518*/ char c = longText.charAt(i);
                /* 519*/ if (c == '*') {
                    /* 520*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 522*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 525*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 527*/ if (codigoMaterial != null && !codigoMaterial.isEmpty()) {
            /* 528*/ codigoMaterial = codigoMaterial.trim();
            /* 529*/ String sql_temp = "AND MATNR LIKE '";
            /* 530*/ for (int i = 0; i < codigoMaterial.length(); i++) {
                /* 531*/ char c = codigoMaterial.charAt(i);
                /* 532*/ if (c == '*') {
                    /* 533*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 535*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 538*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 540*/ if (tipo == 2 && tipologia != null) {
            /* 543*/ try {
                /* 543*/ tipologia = (new StringBuilder()).append("").append(Integer.parseInt(tipologia)).toString();
            } /* 544*/ catch (NumberFormatException e) {
                /* 545*/ tipologia = "";
            }
            /* 547*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(" ").toString();
        }
        /* 550*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion.trim()).append(" ORDER BY VENTAS_ACUMULADO DESC;").toString();
        /* 552*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /* 554*/ HashMap res = null;
        /* 555*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 556*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 557*/ Material material = new Material();
            /* 558*/ material.setStrMATNR(res.get("MATNR").toString());
            /* 559*/ material.setStrSU(res.get("S_U").toString());
            /* 560*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /* 561*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
            /* 562*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /* 563*/ material.setTipoMaterial(res.get("HER").toString());
            /* 564*/ material.setStrNORMT(res.get("NORMT").toString());
            /* 565*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /* 566*/ material.setStrVentasAcumulado((new StringBuilder()).append("").append(Double.parseDouble(res.get("VENTAS_ACUMULADO").toString())).toString());
            /* 567*/ material.setStrVentasPromedio((new StringBuilder()).append("").append(Double.parseDouble(res.get("VENTAS_PROMEDIO").toString())).toString());
            /* 568*/ material.setStrCliente(res.get("CLIENTE").toString());
            /* 569*/ materiales.add(material);
        }

        /* 571*/ return materiales;
    }

    public List buscarMaterialesPromoOferta(int tipo, String codigo, String shortText, String longText, String codigoMaterial, String tipologia, int t,
            String marca) {
        /* 579*/ codigo = codigo.replaceAll("'", "");
        /* 580*/ shortText = shortText.replaceAll("'", "");
        /* 581*/ longText = longText.replaceAll("'", "");
        /* 582*/ codigoMaterial = codigoMaterial.replaceAll("'", "");
        /* 583*/ tipologia = tipologia.replaceAll("'", "");
        /* 584*/ marca = marca.replaceAll("'", "");
        /* 585*/ List materiales = new ArrayList();
        /* 586*/ column = new HashMap();
        /* 587*/ column.put("String:0", "MATNR");
        /* 588*/ column.put("String:1", "STOCK");
        /* 589*/ column.put("String:2", "S_U");
        /* 590*/ column.put("String:3", "SHORT_TEXT");
        /* 591*/ column.put("String:4", "TEXT_LINE");
        /* 592*/ column.put("String:5", "TARGET_QTY");
        /* 593*/ column.put("String:6", "PRICE_1");
        /* 594*/ column.put("String:7", "PRICE_2");
        /* 595*/ column.put("String:8", "PRICE_3");
        /* 596*/ column.put("String:9", "PRICE_4");
        /* 597*/ column.put("String:10", "PRDHA");
        /* 598*/ column.put("String:11", "HER");
        /* 599*/ column.put("String:12", "NORMT");
        /* 600*/ column.put("String:13", "ZZORDCO");
        /* 601*/ column.put("String:14", "CELL_DESIGN");
        /* 602*/ column.put("String:15", "TYPEMAT");
        /* 603*/ String type = "";
        /* 604*/ switch (t) {
            /* 606*/ case 0: // '\0'
/* 606*/ type = "";
                break;

            /* 609*/ case 1: // '\001'
/* 609*/ type = "TYPEMAT='N' AND";
                break;

            /* 612*/ case 2: // '\002'
/* 612*/ type = "TYPEMAT='P' AND";
                break;

            /* 615*/ case 3: // '\003'
/* 615*/ type = "TYPEMAT='R' AND";
                break;

            /* 618*/ case 4: // '\004'
/* 618*/ type = "TYPEMAT='B' AND";
                break;

            /* 621*/ default:
                /* 621*/ type = "";
                break;
        }
        /* 624*/ sqlDivsion = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL_PROMO_OFERTA WHERE ").append(type).append(" PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 625*/ if (shortText != null && !shortText.isEmpty()) {
            /* 626*/ shortText = shortText.trim();
            /* 627*/ String sql_temp = "AND SHORT_TEXT LIKE '";
            /* 628*/ for (int i = 0; i < shortText.length(); i++) {
                /* 629*/ char c = shortText.charAt(i);
                /* 630*/ if (c == '*') {
                    /* 631*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 633*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 636*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 639*/ if (marca != null && !marca.isEmpty()) {
            /* 640*/ marca = marca.trim();
            /* 641*/ String sql_temp = "AND NORMT LIKE '";
            /* 642*/ for (int i = 0; i < marca.length(); i++) {
                /* 643*/ char c = marca.charAt(i);
                /* 644*/ if (c == '*') {
                    /* 645*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 647*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 650*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 653*/ if (longText != null && !longText.isEmpty()) {
            /* 654*/ longText = longText.trim();
            /* 655*/ String sql_temp = "AND TEXT_LINE LIKE '";
            /* 656*/ for (int i = 0; i < longText.length(); i++) {
                /* 657*/ char c = longText.charAt(i);
                /* 658*/ if (c == '*') {
                    /* 659*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 661*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 664*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 666*/ if (codigoMaterial != null && !codigoMaterial.isEmpty()) {
            /* 667*/ codigoMaterial = codigoMaterial.trim();
            /* 668*/ String sql_temp = "AND MATNR LIKE '";
            /* 669*/ for (int i = 0; i < codigoMaterial.length(); i++) {
                /* 670*/ char c = codigoMaterial.charAt(i);
                /* 671*/ if (c == '*') {
                    /* 672*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 674*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 677*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 679*/ if (tipo == 2 && tipologia != null) {
            /* 682*/ try {
                /* 682*/ tipologia = (new StringBuilder()).append("").append(Integer.parseInt(tipologia)).toString();
            } /* 683*/ catch (NumberFormatException e) {
                /* 684*/ Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            }
            /* 686*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(" ").toString();
        }
        /* 689*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion.trim()).append(";").toString();
        /* 691*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /* 693*/ HashMap res = null;
        /* 694*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 695*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 696*/ Material material = new Material();
            /* 697*/ material.setStrMATNR(res.get("MATNR").toString());
            /* 698*/ material.setStrSU(res.get("S_U").toString());
            /* 699*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /* 700*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
            /* 701*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /* 702*/ material.setTipoMaterial(res.get("HER").toString());
            /* 703*/ material.setStrNORMT(res.get("NORMT").toString());
            /* 704*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /* 705*/ materiales.add(material);
        }

        /* 707*/ return materiales;
    }

    public List buscarMaterialesDescuentoPolitica(int tipo, String codigo, String shortText, String longText, String codigoMaterial, String tipologia, int t,
            String marca) {
        /* 715*/ codigo = codigo.replaceAll("'", "");
        /* 716*/ shortText = shortText.replaceAll("'", "");
        /* 717*/ longText = longText.replaceAll("'", "");
        /* 718*/ codigoMaterial = codigoMaterial.replaceAll("'", "");
        /* 719*/ tipologia = tipologia.replaceAll("'", "");
        /* 720*/ marca = marca.replaceAll("'", "");
        /* 721*/ List materiales = new ArrayList();
        /* 722*/ column = new HashMap();
        /* 723*/ column.put("String:0", "MATNR");
        /* 724*/ column.put("String:1", "STOCK");
        /* 725*/ column.put("String:2", "S_U");
        /* 726*/ column.put("String:3", "SHORT_TEXT");
        /* 727*/ column.put("String:4", "TEXT_LINE");
        /* 728*/ column.put("String:5", "TARGET_QTY");
        /* 729*/ column.put("String:6", "PRICE_1");
        /* 730*/ column.put("String:7", "PRICE_2");
        /* 731*/ column.put("String:8", "PRICE_3");
        /* 732*/ column.put("String:9", "PRICE_4");
        /* 733*/ column.put("String:10", "PRDHA");
        /* 734*/ column.put("String:11", "HER");
        /* 735*/ column.put("String:12", "NORMT");
        /* 736*/ column.put("String:13", "ZZORDCO");
        /* 737*/ column.put("String:14", "CELL_DESIGN");
        /* 738*/ column.put("String:15", "TYPEMAT");
        /* 739*/ String type = "";
        /* 740*/ switch (t) {
            /* 742*/ case 0: // '\0'
/* 742*/ type = "";
                break;

            /* 745*/ case 1: // '\001'
/* 745*/ type = "TYPEMAT='N' AND";
                break;

            /* 748*/ case 2: // '\002'
/* 748*/ type = "TYPEMAT='P' AND";
                break;

            /* 751*/ case 3: // '\003'
/* 751*/ type = "TYPEMAT='R' AND";
                break;

            /* 754*/ case 4: // '\004'
/* 754*/ type = "TYPEMAT='B' AND";
                break;

            /* 757*/ default:
                /* 757*/ type = "";
                break;
        }
        /* 760*/ sqlDivsion = (new StringBuilder()).append("SELECT DISTINCT * FROM PROFFLINE_TB_MATERIAL_DSCTO_POLITICA WHERE ").append(type).append(" PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 761*/ if (shortText != null && !shortText.isEmpty()) {
            /* 762*/ shortText = shortText.trim();
            /* 763*/ String sql_temp = "AND SHORT_TEXT LIKE '";
            /* 764*/ for (int i = 0; i < shortText.length(); i++) {
                /* 765*/ char c = shortText.charAt(i);
                /* 766*/ if (c == '*') {
                    /* 767*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 769*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 772*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 775*/ if (marca != null && !marca.isEmpty()) {
            /* 776*/ marca = marca.trim();
            /* 777*/ String sql_temp = "AND NORMT LIKE '";
            /* 778*/ for (int i = 0; i < marca.length(); i++) {
                /* 779*/ char c = marca.charAt(i);
                /* 780*/ if (c == '*') {
                    /* 781*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 783*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 786*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 789*/ if (longText != null && !longText.isEmpty()) {
            /* 790*/ longText = longText.trim();
            /* 791*/ String sql_temp = "AND TEXT_LINE LIKE '";
            /* 792*/ for (int i = 0; i < longText.length(); i++) {
                /* 793*/ char c = longText.charAt(i);
                /* 794*/ if (c == '*') {
                    /* 795*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 797*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 800*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 802*/ if (codigoMaterial != null && !codigoMaterial.isEmpty()) {
            /* 803*/ codigoMaterial = codigoMaterial.trim();
            /* 804*/ String sql_temp = "AND MATNR LIKE '";
            /* 805*/ for (int i = 0; i < codigoMaterial.length(); i++) {
                /* 806*/ char c = codigoMaterial.charAt(i);
                /* 807*/ if (c == '*') {
                    /* 808*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 810*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 813*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 815*/ if (tipo == 2 && tipologia != null) {
            /* 818*/ try {
                /* 818*/ tipologia = (new StringBuilder()).append("").append(Integer.parseInt(tipologia)).toString();
            } /* 819*/ catch (NumberFormatException e) {
                /* 820*/ Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            }
            /* 822*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(" ").toString();
        }
        /* 825*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion.trim()).append(";").toString();
        /* 827*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /* 829*/ HashMap res = null;
        /* 830*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 831*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 832*/ Material material = new Material();
            /* 833*/ material.setStrMATNR(res.get("MATNR").toString());
            /* 834*/ material.setStrSU(res.get("S_U").toString());
            /* 835*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /* 836*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
            /* 837*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /* 838*/ material.setTipoMaterial(res.get("HER").toString());
            /* 839*/ material.setStrNORMT(res.get("NORMT").toString());
            /* 840*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /* 841*/ materiales.add(material);
        }

        /* 843*/ return materiales;
    }

    public List buscarMaterialesDescuentoManual(int tipo, String codigo, String shortText, String longText, String codigoMaterial, String tipologia, int t,
            String marca) {
        /* 851*/ codigo = codigo.replaceAll("'", "");
        /* 852*/ shortText = shortText.replaceAll("'", "");
        /* 853*/ longText = longText.replaceAll("'", "");
        /* 854*/ codigoMaterial = codigoMaterial.replaceAll("'", "");
        /* 855*/ tipologia = tipologia.replaceAll("'", "");
        /* 856*/ marca = marca.replaceAll("'", "");
        /* 857*/ List materiales = new ArrayList();
        /* 858*/ column = new HashMap();
        /* 859*/ column.put("String:0", "MATNR");
        /* 860*/ column.put("String:1", "STOCK");
        /* 861*/ column.put("String:2", "S_U");
        /* 862*/ column.put("String:3", "SHORT_TEXT");
        /* 863*/ column.put("String:4", "TEXT_LINE");
        /* 864*/ column.put("String:5", "TARGET_QTY");
        /* 865*/ column.put("String:6", "PRICE_1");
        /* 866*/ column.put("String:7", "PRICE_2");
        /* 867*/ column.put("String:8", "PRICE_3");
        /* 868*/ column.put("String:9", "PRICE_4");
        /* 869*/ column.put("String:10", "PRDHA");
        /* 870*/ column.put("String:11", "HER");
        /* 871*/ column.put("String:12", "NORMT");
        /* 872*/ column.put("String:13", "ZZORDCO");
        /* 873*/ column.put("String:14", "CELL_DESIGN");
        /* 874*/ column.put("String:15", "TYPEMAT");
        /* 875*/ String type = "";
        /* 876*/ switch (t) {
            /* 878*/ case 0: // '\0'
/* 878*/ type = "";
                break;

            /* 881*/ case 1: // '\001'
/* 881*/ type = "TYPEMAT='N' AND";
                break;

            /* 884*/ case 2: // '\002'
/* 884*/ type = "TYPEMAT='P' AND";
                break;

            /* 887*/ case 3: // '\003'
/* 887*/ type = "TYPEMAT='R' AND";
                break;

            /* 890*/ case 4: // '\004'
/* 890*/ type = "TYPEMAT='B' AND";
                break;

            /* 893*/ default:
                /* 893*/ type = "";
                break;
        }
        /* 896*/ sqlDivsion = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL_DSCTO_MANUAL WHERE ").append(type).append(" PRDHA LIKE '").append(codigo).append("%' ").toString();
        /* 897*/ if (shortText != null && !shortText.isEmpty()) {
            /* 898*/ shortText = shortText.trim();
            /* 899*/ String sql_temp = "AND SHORT_TEXT LIKE '";
            /* 900*/ for (int i = 0; i < shortText.length(); i++) {
                /* 901*/ char c = shortText.charAt(i);
                /* 902*/ if (c == '*') {
                    /* 903*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 905*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 908*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 911*/ if (marca != null && !marca.isEmpty()) {
            /* 912*/ marca = marca.trim();
            /* 913*/ String sql_temp = "AND NORMT LIKE '";
            /* 914*/ for (int i = 0; i < marca.length(); i++) {
                /* 915*/ char c = marca.charAt(i);
                /* 916*/ if (c == '*') {
                    /* 917*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 919*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 922*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 925*/ if (longText != null && !longText.isEmpty()) {
            /* 926*/ longText = longText.trim();
            /* 927*/ String sql_temp = "AND TEXT_LINE LIKE '";
            /* 928*/ for (int i = 0; i < longText.length(); i++) {
                /* 929*/ char c = longText.charAt(i);
                /* 930*/ if (c == '*') {
                    /* 931*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 933*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 936*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 938*/ if (codigoMaterial != null && !codigoMaterial.isEmpty()) {
            /* 939*/ codigoMaterial = codigoMaterial.trim();
            /* 940*/ String sql_temp = "AND MATNR LIKE '";
            /* 942*/ for (int i = 0; i < codigoMaterial.length(); i++) {
                /* 943*/ char c = codigoMaterial.charAt(i);
                /* 944*/ if (c == '*') {
                    /* 945*/ sql_temp = (new StringBuilder()).append(sql_temp).append("%").toString();
                } else {
                    /* 947*/ sql_temp = (new StringBuilder()).append(sql_temp).append(c).toString();
                }
            }

            /* 950*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(sql_temp).append("' ").toString();
        }
        /* 952*/ if (tipo == 2 && tipologia != null) {
            /* 955*/ try {
                /* 955*/ tipologia = (new StringBuilder()).append("").append(Integer.parseInt(tipologia)).toString();
            } /* 956*/ catch (NumberFormatException e) {
                /* 957*/ Util.escribirErrorAArchivoLog(Util.getStackTrace(e));
            }
            /* 959*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion).append(" ").toString();
        }
        /* 962*/ sqlDivsion = (new StringBuilder()).append(sqlDivsion.trim()).append(";").toString();
        /* 964*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sqlDivsion, column, 0);
        /* 966*/ HashMap res = null;
        /* 967*/ for (int i = 0; i < mapResultado.size(); i++) {
            /* 968*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /* 969*/ Material material = new Material();
            /* 970*/ material.setStrMATNR(res.get("MATNR").toString());
            /* 971*/ material.setStrSU(res.get("S_U").toString());
            /* 972*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /* 973*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
            /* 974*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /* 975*/ material.setTipoMaterial(res.get("HER").toString());
            /* 976*/ material.setStrNORMT(res.get("NORMT").toString());
            /* 977*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /* 978*/ materiales.add(material);
        }

        /* 980*/ return materiales;
    }

    public List getListaPrecios(String hierarchy, String strCodigoVendedor) {
        /* 986*/ List materiales = new ArrayList();
        /* 987*/ column = new HashMap();
        /* 988*/ column.put("String:0", "MATNR");
        /* 989*/ column.put("String:1", "STOCK");
        /* 990*/ column.put("String:2", "S_U");
        /* 991*/ column.put("String:3", "SHORT_TEXT");
        /* 992*/ column.put("String:4", "TEXT_LINE");
        /* 993*/ column.put("String:5", "TARGET_QTY");
        /* 994*/ column.put("String:6", "PRICE_1");
        /* 995*/ column.put("String:7", "PRICE_2");
        /* 996*/ column.put("String:8", "PRICE_3");
        /* 997*/ column.put("String:9", "PRICE_4");
        /* 998*/ column.put("String:10", "PRDHA");
        /* 999*/ column.put("String:11", "HER");
        /*1000*/ column.put("String:12", "NORMT");
        /*1001*/ column.put("String:13", "ZZORDCO");
        /*1002*/ column.put("String:14", "CELL_DESIGN");
        /*1003*/ column.put("String:15", "TYPEMAT");
        /*1004*/ sqlDivsion = (new StringBuilder()).append("SELECT * FROM PROFFLINE_TB_MATERIAL WHERE PRDHA LIKE '").append(hierarchy).append("%';").toString();
        /*1007*/ mapResultado = ResultExecuteQuery.obtenerDatosConsultaPorVendedor(sqlDivsion, column, strCodigoVendedor);
        /*1009*/ HashMap res = null;
        /*1010*/ for (int i = 0; i < mapResultado.size(); i++) {
            /*1011*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
            /*1012*/ Material material = new Material();
            /*1013*/ material.setStrMATNR(res.get("MATNR").toString());
            /*1014*/ material.setStrSU(res.get("S_U").toString());
            /*1015*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
            /*1016*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
            /*1017*/ material.setStrPrice1(res.get("PRICE_1").toString());
            /*1018*/ material.setTipoMaterial(res.get("HER").toString());
            /*1019*/ material.setStrNORMT(res.get("NORMT").toString());
            /*1020*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
            /*1021*/ materiales.add(material);
        }

        /*1024*/ return materiales;
    }

    public List obtenerMateriales() {
        /*1029*/ List listaMateriales = null;
        /*1030*/ column = new HashMap();
        /*1031*/ column.put("String:0", "MATNR");
        /*1032*/ column.put("String:1", "STOCK");
        /*1033*/ column.put("String:2", "S_U");
        /*1034*/ column.put("String:3", "SHORT_TEXT");
        /*1035*/ column.put("String:4", "TEXT_LINE");
        /*1036*/ column.put("String:5", "TARGET_QTY");
        /*1037*/ column.put("String:6", "PRICE_1");
        /*1038*/ column.put("String:7", "PRICE_2");
        /*1039*/ column.put("String:8", "PRICE_3");
        /*1040*/ column.put("String:9", "PRICE_4");
        /*1041*/ column.put("String:10", "PRDHA");
        /*1042*/ column.put("String:11", "HER");
        /*1043*/ column.put("String:12", "NORMT");
        /*1044*/ column.put("String:13", "ZZORDCO");
        /*1045*/ column.put("String:14", "CELL_DESIGN");
        /*1046*/ column.put("String:15", "TYPEMAT");
        /*1047*/ column.put("String:16", "GRUPO_COMPRA");
        /*1048*/ column.put("String:17", "ST_1");
        /*1049*/ column.put("String:18", "VENTAS_ACUMULADO");
        /*1050*/ column.put("String:19", "VENTAS_PROMEDIO");
        /*1051*/ column.put("String:20", "CLIENTE");
        /*1053*/ String sql = "select * from proffline_tb_material;";
        /*1055*/ mapResultado = ResultExecuteQuery.obtenerDatosConsulta(sql, column, 0);
        /*1056*/ listaMateriales = new ArrayList();
        /*1057*/ if (mapResultado.size() > 0) {
            /*1058*/ HashMap res = null;
            /*1059*/ for (int i = 0; i < mapResultado.size(); i++) {
                /*1060*/ res = (HashMap) mapResultado.get(Integer.valueOf(i));
                /*1061*/ Material material = new Material();
                /*1062*/ material.setStrMATNR(res.get("MATNR").toString());
                /*1063*/ material.setStrSU(res.get("S_U").toString());
                /*1064*/ material.setStrTextLine(res.get("TEXT_LINE").toString());
                /*1065*/ material.setStrShortText(res.get("SHORT_TEXT").toString());
                /*1066*/ material.setStrPrice1(res.get("PRICE_1").toString());
                /*1067*/ material.setTipoMaterial(res.get("HER").toString());
                /*1068*/ material.setStrNORMT(res.get("NORMT").toString());
                /*1069*/ material.setStrTypeMat(res.get("TYPEMAT").toString());
                /*1070*/ listaMateriales.add(material);
            }

        }
        /*1073*/ return listaMateriales;
    }

}
