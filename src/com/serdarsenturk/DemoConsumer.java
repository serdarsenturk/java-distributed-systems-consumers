package com.serdarsenturk;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class DemoConsumer {
    public static final String EX = "demo";

    public static void receiver() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EX,  BuiltinExchangeType.FANOUT);
        String queueName = channel.queueDeclare().getQueue();
        String queueName1 = channel.queueDeclare().getQueue();
        String queueName2 = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EX, "");
        channel.queueBind(queueName1, EX, "");
        channel.queueBind(queueName2, EX, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumeTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body){
                System.out.println(new String(body));
            }
        };

        channel.basicConsume(queueName, true, consumer);
    }
}