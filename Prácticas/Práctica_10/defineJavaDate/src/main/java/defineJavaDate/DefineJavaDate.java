package defineJavaDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;

public class DefineJavaDate {
	private final static Logger LOGGER = Logger.getLogger(DefineJavaDate.class.getName());

	public static void main(String[] args) {
		ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();

		// subscribe to an external task topic as specified in the process
		client.subscribe("defineJavaDate")
			.lockDuration(1000) 
			.handler((externalTask, externalTaskService) -> {
					
					LOGGER.info("Definiendo la fecha desde JAVA...");
					
					Map<String, Object> variables = new HashMap<String, Object>();
					// Get a process variable
					Date cita = null;
					Date currentDate = null;
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					
					try {
						cita = dateFormat.parse("2021-12-21T16:55:00");
						currentDate = dateFormat.parse(LocalDateTime.now().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}

					variables.put("cita", cita);
					variables.put("currentDate", currentDate);
					
					LOGGER.info("Fin de tarea...\n  -" + cita + "\n  -" + currentDate);
					externalTaskService.complete(externalTask, variables);			
				})
			.open();
	}

}
