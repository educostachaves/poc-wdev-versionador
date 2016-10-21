package com.versionador.spring.jms;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.versionador.spring.domain.QueueList;
 
@Component
public class MsgProducer {
 
    @Resource
    private JmsTemplate jmsTemplate;
 
    @Resource(name="aprovedQueue")
    private Destination aprovedDestination;
    
    @Resource(name="failedQueue")
    private Destination failedDestination;
     
    public void sendMessage(final String message, final QueueList flag) throws JMSException {
        MessageCreator messageCreator = new MessageCreator() {
             public Message createMessage(Session session) throws JMSException {
                MapMessage msg = session.createMapMessage();
                msg.setString("message", message);
 
                return msg;
            }
        };
        
        if (flag == QueueList.APROVED) {
        	jmsTemplate.send(aprovedDestination, messageCreator);
        } else {
        	jmsTemplate.send(failedDestination, messageCreator);
        }
        
    }
    
}