package org.murugappan.service;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.murugappan.DAO.ProductsDAO;
import org.murugappan.DAO.ProductsImpl;

import javax.activation.*;

public class MailService {
			static Session newSession = null;
		    MimeMessage mimeMessage = null;
		    ProductsDAO pi=new ProductsImpl();
		 
		    void sendMail() {
		        MailService mail = new MailService();
		        MailService.setupServerProperties();
		        mail.draftEmail();
		        mail.sendEmail();
		    }	    
		 
		    private MimeMessage draftEmail() {
		        String emailRecepients = "murugappan_m@solartis.com";
		        String emailSubject = "List Of Products Out Of Stock";
		        String lowStockProducts=pi.showLowStockItems();
		        System.out.println(lowStockProducts);
		        String emailBody = "<h1><b>The Following Products Are Running Out Of Stock </b></h1> <h2><i>Please Restock It!</i></h2>"+lowStockProducts+"";
		        mimeMessage = new MimeMessage(newSession);
		        try {
		            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecepients));
		            mimeMessage.setSubject(emailSubject);
		            MimeMultipart multipart = new MimeMultipart();
		            MimeBodyPart bodyPart = new MimeBodyPart();
		            bodyPart.setContent(emailBody, "text/html");
		            multipart.addBodyPart(bodyPart);
		            mimeMessage.setContent(multipart);
		            return mimeMessage;
		        } catch (MessagingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		 
		    private void sendEmail() {
		        String fromUser = "sriharishr105@gmail.com";
		        String fromUserPassword = "czwympvajwawowbh";
		String emailHost = "smtp.gmail.com";
		        Transport transport = null;
		        try {
		            transport = newSession.getTransport("smtp");
		            transport.connect(emailHost,587, fromUser, fromUserPassword);
		            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		            transport.close();
		            System.out.println("success!");
		        } catch (MessagingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		 
		    private static void setupServerProperties() {
		        Properties properties = System.getProperties();
		        properties.put("mail.smtp.port", "587");
		        properties.put("mail.smtp.starttls.enable", "true");
		        newSession = Session.getDefaultInstance(properties, null);
		    }
		
	}


