package br.com.eric.microsservicerabbitmq.basics.topicExchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and AC";

        channel.basicPublish("Topic-Exchange", "tv.mobile.ac", null, message.getBytes());

        channel.close();
        connection.close();
    }
}
