package br.com.eric.microsservicerabbitmq.rest.mvc;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class RabbitMQConsumerr {

    /*@RabbitListener(queues = "Mobile")
    public void getMessage(Person person){
        System.out.println(person);
    }*/
}
