package org.camunda.bpmn.mails;

import java.util.logging.Logger;
import org.camunda.bpm.client.ExternalTaskClient;

public class MailWorker {
	final static Logger LOGGER = Logger.getLogger(MailWorker.class.getName());
	
	public static void main(String[] args) {
		ExternalTaskClient client = ExternalTaskClient.create()
				.baseUrl("http://localhost:8080/engine-rest")
				.build();
		
		SendMail sendMail = new SendMail();
		
		client.subscribe("payment-mail")
		.lockDuration(1000)
		.handler((externalTask, externalTaskService) -> {
			String mail = (String) externalTask.getVariable("email");
			String user = (String) externalTask.getVariable("user");
			String item = (String) externalTask.getVariable("item");
			Long amount = (Long) externalTask.getVariable("amount");
			
			LOGGER.info("CONTENIDO DEL MENSAJE A ENVIAR:"
					+ "\n Estimado/a " + user + " " + mail + ",\n"
					+ "\n Se ha realizado el cargo a su tarjeta de cr�dito con los siguientes datos:"
					+ "\n - Item: " + item + "\n - Cantidad: " + amount);
			
			try {
				sendMail.sendMail("alejandrofertry@gmail.com","Alejandro","GPS Item",800);
				LOGGER.info(">> El correo electr�nico ha sido enviado...");
			} catch(Exception e) {
				LOGGER.info(">> El correo electr�nico no ha sido enviado...");
			}
			externalTaskService.complete(externalTask);
		})
		.open();
	}

}
