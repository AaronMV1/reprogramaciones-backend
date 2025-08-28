

package pe.edu.upsjb.reprogramaciones.util;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Properties;


@Service


public class CorreoService {


    /*
    static final String FROM = "requerimientosdsi@upsjb.edu.pe";
    static final String FROMNAME = "Requerimientos UPSJB";
    static final String HOST = "smtp.office365.com";
    static final String PASSWORD = "K!404099523005aw";
    static final int PORT = 587;
    */


    static final String FROM = "halion71332@gmail.com";
    static final String FROMNAME = "Requerimientos UPSJB";
    static final String HOST = "smtp.gmail.com";
    static final String PASSWORD = "fcot wzgq xogj erli";
    static final int PORT = 587;


    @Autowired

    public static Session getConfiguracion() {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });

        return session;

    }

    public static void enviarMensaje(String destinatario, String correoCopia, String asunto, String cuerpo) {

        try {

            Session session = getConfiguracion();
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            if (correoCopia != null && !correoCopia.trim().isEmpty()) {
                String[] correos = correoCopia.split(",");
                for (String correo : correos) {
                    msg.addRecipient(Message.RecipientType.CC, new InternetAddress(correo.trim()));
                }
            }

            msg.setSubject(asunto);

            msg.setContent(cuerpo, "text/html; charset=UTF-8");

            Transport.send(msg);
            System.out.println("Mensaje enviado exitosamente");

        } catch (MessagingException e) {

            System.out.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

