package com.efake.service;

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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author PedroArenas
 */
@Stateless
public class EmailService {

    //Connection elements
    private int port = 587;
    private String host = "smtp.gmail.com";
    private String from = "Admin@efake.com";
    private boolean auth = true;
    private String username = "efakeinc@gmail.com";
    private String password = "Efake123_";
    private ProtocolEnum protocol = ProtocolEnum.TLS;
    private boolean debug = false; //Verbose mode

    public void sendEmail(Properties mailProperties) {
        //Prepare Mail Session
        Properties properties = setProperties();
        Authenticator authenticator = setAuthentication(properties);
        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(debug);

        //Set Content
        MimeMessage message = new MimeMessage(session);
        try {
            setBasicMessageAttributes(mailProperties, message);

            //HTML email content
            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart htmlPart = new MimeBodyPart();
            String templateHTMLCode = setMailContentTemplate(mailProperties);

            htmlPart.setContent(templateHTMLCode, "text/html");

            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);

            //Send email
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    //Prepare Mail Methods
    private Properties setProperties() {
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

        return props;
    }

    private Authenticator setAuthentication(Properties properties) {
        Authenticator authenticator = null;
        if (auth) {
            properties.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }

        return authenticator;
    }

    //Set Mail Content Methods
    private void setBasicMessageAttributes(Properties mailProperties, MimeMessage message) throws AddressException, MessagingException {
        String to = mailProperties.getProperty("to");
        String subject = mailProperties.getProperty("subject");

        message.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(to)};
        message.setRecipients(Message.RecipientType.TO, address);
        message.setSubject(subject);
        message.setSentDate(new Date());
    }

    private String setMailContentTemplate(Properties mailProperties) {
        String templateString = mailProperties.getProperty("template");
        TemplatesEnum template = TemplatesEnum.valueOf(templateString);
        String HTMLCode = null;
        switch (template) {
            case DELETE_USER:
                HTMLCode = getDeleteHTMLTemplate(mailProperties);
                break;
            case EDIT_USER:
                HTMLCode = getEditHTMLTemplate(mailProperties);
        }

        return HTMLCode;
    }

    //Template getters
    private String getDeleteHTMLTemplate(Properties mailProperties) {
        String deleteAccountHTMLTemplate = "<html>\n"
                + "            <img src=\"https://github.com/PedroArs99/EFake/raw/master/img/logo.png\">\n"
                + "            <hr>\n"
                + "            <p>\n"
                + "                Dear " + mailProperties.getProperty("userName") + ", <br> \n"
                + "                We have decide to delete your Efake account for the following reasons: <br>"
                + mailProperties.getProperty("body") + "<br>"
                + "                If you want more information, please respond this email submitting your questions. <br>\n"
                + "                Yours trully, Efake Inc.<br>\n"
                + "            </p>\n"
                + "            <hr>\n"
                + "            <span>Copyright &copy; 2020 eFake Inc. All Rights Reserved.</span>\n"
                + "</html>";

        return deleteAccountHTMLTemplate;
    }

    private String getEditHTMLTemplate(Properties mailProperties) {
        String deleteAccountHTMLTemplate = "<html>\n"
                + "<img src='https://github.com/PedroArs99/EFake/raw/master/img/logo.png'>\n"
                + "\n"
                + "<hr>\n"
                + "<p>\n"
                + "    Dear " + mailProperties.getProperty("fname") + " , <br>\n"
                + "    For various reasons, an Efake administrator has decided to modify your account.<br>\n"
                + "    Here you can find your new information:\n"
                + "    <ul>\n"
                + "        <li>Email: " + mailProperties.getProperty("email") + "</li>\n"
                + "        <li>Password (Empty means it remains as always): " + mailProperties.getProperty("password") + "</li>\n"
                + "        <li>First Name: " + mailProperties.getProperty("fname") + "</li>\n"
                + "        <li>Second Name: " + mailProperties.getProperty("sname") + "</li>\n"
                + "        <li>Age: " + mailProperties.getProperty("age") + "</li>\n"
                + "        <li>Phone Number: " + mailProperties.getProperty("phone") + " </li>\n"
                + "    </ul>\n"
                + "    <br>\n"
                + "    If you want more information, please respond this email submitting your questions. <br>\n"
                + "    Yours trully, Efake Inc.<br>\n"
                + "</p>\n"
                + "\n"
                + "<hr>\n"
                + "<span>Copyright &copy; 2020 eFake Inc. All Rights Reserved.</span>\n"
                + "\n"
                + "\n"
                + "</html>;";

        return deleteAccountHTMLTemplate;
    }

}
