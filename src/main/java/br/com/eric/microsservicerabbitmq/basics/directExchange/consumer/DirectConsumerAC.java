package br.com.eric.microsservicerabbitmq.basics.directExchange.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectConsumerAC {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback callback = (consumerTag, delivery) -> {
          String message = new String (delivery.getBody());
          System.out.println("Message received: " + message);
        };
        channel.basicConsume("AC", true, callback, consumerTag -> {});

    }
}
