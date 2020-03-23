import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @author: huajiang
 * @DATE: 2020-03-17
 * @DESCRIPTION:
 * @MODIFIED BY:
 */
public class myTest {

    private static final String mqurl = "tcp://localhost:61616";

    /**
     *
     * @throws Exception
     */
    @Test
    public void sendMessage() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqurl);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQueue");
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 10; i++) {
            TextMessage message = session.createTextMessage("发送第" + i + "条信息");
            producer.send(message);
        }
        connection.close();
    }

    @Test
    public void receiveMessage() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqurl);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQueue");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage message1 = (TextMessage) message;
                try {
                    System.out.println(message1.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Test
    public void receiveMessage2() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(mqurl);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("myQueue");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage message1 = (TextMessage) message;
                try {
                    System.out.println(message1.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
