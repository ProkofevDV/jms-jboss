package ru.luxoft.listeners;

import ru.luxoft.services.ITestJbossLocalBean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by dprokofev on 18.07.2017.
 */
@MessageDriven(name = "TestListenerEJB",
        activationConfig =
                {
                        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                        @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test")
                })
public class TestListenerBean implements MessageListener {

    @EJB
    private ITestJbossLocalBean bb;

    public TestListenerBean() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage message1 = (TextMessage) message;
        try {
            System.out.println(" onMessage(Message listeners) " + message1.getText() + bb.getName());
//            message1.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
