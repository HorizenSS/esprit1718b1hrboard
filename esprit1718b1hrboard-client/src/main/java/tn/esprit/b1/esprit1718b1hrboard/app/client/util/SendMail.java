package tn.esprit.b1.esprit1718b1hrboard.app.client.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */

    import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class SendMail 
{

    public static void send(String to, String sub,String msg, final String user, final String pass) 
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            JOptionPane.showMessageDialog(null,"Email sended!");
            
        } catch (MessagingException e) 
        {
            JOptionPane.showMessageDialog(null,"Something happened!");
            
            throw new RuntimeException(e);
        }
        
    }

    public static void sendEmailWithAttachments(
             String toAddress,
            String subject, String message, String attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", "allstarcreww@gmail.com");
        props.put("mail.password", "pumapuma");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("allstarcreww@gmail.com", "pumapuma");
            }
        };
        Session session = Session.getInstance(props, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress("allstarcreww@gmail.com"));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
     //   if (attachFiles != null && attachFiles.length > 0) {
       //     for (String filePath : attachFiles) {
        messageBodyPart = new MimeBodyPart();
      
        DataSource source = new FileDataSource(attachFiles);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(attachFiles);
        multipart.addBodyPart(messageBodyPart);
            
        
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }

}
