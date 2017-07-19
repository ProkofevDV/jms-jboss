package ru.luxoft.web;

import ru.luxoft.listeners.TestListenerBean;
import ru.luxoft.services.ITestJbossRemoteBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dprokofev on 17.07.2017.
 */
@WebServlet(name = "ServletIt", urlPatterns = "/dimazz")
public class ServletIt extends HttpServlet {

    @EJB
    private ITestJbossRemoteBean bb;


    @Resource(name = "java:/ConnectionFactory")
    private ConnectionFactory cf;

//    @Resource(name = "jms/RemoteConnectionFactory")
//    private ConnectionFactory cf2;

    @Resource(name = "java:jboss/exported/jms/topic/test")
    private Topic testTopic;

//    @Resource(name="queueTest/test")
//    private HornetQQueue testQueue;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("do post");

        try {
            TopicConnection connection = ((TopicConnectionFactory) cf).createTopicConnection();
            TopicSession topicSession = connection.createTopicSession(false, TopicSession.CLIENT_ACKNOWLEDGE);

            connection.start();

            TopicSubscriber topicSubscriber = topicSession.createSubscriber(testTopic);
            topicSubscriber.setMessageListener(new TestListenerBean());

            TopicPublisher publisher = topicSession.createPublisher(testTopic);

            TextMessage teext = topicSession.createTextMessage("dimaz say: " + request.getParameter("teext") + " " + request.getContextPath());
            publisher.publish(teext);
            publisher.close();
            topicSession.close();
//            connection.close();
            int x = 6;


        } catch (JMSException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            (new InitialContext()).lookup("java:jboss/datasources/ExampleDS").getClass();
            (new InitialContext()).lookup("java:jboss/mail/Default").getClass();
            int x = 6;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        System.out.println("do get");
    }
}
