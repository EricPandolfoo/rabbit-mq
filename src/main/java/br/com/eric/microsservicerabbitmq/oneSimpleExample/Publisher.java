package br.com.eric.microsservicerabbitmq.oneSimpleExample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {


    public static void main(String[] args) throws IOException, TimeoutException {

        //Configurações para publicar a mensagem na fila.
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //String message = "Message to the queue-1";
        String[] messages = {"First", "Second", "Third", "Fourth"};
        for (String message : messages) {
            channel.basicPublish("", "Queue-1", null, message.getBytes());
        }

        channel.close();
        connection.close();
    }
}
