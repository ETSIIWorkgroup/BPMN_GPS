package org.camunda.bpmn.sepsis.resources;

import java.util.logging.Logger;
import org.camunda.bpm.client.ExternalTaskClient;

/*
 * Clase Datos que captura los datos.
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fern�ndez Trigo
 *  - Juan Diego Villalobos Quir�s
 */

public class Datos {

	final static Logger LOGGER = Logger.getLogger(Datos.class.getName());
	
	public static void ejecuta() {
		
		/*
		 * Llamada a la API de Camunda (puerto 8080); crea un objeto
		 * de la clase 'ExternalTaskClient':
		 */
		ExternalTaskClient cliente = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest").build();
		capturaDatos(cliente);
		
	}
	
	private static void capturaDatos(ExternalTaskClient cliente) {

		// Inicializar un objeto de tipo 'Correo' para el env�o del email:
		Emails Correo = new Emails();

		/* Capturar el topic del evento lanzado en Camunda seg�n sea:
		 *  - Defunci�n del paciente
		 *  - Alta de paciente
		 */
		
		// ################################################################################################################
		cliente.subscribe("defuncion").lockDuration(1000).handler((externalTask, externalTaskService) -> {
			
			String email = (String) externalTask.getVariable("email");
			String nombre = (String) externalTask.getVariable("nombre");

			LOGGER.info("CONTENIDO DEL MENSAJE A ENVIAR:");

			try {
				
				Correo.enviaEmailDefuncion(email, nombre);
				LOGGER.info(">> El correo electr�nico ha sido enviado...");
				
			} catch (Exception e) {
				
				LOGGER.info(">> El correo electr�nico no ha sido enviado...");
				
			}
			externalTaskService.complete(externalTask);
		}
		).open();
		
		// ################################################################################################################
		
		cliente.subscribe("alta").lockDuration(1000).handler((externalTask, externalTaskService) -> {
			
			String email = (String) externalTask.getVariable("email");
			String nombre = (String) externalTask.getVariable("nombre");

			LOGGER.info("CONTENIDO DEL MENSAJE A ENVIAR:");

			try {
				
				Correo.enviaEmailAlta(email, nombre);
				LOGGER.info(">> El correo electr�nico ha sido enviado...");
				
			} catch (Exception e) {
				
				LOGGER.info(">> El correo electr�nico no ha sido enviado...");
				
			}
			externalTaskService.complete(externalTask);
		}
		).open();
		// ################################################################################################################
		
	}

}
