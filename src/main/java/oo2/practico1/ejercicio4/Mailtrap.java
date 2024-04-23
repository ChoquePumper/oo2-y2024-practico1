package oo2.practico1.ejercicio4;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Mailtrap implements ServicioEmail {

	private static final String host = "sandbox.smtp.mailtrap.io";
	private final String username;
	private final String password;

	public Mailtrap(String username, String password) {
		this.username = username;
		this.password = password;
	}

	private Properties armarPropiedades() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		return props;
	}

	private Session getSesion() {
		return Session.getInstance(armarPropiedades(),
				new jakarta.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}

	@Override
	public void enviarMensaje(Mensaje mensaje) {
		try {
			//create a MimeMessage object
			Message message = new MimeMessage(getSesion());
			//set From email field
			message.setFrom(new InternetAddress(mensaje.remitente()));
			//set To email field
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mensaje.destinatario()));
			//set email subject field
			message.setSubject(mensaje.asunto());
			//set the content of the email message
			message.setText(mensaje.cuerpo());
			//send the email message
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
