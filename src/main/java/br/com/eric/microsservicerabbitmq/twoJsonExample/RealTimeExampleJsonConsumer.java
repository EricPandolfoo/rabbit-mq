package br.com.eric.microsservicerabbitmq.twoJsonExample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RealTimeExampleJsonConsumer {


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());

            JsonEntity jsonEntity = new ObjectMapper().readValue(message, JsonEntity.class);
            System.out.println("Message received = " + jsonEntity.toString());
        };
        channel.basicConsume("Queue-1", true, callback, consumerTag -> {});
    }
}
