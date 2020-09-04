package com.serdarsenturk;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException {
        System.out.println("Please Wait");
<<<<<<< HEAD
        SendMessage.sendMessage();
        SendMail.sendMail();
=======
        DemoConsumer.receiver();
>>>>>>> 2047b23781663d2cce6f04541c4a97b33cbd52c6
    }
}