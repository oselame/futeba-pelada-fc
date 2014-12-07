package br.com.softal.pfc.email;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail extends Authenticator {
	
    static {
    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    }
	
    //private String user = "peladafc@googlegroups.com";
    //private String password = "email.peladafc";
    
    private String user = "adrianooselame@gmail.com";
    private String password = "04047501";    
	
	public SendEmail() {
	}
	
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
    
	@SuppressWarnings("static-access")
	public void enviarEmail(StringBuilder corpoMsg, String sdtPartida) throws Exception {
		
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465"); //-- 465
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
		
        Session session = Session.getDefaultInstance(props, this);
        session.setDebug(true);
        Transport transport = session.getTransport();

        MimeMessage message = new MimeMessage(session);
        message.setSubject("Pelada Futebol Clube - Resultado - " + sdtPartida);
        //message.setFrom(new InternetAddress("peladafc@googlegroups.com"));
        //message.addRecipient(Message.RecipientType.TO, new InternetAddress("peladafc@googlegroups.com"));
        
        message.setFrom(new InternetAddress("adrianooselame@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("adrianoozelame@gmail.com"));

        //
        // This HTML mail have to 2 part, the BODY and the embedded image
        //
        MimeMultipart multipart = new MimeMultipart("related");

        // first part  (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        
        messageBodyPart.setContent(corpoMsg.toString(), "text/html");

        // add it
        multipart.addBodyPart(messageBodyPart);
        
        // second part (the image)
        /*
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:\\images\\jht.gif");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<image>");
		*/
        // add it
        multipart.addBodyPart(messageBodyPart);

        // put everything together
        message.setContent(multipart);
        transport.send( message );
        /*
        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
        */

	}
}
