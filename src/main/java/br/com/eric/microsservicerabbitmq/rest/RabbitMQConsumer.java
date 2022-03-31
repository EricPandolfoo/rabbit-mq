package br.com.eric.microsservicerabbitmq.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);

    // Consumer para os métodos de exchange.. Direct, Topic e Fanout
    @RabbitListener(queues = "Mobile")
    public void getMessageMobileQueue(Person person) {
        LOG.info("Message consumed for Mobile Queue: {}", person);
    }

    @RabbitListener(queues = "TV")
    public void getMessageTVQueue(Person person) {
        LOG.info("Message consumed for TV Queue: {}", person);
    }

    @RabbitListener(queues = "AC")
    public void getMessageACQueue(Person person) {
        LOG.info("Message consumed for AC Queue: {}", person);
    }


    //Consumer para Headers Exchange, pois o método que publica a mensagem necessita que seja em byte[].. diferente dos outros exchanges que
    // aceitam objeto.
    /*@RabbitListener(queues = "Mobile")
    public void getMessageHeadersExchange(byte[] message) throws IOException, ClassNotFoundException {

        //Desserializar mensagem byte[] para objeto Person
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput in = new ObjectInputStream(bis);
        Person person = (Person) in.readObject();
        in.close();
        bis.close();
        LOG.info("Desserialized message option 01: {}", person);

        //Forma muito mais simples de converter byte[] em objeto
        Person deserializedMessage = (Person) SerializationUtils.deserialize(message);
        LOG.info("Desserialized message option 02: {}", deserializedMessage);
    }*/
}
