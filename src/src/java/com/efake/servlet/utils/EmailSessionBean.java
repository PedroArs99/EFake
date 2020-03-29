/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.utils;

import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class EmailSessionBean {
    //Connection elements
    private int port = 587;
    private String host = "smtp.gmail.com";
    private String from = "Admin@efake.com";
    private boolean auth = true;
    private String username = "efakeinc@gmail.com";
    private String password = "Efake123_";
    private ProtocolEnum protocol = ProtocolEnum.TLS;
    private boolean debug = false; //Verbose mode
    
    
    //template elements
    

    public void sendEmail(String to, String subject, String userName, String body,TemplatesEnum template) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        switch (protocol) {
            case SMTPS:
                props.put("mail.smtp.ssl.enable", true);
                break;
            case TLS:
                props.put("mail.smtp.starttls.enable", true);
                break;
        }

        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            //message.setText(body); // Plain text message

            //HTML email content
            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart htmlPart = new MimeBodyPart();
            String templateHTMLCode = null;
            switch(template){
                case DELETE_USER: 
                    templateHTMLCode = getDeleteHTMLTemplate(userName, body);
                    break;
            }
            htmlPart.setContent(templateHTMLCode, "text/html");

            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);

            //Send email
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }
    
    private String getDeleteHTMLTemplate(String userName,String body){
        String deleteAccountHTMLTemplate = "<html>\n"
                    + "            <img src=\"https://github.com/PedroArs99/EFake/raw/master/img/logo.png\">\n"
                    + "            <hr>\n"
                    + "            <p>\n"
                    + "                Dear "+ userName + ", <br> \n"
                    + "                We have decide to delete your Efake account for the following reasons: <br>"
                    +                  body + "<br>"
                    + "                If you want more information, please respond this email submitting your questions. <br>\n"
                    + "                Yours trully, Efake Inc.<br>\n"
                    + "            </p>\n"
                    + "            <hr>\n"
                    + "            <span>Copyright &copy; 2020 eFake Inc. All Rights Reserved.</span>\n"
                    + "</html>";
        
        return deleteAccountHTMLTemplate;
    }
}
