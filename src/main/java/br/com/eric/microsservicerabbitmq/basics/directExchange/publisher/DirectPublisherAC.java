package br.com.eric.microsservicerabbitmq.basics.directExchange.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisherAC {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

       String message = "This is ac";

       //channel.basicPublish("Direct-Exchange", "ac", null, message.getBytes());
       channel.basicPublish("Direct-Exchange", "ac", null, message.getBytes());

       channel.close();
       connection.close();
    }
}
