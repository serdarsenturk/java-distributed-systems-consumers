package com.serdarsenturk;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SendMessage {
    public static final String EX = "demo1";

    public static void sendMessage() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqps://ymmlbeft:IPSFfJDg35dEtLtQttfQNKnIw0XCRokl@sparrow.rmq.cloudamqp.com/ymmlbeft");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EX, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EX, "one");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumeTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body){
                System.out.println("Message received : " + new String(body));
            }
        };

        channel.basicConsume(queueName, true, consumer);
    }
}
