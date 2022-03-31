package br.com.eric.microsservicerabbitmq.basics.fanoutExchange.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and AC";

        channel.basicPublish("Fanout-Exchange", Strings.EMPTY, null,message.getBytes());

        channel.close();
        connection.close();
    }
}
