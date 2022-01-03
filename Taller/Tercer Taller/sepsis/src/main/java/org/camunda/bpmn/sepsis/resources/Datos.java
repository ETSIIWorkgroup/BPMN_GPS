package org.camunda.bpmn.sepsis.resources;

import java.util.logging.Logger;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpmn.sepsis.main.GenerarPdfTriaje;

/*
 * Clase Datos que captura los datos.
 * 
 * Taller III - Tema: SEPSIS - Grupo 5 
 * Integrantes:
 *  - Pedro Escobar Rubio
 *  - Alejandro Fernández Trigo
 *  - Juan Diego Villalobos Quirós
 */

public class Datos {

	final static Logger LOGGER = Logger.getLogger(Datos.class.getName());

	public static void ejecuta() {

		/*
		 * Llamada a la API de Camunda (puerto 8080); crea un objeto de la clase
		 * 'ExternalTaskClient':
		 */
		ExternalTaskClient cliente = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest").build();
		capturaDatos(cliente);

	}

	private static void capturaDatos(ExternalTaskClient cliente) {

		// Inicializar un objeto de tipo 'Correo' para el envío del email:
		Emails Correo = new Emails();

		/*
		 * Capturar el topic del evento lanzado en Camunda según sea: - Defunción del
		 * paciente - Alta de paciente
		 */

		// ################################################################################################################
		cliente.subscribe("defuncion").lockDuration(1000).handler((externalTask, externalTaskService) -> {

			String email = (String) externalTask.getVariable("email");
			String nombre = (String) externalTask.getVariable("nombre");

			LOGGER.info("CONTENIDO DEL MENSAJE A ENVIAR:");

			try {

				Correo.enviaEmailDefuncion(email, nombre);
				LOGGER.info(">> El correo electrónico ha sido enviado...");

			} catch (Exception e) {

				LOGGER.info(">> El correo electrónico no ha sido enviado...");

			}
			externalTaskService.complete(externalTask);
		}).open();

		// ################################################################################################################

		cliente.subscribe("alta").lockDuration(1000).handler((externalTask, externalTaskService) -> {

			String email = (String) externalTask.getVariable("email");
			String nombre = (String) externalTask.getVariable("nombre");

			try {
				//GenerarPdfTriaje.nombre = nombre;
				//GenerarPdfTriaje.apellidos = email;
				GenerarPdfTriaje.generarHojaDatosTriaje();
			} catch (Exception e) {
				LOGGER.info("El pdf ha fallado");
			}

			LOGGER.info("CONTENIDO DEL MENSAJE A ENVIAR:");

			try {

				Correo.enviaEmailAlta(email, nombre);
				LOGGER.info(">> El correo electrónico ha sido enviado...");

			} catch (Exception e) {

				LOGGER.info(">> El correo electrónico no ha sido enviado...");

			}
			externalTaskService.complete(externalTask);
		}).open();
		// ################################################################################################################

	}

}
