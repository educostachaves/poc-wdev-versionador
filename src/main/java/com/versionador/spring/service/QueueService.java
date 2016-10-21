package com.versionador.spring.service;

import java.util.List;

import javax.jms.JMSException;

import com.versionador.spring.model.QueueObj;

public interface QueueService {
	
	public List<QueueObj> ListQueue(String Queue) throws JMSException;
	
}
