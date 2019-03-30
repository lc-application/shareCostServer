package com.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private static void sendEmail(String recipient, String subject, String text){
        String email = "sharecostservice@gmail.com";
        String password = "sharecost";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(email, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }

    }

    public static void sendRegisterEmail(String recipient, String name){
        if (recipient == null || recipient.equals("") || name == null || name.equals("")) {
            throw new IllegalArgumentException("empty");
        }
        new Thread(() -> {
            String subject = "Hello from share cost";
            String text = "Dear " + name + ":" +
                    "\n\n This is Share cost! Thanks for using our products! :)" +
                    "\n\n - Team Share Cost";
            sendEmail(recipient, subject, text);
        }).start();
    }

}
