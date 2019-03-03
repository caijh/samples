package com.coding.samples;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {

    public void send(Connection connection, String queueName) throws JMSException {
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Queue testQueue = session.createQueue(queueName);
        MessageProducer producer = session.createProducer(testQueue);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("hello world");
        producer.send(textMessage);

        producer.close();
        session.close();
    }

}
