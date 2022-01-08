package org.camunda.bpmn.sepsis.resources;

import java.util.logging.Logger;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpmn.sepsis.documentos.GenerarPdfAlta;
import org.camunda.bpmn.sepsis.documentos.GenerarPdfTriaje;

/*
 * Clase Datos que captura los datos del proceso.
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
		 * Capturar el topic del evento lanzado en Camunda según sea: 
		 * - Defunción del paciente 
		 * - Alta de paciente
		 */

		// ################################################################################################################
		cliente.subscribe("defuncion").lockDuration(1000).handler((externalTask, externalTaskService) -> {

			String email = "emaildeldirectordelcentro@hospital.com";
			String nombre = "Javier Ochoa Gómez - Director del Centro";
			
			// Genera el documento relativo a los datos del paciente, capturados en el Triaje:
			GenerarPdfTriaje.nombre = nombre;
			GenerarPdfTriaje.apellidos = (String) externalTask.getVariable("apellidos");
			GenerarPdfTriaje.edad = (String) externalTask.getVariable("edad");
			GenerarPdfTriaje.fechaDeNacimiento = (String) externalTask.getVariable("fecha");
			GenerarPdfTriaje.seguridadSocial = (String) externalTask.getVariable("social");
			GenerarPdfTriaje.sexo = (String) externalTask.getVariable("sexo");
			GenerarPdfTriaje.comunidadAutonoma = (String) externalTask.getVariable("comunidad");
			GenerarPdfTriaje.pais = (String) externalTask.getVariable("pais");
			GenerarPdfTriaje.direccion = (String) externalTask.getVariable("direccion");
			GenerarPdfTriaje.generarHojaDatosTriaje();

			LOGGER.info("Se ha comunicado una defunción de paciente asociado al área de tratamiento de la SEPSIS - Revisar ahora.");

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

			// Genera el documento relativo a los datos del paciente, capturados en el Triaje:
			GenerarPdfTriaje.nombre = nombre;
			GenerarPdfTriaje.apellidos = (String) externalTask.getVariable("apellidos");
			GenerarPdfTriaje.edad = (String) externalTask.getVariable("edad");
			GenerarPdfTriaje.fechaDeNacimiento = (String) externalTask.getVariable("fecha");
			GenerarPdfTriaje.seguridadSocial = (String) externalTask.getVariable("social");
			GenerarPdfTriaje.sexo = (String) externalTask.getVariable("sexo");
			GenerarPdfTriaje.comunidadAutonoma = (String) externalTask.getVariable("comunidad");
			GenerarPdfTriaje.pais = (String) externalTask.getVariable("pais");
			GenerarPdfTriaje.direccion = (String) externalTask.getVariable("direccion");
			GenerarPdfTriaje.generarHojaDatosTriaje();
			
			// Genera el documento relativo al tratamiento aplicado al paciente, capturado al final del proceso, antes del alta:
			GenerarPdfAlta.nombre = nombre;
			GenerarPdfAlta.horaTriaje = (String) externalTask.getVariable("horatriaje");
			GenerarPdfAlta.horaActivacionCodigoSEPSIS = (String) externalTask.getVariable("horaactivacion");
			GenerarPdfAlta.horaAtencionMedica = (String) externalTask.getVariable("horaatencion");
			GenerarPdfAlta.horaAdministracionAntibiotico = (String) externalTask.getVariable("horaantibioticos");
			GenerarPdfAlta.horaAdministracionSuero = (String) externalTask.getVariable("horasuero");
			GenerarPdfAlta.horaAltaUrgencias = (String) externalTask.getVariable("horaalta");
			GenerarPdfAlta.antibioticosSuministrados = (String) externalTask.getVariable("antibioticos");
			GenerarPdfAlta.observaciones = (String) externalTask.getVariable("observaciones");
			GenerarPdfAlta.firma = (String) externalTask.getVariable("firma");
			GenerarPdfAlta.generarHojaDatosAlta();
			
			LOGGER.info("Se va a proceder a enviar el email respectivo al alta del paciente " + nombre + ".");

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
