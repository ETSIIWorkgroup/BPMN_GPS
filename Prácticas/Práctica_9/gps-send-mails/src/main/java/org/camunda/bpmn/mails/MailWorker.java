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
				String mail = (String) externalTask.getVariable("mail");
				String user = (String) externalTask.getVariable("user");
				String item = (String) externalTask.getVariable("item");
				Long amount = (Long) externalTask.getVariable("amount");
	
				LOGGER.info("CONTENIDO DEL MENSAJE A ENVIAR:"
						+ "\n Estimado/a " + user + " <" + mail + ">,\n"
						+ "\n Se ha realizado el cargo a su tarjeta de crédito con los siguientes datos: "
						+ "\n - Item: " + item + "\n - Monto: " + amount);
		
				try {
					sendMail.sendMail(mail,user,item,amount);
					LOGGER.info(">> El correo electrónico ha sido enviado...");
				}catch(Exception e) {
					LOGGER.info(">> El correo electrónico no ha sido enviado...");					
				}
				externalTaskService.complete(externalTask);
			})
			.open();
	}
}











	


