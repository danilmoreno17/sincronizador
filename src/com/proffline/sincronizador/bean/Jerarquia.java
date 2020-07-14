/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.bean;

/**
 *
 * @author Administrador
 */
public class Jerarquia {

    private String strIdPadre;
    private String strPRODH;
    private String strS;
    private String strVTEXT;
    private String strZZSEQ;
    private String strICON;
    private String strI;
    private String strCellDesign;

    public String getStrCellDesign() {
        return strCellDesign;
    }

    public void setStrCellDesign(String strCellDesign) {
        this.strCellDesign = strCellDesign;
    }

    public String getStrI() {
        return strI;
    }

    public void setStrI(String strI) {
        this.strI = strI;
    }

    public String getStrICON() {
        return strICON;
    }

    public void setStrICON(String strICON) {
        this.strICON = strICON;
    }

    public String getStrIdPadre() {
        return strIdPadre;
    }

    public void setStrIdPadre(String strIdPadre) {
        this.strIdPadre = strIdPadre;
    }

    public String getStrPRODH() {
        return strPRODH;
    }

    public void setStrPRODH(String strPRODH) {
        this.strPRODH = strPRODH;
    }

    public String getStrS() {
        return strS;
    }

    public void setStrS(String strS) {
        this.strS = strS;
    }

    public String getStrVTEXT() {
        return strVTEXT;
    }

    public void setStrVTEXT(String strVTEXT) {
        this.strVTEXT = strVTEXT;
    }

    public String getStrZZSEQ() {
        return strZZSEQ;
    }

    public void setStrZZSEQ(String strZZSEQ) {
        this.strZZSEQ = strZZSEQ;
    }

    @Override
    public String toString() {
        return "Jerarquia{" + "strIdPadre=" + strIdPadre + ", strPRODH=" + strPRODH + ", strS=" + strS + ", strVTEXT=" + strVTEXT + ", strZZSEQ=" + strZZSEQ + ", strICON=" + strICON + ", strI=" + strI + ", strCellDesign=" + strCellDesign + '}';
    }
}
