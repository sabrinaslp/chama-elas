package com.soulcode.chamaelas.ChamaElas.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    public void enviarEmailBoasVindas(String destinatario, String nomeUsuario, String token) {
        String assunto = "Bem-vindo(a) ao ChamaElas!";
        String corpo = "<html><body>" +
                "<p>Olá, <strong>" + nomeUsuario + "</strong>!</p>" +
                "<p>Você se registrou em nossa aplicação e tem direito a 7 dias de teste gratuito! Aproveite para conhecer todos os recursos e funcionalidades.</p>" +
                "<p>Seu token de confirmação é: <strong>" + token + "</strong></p>" +
                "<p>Atenciosamente,<br>ChamaElas</p>" +
                "</body></html>";

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Adiciona o provedor de ativação específico do JavaMail
            MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mailcapCommandMap);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);

            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(corpo, "text/html; charset=utf-8");
            mimeMultipart.addBodyPart(htmlPart);

            message.setContent(mimeMultipart);

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso para: " + destinatario);
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar e-mail para: " + destinatario);
            e.printStackTrace();
        }
    }
}
