package br.com.softal.pfc.email;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.softal.pfc.Constantes;
import br.com.softal.pfc.config.PfcConfig;

public class SendEmail2 extends Authenticator {

	static {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	}

	private Properties props = new Properties();
	private Transport transport = null;
	private MimeMessage message = null;
	private Session session = null;
	
	// private String user = "peladafc@googlegroups.com";
	// private String password = "email.peladafc";

	private String user = null; // "adrianooselame@gmail.com";
	private String password = null; // "04047501";

	private StringBuilder corpoMsg;
	private String subject;
	private String from = null;
	
	private List<InternetAddress> listRecipiente;
	
	protected String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public StringBuilder getCorpoMsg() {
		return corpoMsg;
	}
	
	public void setCorpoMsg(String corpoMsg) {
		setCorpoMsg(new StringBuilder());
		getCorpoMsg().append(corpoMsg);
	}

	public void setCorpoMsg(StringBuilder corpoMsg) {
		this.corpoMsg = corpoMsg;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	protected List<InternetAddress> getListRecipiente() {
		return listRecipiente;
	}

	protected void setListRecipiente(List<InternetAddress> listRecipiente) {
		this.listRecipiente = listRecipiente;
	}
	
	protected String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	protected String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addRecipient(String address, String personal) {
		try {
			if (personal != null) {
				getListRecipiente().add(new InternetAddress(address, personal));
			} else {
				getListRecipiente().add(new InternetAddress(address));
			}
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addRecipient(String address) {
		this.addRecipient(address, null);
	}

	public SendEmail2() {
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465"); // -- 465
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");
		session = Session.getDefaultInstance(props, this);
		session.setDebug(true);
		setListRecipiente(new ArrayList<InternetAddress>());
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}

	@SuppressWarnings("static-access")
	public void enviarEmail() {
		try {
			Boolean bEnviarEmail = Boolean.valueOf( PfcConfig.getProperty(Constantes.CONFIG_EMAIL_ENVIAR) );
			if (bEnviarEmail) {
				transport = session.getTransport();
				message = new MimeMessage(session);
				message.setSubject(getSubject());
				message.setFrom(new InternetAddress(getFrom()));
				for (InternetAddress ia : listRecipiente) {
					message.addRecipient(Message.RecipientType.TO, ia);
				}
				MimeMultipart multipart = new MimeMultipart("related");
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(corpoMsg.toString(), "text/html");
				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
				if (listRecipiente.size() > 0) {
					transport.send(message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
