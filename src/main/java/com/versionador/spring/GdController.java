package com.versionador.spring;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.versionador.spring.model.Gd;
import com.versionador.spring.service.GdService;

@Controller("/")
public class GdController {
	
	@Autowired
	private GdService gdService;
	
	@Qualifier(value="gdService")
	public void setGdService(GdService gs){
		this.gdService = gs;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listGds(Model model) {
		model.addAttribute("gd", new Gd());
		model.addAttribute("listGds", this.gdService.listGds());
		return "gd";
	}
		
	@RequestMapping("/gd/remove/{id}")
    public String removeGd(@PathVariable("id") int id){
        this.gdService.removeGd(id);
        return "redirect:/gds";
    }
	
	@RequestMapping(value = "/gd/download/{id}", method = RequestMethod.GET)
    public void downloadGd(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Gd gd= new Gd();
        gd = this.gdService.getGdById(id);
        
        byte[] pdf = gd.getArquivo();

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + gd.getNomeArquivo());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.getOutputStream().write(pdf);
    }
	
}