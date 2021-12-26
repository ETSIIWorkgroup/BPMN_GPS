package chargeCardWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.net.URI;

import org.camunda.bpm.client.ExternalTaskClient;

public class ChargeCardWorker {
  private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());

  public static void main(String[] args) {
    ExternalTaskClient client = ExternalTaskClient.create()
        .baseUrl("http://localhost:8080/engine-rest")
        .asyncResponseTimeout(10000) // long polling timeout
        .build();

    // subscribe to an external task topic as specified in the process
    client.subscribe("charge-card")
        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
        .handler((externalTask, externalTaskService) -> {
          // Put your business logic here

          // Get a process variable
          String item = (String) externalTask.getVariable("item");
          Long amount = (Long) externalTask.getVariable("amount");
          
          // Map para guardar las variables
          Map<String, Object> variables = new HashMap<String, Object>();
          
          if (amount < 1000) {
        	  System.out.println("Valor menor a 1000");
        	  variables.put("result", "Approved");
          } else {
        	  System.out.println("Valor mayor a 1000");
        	  variables.put("result", "Not approved");
          }

          LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

          try {
              Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
          } catch (Exception e) {
              e.printStackTrace();
          }

          // Complete the task (enviando o no variables)
          //externalTaskService.complete(externalTask);
          externalTaskService.complete(externalTask, variables);
        })
        .open();
  }
}