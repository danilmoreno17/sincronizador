/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proffline.sincronizador.test;

import com.proffline.sincronizador.sap.RFC;
import com.proffline.sincronizador.sqlite.DAO;
import java.util.List;

/**
 *
 * @author Rolando
 */
public class TestConexion {

    public static void main(String[] args) {
//        AGENDA
//        ----------
//        try {
//            Date fechaFin = new Date();
//            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
//            String strFechaFin = formato.format(fechaFin);
//            List<Agenda> agenda = RFC.obtenerListaAgenda("80037", strFechaFin);
//            DAO.insertarListaAgenda(agenda);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("SE TERMINÓ");
//        FIN AGENDA
//        --------------

//        CLIENTES
//        ----------
//        try {
//            List<Cliente> clientes = RFC.obtenerListaCliente("80037", "","");
//            for (Cliente cliente : clientes) {
//                System.out.println(cliente);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("SE TERMINÓ");
//        FIN CLIENTES
//        --------------

//        STOCK
//        -------
//        try {
//            List<MaterialStock> listaMaterialStock = RFC.obtenerListaMaterialStock();
//            for (MaterialStock ms : listaMaterialStock) {
//                System.out.println(ms);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FIN STOCK
//        ------------

//        SEDE
//        --------
//        try {
//            List<Destinatario> dts = RFC.obtenerListaDestinatario("80537");
//            for (Destinatario destinatario : dts) {
//                System.out.println(destinatario);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FIN SEDE
//        ----------
//        DISPOSITIVO
//        ------------
//        try {
//            List<Dispositivo> dispositivos = RFC.obtenerListaDispositivo("");
//            for (Dispositivo dispositivo : dispositivos) {
//                System.out.println(dispositivo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FIN DISPOSITIVO
//        ---------------
//        try {
//            Sincronizador.sincronizarTablaRol();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<String> roles = DAO.obtenerListaIdRoles();
//        for (String rol : roles) {
//            System.out.println(rol);
//        }
//        try {
//            Sincronizador.sincronizarTablaRol();
//            Sincronizador.sincronizarTablaVista();
//            Sincronizador.sincronizarTablaVistaRol();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            List<List> listaCombo = RFC.obtenerListaCombo();
            DAO.insertarListaCombo(listaCombo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
