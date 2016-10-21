package com.versionador.spring;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.versionador.spring.service.QueueService;

@Controller("/queue")
public class QueueController {
		
	@Autowired
	private QueueService qs;
	
	@Qualifier(value="queueService")
	public void setQueueService(QueueService qs){
		this.qs = qs;
	}
	
	@RequestMapping(value = "/failed", method = RequestMethod.GET)
    public String queueAproved(Model model){
        try {
        	model.addAttribute("listQueue", qs.ListQueue("failedQueue"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
        return "queue";
    }
	
	
}
