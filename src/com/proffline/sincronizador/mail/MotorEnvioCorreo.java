package com.proffline.sincronizador.mail;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MotorEnvioCorreo {

    public static void main(String[] args) {
//        enviarCorreo("Test", "Test", "rflores@promesa.com.ec.ec", "mmoyanol@espol.edu.ec", "", "");
        envioCorreoElectronico("Texto de Prueba");
    }

    /**
     * @param asunto : Asunto del mensaje
     * @param mensaje : Cuerpo del Mensaje
     * @param correoDestinatarioPara : Correos destinatarios que se encuentran
     * en el campo Para
     * @param correoDestinatarioConCopia : Correos destinatarios que se
     * encuentran en el campo Cc
     * @param correoDestinatarioConCopiaOculto : Correos destinatarios que se
     * encuentran en el campo Cco
     * @return true si el envío es conforme y false si no es conforme.
     */
    @SuppressWarnings({"finally"})
    public static synchronized boolean enviarCorreo(String asunto, String mensaje, String correoDestinatarioPara, String correoDestinatarioConCopia, String correoDestinatarioConCopiaOculto, String archivoAdjunto) {
        boolean envio = false;
        try {
            final Properties props = new Properties();
            InputStreamReader in = null;
            in = new InputStreamReader(new FileInputStream("smtp.properties"), "UTF-8");
            props.load(in);
            String remitente = props.getProperty("mail.smtp.correoRemitente").trim();
            String destinatario = props.getProperty("mail.smtp.correoDestinatario").trim();
            String host = props.getProperty("mail.smtp.host").trim();
            System.getProperties().put("mail.host", host);
            asunto = props.getProperty("mail.smtp.asunto").trim();

            URL u = new URL("mailto:" + destinatario);

            System.out.println("From: \"" + remitente + "\" <"
                    + System.getProperty("user.name") + "@"
                    + InetAddress.getLocalHost().getHostName() + ">");

            URLConnection c = u.openConnection();
            c.setDoInput(false);
            c.setDoOutput(true);
            c.connect();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(c.getOutputStream()));
            out.println("From: \"" + remitente + "\" <"
                    + System.getProperty("user.name") + "@"
                    + InetAddress.getLocalHost().getHostName() + ">");
            out.println("To: " + destinatario);
            out.println("Subject: " + asunto);
            out.println();
            out.println(mensaje);
            out.close();

//            Properties propiedades = new Properties();
//            propiedades.setProperty("mail.smtp.host", props.getProperty("mail.smtp.host").trim());
//            propiedades.setProperty("mail.smtp.starttls.enable", props.getProperty("mail.smtp.ssl.enable").trim());
//            propiedades.setProperty("mail.smtp.port", props.getProperty("mail.smtp.port").trim());
//            propiedades.setProperty("mail.smtp.user", props.getProperty("mail.smtp.user").trim());
//            propiedades.setProperty("mail.smtp.auth", props.getProperty("mail.smtp.auth").trim());
//            Session session = Session.getDefaultInstance(propiedades);
//            session.setDebug(true);
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(props.getProperty("mail.smtp.user").trim()));
//            if(correoDestinatarioPara != null){
//                // Enviamos el correo a los principales destinatarios
//                if (correoDestinatarioPara.indexOf(',') > 0)
//                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestinatarioPara));
//                else
//                    message.setRecipient(Message.RecipientType.TO,new InternetAddress(correoDestinatarioPara));
//            }
//            if(correoDestinatarioConCopia != null){
//                // Enviamos el correo con copia
//                if (correoDestinatarioConCopia.indexOf(',') > 0)
//                    message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(correoDestinatarioConCopia));
//                else
//                    message.setRecipient(Message.RecipientType.CC,new InternetAddress(correoDestinatarioConCopia));
//            }
//            if(correoDestinatarioConCopiaOculto != null){
//                // Enviamos el correo con copia oculta
//                if (correoDestinatarioConCopiaOculto.indexOf(',') > 0)
//                    message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(correoDestinatarioConCopiaOculto));
//                else
//                    message.setRecipient(Message.RecipientType.BCC,new InternetAddress(correoDestinatarioConCopiaOculto));
//            }
//            message.setSubject(asunto);
//            message.setText(mensaje);
//            BodyPart adjunto = new MimeBodyPart();
//            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivoAdjunto)));
//            adjunto.setFileName(archivoAdjunto);
//            Transport t = session.getTransport("smtp");
//            t.connect(props.getProperty("mail.smtp.user").trim(), props.getProperty("mail.smtp.password").trim());
//            t.sendMessage(message, message.getAllRecipients());
//            t.close();
//            in.close();
            envio = true;
        } catch (Exception e) {
            e.printStackTrace();
            envio = false;
        } finally {
            return envio;
        }
    }

    public static boolean envioCorreoElectronico(String mensaje) {
        try {
            Properties propiedades = new Properties();
            
            propiedades.put("mail.smtp.starttls.enable", true);
            propiedades.put("mail.smtp.auth", true);
            propiedades.put("mail.smtp.debug", true);
            
            InputStreamReader in = null;
            in = new InputStreamReader(new FileInputStream("smtp.properties"), "UTF-8");
            propiedades.load(in);
            
//            propiedades.put("mail.smtp.host", "10.10.10.208");
//            propiedades.put("mail.smtp.correoRemitente", "envios@promesa.com.ec");
//            propiedades.put("mail.smtp.password", "Pr0m3s42012");
//            propiedades.put("mail.smtp.port", "25");
//            propiedades.put("mail.smtp.ssl.trust", "10.10.10.208");
//            propiedades.put("mail.smtp.correoDestinatario", "mmoyano@espol.edu.ec");
//            propiedades.put("mail.smtp.asunto", "Informe de sincronizacion PROFFLINE-10.10.10.19");
//            propiedades.put("mail.smtp.ssl.trust", "10.10.10.208");
            
            Session session = Session.getDefaultInstance(propiedades);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.correoRemitente")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) propiedades.get("mail.smtp.correoDestinatario")));
            message.setSubject(propiedades.get("mail.smtp.asunto").toString());
            message.setText(mensaje);
            Transport t = session.getTransport("smtp");
            t.connect((String) propiedades.get("mail.smtp.correoRemitente"), propiedades.getProperty("mail.smtp.password"));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MotorEnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
