package org.camunda.bpmn.sepsis.resources;

import java.util.logging.Logger;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpmn.sepsis.main.GenerarPdfTriaje;

/*
 * Clase Emails que gestiona el envío de los mensajes.
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fernández Trigo
 *  - Juan Diego Villalobos Quirós
 */

public class Emails {

	final static Logger LOGGER = Logger.getLogger(Emails.class.getName());

	/*
	 * Detalles del servidor de correo:
	 */
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "gsigrupo12021@gmail.com";
	private static final String PWD = "gdvtkdqseigahads";
	private static final int PORT = 465;

	/*
	 * Casuística capturada:
	 * 	- Defunción del paciente --> public void enviaEmailDefuncion()
	 *  - Alta del paciente --> public void enviaEmailAlta()
	 */
	
	// ################################################################################################################
	public void enviaEmailDefuncion(String email, String nombre) {
		
		if (email != null && !email.isEmpty()) {
			
			// Nuevo objeto de tipo 'Email' para el envío del correo:
			Email mail = new SimpleEmail();
			mail.setStartTLSEnabled(true);
			mail.setSSLOnConnect(true);
			mail.setHostName(HOST);
			mail.setAuthenticator(new DefaultAuthenticator(USER, PWD));
			mail.setSmtpPort(PORT);
			mail.setStartTLSEnabled(true);
			
			try {
				
				mail.setFrom(USER, "[Hospital]");
				mail.setSubject("Defunción Paciente");
				mail.setMsg("Estimado/a...");
				mail.addTo(email, nombre);
				mail.send();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				LOGGER.warning("No fue posible enviar el correo a la dirección " + mail + " - " + e.getMessage());
				
			}
		}
	}
	
	// ################################################################################################################
	
	public void enviaEmailAlta(String email, String nombre) {
		
		if (email != null && !email.isEmpty()) {
			
			System.out.println("El nombre es: " + nombre);
			System.out.println("El email es: " + email);

			// Nuevo objeto de tipo 'Email' para el envío del correo:
			Email mail = new SimpleEmail();
			mail.setStartTLSEnabled(true);
			mail.setSSLOnConnect(true);
			mail.setHostName(HOST);
			mail.setAuthenticator(new DefaultAuthenticator(USER, PWD));
			mail.setSmtpPort(PORT);
			mail.setStartTLSEnabled(true);
			
			try {
				
				mail.setFrom(USER, "[Hospital]");
				mail.setSubject("Alta Paciente");
				mail.setMsg("Estimado/a...");
				mail.addTo(email, nombre);
				mail.send();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				LOGGER.warning("No fue posible enviar el correo a la dirección " + mail + " - " + e.getMessage());
				
			}
		}
	}
	// ################################################################################################################
	
}
