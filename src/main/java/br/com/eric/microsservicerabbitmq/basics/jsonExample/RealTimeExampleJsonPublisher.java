package br.com.eric.microsservicerabbitmq.basics.jsonExample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RealTimeExampleJsonPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject json = new JSONObject();
        json.put("fromDate", "20/03/2022");
        json.put("toDate", "29/03/2022");
        json.put("email", "eric.pandolfo@gmail.com");
        json.put("query", "select * from data");

        channel.basicPublish("", "Queue-1", null, json.toString().getBytes());

        channel.close();
        connection.close();
    }
}
