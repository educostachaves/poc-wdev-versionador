package com.versionador.spring.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

import com.versionador.spring.model.QueueObj;

public class QueueServiceImpl implements QueueService{

	@Override
	public List<QueueObj> ListQueue(String queueName) throws JMSException {
		List<QueueObj> listQueue = new ArrayList<QueueObj>();
		
	    ConnectionFactory factory =  new ActiveMQConnectionFactory();
	    Connection con = factory.createConnection();
	    Session session =  con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    con.start();
	    
		Queue queue = session.createQueue(queueName);
		QueueBrowser browser = session.createBrowser(queue);
		browser.getMessageSelector();
		Enumeration<?> messagesInQueue = browser.getEnumeration();

		while (messagesInQueue.hasMoreElements()) {
			QueueObj queueObj = new QueueObj();
			MapMessage mapmessage = (MapMessage) messagesInQueue.nextElement();
			queueObj.setArquivo(mapmessage.getString("message"));
			queueObj.setId(mapmessage.getJMSMessageID());
			listQueue.add(queueObj);
		}
		
		return listQueue;
	}
	
	

}
