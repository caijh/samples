package com.coding.samples;

import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

public class App {

    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        new Producer().send(connection, "testQueue");

        new Consumer().consume(connection, "testQueue");

        connection.close();
    }

}
