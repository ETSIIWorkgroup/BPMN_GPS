package org.camunda.bpmn.mails;

import java.util.logging.Logger;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class SendMail {
	final static Logger LOGGER = Logger.getLogger(SendMail.class.getName());
	
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "gsigrupo12021@gmail.com";
	private static final String PWD = "gdvtkdqseigahads";
	private static final int PORT = 465;
	
	public void sendMail(String mail, String user, String item, long amount) {
		if(mail != null && !mail.isEmpty()) {
			Email email = new SimpleEmail();
			email.setStartTLSEnabled(true);
			email.setSSLOnConnect(true);
			email.setHostName(HOST);
			email.setAuthenticator(new DefaultAuthenticator(USER, PWD));
			email.setSmtpPort(PORT);
			email.setStartTLSEnabled(true);
			try {
				email.setFrom(USER, "[GPS]");
				email.setSubject("Confirmación - Cargo tarjeta");
				email.setMsg("Estimado/a " + user + " " + mail + ","
						+ "\n\n Se ha realizado el cargo a su tarjeta de crédito. "
						+ "\n - Item: " + item + "\n - Cantidad: " + amount
						+ "\n\n Saludos cordiales."
						+ "\n Equipo técnico");
				email.addTo(mail,user);
				email.send();
			} catch(Exception e) {
				e.printStackTrace();
				LOGGER.warning("No fue posible enviar el correo a la dirección " + mail
						+ " - " + e.getMessage());
			}
		}
	}

}
