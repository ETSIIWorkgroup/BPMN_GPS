package org.camunda.bpmn.sepsis.resources;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/*
 * Clase Emails que gestiona el env�o de los mensajes.
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fern�ndez Trigo
 *  - Juan Diego Villalobos Quir�s
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
	 * Casu�stica capturada:
	 * 	- Defunci�n del paciente --> public void enviaEmailDefuncion()
	 *  - Alta del paciente --> public void enviaEmailAlta()
	 */
	
	// ################################################################################################################
	public void enviaEmailDefuncion(String email, String nombre) {
		
		if (email != null && !email.isEmpty()) {
			
			// Nuevo objeto de tipo 'Email' para el env�o del correo:
			Email mail = new SimpleEmail();
			mail.setStartTLSEnabled(true);
			mail.setSSLOnConnect(true);
			mail.setHostName(HOST);
			mail.setAuthenticator(new DefaultAuthenticator(USER, PWD));
			mail.setSmtpPort(PORT);
			mail.setStartTLSEnabled(true);
			
			try {
				
				mail.setFrom(USER, "[Hospital]");
				mail.setSubject("Defunci�n Paciente");
				mail.setMsg("A la atenci�n del director del centro; se ha comunicado la defunci�n de un paciente a fecha de " + LocalDate.now().toString());
				mail.addTo(email, nombre);
				mail.send();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				LOGGER.warning("No fue posible enviar el correo a la direcci�n " + mail + " - " + e.getMessage());
				
			}
		}
	}
	
	// ################################################################################################################
	
	public void enviaEmailAlta(String email, String nombre, String horaActivacionCodigoSEPSIS, String horaAltaUrgencias, String observaciones, String firma) {
		
		if (email != null && !email.isEmpty()) {
			
			System.out.println("Nombre del paciente: " + nombre);
			System.out.println("Email del paciente: " + email);

			// Nuevo objeto de tipo 'Email' para el env�o del correo:
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
				mail.setMsg("Estimado/a " + nombre + " se procede a remitirle los datos de su estancia en nuestras �reas hospitalarias."
						+ "\n" + "Hora ingreso: " + horaActivacionCodigoSEPSIS
						+ "\n" + "Hora alta: " + horaAltaUrgencias
						+ " del " + LocalDate.now().toString()
						+ "\n\n" + "Observaciones: " + observaciones
						+ "\n" + "Firmado: " + firma
						);
				mail.addTo(email, nombre);
				mail.send();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				LOGGER.warning("No fue posible enviar el correo a la direcci�n " + mail + " - " + e.getMessage());
				
			}
		}
	}
	// ################################################################################################################
	
}
