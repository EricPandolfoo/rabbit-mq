package br.com.eric.microsservicerabbitmq.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    private final RabbitTemplate rabbitTemplate;

    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) {
        Person person = new Person(1L, "Eric");
        rabbitTemplate.convertAndSend("TV", person); //Na verdade aqui é o nome da fila e não a routingKey.
        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", person);
        rabbitTemplate.convertAndSend("Fanout-Exchange", "", person);
        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);
        LOG.info("The message sent was: {}", person);
        return "Success";
    }

    @GetMapping("/headers/{name}")
    public String testHandlerExchange(@PathVariable("name") String name) throws IOException {
        Person person = new Person(1L, "Eric");

        //Maneira "primitiva" de transformar um objeto em byte[];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(person);
        out.flush();
        out.close();

        byte[] byteMessage = bos.toByteArray();
        bos.close();

        //Poderia converter dessa meneira também
        byte[] bytes = convertToBytes(person);

        //Outra maneira muito mais simples de converter.
        byte[] serialize = SerializationUtils.serialize(person);
        //Fazer o processo inverso, converter byte[] para o objeto
        Person deserialize = (Person) SerializationUtils.deserialize(byteMessage);


        //Fazendo o build de um Message pois é o que o método "send" do template do rabbitMQ solicita.
        Message message = MessageBuilder.withBody(byteMessage)
                .setHeader("item1", "mobile")
                .setHeader("item2", "television")
                .build();

        rabbitTemplate.send("Headers-Exchange", "", message);

        LOG.info("Message is: {}", deserialize);

        return "Success";
    }

    @GetMapping("/default-exchange/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String defaultExchange(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("", "Mobile", person);


        LOG.info("Message send to Mobile Queue was: {}", person);
        LOG.warn("Message send to Mobile Queue was: {}", person);
        return "Success";
    }

    //Poderia converter dessa meneira também
    private byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }
}
