package br.com.eric.microsservicerabbitmq.rest.exchangeToExchange;

import br.com.eric.microsservicerabbitmq.rest.Person;
import br.com.eric.microsservicerabbitmq.rest.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ExchanteToExchangeController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    private final RabbitTemplate rabbitTemplate;

    public ExchanteToExchangeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //A única diferença aqui é que nas configurações do RabbitMQ foi feito um bind dentro da Direct-Exchange com a Fanout-Exchange, ou seja, estou
    // enviando a mensagem para a Direct-Exchange mas com a routing key fanout, que irá enviar a mensagem para a Fanout-Exchange e então irá enviar
    // a mensagem para as filas cadastradas dentro da Fanout-Exchange.
    @GetMapping("/exchange-to-exchange/{name}")
    public String testAPI(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("Direct-Exchange", "fanout", person);
        LOG.info("The message sent to Fanout-Exchange was: {}", person);
        return "Success";
    }
}
